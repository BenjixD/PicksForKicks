package dotstar.picksforkicks.API.model;

import android.support.annotation.NonNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Benjamin on 2016-12-29.
 */

public class Match_History {
    public enum gameType{
        CUSTOM ("CUSTOM"),
        BOT (" BOT"),
        RANKED_FLEX_SR ("RANKED_FLEX_SR"),
        RANKED_FLEX_TT ("RANKED_FLEX_TT"),
        RANKED_SOLO_5x5 ("RANKED_SOLO_5x5"),
        RANKED_PREMADE_3x3 ("RANKED_PREMADE_3x3"),
        RANKED_PREMADE_5x5 ("RANKED_PREMADE_5x5"),
        NORMAL ("NORMAL"),
        NONE ("NONE");
        //Theres More but I dont Know em

        private final String game;  //Game Type String Name
        gameType(String thisGame){
            this.game = thisGame;
        }

        private String game() {return game;}
    }

    /* Remember to make this a enum actually */
    private static final String gameListId = "games";
    private static final String createDateId = "createDate";
    private static final String gameModeId = "gameMode";
    private static final String gameTypeId = "subType";
    private static final String fellowPlayersId = "fellowPlayers";
    private static final String teamId = "teamId";
    private static final String championId = "championId";
    private static final String gameId = "gameId";

    public static JsonObject getSpecificGameData(JsonObject history, gameType gt, double startTime, double threshold){
        //Get the Json array of games
        JsonArray listOfGames = history.getAsJsonArray(gameListId);

        JsonArray specificGames = new JsonArray();
        Iterator<JsonElement> itr = listOfGames.iterator();
        while(itr.hasNext()){
            //Get the game
            JsonObject game = itr.next().getAsJsonObject();

            //Break if game is too old (outside the threshold)
            if(game.get(createDateId).getAsDouble() < (startTime - threshold)) {break;}

            //Got Our Game
            if(game.get(gameTypeId).getAsString().equals(gt.game())){
                //Add the game to our list
                specificGames.add(game);
            }
        }

        //Response Json Object
        JsonObject response = new JsonObject();
        response.add("games", specificGames);

        return response;
    }

    public static JsonObject getListOfChampionsGameData(JsonObject game){
        //Get the JsonArray of players
        JsonArray players = game.getAsJsonArray(fellowPlayersId);
        Iterator<JsonElement> itr = players.iterator();
        Map<String, JsonArray> teams = new HashMap<String, JsonArray>();

        while(itr.hasNext()){
            //Get a player
            JsonObject player = itr.next().getAsJsonObject();

            //If team doesn't exist in the map
            if(!teams.containsKey(player.get(teamId).getAsString())){
                JsonArray team = new JsonArray();
                JsonObject champion = new JsonObject();

                champion.add(championId, player.get(championId));
                team.add(champion);
                teams.put(player.get(teamId).getAsString(), team);
            }

            //Otherwise the team exists in the map and we just add the new guy in
            else{
                JsonArray team = teams.get(player.get(teamId));
                JsonObject champion = new JsonObject();

                champion.add(championId, player.get(championId));
                team.add(champion);
                teams.put(player.get(teamId).getAsString(), team);
            }
        }

        //Finished the Map & Now we'll transform it back into a JsonObject
        JsonObject response = new JsonObject();
        Iterator<Map.Entry<String, JsonArray>> itr2 = teams.entrySet().iterator();
        while(itr2.hasNext()){
            Map.Entry<String, JsonArray> pair = itr2.next();
            response.add(pair.getKey(), pair.getValue());
        }
        return response;
    }

    // Given a game from match history get the Match Id(Game Id)
    public static JsonObject getMatchIdGameData(JsonObject game){
        //Create the response
        JsonObject response = new JsonObject();
        response.add(gameId, game.get(gameId));

        return response;
    }
}
