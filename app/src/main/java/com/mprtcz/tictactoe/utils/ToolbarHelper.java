package com.mprtcz.tictactoe.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.mprtcz.tictactoe.R;
import com.mprtcz.tictactoe.activities.LoginActivity;
import com.mprtcz.tictactoe.activities.RegisterUserActivity;
import com.mprtcz.tictactoe.activities.UserProfileActivity;

/**
 * Created by Azet on 2017-03-24.
 */

public class ToolbarHelper {

    public static Toolbar chooseToolbarIcons(final Activity activity) {
        Toolbar myToolbar = (Toolbar) activity.findViewById(R.id.my_toolbar);
        ImageView firstIcon = (ImageView) myToolbar.findViewById(R.id.firstToolbarIcon);
        ImageView secondIcon = (ImageView) myToolbar.findViewById(R.id.secondToolbarIcon);
        if(LoggedUserDataStore.isUserLoggedIn()) {
            firstIcon.setImageResource(R.drawable.ic_person_black_24dp);
            firstIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, UserProfileActivity.class);
                    activity.startActivity(intent);
                }
            });
            secondIcon.setImageResource(R.drawable.ic_exit_to_app_black_24dp);
        } else {
            firstIcon.setImageResource(R.drawable.ic_person_black_24dp);
            firstIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent);
                }
            });
            secondIcon.setImageResource(R.drawable.ic_person_add_black_24dp);
            secondIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, RegisterUserActivity.class);
                    activity.startActivity(intent);
                }
            });
        }
        return myToolbar;
    }
}
