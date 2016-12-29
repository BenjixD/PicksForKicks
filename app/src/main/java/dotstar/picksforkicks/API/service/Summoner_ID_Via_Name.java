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

    //get summonerId by passing a function and summoner name
    public static void get_SummonerId_via_SummonerName(String region, String summonerName){
        String reg = region;
        String name  = summonerName;
        String url ="";
        final String summoner_id;

        try {
            //encode to sanitize user input before passing to Riot Api
            url = URLEncoder.encode(reg, "UTF-8") + "/v1.4/summoner/by-name/" + URLEncoder.encode(name, "UTF-8");

            Riot_Games_API.getApi(url, new Riot_Games_API.Callback() {
                @Override
                public void onSuccess(JsonObject result) {
                    result.get("id").getAsString();
                }
            });

        }
        catch(Exception e){
            Log.d("testApiCalls", url);
        }
    }
}
