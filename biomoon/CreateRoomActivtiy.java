package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.HomeActivity.madeHostEarlier;

/**
 * Created by Greg on 2017-09-02.
 */

public class CreateRoomActivtiy extends Activity {

    public static String roomOwner;
    public static String password;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private Boolean madeAGame=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_rrom_layout);
        madeHostEarlier=true;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(madeAGame)
                {
                    myRef.removeEventListener(this);
                }
                else
                gameRoomNumGlobal = (int)dataSnapshot.child("Games").getChildrenCount() +1;
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });
    }

    public void proceed(View view)
    {
        EditText roomOwnerEditText = (EditText)findViewById(R.id.roomOwnerEditText);
        EditText passwordEditText = (EditText)findViewById(R.id.passwordEditText);

        roomOwner = roomOwnerEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();

        makeGame();
        goToActualWaitActivity();
    }

    private void goToActualWaitActivity()
    {
        Intent intent = new Intent(this, ActualWaitActivity.class);
        startActivity(intent);
    }

    //We actually make the game in ActualWaitActivity, this just changes a few things in it
    private void makeGame()
    {
        madeHostEarlier=true;
        madeAGame=true;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        madeHostEarlier=false;
    }

}
