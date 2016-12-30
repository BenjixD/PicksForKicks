package dotstar.picksforkicks.Game;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Benjamin on 2016-12-29.
 */

public class Game_Handler {
    //Database Reference
    private DatabaseReference mDatabase;

    //Instantiate database reference
    public Game_Handler(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    //Set of Strings of the database hierarchy
    private static final String summoner = "summoner";

    //[Get SummonerList]
    public interface callbackSummoner{
        void onSuccess(String result);
    }
    //The callback interface
    public interface CallbackSummonerList{
        void onSuccess(List<String> result);
    }
    public void getSummoner(final callbackSummoner cb){
        retrieveSummonerList(new CallbackSummonerList() {
            @Override
            public void onSuccess(List<String> result) {
                Random rand = new Random();
                //Get the list and randomize a summoner id from the list inclusion:[,)
                int randomNum = rand.nextInt(result.size());
                cb.onSuccess(result.get(randomNum));
            }
        });
    }
    //Create the listener of retrieving summoner list
    private void retrieveSummonerList(final CallbackSummonerList cb){
        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> summonerList = new ArrayList<String>();
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    //Add the key (SummonerId) to our list
                    summonerList.add(child.getKey());
                }
                cb.onSuccess(summonerList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(this.getClass().toString(), "loadSummonerList:onCancelled", databaseError.toException());
            }
        };
        mDatabase.child(summoner).addListenerForSingleValueEvent(vel);
    }
    //[end Get SummonerList]
}
