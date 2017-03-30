package com.mprtcz.tictactoe.asyncservice;

import com.mprtcz.tictactoe.game.endpoint.GameEndpoint;
import com.mprtcz.tictactoe.game.endpoint.TicTacToeEndpoint;
import com.mprtcz.tictactoe.game.model.GameRecord;
import com.mprtcz.tictactoe.game.model.TicTacToeGameDTO;
import com.mprtcz.tictactoe.user.endpoint.UserEndpoint;
import com.mprtcz.tictactoe.user.model.NewUser;
import com.mprtcz.tictactoe.user.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by Azet on 2017-03-23.
 */

public class AsyncService {
    
    public void getUsersFromServerAsync(Callback<List<String>> callback) {
        UserEndpoint taskEndpoint = RetrofitUtils.getUserEndpointRetrofit();
        Call<List<String>> usersListCall = taskEndpoint.getOnlineUsernames();
        usersListCall.enqueue(callback);
    }

    public void authenticateUser(Callback<User> callback, String header) {
        UserEndpoint userEndpoint = RetrofitUtils.getUserEndpointRetrofit();
        Call<User> userCall = userEndpoint.authenticateUser(header);
        userCall.enqueue(callback);
    }

    public void registerNewUserOnServer(Callback<Void> callback, NewUser newUser) {
        UserEndpoint userEndpoint = RetrofitUtils.getUserEndpointRetrofit();
        userEndpoint.registerNewUser(newUser);
        Call<Void> registerNewUserCall = userEndpoint.registerNewUser(newUser);
        registerNewUserCall.enqueue(callback);
    }

    public void getUserGameHistory(Callback<List<GameRecord>> callback, String username, String sessionId) {
        GameEndpoint gameEndpoint = RetrofitUtils.getGameEndpointRetrofit();
        Call<List<GameRecord>> userGameHistoryCall = gameEndpoint.getUserHistory(username, sessionId);
        userGameHistoryCall.enqueue(callback);
    }

    public void getActiveTTTGames(Callback<List<TicTacToeGameDTO>> callback, String sessionId) {
        GameEndpoint gameEndpoint = RetrofitUtils.getGameEndpointRetrofit();
        Call<List<TicTacToeGameDTO>> gamesCall = gameEndpoint.getTicTacToeGames(sessionId);
        gamesCall.enqueue(callback);
    }

    public void createTicTacToeGame(Callback<String> callback, String sessionId) {
        TicTacToeEndpoint ticTacToeEndpoint = RetrofitUtils.getTicTacToeEndpointRetrofit();
        Call<String> createTicTacToeCall = ticTacToeEndpoint.createTicTacToeGame(sessionId);
        createTicTacToeCall.enqueue(callback);
    }

    public Retrofit getRetrofit() {
        return RetrofitUtils.getInstance();
    }

    public void getTTTGameState(Callback<TicTacToeGameDTO> callback, String gameHost, String sessionId) {
        TicTacToeEndpoint ticTacToeEndpoint = RetrofitUtils.getTicTacToeEndpointRetrofit();
        Call<TicTacToeGameDTO> getTicTacToeStateCall = ticTacToeEndpoint.getTTTGameState(gameHost, sessionId);
        getTicTacToeStateCall.enqueue(callback);
    }

    public void sendInsertedSign(Callback<String> callback, String coordinatesString, String sessionId) {
        TicTacToeEndpoint ticTacToeEndpoint = RetrofitUtils.getTicTacToeEndpointRetrofit();
        Call<String> insertSymbolCall = ticTacToeEndpoint.sendTTTInsertedSymbol(coordinatesString, sessionId);
        insertSymbolCall.enqueue(callback);

    }
}