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
import static com.greg.android.biomoon.Globals.doAlmostEverythingHostVersion;
import static com.greg.android.biomoon.Globals.moves;
import static com.greg.android.biomoon.Globals.name1;
import static com.greg.android.biomoon.Globals.p1Ref;
import static com.greg.android.biomoon.Globals.p2Pokemon;
import static com.greg.android.biomoon.Globals.p2Switch;
import static com.greg.android.biomoon.Globals.usedMove2;
import static com.greg.android.biomoon.HomeActivity.gameRoom;
import static com.greg.android.biomoon.HomeActivity.host;
import static com.greg.android.biomoon.HomeActivity.otherPlayersKey;
import static com.greg.android.biomoon.Globals.activePokemon2;
import static com.greg.android.biomoon.HomeActivity.playingOnline;
import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;

/**
 * Created by User on 2/8/2017.
 */

public class WaitForMoveOrSwitch extends AppCompatActivity {
    private static final String TAG = "WaitForMoveOrSwitch";

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private boolean readyToPlay;
    private boolean shouldDo;
    private boolean shouldAddBasicallyEverything;
    private boolean game1Available;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wait_layout);
        readyToPlay=false;
        shouldDo=true;
        shouldAddBasicallyEverything=true;

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                if(readyToPlay)
                {
                    shouldDo=false;
                    goPlayGame();
                }
                else if(shouldDo)
                {
                    showData(dataSnapshot);
                }

                if(readyToPlay)
                {
                    myRef.removeEventListener(this);
                    goPlayGame();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {

            game1Available=true;

            if(shouldAddBasicallyEverything)
            {
                tellDatabaseWeAreReadyToProceed();
                shouldAddBasicallyEverything=false;
            }

            readyToPlay =false;

            long amount = dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Has Chosen").getChildrenCount();
            if (amount>2)
            {
                readyToPlay =true;

                //We know that both players have locked in their decisions, so now getting the Other User's Decision

                //Checking whether other player switched or not
                if(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Switch").getValue().toString().equals("false"))
                {

                    //They didn't switch, so they used a move- Getting that move now and assigning it to usedMove2
                    p2Switch =false;

                    String indicieStr = dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Used Move").getValue().toString();
                    int indicie = Integer.parseInt(indicieStr);

                    usedMove2 = moves[indicie];

                }
                else
                {
                    //The other player switched, so let's set activePokemon2 to whatever they switched to
                    activePokemon2 = p2Pokemon[(Integer.parseInt(dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Switch").getValue().toString()))];
                    p2Switch=true;
                }
            }

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
        if(game1Available)
        {
            playGame(1);
        }
    }

    private void playGame(int gameNum)
    {
        gameRoom = gameNum;

        if(host)
        {
            //Since we are host, do all the calculations and stuff on the host phone, and upload
            //what is neccessary to do the database
            doAlmostEverythingHostVersion();

            //The above function also tells other player we are done everything so they can carry on
            //by doing this code....
            //myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Ready to Proceed").setValue("true");

            //Then proceed to the graphic
            Intent intent = new Intent(this, GraphicActivity.class);
            startActivity(intent);

        }
        else
        {
            //We are not the host, so we need to gather all of the data- go to this activity to do so
            Intent intent = new Intent(this, GetAlmostAllDataFromDatabaseActivity.class);
            startActivity(intent);
        }

    }

    void tellDatabaseWeAreReadyToProceed()
    {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        //Basically tells other player that we have done what we need to do so we can move on
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Has Chosen").push().setValue("true");
    }

    @Override
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
        AudioPlay.stopAudio();
        /*if(playingOnline)
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
