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


    public void setYear(String year) {
        this.year = year;
    }

    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public void setMonthValue(String monthValue) {
        this.monthValue = monthValue;
    }

    public String getYear() {
        return year;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public String getMonthValue() {
        return monthValue;
    }

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
}
