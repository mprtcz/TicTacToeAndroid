package com.mprtcz.tictactoe.game.service;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.game.model.GameRecord;
import com.mprtcz.tictactoe.game.model.TicTacToeGame;
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

    private Callback<List<TicTacToeGame>> getExistingGamesCallback(final TableLayout tableLayout) {
        return new Callback<List<TicTacToeGame>>() {
            @Override
            public void onResponse(Call<List<TicTacToeGame>> call, Response<List<TicTacToeGame>> response) {
                if (response.code() == 200) {
                    List<TicTacToeGame> existingGames = response.body();
                    populateExistingGamesTable(existingGames, tableLayout);
                }
            }

            @Override
            public void onFailure(Call<List<TicTacToeGame>> call, Throwable t) {
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

    private void populateExistingGamesTable(List<TicTacToeGame> existingGames, TableLayout tableLayout) {
        tableLayout.addView(getExistingGameTableHeader(tableLayout.getContext()));
        for (TicTacToeGame game : existingGames) {
            tableLayout.addView(getExistingGameTableRow(game, tableLayout.getContext()));
        }
    }

    private TableRow getExistingGameTableHeader(Context context) {
        TableRow tableHeader = new TableRow(context);
        TextView gameHostTExtView = new TextView(context);
        gameHostTExtView.setText(R.string.game_host_table_header);
        TextView secondPlayerTextView = new TextView(context);
        secondPlayerTextView.setText(R.string.second_player_table_header);
        tableHeader.addView(gameHostTExtView);
        tableHeader.addView(secondPlayerTextView);
        return tableHeader;
    }

    private TableRow getExistingGameTableRow(TicTacToeGame existingGame, Context context) {
        TableRow tableRow = new TableRow(context);
        TextView gameHostTextView = new TextView(context);
        gameHostTextView.setText(existingGame.getGameHost());
        View secondPlayer;
        if (existingGame.getSecondPlayer() == null) {
            Button joinButton = new Button(context);
            joinButton.setText(R.string.join_specific_game_button_text);
            secondPlayer = joinButton;
        } else {
            TextView secondPlayerTextView = new TextView(context);
            secondPlayerTextView.setText(existingGame.getSecondPlayer());
            secondPlayer = secondPlayerTextView;
        }
        tableRow.addView(gameHostTextView);
        tableRow.addView(secondPlayer);
        return tableRow;
    }

    public static GameService getInstance(AsyncService asyncService) {
        if (gameService == null) {
            gameService = new GameService(asyncService);
        }
        return gameService;
    }
}
