package com.mprtcz.tictactoe.user.endpoint;

import com.mprtcz.tictactoe.user.model.NewUser;
import com.mprtcz.tictactoe.user.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

/**
 * Created by Azet on 2017-03-23.
 */

public interface UserEndpoint {

    @GET("api/users/online")
    Call<List<String>> getOnlineUsernames();

    @DELETE("/api/users/profile")
    Call<String> deleteUser();

    @POST("/api/users/add")
    Call<String> registerNewUser(@Body NewUser newUser);

    @PATCH("/api/users/profile")
    Call<String> updateUser(@Body User user);

    @GET("/api/users/profile")
    Call<User> authenticateUser(@Header("Authorization") String authorization);

}
