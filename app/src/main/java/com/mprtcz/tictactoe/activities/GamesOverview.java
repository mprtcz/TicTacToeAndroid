package com.mprtcz.tictactoe.activities;

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

public class GamesOverview extends AppCompatActivity {
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
        Button startGameButton = new Button(this);
        startGameButton.setText(R.string.start_game_button);
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

    private void getActiveGamesFromServer() {
        GameService gameService = GameService.getInstance(new AsyncService());
        TableLayout existingGamesTableLayout = (TableLayout) findViewById(R.id.existingGamesTableLayout);
        gameService.getExistingGamesFromServer(existingGamesTableLayout);
    }




}
