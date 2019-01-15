package com.greg.android.biomoon;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.greg.android.biomoon.Globals.name1;
import static com.greg.android.biomoon.Globals.name2;
import static com.greg.android.biomoon.Globals.winner;
import static com.greg.android.biomoon.HomeActivity.host;
import static com.greg.android.biomoon.HomeActivity.isP2CPU;
import static com.greg.android.biomoon.HomeActivity.playingOnline;
import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;

/**
 * Created by Greg on 2017-06-30.
 */

public class winActivity extends Activity {
    boolean cpuWon=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_layout);
        TextView t1 = (TextView)findViewById(R.id.message);
        Button b1 = (Button)findViewById(R.id.button);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/turtles.otf");
        t1.setTypeface(custom_font);
        b1.setTypeface(custom_font);
        ImageView imageView = (ImageView)findViewById(R.id.winorloseimg);


        TextView message = (TextView) findViewById(R.id.message);
        if(winner == 1)
        {
            message.setText("Congrats " + name1 + ", you have won!");

            imageView.setImageResource(R.drawable.youwin);
        }
        if(winner == 2)
        {
            if(isP2CPU) {
                message.setText("Joey won! Try Again Next Time!");
                imageView.setImageResource(R.drawable.loser);
                cpuWon = true;
            }
            else
                message.setText("Congrats " + name2 + ", you have won!");
        }

    }

    @Override
    public void onPause() {
        super.onPause();
            AudioPlay.stopAudio();
    }

    @Override
    protected void onStart()  {
        super.onStart();
        AudioPlay.stopAudio();
        if(!cpuWon)
        AudioPlay.playAudio(this, R.raw.wintheme);
        else
            AudioPlay.playAudio(this, R.raw.lost);

    }

    @Override
    protected void onResume()  {
        super.onResume();
        if(!AudioPlay.isplayingAudio)
            if(!cpuWon)
                AudioPlay.playAudio(this, R.raw.wintheme);
            else
                AudioPlay.playAudio(this, R.raw.lost);

    }

    public void restart(View view)
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
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
}