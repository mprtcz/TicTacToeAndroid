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

import java.util.LinkedList;
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

        List<View> views = new LinkedList<>();
        views.add(getTextView(gameRecord.getPlayerOne().getNickname()));
        views.add(getTextView(gameRecord.getPlayerTwo().getNickname()));
        views.add(getTextView(gameRecord.getDateTime().getFormattedDate()));
        views.add(getButton(activity.getString(R.string.show_moves_button), getGameMovesButtonListener(gameRecord)));
        addViewsToRow(tableRow, views);

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

        List<View> views = new LinkedList<>();
        views.add(getTextView(activity.getString(R.string.table_row_first_player_header)));
        views.add(getTextView(activity.getString(R.string.table_row_second_player_header)));
        views.add(getTextView(activity.getString(R.string.table_row_date_header)));
        views.add(getTextView(activity.getString(R.string.table_row_action)));
        addViewsToRow(tableRow, views);

        return tableRow;
    }

    private void addViewsToRow(TableRow row, List<View> views) {
        for (View v :
                views) {
            row.addView(v);
        }
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

        List<View> views = new LinkedList<>();
        views.add(getTextView(gameMove.getField()));
        views.add(getTextView(gameMove.getSymbol()));
        views.add(getTextView(gameMove.getDateTime().getFormattedTime()));
        addViewsToRow(tableRow, views);

        return tableRow;
    }

    private TableRow getGameMoveTableHeaders() {
        TableRow tableRow = getMoveTableRow();

        List<View> views = new LinkedList<>();
        views.add(getTextView(activity.getString(R.string.field_header_game_move)));
        views.add(getTextView(activity.getString(R.string.symbol_header_game_move)));
        views.add(getTextView(activity.getString(R.string.time_header_game_move)));
        addViewsToRow(tableRow, views);

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
