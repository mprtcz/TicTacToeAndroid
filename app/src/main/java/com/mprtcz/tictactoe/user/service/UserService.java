package com.mprtcz.tictactoe.user.service;

import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.interfaces.UserLogin;
import com.mprtcz.tictactoe.interfaces.UserRegister;
import com.mprtcz.tictactoe.user.model.NewUser;
import com.mprtcz.tictactoe.user.model.User;
import com.mprtcz.tictactoe.user.model.UserRegistrationError;
import com.mprtcz.tictactoe.utils.LoggedUserDataStore;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Azet on 2017-03-23.
 */

public class UserService {
    private static final String TAG = "UserService";
    private AsyncService asyncService;
    private SummaryPresenter summaryPresenter;

    public UserService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    public void getServerSummary(ArrayAdapter<String> userArrayAdapter, TextView summaryTextView, ArrayAdapter<String> games) {
        this.summaryPresenter = new SummaryPresenter(summaryTextView);
        fillListWithUsersFromServer(userArrayAdapter, this.summaryPresenter);
    }

    public void fillListWithUsersFromServer(ArrayAdapter<String> userArrayAdapter, SummaryPresenter summaryPresenter) {
        this.asyncService.getUsersFromServerAsync(getUsersCallback(userArrayAdapter, summaryPresenter));
    }

    private Callback<List<String>> getUsersCallback(final ArrayAdapter<String> userArrayAdapter,
                                                    final SummaryPresenter summaryPresenter) {
        return new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()) {
                    List<String> users = response.body();
                    summaryPresenter.setOnlineUsersNumber(users.size());
                    Log.d(TAG, "users: " + users.toString());
                    userArrayAdapter.addAll(users);
                } else {
                    Log.d(TAG, "response.code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e(TAG, "Failure to call users: " + call.toString() +" with throwable " + t.toString());
            }
        };
    }

    private Callback<User> getAuthenticationCallback(final UserLogin userLogin) {
        return new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200) {
                    Headers headers = response.headers();
                    if(headers.names().contains("Set-Cookie")) {
                        LoggedUserDataStore.setLoggedUserAndSession(response.body(),
                                getSessionIdString(headers.get("Set-Cookie")));
                    }
                    userLogin.redirectSuccessfulLogin();
                } else {
                    userLogin.setBadCredentialsErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Response unsuccessful, " + t.toString());
            }
        };
    }

    private static String getSessionIdString(String cookieString) {
        String[] cookieParts = cookieString.split(";");
        for (String s :
                cookieParts) {
            if(s. contains("JSESSIONID=")) {
                return s;
            }
        }
        return "";
    }

    public void authenticateUser(UserLogin userLogin) {
        String headerData = "Basic " +new String(encodeUserAndPassword(
                userLogin.getLoginUsername(),
                userLogin.getLoginPassword()));
        this.asyncService.authenticateUser(getAuthenticationCallback(userLogin), headerData.replace("\n", ""));
    }

    private byte[] encodeUserAndPassword(String name, String password) {
        String joinedData = name + ":" + password;
        byte[] data = joinedData.getBytes();
        return Base64.encode(data, Base64.DEFAULT);
    }

    public void registerNewUserOnServer(UserRegister userRegister, NewUser newUser) {
        this.asyncService.registerNewUserOnServer(getUserRegistrationCallback(userRegister), newUser);
    }

    private Callback<Void> getUserRegistrationCallback(final UserRegister userRegister) {
        return new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200) {
                    userRegister.successfulRegistrationRedirect();
                } else {
                    try {
                        Converter<ResponseBody, UserRegistrationError[]> converter =
                        asyncService.getRetrofit().responseBodyConverter(UserRegistrationError[].class, new Annotation[0]);
                        UserRegistrationError[] errors = converter.convert(response.errorBody());
                        userRegister.showBackendErrorResponse(errors);

                        Log.i(TAG, "onResponse: parsed errors = " + Arrays.toString(errors));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "onFailure: REGISTRATION ERROR " +t.toString());
            }
        };

    }

    private class SummaryPresenter {
        private int onlineUsersNumber = 0;
        private int onlineGamesNumber = 0;
        TextView summaryTextView;

        SummaryPresenter(TextView summaryTextView) {
            this.summaryTextView = summaryTextView;
        }

        void setOnlineUsersNumber(int onlineUsersNumber) {
            this.onlineUsersNumber = onlineUsersNumber;
            updateSummaryTextView();
        }

        public void setOnlineGamesNumber(int onlineGamesNumber) {
            this.onlineGamesNumber = onlineGamesNumber;
            updateSummaryTextView();
        }

        private void updateSummaryTextView() {
            this.summaryTextView.setText(composeText());
        }

        private String composeText() {
            return "Online users: " + this.onlineUsersNumber
                    +"\nOnline games: " + this.onlineGamesNumber;

        }
    }
}
