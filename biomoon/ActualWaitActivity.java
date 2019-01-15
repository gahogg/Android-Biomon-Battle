package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.greg.android.biomoon.HomeActivity.madeHostEarlier;
import static com.greg.android.biomoon.CreateRoomActivtiy.roomOwner;
import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;

/**
 * Created by Greg on 2017-09-02.
 */

public class ActualWaitActivity extends Activity{
    boolean haveJoinedGame =false;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wait_layout);

        TextView label = (TextView)findViewById(R.id.labelTextView);
        label.setText("We are waiting for someone to join game " + gameRoomNumGlobal);

         mFirebaseDatabase = FirebaseDatabase.getInstance();
         myRef = mFirebaseDatabase.getReference();

        //If we need to make a game, make it, and place us in it
        if (madeHostEarlier)
        {
            makeAGame(gameRoomNumGlobal);
        }

        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Joins/makes first Active Game only on first time in this listener
                if(!haveJoinedGame)
                {
                    //Places us in the game we selected from the list activity/just made
                    joinGame(dataSnapshot);
                }

                //If both players have joined in a room, we can now start the game
                //We can
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

    private void goPlayGame()
    {
        Intent intent=new Intent(this, GetData1ActivityOnline.class);
        startActivity(intent);
    }

    private void makeAGame(int attemptedGameRoomNum)
    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        if(madeHostEarlier)
        myRef.child("Games").child(Integer.toString(attemptedGameRoomNum)).child("Host").setValue(roomOwner);

        myRef.child("Games").child(Integer.toString(attemptedGameRoomNum)).child("Has Chosen").child("Default").setValue("0");
        myRef.child("Games").child(Integer.toString(attemptedGameRoomNum)).child("Users").child("Default").setValue("true");
        myRef.child("Games").child(Integer.toString(attemptedGameRoomNum)).child("Available").setValue("true");
    }

    private void joinGame(DataSnapshot dataSnapshot)
    {
        haveJoinedGame=true;
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

    }

