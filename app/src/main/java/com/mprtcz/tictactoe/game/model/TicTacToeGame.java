package com.mprtcz.tictactoe.game.model;

/**
 * Created by Azet on 2017-03-23.
 */

public class TicTacToeGame {
    String[][] table;
    String[] oneDimTable;
    String currentPlayer;
    String gameHost;
    String secondPlayer;
    String winner;

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
}
