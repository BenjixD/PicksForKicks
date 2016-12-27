package dotstar.picksforkicks.API;

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

    //SUMMONER NAME -> SUMMONER INFO
    public interface Summoner_Name_To_Info{
        String SERVICE_ENDPOINT = "https://na.api.pvp.net/api/lol/";

        @GET("/{region}/{version}/summoner/by-name/{name}?api_key={key}")
        Observable<Summoner_Info> getInfo(
                @Path("region") String region,
                @Path("version") String version,
                @Path("name") String name,
                @Path("key") String api_key
        );
    }
}

public class Summoner_Info {

    private Integer id;
    private String name;
    private Integer profileIconId;
    private Integer revisionDate;
    private Integer summonerLevel;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    public Integer getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Integer revisionDate) {
        this.revisionDate = revisionDate;
    }

    public Integer getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(Integer summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
