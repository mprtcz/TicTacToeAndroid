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
import com.mprtcz.tictactoe.utils.ToolbarHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
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
        setSupportActionBar(ToolbarHelper.chooseToolbarIcons(this));
        getUIElementsReferences();
        getFieldsReferences();
        if(savedInstanceState == null) {
            getUsersFromServer();
        }
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

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        ArrayList<String> userNames = new ArrayList<>();
        for (int i = 0; i < userNamesArrayAdapter.getCount(); i++) {
            userNames.add(userNamesArrayAdapter.getItem(i));
        }
        state.putStringArrayList("users", userNames);
    }

    @Override
    public void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        if(state.getStringArrayList("users") != null) {
            userNamesArrayAdapter.addAll(state.getStringArrayList("users"));
        }
    }

    public void onGamesOverviewButtonClicked(View view) {
        Intent intent = new Intent(this, GamesOverview.class);
        startActivity(intent);
    }
}
