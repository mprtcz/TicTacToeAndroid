package com.mprtcz.tictactoe.game.model;

import com.mprtcz.tictactoe.user.model.User;
import com.mprtcz.tictactoe.utils.DateTimeHelper;

import java.util.List;

/**
 * Created by Azet on 2017-03-24.
 */

public class GameRecord {
    private Long id;

    private User playerOne;

    private User playerTwo;

    private DateTimeHelper dateTime;

    private List<GameMove> gameMovesList;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlayerOne(User playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(User playerTwo) {
        this.playerTwo = playerTwo;
    }

    public DateTimeHelper getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTimeHelper dateTime) {
        this.dateTime = dateTime;
    }

    public void setGameMovesList(List<GameMove> gameMovesList) {
        this.gameMovesList = gameMovesList;
    }

    public Long getId() {
        return id;
    }

    public User getPlayerOne() {
        return playerOne;
    }

    public User getPlayerTwo() {
        return playerTwo;
    }

    public List<GameMove> getGameMovesList() {
        return gameMovesList;
    }

    @Override
    public String toString() {
        return "GameRecord{" +
                "id=" + id +
                ", playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                ", dateTime='" + dateTime + '\'' +
                ", gameMovesList=" + gameMovesList.toString() +
                '}';
    }
}
