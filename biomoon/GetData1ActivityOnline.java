package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.greg.android.biomoon.Globals.name2;
import static com.greg.android.biomoon.HomeActivity.host;
import static com.greg.android.biomoon.HomeActivity.isP2CPU;
import static com.greg.android.biomoon.HomeActivity.playingOnline;
import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;

/**
 * Created by Greg on 2017-06-25.
 */

public class GetData1ActivityOnline extends Activity {
    boolean stayingInApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_data1_layout);
        stayingInApp=false;

        Button b = (Button) findViewById(R.id.button);
        TextView t = (TextView)findViewById(R.id.tv) ;
        EditText e = (EditText)findViewById(R.id.name1Box);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/turtles.otf");
        b.setTypeface(custom_font);
        t.setTypeface(custom_font);
        e.setTypeface(custom_font);
    }

    public void OpenGetData2Activity (View v)
    {
        stayingInApp=true;
        EditText edit = (EditText)findViewById(R.id.name1Box);
        Globals.name1 = edit.getText().toString();

        try
        {
            Globals.getMoves(this, "moveList.txt");
            Globals.getPokemon(this, "list.txt");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        Intent intent= new Intent(this, Play1ActivityOnline.class);
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

    @Override
    public void onPause() {
        super.onPause();
        if(!stayingInApp)
            AudioPlay.stopAudio();
    }

    @Override
    protected void onResume()  {
        super.onResume();
        if(!AudioPlay.isplayingAudio)
            AudioPlay.playAudio(this, R.raw.openingtheme);

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
