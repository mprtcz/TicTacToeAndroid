package com.mprtcz.tictactoe.game.endpoint;

import com.mprtcz.tictactoe.game.model.GameRecord;
import com.mprtcz.tictactoe.game.model.TicTacToeGameDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

/**
 * Created by Azet on 2017-03-23.
 */

public interface GameEndpoint {

    @PATCH("/api/games/{username}")
    Call<List<GameRecord>> getUserHistory(@Path("username") String username, @Header("Cookie") String jSessionId);

    @GET("/api/games/")
    Call<List<TicTacToeGameDTO>> getTicTacToeGames(@Header("Cookie") String jSessionId);


}
