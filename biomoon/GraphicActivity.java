package com.greg.android.biomoon;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import static com.greg.android.biomoon.Globals.CPUSwitch;
import static com.greg.android.biomoon.Globals.absoluteOrderOfMoves;
import static com.greg.android.biomoon.Globals.activePokemon1;
import static com.greg.android.biomoon.Globals.activePokemon2;
import static com.greg.android.biomoon.Globals.doAlmostEverythingClientVersion;
import static com.greg.android.biomoon.Globals.fainted1;
import static com.greg.android.biomoon.Globals.fainted2;
import static com.greg.android.biomoon.Globals.healthAfterHealing1;
import static com.greg.android.biomoon.Globals.healthAfterHealing2;
import static com.greg.android.biomoon.Globals.healthBeforeHealing1;
import static com.greg.android.biomoon.Globals.healthBeforeHealing2;
import static com.greg.android.biomoon.Globals.moveUse1;
import static com.greg.android.biomoon.Globals.moveUse2;
import static com.greg.android.biomoon.Globals.p1Critt;
import static com.greg.android.biomoon.Globals.p2Critt;
import static com.greg.android.biomoon.Globals.player1Healed;
import static com.greg.android.biomoon.Globals.weather;
import static com.greg.android.biomoon.HomeActivity.host;
import static com.greg.android.biomoon.HomeActivity.isP2CPU;
import static com.greg.android.biomoon.Globals.name1;
import static com.greg.android.biomoon.Globals.name2;
import static com.greg.android.biomoon.Globals.p1Damage;
import static com.greg.android.biomoon.Globals.p1Pokemon;
import static com.greg.android.biomoon.Globals.p1Switch;
import static com.greg.android.biomoon.Globals.p2Pokemon;
import static com.greg.android.biomoon.Globals.p2Switch;
import static com.greg.android.biomoon.Globals.p2Damage;
import static com.greg.android.biomoon.Globals.usedMove1;
import static com.greg.android.biomoon.Globals.usedMove2;
import static com.greg.android.biomoon.Globals.winner;
import static com.greg.android.biomoon.Globals.start1;
import static com.greg.android.biomoon.Globals.start2;
import static com.greg.android.biomoon.Battle1Activity.previousPokemonDexNumber1;
import static com.greg.android.biomoon.Battle1Activity.previousPokemonDexNumber2;
import static com.greg.android.biomoon.Globals.healthAfterHit1;
import static com.greg.android.biomoon.HomeActivity.healthAfterHit2;
import static com.greg.android.biomoon.Globals.absoluteStart1;
import static com.greg.android.biomoon.Globals.absoluteStart2;
import static com.greg.android.biomoon.Globals.p1Effectiveness;
import static com.greg.android.biomoon.Globals.p2Effectiveness;
import static com.greg.android.biomoon.Globals.player2Healed;
import static com.greg.android.biomoon.HomeActivity.playingOnline;
import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;

/**
 * Created by Greg on 2017-06-30.
 */


public class GraphicActivity extends Activity {

    public static int animationDone=0;
    public boolean graphicsDone=false;
    boolean stayingInApp =false;

void helpingDebugByUploadAfterDoneEverythingBeforeAnimation()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p1 flinch is").setValue(activePokemon1.flinch);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p2 flinch is").setValue(activePokemon2.flinch);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p1 fainted is").setValue(activePokemon1.fainted);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p2 fainted is").setValue(activePokemon2.fainted);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p1 damage is").setValue(p1Damage);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p2 damage is").setValue(p2Damage);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p1 crit is").setValue(p1Critt);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p2 crit is").setValue(p2Critt);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p1 hit is").setValue(activePokemon1.hit);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p2 hit is").setValue(activePokemon2.hit);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p1 fail is").setValue(usedMove1.fail);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p2 fail is").setValue(usedMove2.fail);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p1 move is").setValue(usedMove1.name);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p2 move is").setValue(usedMove2.name);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p1 switch is").setValue(p1Switch);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p2 switch is").setValue(p2Switch);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p1 hp is").setValue(activePokemon1.hp);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, client claims p2 hp is").setValue(activePokemon2.hp);
        }
        if(host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p1 flinch is").setValue(activePokemon1.flinch);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p2 flinch is").setValue(activePokemon2.flinch);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p1 fainted is").setValue(activePokemon1.fainted);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p2 fainted is").setValue(activePokemon2.fainted);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p1 damage is").setValue(p1Damage);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p2 damage is").setValue(p2Damage);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p1 crit is").setValue(p1Critt);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p2 crit is").setValue(p2Critt);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p1 hit is").setValue(activePokemon1.hit);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p2 hit is").setValue(activePokemon2.hit);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p1 fail is").setValue(usedMove1.fail);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p2 fail is").setValue(usedMove2.fail);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p1 move is").setValue(usedMove1.name);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p2 move is").setValue(usedMove2.name);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p1 switch is").setValue(p1Switch);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p2 switch is").setValue(p2Switch);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p1 hp is").setValue(activePokemon1.hp);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims p2 hp is").setValue(activePokemon2.hp);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims start1 is").setValue(start1);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims start2 is").setValue(start2);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims absoluteStart1 is").setValue(absoluteStart1);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims absoluteStart2 is").setValue(absoluteStart2);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims healthAfterHit1 is").setValue(healthAfterHit1);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims healthAfterHit2 is").setValue(healthAfterHit2);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims healthAfterHealing1 is").setValue(healthAfterHealing1);
            myRef.child("Games").child("Important Info After Done Everything Before Animation").child("After done everything, host claims healthAfterHealing2 is").setValue(healthAfterHealing2);
        }
    }

