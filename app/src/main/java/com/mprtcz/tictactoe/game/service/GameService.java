package com.mprtcz.tictactoe.game.service;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.game.model.GameRecord;
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
                    tableLayout.setVisibility(View.VISIBLE);
                    for (GameRecord gameRecord :
                            gameRecords) {
                        tableLayout.addView(getTableRow(activity, gameRecord));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GameRecord>> call, Throwable t) {
                Log.e(TAG, "Game records request failure: " + t.toString());
            }
        };
    }

    private TableRow getTableRow(Activity activity, GameRecord gameRecord) {
        TableRow tableRow = new TableRow(activity);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        tableRow.setLayoutParams(lp);
        TextView firstPlayerTextView = new TextView(activity);
        firstPlayerTextView.setText(gameRecord.getPlayerOne().getNickname());
        TextView secondPlayerTextView = new TextView(activity);
        secondPlayerTextView.setText(gameRecord.getPlayerTwo().getNickname());
        TextView dateTextView = new TextView(activity);
        dateTextView.setText(gameRecord.getDateTime().getFormattedDate());
        Button actionButton = new Button(activity);
        actionButton.setText(R.string.show_moves_button);
        tableRow.addView(firstPlayerTextView);
        tableRow.addView(secondPlayerTextView);
        tableRow.addView(dateTextView);
        tableRow.addView(actionButton);
        return tableRow;
    }
}
