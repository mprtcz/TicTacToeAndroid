package com.mprtcz.tictactoe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.interfaces.UserLogin;
import com.mprtcz.tictactoe.interfaces.UserRegister;
import com.mprtcz.tictactoe.user.model.NewUser;
import com.mprtcz.tictactoe.user.model.UserRegistrationError;
import com.mprtcz.tictactoe.user.service.UserService;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterUserActivity extends AppCompatActivity implements UserRegister, UserLogin {
    private static final String TAG = "RegisterUserActivity";

    @BindView(R.id.registerUserButton)
    Button confirmButton;
    @BindView(R.id.registerUsernameEditText)
    EditText usernameTextEdit;
    @BindView(R.id.registerUserNicknameEditText)
    EditText nicknameTextEdit;
    @BindView(R.id.registerUserPasswordEditText)
    EditText passwordTextEdit;
    @BindView(R.id.registerUserConfirmPasswordEditText)
    EditText confirmPasswordTextEdit;
    @BindView(R.id.registerUserEmailEditText)
    EditText emailTextEdit;
    @BindView(R.id.registrationStatusTextView)
    TextView registrationStatusTextView;

    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);
    }

    public void onRegisterUserButtonClicked(View view) {
        if (!isDataValid()) {
            return;
        }
        NewUser newUser = new NewUser();
        newUser.setSsoId(this.usernameTextEdit.getText().toString());
        newUser.setNickname(this.nicknameTextEdit.getText().toString());
        newUser.setEmail(this.emailTextEdit.getText().toString());
        newUser.setPassword(this.passwordTextEdit.getText().toString());
        UserService.getInstance(new AsyncService()).registerNewUserOnServer(this, newUser);
    }

    private boolean isDataValid() {
        boolean isValid;
        isValid = isEditTextFilled(this.usernameTextEdit);
        isValid &= isEditTextFilled(this.nicknameTextEdit);
        isValid &= isEditTextFilled(this.passwordTextEdit);
        isValid &= isEditTextFilled(this.confirmPasswordTextEdit);
        isValid &= isEditTextFilled(this.emailTextEdit);
        Log.i(TAG, "isDataValid pre-pw equality: " + isValid);
        if (isValid) {
            isValid = arePasswordsEqual();
        }
        Log.i(TAG, "isDataValid: " + isValid);
        return isValid;
    }

    private boolean arePasswordsEqual() {
        Log.i(TAG, "checking password equality");
        if (!this.passwordTextEdit.getText().toString().equals(this.confirmPasswordTextEdit.getText().toString())) {
            this.passwordTextEdit.setError("Password do not match");
            this.confirmPasswordTextEdit.setError("Password do not match");
            return false;
        }
        return true;
    }

    private boolean isEditTextFilled(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            Log.i(TAG, "isEditTextFilled: " + editText.getText().toString() + " appears to be empty");
            editText.setError(getString(R.string.registration_field_empty_text));
            return false;
        }
        return true;
    }

    @Override
    public void successfulRegistrationRedirect(NewUser newUser) {
        UserService.getInstance(new AsyncService()).authenticateUser(this, newUser.getSsoId(), newUser.getPassword());
    }

    @Override
    public void showBackendErrorResponse(UserRegistrationError[] errors) {
        Log.i(TAG, "showBackendErrorResponse: errors: " + Arrays.toString(errors));
        for (UserRegistrationError error : errors) {
            switch (error.getProperty()) {
                case "SsoId":
                    this.usernameTextEdit.setError(error.getMessage());
                    break;
                case "Email":
                    this.emailTextEdit.setError(error.getMessage());
                    break;
                default: {
                    Log.i(TAG, "showBackendErrorResponse: unrecognized case for error property: " + error.getProperty());
                }
            }
        }
    }

    @Override
    public void redirectSuccessfulLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void setBadCredentialsErrorMessage() {
        Log.e(TAG, "setBadCredentialsErrorMessage: Bad login credentials");
    }
}