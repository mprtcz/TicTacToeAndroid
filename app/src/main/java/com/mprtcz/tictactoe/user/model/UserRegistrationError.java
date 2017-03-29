package com.mprtcz.tictactoe.user.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Azet on 29.03.2017.
 */

public class UserRegistrationError {
    @Expose
    private
    String property;
    @Expose
    private
    String message;

    public String getProperty() {
        return property;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "UserRegistrationError{" +
                "property='" + property + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
