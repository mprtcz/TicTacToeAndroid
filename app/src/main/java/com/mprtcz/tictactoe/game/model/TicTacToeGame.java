package com.mprtcz.tictactoe.game.model;

import android.widget.Button;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Azet on 30.03.2017.
 */

public class TicTacToeGame {
    private String[] gameArray = new String[] {" "," "," "," "," "," "," "," "," "};
    private String gameHost;
    private String loggedPlayer;
    private boolean isSecondPlayerInGame;
    private TicTacToeGameDTO ticTacToeGameDTO;
    private String playersSign;
    private boolean isGameDisabled = false;
    private String gameMessage;
    private List<Button> buttons;

    public void updateGameDto(TicTacToeGameDTO ticTacToeGameDTO) {
        this.ticTacToeGameDTO = ticTacToeGameDTO;
        this.isSecondPlayerInGame = this.ticTacToeGameDTO.getSecondPlayer() != null;
        this.gameArray = this.ticTacToeGameDTO.getOneDimTable();
        this.resolveCurrentGameMessage();
        this.updateButtons();
        if(this.ticTacToeGameDTO.getWinner() != null) {
            this.terminateGame();
        }
    }

    private void updateButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(gameArray[i]);
        }
    }

    private void terminateGame() {
        //TODO termination of game loop
    }

    private void resolveCurrentGameMessage() {
        if(this.ticTacToeGameDTO.getSecondPlayer() == null) {
            this.isGameDisabled = true;
            this.gameMessage = "Waiting for second player ";
            return;
        }
        if (this.ticTacToeGameDTO.getWinner() != null) {
            this.isGameDisabled = true;
            this.gameMessage = "The winner is: " + this.ticTacToeGameDTO.getWinner();
            return;
        }
        if (Objects.equals(this.playersSign, this.ticTacToeGameDTO.getCurrentPlayer())) {
            this.gameMessage = "Your move, place " + this.playersSign;
            this.isGameDisabled = false;
        } else {
            this.isGameDisabled = true;
            this.gameMessage = "Waiting for opponent\"s move";
        }
    }

    public String[] getGameArray() {
        return gameArray;
    }

    public void setGameArray(String[] gameArray) {
        this.gameArray = gameArray;
    }

    public String getGameHost() {
        return gameHost;
    }

    public void setGameHost(String gameHost) {
        this.gameHost = gameHost;
    }

    public String getLoggedPlayer() {
        return loggedPlayer;
    }

    public void setLoggedPlayer(String loggedPlayer) {
        this.loggedPlayer = loggedPlayer;
    }

    public boolean isSecondPlayerInGame() {
        return isSecondPlayerInGame;
    }

    public void setSecondPlayerInGame(boolean secondPlayerInGame) {
        isSecondPlayerInGame = secondPlayerInGame;
    }

    public TicTacToeGameDTO getTicTacToeGameDTO() {
        return ticTacToeGameDTO;
    }

    public void setTicTacToeGameDTO(TicTacToeGameDTO ticTacToeGameDTO) {
        this.ticTacToeGameDTO = ticTacToeGameDTO;
    }

    public String getPlayersSign() {
        return playersSign;
    }

    public void setPlayersSign(String playersSign) {
        this.playersSign = playersSign;
    }

    public boolean isGameDisabled() {
        return isGameDisabled;
    }

    public void setGameDisabled(boolean gameDisabled) {
        isGameDisabled = gameDisabled;
    }

    public String getGameMessage() {
        return gameMessage;
    }

    public void setGameMessage(String gameMessage) {
        this.gameMessage = gameMessage;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }
}
