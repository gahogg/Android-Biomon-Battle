package com.greg.android.biomoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.greg.android.biomoon.Globals.absoluteOrderOfMoves;
import static com.greg.android.biomoon.Globals.absoluteStart1;
import static com.greg.android.biomoon.Globals.activePokemon1;
import static com.greg.android.biomoon.Globals.activePokemon2;
import static com.greg.android.biomoon.Globals.clientHit1;
import static com.greg.android.biomoon.Globals.clientHit2;
import static com.greg.android.biomoon.Globals.healthAfterHealing1;
import static com.greg.android.biomoon.Globals.healthAfterHealing2;
import static com.greg.android.biomoon.Globals.start1;
import static com.greg.android.biomoon.Globals.start2;
import static com.greg.android.biomoon.Globals.usedMove1;
import static com.greg.android.biomoon.Globals.usedMove2;
import static com.greg.android.biomoon.HomeActivity.gameRoom;
import static com.greg.android.biomoon.HomeActivity.host;
import static com.greg.android.biomoon.HomeActivity.otherPlayersKey;
import static com.greg.android.biomoon.HomeActivity.playingOnline;
import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;
import static com.greg.android.biomoon.Globals.p1Damage;
import static com.greg.android.biomoon.Globals.p1Critt;
import static com.greg.android.biomoon.Globals.healthAfterHit1;
import static com.greg.android.biomoon.Globals.p2Damage;
import static com.greg.android.biomoon.Globals.p2Critt;
import static com.greg.android.biomoon.HomeActivity.healthAfterHit2;
import static com.greg.android.biomoon.Globals.absoluteStart2;
import static com.greg.android.biomoon.Globals.clientAccuracyStages1;
import static com.greg.android.biomoon.Globals.clientAttackStages1;
import static com.greg.android.biomoon.Globals.clientAttackStages2;
import static com.greg.android.biomoon.Globals.clientDefenseStages1;
import static com.greg.android.biomoon.Globals.clientDefenseStages2;
import static com.greg.android.biomoon.Globals.clientEvasionStages1;
import static com.greg.android.biomoon.Globals.clientEvasionStages2;
import static com.greg.android.biomoon.Globals.clientSpattackStages1;
import static com.greg.android.biomoon.Globals.clientSpattackStages2;
import static com.greg.android.biomoon.Globals.clientSpdefenseStages1;
import static com.greg.android.biomoon.Globals.clientSpdefenseStages2;
import static com.greg.android.biomoon.Globals.clientSpeedStages1;
import static com.greg.android.biomoon.Globals.clientSpeedStages2;
import static com.greg.android.biomoon.Globals.clientAccuracyStages2;
import static com.greg.android.biomoon.Globals.p2Damage;
import static com.greg.android.biomoon.Globals.p2Critt;



/**
 * Created by User on 2/8/2017.
 */

