package com.mprtcz.tictactoe.game.service;

import android.os.Handler;
import android.util.Log;

import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.game.model.TicTacToeGame;
import com.mprtcz.tictactoe.game.model.TicTacToeGameDTO;
import com.mprtcz.tictactoe.utils.LoggedUserDataStore;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Azet on 30.03.2017.
 */

public class TicTacToeService {
    private static final String TAG = "TicTacToeService";
    private AsyncService asyncService;
    private TicTacToeGame ticTacToeGame;
    private boolean isGameOn = false;


    public TicTacToeService(AsyncService asyncService, TicTacToeGame ticTacToeGame) {
        this.asyncService = asyncService;
        this.ticTacToeGame = ticTacToeGame;
    }

    public void createTicTacToeGame() {
        this.asyncService.createTicTacToeGame(getTTTCreationCallback(), LoggedUserDataStore.getSessionId());
    }

    private Callback<String> getTTTCreationCallback() {
        return new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    ticTacToeGame.setGameHost(LoggedUserDataStore.getLoggedInUser().getSsoId());
                    ticTacToeGame.setPlayersSign("O");
                    getGameState();
                    setUpTimer();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i(TAG, "onFailure: Request failure during game creation" + t.toString());
            }
        };
    }

    private Callback<TicTacToeGameDTO> getGameStateCallback() {
        return new Callback<TicTacToeGameDTO>() {
            @Override
            public void onResponse(Call<TicTacToeGameDTO> call, Response<TicTacToeGameDTO> response) {
                if (response.code() == 200) {
                    TicTacToeGameDTO gameDto = response.body();
                    ticTacToeGame.updateGameDto(gameDto);
                    isGameOn = true;
                }
            }

            @Override
            public void onFailure(Call<TicTacToeGameDTO> call, Throwable t) {
                Log.i(TAG, "onFailure: Failure during game state fetching: " + t.toString());
            }
        };
    }

    private void setUpTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (ticTacToeGame.getTicTacToeGameDTO().getSecondPlayer() != null) {
                    getGameState();
                } else {
                    checkIfSomeoneJoinedGame();
                }
                if (ticTacToeGame.getTicTacToeGameDTO().getWinner() != null) {
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    public void insertSymbol(int index) {
        if (this.ticTacToeGame.getGameArray()[index].equals(" ") && !this.ticTacToeGame.isGameDisabled()) {
            this.ticTacToeGame.getGameArray()[index] = this.ticTacToeGame.getPlayersSign();
            this.sendInsertion(index);
        }
    }

    private void sendInsertion(int index) {
        this.asyncService.sendInsertedSign(getInsertionCallback(), parseCoordinates(index), LoggedUserDataStore.getSessionId());
    }

    private Callback<String> getInsertionCallback() {
        return new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code() == 200) {
                    Log.i(TAG, "onResponse: ALL OK");
                } else {
                    try {
                        Log.w(TAG, "onResponse: Error during symbol insertion: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.w(TAG, "onFailure: Failure during symbol insertion " +t.toString());
            }
        };
    }

    private String parseCoordinates(int index) {
        int firstCoordinate = index / 3;
        int secondCoordinate = index % 3;
        return firstCoordinate + ":" + secondCoordinate;
    }


    private void checkIfSomeoneJoinedGame() {
        getGameState();
    }

    private void getGameState() {
        this.asyncService.getTTTGameState(getGameStateCallback(), LoggedUserDataStore.getLoggedInUser().getSsoId(), LoggedUserDataStore.getSessionId());
    }
}