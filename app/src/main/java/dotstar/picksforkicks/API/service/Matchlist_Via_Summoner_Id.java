package dotstar.picksforkicks.API.service;

import android.util.Log;

import com.google.gson.JsonObject;

import java.net.URLEncoder;

/**
 * Created by iceya on 1/4/2017.
 */

public class Matchlist_Via_Summoner_Id {

    public interface Callback{

        void onSuccess(JsonObject result);
    }

    public static void get_Matchlist_via_Summoner_Id(String region, String summoner_id, final Callback cb){

        String url = "";

        try{
            url ="/api/lol/" + URLEncoder.encode(region, "UTF-8") + "/v2.2/matchlist/by-summoner/" + URLEncoder.encode(summoner_id, "UTF-8");
            Riot_Games_API.getApi(url,new Riot_Games_API.Callback() {
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
