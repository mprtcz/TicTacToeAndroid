package com.mprtcz.tictactoe.user.model;

/**
 * Created by Azet on 2017-03-23.
 */

public class User {

    private String ssoId;

    private String nickname;

    private String email;

    private String role;

    public String getSsoId() {
        return ssoId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "ssoId='" + ssoId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
