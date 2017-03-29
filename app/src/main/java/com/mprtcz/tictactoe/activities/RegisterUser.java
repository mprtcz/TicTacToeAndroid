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
import com.mprtcz.tictactoe.user.model.NewUser;
import com.mprtcz.tictactoe.user.service.UserService;

public class RegisterUser extends AppCompatActivity {
    private static final String TAG = "RegisterUser";
    EditText usernameTextEdit;
    EditText nicknameTextEdit;
    EditText passwordTextEdit;
    EditText confirmPasswordTextEdit;
    EditText emailTextEdit;
    Button confirmButton;
    TextView registrationStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        getViewReferences();
    }

    private void getViewReferences() {
        this.confirmButton = (Button) findViewById(R.id.registerUserButton);
        this.usernameTextEdit = (EditText) findViewById(R.id.registerUsernameEditText);
        this.nicknameTextEdit = (EditText) findViewById(R.id.registerUserNicknameEditText);
        this.passwordTextEdit = (EditText) findViewById(R.id.registerUserPasswordEditText);
        this.confirmPasswordTextEdit = (EditText) findViewById(R.id.registerUserConfirmPasswordEditText);
        this.emailTextEdit = (EditText) findViewById(R.id.registerUserEmailEditText);
        this.registrationStatusTextView = (TextView) findViewById(R.id.registrationStatusTextView);
    }

    public void onRegisterUserButtonClicked(View view) {
        if(!isDataValid()) {return;}
        NewUser newUser = new NewUser();
        newUser.setSsoId(this.usernameTextEdit.getText().toString());
        newUser.setNickname(this.nicknameTextEdit.getText().toString());
        newUser.setEmail(this.emailTextEdit.getText().toString());
        newUser.setPassword(this.passwordTextEdit.getText().toString());
        UserService userService = new UserService(new AsyncService());
        userService.registerNewUserOnServer(this, newUser);
    }

    private boolean isDataValid() {
        boolean isValid;
        isValid = isEditTextFilled(this.usernameTextEdit);
        isValid &= isEditTextFilled(this.nicknameTextEdit);
        isValid &= isEditTextFilled(this.passwordTextEdit);
        isValid &= isEditTextFilled(this.confirmPasswordTextEdit);
        isValid &= isEditTextFilled(this.emailTextEdit);
        Log.i(TAG, "isDataValid pre-pw equality: " + isValid);
        if(isValid) {
            isValid = arePasswordsEqual();
        }
        Log.i(TAG, "isDataValid: " + isValid);
        return isValid;
    }

    private boolean arePasswordsEqual() {
        Log.i(TAG, "checking password equality");
        if(!this.passwordTextEdit.getText().toString().equals(this.confirmPasswordTextEdit.getText().toString())) {
            this.passwordTextEdit.setError("Password do not match");
            this.confirmPasswordTextEdit.setError("Password do not match");
            return false;
        }
        return true;
    }

    private boolean isEditTextFilled(EditText editText) {
        if(editText.getText().toString().isEmpty()) {
            Log.i(TAG, "isEditTextFilled: " +editText.getText().toString() + " appears to be empty");
            displayEmptyEditWarning(editText);
            return false;
        }
        return true;
    }

    private void displayEmptyEditWarning(EditText editText) {
        editText.setError(getString(R.string.registration_field_empty_text));
    }

    public void successfulRegistrationRedirect() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showBackendErrorResponse(String errorMessage) {
        this.registrationStatusTextView.setText(errorMessage);
    }
}