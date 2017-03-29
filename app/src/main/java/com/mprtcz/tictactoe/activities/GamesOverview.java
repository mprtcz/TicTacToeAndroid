package com.mprtcz.tictactoe.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mprtcz.tictactoe.R;

public class GamesOverview extends AppCompatActivity {
    String[] gameNames = new String[] {"TicTacToe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_overview);
        populateTableWithGames();
    }

    private void populateTableWithGames() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.startJoinGameTableLayout);
        for (String s : gameNames) {
            tableLayout.addView(getTableRow(s));
        }

    }

    private TableRow getTableRow(String gameName) {
        TableRow tableRow = new TableRow(this);
        TextView gameNameText = new TextView(this);
        gameNameText.setText(gameName);
        tableRow.addView(gameNameText);
        TextView placeholderTextView = new TextView(this);
        placeholderTextView.setLayoutParams(new TableLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, 1f));
        Button startGameButton = new Button(this);
        startGameButton.setText(R.string.start_game_button);
        tableRow.addView(startGameButton);
        Button joinButton = new Button(this);
        joinButton.setText(R.string.join_game_button_text);
        tableRow.addView(joinButton);
        return tableRow;
    }
}
