package com.mprtcz.tictactoe.user.endpoint;

import android.util.Log;

import com.mprtcz.tictactoe.user.model.User;

/**
 * Created by Azet on 2017-03-23.
 */

public class LoggedUserDataStore {
    private static final String TAG = "LoggedUserDataStore";
    private User loggedInUser;
    private static LoggedUserDataStore loggedUserDataStore;
    private String sessionId;

    private LoggedUserDataStore() {}

    public static void setLoggedUserAndSession(User user, String sessionId) {
        if(loggedUserDataStore == null) {
            loggedUserDataStore = new LoggedUserDataStore();
        }
        loggedUserDataStore.loggedInUser = user;
        loggedUserDataStore.sessionId = sessionId;
        Log.d(TAG, loggedUserDataStore.toString());
    }

    public static User getLoggedInUser() {
        return loggedUserDataStore.loggedInUser;
    }

    public static String getSessionId() {
        return loggedUserDataStore.sessionId;
    }

    @Override
    public String toString() {
        return "LoggedUserDataStore{" +
                "loggedInUser=" + loggedInUser +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}