//This activity will get all the damage calcs from the database that the other player (the host) uploaded to the database
public class GetAlmostAllDataFromDatabaseActivity extends AppCompatActivity {
    private static final String TAG = "GetAlmostAllDataFromDat";

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private boolean readyToPlay;
    private boolean shouldDo;
    private boolean shouldAddActivePokemon;
    private boolean game1Available;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wait_layout);
        shouldDo=true;
        shouldAddActivePokemon=true;

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //Makes readyToPlay true if we are ready to proceed
                checkIfReady(dataSnapshot);

                if(readyToPlay)
                {
                    //We can now gather everything we need to proceed

                    gatherAndAssignEverything(dataSnapshot);

                    myRef.removeEventListener(this);
                    goPlayGame();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    private void gatherAndAssignEverything(DataSnapshot dataSnapshot) {
        //The client only knows what the other player switched to (if they did switch), and what move they used (if they didnt switch)

        //Getting everything else from the dataSnapshot

        //Getting all of our data (p1 from this perspective) (ourNameKey)
        p1Damage = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Damage").getValue().toString());
        p1Critt = Boolean.parseBoolean(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Crit").getValue().toString());
        clientHit1 = Boolean.parseBoolean(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Hit").getValue().toString());
        activePokemon1.hit = clientHit1;
        activePokemon1.flinch = Boolean.parseBoolean(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Flinch").getValue().toString());
        start1 = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Start").getValue().toString());
        absoluteStart1 = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Absolute Start").getValue().toString());
        healthAfterHealing1 = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Health After Healing").getValue().toString());
        healthAfterHit1 = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Health After Hit").getValue().toString());
        usedMove1.fail = Boolean.parseBoolean(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Fail").getValue().toString());
        //p1Effectiveness = Double.parseDouble(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Effectiveness").getValue().toString());
        clientAttackStages1  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("attack").getValue().toString());
        clientDefenseStages1  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("defense").getValue().toString());
        clientSpdefenseStages1  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("spdefense").getValue().toString());
        clientSpattackStages1  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("spattack").getValue().toString());
        clientSpeedStages1  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("speed").getValue().toString());
        clientAccuracyStages1  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("accuracy").getValue().toString());
        clientEvasionStages1  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("evasion").getValue().toString());

        //Getting all of their data (p2 from this perspective) (theirNameKey)
        p2Damage = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Damage").getValue().toString());
        p2Critt = Boolean.parseBoolean(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Crit").getValue().toString());
        clientHit2 = Boolean.parseBoolean(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Hit").getValue().toString());
        activePokemon2.hit = clientHit2;
        activePokemon2.flinch = Boolean.parseBoolean(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Flinch").getValue().toString());
        start2 = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Start").getValue().toString());
        absoluteStart2 = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Absolute Start").getValue().toString());
        healthAfterHealing2 = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Health After Healing").getValue().toString());
        healthAfterHit2 = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Health After Hit").getValue().toString());
        usedMove2.fail = Boolean.parseBoolean(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Fail").getValue().toString());
        //p2Effectiveness = Double.parseDouble(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Effectiveness").getValue().toString());
        clientAttackStages2  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("attack").getValue().toString());
        clientDefenseStages2  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("defense").getValue().toString());
        clientSpdefenseStages2  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("spdefense").getValue().toString());
        clientSpattackStages2  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("spattack").getValue().toString());
        clientSpeedStages2  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("speed").getValue().toString());
        clientAccuracyStages2  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("accuracy").getValue().toString());
        clientEvasionStages2  = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("evasion").getValue().toString());


        absoluteOrderOfMoves = Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Absolute Order of Moves").getValue().toString());

    }

    private void checkIfReady(DataSnapshot dataSnapshot) {
        //This will only become true if the host tells the database that it has done what it needs to do
       readyToPlay = dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).hasChild("Ready to Proceed");

    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void goPlayGame()
    {
        carryOn(1);
    }

    private void carryOn(int gameNum)
    {
        //We have all the data, so we can see the graphic activity and process the changes
        gameRoom = gameNum;
        Intent intent=new Intent(this, GraphicActivity.class);
        startActivity(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        AudioPlay.stopAudio();
        if(playingOnline)
        {
            DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();

            //Resetting the database for next time
            DatabaseReference ref3 = ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Has Chosen");
            DatabaseReference ref2 = ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Has Chosen");
            ref3.removeValue();
            ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Has Chosen").child("Default").setValue("0");

            //Remove Ready to Proceed
            ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).removeValue();

            //I only want one of them to do this
            if(host)
            {
                ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child("Default").setValue("0");
                ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Host").removeValue();

                //Setting the game to available now
                ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Available").setValue("true");
            }
        }
    }

    public void onStop()
    {
        super.onStop();
        /*AudioPlay.stopAudio();
        if(playingOnline)
        {
            DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();

            //Resetting the database for next time
            DatabaseReference ref3 = ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Has Chosen");
            DatabaseReference ref2 = ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Has Chosen");
            ref3.removeValue();
            ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Has Chosen").child("Default").setValue("0");

            //Remove Ready to Proceed
            ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).removeValue();

            //I only want one of them to do this
            if(host)
            {
                ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child("Default").setValue("0");
                ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Host").removeValue();

                //Setting the game to available now
                ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Available").setValue("true");
            }
        }*/
    }

}
