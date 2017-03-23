package com.mprtcz.tictactoe.user.service;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.user.model.User;

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

    public UserService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    public void fillListWithUsersFromServer(ArrayAdapter<String> userArrayAdapter) {
        Log.d(TAG, "fillListWithUsersFromServer() called with: userArrayAdapter = [" + userArrayAdapter + "]");
        this.asyncService.getUsersFromServerAsync(getUsersCallback(userArrayAdapter));
    }

    private Callback<List<String>> getUsersCallback(final ArrayAdapter<String> userArrayAdapter) {
        return new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()) {
                    List<String> users = response.body();
                    Log.d(TAG, "users: " + users.toString());
                    userArrayAdapter.addAll(users);
                }
                Log.d(TAG, "response.body: " + response.body());
                Log.d(TAG, "response.message: " + response.message());
                Log.d(TAG, "response.code: " + response.code());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e(TAG, "Failure to call users: " + call.toString() +" with throwable " + t.toString());
            }
        };
    }
}
