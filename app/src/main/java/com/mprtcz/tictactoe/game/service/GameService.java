package com.mprtcz.tictactoe.game.service;

import android.app.Activity;
import android.util.Log;
import android.widget.TableLayout;

import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.game.model.GameRecord;
import com.mprtcz.tictactoe.game.model.TicTacToeGameDTO;
import com.mprtcz.tictactoe.uielements.GameRecordsTable;
import com.mprtcz.tictactoe.utils.LoggedUserDataStore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mprtcz.tictactoe.utils.UiTablesComposer.getExistingGameTableHeader;
import static com.mprtcz.tictactoe.utils.UiTablesComposer.getExistingGameTableRow;

/**
 * Created by Azet on 2017-03-23.
 */

public class GameService {
    private static final String TAG = "GameService";
    private AsyncService asyncService;
    private static GameService gameService;

    public GameService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    public void getGamesHistoryFromServer(Activity activity, TableLayout tableLayout) {
        this.asyncService.getUserGameHistory(getUserHistoryCallback(activity, tableLayout),
                LoggedUserDataStore.getLoggedInUser().getSsoId(), LoggedUserDataStore.getSessionId());
    }

    public void getExistingGamesFromServer(TableLayout tableLayout) {
        this.asyncService.getActiveTTTGames(getExistingGamesCallback(tableLayout), LoggedUserDataStore.getSessionId());
    }

    private Callback<List<TicTacToeGameDTO>> getExistingGamesCallback(final TableLayout tableLayout) {
        return new Callback<List<TicTacToeGameDTO>>() {
            @Override
            public void onResponse(Call<List<TicTacToeGameDTO>> call, Response<List<TicTacToeGameDTO>> response) {
                if (response.code() == 200) {
                    List<TicTacToeGameDTO> existingGames = response.body();
                    Log.i(TAG, "onResponse: existingGames = " + existingGames.toString());
                    populateExistingGamesTable(existingGames, tableLayout);
                }
            }

            @Override
            public void onFailure(Call<List<TicTacToeGameDTO>> call, Throwable t) {
                Log.i(TAG, "onFailure: Existing games request failed " + t.toString());
                t.printStackTrace();
            }
        };
    }

    private Callback<List<GameRecord>> getUserHistoryCallback(final Activity activity, final TableLayout tableLayout) {
        return new Callback<List<GameRecord>>() {
            @Override
            public void onResponse(Call<List<GameRecord>> call, Response<List<GameRecord>> response) {
                if (response.code() == 200) {
                    List<GameRecord> gameRecords = response.body();
                    Log.d(TAG, "Game records : " + gameRecords.toString());
                    populateGameRecordsTableRows(activity, tableLayout, gameRecords);
                    LoggedUserDataStore.setGameRecords(gameRecords);
                }
            }

            @Override
            public void onFailure(Call<List<GameRecord>> call, Throwable t) {
                Log.e(TAG, "Game records request failure: " + t.toString());
            }
        };
    }

    public void populateGameRecordsTableRows(Activity activity, TableLayout tableLayout, List<GameRecord> gameRecords) {
        GameRecordsTable gameRecordsTable = new GameRecordsTable(activity);
        gameRecordsTable.populateTableRows(tableLayout, gameRecords);
    }

    private void populateExistingGamesTable(List<TicTacToeGameDTO> existingGames, TableLayout tableLayout) {
        tableLayout.addView(getExistingGameTableHeader(tableLayout.getContext()));
        for (TicTacToeGameDTO game : existingGames) {
            tableLayout.addView(getExistingGameTableRow(game, tableLayout.getContext()));
        }
    }

    public static GameService getInstance(AsyncService asyncService) {
        if (gameService == null) {
            gameService = new GameService(asyncService);
        }
        return gameService;
    }
}
