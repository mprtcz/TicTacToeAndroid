package com.mprtcz.tictactoe.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.game.model.TicTacToeGame;
import com.mprtcz.tictactoe.game.service.TicTacToeService;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeActivity extends AppCompatActivity {
    Button[] gameButtons;
    TicTacToeService ticTacToeService;
    TicTacToeGame ticTacToeGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        createNewTicTacToeGame();
    }

    private void getButtonsReferencesAndCallbacks() {
        List<Button> buttons = new ArrayList<>();
        Button field0GameButton = (Button) findViewById(R.id.field0GameButton);
        field0GameButton.setOnClickListener(getClickListener(0));
        Button field1GameButton = (Button) findViewById(R.id.field1GameButton);
        field1GameButton.setOnClickListener(getClickListener(1));
        Button field2GameButton = (Button) findViewById(R.id.field2GameButton);
        field2GameButton.setOnClickListener(getClickListener(2));
        Button field3GameButton = (Button) findViewById(R.id.field3GameButton);
        field3GameButton.setOnClickListener(getClickListener(3));
        Button field4GameButton = (Button) findViewById(R.id.field4GameButton);
        field4GameButton.setOnClickListener(getClickListener(4));
        Button field5GameButton = (Button) findViewById(R.id.field5GameButton);
        field5GameButton.setOnClickListener(getClickListener(5));
        Button field6GameButton = (Button) findViewById(R.id.field6GameButton);
        field6GameButton.setOnClickListener(getClickListener(6));
        Button field7GameButton = (Button) findViewById(R.id.field7GameButton);
        field7GameButton.setOnClickListener(getClickListener(7));
        Button field8GameButton = (Button) findViewById(R.id.field8GameButton);
        field8GameButton.setOnClickListener(getClickListener(8));

        buttons.add(field0GameButton);
        buttons.add(field1GameButton);
        buttons.add(field2GameButton);
        buttons.add(field3GameButton);
        buttons.add(field4GameButton);
        buttons.add(field5GameButton);
        buttons.add(field6GameButton);
        buttons.add(field7GameButton);
        buttons.add(field8GameButton);

        ticTacToeGame.setButtons(buttons);
    }

    private View.OnClickListener getClickListener(final int buttonIdex) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticTacToeService.insertSymbol(buttonIdex);
            }
        };
    }

    private void createNewTicTacToeGame() {
        this.ticTacToeGame = new TicTacToeGame();
        this.ticTacToeService = new TicTacToeService(new AsyncService(), this.ticTacToeGame);
        ticTacToeService.createTicTacToeGame();
    }
}
