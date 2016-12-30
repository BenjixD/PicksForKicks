package dotstar.picksforkicks.API.service;

import android.util.Log;
import android.widget.TextView;
import java.net.URLEncoder;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;

/**
 * Created by iceya on 12/29/2016.
 */

public class Summoner_ID_Via_Name {

    public interface Callback{
        void onSuccess(String result);
    }

    //get summonerId by passing a function and summoner name
    public static void get_SummonerId_via_SummonerName(String region, String summonerName, final Callback cb){

        String url ="";
        try {
            //encode to sanitize user input before passing to Riot Api
            url = "api/lol/" + URLEncoder.encode(region, "UTF-8") + "/v1.4/summoner/by-name/" + URLEncoder.encode(summonerName, "UTF-8");

            Riot_Games_API.getApi(url, new Riot_Games_API.Callback() {
                @Override
                public void onSuccess(JsonObject result) {

                    cb.onSuccess(result.get("id").getAsString());
                }
            });

        }
        catch(Exception e){
            Log.d("testApiCalls", url);
        }
    }
}
