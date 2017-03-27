package com.mprtcz.tictactoe.uielements;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.game.model.GameMove;
import com.mprtcz.tictactoe.game.model.GameRecord;

import java.util.List;

/**
 * Created by Azet on 2017-03-27.
 */

public class GameRecordsTable {
    private Activity activity;

    public GameRecordsTable(Activity activity) {
        this.activity = activity;
    }

    public void populateTableRows(TableLayout tableLayout, List<GameRecord> gameRecords) {
        tableLayout.addView(getTableHeaders(activity));
        for (GameRecord gameRecord :
                gameRecords) {
            tableLayout.addView(getTableRow(gameRecord));
        }
    }

    private TableRow getTableRow(GameRecord gameRecord) {
        TableRow tableRow = new TableRow(activity);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        tableRow.setLayoutParams(lp);
        tableRow.setPadding(40, 5, 40, 5);
        TextView firstPlayerTextView = new TextView(activity);
        firstPlayerTextView.setText(gameRecord.getPlayerOne().getNickname());
        TextView secondPlayerTextView = new TextView(activity);
        secondPlayerTextView.setText(gameRecord.getPlayerTwo().getNickname());
        TextView dateTextView = new TextView(activity);
        dateTextView.setText(gameRecord.getDateTime().getFormattedDate());
        Button actionButton = new Button(activity);
        addGameMovesButtonListener(actionButton, gameRecord);
        actionButton.setText(R.string.show_moves_button);
        tableRow.addView(firstPlayerTextView);
        tableRow.addView(secondPlayerTextView);
        tableRow.addView(dateTextView);
        tableRow.addView(actionButton);
        return tableRow;
    }

    private void addGameMovesButtonListener(Button button, final GameRecord gameRecord) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage(composeDialogHeaderMessage(gameRecord))
                        .setView(GameMovesTable.getScrolledTable(activity, gameRecord))
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.create().show();
            }
        });
    }

    private TableRow getTableHeaders(Activity activity) {
        TableRow tableRow = new TableRow(activity);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        tableRow.setLayoutParams(lp);
        tableRow.setPadding(40, 5, 40, 5);
        TextView firstPlayerTextView = new TextView(activity);
        firstPlayerTextView.setText(R.string.table_row_first_player_header);
        TextView secondPlayerTextView = new TextView(activity);
        secondPlayerTextView.setText(R.string.table_row_second_player_header);
        TextView dateTextView = new TextView(activity);
        dateTextView.setText(R.string.table_row_date_header);
        TextView actionTextView = new TextView(activity);
        actionTextView.setText(R.string.table_row_action);
        tableRow.addView(firstPlayerTextView);
        tableRow.addView(secondPlayerTextView);
        tableRow.addView(dateTextView);
        tableRow.addView(actionTextView);
        return tableRow;
    }

    private static String composeDialogHeaderMessage(GameRecord gameRecord) {
        if (gameRecord.getDateTime() == null ||
                gameRecord.getPlayerOne() == null ||
                gameRecord.getPlayerTwo() == null) {
            return "";
        }
        return "Game " +
                gameRecord.getDateTime().getFormattedDate() +
                " between " +
                gameRecord.getPlayerOne().getNickname() +
                " (O) and " +
                gameRecord.getPlayerTwo().getNickname() +
                " (X)";
    }

    /**
     * Created by Azet on 2017-03-27.
     */

    private static class GameMovesTable {

        static ScrollView getScrolledTable(Activity activity, GameRecord gameRecord) {
            ScrollView scrollView = new ScrollView(activity);
            scrollView.addView(getGameMovesTableLayout(activity, gameRecord));
            return scrollView;
        }

        static TableLayout getGameMovesTableLayout(Activity activity, GameRecord gameRecord) {
            TableLayout tableLayout = new TableLayout(activity);
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            tableLayout.setLayoutParams(params);
            tableLayout.setStretchAllColumns(true);
            tableLayout.addView(getGameMoveTableHeaders(activity));
            for (GameMove gameMove : gameRecord.getGameMovesList()) {
                tableLayout.addView(getGameMoveTableRow(activity, gameMove));
            }
            return tableLayout;
        }

        private static TableRow getGameMoveTableRow(Activity activity, GameMove gameMove) {
            TableRow tableRow = new TableRow(activity);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            tableRow.setLayoutParams(lp);
            tableRow.setPadding(40, 5, 40, 5);
            TextView fieldTextView = new TextView(activity);
            TextView symbolTextView = new TextView(activity);
            TextView dateTextView = new TextView(activity);
            fieldTextView.setText(gameMove.getField());
            symbolTextView.setText(gameMove.getSymbol());
            dateTextView.setText(gameMove.getDateTime().getFormattedTime());
            tableRow.addView(fieldTextView);
            tableRow.addView(symbolTextView);
            tableRow.addView(dateTextView);
            return tableRow;
        }

        private static TableRow getGameMoveTableHeaders(Activity activity) {
            TableRow tableRow = new TableRow(activity);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            tableRow.setLayoutParams(lp);
            tableRow.setPadding(40, 5, 40, 5);
            TextView fieldTextView = new TextView(activity);
            TextView symbolTextView = new TextView(activity);
            TextView dateTextView = new TextView(activity);
            fieldTextView.setText(R.string.field_header_game_move);
            symbolTextView.setText(R.string.symbol_header_game_move);
            dateTextView.setText(R.string.time_header_game_move);
            tableRow.addView(fieldTextView);
            tableRow.addView(symbolTextView);
            tableRow.addView(dateTextView);
            return tableRow;
        }
    }
}
