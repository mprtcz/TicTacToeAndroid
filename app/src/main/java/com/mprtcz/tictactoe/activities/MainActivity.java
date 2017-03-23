package com.mprtcz.tictactoe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.user.service.UserService;

public class MainActivity extends AppCompatActivity {

    ListView usersOnlineListView;
    TextView summaryTextView;
    Button loginRegisterButton;
    UserService userService;
    ArrayAdapter<String> userNamesArrayAdapter;
    private ArrayAdapter<String> onlineGamesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUIElementsReferences();
        getFieldsReferences();
        getUsersFromServer();
    }

    private void getFieldsReferences() {
        this.userService = new UserService(new AsyncService());
        this.userNamesArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        this.usersOnlineListView.setAdapter(this.userNamesArrayAdapter);
    }

    private void getUsersFromServer() {
        this.userService.getServerSummary(this.userNamesArrayAdapter, this.summaryTextView, this.onlineGamesArrayAdapter);
    }

    private void getUIElementsReferences() {
        this.usersOnlineListView = (ListView) findViewById(R.id.usersOnlineListView);
        this.summaryTextView = (TextView) findViewById(R.id.summaryTextView);
        this.loginRegisterButton = (Button) findViewById(R.id.loginRegisterButton);
    }

    public void onLoginRegisterButtonClicked(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
