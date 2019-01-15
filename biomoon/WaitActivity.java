package com.greg.android.biomoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.greg.android.biomoon.HomeActivity.host;
import static com.greg.android.biomoon.HomeActivity.playingOnline;


/**
 * Created by User on 2/8/2017.
 */

public class WaitActivity extends AppCompatActivity {
    private static final String TAG = "ViewDatabase";


    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    public static int gameRoomNumGlobal;
    public static String nameKeyOurs;
    private boolean haveJoinedGame=false;
    private TextView label;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wait_layout);

        label = (TextView)findViewById(R.id.labelTextView);
        label.append(gameRoomNumGlobal + "");

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Joins/makes first Active Game only on first time in this listener
                if(!haveJoinedGame)
                {
                    //It also makes a game if there isnt one we can join
                    findAndJoinAvailableGame(dataSnapshot);
                }

                //If both players have joined in a room, we can now start the game
                if(isReadyToPlay(dataSnapshot))
                {
                    goPlayGame();

                    //Have to Remove Listener so it doesn't keep going in it
                    myRef.removeEventListener(this);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    private void goPlayGame() {
        Intent intent=new Intent(this, GetData1ActivityOnline.class);
        startActivity(intent);
    }

    private void makeAGame(int attemptedGameRoomNum)
    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        myRef.child("Games").child(Integer.toString(attemptedGameRoomNum)).child("Has Chosen").child("Default").setValue("0");
        myRef.child("Games").child(Integer.toString(attemptedGameRoomNum)).child("Users").child("Default").setValue("true");
        myRef.child("Games").child(Integer.toString(attemptedGameRoomNum)).child("Available").setValue("true");
    }

    private void findAndJoinAvailableGame(DataSnapshot dataSnapshot)
    {
        Boolean available;
        int attemptedGameRoomNum=0;
        do
        {
            attemptedGameRoomNum++;

            //Check if a game with this number exists
            if(dataSnapshot.child("Games").child(Integer.toString(attemptedGameRoomNum)).exists())
            {
                //If the game exists, check if it is available
                available = (Boolean.parseBoolean(dataSnapshot.child("Games").child(Integer.toString(attemptedGameRoomNum)).child("Available").getValue().toString()));
            }
            else
            {
                //A game with this room number does not exist, so let's make it exist!
                makeAGame(attemptedGameRoomNum);

                //We now know this game is available (since we made it), so we can now say that we found an available game
                available=true;
            }
        }
        while(!available);

        haveJoinedGame=true;
        gameRoomNumGlobal = attemptedGameRoomNum;
        tellDatabaseWeHaveJoined();
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void tellDatabaseWeHaveJoined()
    {
        //add Firebase Database stuff
         FirebaseDatabase mFirebaseDatabase;
         DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        nameKeyOurs= myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").push().getKey();
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).setValue("true");
    }

    private boolean isReadyToPlay(DataSnapshot dataSnapshot)
    {
        long amount = dataSnapshot.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").getChildrenCount();
        if(amount>2)
        {
            //Both players have joined, so this sets available to false
            makeThisGameUnavailable();
            return true;
        }
        else
        {
            return false;
        }
    }

    private void makeThisGameUnavailable()
    {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Available").setValue("false");
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
