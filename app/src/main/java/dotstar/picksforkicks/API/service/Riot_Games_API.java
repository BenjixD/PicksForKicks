package dotstar.picksforkicks.API.service;

import android.util.Log;
import com.google.gson.*;

import dotstar.picksforkicks.API.model.*;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Benjamin on 2016-12-27.
 */

public class Riot_Games_API {

    //Static key
    public static final String key = "fc583505-9dda-425b-847b-ca94daec7373";

    //Interface Parent of all API Calls
    /*
        !!Maybe HardCode the URLs for now, but we need a enumeration to fill in the URLs later!!
     */
    public interface Service_Endpoint{
        String SERVICE_ENDPOINT = "https://na.api.pvp.net";
        @GET("/api/lol/{url}")
        Observable<JsonObject> getData(
                @Path("url") String url,
                @Query("api_key") String api_key
        );
    }

    //SUMMONER NAME -> SUMMONER INFO
    /*
        DO WE EVEN NEED THIS NOW?

    public interface Summoner_Name_To_Info extends Service_Endpoint{
        //String SERVICE_ENDPOINT = "https://na.api.pvp.net/api/lol/";
        //@GET("/{region}/{version}/summoner/by-name/{name}?api_key={key}")
        @GET("/api/lol/{url}")
        Observable<Summoner_Info> getData(
                @Path("url") String url
        );
    }*/

    //The callback for getApi
    public interface Callback{
        void onSuccess(JsonObject result);
    }

    //Generic getApi Data function (Use Poly to transform Json_Data -> [Your Desired Json Model])
    public static void getApi(String url, final Callback cb){
        Service_Endpoint service = ServiceFactory.createRetrofitService(Service_Endpoint.class, Service_Endpoint.SERVICE_ENDPOINT);
        service.getData(url, key)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<JsonObject>() {
                @Override
                public final void onCompleted() {
                    // do nothing
                }

                @Override
                public final void onError(Throwable e) {
                    //Log the Error
                    Log.e(this.getClass().toString(), e.getMessage());
                }

                @Override
                public final void onNext(JsonObject response) {
                    cb.onSuccess(response);
                }
        });
    }
}


