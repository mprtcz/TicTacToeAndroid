package com.mprtcz.tictactoe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.game.service.GameService;

public class GamesOverviewActivity extends AppCompatActivity {
    String[] gameNames = new String[] {"TicTacToe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_overview);
        populateTableWithGameTypes();
    }

    private void populateTableWithGameTypes() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.startJoinGameTableLayout);
        for (String s : gameNames) {
            tableLayout.addView(getGameTypeTableRow(s));
        }
    }

    private TableRow getGameTypeTableRow(String gameName) {
        TableRow tableRow = new TableRow(this);
        TextView gameNameText = new TextView(this);
        gameNameText.setText(gameName);
        tableRow.addView(gameNameText);
        TextView placeholderTextView = new TextView(this);
        placeholderTextView.setLayoutParams(new TableLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, 1f));
        Button startGameButton = getStartButtonWithCallback();
        tableRow.addView(startGameButton);
        Button joinButton = getJoinButtonWithCallback();
        tableRow.addView(joinButton);
        return tableRow;
    }

    private Button getJoinButtonWithCallback() {
        Button joinButton = new Button(this);
        joinButton.setText(R.string.join_game_button_text);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActiveGamesFromServer();
            }
        });
        return joinButton;
    }

    private Button getStartButtonWithCallback() {
        Button startButton = new Button(this);
        startButton.setText(R.string.start_game_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewTicTacToeGame();
            }
        });
        return startButton;

    }

    private void startNewTicTacToeGame() {
        Intent intent = new Intent(this, TicTacToeActivity.class);
        startActivity(intent);
    }

    private void getActiveGamesFromServer() {
        GameService gameService = GameService.getInstance(new AsyncService());
        TableLayout existingGamesTableLayout = (TableLayout) findViewById(R.id.existingGamesTableLayout);
        gameService.getExistingGamesFromServer(existingGamesTableLayout);
    }
}
