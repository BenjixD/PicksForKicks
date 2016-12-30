package dotstar.picksforkicks.testActivities;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonObject;

import dotstar.picksforkicks.API.model.Match_History;
import dotstar.picksforkicks.API.service.Match_History_Via_Summoner_Id;
import dotstar.picksforkicks.Game.Game_Handler;
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
        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //Run Activity
                    testActivity();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    //Send back to signin

                }
            }
        };
        // [END auth_state_listener]
    }

    @Override
    public void onClick(View v) {

    }

    private void testActivity(){
        //Create textview of username
        TextView tv = new TextView(this);

        //Add the new textview to layout
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_test_login);
        rl.addView(tv);

        //Get the api and do things with it
        Game_Handler gh = new Game_Handler();
        gh.getSummoner(new Game_Handler.callbackSummoner(){
            @Override
            public void onSuccess(String result){
                //Get the Summoner Match History
                Match_History_Via_Summoner_Id.get_Match_History_Via_Summoner_Name("na", result, new Match_History_Via_Summoner_Id.Callback(){
                    @Override
                    public void onSuccess(JsonObject result){
                        //Get Specific Game Data (NORMAL)
                        double twoDays = 2 * 24 * 60 * 60 * 1000;
                        double noDays = 0;
                        JsonObject specificGames = Match_History.getSpecificGameData(result, Match_History.gameType.NORMAL, System.currentTimeMillis(), twoDays);
                        tv.setText(specificGames.toString());
                    }
                });
            }
        });
    }
}
