package dotstar.picksforkicks.API.service;

import android.util.Log;

import com.google.gson.JsonObject;

import java.net.URLEncoder;

/**
 * Created by iceya on 12/29/2016.
 */

public class Match_History_Via_Summoner_Id {

    public interface Callback{
        void onSuccess(JsonObject result);
    }

    //get match by passing a function and summoner name
    public static void get_Match_History_Via_Summoner_Name(String region, String summonerId, final Callback cb){

        String url ="";

        try {
            //encode to sanitize user input before passing to Riot Api
            url = "api/lol/" + URLEncoder.encode(region, "UTF-8") + "/v1.3/game/by-summoner/" + URLEncoder.encode(summonerId, "UTF-8") + "/recent";
            Riot_Games_API.getApi(url, new Riot_Games_API.Callback() {
                @Override
                //store result in callback passed
                public void onSuccess(JsonObject result) {

                    cb.onSuccess(result);
                }
            });

        }
        catch(Exception e){
            Log.d("testApiCalls", url);
        }
    }

}
