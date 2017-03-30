package com.mprtcz.tictactoe.game.endpoint;

import com.mprtcz.tictactoe.game.model.TicTacToeGameDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

/**
 * Created by Azet on 30.03.2017.
 */

public interface TicTacToeEndpoint {

    @GET("/api/tictactoe/create")
    Call<String> createTicTacToeGame(@Header("Cookie") String jSessionId);

    @GET("/api/tictactoe/{gameHost}/game")
    Call<TicTacToeGameDTO> getTTTGameState(@Path("gameHost") String gameHost, String sessionId);

    @PATCH("/api/tictactoe/move")
    Call<String> sendTTTInsertedSymbol(String coordinatesString, String sessionId);
}
