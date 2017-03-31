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

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = "UserProfileActivity";
    @BindView(R.id.userProfileName)
    TextView nameTextView;
    @BindView(R.id.userProfileNickname)
    TextView nicknameTextView;
    @BindView(R.id.userProfileEmail)
    TextView emailTextView;
    @BindView(R.id.gamesHistoryTableLayout)
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        setSupportActionBar(ToolbarHelper.chooseToolbarIcons(this));
        fillTextFieldsWithUserData();
    }

    private void fillTextFieldsWithUserData() {
        User loggedInUser = LoggedUserDataStore.getLoggedInUser();
        if (loggedInUser == null) {
            return;
        }
        nameTextView.setText(getString(R.string.user_profile_name_textview) + " " + loggedInUser.getSsoId());
        nicknameTextView.setText(getString(R.string.user_profile_nickname_textview) + " " + loggedInUser.getNickname());
        emailTextView.setText(getString(R.string.user_profile_email_textview) + " " + loggedInUser.getEmail());
    }

    public void onShowHistoryButtonClicked(View view) {
        GameService gameService = new GameService(new AsyncService());
        if (LoggedUserDataStore.getGameRecords() != null && !LoggedUserDataStore.getGameRecords().isEmpty()) {
            gameService.populateGameRecordsTableRows(this, tableLayout, LoggedUserDataStore.getGameRecords());
        } else {
            gameService.getGamesHistoryFromServer(this, tableLayout);
        }
    }
}
