package dotstar.picksforkicks.API.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;

/**
 * Created by Benjamin on 2017-01-03.
 */

public class Match_List {
    public enum queueType{
        RANKED_SOLO_5x5 ("RANKED_SOLO_5x5");

        private final String queue;
        queueType(String thisQueue) { this.queue = thisQueue; }

        private String queue() { return queue; }
    }

    public enum matchListJson{
        MATCHES ("matches"),
        TIMESTAMP ("timestamp"),
        QUEUE ("queue"),
        CHAMPION ("champion");

        private final String key;
        matchListJson(String thisKey) {this.key = thisKey;}

        private String key() { return key; }
    }

    public static JsonObject getSpecificGameData(JsonObject history, queueType qt, double startTime, double threshold){
        JsonArray listOfGames = history.getAsJsonArray(matchListJson.MATCHES.key());

        JsonArray specificGames = new JsonArray();
        Iterator<JsonElement> itr = listOfGames.iterator();
        while(itr.hasNext()){
            //Get the game
            JsonObject game = itr.next().getAsJsonObject();
            //Game is too old
            if(game.get(matchListJson.TIMESTAMP.key()).getAsDouble() < (startTime - threshold)){ break;}

            //Check if game is part of list
            if(game.get(matchListJson.QUEUE.key()).getAsString().equals(qt.queue())){
                //Add the game to our list
                specificGames.add(game);
            }
        }

        //Response Json Object
        JsonObject response = new JsonObject();
        response.add("matches", specificGames);

        return response;
    }
}
