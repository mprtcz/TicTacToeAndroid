package com.mprtcz.tictactoe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.interfaces.UserLogin;
import com.mprtcz.tictactoe.user.service.UserService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements UserLogin {

    @BindView(R.id.loginUsername)
    EditText loginUsernameEditText;
    @BindView(R.id.loginPassword)
    EditText loginPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    public void onLoginRegisterLinkTextViewClicked(View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

    public void onLoginButtonConfirmClicked(View view) {
        if (validateEnteredUserData()) {
            UserService userService = new UserService(new AsyncService());
            userService.authenticateUser(this,
                    this.loginUsernameEditText.getText().toString(),
                    this.loginPasswordEditText.getText().toString());
        }
    }

    private boolean validateEnteredUserData() {
        boolean flag = true;
        if (this.loginUsernameEditText.getText().equals("")) {
            this.loginUsernameEditText.setError(getString(R.string.login_name_empty));
            flag = false;
        }
        if (this.loginPasswordEditText.getText().equals("")) {
            flag = false;
            this.loginPasswordEditText.setError(getString(R.string.login_password_empty));
        }
        return flag;
    }

    public void setBadCredentialsErrorMessage() {
        loginPasswordEditText.setError(getString(R.string.bad_login_error));
        loginUsernameEditText.setError(getString(R.string.bad_login_error));
    }

    public void redirectSuccessfulLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
