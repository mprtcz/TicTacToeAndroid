package com.mprtcz.tictactoe.game.service;

import android.app.Activity;
import android.util.Log;
import android.widget.TableLayout;

import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.game.model.GameRecord;
import com.mprtcz.tictactoe.uielements.GameRecordsTable;
import com.mprtcz.tictactoe.utils.LoggedUserDataStore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Azet on 2017-03-23.
 */

public class GameService {
    private static final String TAG = "GameService";
    private AsyncService asyncService;

    public GameService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    public void getGamesHistoryFromServer(Activity activity, TableLayout tableLayout) {
        this.asyncService.getUserGameHistory(getUserHistoryCallback(activity, tableLayout),
                LoggedUserDataStore.getLoggedInUser().getSsoId(), LoggedUserDataStore.getSessionId());
    }

    private Callback<List<GameRecord>> getUserHistoryCallback(final Activity activity, final TableLayout tableLayout) {
        return new Callback<List<GameRecord>>() {
            @Override
            public void onResponse(Call<List<GameRecord>> call, Response<List<GameRecord>> response) {
                if (response.code() == 200) {
                    List<GameRecord> gameRecords = response.body();
                    Log.d(TAG, "Game records : " + gameRecords.toString());
                    populateTableRows(activity, tableLayout, gameRecords);
                    LoggedUserDataStore.setGameRecords(gameRecords);
                }
            }

            @Override
            public void onFailure(Call<List<GameRecord>> call, Throwable t) {
                Log.e(TAG, "Game records request failure: " + t.toString());
            }
        };
    }

    public void populateTableRows(Activity activity, TableLayout tableLayout, List<GameRecord> gameRecords) {
        GameRecordsTable gameRecordsTable = new GameRecordsTable(activity);
        gameRecordsTable.populateTableRows(tableLayout, gameRecords);
    }
}
