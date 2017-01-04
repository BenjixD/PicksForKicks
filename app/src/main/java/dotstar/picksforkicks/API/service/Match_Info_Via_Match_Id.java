package dotstar.picksforkicks.API.service;

import android.util.Log;

import com.google.gson.JsonObject;

import java.net.URLEncoder;

/**
 * Created by iceya on 1/4/2017.
 */

public class Match_Info_Via_Match_Id {

    public interface Callback{

        void onSuccess(JsonObject result);
    }

    public static void get_Match_Info_Via_Match_Id(String region, String match_Id, final Callback cb){

        String url = "";
        try{
            url = "/api/lol/" + URLEncoder.encode(region, "UTF-8") + "/v2.2/match/" + URLEncoder.encode(match_Id, "UTF-8");

            Riot_Games_API.getApi(url, new Riot_Games_API.Callback(){
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
