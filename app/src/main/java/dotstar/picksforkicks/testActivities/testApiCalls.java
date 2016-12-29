package dotstar.picksforkicks.testActivities;

import android.os.Bundle;
import android.view.View;

import dotstar.picksforkicks.R;
import dotstar.picksforkicks.API.service.Riot_Games_API;
import dotstar.picksforkicks.*;

/**
 * Created by iceya on 12/28/2016.
 */

public class testApiCalls extends GoogleSignIn {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_login);
    }

    @Override
    protected void setOnAuthStateListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
