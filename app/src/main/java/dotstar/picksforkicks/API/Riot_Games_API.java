package dotstar.picksforkicks.API;

import android.app.Service;
import android.database.Observable;

/**
 * Created by Benjamin on 2016-12-27.
 */

public class Riot_Games_API {
    //Static key
    private static final String key = "fc583505-9dda-425b-847b-ca94daec7373";

    //Generic Factory
    static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .build();
        T service = restAdapter.create(clazz);

        return service;
    }

    public interface Service_Endpoint{
        String SERVICE_ENDPOINT = "https://na.api.pvp.net/api/lol/";
    }

    //SUMMONER NAME -> SUMMONER INFO
    public interface Summoner_Name_To_Info extends Service_Endpoint{
        String SERVICE_ENDPOINT = "https://na.api.pvp.net/api/lol/";
        //@GET("/{region}/{version}/summoner/by-name/{name}?api_key={key}")
        @GET("/{url}")
        Observable<Summoner_Info> getData(
                @Path("url") String url
        );
    }

    public static void getApi(Class<? extends Service_Endpoint> clazz, String url){
        Class<? extends Service_Endpoint> service = Riot_Games_API.createRetrofitService(clazz.getClass(), Service_Endpoint.SERVICE_ENDPOINT);
            service.getData(url)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Json_Data>() {
                        @Override
                        public final void onCompleted() {
                            // do nothing
                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("GithubDemo", e.getMessage());
                        }

                        @Override
                        public final void onNext(Json_Data response) {

                        }
                    });

    }


}

public class Json_Data{
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
