package com.mprtcz.tictactoe.game.model;

import com.mprtcz.tictactoe.utils.DateTimeHelper;

/**
 * Created by Azet on 2017-03-24.
 */

public class GameMove {
    private Long id;

    private String field;

    private String symbol;

    private DateTimeHelper dateTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public String getField() {
        return field;
    }

    public String getSymbol() {
        return symbol;
    }

    public DateTimeHelper getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTimeHelper dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "GameMove{" +
                "id=" + id +
                ", field='" + field + '\'' +
                ", symbol='" + symbol + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
