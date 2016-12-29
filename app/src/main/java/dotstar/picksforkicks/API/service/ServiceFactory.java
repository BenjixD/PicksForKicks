package dotstar.picksforkicks.API.service;
import retrofit.RestAdapter;


/**
 * Created by Benjamin on 2016-12-28.
 */

public class ServiceFactory {
    //Generic Factory
    static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .build();
        T service = restAdapter.create(clazz);

        return service;
    }
}
