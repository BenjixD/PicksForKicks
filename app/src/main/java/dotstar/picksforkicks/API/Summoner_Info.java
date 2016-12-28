package dotstar.picksforkicks.API;

/**
 * Created by Aerosol on 2016-12-27.
 */

public class Summoner_Info extends Json_Data {

    private Integer id;
    private String name;
    private Integer profileIconId;
    private Integer revisionDate;
    private Integer summonerLevel;

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
}
