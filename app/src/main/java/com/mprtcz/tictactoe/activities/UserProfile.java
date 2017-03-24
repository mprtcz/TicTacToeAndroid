package com.mprtcz.tictactoe.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.asyncservice.AsyncService;
import com.mprtcz.tictactoe.game.service.GameService;
import com.mprtcz.tictactoe.user.model.User;
import com.mprtcz.tictactoe.utils.LoggedUserDataStore;
import com.mprtcz.tictactoe.utils.ToolbarHelper;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setSupportActionBar(ToolbarHelper.chooseToolbarIcons(this));
        fillTextFieldsWithUserData();
    }

    private void fillTextFieldsWithUserData() {
        User loggedInUser = LoggedUserDataStore.getLoggedInUser();
        if(loggedInUser == null) {return;}
        TextView nameTextView = (TextView) findViewById(R.id.userProfileName);
        TextView nicknameTextView = (TextView) findViewById(R.id.userProfileNickname);
        TextView emailTextView = (TextView) findViewById(R.id.userProfileEmail);
        String userNameString = getString(R.string.user_profile_name_textview) +" " + loggedInUser.getSsoId();
        String userNicknameString = getString(R.string.user_profile_nickname_textview) +" " +  loggedInUser.getNickname();
        String userEmailString = getString(R.string.user_profile_email_textview) +" " +  loggedInUser.getEmail();
        nameTextView.setText(userNameString);
        nicknameTextView.setText(userNicknameString);
        emailTextView.setText(userEmailString);
    }

    public void onShowHistoryButtonClicked(View view) {
        GameService gameService = new GameService(new AsyncService());
        TableLayout tableLayout = (TableLayout) findViewById(R.id.gamesHistoryTableLayout);
        gameService.getGamesHistoryFromServer(this, tableLayout);
    }
}
