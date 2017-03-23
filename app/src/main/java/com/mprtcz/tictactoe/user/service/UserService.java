package com.mprtcz.tictactoe.user.service;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mprtcz.tictactoe.asyncservice.AsyncService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Azet on 2017-03-23.
 */

public class UserService {
    public static final String TAG = "UserService";
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

    private Callback<List<String>> getUsersCallback(final ArrayAdapter<String> userArrayAdapter, final SummaryPresenter summaryPresenter) {
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
