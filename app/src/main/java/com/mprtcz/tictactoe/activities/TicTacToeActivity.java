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

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicTacToeActivity extends AppCompatActivity {
    TicTacToeService ticTacToeService;
    TicTacToeGame ticTacToeGame;
    @BindView(R.id.field0GameButton)
    Button field0GameButton;
    @BindView(R.id.field1GameButton)
    Button field1GameButton;
    @BindView(R.id.field2GameButton)
    Button field2GameButton;
    @BindView(R.id.field3GameButton)
    Button field3GameButton;
    @BindView(R.id.field4GameButton)
    Button field4GameButton;
    @BindView(R.id.field5GameButton)
    Button field5GameButton;
    @BindView(R.id.field6GameButton)
    Button field6GameButton;
    @BindView(R.id.field7GameButton)
    Button field7GameButton;
    @BindView(R.id.field8GameButton)
    Button field8GameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        ButterKnife.bind(this);
        setButtonsCallbacks();
        createNewTicTacToeGame();
    }

    private void setButtonsCallbacks() {
        List<Button> buttons = new ArrayList<>();
        field0GameButton.setOnClickListener(getClickListener(0));
        field1GameButton.setOnClickListener(getClickListener(1));
        field2GameButton.setOnClickListener(getClickListener(2));
        field3GameButton.setOnClickListener(getClickListener(3));
        field4GameButton.setOnClickListener(getClickListener(4));
        field5GameButton.setOnClickListener(getClickListener(5));
        field6GameButton.setOnClickListener(getClickListener(6));
        field7GameButton.setOnClickListener(getClickListener(7));
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

    private View.OnClickListener getClickListener(final int buttonIndex) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticTacToeService.insertSymbol(buttonIndex);
            }
        };
    }

    private void createNewTicTacToeGame() {
        this.ticTacToeGame = new TicTacToeGame();
        this.ticTacToeService = new TicTacToeService(new AsyncService(), this.ticTacToeGame);
        ticTacToeService.createTicTacToeGame();
    }
}
