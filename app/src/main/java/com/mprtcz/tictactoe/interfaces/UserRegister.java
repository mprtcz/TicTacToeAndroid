package com.mprtcz.tictactoe.interfaces;

import com.mprtcz.tictactoe.user.model.UserRegistrationError;

/**
 * Created by Azet on 29.03.2017.
 */

public interface UserRegister {
    void successfulRegistrationRedirect();

    void showBackendErrorResponse(UserRegistrationError[] userRegistrationErrors);
}
