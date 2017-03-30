package com.mprtcz.tictactoe.game.model;

import java.util.Arrays;

/**
 * Created by Azet on 2017-03-23.
 */

public class TicTacToeGameDTO {
    private String[][] table;
    private String[] oneDimTable;
    private String currentPlayer;
    private String gameHost;
    private String secondPlayer;
    private String winner;

    public String[][] getTable() {
        return table;
    }

    public void setTable(String[][] table) {
        this.table = table;
    }

    public String[] getOneDimTable() {
        return oneDimTable;
    }

    public void setOneDimTable(String[] oneDimTable) {
        this.oneDimTable = oneDimTable;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getGameHost() {
        return gameHost;
    }

    public void setGameHost(String gameHost) {
        this.gameHost = gameHost;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(String secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "TicTacToeGameDTO{" +
                "table=" + Arrays.deepToString(table) +
                ", oneDimTable=" + Arrays.toString(oneDimTable) +
                ", currentPlayer='" + currentPlayer + '\'' +
                ", gameHost='" + gameHost + '\'' +
                ", secondPlayer='" + secondPlayer + '\'' +
                ", winner='" + winner + '\'' +
                '}';
    }
}
