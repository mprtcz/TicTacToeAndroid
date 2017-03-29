package com.mprtcz.tictactoe.interfaces;

import com.mprtcz.tictactoe.user.model.NewUser;
import com.mprtcz.tictactoe.user.model.UserRegistrationError;

/**
 * Created by Azet on 29.03.2017.
 */

public interface UserRegister {
    void successfulRegistrationRedirect(NewUser newUser);

    void showBackendErrorResponse(UserRegistrationError[] userRegistrationErrors);
}
