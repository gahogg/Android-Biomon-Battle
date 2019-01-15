package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.greg.android.biomoon.Globals.activePokemon1;
import static com.greg.android.biomoon.Globals.name1;
import static com.greg.android.biomoon.Globals.p1Pokemon;
import static com.greg.android.biomoon.Battle1Activity.previousPokemonDexNumber1;
import static com.greg.android.biomoon.HomeActivity.host;
import static com.greg.android.biomoon.HomeActivity.playingOnline;
import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;

/**
 * Created by Greg on 2017-07-04.
 */

public class Switch1ActivityOnline extends Activity {
    boolean stayingInApp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        stayingInApp=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch1activity);

        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Switching In").setValue("true");

        TextView tv1 = (TextView)findViewById(R.id.tv1);
        String msg1 = name1 + ", who are you going to switch in?";
        tv1.setText(msg1);

        Button b1a = (Button)findViewById(R.id.pokemon1_1);
        b1a.setText(p1Pokemon[0].name);
        Button b2a = (Button)findViewById(R.id.pokemon2_1);
        b2a.setText(p1Pokemon[1].name);
        Button b3a = (Button)findViewById(R.id.pokemon3_1);
        b3a.setText(p1Pokemon[2].name);
        Button b4a = (Button)findViewById(R.id.pokemon4_1);
        b4a.setText(p1Pokemon[3].name);
        Button b5a = (Button)findViewById(R.id.pokemon5_1);
        b5a.setText(p1Pokemon[4].name);
        Button b6a = (Button)findViewById(R.id.pokemon6_1);
        b6a.setText(p1Pokemon[5].name);


        if(p1Pokemon[0].fainted)
        {
            b1a.setEnabled(false);
        }
        if(p1Pokemon[1].fainted)
        {
            b2a.setEnabled(false);
        }
        if(p1Pokemon[2].fainted)
        {
            b3a.setEnabled(false);
        }
        if(p1Pokemon[3].fainted)
        {
            b4a.setEnabled(false);
        }
        if(p1Pokemon[4].fainted)
        {
            b5a.setEnabled(false);
        }
        if(p1Pokemon[5].fainted)
        {
            b6a.setEnabled(false);
        }

        TextView t1 = (TextView)findViewById(R.id.tv1) ;
        TextView t8 = (TextView)findViewById(R.id.biomonlogo) ;
        Button b1 = (Button) findViewById(R.id.pokemon1_1);
        Button b2 = (Button) findViewById(R.id.pokemon2_1);
        Button b3 = (Button) findViewById(R.id.pokemon3_1);
        Button b4 = (Button) findViewById(R.id.pokemon4_1);
        Button b5 = (Button) findViewById(R.id.pokemon5_1);
        Button b6 = (Button) findViewById(R.id.pokemon6_1);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/turtles.otf");
        t1.setTypeface(custom_font);
        t8.setTypeface(custom_font);

        b1.setTypeface(custom_font);
        b2.setTypeface(custom_font);
        b3.setTypeface(custom_font);
        b4.setTypeface(custom_font);
        b5.setTypeface(custom_font);
        b6.setTypeface(custom_font);
    }

    public void Choose1Lead1(View view) {
        stayingInApp=true;
        p1Pokemon[0].resetPokemon();
        activePokemon1 = p1Pokemon[0];
        activePokemon1.fainted = false;
        previousPokemonDexNumber1 = activePokemon1.dexNumber;
        Intent intent = new Intent(this, WaitForSwitchInActivityOnline.class);
        startActivity(intent);

    }

    public void Choose1Lead2(View view) {
        stayingInApp=true;
        p1Pokemon[1].resetPokemon();
        activePokemon1 = p1Pokemon[1];
        activePokemon1.fainted = false;
        previousPokemonDexNumber1 = activePokemon1.dexNumber;
        Intent intent = new Intent(this, WaitForSwitchInActivityOnline.class);
        startActivity(intent);

    }

    public void Choose1Lead3(View view) {
        stayingInApp=true;
        p1Pokemon[2].resetPokemon();
        activePokemon1 = p1Pokemon[2];
        activePokemon1.fainted = false;
        previousPokemonDexNumber1 = activePokemon1.dexNumber;
        Intent intent = new Intent(this, WaitForSwitchInActivityOnline.class);
        startActivity(intent);

    }

    public void Choose1Lead4(View view) {
        stayingInApp=true;
        p1Pokemon[3].resetPokemon();
        activePokemon1 = p1Pokemon[3];
        activePokemon1.fainted = false;
        previousPokemonDexNumber1 = activePokemon1.dexNumber;
        Intent intent = new Intent(this, WaitForSwitchInActivityOnline.class);
        startActivity(intent);
    }

    public void Choose1Lead5(View view) {
        stayingInApp=true;
        p1Pokemon[4].resetPokemon();
        activePokemon1 = p1Pokemon[4];
        activePokemon1.fainted = false;
        previousPokemonDexNumber1 = activePokemon1.dexNumber;
        Intent intent = new Intent(this, WaitForSwitchInActivityOnline.class);
        startActivity(intent);
    }

    public void Choose1Lead6(View view) {
        stayingInApp=true;
        p1Pokemon[5].resetPokemon();
        activePokemon1 = p1Pokemon[5];
        activePokemon1.fainted = false;
        previousPokemonDexNumber1 = activePokemon1.dexNumber;
        Intent intent = new Intent(this, WaitForSwitchInActivityOnline.class);
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
            AudioPlay.playAudio(this, R.raw.battletheme);

    }

    private void playBringOutNewPokemon() {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.bringoutnewpokemon);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }

        });
    }
}
