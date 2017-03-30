package com.mprtcz.tictactoe.utils;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.game.model.TicTacToeGameDTO;

/**
 * Created by Azet on 30.03.2017.
 */

public class UiTablesComposer {

    public static TableRow getExistingGameTableHeader(Context context) {
        TableRow tableHeader = new TableRow(context);
        TextView gameHostTExtView = new TextView(context);
        gameHostTExtView.setText(R.string.game_host_table_header);
        TextView secondPlayerTextView = new TextView(context);
        secondPlayerTextView.setText(R.string.second_player_table_header);
        tableHeader.addView(gameHostTExtView);
        tableHeader.addView(secondPlayerTextView);
        return tableHeader;
    }

    public static TableRow getExistingGameTableRow(TicTacToeGameDTO existingGame, Context context) {
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
}
