package com.greg.android.biomoon;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;

public class HomeActivity extends AppCompatActivity {
    public static boolean isP2CPU = false;
    public static boolean host;
    public static boolean madeAHost;
    public static int healthAfterHit2=0;
    boolean stayingInApp;
    public static boolean firstTime = true;
    public static int gameRoom=0;
    public static boolean playingOnline=false;
    public static String otherPlayersKey="";
    public static boolean madeHostEarlier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gameRoom=0;
        madeAHost=false;
        host=false;
        madeHostEarlier=false;
        stayingInApp=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        isP2CPU =false;
    }

    void setup()
    {
        TextView tx = (TextView)findViewById(R.id.textView2);
        TextView txplay = (TextView)findViewById(R.id.textView);
        TextView txLogo = (TextView)findViewById(R.id.tvLogo);
        CheckBox check = (CheckBox) findViewById(R.id.checkBox);
        CheckBox check2 = (CheckBox) findViewById(R.id.playOnlineCheckbox);

        Button b = (Button) findViewById(R.id.button);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/turtles.otf");


        tx.setTypeface(custom_font);
        txplay.setTypeface(custom_font);
        check.setTypeface(custom_font);
        check2.setTypeface(custom_font);
        txLogo.setTypeface(custom_font);
        b.setTypeface(custom_font);
    }

    public void OpenGetData1Activity (View v)
    {
        stayingInApp=true;

        CheckBox c = (CheckBox) findViewById(R.id.checkBox);
        CheckBox onlineCheck = (CheckBox) findViewById(R.id.playOnlineCheckbox);

        if(c.isChecked())
        {
            isP2CPU=true;
        }
        else
        {
            isP2CPU=false;
        }

        if(onlineCheck.isChecked())
        {
            playingOnline=true;
        }
        else
        {
            playingOnline=false;
        }

        if(playingOnline)
        {
            Intent intent = new Intent(this, ListOfGamesActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, GetData1Activity.class);
            startActivity(intent);
        }

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
        if(playingOnline && !stayingInApp)
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
    protected void onStart()  {
        super.onStart();
        firstTime=true;
        if(!AudioPlay.isplayingAudio)
        AudioPlay.playAudio(this, R.raw.openingtheme);
    }

    @Override
    protected void onResume()  {
        super.onResume();
        if(!AudioPlay.isplayingAudio)
        AudioPlay.playAudio(this, R.raw.openingtheme);

    }

    public void goToSecretActivity(View view) {
        Intent intent = new Intent(this, SecretActivity.class);
        startActivity(intent);
    }
}
