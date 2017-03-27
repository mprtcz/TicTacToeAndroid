package com.mprtcz.tictactoe.utils;

/**
 * Created by Azet on 2017-03-24.
 */

public class DateTimeHelper {

    private String year;
    private String dayOfMonth;
    private String hour;
    private String minute;
    private String monthValue;
    private String second;

    @Override
    public String toString() {
        return "DateTimeHelper{" +
                "year='" + year + '\'' +
                ", dayOfMonth='" + dayOfMonth + '\'' +
                ", hour='" + hour + '\'' +
                ", minute='" + minute + '\'' +
                ", monthValue='" + monthValue + '\'' +
                '}';
    }

    public String getFormattedDate() {
        return dayOfMonth + "." + monthValue + "." + year;
    }

    public String getFormattedTime() {
        return hour + ":" + minute +":" + second;
    }
}
