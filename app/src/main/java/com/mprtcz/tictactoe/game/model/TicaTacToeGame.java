package com.mprtcz.tictactoe.game.model;

/**
 * Created by Azet on 2017-03-23.
 */

public class TicaTacToeGame {
    private String[] symbols = new String[9];

    public TicaTacToeGame() {
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = " ";
        }
    }
}
