package dotstar.picksforkicks.testActivities;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.Random;

import dotstar.picksforkicks.API.model.Match_History;
import dotstar.picksforkicks.API.service.Champion_Data_Via_Champion_Id;
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
        TextView team100 = (TextView) findViewById(R.id.team100);
        TextView team200 = (TextView) findViewById(R.id.team200);
        TextView answer = (TextView) findViewById(R.id.answer);

        //Empty Text
        team100.setText("");
        team200.setText("");
        answer.setText("");

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
                        //Get Specific Game Data
                        double twoDays = 4 * 24 * 60 * 60 * 1000;
                        int winningTeam;
                        Random rand = new Random();
                        int randInt;
                        JsonObject teams;

                        JsonObject specificGames = Match_History.getSpecificGameData(result, Match_History.gameType.RANKED_FLEX_SR, System.currentTimeMillis(), twoDays);
                        randInt = rand.nextInt(specificGames.getAsJsonArray("games").size());
                        specificGames = specificGames.getAsJsonArray("games").get(randInt).getAsJsonObject();

                        winningTeam = Match_History.getWinningTeam(specificGames);
                        teams = Match_History.getListOfChampionsGameData(specificGames);

                        //Get the champions
                        Iterator<JsonElement> itr100 = teams.get("100").getAsJsonArray().iterator();
                        Iterator<JsonElement> itr200 = teams.get("200").getAsJsonArray().iterator();

                        while(itr100.hasNext()){
                            Champion_Data_Via_Champion_Id.get_Champion_Data_Via_Champion_Id("na", itr100.next().getAsJsonObject().get("championId").getAsString(), new Champion_Data_Via_Champion_Id.Callback(){
                                @Override
                                public void onSuccess(JsonObject result){
                                    team100.setText(team100.getText().toString() + "\n" + result.get("name").toString());
                                }
                            });
                        }
                        while(itr200.hasNext()){
                            Champion_Data_Via_Champion_Id.get_Champion_Data_Via_Champion_Id("na", itr200.next().getAsJsonObject().get("championId").getAsString(), new Champion_Data_Via_Champion_Id.Callback(){
                                @Override
                                public void onSuccess(JsonObject result){
                                    team200.setText(team200.getText().toString() + "\n" + result.get("name").toString());
                                }
                            });
                        }
                        tv.setText(teams.toString());

                        //Open button listeners
                        Button blueButton = (Button) findViewById(R.id.blue);
                        Button redButton = (Button) findViewById(R.id.red);

                        blueButton.setOnClickListener(new Button.OnClickListener(){
                            public void onClick(View v){
                                if(100 == winningTeam){
                                    answer.setText("Correct!");
                                }else{
                                    answer.setText("Wrong!");
                                }
                            }
                        });

                        redButton.setOnClickListener(new Button.OnClickListener(){
                            public void onClick(View v){
                                if(200 == winningTeam){
                                    answer.setText("Correct!");
                                }else{
                                    answer.setText("Wrong!");
                                }
                            }
                        });

                    }
                });
            }
        });
    }
}
