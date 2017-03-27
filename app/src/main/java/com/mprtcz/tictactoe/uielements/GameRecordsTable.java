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
        tableLayout.addView(getRecordTableHeaders());
        for (GameRecord gameRecord :
                gameRecords) {
            tableLayout.addView(getTableRowOfRecord(gameRecord));
        }
    }

    private TableRow getTableRowOfRecord(GameRecord gameRecord) {
        TableRow tableRow = getRecordTableRow();
        tableRow.addView(getTextView(gameRecord.getPlayerOne().getNickname()));
        tableRow.addView(getTextView(gameRecord.getPlayerTwo().getNickname()));
        tableRow.addView(getTextView(gameRecord.getDateTime().getFormattedDate()));
        tableRow.addView(getButton(activity.getString(R.string.show_moves_button), getGameMovesButtonListener(gameRecord)));
        return tableRow;
    }

    private View.OnClickListener getGameMovesButtonListener(final GameRecord gameRecord) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage(composeDialogHeaderMessage(gameRecord))
                        .setView(getScrolledMovesTable(gameRecord))
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.create().show();
            }
        };
    }

    private TableRow getRecordTableHeaders() {
        TableRow tableRow = getRecordTableRow();
        tableRow.addView(getTextView(activity.getString(R.string.table_row_first_player_header)));
        tableRow.addView(getTextView(activity.getString(R.string.table_row_second_player_header)));
        tableRow.addView(getTextView(activity.getString(R.string.table_row_date_header)));
        tableRow.addView(getTextView(activity.getString(R.string.table_row_action)));
        return tableRow;
    }

    private Button getButton(String text, View.OnClickListener listener) {
        Button button = new Button(activity);
        button.setText(text);
        button.setOnClickListener(listener);
        return button;
    }

    private TextView getTextView(String text) {
        TextView textView = new TextView(activity);
        textView.setText(text);
        return textView;
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


    private ScrollView getScrolledMovesTable(GameRecord gameRecord) {
        ScrollView scrollView = new ScrollView(activity);
        scrollView.addView(getGameMovesTableLayout(gameRecord));
        return scrollView;
    }

    private TableLayout getGameMovesTableLayout(GameRecord gameRecord) {
        TableLayout tableLayout = getMovesTableLayout();
        tableLayout.addView(getGameMoveTableHeaders());
        for (GameMove gameMove : gameRecord.getGameMovesList()) {
            tableLayout.addView(getGameMoveTableRow(gameMove));
        }
        return tableLayout;
    }

    private TableLayout getMovesTableLayout() {
        TableLayout tableLayout = new TableLayout(activity);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tableLayout.setLayoutParams(params);
        tableLayout.setStretchAllColumns(true);
        return tableLayout;
    }

    private TableRow getGameMoveTableRow(GameMove gameMove) {
        TableRow tableRow = getMoveTableRow();
        tableRow.addView(getTextView(gameMove.getField()));
        tableRow.addView(getTextView(gameMove.getSymbol()));
        tableRow.addView(getTextView(gameMove.getDateTime().getFormattedTime()));
        return tableRow;
    }

    private TableRow getGameMoveTableHeaders() {
        TableRow tableRow = getMoveTableRow();
        tableRow.addView(getTextView(activity.getString(R.string.field_header_game_move)));
        tableRow.addView(getTextView(activity.getString(R.string.symbol_header_game_move)));
        tableRow.addView(getTextView(activity.getString(R.string.time_header_game_move)));
        return tableRow;
    }

    private TableRow getRecordTableRow() {
        TableRow tableRow = getTableRow();
        tableRow.setPadding(5, 5, 5, 5);
        return tableRow;
    }

    private TableRow getTableRow() {
        TableRow tableRow = new TableRow(activity);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        tableRow.setLayoutParams(lp);
        return tableRow;
    }

    private TableRow getMoveTableRow() {
        TableRow tableRow = getTableRow();
        tableRow.setPadding(40, 5, 40, 5);
        return tableRow;
    }
}
