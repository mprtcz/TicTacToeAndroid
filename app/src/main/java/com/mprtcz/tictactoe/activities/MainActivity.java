package com.mprtcz.tictactoe.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.user.service.UserService;

public class MainActivity extends AppCompatActivity {

    ListView usersOnlineListView;
    UserService userService;
    ArrayAdapter<String> usernamesArrayAdapter;

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
        this.usernamesArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        this.usersOnlineListView.setAdapter(this.usernamesArrayAdapter);
    }

    private void getUsersFromServer() {
        this.userService.fillListWithUsersFromServer(usernamesArrayAdapter);
    }

    private void getUIElementsReferences() {
        this.usersOnlineListView = (ListView) findViewById(R.id.usersOnlineListView);
    }
}
