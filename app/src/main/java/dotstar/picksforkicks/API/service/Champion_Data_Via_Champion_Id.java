package dotstar.picksforkicks.API.service;

import android.util.Log;

import com.google.gson.JsonObject;

import java.net.URLEncoder;

/**
 * Created by iceya on 12/29/2016.
 */

public class Champion_Data_Via_Champion_Id {

    public interface Callback{
        void onSuccess(JsonObject result);
    }

    //get match by passing a function and summoner name
    public static void get_Champion_Data_Via_Champion_Id(String region, String championId, final Callback cb){
        String url ="";

        try {
            //encode to sanitize user input before passing to Riot Api
            url = "/api/lol/static-data/" + URLEncoder.encode(region, "UTF-8") + "/v1.2/champion/" + URLEncoder.encode(championId, "UTF-8");
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