void helpingDebugByUploadAfterDoneEverythingAfterAnimation()
{
    if(host)
    {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p1 flinch is").setValue(activePokemon1.flinch);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p2 flinch is").setValue(activePokemon2.flinch);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p1 fainted is").setValue(activePokemon1.fainted);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p2 fainted is").setValue(activePokemon2.fainted);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p1 damage is").setValue(p1Damage);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p2 damage is").setValue(p2Damage);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p1 crit is").setValue(p1Critt);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p2 crit is").setValue(p2Critt);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p1 hit is").setValue(activePokemon1.hit);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p2 hit is").setValue(activePokemon2.hit);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p1 fail is").setValue(usedMove1.fail);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p2 fail is").setValue(usedMove2.fail);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p1 move is").setValue(usedMove1.name);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p2 move is").setValue(usedMove2.name);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p1 switch is").setValue(p1Switch);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p2 switch is").setValue(p2Switch);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p1 hp is").setValue(activePokemon1.hp);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims p2 hp is").setValue(activePokemon2.hp);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims start1 is").setValue(start1);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims start2 is").setValue(start2);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims absoluteStart1 is").setValue(absoluteStart1);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims absoluteStart2 is").setValue(absoluteStart2);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims healthAfterHit1 is").setValue(healthAfterHit1);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims healthAfterHit2 is").setValue(healthAfterHit2);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims healthAfterHealing1 is").setValue(healthAfterHealing1);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, host claims healthAfterHealing2 is").setValue(healthAfterHealing2);
    }
    if(!host)
    {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p1 flinch is").setValue(activePokemon1.flinch);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p2 flinch is").setValue(activePokemon2.flinch);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p1 fainted is").setValue(activePokemon1.fainted);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p2 fainted is").setValue(activePokemon2.fainted);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p1 damage is").setValue(p1Damage);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p2 damage is").setValue(p2Damage);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p1 crit is").setValue(p1Critt);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p2 crit is").setValue(p2Critt);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p1 hit is").setValue(activePokemon1.hit);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p2 hit is").setValue(activePokemon2.hit);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p1 fail is").setValue(usedMove1.fail);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p2 fail is").setValue(usedMove2.fail);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p1 move is").setValue(usedMove1.name);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p2 move is").setValue(usedMove2.name);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p1 switch is").setValue(p1Switch);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p2 switch is").setValue(p2Switch);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p1 hp is").setValue(activePokemon1.hp);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims p2 hp is").setValue(activePokemon2.hp);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims start1 is").setValue(start1);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims start2 is").setValue(start2);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims absoluteStart1 is").setValue(absoluteStart1);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims absoluteStart2 is").setValue(absoluteStart2);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims healthAfterHit1 is").setValue(healthAfterHit1);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims healthAfterHit2 is").setValue(healthAfterHit2);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims healthAfterHealing1 is").setValue(healthAfterHealing1);
        myRef.child("Games").child("Important Info After Done Everything After Animation").child("After done everything, client claims healthAfterHealing2 is").setValue(healthAfterHealing2);
    }

}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphic_layout);

        if(playingOnline)
            //helpingDebugByUploadAfterDoneEverythingBeforeAnimation();

        if(!host && playingOnline)
        doAlmostEverythingClientVersion();

        if(host)
        {
            //Waiting to ensure other phone has connected properly to reset the has chosen
            //(2 seconds should easily be enough)
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();

                    //Remove Has Chosen (But add back in the default)
                    DatabaseReference ref3 = ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Has Chosen");
                    ref3.removeValue();
                    ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Has Chosen").child("Default").setValue("0");

                    //Remove Ready to Proceed
                    ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Ready to Proceed").removeValue();
                }
            }, 1500);
        }

        initialSetup();
        stayingInApp=false;
        animateEverything();

        //helpingDebugByUploadAfterDoneEverythingAfterAnimation();

    }

    void animation1Color() {
        /*TextView atk1 = (TextView) findViewById(R.id.atk1);

        if (!p1Switch) {
            if (usedMove1.category == 1 || usedMove1.category == 2) {
                switch (usedMove1.type) {
                    case 1:
                        atk1.setBackgroundColor(Color.parseColor("#e0e0e0"));
                        break;
                    case 2:
                        atk1.setBackgroundColor(Color.parseColor("#e53935"));
                        break;
                    case 3:
                        atk1.setBackgroundColor(Color.parseColor("#3f51b5"));
                        break;
                    case 4:
                        atk1.setBackgroundColor(Color.parseColor("#fdd835"));
                        break;
                    case 5:
                        atk1.setBackgroundColor(Color.parseColor("#43a047"));
                        break;
                    case 6:
                        atk1.setBackgroundColor(Color.parseColor("#81d4fa"));
                        break;
                    case 7:
                        atk1.setBackgroundColor(Color.parseColor("#d50000"));
                        break;
                    case 8:
                        atk1.setBackgroundColor(Color.parseColor("#9c27b0"));
                        break;
                    case 9:
                        atk1.setBackgroundColor(Color.parseColor("#795548"));
                        break;
                    case 10:
                        atk1.setBackgroundColor(Color.parseColor("#e1bee7"));
                        break;
                    case 11:
                        atk1.setBackgroundColor(Color.parseColor("#f48fb1"));
                        break;
                    case 12:
                        atk1.setBackgroundColor(Color.parseColor("#64dd17"));
                        break;
                    case 13:
                        atk1.setBackgroundColor(Color.parseColor("#5d4037"));
                        break;
                    case 14:
                        atk1.setBackgroundColor(Color.parseColor("#4a148c "));
                        break;
                    case 15:
                        atk1.setBackgroundColor(Color.parseColor("#311b92 "));
                        break;
                    case 16:
                        atk1.setBackgroundColor(Color.parseColor("#212121 "));
                        break;
                    case 17:
                        atk1.setBackgroundColor(Color.parseColor("#b0bec5"));
                        break;
                    case 18:
                        atk1.setBackgroundColor(Color.parseColor("#ff1744"));
                        break;

                }
            }

        }
        */
    }

    void animation2Color() {
        /*TextView atk2 = (TextView) findViewById(R.id.atk2);

        if (!p2Switch) {
            if (usedMove2.category == 1 || usedMove2.category == 2) {
                switch (usedMove2.type) {
                    case 1:
                        atk2.setBackgroundColor(Color.parseColor("#e0e0e0"));
                        break;
                    case 2:
                        atk2.setBackgroundColor(Color.parseColor("#e53935"));
                        break;
                    case 3:
                        atk2.setBackgroundColor(Color.parseColor("#3f51b5"));
                        break;
                    case 4:
                        atk2.setBackgroundColor(Color.parseColor("#fdd835"));
                        break;
                    case 5:
                        atk2.setBackgroundColor(Color.parseColor("#43a047"));
                        break;
                    case 6:
                        atk2.setBackgroundColor(Color.parseColor("#81d4fa"));
                        break;
                    case 7:
                        atk2.setBackgroundColor(Color.parseColor("#d50000"));
                        break;
                    case 8:
                        atk2.setBackgroundColor(Color.parseColor("#9c27b0"));
                        break;
                    case 9:
                        atk2.setBackgroundColor(Color.parseColor("#795548"));
                        break;
                    case 10:
                        atk2.setBackgroundColor(Color.parseColor("#e1bee7"));
                        break;
                    case 11:
                        atk2.setBackgroundColor(Color.parseColor("#f48fb1"));
                        break;
                    case 12:
                        atk2.setBackgroundColor(Color.parseColor("#64dd17"));
                        break;
                    case 13:
                        atk2.setBackgroundColor(Color.parseColor("#5d4037"));
                        break;
                    case 14:
                        atk2.setBackgroundColor(Color.parseColor("#4a148c "));
                        break;
                    case 15:
                        atk2.setBackgroundColor(Color.parseColor("#311b92 "));
                        break;
                    case 16:
                        atk2.setBackgroundColor(Color.parseColor("#212121 "));
                        break;
                    case 17:
                        atk2.setBackgroundColor(Color.parseColor("#b0bec5"));
                        break;
                    case 18:
                        atk2.setBackgroundColor(Color.parseColor("#ff1744"));
                        break;

                }
            }

        }*/
    }

    public void setFirstPics1() {
        ImageView img1 = (ImageView) findViewById(R.id.gifp1);

        if (previousPokemonDexNumber1 == 1) {
            img1.setImageResource(R.drawable.a1);
        }

        if (previousPokemonDexNumber1 == 2) {
            img1.setImageResource(R.drawable.a2);
        }


        if (previousPokemonDexNumber1 == 3) {
            img1.setImageResource(R.drawable.a3);
        }


        if (previousPokemonDexNumber1 == 4) {
            img1.setImageResource(R.drawable.a4);
        }

        if (previousPokemonDexNumber1 == 5) {
            img1.setImageResource(R.drawable.a5);
        }


        if (previousPokemonDexNumber1 == 6) {
            img1.setImageResource(R.drawable.a6);
        }


        if (previousPokemonDexNumber1 == 7) {
            img1.setImageResource(R.drawable.a7);
        }

        if (previousPokemonDexNumber1 == 8) {
            img1.setImageResource(R.drawable.a8);
        }


        if (previousPokemonDexNumber1 == 9) {
            img1.setImageResource(R.drawable.a9);
        }


        if (previousPokemonDexNumber1 == 10) {
            img1.setImageResource(R.drawable.a10);
        }

        if (previousPokemonDexNumber1 == 11) {
            img1.setImageResource(R.drawable.a11);
        }


        if (previousPokemonDexNumber1 == 12) {
            img1.setImageResource(R.drawable.a12);
        }


        if (previousPokemonDexNumber1 == 13) {
            img1.setImageResource(R.drawable.a13);
        }


        if (previousPokemonDexNumber1 == 14) {
            img1.setImageResource(R.drawable.a14);
        }


        if (previousPokemonDexNumber1 == 15) {
            img1.setImageResource(R.drawable.a15);
        }


        if (previousPokemonDexNumber1 == 16) {
            img1.setImageResource(R.drawable.a16);
        }


        if (previousPokemonDexNumber1 == 17) {
            img1.setImageResource(R.drawable.a17);
        }


        if (previousPokemonDexNumber1 == 18) {
            img1.setImageResource(R.drawable.a18);
        }


        if (previousPokemonDexNumber1 == 19) {
            img1.setImageResource(R.drawable.a19);
        }


        if (previousPokemonDexNumber1 == 20) {
            img1.setImageResource(R.drawable.a20);
        }


        if (previousPokemonDexNumber1 == 21) {
            img1.setImageResource(R.drawable.a21);
        }


        if (previousPokemonDexNumber1 == 22) {
            img1.setImageResource(R.drawable.a22);
        }


        if (previousPokemonDexNumber1 == 23) {
            img1.setImageResource(R.drawable.a23);
        }


        if (previousPokemonDexNumber1 == 24) {
            img1.setImageResource(R.drawable.a24);
        }

        if (previousPokemonDexNumber1 == 25) {
            img1.setImageResource(R.drawable.a25);
        }


        if (previousPokemonDexNumber1 == 26) {
            img1.setImageResource(R.drawable.a26);
        }


        if (previousPokemonDexNumber1 == 27) {
            img1.setImageResource(R.drawable.a27);
        }


        if (previousPokemonDexNumber1 == 28) {
            img1.setImageResource(R.drawable.a28);
        }


        if (previousPokemonDexNumber1 == 29) {
            img1.setImageResource(R.drawable.a29);
        }

        if (previousPokemonDexNumber1 == 30) {
            img1.setImageResource(R.drawable.a30);
        }


        if (previousPokemonDexNumber1 == 31) {
            img1.setImageResource(R.drawable.a31);
        }

        if (previousPokemonDexNumber1 == 32) {
            img1.setImageResource(R.drawable.a32);
        }


        if (previousPokemonDexNumber1 == 33) {
            img1.setImageResource(R.drawable.a33);
        }


        if (previousPokemonDexNumber1 == 34) {
            img1.setImageResource(R.drawable.a34);
        }


        if (previousPokemonDexNumber1 == 35) {
            img1.setImageResource(R.drawable.a35);
        }


        if (previousPokemonDexNumber1 == 36) {
            img1.setImageResource(R.drawable.a36);
        }

        if (previousPokemonDexNumber1 == 37) {
            img1.setImageResource(R.drawable.a37);
        }


        if (previousPokemonDexNumber1 == 38) {
            img1.setImageResource(R.drawable.a38);
        }

        if (previousPokemonDexNumber1 == 39) {
            img1.setImageResource(R.drawable.a39);
        }


        if (previousPokemonDexNumber1 == 40) {
            img1.setImageResource(R.drawable.a40);
        }


        if (previousPokemonDexNumber1 == 41) {
            img1.setImageResource(R.drawable.a41);
        }

        if (previousPokemonDexNumber1 == 42) {
            img1.setImageResource(R.drawable.a42);
        }


        if (previousPokemonDexNumber1 == 43) {
            img1.setImageResource(R.drawable.a43);
        }


        if (previousPokemonDexNumber1 == 44) {
            img1.setImageResource(R.drawable.a44);
        }

        if (previousPokemonDexNumber1 == 45) {
            img1.setImageResource(R.drawable.a45);
        }


        if (previousPokemonDexNumber1 == 46) {
            img1.setImageResource(R.drawable.a46);
        }


        if (previousPokemonDexNumber1 == 47) {
            img1.setImageResource(R.drawable.a47);
        }


        if (previousPokemonDexNumber1 == 48) {
            img1.setImageResource(R.drawable.a48);
        }


        if (previousPokemonDexNumber1 == 49) {
            img1.setImageResource(R.drawable.a49);
        }


        if (previousPokemonDexNumber1 == 50) {
            img1.setImageResource(R.drawable.a50);
        }

        if (previousPokemonDexNumber1 == 51) {
            img1.setImageResource(R.drawable.a51);
        }

        if (previousPokemonDexNumber1 == 52) {
            img1.setImageResource(R.drawable.a52);
        }

        if (previousPokemonDexNumber1 == 53) {
            img1.setImageResource(R.drawable.a53);
        }


        if (previousPokemonDexNumber1 == 54) {
            img1.setImageResource(R.drawable.a54);
        }


        if (previousPokemonDexNumber1 == 55) {
            img1.setImageResource(R.drawable.a55);
        }


        if (previousPokemonDexNumber1 == 56) {
            img1.setImageResource(R.drawable.a56);
        }


        if (previousPokemonDexNumber1 == 57) {
            img1.setImageResource(R.drawable.a57);
        }


        if (previousPokemonDexNumber1 == 58) {
            img1.setImageResource(R.drawable.a5);
        }

        if (previousPokemonDexNumber1 == 59) {
            img1.setImageResource(R.drawable.a59);
        }


        if (previousPokemonDexNumber1 == 60) {
            img1.setImageResource(R.drawable.a60);
        }

        if (previousPokemonDexNumber1 == 61) {
            img1.setImageResource(R.drawable.a61);
        }


        if (previousPokemonDexNumber1 == 62) {
            img1.setImageResource(R.drawable.a62);
        }


        if (previousPokemonDexNumber1 == 63) {
            img1.setImageResource(R.drawable.a63);
        }


        if (previousPokemonDexNumber1 == 64) {
            img1.setImageResource(R.drawable.a64);
        }

        if (previousPokemonDexNumber1 == 65) {
            img1.setImageResource(R.drawable.a65);
        }

        if (previousPokemonDexNumber1 == 66) {
            img1.setImageResource(R.drawable.a66);
        }

        if (previousPokemonDexNumber1 == 67) {
            img1.setImageResource(R.drawable.a67);
        }


        if (previousPokemonDexNumber1 == 68) {
            img1.setImageResource(R.drawable.a68);
        }

        if (previousPokemonDexNumber1 == 69) {
            img1.setImageResource(R.drawable.a69);
        }


        if (previousPokemonDexNumber1 == 70) {
            img1.setImageResource(R.drawable.a70);
        }

        if (previousPokemonDexNumber1 == 71) {
            img1.setImageResource(R.drawable.a71);
        }


        if (previousPokemonDexNumber1 == 72) {
            img1.setImageResource(R.drawable.a72);
        }


        if (previousPokemonDexNumber1 == 73) {
            img1.setImageResource(R.drawable.a73);
        }


        if (previousPokemonDexNumber1 == 74) {
            img1.setImageResource(R.drawable.a74);
        }

        if (previousPokemonDexNumber1 == 75) {
            img1.setImageResource(R.drawable.a75);
        }


        if (previousPokemonDexNumber1 == 76) {
            img1.setImageResource(R.drawable.a76);
        }


        if (previousPokemonDexNumber1 == 77) {
            img1.setImageResource(R.drawable.a77);
        }


        if (previousPokemonDexNumber1 == 78) {
            img1.setImageResource(R.drawable.a78);
        }


        if (previousPokemonDexNumber1 == 79) {
            img1.setImageResource(R.drawable.a79);
        }


        if (previousPokemonDexNumber1 == 80) {
            img1.setImageResource(R.drawable.a80);
        }


        if (previousPokemonDexNumber1 == 81) {
            img1.setImageResource(R.drawable.a81);
        }


        if (previousPokemonDexNumber1 == 82) {
            img1.setImageResource(R.drawable.a82);
        }


        if (previousPokemonDexNumber1 == 83) {
            img1.setImageResource(R.drawable.a83);
        }


        if (previousPokemonDexNumber1 == 84) {
            img1.setImageResource(R.drawable.a84);
        }


        if (previousPokemonDexNumber1 == 85) {
            img1.setImageResource(R.drawable.a85);
        }


        if (previousPokemonDexNumber1 == 86) {
            img1.setImageResource(R.drawable.a86);
        }

        if (previousPokemonDexNumber1 == 87) {
            img1.setImageResource(R.drawable.a87);
        }


        if (previousPokemonDexNumber1 == 88) {
            img1.setImageResource(R.drawable.a88);
        }


        if (previousPokemonDexNumber1 == 89) {
            img1.setImageResource(R.drawable.a89);
        }


        if (previousPokemonDexNumber1 == 90) {
            img1.setImageResource(R.drawable.a90);
        }

        if (previousPokemonDexNumber1 == 91) {
            img1.setImageResource(R.drawable.a91);
        }


        if (previousPokemonDexNumber1 == 92) {
            img1.setImageResource(R.drawable.a92);
        }


        if (previousPokemonDexNumber1 == 93) {
            img1.setImageResource(R.drawable.a93);
        }


        if (previousPokemonDexNumber1 == 94) {
            img1.setImageResource(R.drawable.a94);
        }


        if (previousPokemonDexNumber1 == 95) {
            img1.setImageResource(R.drawable.a95);
        }


        if (previousPokemonDexNumber1 == 96) {
            img1.setImageResource(R.drawable.a96);
        }


        if (previousPokemonDexNumber1 == 97) {
            img1.setImageResource(R.drawable.a97);
        }


        if (previousPokemonDexNumber1 == 98) {
            img1.setImageResource(R.drawable.a98);
        }


        if (previousPokemonDexNumber1 == 99) {
            img1.setImageResource(R.drawable.a99);
        }

    }

    public void setFirstPics2() {
        ImageView img1 = (ImageView) findViewById(R.id.gifp2);

        if (previousPokemonDexNumber2 == 1) {
            img1.setImageResource(R.drawable.a1);
        }

        if (previousPokemonDexNumber2 == 2) {
            img1.setImageResource(R.drawable.a2);
        }


        if (previousPokemonDexNumber2 == 3) {
            img1.setImageResource(R.drawable.a3);
        }


        if (previousPokemonDexNumber2 == 4) {
            img1.setImageResource(R.drawable.a4);
        }

        if (previousPokemonDexNumber2 == 5) {
            img1.setImageResource(R.drawable.a5);
        }


        if (previousPokemonDexNumber2 == 6) {
            img1.setImageResource(R.drawable.a6);
        }


        if (previousPokemonDexNumber2 == 7) {
            img1.setImageResource(R.drawable.a7);
        }

        if (previousPokemonDexNumber2 == 8) {
            img1.setImageResource(R.drawable.a8);
        }


        if (previousPokemonDexNumber2 == 9) {
            img1.setImageResource(R.drawable.a9);
        }


        if (previousPokemonDexNumber2 == 10) {
            img1.setImageResource(R.drawable.a10);
        }

        if (previousPokemonDexNumber2 == 11) {
            img1.setImageResource(R.drawable.a11);
        }


        if (previousPokemonDexNumber2 == 12) {
            img1.setImageResource(R.drawable.a12);
        }


        if (previousPokemonDexNumber2 == 13) {
            img1.setImageResource(R.drawable.a13);
        }


        if (previousPokemonDexNumber2 == 14) {
            img1.setImageResource(R.drawable.a14);
        }


        if (previousPokemonDexNumber2 == 15) {
            img1.setImageResource(R.drawable.a15);
        }


        if (previousPokemonDexNumber2 == 16) {
            img1.setImageResource(R.drawable.a16);
        }


        if (previousPokemonDexNumber2 == 17) {
            img1.setImageResource(R.drawable.a17);
        }


        if (previousPokemonDexNumber2 == 18) {
            img1.setImageResource(R.drawable.a18);
        }


        if (previousPokemonDexNumber2 == 19) {
            img1.setImageResource(R.drawable.a19);
        }


        if (previousPokemonDexNumber2 == 20) {
            img1.setImageResource(R.drawable.a20);
        }


        if (previousPokemonDexNumber2 == 21) {
            img1.setImageResource(R.drawable.a21);
        }


        if (previousPokemonDexNumber2 == 22) {
            img1.setImageResource(R.drawable.a22);
        }


        if (previousPokemonDexNumber2 == 23) {
            img1.setImageResource(R.drawable.a23);
        }


        if (previousPokemonDexNumber2 == 24) {
            img1.setImageResource(R.drawable.a24);
        }

        if (previousPokemonDexNumber2 == 25) {
            img1.setImageResource(R.drawable.a25);
        }


        if (previousPokemonDexNumber2 == 26) {
            img1.setImageResource(R.drawable.a26);
        }


        if (previousPokemonDexNumber2 == 27) {
            img1.setImageResource(R.drawable.a27);
        }


        if (previousPokemonDexNumber2 == 28) {
            img1.setImageResource(R.drawable.a28);
        }


        if (previousPokemonDexNumber2 == 29) {
            img1.setImageResource(R.drawable.a29);
        }

        if (previousPokemonDexNumber2 == 30) {
            img1.setImageResource(R.drawable.a30);
        }


        if (previousPokemonDexNumber2 == 31) {
            img1.setImageResource(R.drawable.a31);
        }

        if (previousPokemonDexNumber2 == 32) {
            img1.setImageResource(R.drawable.a32);
        }


        if (previousPokemonDexNumber2 == 33) {
            img1.setImageResource(R.drawable.a33);
        }


        if (previousPokemonDexNumber2 == 34) {
            img1.setImageResource(R.drawable.a34);
        }


        if (previousPokemonDexNumber2 == 35) {
            img1.setImageResource(R.drawable.a35);
        }


        if (previousPokemonDexNumber2 == 36) {
            img1.setImageResource(R.drawable.a36);
        }

        if (previousPokemonDexNumber2 == 37) {
            img1.setImageResource(R.drawable.a37);
        }


        if (previousPokemonDexNumber2 == 38) {
            img1.setImageResource(R.drawable.a38);
        }

        if (previousPokemonDexNumber2 == 39) {
            img1.setImageResource(R.drawable.a39);
        }


        if (previousPokemonDexNumber2 == 40) {
            img1.setImageResource(R.drawable.a40);
        }


        if (previousPokemonDexNumber2 == 41) {
            img1.setImageResource(R.drawable.a41);
        }

        if (previousPokemonDexNumber2 == 42) {
            img1.setImageResource(R.drawable.a42);
        }


        if (previousPokemonDexNumber2 == 43) {
            img1.setImageResource(R.drawable.a43);
        }


        if (previousPokemonDexNumber2 == 44) {
            img1.setImageResource(R.drawable.a44);
        }

        if (previousPokemonDexNumber2 == 45) {
            img1.setImageResource(R.drawable.a45);
        }


        if (previousPokemonDexNumber2 == 46) {
            img1.setImageResource(R.drawable.a46);
        }


        if (previousPokemonDexNumber2 == 47) {
            img1.setImageResource(R.drawable.a47);
        }


        if (previousPokemonDexNumber2 == 48) {
            img1.setImageResource(R.drawable.a48);
        }


        if (previousPokemonDexNumber2 == 49) {
            img1.setImageResource(R.drawable.a49);
        }


        if (previousPokemonDexNumber2 == 50) {
            img1.setImageResource(R.drawable.a50);
        }

        if (previousPokemonDexNumber2 == 51) {
            img1.setImageResource(R.drawable.a51);
        }

        if (previousPokemonDexNumber2 == 52) {
            img1.setImageResource(R.drawable.a52);
        }

        if (previousPokemonDexNumber2 == 53) {
            img1.setImageResource(R.drawable.a53);
        }


        if (previousPokemonDexNumber2 == 54) {
            img1.setImageResource(R.drawable.a54);
        }


        if (previousPokemonDexNumber2 == 55) {
            img1.setImageResource(R.drawable.a55);
        }


        if (previousPokemonDexNumber2 == 56) {
            img1.setImageResource(R.drawable.a56);
        }


        if (previousPokemonDexNumber2 == 57) {
            img1.setImageResource(R.drawable.a57);
        }


        if (previousPokemonDexNumber2 == 58) {
            img1.setImageResource(R.drawable.a5);
        }

        if (previousPokemonDexNumber2 == 59) {
            img1.setImageResource(R.drawable.a59);
        }


        if (previousPokemonDexNumber2 == 60) {
            img1.setImageResource(R.drawable.a60);
        }

        if (previousPokemonDexNumber2 == 61) {
            img1.setImageResource(R.drawable.a61);
        }


        if (previousPokemonDexNumber2 == 62) {
            img1.setImageResource(R.drawable.a62);
        }


        if (previousPokemonDexNumber2 == 63) {
            img1.setImageResource(R.drawable.a63);
        }


        if (previousPokemonDexNumber2 == 64) {
            img1.setImageResource(R.drawable.a64);
        }

        if (previousPokemonDexNumber2 == 65) {
            img1.setImageResource(R.drawable.a65);
        }

        if (previousPokemonDexNumber2 == 66) {
            img1.setImageResource(R.drawable.a66);
        }

        if (previousPokemonDexNumber2 == 67) {
            img1.setImageResource(R.drawable.a67);
        }


        if (previousPokemonDexNumber2 == 68) {
            img1.setImageResource(R.drawable.a68);
        }

        if (previousPokemonDexNumber2 == 69) {
            img1.setImageResource(R.drawable.a69);
        }


        if (previousPokemonDexNumber2 == 70) {
            img1.setImageResource(R.drawable.a70);
        }

        if (previousPokemonDexNumber2 == 71) {
            img1.setImageResource(R.drawable.a71);
        }


        if (previousPokemonDexNumber2 == 72) {
            img1.setImageResource(R.drawable.a72);
        }


        if (previousPokemonDexNumber2 == 73) {
            img1.setImageResource(R.drawable.a73);
        }


        if (previousPokemonDexNumber2 == 74) {
            img1.setImageResource(R.drawable.a74);
        }

        if (previousPokemonDexNumber2 == 75) {
            img1.setImageResource(R.drawable.a75);
        }


        if (previousPokemonDexNumber2 == 76) {
            img1.setImageResource(R.drawable.a76);
        }


        if (previousPokemonDexNumber2 == 77) {
            img1.setImageResource(R.drawable.a77);
        }


        if (previousPokemonDexNumber2 == 78) {
            img1.setImageResource(R.drawable.a78);
        }


        if (previousPokemonDexNumber2 == 79) {
            img1.setImageResource(R.drawable.a79);
        }


        if (previousPokemonDexNumber2 == 80) {
            img1.setImageResource(R.drawable.a80);
        }


        if (previousPokemonDexNumber2 == 81) {
            img1.setImageResource(R.drawable.a81);
        }


        if (previousPokemonDexNumber2 == 82) {
            img1.setImageResource(R.drawable.a82);
        }


        if (previousPokemonDexNumber2 == 83) {
            img1.setImageResource(R.drawable.a83);
        }


        if (previousPokemonDexNumber2 == 84) {
            img1.setImageResource(R.drawable.a84);
        }


        if (previousPokemonDexNumber2 == 85) {
            img1.setImageResource(R.drawable.a85);
        }


        if (previousPokemonDexNumber2 == 86) {
            img1.setImageResource(R.drawable.a86);
        }

        if (previousPokemonDexNumber2 == 87) {
            img1.setImageResource(R.drawable.a87);
        }


        if (previousPokemonDexNumber2 == 88) {
            img1.setImageResource(R.drawable.a88);
        }


        if (previousPokemonDexNumber2 == 89) {
            img1.setImageResource(R.drawable.a89);
        }


        if (previousPokemonDexNumber2 == 90) {
            img1.setImageResource(R.drawable.a90);
        }

        if (previousPokemonDexNumber2 == 91) {
            img1.setImageResource(R.drawable.a91);
        }


        if (previousPokemonDexNumber2 == 92) {
            img1.setImageResource(R.drawable.a92);
        }


        if (previousPokemonDexNumber2 == 93) {
            img1.setImageResource(R.drawable.a93);
        }


        if (previousPokemonDexNumber2 == 94) {
            img1.setImageResource(R.drawable.a94);
        }


        if (previousPokemonDexNumber2 == 95) {
            img1.setImageResource(R.drawable.a95);
        }


        if (previousPokemonDexNumber2 == 96) {
            img1.setImageResource(R.drawable.a96);
        }


        if (previousPokemonDexNumber2 == 97) {
            img1.setImageResource(R.drawable.a97);
        }


        if (previousPokemonDexNumber2 == 98) {
            img1.setImageResource(R.drawable.a98);
        }


        if (previousPokemonDexNumber2 == 99) {
            img1.setImageResource(R.drawable.a99);
        }

    }

    public void setActualPics1() {
        ImageView img1 = (ImageView) findViewById(R.id.gifp1);

        if (activePokemon1.dexNumber == 1) {
            img1.setImageResource(R.drawable.a1);
        }

        if (activePokemon1.dexNumber == 2) {
            img1.setImageResource(R.drawable.a2);
        }


        if (activePokemon1.dexNumber == 3) {
            img1.setImageResource(R.drawable.a3);
        }


        if (activePokemon1.dexNumber == 4) {
            img1.setImageResource(R.drawable.a4);
        }

        if (activePokemon1.dexNumber == 5) {
            img1.setImageResource(R.drawable.a5);
        }


        if (activePokemon1.dexNumber == 6) {
            img1.setImageResource(R.drawable.a6);
        }


        if (activePokemon1.dexNumber == 7) {
            img1.setImageResource(R.drawable.a7);
        }

        if (activePokemon1.dexNumber == 8) {
            img1.setImageResource(R.drawable.a8);
        }


        if (activePokemon1.dexNumber == 9) {
            img1.setImageResource(R.drawable.a9);
        }


        if (activePokemon1.dexNumber == 10) {
            img1.setImageResource(R.drawable.a10);
        }

        if (activePokemon1.dexNumber == 11) {
            img1.setImageResource(R.drawable.a11);
        }


        if (activePokemon1.dexNumber == 12) {
            img1.setImageResource(R.drawable.a12);
        }


        if (activePokemon1.dexNumber == 13) {
            img1.setImageResource(R.drawable.a13);
        }


        if (activePokemon1.dexNumber == 14) {
            img1.setImageResource(R.drawable.a14);
        }


        if (activePokemon1.dexNumber == 15) {
            img1.setImageResource(R.drawable.a15);
        }


        if (activePokemon1.dexNumber == 16) {
            img1.setImageResource(R.drawable.a16);
        }


        if (activePokemon1.dexNumber == 17) {
            img1.setImageResource(R.drawable.a17);
        }


        if (activePokemon1.dexNumber == 18) {
            img1.setImageResource(R.drawable.a18);
        }


        if (activePokemon1.dexNumber == 19) {
            img1.setImageResource(R.drawable.a19);
        }


        if (activePokemon1.dexNumber == 20) {
            img1.setImageResource(R.drawable.a20);
        }


        if (activePokemon1.dexNumber == 21) {
            img1.setImageResource(R.drawable.a21);
        }


        if (activePokemon1.dexNumber == 22) {
            img1.setImageResource(R.drawable.a22);
        }


        if (activePokemon1.dexNumber == 23) {
            img1.setImageResource(R.drawable.a23);
        }


        if (activePokemon1.dexNumber == 24) {
            img1.setImageResource(R.drawable.a24);
        }

        if (activePokemon1.dexNumber == 25) {
            img1.setImageResource(R.drawable.a25);
        }


        if (activePokemon1.dexNumber == 26) {
            img1.setImageResource(R.drawable.a26);
        }


        if (activePokemon1.dexNumber == 27) {
            img1.setImageResource(R.drawable.a27);
        }


        if (activePokemon1.dexNumber == 28) {
            img1.setImageResource(R.drawable.a28);
        }


        if (activePokemon1.dexNumber == 29) {
            img1.setImageResource(R.drawable.a29);
        }

        if (activePokemon1.dexNumber == 30) {
            img1.setImageResource(R.drawable.a30);
        }


        if (activePokemon1.dexNumber == 31) {
            img1.setImageResource(R.drawable.a31);
        }

        if (activePokemon1.dexNumber == 32) {
            img1.setImageResource(R.drawable.a32);
        }


        if (activePokemon1.dexNumber == 33) {
            img1.setImageResource(R.drawable.a33);
        }


        if (activePokemon1.dexNumber == 34) {
            img1.setImageResource(R.drawable.a34);
        }


        if (activePokemon1.dexNumber == 35) {
            img1.setImageResource(R.drawable.a35);
        }


        if (activePokemon1.dexNumber == 36) {
            img1.setImageResource(R.drawable.a36);
        }

        if (activePokemon1.dexNumber == 37) {
            img1.setImageResource(R.drawable.a37);
        }


        if (activePokemon1.dexNumber == 38) {
            img1.setImageResource(R.drawable.a38);
        }

        if (activePokemon1.dexNumber == 39) {
            img1.setImageResource(R.drawable.a39);
        }


        if (activePokemon1.dexNumber == 40) {
            img1.setImageResource(R.drawable.a40);
        }


        if (activePokemon1.dexNumber == 41) {
            img1.setImageResource(R.drawable.a41);
        }

        if (activePokemon1.dexNumber == 42) {
            img1.setImageResource(R.drawable.a42);
        }


        if (activePokemon1.dexNumber == 43) {
            img1.setImageResource(R.drawable.a43);
        }


        if (activePokemon1.dexNumber == 44) {
            img1.setImageResource(R.drawable.a44);
        }

        if (activePokemon1.dexNumber == 45) {
            img1.setImageResource(R.drawable.a45);
        }


        if (activePokemon1.dexNumber == 46) {
            img1.setImageResource(R.drawable.a46);
        }


        if (activePokemon1.dexNumber == 47) {
            img1.setImageResource(R.drawable.a47);
        }


        if (activePokemon1.dexNumber == 48) {
            img1.setImageResource(R.drawable.a48);
        }


        if (activePokemon1.dexNumber == 49) {
            img1.setImageResource(R.drawable.a49);
        }


        if (activePokemon1.dexNumber == 50) {
            img1.setImageResource(R.drawable.a50);
        }

        if (activePokemon1.dexNumber == 51) {
            img1.setImageResource(R.drawable.a51);
        }

        if (activePokemon1.dexNumber == 52) {
            img1.setImageResource(R.drawable.a52);
        }

        if (activePokemon1.dexNumber == 53) {
            img1.setImageResource(R.drawable.a53);
        }


        if (activePokemon1.dexNumber == 54) {
            img1.setImageResource(R.drawable.a54);
        }


        if (activePokemon1.dexNumber == 55) {
            img1.setImageResource(R.drawable.a55);
        }


        if (activePokemon1.dexNumber == 56) {
            img1.setImageResource(R.drawable.a56);
        }


        if (activePokemon1.dexNumber == 57) {
            img1.setImageResource(R.drawable.a57);
        }


        if (activePokemon1.dexNumber == 58) {
            img1.setImageResource(R.drawable.a5);
        }

        if (activePokemon1.dexNumber == 59) {
            img1.setImageResource(R.drawable.a59);
        }


        if (activePokemon1.dexNumber == 60) {
            img1.setImageResource(R.drawable.a60);
        }

        if (activePokemon1.dexNumber == 61) {
            img1.setImageResource(R.drawable.a61);
        }


        if (activePokemon1.dexNumber == 62) {
            img1.setImageResource(R.drawable.a62);
        }


        if (activePokemon1.dexNumber == 63) {
            img1.setImageResource(R.drawable.a63);
        }


        if (activePokemon1.dexNumber == 64) {
            img1.setImageResource(R.drawable.a64);
        }

        if (activePokemon1.dexNumber == 65) {
            img1.setImageResource(R.drawable.a65);
        }

        if (activePokemon1.dexNumber == 66) {
            img1.setImageResource(R.drawable.a66);
        }

        if (activePokemon1.dexNumber == 67) {
            img1.setImageResource(R.drawable.a67);
        }


        if (activePokemon1.dexNumber == 68) {
            img1.setImageResource(R.drawable.a68);
        }

        if (activePokemon1.dexNumber == 69) {
            img1.setImageResource(R.drawable.a69);
        }


        if (activePokemon1.dexNumber == 70) {
            img1.setImageResource(R.drawable.a70);
        }

        if (activePokemon1.dexNumber == 71) {
            img1.setImageResource(R.drawable.a71);
        }


        if (activePokemon1.dexNumber == 72) {
            img1.setImageResource(R.drawable.a72);
        }


        if (activePokemon1.dexNumber == 73) {
            img1.setImageResource(R.drawable.a73);
        }


        if (activePokemon1.dexNumber == 74) {
            img1.setImageResource(R.drawable.a74);
        }

        if (activePokemon1.dexNumber == 75) {
            img1.setImageResource(R.drawable.a75);
        }


        if (activePokemon1.dexNumber == 76) {
            img1.setImageResource(R.drawable.a76);
        }


        if (activePokemon1.dexNumber == 77) {
            img1.setImageResource(R.drawable.a77);
        }


        if (activePokemon1.dexNumber == 78) {
            img1.setImageResource(R.drawable.a78);
        }


        if (activePokemon1.dexNumber == 79) {
            img1.setImageResource(R.drawable.a79);
        }


        if (activePokemon1.dexNumber == 80) {
            img1.setImageResource(R.drawable.a80);
        }


        if (activePokemon1.dexNumber == 81) {
            img1.setImageResource(R.drawable.a81);
        }


        if (activePokemon1.dexNumber == 82) {
            img1.setImageResource(R.drawable.a82);
        }


        if (activePokemon1.dexNumber == 83) {
            img1.setImageResource(R.drawable.a83);
        }


        if (activePokemon1.dexNumber == 84) {
            img1.setImageResource(R.drawable.a84);
        }


        if (activePokemon1.dexNumber == 85) {
            img1.setImageResource(R.drawable.a85);
        }


        if (activePokemon1.dexNumber == 86) {
            img1.setImageResource(R.drawable.a86);
        }

        if (activePokemon1.dexNumber == 87) {
            img1.setImageResource(R.drawable.a87);
        }


        if (activePokemon1.dexNumber == 88) {
            img1.setImageResource(R.drawable.a88);
        }


        if (activePokemon1.dexNumber == 89) {
            img1.setImageResource(R.drawable.a89);
        }


        if (activePokemon1.dexNumber == 90) {
            img1.setImageResource(R.drawable.a90);
        }

        if (activePokemon1.dexNumber == 91) {
            img1.setImageResource(R.drawable.a91);
        }


        if (activePokemon1.dexNumber == 92) {
            img1.setImageResource(R.drawable.a92);
        }


        if (activePokemon1.dexNumber == 93) {
            img1.setImageResource(R.drawable.a93);
        }


        if (activePokemon1.dexNumber == 94) {
            img1.setImageResource(R.drawable.a94);
        }


        if (activePokemon1.dexNumber == 95) {
            img1.setImageResource(R.drawable.a95);
        }


        if (activePokemon1.dexNumber == 96) {
            img1.setImageResource(R.drawable.a96);
        }


        if (activePokemon1.dexNumber == 97) {
            img1.setImageResource(R.drawable.a97);
        }


        if (activePokemon1.dexNumber == 98) {
            img1.setImageResource(R.drawable.a98);
        }


        if (activePokemon1.dexNumber == 99) {
            img1.setImageResource(R.drawable.a99);
        }

    }

    public void setActualPics2() {
        ImageView img2 = (ImageView) findViewById(R.id.gifp2);

        if (activePokemon2.dexNumber == 1) {
            img2.setImageResource(R.drawable.a1);
        }

        if (activePokemon2.dexNumber == 2) {
            img2.setImageResource(R.drawable.a2);
        }


        if (activePokemon2.dexNumber == 3) {
            img2.setImageResource(R.drawable.a3);
        }


        if (activePokemon2.dexNumber == 4) {
            img2.setImageResource(R.drawable.a4);
        }

        if (activePokemon2.dexNumber == 5) {
            img2.setImageResource(R.drawable.a5);
        }


        if (activePokemon2.dexNumber == 6) {
            img2.setImageResource(R.drawable.a6);
        }


        if (activePokemon2.dexNumber == 7) {
            img2.setImageResource(R.drawable.a7);
        }

        if (activePokemon2.dexNumber == 8) {
            img2.setImageResource(R.drawable.a8);
        }


        if (activePokemon2.dexNumber == 9) {
            img2.setImageResource(R.drawable.a9);
        }


        if (activePokemon2.dexNumber == 10) {
            img2.setImageResource(R.drawable.a10);
        }

        if (activePokemon2.dexNumber == 11) {
            img2.setImageResource(R.drawable.a11);
        }


        if (activePokemon2.dexNumber == 12) {
            img2.setImageResource(R.drawable.a12);
        }


        if (activePokemon2.dexNumber == 13) {
            img2.setImageResource(R.drawable.a13);
        }


        if (activePokemon2.dexNumber == 14) {
            img2.setImageResource(R.drawable.a14);
        }


        if (activePokemon2.dexNumber == 15) {
            img2.setImageResource(R.drawable.a15);
        }


        if (activePokemon2.dexNumber == 16) {
            img2.setImageResource(R.drawable.a16);
        }


        if (activePokemon2.dexNumber == 17) {
            img2.setImageResource(R.drawable.a17);
        }


        if (activePokemon2.dexNumber == 18) {
            img2.setImageResource(R.drawable.a18);
        }


        if (activePokemon2.dexNumber == 19) {
            img2.setImageResource(R.drawable.a19);
        }


        if (activePokemon2.dexNumber == 20) {
            img2.setImageResource(R.drawable.a20);
        }


        if (activePokemon2.dexNumber == 21) {
            img2.setImageResource(R.drawable.a21);
        }


        if (activePokemon2.dexNumber == 22) {
            img2.setImageResource(R.drawable.a22);
        }


        if (activePokemon2.dexNumber == 23) {
            img2.setImageResource(R.drawable.a23);
        }


        if (activePokemon2.dexNumber == 24) {
            img2.setImageResource(R.drawable.a24);
        }

        if (activePokemon2.dexNumber == 25) {
            img2.setImageResource(R.drawable.a25);
        }


        if (activePokemon2.dexNumber == 26) {
            img2.setImageResource(R.drawable.a26);
        }


        if (activePokemon2.dexNumber == 27) {
            img2.setImageResource(R.drawable.a27);
        }


        if (activePokemon2.dexNumber == 28) {
            img2.setImageResource(R.drawable.a28);
        }


        if (activePokemon2.dexNumber == 29) {
            img2.setImageResource(R.drawable.a29);
        }

        if (activePokemon2.dexNumber == 30) {
            img2.setImageResource(R.drawable.a30);
        }


        if (activePokemon2.dexNumber == 31) {
            img2.setImageResource(R.drawable.a31);
        }

        if (activePokemon2.dexNumber == 32) {
            img2.setImageResource(R.drawable.a32);
        }


        if (activePokemon2.dexNumber == 33) {
            img2.setImageResource(R.drawable.a33);
        }


        if (activePokemon2.dexNumber == 34) {
            img2.setImageResource(R.drawable.a34);
        }


        if (activePokemon2.dexNumber == 35) {
            img2.setImageResource(R.drawable.a35);
        }


        if (activePokemon2.dexNumber == 36) {
            img2.setImageResource(R.drawable.a36);
        }

        if (activePokemon2.dexNumber == 37) {
            img2.setImageResource(R.drawable.a37);
        }


        if (activePokemon2.dexNumber == 38) {
            img2.setImageResource(R.drawable.a38);
        }

        if (activePokemon2.dexNumber == 39) {
            img2.setImageResource(R.drawable.a39);
        }


        if (activePokemon2.dexNumber == 40) {
            img2.setImageResource(R.drawable.a40);
        }


        if (activePokemon2.dexNumber == 41) {
            img2.setImageResource(R.drawable.a41);
        }

        if (activePokemon2.dexNumber == 42) {
            img2.setImageResource(R.drawable.a42);
        }


        if (activePokemon2.dexNumber == 43) {
            img2.setImageResource(R.drawable.a43);
        }


        if (activePokemon2.dexNumber == 44) {
            img2.setImageResource(R.drawable.a44);
        }

        if (activePokemon2.dexNumber == 45) {
            img2.setImageResource(R.drawable.a45);
        }


        if (activePokemon2.dexNumber == 46) {
            img2.setImageResource(R.drawable.a46);
        }


        if (activePokemon2.dexNumber == 47) {
            img2.setImageResource(R.drawable.a47);
        }


        if (activePokemon2.dexNumber == 48) {
            img2.setImageResource(R.drawable.a48);
        }


        if (activePokemon2.dexNumber == 49) {
            img2.setImageResource(R.drawable.a49);
        }


        if (activePokemon2.dexNumber == 50) {
            img2.setImageResource(R.drawable.a50);
        }

        if (activePokemon2.dexNumber == 51) {
            img2.setImageResource(R.drawable.a51);
        }

        if (activePokemon2.dexNumber == 52) {
            img2.setImageResource(R.drawable.a52);
        }

        if (activePokemon2.dexNumber == 53) {
            img2.setImageResource(R.drawable.a53);
        }


        if (activePokemon2.dexNumber == 54) {
            img2.setImageResource(R.drawable.a54);
        }


        if (activePokemon2.dexNumber == 55) {
            img2.setImageResource(R.drawable.a55);
        }


        if (activePokemon2.dexNumber == 56) {
            img2.setImageResource(R.drawable.a56);
        }


        if (activePokemon2.dexNumber == 57) {
            img2.setImageResource(R.drawable.a57);
        }


        if (activePokemon2.dexNumber == 58) {
            img2.setImageResource(R.drawable.a5);
        }

        if (activePokemon2.dexNumber == 59) {
            img2.setImageResource(R.drawable.a59);
        }


        if (activePokemon2.dexNumber == 60) {
            img2.setImageResource(R.drawable.a60);
        }

        if (activePokemon2.dexNumber == 61) {
            img2.setImageResource(R.drawable.a61);
        }


        if (activePokemon2.dexNumber == 62) {
            img2.setImageResource(R.drawable.a62);
        }


        if (activePokemon2.dexNumber == 63) {
            img2.setImageResource(R.drawable.a63);
        }


        if (activePokemon2.dexNumber == 64) {
            img2.setImageResource(R.drawable.a64);
        }

        if (activePokemon2.dexNumber == 65) {
            img2.setImageResource(R.drawable.a65);
        }

        if (activePokemon2.dexNumber == 66) {
            img2.setImageResource(R.drawable.a66);
        }

        if (activePokemon2.dexNumber == 67) {
            img2.setImageResource(R.drawable.a67);
        }


        if (activePokemon2.dexNumber == 68) {
            img2.setImageResource(R.drawable.a68);
        }

        if (activePokemon2.dexNumber == 69) {
            img2.setImageResource(R.drawable.a69);
        }


        if (activePokemon2.dexNumber == 70) {
            img2.setImageResource(R.drawable.a70);
        }

        if (activePokemon2.dexNumber == 71) {
            img2.setImageResource(R.drawable.a71);
        }


        if (activePokemon2.dexNumber == 72) {
            img2.setImageResource(R.drawable.a72);
        }


        if (activePokemon2.dexNumber == 73) {
            img2.setImageResource(R.drawable.a73);
        }


        if (activePokemon2.dexNumber == 74) {
            img2.setImageResource(R.drawable.a74);
        }

        if (activePokemon2.dexNumber == 75) {
            img2.setImageResource(R.drawable.a75);
        }


        if (activePokemon2.dexNumber == 76) {
            img2.setImageResource(R.drawable.a76);
        }


        if (activePokemon2.dexNumber == 77) {
            img2.setImageResource(R.drawable.a77);
        }


        if (activePokemon2.dexNumber == 78) {
            img2.setImageResource(R.drawable.a78);
        }


        if (activePokemon2.dexNumber == 79) {
            img2.setImageResource(R.drawable.a79);
        }


        if (activePokemon2.dexNumber == 80) {
            img2.setImageResource(R.drawable.a80);
        }


        if (activePokemon2.dexNumber == 81) {
            img2.setImageResource(R.drawable.a81);
        }


        if (activePokemon2.dexNumber == 82) {
            img2.setImageResource(R.drawable.a82);
        }


        if (activePokemon2.dexNumber == 83) {
            img2.setImageResource(R.drawable.a83);
        }


        if (activePokemon2.dexNumber == 84) {
            img2.setImageResource(R.drawable.a84);
        }


        if (activePokemon2.dexNumber == 85) {
            img2.setImageResource(R.drawable.a85);
        }


        if (activePokemon2.dexNumber == 86) {
            img2.setImageResource(R.drawable.a86);
        }

        if (activePokemon2.dexNumber == 87) {
            img2.setImageResource(R.drawable.a87);
        }


        if (activePokemon2.dexNumber == 88) {
            img2.setImageResource(R.drawable.a88);
        }


        if (activePokemon2.dexNumber == 89) {
            img2.setImageResource(R.drawable.a89);
        }


        if (activePokemon2.dexNumber == 90) {
            img2.setImageResource(R.drawable.a90);
        }

        if (activePokemon2.dexNumber == 91) {
            img2.setImageResource(R.drawable.a91);
        }


        if (activePokemon2.dexNumber == 92) {
            img2.setImageResource(R.drawable.a92);
        }


        if (activePokemon2.dexNumber == 93) {
            img2.setImageResource(R.drawable.a93);
        }


        if (activePokemon2.dexNumber == 94) {
            img2.setImageResource(R.drawable.a94);
        }


        if (activePokemon2.dexNumber == 95) {
            img2.setImageResource(R.drawable.a95);
        }


        if (activePokemon2.dexNumber == 96) {
            img2.setImageResource(R.drawable.a96);
        }


        if (activePokemon2.dexNumber == 97) {
            img2.setImageResource(R.drawable.a97);
        }


        if (activePokemon2.dexNumber == 98) {
            img2.setImageResource(R.drawable.a98);
        }


        if (activePokemon2.dexNumber == 99) {
            img2.setImageResource(R.drawable.a99);
        }
    }

    public void proceed(View view) {

        stayingInApp=true;
        boolean alive1 = false;
        boolean alive2 = false;
        for (int i = 0; i < 6; i++) {
            if (!p1Pokemon[i].fainted) {
                alive1 = true;
            }
            if (!p2Pokemon[i].fainted) {
                alive2 = true;
            }
        }
        if (!alive1)
        {
            winner = 2;
            Intent intent = new Intent(this, winActivity.class);
            startActivity(intent);
        }
        else if (!alive2)
        {
            winner = 1;
            Intent intent = new Intent(this, winActivity.class);
            startActivity(intent);
        }
        else if (activePokemon1.fainted)
        {
            if(playingOnline)
            {
                Intent intent = new Intent(this, Switch1ActivityOnline.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(this, switch1Activity.class);
                startActivity(intent);
            }

        }
        else if (activePokemon2.fainted)
        {
            if(playingOnline)
            {
                Intent intent = new Intent(this, WaitForSwitchInActivityOnline.class);
                startActivity(intent);
            }
            else if (isP2CPU)
            {
                CPUSwitch();
                Intent intent = new Intent(this, Battle1Activity.class);
                startActivity(intent);

            }
            else
            {
                Intent intent = new Intent(this, switch2Activity.class);
                startActivity(intent);
            }

        }
        else
        {
            if(playingOnline)
            {
                Intent intent = new Intent(this, WaitForSwitchInActivityOnline.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(this, Battle1Activity.class);
                startActivity(intent);
            }
        }


    }

    private void startCountAnimation(final TextView t, int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                t.setText("Health: " + animation.getAnimatedValue().toString() + "%");
            }
        });
        animator.start();
    }

    private void translatorAnimation(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setX((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    public static String getTypeColor(int type) {
        switch (type) {
            case 1:
                return "#e0e0e0";
            case 2:
                return "#e53935";
            case 3:
                return "#3f51b5";
            case 4:
                return "#fdd835";
            case 5:
                return "#43a047";
            case 6:
                return "#81d4fa";
            case 7:
                return "#d50000";
            case 8:
                return "#9c27b0";
            case 9:
                return "#795548";
            case 10:
                return "#F3E5F5";
            case 11:
                return "#f48fb1";
            case 12:
                return "#64dd17";
            case 13:
                return "#5d4037";
            case 14:
                return "#4a148c";
            case 15:
                return "#311b92";
            case 16:
                return "#212121";
            case 17:
                return "#b0bec5";
            case 18:
                return "#ff1744";
        }

        return "";
    }

    private void p1LowerHealthAnimation() {
        final int goTil = (int) Math.round(Double.parseDouble(activePokemon1.hpPercent()));
        final ProgressBar p1 = (ProgressBar) findViewById(R.id.healthBar1);
        final TextView h1 = (TextView) findViewById(R.id.health1);
        //decrease health bar accordingly
        ProgressBarAnimation anim1 = new ProgressBarAnimation(p1, start1, goTil);
        p1.startAnimation(anim1);

        //decrease health textview accordingly
        startCountAnimation(h1, start1, goTil);
    }

    private void p2LowerHealthAnimation() {
        final int goTil2 = (int) Math.round(Double.parseDouble(activePokemon2.hpPercent()));
        final TextView h2 = (TextView) findViewById(R.id.health2);
        final ProgressBar p2 = (ProgressBar) findViewById(R.id.healthBar2);

        //decrease health bar accordingly
        ProgressBarAnimation anim2 = new ProgressBarAnimation(p2, start2, goTil2);
        p2.startAnimation(anim2);

        //decrease health textview accordingly
        startCountAnimation(h2, start2, goTil2);
    }

    private void p1StatusedMessage() {
        final TextView message = (TextView) findViewById(R.id.chat);
        message.append(" " + activePokemon1.name + activePokemon1.statusMessage(activePokemon1.statusMethod()));
    }

    private void p2StatusedMessage() {
        final TextView message = (TextView) findViewById(R.id.chat);
        message.append(" " + activePokemon2.name + activePokemon2.statusMessage(activePokemon2.statusMethod()));
    }

    private void p1FaintedMessage() {
        final TextView message = (TextView) findViewById(R.id.chat);
        message.append(fainted1);
        playBringOutNewPokemon();
    }

    private void p2FaintedMessage() {
        final TextView message = (TextView) findViewById(R.id.chat);
        message.append(fainted2);
        playBringOutNewPokemon();
    }

    private void p1MoveMessage() {
        final TextView message = (TextView) findViewById(R.id.chat);
        message.append((moveUse1));
    }

    private void p2MoveMessage() {
        final TextView message = (TextView) findViewById(R.id.chat);
        message.append((moveUse2));
    }

    private void p1EffectivenessMessage() {
        final TextView message = (TextView) findViewById(R.id.chat);
        double num = 1;
        num*=(Globals.getEffectiveness(usedMove1, activePokemon2, 1) * Globals.getEffectiveness(usedMove1, activePokemon2, 2));
        double a =num;
        if (num < .6) {
            message.append(" It wasn't very effective.");
            playIneffectiveSound();
        } else if (num > 1.5) {
            message.append(" It was super effective!");
            playBrutalSound();
        }

    }

    private void p2EffectivenessMessage() {
        final TextView message = (TextView) findViewById(R.id.chat);
        double num = (Globals.getEffectiveness(usedMove2, activePokemon1, 1) * Globals.getEffectiveness(usedMove2, activePokemon1, 2));
        double a =num;
        if (num < .6) {
            message.append(" It wasn't very effective.");
            playIneffectiveSound();
        } else if (num > 1.5) {
            message.append(" It was super effective!");
            playBrutalSound();
        }
    }

    private void p1StatusDamage() {
        final TextView message = (TextView) findViewById(R.id.chat);
        switch (activePokemon1.statusMethod()) {
            case "burn":
                message.append(activePokemon1.name + " lost health due to its burn!");
                break;
            case "poison":
                message.append(activePokemon1.name + " lost health due to its poison!");
                break;
            case "badlyPoison":
                message.append(activePokemon1.name + " lost health due to its toxic poisoning!");
                break;
        }
    }

    private void p2StatusDamage() {
        final TextView message = (TextView) findViewById(R.id.chat);
        switch (activePokemon2.statusMethod()) {
            case "burn":
                message.append(activePokemon2.name + " lost health due to its burn!");
                break;
            case "poison":
                message.append(activePokemon2.name + " lost health due to its poison!");
                break;
            case "badlyPoison":
                message.append(activePokemon2.name + " lost health due to its toxic poisoning!");
                break;
        }
    }

    private void withdraw1Message() {
        final TextView message = (TextView) findViewById(R.id.chat);
        message.append("\n" + name1 + " withdrew their " + getPokemonByDexNumber(previousPokemonDexNumber1, 1).name + "!");
    }

    private void withdraw2Message() {
        final TextView message = (TextView) findViewById(R.id.chat);
        message.append("\n" + name2 + " withdrew their " + getPokemonByDexNumber(previousPokemonDexNumber2, 2).name + "!");
    }

    void doSecondAnimationStuff(final int moveUsingPlayer){

         Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView message = (TextView)findViewById(R.id.chat);
                switch (moveUsingPlayer)
                {
                    case 1:
                        //P1 is Using the second move
                        switch ((int) (p1Damage)) {
                            case 0:
                                checkP1Crap();
                                break;
                            default:

                                //P1 is using an Attacking Move
                                p1AttackAnimationStuff();
                                break;
                        }
                        break;
                    case 2:
                        //P2 is Using the second move
                        switch ((int) (p2Damage)) {
                            case 0:
                                checkP2Crap();
                                break;
                            default:
                                //P1 is using an Attacking Move
                                p2AttackAnimationStuff();
                                break;
                        }
                        break;
                }
            }
        }, 3000);


    }
    void doFirstAnimationStuff() {

        TextView message = (TextView)findViewById(R.id.chat);
        switch (absoluteOrderOfMoves) {
            case 1: {
                if (!p1Switch) {
                    //P1 is Using the first move
                    switch ((int) (p1Damage)) {
                        case 0:
                            checkP1Crap();
                            break;
                        default:
                            //P1 is using an Attacking Move
                            p1AttackAnimationStuff();
                            break;
                    }
                }
                animationDone=1;
                break;
            }
            case 2: {
                if (!p2Switch) {
                    //P2 is Using the first move
                    switch ((int) Math.round(p2Damage)) {
                        case 0:
                            checkP2Crap();

                            //P2 is using a status move
                            p2StatusMoveAnimation();
                            break;
                        default:
                            //P2 is using an Attacking Move
                            p2AttackAnimationStuff();
                            break;
                    }
                }
                animationDone=2;
                break;
            }
        }
    }

    void doFirstAnimation()
    {
        final Handler handler = new Handler();

        //wait 3 seconds only if one of them switched
        if(p1Switch || p2Switch)
        {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doFirstAnimationStuff();
                }
            }, 3000);
        }

        ///Do the attack right away because nobody switched
        else
        {
            doFirstAnimationStuff();
        }

    }

    void doSecondAnimation()
    {

        switch (absoluteOrderOfMoves)
        {
            case 1:
                //Do player two's attack if they didn't switch and
                // if p2 went first, and p1 is still alive
                if(!p2Switch && !activePokemon2.fainted)
                {
                    doSecondAnimationStuff(2);
                }
                break;
            case 2:
                //Do player one's attack if they didn't switch and
                // if p2 went first, and p1 is still alive
                if(!p1Switch && !activePokemon1.fainted)
                {
                    doSecondAnimationStuff(1);
                }
                break;
        }
    }

    void animateEverything()
    {
        //Handle all of the switching animations
        switchAnimationStuff();

        //Do animation only if one of them is attacking
        if(!p1Switch || !p2Switch)
        {
            //Handle the first attacking animation (Someone is using a move)
            doFirstAnimation();

            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            if(!host && playingOnline)
            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("It knows to do the animations and here is absStart1").setValue(absoluteStart1);

            //Handle the second attacking animation (Someone is using a move)
            doSecondAnimation();

        }

    }

    private void shrinkOrGrowImageAnimation(final ImageView imageView, int start, int end, int duration) {
        final android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int num = Integer.parseInt(animation.getAnimatedValue().toString());
                layoutParams.width = num;
                layoutParams.height = num;
                imageView.setLayoutParams(layoutParams);
            }
        });
        animator.start();
    }

    private void changeP1HealthBarAndHealthText()
    {
         TextView h1 = (TextView)findViewById(R.id.health1);
         ProgressBar p1 = (ProgressBar)findViewById(R.id.healthBar1);

        h1.setText("Health: " + start1 + "%");
        p1.setProgress(start1);
    }
    private void changeP2HealthBarAndHealthText()
    {
        TextView h2 = (TextView)findViewById(R.id.health2);
        ProgressBar p2 = (ProgressBar)findViewById(R.id.healthBar2);

        h2.setText("Health: " + start2 + "%");
        p2.setProgress(start2);
    }
    private void p1SwitchedInMessage()
    {
        final TextView message = (TextView)findViewById(R.id.chat);
        message.append("\n"+name1 + " switched in " + activePokemon1.name+"!");
        playSwitch();
    }
    private void p2SwitchedInMessage()
    {
        final TextView message = (TextView)findViewById(R.id.chat);
        message.append("\n"+name2 + " switched in " + activePokemon2.name+"!");
    }
    private void critMessage()
    {
        final TextView message = (TextView)findViewById(R.id.chat);
        message.append(" It was a critical hit! ");
    }
    private void p1SwitchAnimationAndMessages()
    {
        final ImageView pic1 = (ImageView)findViewById(R.id.gifp1);

        shrinkOrGrowImageAnimation(pic1, 250, 0, 1500);
        withdraw1Message();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setActualPics1();
                shrinkOrGrowImageAnimation(pic1, 0, 250, 1500);
                p1SwitchedInMessage();
                changeP1HealthBarAndHealthText();
                changeP1HealthBarsForSwitch();
            }
        }, 1500);

    }
    private void p2SwitchAnimationAndMessages()
    {
        final ImageView pic2 = (ImageView)findViewById(R.id.gifp2);

        shrinkOrGrowImageAnimation(pic2, 250, 0, 1500);
        withdraw2Message();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setActualPics2();
                shrinkOrGrowImageAnimation(pic2, 0, 250, 1500);
                p2SwitchedInMessage();
                changeP2HealthBarAndHealthText();
                changeP2HealthBarsForSwitch();
            }
        }, 1500);

    }
    private void switchBothPlayers()
    {
        final Handler handler = new Handler();

        p1SwitchAnimationAndMessages();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                p2SwitchAnimationAndMessages();
            }
        }, 3500);
    }
    void switchAnimationStuff()
    {
        //If Both of them Switch, do p1s first, then after they are finished do p2s
        if(p1Switch && p2Switch)
        {
            switchBothPlayers();
        }

        //Just switch p1
        else if(p1Switch)
        {
            p1SwitchAnimationAndMessages();
        }

        //Just switch p2
        else if(p2Switch)
        {
            p2SwitchAnimationAndMessages();
        }
    }
    public static AbstractBiomon getPokemonByDexNumber(int dexNumber, int player)
    {
        if(player==1)
        {
            for(int i=0;i<6;i++)
            {
                if(p1Pokemon[i].dexNumber == dexNumber)
                {
                    return p1Pokemon[i];
                }
            }
        }
        else
        {
            for(int i=0;i<6;i++)
            {
                if(p2Pokemon[i].dexNumber == dexNumber)
                {
                    return p2Pokemon[i];
                }
            }
        }
        return p1Pokemon[0];
    }
    public void p1AttackAnimationStuff()
    {
        switch (usedMove1.category)
        {
            case 1:
                p1PhysicalAttackAnimation();
                break;
            default:
                p1SpecialAttackAnimation();
                break;
        }
    }
    public void p2AttackAnimationStuff()
    {
        switch (usedMove2.category)
        {
            case 1:
                p2PhysicalAttackAnimation();
                break;
            case 2:
                p2SpecialAttackAnimation();
                break;
        }
    }
    public void p1PhysicalAttackAnimation()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

        }

        final Handler handler = new Handler();
        final ImageView atk = (ImageView)findViewById(R.id.atkAnim1);
        final ProgressBar p2 = (ProgressBar)findViewById(R.id.healthBar2);
        atk.setVisibility(View.VISIBLE);
        chooseAttackPicture1(usedMove1.type);

        translatorAnimation(atk, 300, 800);
        p1MoveMessage();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                atk.setVisibility(View.INVISIBLE);
                int start = start2;
                int end = healthAfterHit2;
                final TextView healthText2= (TextView)findViewById(R.id.health2);

                final ValueAnimator animator = ValueAnimator.ofInt(start, end);
                animator.setDuration(1500);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        healthText2.setText("Health: " + animator.getAnimatedValue() + "%");
                        p2.setProgress((int) animator.getAnimatedValue());
                        changeP2HealthBars();
                    }
                });
                animator.start();
                p1EffectivenessMessage();
                if(p1Critt)
                {
                    critMessage();
                }

                if(activePokemon2.fainted)
                {
                    p2FaintAnimation();
                }
            }
        }, 1500);

    }
    public void p1SpecialAttackAnimation()
    {

        final Handler handler = new Handler();
        final ImageView atk = (ImageView)findViewById(R.id.atkAnim1);
        final ProgressBar p2 = (ProgressBar)findViewById(R.id.healthBar2);
        atk.setVisibility(View.VISIBLE);
        getAtkAnimColor(usedMove1.type);
        translatorAnimation(atk, 300, 800);
        p1MoveMessage();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                atk.setVisibility(View.INVISIBLE);
                int start = start2;
                int end = healthAfterHit2;
                final TextView healthText2= (TextView)findViewById(R.id.health2);


                final ValueAnimator animator = ValueAnimator.ofInt(start, end);
                animator.setDuration(1500);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        healthText2.setText("Health: " + animator.getAnimatedValue() + "%");
                        p2.setProgress((int) animator.getAnimatedValue());
                        changeP2HealthBars();
                    }
                });
                animator.start();
                p1EffectivenessMessage();
                if(p1Critt)
                {
                    critMessage();
                }

                if(activePokemon2.fainted)
                {
                    p2FaintAnimation();
                }
            }
        }, 1500);

    }
    public void p1StatusMoveAnimation()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

        }

        final Handler handler = new Handler();
        final ImageView pokemon1 = (ImageView)findViewById(R.id.gifp1);
        shrinkOrGrowImageAnimation(pokemon1, 250, 400, 1500);
        p1MoveMessage();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shrinkOrGrowImageAnimation(pokemon1, 400, 250, 1500);
                if(player1Healed)
                {
                    p1HealAnimation();
                }
            }
        }, 1500);
    }
    public void p2StatusMoveAnimation()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();


            //myRef.child("Games").child("Location").child("client is at top of p2StatusMoveAnimation").setValue("true");
        }

        final Handler handler = new Handler();
        final ImageView pokemon2 = (ImageView)findViewById(R.id.gifp2);
        shrinkOrGrowImageAnimation(pokemon2, 250, 400, 1500);
        p1MoveMessage();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shrinkOrGrowImageAnimation(pokemon2, 400, 250, 1500);
                if(player2Healed)
                {
                    p2HealAnimation();
                }
                //Message
            }
        }, 1500);

    }
    public void p2SpecialAttackAnimation()
    {
        final Handler handler = new Handler();
        final ImageView atk = (ImageView)findViewById(R.id.atkAnim1);
        final ProgressBar p1 = (ProgressBar)findViewById(R.id.healthBar1);
        atk.setVisibility(View.VISIBLE);
        atk.setBackgroundResource(R.drawable.button_border_bug);
        getAtkAnimColor(usedMove2.type);
        translatorAnimation(atk, 800, 200);
        p2MoveMessage();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                atk.setVisibility(View.INVISIBLE);
                int start = start1;
                int end = healthAfterHit1;
                final TextView healthText1= (TextView)findViewById(R.id.health1);
                final ValueAnimator animator = ValueAnimator.ofInt(start, end);
                animator.setDuration(1500);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        healthText1.setText("Health: " + animator.getAnimatedValue() + "%");
                        p1.setProgress((int) animator.getAnimatedValue());
                        changeP1HealthBars();
                    }
                });
                animator.start();
                p2EffectivenessMessage();

                if(p2Critt)
                {
                    critMessage();
                }

                if(activePokemon1.fainted)
                {
                   p1FaintAnimation();
                }

            }
        }, 1500);

    }
    public void p2PhysicalAttackAnimation()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            //myRef.child("Games").child("Location").child("client is at top of p2PhysicalAttackAnimation").setValue("true");
        }

        final Handler handler = new Handler();
        final ImageView atk = (ImageView)findViewById(R.id.atkAnim1);
        final ProgressBar p1 = (ProgressBar)findViewById(R.id.healthBar1);
        atk.setVisibility(View.VISIBLE);
        chooseAttackPicture2(usedMove2.type);
        translatorAnimation(atk, 800, 200);
        p2MoveMessage();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                atk.setVisibility(View.INVISIBLE);
                int start = start1;
                int end = healthAfterHit1;
                final TextView healthText1= (TextView)findViewById(R.id.health1);
                final ValueAnimator animator = ValueAnimator.ofInt(start, end);

                animator.setDuration(1500);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        healthText1.setText("Health: " + animator.getAnimatedValue() + "%");
                        p1.setProgress((int) animator.getAnimatedValue());
                        changeP1HealthBars();
                    }
                });
                animator.start();
                p2EffectivenessMessage();
                if(p2Critt)
                {
                    critMessage();
                }

                if(activePokemon1.fainted)
                {
                    p1FaintAnimation();
                }
            }
        }, 1500);

    }
    void p1FaintAnimation()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

           // myRef.child("Games").child("Location").child("client is at top of p1FaintAnimation").setValue("true");
        }

        final ImageView pic1 = (ImageView)findViewById(R.id.gifp1);

        shrinkOrGrowImageAnimation(pic1, 250, 0, 1500);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pic1.setImageResource(R.drawable.redx);
                shrinkOrGrowImageAnimation(pic1, 0, 250, 1500);
                p1FaintedMessage();
            }
        }, 1500);
    }
    void p2FaintAnimation()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

           // myRef.child("Games").child("Location").child("client is at top of p2FaintAnimation").setValue("true");
        }

        final ImageView pic2 = (ImageView)findViewById(R.id.gifp2);

        shrinkOrGrowImageAnimation(pic2, 250, 0, 1500);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pic2.setImageResource(R.drawable.redx);
                shrinkOrGrowImageAnimation(pic2, 0, 250, 1500);
                p2FaintedMessage();
            }
        }, 1500);
    }
    void initialSetup()
    {
        final ProgressBar p1 = (ProgressBar) findViewById(R.id.healthBar1);
        final ProgressBar p2 = (ProgressBar) findViewById(R.id.healthBar2);
        final TextView h1 = (TextView) findViewById(R.id.health1);
        final TextView h2 = (TextView) findViewById(R.id.health2);
        final TextView message = (TextView) findViewById(R.id.chat);

        setupP1HealthBars();
        setupP2HealthBars();

        ImageView weatherImage = (ImageView)findViewById(R.id.weatherImage);
        switch (weather)
        {
            case 1:
                weatherImage.setImageResource(R.drawable.sun);
                break;
            case 2:
                weatherImage.setImageResource(R.drawable.rain);
                break;
        }

        String msg1 = "Health: " + absoluteStart1 + "%";
        h1.setText(msg1);

        String msg2 = "Health: " + absoluteStart2 + "%";
        h2.setText(msg2);

        TextView tvpokemon1 = (TextView) findViewById(R.id.tvpokemon1);
        String msg3 = name1 + "'s " + activePokemon1.name;
        tvpokemon1.setText(msg3);

        TextView tvpokemon2 = (TextView) findViewById(R.id.tvpokemon2);
        String msg4 = name2 + "'s " + activePokemon2.name;
        tvpokemon2.setText(msg4);

        p1.setMax(100);
        p1.setProgress(absoluteStart1);

        p2.setMax(100);
        p2.setProgress(absoluteStart2);


        message.setText("");

        moveUse1 = " " + activePokemon1.name + " used " + usedMove1.name + "!";
        moveUse2 = " " + activePokemon2.name + " used " + usedMove2.name + "!";
        if (activePokemon1.fainted) {
            fainted1 = " " + activePokemon1.name + " fainted!";
        }
        if (activePokemon2.fainted) {
            fainted2 = " " + activePokemon2.name + " fainted!";
        }

        setFirstPics1();
        setFirstPics2();

        TextView t = (TextView)findViewById(R.id.biomonlogo) ;
        TextView t1 = (TextView)findViewById(R.id.chat) ;
        TextView t3 = (TextView)findViewById(R.id.tvpokemon1) ;
        TextView t4 = (TextView)findViewById(R.id.tvpokemon2) ;
        TextView t2 = (TextView)findViewById(R.id.health1) ;
        TextView t5 = (TextView)findViewById(R.id.health2) ;
        Button b = (Button) findViewById(R.id.button) ;

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/turtles.otf");
        t1.setTypeface(custom_font);
        t.setTypeface(custom_font);
        t2.setTypeface(custom_font);
        t3.setTypeface(custom_font);
        t4.setTypeface(custom_font);
        t5.setTypeface(custom_font);
        b.setTypeface(custom_font);

    }

    public void getAtkAnimColor(int type)
    {
        View view = (View)findViewById(R.id.atkAnim1);

        switch(type)
        {
            case 1:
                view.setBackgroundResource(R.drawable.button_border_normal);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.button_border_fire);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.button_border_water);
                break;
            case 4:
                view.setBackgroundResource(R.drawable.button_border_electric);
                break;
            case 5:
                view.setBackgroundResource(R.drawable.button_border_grass);
                break;
            case 6:
                view.setBackgroundResource(R.drawable.button_border_ice);
                break;
            case 7:
                view.setBackgroundResource(R.drawable.button_border_fighting);
                break;
            case 8:
                view.setBackgroundResource(R.drawable.button_border_poison);
                break;
            case 9:
                view.setBackgroundResource(R.drawable.button_border_ground);
                break;
            case 10:
                view.setBackgroundResource(R.drawable.button_border_flying);
                break;
            case 11:
                view.setBackgroundResource(R.drawable.button_border_psychic);
                break;
            case 12:
                view.setBackgroundResource(R.drawable.button_border_bug);
                break;
            case 13:
                view.setBackgroundResource(R.drawable.button_border_rock);
                break;
            case 14:
                view.setBackgroundResource(R.drawable.button_border_ghost);
                break;
            case 15:
                view.setBackgroundResource(R.drawable.button_border_dragon);
                break;
            case 16:
                view.setBackgroundResource(R.drawable.button_border_dark);
                break;
            case 17:
                view.setBackgroundResource(R.drawable.button_border_steel);
                break;
            case 18:
                view.setBackgroundResource(R.drawable.button_border_fairy);
                break;
        }

    }

    void checkP2Crap()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

           // myRef.child("Games").child("Location").child("client is at top of checkP2Crap").setValue("true");
        }

        TextView message = (TextView)findViewById(R.id.chat);

        if(activePokemon2.statusMethod()!="")
        {
            message.append(activePokemon2.cantMoveCuzStatusMessage());
        }

        else if(activePokemon2.flinch)
        {
            p2FlinchMessage();
        }
        else if(usedMove2.category==3)
        {
            //P2 is using a status move
            p2StatusMoveAnimation();
        }
        else if(!activePokemon2.hit)
        {
            p2MissedMessage();
        }
        else if(p2Effectiveness==0)
        {
            message.append(" " + usedMove2.name + " does not affect " + activePokemon1.name + "!");
            playDidntAffectSound();
        }

    }

    void p1FlinchMessage()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

           // myRef.child("Games").child("Location").child("client is at top of p1FlinchMessage").setValue("true");
        }

        TextView message = (TextView)findViewById(R.id.chat);
        message.append(" " + activePokemon1.name + " flinched!");
        playFlinch();
    }

    void p2FlinchMessage()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

           // myRef.child("Games").child("Location").child("client is at top of p2FlinchMessage").setValue("true");
        }

        TextView message = (TextView)findViewById(R.id.chat);
        message.append(" " + activePokemon2.name + " flinched!");
        playFlinch();
    }

    void p1MissedMessage()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            //myRef.child("Games").child("Location").child("client is at top of p1MissedMessage").setValue("true");
        }

        TextView message = (TextView)findViewById(R.id.chat);
        message.append(" " + activePokemon1.name + " missed!");
        playMiss();
    }

    void p2MissedMessage()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            //Initialize database
           // myRef.child("Games").child("Location").child("client is at top of p2MissedMessage").setValue("true");
        }

        TextView message = (TextView)findViewById(R.id.chat);
        message.append(" " + activePokemon2.name + " missed!");
        playMiss();
    }

    void checkP1Crap()
    {
        if(!host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            //myRef.child("Games").child("Location").child("client is at top of checkP1Crap").setValue("true");
        }

        TextView message = (TextView)findViewById(R.id.chat);

        if(activePokemon1.statusMethod()!="")
        {
            message.append(activePokemon1.cantMoveCuzStatusMessage());
        }

        else if(activePokemon1.flinch)
        {
            p1FlinchMessage();
        }
        else if(usedMove1.category==3)
        {
            //P1 is using a status move
            p1StatusMoveAnimation();
        }
        else if(!activePokemon1.hit)
        {
            p1MissedMessage();
        }
        else if(p1Effectiveness==0)
        {
            message.append(" " + usedMove1.name + " does not affect " + activePokemon2.name + "!");
            playDidntAffectSound();
        }
    }

    void p1HealAnimation()
    {
        final ProgressBar p1= (ProgressBar)findViewById(R.id.healthBar1);
        p1MoveMessage();

                int start = healthBeforeHealing1;
                int end = healthAfterHealing1;
                final TextView healthText1= (TextView)findViewById(R.id.health1);
                final ValueAnimator animator = ValueAnimator.ofInt(start, end);
                animator.setDuration(1500);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        healthText1.setText("Health: " + animator.getAnimatedValue() + "%");
                        p1.setProgress((int) animator.getAnimatedValue());
                        changeP1HealthBars();
                    }
                });
                animator.start();
    }
    void p2HealAnimation()
    {
        final ProgressBar p2= (ProgressBar)findViewById(R.id.healthBar2);
        p2MoveMessage();

        int start = healthBeforeHealing2;
        int end = healthAfterHealing2;
        final TextView healthText2= (TextView)findViewById(R.id.health2);
        final ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                healthText2.setText("Health: " + animator.getAnimatedValue() + "%");
                p2.setProgress((int) animator.getAnimatedValue());
                changeP2HealthBars();
            }
        });
        animator.start();
    }

    void chooseAttackPicture1(int type)
    {
        ImageView imageView = (ImageView)findViewById(R.id.atkAnim1);
        switch (type)
        {
            case 1:
                imageView.setBackgroundResource(R.drawable.defaultp1fist);
                break;
            case 2:
                imageView.setBackgroundResource(R.drawable.p1firefist);
                break;
            case 3:
                imageView.setBackgroundResource(R.drawable.p1waterfist);
                break;
            case 4:
                imageView.setBackgroundResource(R.drawable.p1electricfist);
                break;
            case 5:
                imageView.setBackgroundResource(R.drawable.p1grassfist);
                break;
            case 6:
                imageView.setBackgroundResource(R.drawable.p1icefist);
                break;
            case 7:
                imageView.setBackgroundResource(R.drawable.p1fightingfist);
                break;
            case 8:
                imageView.setBackgroundResource(R.drawable.p1poisonfist);
                break;
            case 9:
                imageView.setBackgroundResource(R.drawable.p1groundfist);
                break;
            case 10:
                imageView.setBackgroundResource(R.drawable.defaultp1fist);
                break;
            case 11:
                imageView.setBackgroundResource(R.drawable.p1poisonfist);
                break;
            case 12:
                imageView.setBackgroundResource(R.drawable.p1bugfist);
                break;
            case 13:
                imageView.setBackgroundResource(R.drawable.p1rockfist);
                break;
            case 14:
                imageView.setBackgroundResource(R.drawable.p1ghostfist);
                break;
            case 15:
                imageView.setBackgroundResource(R.drawable.p1dragonfist);
                break;
            case 16:
                imageView.setBackgroundResource(R.drawable.p1darkfist);
                break;
            case 17:
                imageView.setBackgroundResource(R.drawable.p1steelfist);
                break;
            case 18:
                imageView.setBackgroundResource(R.drawable.p1fairyfist);
                break;

        }
    }

    void chooseAttackPicture2(int type)
    {
        ImageView imageView = (ImageView)findViewById(R.id.atkAnim1);
        switch (type)
        {
            case 1:
                imageView.setBackgroundResource(R.drawable.defaultp2fist);
                break;
            case 2:
                imageView.setBackgroundResource(R.drawable.p2firefist);
                break;
            case 3:
                imageView.setBackgroundResource(R.drawable.p2waterfist);
                break;
            case 4:
                imageView.setBackgroundResource(R.drawable.p2electricfist);
                break;
            case 5:
                imageView.setBackgroundResource(R.drawable.p2grassfist);
                break;
            case 6:
                imageView.setBackgroundResource(R.drawable.p2icefist);
                break;
            case 7:
                imageView.setBackgroundResource(R.drawable.p2fightingfist);
                break;
            case 8:
                imageView.setBackgroundResource(R.drawable.p2poisonfist);
                break;
            case 9:
                imageView.setBackgroundResource(R.drawable.p2groundfist);
                break;
            case 10:
                imageView.setBackgroundResource(R.drawable.defaultp2fist);
                break;
            case 11:
                imageView.setBackgroundResource(R.drawable.p2poisonfist);
                break;
            case 12:
                imageView.setBackgroundResource(R.drawable.p2bugfist);
                break;
            case 13:
                imageView.setBackgroundResource(R.drawable.p2rockfist);
                break;
            case 14:
                imageView.setBackgroundResource(R.drawable.p2ghostfist);
                break;
            case 15:
                imageView.setBackgroundResource(R.drawable.p2dragonfist);
                break;
            case 16:
                imageView.setBackgroundResource(R.drawable.p2darkfist);
                break;
            case 17:
                imageView.setBackgroundResource(R.drawable.p2steelfist);
                break;
            case 18:
                imageView.setBackgroundResource(R.drawable.p2fairyfist);
                break;

        }
    }

    void changeP1HealthBars()
    {
        final ProgressBar p1 = (ProgressBar) findViewById(R.id.healthBar1);
        Drawable draw;

        if(activePokemon1.hpPercentAsInt()>50)
        {
            draw= getResources().getDrawable(R.drawable.custom_health_bar_over_half);
        }
        else if(activePokemon1.hpPercentAsInt()>20)
        {
            draw= getResources().getDrawable(R.drawable.custom_health_bar_over_20);
        }
        else
        {
            draw= getResources().getDrawable(R.drawable.custom_health_bar_red);
        }

        p1.setProgressDrawable(draw);
    }
    void changeP2HealthBars()
    {
        final ProgressBar p2 = (ProgressBar) findViewById(R.id.healthBar2);
        Drawable draw2;

        if(activePokemon2.hpPercentAsInt()>50)
        {
            draw2= getResources().getDrawable(R.drawable.custom_health_bar_over_half);
        }
        else if(activePokemon2.hpPercentAsInt()>20)
        {
            draw2= getResources().getDrawable(R.drawable.custom_health_bar_over_20);
        }
        else
        {
            draw2= getResources().getDrawable(R.drawable.custom_health_bar_red);
        }
        p2.setProgressDrawable(draw2);
    }

    void setupP1HealthBars()
    {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        final ProgressBar p1 = (ProgressBar) findViewById(R.id.healthBar1);
        Drawable draw;

        if(absoluteStart1>50)
        {
            draw= getResources().getDrawable(R.drawable.custom_health_bar_over_half);
        }
        else if(absoluteStart1>20)
        {
            draw= getResources().getDrawable(R.drawable.custom_health_bar_over_20);
        }
        else
        {
            draw= getResources().getDrawable(R.drawable.custom_health_bar_red);
        }

        p1.setProgressDrawable(draw);
    }
    void setupP2HealthBars()
    {
        final ProgressBar p2 = (ProgressBar) findViewById(R.id.healthBar2);
        Drawable draw2;

        if(absoluteStart2>50)
        {
            draw2= getResources().getDrawable(R.drawable.custom_health_bar_over_half);
        }
        else if(absoluteStart2>20)
        {
            draw2= getResources().getDrawable(R.drawable.custom_health_bar_over_20);
        }
        else
        {
            draw2= getResources().getDrawable(R.drawable.custom_health_bar_red);
        }
        p2.setProgressDrawable(draw2);
    }

    void changeP1HealthBarsForSwitch()
    {
        final ProgressBar p1 = (ProgressBar) findViewById(R.id.healthBar1);
        Drawable draw;

        if(start1>50)
        {
            draw= getResources().getDrawable(R.drawable.custom_health_bar_over_half);
        }
        else if(start1>20)
        {
            draw= getResources().getDrawable(R.drawable.custom_health_bar_over_20);
        }
        else
        {
            draw= getResources().getDrawable(R.drawable.custom_health_bar_red);
        }

        p1.setProgressDrawable(draw);
    }
    void changeP2HealthBarsForSwitch()
    {
        final ProgressBar p2 = (ProgressBar) findViewById(R.id.healthBar2);
        Drawable draw2;

        if(start2>50)
        {
            draw2= getResources().getDrawable(R.drawable.custom_health_bar_over_half);
        }
        else if(start2>20)
        {
            draw2= getResources().getDrawable(R.drawable.custom_health_bar_over_20);
        }
        else
        {
            draw2= getResources().getDrawable(R.drawable.custom_health_bar_red);
        }
        p2.setProgressDrawable(draw2);
    }

    private void playBrutalSound() {
        if(chanceOfSound())
        {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.brutal);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }
    }

    private void playIneffectiveSound() {
        if(chanceOfSound())
        {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.notveryeffective);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }
    }

    private void playDidntAffectSound() {
        if(chanceOfSound())
        {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.didntaffect);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }
    }

    private void playFlinch() {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.cantmove);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });

    }

    private void playMiss() {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.miss);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
    }
    private void playSwitch() {
        if(chanceOfSound())
        {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.switch1);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }
    }

    private void playBringOutNewPokemon() {
        if(chanceOfSound())
        {
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

    //Returns a 50-50 true/false
    public boolean chanceOfSound()
    {
        Random random = new Random();
        boolean a = random.nextBoolean();
        return a;
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
            ref3.removeValue();
            ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Has Chosen").child("Default").setValue("0");

            //Remove Ready to Proceed
            ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).removeValue();

            //I only want one of them to add back in the default user
            if(host)
            {
                ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child("Default").setValue("0");
                ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Host").removeValue();
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
        AudioPlay.playAudio(this, R.raw.battletheme);

    }

    public void onStop()
    {
        super.onStop();
        AudioPlay.stopAudio();
        if(playingOnline&& !stayingInApp)
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
