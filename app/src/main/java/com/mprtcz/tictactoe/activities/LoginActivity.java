package com.mprtcz.tictactoe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.user.service.UserService;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsernameEditText;
    EditText loginPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getUiReferences();
    }

    private void getUiReferences() {
        this.loginUsernameEditText = (EditText) findViewById(R.id.loginUsername);
        this.loginPasswordEditText = (EditText) findViewById(R.id.loginPassword);
    }

    public void onLoginRegisterLinkTextViewClicked(View view) {
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }

    public void onLoginButtonConfirmClicked(View view) {
        if(validateEnteredUserData()) {
            UserService userService = new UserService(new AsyncService());
            userService.authenticateUser(this);
        }
    }

    private boolean validateEnteredUserData() {
        boolean flag = true;
        if(this.loginUsernameEditText.getText().equals("")) {
            this.loginUsernameEditText.setError(getString(R.string.login_name_empty));
            flag= false;
        }
        if(this.loginPasswordEditText.getText().equals("")) {
            flag = false;
            this.loginPasswordEditText.setError(getString(R.string.login_password_empty));
        }
        return flag;
    }

    public String getLoginUsername() {
        return loginUsernameEditText.getText().toString();
    }

    public String getLoginPassword() {
        return loginPasswordEditText.getText().toString();
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
