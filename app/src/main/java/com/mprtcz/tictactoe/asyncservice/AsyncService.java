package com.mprtcz.tictactoe.asyncservice;

import com.mprtcz.tictactoe.user.endpoint.UserEndpoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Azet on 2017-03-23.
 */

public class AsyncService {
    
    public void getUsersFromServerAsync(Callback<List<String>> callback) {
        UserEndpoint taskEndpoint = RetrofitUtils.getUserEndpointRetrofit();
        Call<List<String>> activitiesCall = taskEndpoint.getOnlineUsernames();
        activitiesCall.enqueue(callback);
    }
}