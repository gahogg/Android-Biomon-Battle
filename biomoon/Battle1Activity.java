package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;
import static com.greg.android.biomoon.Globals.CPUStuff;
import static com.greg.android.biomoon.Globals.activePokemon1;
import static com.greg.android.biomoon.Globals.activePokemon2;
import static com.greg.android.biomoon.Globals.effectiveness1;
import static com.greg.android.biomoon.Globals.effectiveness2;
import static com.greg.android.biomoon.Globals.fainted1;
import static com.greg.android.biomoon.Globals.fainted2;
import static com.greg.android.biomoon.Globals.healthAfterHealing1;
import static com.greg.android.biomoon.Globals.healthAfterHit1;
import static com.greg.android.biomoon.Globals.moveUse1;
import static com.greg.android.biomoon.Globals.moveUse2;
import static com.greg.android.biomoon.Globals.pokemon;
import static com.greg.android.biomoon.Globals.pokemonStatused1;
import static com.greg.android.biomoon.Globals.pokemonStatused2;
import static com.greg.android.biomoon.Globals.reasonForError1;
import static com.greg.android.biomoon.Globals.reasonForError2;
import static com.greg.android.biomoon.Globals.statusMove1;
import static com.greg.android.biomoon.Globals.statusMove2;
import static com.greg.android.biomoon.HomeActivity.isP2CPU;
import static com.greg.android.biomoon.Globals.moves;
import static com.greg.android.biomoon.Globals.name1;
import static com.greg.android.biomoon.Globals.name2;
import static com.greg.android.biomoon.Globals.start1;
import static com.greg.android.biomoon.Globals.start2;
import static com.greg.android.biomoon.Globals.p1Pokemon;
import static com.greg.android.biomoon.Globals.p1Switch;
import static com.greg.android.biomoon.Globals.usedMove1;
import static com.greg.android.biomoon.Globals.usedMove2;
import static com.greg.android.biomoon.Globals.pokemonP2Sees;
import static com.greg.android.biomoon.HomeActivity.healthAfterHit2;
import static com.greg.android.biomoon.Globals.healthAfterRecoil1;
import static com.greg.android.biomoon.Globals.healthAfterRecoil2;
import static com.greg.android.biomoon.Globals.healthAfterStatusDamage1;
import static com.greg.android.biomoon.Globals.healthAfterStatusDamage2;
import static com.greg.android.biomoon.Globals.healthAfterWeatherEffects1;
import static com.greg.android.biomoon.Globals.getHealthAfterWeatherEffects2;
import static com.greg.android.biomoon.Globals.absoluteStart1;
import static com.greg.android.biomoon.Globals.absoluteStart2;
import static com.greg.android.biomoon.Globals.p1Effectiveness;
import static com.greg.android.biomoon.Globals.p2Effectiveness;
import static com.greg.android.biomoon.Globals.healthAfterHealing2;
import static com.greg.android.biomoon.Globals.healthBeforeHealing1;
import static com.greg.android.biomoon.Globals.healthBeforeHealing2;
import static com.greg.android.biomoon.Globals.player1Healed;
import static com.greg.android.biomoon.Globals.player2Healed;
import static com.greg.android.biomoon.HomeActivity.firstTime;
import static com.greg.android.biomoon.Globals.p1Critt;
import static com.greg.android.biomoon.Globals.p2Critt;
import static com.greg.android.biomoon.HomeActivity.otherPlayersKey;
import static com.greg.android.biomoon.HomeActivity.playingOnline;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;

/**
 * Created by Greg on 2017-06-25.
 */
public class Battle1Activity extends Activity {
    ViewGroup gregsLayout;

    public static AbstractBiomon actualPokemonP2Sees;
    public static int previousPokemonDexNumber1 = activePokemon1.dexNumber;
    public static int previousPokemonDexNumber2 = activePokemon2.dexNumber;
    boolean stayingInApp=false;

    public static String pokemonP2SeesName ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle1);


        TextView tvpokemon2 = (TextView) findViewById(R.id.tvpokemon2);
        String msg2 = name2 + "'s " + activePokemon2.name;
        tvpokemon2.setText(msg2);

        setTable2Type1Img();
        if(activePokemon2.type2>0)
        {
            setTable2Type2Img();
        }
        gifStuffP2();

        stayingInApp=false;


        absoluteStart1 = activePokemon1.hpPercentAsInt();
        absoluteStart2 = activePokemon2.hpPercentAsInt();
        //Sending database these numbers



        gregsLayout = (ViewGroup) findViewById(R.id.battle1Layout);
        getMoveBackgroundColors1();

        actualPokemonP2Sees = activePokemon1;

        Button b1a = (Button) findViewById(R.id.pokemon1_1battle);
        b1a.setText(p1Pokemon[0].name);
        Button b2a = (Button) findViewById(R.id.pokemon2_1battle);
        b2a.setText(p1Pokemon[1].name);
        Button b3a = (Button) findViewById(R.id.pokemon3_1battle);
        b3a.setText(p1Pokemon[2].name);
        Button b4a = (Button) findViewById(R.id.pokemon4_1battle);
        b4a.setText(p1Pokemon[3].name);
        Button b5a = (Button) findViewById(R.id.pokemon5_1battle);
        b5a.setText(p1Pokemon[4].name);
        Button b6a = (Button) findViewById(R.id.pokemon6_1battle);
        b6a.setText(p1Pokemon[5].name);

        TextView tvpokemon1 = (TextView) findViewById(R.id.tvpokemon1);
        String msg3 = name1 + "'s " + activePokemon1.name;
        tvpokemon1.setText(msg3);


        if (p1Pokemon[0].fainted) {
            b1a.setEnabled(false);
        }
        if (p1Pokemon[1].fainted) {
            b2a.setEnabled(false);
        }
        if (p1Pokemon[2].fainted) {
            b3a.setEnabled(false);
        }
        if (p1Pokemon[3].fainted) {
            b4a.setEnabled(false);
        }
        if (p1Pokemon[4].fainted) {
            b5a.setEnabled(false);
        }
        if (p1Pokemon[5].fainted) {
            b6a.setEnabled(false);
        }

        setTable1Type1Img();
        if(activePokemon1.type2>0)
        {
            setTable1Type2Img();
        }


        if(p1Pokemon[0].dexNumber == activePokemon1.dexNumber)
        {
            b1a.setEnabled(false);
        }
        if(p1Pokemon[1].dexNumber == activePokemon1.dexNumber)
        {
            b2a.setEnabled(false);
        }
        if(p1Pokemon[2].dexNumber == activePokemon1.dexNumber)
        {
            b3a.setEnabled(false);
        }
        if(p1Pokemon[3].dexNumber == activePokemon1.dexNumber)
        {
            b4a.setEnabled(false);
        }
        if(p1Pokemon[4].dexNumber == activePokemon1.dexNumber)
        {
            b5a.setEnabled(false);
        }
        if(p1Pokemon[5].dexNumber == activePokemon1.dexNumber)
        {
            b6a.setEnabled(false);
        }


        Button moveA = (Button) findViewById(R.id.p1move1);
        moveA.setText(activePokemon1.move1.name);

        Button moveB = (Button) findViewById(R.id.p1move2);
        moveB.setText(activePokemon1.move2.name);

        Button moveC = (Button) findViewById(R.id.p1move3);
        moveC.setText(activePokemon1.move3.name);

        Button moveD = (Button) findViewById(R.id.p1move4);
        moveD.setText(activePokemon1.move4.name);

        ImageView img1 = (ImageView) findViewById(R.id.gifp1);
        ImageView img2 = (ImageView) findViewById(R.id.gifp2);

        gifStuffP1();


        usedMove1 = moves[0];
        usedMove2 = moves[0];

        pokemonP2Sees = activePokemon1.dexNumber;
        pokemonP2SeesName = activePokemon1.name;

        movePopups();
        getPokemon1Info();
        getPokemon2Info();
        getPokemon3Info();
        getPokemon4Info();
        getPokemon5Info();
        getPokemon6Info();

         start1 = (int)Math.round(Double.parseDouble(activePokemon1.hpPercent()));
         start2 = (int)Math.round(Double.parseDouble(activePokemon2.hpPercent()));

        reasonForError1=""; reasonForError2 =""; moveUse1=""; moveUse2=""; statusMove1=""; statusMove2="";
        pokemonStatused1=""; pokemonStatused2=""; effectiveness1=""; effectiveness2=""; fainted1="";
        fainted2 ="";
        activePokemon1.statusedString="";
        activePokemon2.statusedString= "";
        activePokemon1.statusPreviousTurn = activePokemon1.statusMethod();
        activePokemon2.statusPreviousTurn = activePokemon2.statusMethod();
        previousPokemonDexNumber1 = activePokemon1.dexNumber;
        previousPokemonDexNumber2 = activePokemon2.dexNumber;
        healthAfterHit1=0;
        healthAfterHit1=0; healthAfterHit2=0; healthAfterRecoil1=0; healthAfterRecoil2=0;
                healthAfterStatusDamage1=0; healthAfterStatusDamage2=0; healthAfterWeatherEffects1=0; getHealthAfterWeatherEffects2 =0;
        activePokemon1.hit=false; activePokemon2.hit=false; p1Effectiveness=0; p2Effectiveness=0;
        player1Healed=false; player2Healed=false; healthBeforeHealing1=0; healthBeforeHealing2=0;
        healthAfterHealing1=0; healthAfterHealing2=0; p1Critt=false; p2Critt=false;

        ImageView p1Status = (ImageView)findViewById(R.id.p1StatusImageB1);

        p1Status.setImageResource(0);
        switch(activePokemon1.statusMethod())
        {
            case "burn":
                p1Status.setImageResource(R.drawable.burn);
                break;
            case "frozen":
                p1Status.setImageResource(R.drawable.frozen);
                break;
            case "paralyze":
                p1Status.setImageResource(R.drawable.paralysis);
                break;
            case "poison":
                p1Status.setImageResource(R.drawable.poison);
                break;
        }

        ImageView p2Status = (ImageView)findViewById(R.id.p2StatusImageB1);

        p2Status.setImageResource(0);
        switch(activePokemon2.statusMethod())
        {
            case "burn":
                p2Status.setImageResource(R.drawable.burn);
                break;
            case "frozen":
                p2Status.setImageResource(R.drawable.frozen);
                break;
            case "paralyze":
                p2Status.setImageResource(R.drawable.paralysis);
                break;
            case "poison":
                p2Status.setImageResource(R.drawable.poison);
                break;
        }

        healthStuff();

        Button b1 = (Button) findViewById(R.id.pokemon1_1battle);
        Button b2 = (Button) findViewById(R.id.pokemon2_1battle);
        Button b3 = (Button) findViewById(R.id.pokemon3_1battle);
        Button b4 = (Button) findViewById(R.id.pokemon4_1battle);
        Button b5 = (Button) findViewById(R.id.pokemon5_1battle);
        Button b6 = (Button) findViewById(R.id.pokemon6_1battle);
        Button b7 = (Button) findViewById(R.id.p1move1);
        Button b8 = (Button) findViewById(R.id.p1move2);
        Button b9 = (Button) findViewById(R.id.p1move3);
        Button b10 = (Button) findViewById(R.id.p1move4);
        Button b11 = (Button) findViewById(R.id.button4);

        TextView t1 = (TextView)findViewById(R.id.table1Name) ;
        TextView t2 = (TextView)findViewById(R.id.table1Atk) ;
        TextView t3 = (TextView)findViewById(R.id.table1SpAtk) ;
        TextView t4 = (TextView)findViewById(R.id.table1Def) ;
        TextView t5 = (TextView)findViewById(R.id.table1SpDef) ;
        TextView t6 = (TextView)findViewById(R.id.table1Spd) ;
        TextView t7 = (TextView)findViewById(R.id.table1Ability) ;
        TextView t8 = (TextView)findViewById(R.id.tvpokemon1) ;
        TextView t9 = (TextView)findViewById(R.id.tvpokemon2) ;
        TextView t10 = (TextView)findViewById(R.id.popupMove1) ;

        final TextView h1 = (TextView) findViewById(R.id.health1);
        final TextView h2 = (TextView) findViewById(R.id.health2);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/turtles.otf");
        t1.setTypeface(custom_font);
        t2.setTypeface(custom_font);
        t3.setTypeface(custom_font);
        t4.setTypeface(custom_font);
        t5.setTypeface(custom_font);
        t6.setTypeface(custom_font);
        t7.setTypeface(custom_font);
        t8.setTypeface(custom_font);
        t9.setTypeface(custom_font);
        t10.setTypeface(custom_font);

        b1.setTypeface(custom_font);
        b2.setTypeface(custom_font);
        b3.setTypeface(custom_font);
        b4.setTypeface(custom_font);
        b5.setTypeface(custom_font);
        b6.setTypeface(custom_font);
        b7.setTypeface(custom_font);
        b8.setTypeface(custom_font);
        b9.setTypeface(custom_font);
        b10.setTypeface(custom_font);
        b11.setTypeface(custom_font);

        h1.setTypeface(custom_font);
        h2.setTypeface(custom_font);


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

    void healthStuff()
    {
        final ProgressBar p1 = (ProgressBar) findViewById(R.id.healthBar1);
        final ProgressBar p2 = (ProgressBar) findViewById(R.id.healthBar2);
        final TextView h1 = (TextView) findViewById(R.id.health1);
        final TextView h2 = (TextView) findViewById(R.id.health2);

        changeP1HealthBars();
        changeP2HealthBars();

        String msg1 =  absoluteStart1 + "%";
        h1.setText(msg1);

        String msg2 =  absoluteStart2 + "%";
        h2.setText(msg2);

        p1.setMax(100);
        p1.setProgress(absoluteStart1);

        p2.setMax(100);
        p2.setProgress(absoluteStart2);

    }

    public void movePopups()
    {
        Button moveA = (Button) findViewById(R.id.p1move1);
        moveA.setText(activePokemon1.move1.name);

        Button moveB = (Button) findViewById(R.id.p1move2);
        moveB.setText(activePokemon1.move2.name);

        Button moveC = (Button) findViewById(R.id.p1move3);
        moveC.setText(activePokemon1.move3.name);

        Button moveD = (Button) findViewById(R.id.p1move4);
        moveD.setText(activePokemon1.move4.name);

        final TextView popup = (TextView)findViewById(R.id.popupMove1);

        String powerToDisplay1 ="";

        if(activePokemon1.move1.power<1 || activePokemon1.move1.power>300)
        {
            powerToDisplay1 = "N/A";
        }
        else
        {
            powerToDisplay1 = "" + activePokemon1.move1.power;
        }

        String accuracyToDisplay1 = "";
        if(activePokemon1.move1.accuracy<10 || activePokemon1.move1.accuracy>100)
        {
            accuracyToDisplay1 = "N/A";
        }
        else
        {
            accuracyToDisplay1 = "" + activePokemon1.move1.accuracy;
        }

        final String msgPopupMove1 = activePokemon1.move1.name.toUpperCase() + "\nType: " + typeToString(activePokemon1.move1.type)
                + "\nCategory: " + categoryToString(activePokemon1.move1.category) + "\nPower: " + powerToDisplay1 + "\nPriority: " + activePokemon1.move1.priority
                + "\nAccuracy: " + accuracyToDisplay1;

        String powerToDisplay2 ="";

        if(activePokemon1.move2.power<1 || activePokemon1.move2.power>300)
        {
            powerToDisplay2 = "N/A";
        }
        else
        {
            powerToDisplay2 = "" + activePokemon1.move2.power;
        }

        String accuracyToDisplay2 = "";
        if(activePokemon1.move2.accuracy<10 || activePokemon1.move2.accuracy>100)
        {
            accuracyToDisplay2 = "N/A";
        }
        else
        {
            accuracyToDisplay2 = "" + activePokemon1.move2.accuracy;
        }

        final String msgPopupMove2 = activePokemon1.move2.name.toUpperCase() + "\nType: " + typeToString(activePokemon1.move2.type)
                + "\nCategory: " + categoryToString(activePokemon1.move2.category) + "\nPower: " + powerToDisplay2 + "\nPriority: " + activePokemon1.move2.priority
                + "\nAccuracy: " + accuracyToDisplay2;

        String powerToDisplay3 ="";

        if(activePokemon1.move3.power<1 || activePokemon1.move3.power>300)
        {
            powerToDisplay3 = "N/A";
        }
        else
        {
            powerToDisplay3 = "" + activePokemon1.move3.power;
        }

        String accuracyToDisplay3 = "";
        if(activePokemon1.move3.accuracy<10 || activePokemon1.move3.power>100)
        {
            accuracyToDisplay3 = "N/A";
        }
        else
        {
            accuracyToDisplay3 = "" + activePokemon1.move3.accuracy;
        }

        final String msgPopupMove3 = activePokemon1.move3.name.toUpperCase() + "\nType: " + typeToString(activePokemon1.move3.type)
                + "\nCategory: " + categoryToString(activePokemon1.move3.category) + "\nPower: " + powerToDisplay3 + "\nPriority: " + activePokemon1.move3.priority
                + "\nAccuracy: " + accuracyToDisplay3;


        String powerToDisplay4 ="";

        if(activePokemon1.move4.power<1 || activePokemon1.move4.power>300)
        {
            powerToDisplay4 = "N/A";
        }
        else
        {
            powerToDisplay4 = "" + activePokemon1.move4.power;
        }
        String accuracyToDisplay4 = "";
        if(activePokemon1.move4.accuracy<10 || activePokemon1.move4.accuracy>100)
        {
            accuracyToDisplay4 = "N/A";
        }
        else
        {
            accuracyToDisplay4 = "" + activePokemon1.move4.accuracy;
        }

        final String msgPopupMove4 = activePokemon1.move4.name.toUpperCase() + "\nType: " + typeToString(activePokemon1.move4.type)
                + "\nCategory: " + categoryToString(activePokemon1.move4.category) + "\nPower: " + powerToDisplay4 + "\nPriority: " + activePokemon1.move4.priority
                + "\nAccuracy: " + accuracyToDisplay4;



        moveA.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            popup.setText("");
                            popup.setBackgroundResource(0);
                        }
                        else
                        {
                            useMove1(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        popup.setText(msgPopupMove1);
                        popup.setBackgroundResource(R.drawable.popup_outline);
                        break;
                }
                return false;
            }
        });

        moveB.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            popup.setText("");
                            popup.setBackgroundResource(0);
                        }
                        else
                        {
                            useMove2(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        popup.setText(msgPopupMove2);
                        popup.setBackgroundResource(R.drawable.popup_outline);
                        break;
                }
                return false;
            }
        });

        moveC.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            popup.setText("");
                            popup.setBackgroundResource(0);
                        }
                        else
                        {
                            useMove3(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        popup.setText(msgPopupMove3);
                        popup.setBackgroundResource(R.drawable.popup_outline);
                        break;
                }
                return false;
            }
        });

        moveD.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            popup.setText("");
                            popup.setBackgroundResource(0);
                        }
                        else
                        {
                            useMove4(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        popup.setText(msgPopupMove4);
                        popup.setBackgroundResource(R.drawable.popup_outline);
                        break;
                }
                return false;
            }
        });
    }

    public void gifStuffP1()
    {
        ImageView img1= (ImageView) findViewById(R.id.gifp1);

        if(activePokemon1.dexNumber == 1)
        {
            img1.setImageResource(R.drawable.a1);
        }

        if(activePokemon1.dexNumber == 2)
        {
            img1.setImageResource(R.drawable.a2);
        }


        if(activePokemon1.dexNumber == 3)
        {
            img1.setImageResource(R.drawable.a3);
        }


        if(activePokemon1.dexNumber == 4)
        {
            img1.setImageResource(R.drawable.a4);
        }

        if(activePokemon1.dexNumber == 5)
        {
            img1.setImageResource(R.drawable.a5);
        }


        if(activePokemon1.dexNumber == 6)
        {
            img1.setImageResource(R.drawable.a6);
        }


        if(activePokemon1.dexNumber == 7)
        {
            img1.setImageResource(R.drawable.a7);
        }

        if(activePokemon1.dexNumber == 8)
        {
            img1.setImageResource(R.drawable.a8);
        }


        if(activePokemon1.dexNumber == 9)
        {
            img1.setImageResource(R.drawable.a9);
        }


        if(activePokemon1.dexNumber == 10)
        {
            img1.setImageResource(R.drawable.a10);
        }

        if(activePokemon1.dexNumber == 11)
        {
            img1.setImageResource(R.drawable.a11);
        }


        if(activePokemon1.dexNumber == 12)
        {
            img1.setImageResource(R.drawable.a12);
        }


        if(activePokemon1.dexNumber == 13)
        {
            img1.setImageResource(R.drawable.a13);
        }


        if(activePokemon1.dexNumber == 14)
        {
            img1.setImageResource(R.drawable.a14);
        }


        if(activePokemon1.dexNumber == 15)
        {
            img1.setImageResource(R.drawable.a15);
        }


        if(activePokemon1.dexNumber == 16)
        {
            img1.setImageResource(R.drawable.a16);
        }


        if(activePokemon1.dexNumber == 17)
        {
            img1.setImageResource(R.drawable.a17);
        }


        if(activePokemon1.dexNumber == 18)
        {
            img1.setImageResource(R.drawable.a18);
        }


        if(activePokemon1.dexNumber == 19)
        {
            img1.setImageResource(R.drawable.a19);
        }


        if(activePokemon1.dexNumber == 20)
        {
            img1.setImageResource(R.drawable.a20);
        }


        if(activePokemon1.dexNumber == 21)
        {
            img1.setImageResource(R.drawable.a21);
        }


        if(activePokemon1.dexNumber == 22)
        {
            img1.setImageResource(R.drawable.a22);
        }


        if(activePokemon1.dexNumber == 23)
        {
            img1.setImageResource(R.drawable.a23);
        }


        if(activePokemon1.dexNumber == 24)
        {
            img1.setImageResource(R.drawable.a24);
        }

        if(activePokemon1.dexNumber == 25)
        {
            img1.setImageResource(R.drawable.a25);
        }


        if(activePokemon1.dexNumber == 26)
        {
            img1.setImageResource(R.drawable.a26);
        }


        if(activePokemon1.dexNumber == 27)
        {
            img1.setImageResource(R.drawable.a27);
        }


        if(activePokemon1.dexNumber == 28)
        {
            img1.setImageResource(R.drawable.a28);
        }


        if(activePokemon1.dexNumber == 29)
        {
            img1.setImageResource(R.drawable.a29);
        }

        if(activePokemon1.dexNumber == 30)
        {
            img1.setImageResource(R.drawable.a30);
        }


        if(activePokemon1.dexNumber == 31)
        {
            img1.setImageResource(R.drawable.a31);
        }

        if(activePokemon1.dexNumber == 32)
        {
            img1.setImageResource(R.drawable.a32);
        }


        if(activePokemon1.dexNumber == 33)
        {
            img1.setImageResource(R.drawable.a33);
        }


        if(activePokemon1.dexNumber == 34)
        {
            img1.setImageResource(R.drawable.a34);
        }


        if(activePokemon1.dexNumber == 35)
        {
            img1.setImageResource(R.drawable.a35);
        }


        if(activePokemon1.dexNumber == 36)
        {
            img1.setImageResource(R.drawable.a36);
        }

        if(activePokemon1.dexNumber == 37)
        {
            img1.setImageResource(R.drawable.a37);
        }


        if(activePokemon1.dexNumber == 38)
        {
            img1.setImageResource(R.drawable.a38);
        }

        if(activePokemon1.dexNumber == 39)
        {
            img1.setImageResource(R.drawable.a39);
        }


        if(activePokemon1.dexNumber == 40)
        {
            img1.setImageResource(R.drawable.a40);
        }


        if(activePokemon1.dexNumber == 41)
        {
            img1.setImageResource(R.drawable.a41);
        }

        if(activePokemon1.dexNumber == 42)
        {
            img1.setImageResource(R.drawable.a42);
        }


        if(activePokemon1.dexNumber == 43)
        {
            img1.setImageResource(R.drawable.a43);
        }


        if(activePokemon1.dexNumber == 44)
        {
            img1.setImageResource(R.drawable.a44);
        }

        if(activePokemon1.dexNumber == 45)
        {
            img1.setImageResource(R.drawable.a45);
        }


        if(activePokemon1.dexNumber == 46)
        {
            img1.setImageResource(R.drawable.a46);
        }


        if(activePokemon1.dexNumber == 47)
        {
            img1.setImageResource(R.drawable.a47);
        }


        if(activePokemon1.dexNumber == 48)
        {
            img1.setImageResource(R.drawable.a48);
        }


        if(activePokemon1.dexNumber == 49)
        {
            img1.setImageResource(R.drawable.a49);
        }


        if(activePokemon1.dexNumber == 50)
        {
            img1.setImageResource(R.drawable.a50);
        }

        if(activePokemon1.dexNumber == 51)
        {
            img1.setImageResource(R.drawable.a51);
        }

        if(activePokemon1.dexNumber == 52)
        {
            img1.setImageResource(R.drawable.a52);
        }

        if(activePokemon1.dexNumber == 53)
        {
            img1.setImageResource(R.drawable.a53);
        }


        if(activePokemon1.dexNumber == 54)
        {
            img1.setImageResource(R.drawable.a54);
        }


        if(activePokemon1.dexNumber == 55)
        {
            img1.setImageResource(R.drawable.a55);
        }


        if(activePokemon1.dexNumber == 56)
        {
            img1.setImageResource(R.drawable.a56);
        }


        if(activePokemon1.dexNumber == 57)
        {
            img1.setImageResource(R.drawable.a57);
        }


        if(activePokemon1.dexNumber == 58)
        {
            img1.setImageResource(R.drawable.a5);
        }

        if(activePokemon1.dexNumber == 59)
        {
            img1.setImageResource(R.drawable.a59);
        }


        if(activePokemon1.dexNumber == 60)
        {
            img1.setImageResource(R.drawable.a60);
        }

        if(activePokemon1.dexNumber == 61)
        {
            img1.setImageResource(R.drawable.a61);
        }


        if(activePokemon1.dexNumber == 62)
        {
            img1.setImageResource(R.drawable.a62);
        }


        if(activePokemon1.dexNumber == 63)
        {
            img1.setImageResource(R.drawable.a63);
        }


        if(activePokemon1.dexNumber == 64)
        {
            img1.setImageResource(R.drawable.a64);
        }

        if(activePokemon1.dexNumber == 65)
        {
            img1.setImageResource(R.drawable.a65);
        }

        if(activePokemon1.dexNumber == 66)
        {
            img1.setImageResource(R.drawable.a66);
        }

        if(activePokemon1.dexNumber == 67)
        {
            img1.setImageResource(R.drawable.a67);
        }


        if(activePokemon1.dexNumber == 68)
        {
            img1.setImageResource(R.drawable.a68);
        }

        if(activePokemon1.dexNumber == 69)
        {
            img1.setImageResource(R.drawable.a69);
        }


        if(activePokemon1.dexNumber == 70)
        {
            img1.setImageResource(R.drawable.a70);
        }

        if(activePokemon1.dexNumber == 71)
        {
            img1.setImageResource(R.drawable.a71);
        }


        if(activePokemon1.dexNumber == 72)
        {
            img1.setImageResource(R.drawable.a72);
        }


        if(activePokemon1.dexNumber == 73)
        {
            img1.setImageResource(R.drawable.a73);
        }


        if(activePokemon1.dexNumber == 74)
        {
            img1.setImageResource(R.drawable.a74);
        }

        if(activePokemon1.dexNumber == 75)
        {
            img1.setImageResource(R.drawable.a75);
        }


        if(activePokemon1.dexNumber == 76)
        {
            img1.setImageResource(R.drawable.a76);
        }


        if(activePokemon1.dexNumber == 77)
        {
            img1.setImageResource(R.drawable.a77);
        }


        if(activePokemon1.dexNumber == 78)
        {
            img1.setImageResource(R.drawable.a78);
        }


        if(activePokemon1.dexNumber == 79)
        {
            img1.setImageResource(R.drawable.a79);
        }


        if(activePokemon1.dexNumber == 80)
        {
            img1.setImageResource(R.drawable.a80);
        }


        if(activePokemon1.dexNumber == 81)
        {
            img1.setImageResource(R.drawable.a81);
        }


        if(activePokemon1.dexNumber == 82)
        {
            img1.setImageResource(R.drawable.a82);
        }


        if(activePokemon1.dexNumber == 83)
        {
            img1.setImageResource(R.drawable.a83);
        }


        if(activePokemon1.dexNumber == 84)
        {
            img1.setImageResource(R.drawable.a84);
        }


        if(activePokemon1.dexNumber == 85)
        {
            img1.setImageResource(R.drawable.a85);
        }


        if(activePokemon1.dexNumber == 86)
        {
            img1.setImageResource(R.drawable.a86);
        }

        if(activePokemon1.dexNumber == 87)
        {
            img1.setImageResource(R.drawable.a87);
        }


        if(activePokemon1.dexNumber == 88)
        {
            img1.setImageResource(R.drawable.a88);
        }


        if(activePokemon1.dexNumber == 89)
        {
            img1.setImageResource(R.drawable.a89);
        }


        if(activePokemon1.dexNumber == 90)
        {
            img1.setImageResource(R.drawable.a90);
        }

        if(activePokemon1.dexNumber == 91)
        {
            img1.setImageResource(R.drawable.a91);
        }


        if(activePokemon1.dexNumber == 92)
        {
            img1.setImageResource(R.drawable.a92);
        }


        if(activePokemon1.dexNumber == 93)
        {
            img1.setImageResource(R.drawable.a93);
        }


        if(activePokemon1.dexNumber == 94)
        {
            img1.setImageResource(R.drawable.a94);
        }


        if(activePokemon1.dexNumber == 95)
        {
            img1.setImageResource(R.drawable.a95);
        }


        if(activePokemon1.dexNumber == 96)
        {
            img1.setImageResource(R.drawable.a96);
        }


        if(activePokemon1.dexNumber == 97)
        {
            img1.setImageResource(R.drawable.a97);
        }


        if(activePokemon1.dexNumber == 98)
        {
            img1.setImageResource(R.drawable.a98);
        }


        if(activePokemon1.dexNumber == 99)
        {
            img1.setImageResource(R.drawable.a99);
        }

    }

    public void gifStuffP2()
    {
        ImageView img2= (ImageView) findViewById(R.id.gifp2);

        if(activePokemon2.dexNumber == 1)
        {
            img2.setImageResource(R.drawable.a1);
        }

        if(activePokemon2.dexNumber == 2)
        {
            img2.setImageResource(R.drawable.a2);
        }


        if(activePokemon2.dexNumber == 3)
        {
            img2.setImageResource(R.drawable.a3);
        }


        if(activePokemon2.dexNumber == 4)
        {
            img2.setImageResource(R.drawable.a4);
        }

        if(activePokemon2.dexNumber == 5)
        {
            img2.setImageResource(R.drawable.a5);
        }


        if(activePokemon2.dexNumber == 6)
        {
            img2.setImageResource(R.drawable.a6);
        }


        if(activePokemon2.dexNumber == 7)
        {
            img2.setImageResource(R.drawable.a7);
        }

        if(activePokemon2.dexNumber == 8)
        {
            img2.setImageResource(R.drawable.a8);
        }


        if(activePokemon2.dexNumber == 9)
        {
            img2.setImageResource(R.drawable.a9);
        }


        if(activePokemon2.dexNumber == 10)
        {
            img2.setImageResource(R.drawable.a10);
        }

        if(activePokemon2.dexNumber == 11)
        {
            img2.setImageResource(R.drawable.a11);
        }


        if(activePokemon2.dexNumber == 12)
        {
            img2.setImageResource(R.drawable.a12);
        }


        if(activePokemon2.dexNumber == 13)
        {
            img2.setImageResource(R.drawable.a13);
        }


        if(activePokemon2.dexNumber == 14)
        {
            img2.setImageResource(R.drawable.a14);
        }


        if(activePokemon2.dexNumber == 15)
        {
            img2.setImageResource(R.drawable.a15);
        }


        if(activePokemon2.dexNumber == 16)
        {
            img2.setImageResource(R.drawable.a16);
        }


        if(activePokemon2.dexNumber == 17)
        {
            img2.setImageResource(R.drawable.a17);
        }


        if(activePokemon2.dexNumber == 18)
        {
            img2.setImageResource(R.drawable.a18);
        }


        if(activePokemon2.dexNumber == 19)
        {
            img2.setImageResource(R.drawable.a19);
        }


        if(activePokemon2.dexNumber == 20)
        {
            img2.setImageResource(R.drawable.a20);
        }


        if(activePokemon2.dexNumber == 21)
        {
            img2.setImageResource(R.drawable.a21);
        }


        if(activePokemon2.dexNumber == 22)
        {
            img2.setImageResource(R.drawable.a22);
        }


        if(activePokemon2.dexNumber == 23)
        {
            img2.setImageResource(R.drawable.a23);
        }


        if(activePokemon2.dexNumber == 24)
        {
            img2.setImageResource(R.drawable.a24);
        }

        if(activePokemon2.dexNumber == 25)
        {
            img2.setImageResource(R.drawable.a25);
        }


        if(activePokemon2.dexNumber == 26)
        {
            img2.setImageResource(R.drawable.a26);
        }


        if(activePokemon2.dexNumber == 27)
        {
            img2.setImageResource(R.drawable.a27);
        }


        if(activePokemon2.dexNumber == 28)
        {
            img2.setImageResource(R.drawable.a28);
        }


        if(activePokemon2.dexNumber == 29)
        {
            img2.setImageResource(R.drawable.a29);
        }

        if(activePokemon2.dexNumber == 30)
        {
            img2.setImageResource(R.drawable.a30);
        }


        if(activePokemon2.dexNumber == 31)
        {
            img2.setImageResource(R.drawable.a31);
        }

        if(activePokemon2.dexNumber == 32)
        {
            img2.setImageResource(R.drawable.a32);
        }


        if(activePokemon2.dexNumber == 33)
        {
            img2.setImageResource(R.drawable.a33);
        }


        if(activePokemon2.dexNumber == 34)
        {
            img2.setImageResource(R.drawable.a34);
        }


        if(activePokemon2.dexNumber == 35)
        {
            img2.setImageResource(R.drawable.a35);
        }


        if(activePokemon2.dexNumber == 36)
        {
            img2.setImageResource(R.drawable.a36);
        }

        if(activePokemon2.dexNumber == 37)
        {
            img2.setImageResource(R.drawable.a37);
        }


        if(activePokemon2.dexNumber == 38)
        {
            img2.setImageResource(R.drawable.a38);
        }

        if(activePokemon2.dexNumber == 39)
        {
            img2.setImageResource(R.drawable.a39);
        }


        if(activePokemon2.dexNumber == 40)
        {
            img2.setImageResource(R.drawable.a40);
        }


        if(activePokemon2.dexNumber == 41)
        {
            img2.setImageResource(R.drawable.a41);
        }

        if(activePokemon2.dexNumber == 42)
        {
            img2.setImageResource(R.drawable.a42);
        }


        if(activePokemon2.dexNumber == 43)
        {
            img2.setImageResource(R.drawable.a43);
        }


        if(activePokemon2.dexNumber == 44)
        {
            img2.setImageResource(R.drawable.a44);
        }

        if(activePokemon2.dexNumber == 45)
        {
            img2.setImageResource(R.drawable.a45);
        }


        if(activePokemon2.dexNumber == 46)
        {
            img2.setImageResource(R.drawable.a46);
        }


        if(activePokemon2.dexNumber == 47)
        {
            img2.setImageResource(R.drawable.a47);
        }


        if(activePokemon2.dexNumber == 48)
        {
            img2.setImageResource(R.drawable.a48);
        }


        if(activePokemon2.dexNumber == 49)
        {
            img2.setImageResource(R.drawable.a49);
        }


        if(activePokemon2.dexNumber == 50)
        {
            img2.setImageResource(R.drawable.a50);
        }

        if(activePokemon2.dexNumber == 51)
        {
            img2.setImageResource(R.drawable.a51);
        }

        if(activePokemon2.dexNumber == 52)
        {
            img2.setImageResource(R.drawable.a52);
        }

        if(activePokemon2.dexNumber == 53)
        {
            img2.setImageResource(R.drawable.a53);
        }


        if(activePokemon2.dexNumber == 54)
        {
            img2.setImageResource(R.drawable.a54);
        }


        if(activePokemon2.dexNumber == 55)
        {
            img2.setImageResource(R.drawable.a55);
        }


        if(activePokemon2.dexNumber == 56)
        {
            img2.setImageResource(R.drawable.a56);
        }


        if(activePokemon2.dexNumber == 57)
        {
            img2.setImageResource(R.drawable.a57);
        }


        if(activePokemon2.dexNumber == 58)
        {
            img2.setImageResource(R.drawable.a5);
        }

        if(activePokemon2.dexNumber == 59)
        {
            img2.setImageResource(R.drawable.a59);
        }


        if(activePokemon2.dexNumber == 60)
        {
            img2.setImageResource(R.drawable.a60);
        }

        if(activePokemon2.dexNumber == 61)
        {
            img2.setImageResource(R.drawable.a61);
        }


        if(activePokemon2.dexNumber == 62)
        {
            img2.setImageResource(R.drawable.a62);
        }


        if(activePokemon2.dexNumber == 63)
        {
            img2.setImageResource(R.drawable.a63);
        }


        if(activePokemon2.dexNumber == 64)
        {
            img2.setImageResource(R.drawable.a64);
        }

        if(activePokemon2.dexNumber == 65)
        {
            img2.setImageResource(R.drawable.a65);
        }

        if(activePokemon2.dexNumber == 66)
        {
            img2.setImageResource(R.drawable.a66);
        }

        if(activePokemon2.dexNumber == 67)
        {
            img2.setImageResource(R.drawable.a67);
        }


        if(activePokemon2.dexNumber == 68)
        {
            img2.setImageResource(R.drawable.a68);
        }

        if(activePokemon2.dexNumber == 69)
        {
            img2.setImageResource(R.drawable.a69);
        }


        if(activePokemon2.dexNumber == 70)
        {
            img2.setImageResource(R.drawable.a70);
        }

        if(activePokemon2.dexNumber == 71)
        {
            img2.setImageResource(R.drawable.a71);
        }


        if(activePokemon2.dexNumber == 72)
        {
            img2.setImageResource(R.drawable.a72);
        }


        if(activePokemon2.dexNumber == 73)
        {
            img2.setImageResource(R.drawable.a73);
        }


        if(activePokemon2.dexNumber == 74)
        {
            img2.setImageResource(R.drawable.a74);
        }

        if(activePokemon2.dexNumber == 75)
        {
            img2.setImageResource(R.drawable.a75);
        }


        if(activePokemon2.dexNumber == 76)
        {
            img2.setImageResource(R.drawable.a76);
        }


        if(activePokemon2.dexNumber == 77)
        {
            img2.setImageResource(R.drawable.a77);
        }


        if(activePokemon2.dexNumber == 78)
        {
            img2.setImageResource(R.drawable.a78);
        }


        if(activePokemon2.dexNumber == 79)
        {
            img2.setImageResource(R.drawable.a79);
        }


        if(activePokemon2.dexNumber == 80)
        {
            img2.setImageResource(R.drawable.a80);
        }


        if(activePokemon2.dexNumber == 81)
        {
            img2.setImageResource(R.drawable.a81);
        }


        if(activePokemon2.dexNumber == 82)
        {
            img2.setImageResource(R.drawable.a82);
        }


        if(activePokemon2.dexNumber == 83)
        {
            img2.setImageResource(R.drawable.a83);
        }


        if(activePokemon2.dexNumber == 84)
        {
            img2.setImageResource(R.drawable.a84);
        }


        if(activePokemon2.dexNumber == 85)
        {
            img2.setImageResource(R.drawable.a85);
        }


        if(activePokemon2.dexNumber == 86)
        {
            img2.setImageResource(R.drawable.a86);
        }

        if(activePokemon2.dexNumber == 87)
        {
            img2.setImageResource(R.drawable.a87);
        }


        if(activePokemon2.dexNumber == 88)
        {
            img2.setImageResource(R.drawable.a88);
        }


        if(activePokemon2.dexNumber == 89)
        {
            img2.setImageResource(R.drawable.a89);
        }


        if(activePokemon2.dexNumber == 90)
        {
            img2.setImageResource(R.drawable.a90);
        }

        if(activePokemon2.dexNumber == 91)
        {
            img2.setImageResource(R.drawable.a91);
        }


        if(activePokemon2.dexNumber == 92)
        {
            img2.setImageResource(R.drawable.a92);
        }


        if(activePokemon2.dexNumber == 93)
        {
            img2.setImageResource(R.drawable.a93);
        }


        if(activePokemon2.dexNumber == 94)
        {
            img2.setImageResource(R.drawable.a94);
        }


        if(activePokemon2.dexNumber == 95)
        {
            img2.setImageResource(R.drawable.a95);
        }


        if(activePokemon2.dexNumber == 96)
        {
            img2.setImageResource(R.drawable.a96);
        }


        if(activePokemon2.dexNumber == 97)
        {
            img2.setImageResource(R.drawable.a97);
        }


        if(activePokemon2.dexNumber == 98)
        {
            img2.setImageResource(R.drawable.a98);
        }


        if(activePokemon2.dexNumber == 99)
        {
            img2.setImageResource(R.drawable.a99);
        }

    }

    public void useMove1(View view)
    {
        stayingInApp=true;
        usedMove1 = activePokemon1.move1;
        p1Switch = false;
        if(isP2CPU)
        {
            CPUStuff();
            Intent intent = new Intent(this, GraphicActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Battle2Activity.class);
            startActivity(intent);
        }

    }

    public void useMove2(View view)
    {
        stayingInApp=true;
        usedMove1 = activePokemon1.move2;
        p1Switch = false;
        if(isP2CPU)
        {
            CPUStuff();
            Intent intent = new Intent(this, GraphicActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Battle2Activity.class);
            startActivity(intent);
        }

    }
    public void useMove3(View view)
    {
        stayingInApp=true;
        usedMove1 = activePokemon1.move3;
        p1Switch = false;
        if(isP2CPU)
        {
            CPUStuff();
            Intent intent = new Intent(this, GraphicActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Battle2Activity.class);
            startActivity(intent);
        }

    }
    public void useMove4(View view)
    {
        stayingInApp=true;
        usedMove1 = activePokemon1.move4;
        p1Switch = false;
        if(isP2CPU)
        {
            CPUStuff();
            Intent intent = new Intent(this, GraphicActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Battle2Activity.class);
            startActivity(intent);
        }

    }

    public void switch1(View view)
    {
        stayingInApp=true;
        p1Switch = true;
        p1Pokemon[0].resetPokemon();
        activePokemon1 = p1Pokemon[0];
        if(isP2CPU)
        {
            CPUStuff();
            Intent intent = new Intent(this, GraphicActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Battle2Activity.class);
            startActivity(intent);
        }

    }
    public void switch2(View view)
    {
        stayingInApp=true;
        p1Switch = true;
        p1Pokemon[1].resetPokemon();
        activePokemon1 = p1Pokemon[1];
        if(isP2CPU)
        {
            CPUStuff();
            Intent intent = new Intent(this, GraphicActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Battle2Activity.class);
            startActivity(intent);
        }

    }
    public void switch3(View view)
    {
        stayingInApp=true;
        p1Switch = true;
        p1Pokemon[2].resetPokemon();
        activePokemon1 = p1Pokemon[2];
        if(isP2CPU)
        {
            CPUStuff();
            Intent intent = new Intent(this, GraphicActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Battle2Activity.class);
            startActivity(intent);
        }

    }
    public void switch4(View view)
    {
        stayingInApp=true;
        p1Switch = true;
        p1Pokemon[3].resetPokemon();
        activePokemon1 = p1Pokemon[3];
        if(isP2CPU)
        {
            CPUStuff();
            Intent intent = new Intent(this, GraphicActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Battle2Activity.class);
            startActivity(intent);
        }

    }
    public void switch5(View view)
    {
        stayingInApp=true;
        p1Switch = true;
        p1Pokemon[4].resetPokemon();
        activePokemon1 = p1Pokemon[4];
        if(isP2CPU)
        {
            CPUStuff();
            Intent intent = new Intent(this, GraphicActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Battle2Activity.class);
            startActivity(intent);
        }

    }
    public void switch6(View view)
    {
        stayingInApp=true;
        p1Switch = true;
        p1Pokemon[5].resetPokemon();
        activePokemon1 = p1Pokemon[5];
        if(isP2CPU)
        {
            CPUStuff();
            Intent intent = new Intent(this, GraphicActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Battle2Activity.class);
            startActivity(intent);
        }

    }

    public  void getActivePokemon1Info(View view)
    {
        TextView name1 = (TextView) findViewById(R.id.table1Name);
        TextView ability1 = (TextView) findViewById(R.id.table1Ability);
        TextView atk1 = (TextView) findViewById(R.id.table1Atk);
        TextView def1 = (TextView) findViewById(R.id.table1Def);
        TextView spatk1 = (TextView) findViewById(R.id.table1SpAtk);
        TextView spdef1 = (TextView) findViewById(R.id.table1SpDef);
        TextView spd1 = (TextView) findViewById(R.id.table1Spd);
        Button close = (Button) findViewById(R.id.table1CloseBtn);
        TableLayout t1 = (TableLayout) findViewById(R.id.table1);


        String nameMsg = "" + activePokemon1.name.toUpperCase();
        name1.setText(nameMsg);

        String abilityMsg = "Ability: " + activePokemon1.ability.substring(0, 1).toUpperCase() + activePokemon1.ability.substring(1);
        ability1.setText(abilityMsg);

        if(activePokemon1.attack> activePokemon1.origAttack)
        {
            atk1.setTextColor(Color.parseColor("#66BB6A"));
        }
        if(activePokemon1.attack <activePokemon1.origAttack)
        {
            atk1.setTextColor(Color.parseColor("#D32F2F"));
        }

        String atkMsg = "Attack: " + activePokemon1.attack;
        atk1.setText(atkMsg);

        if(activePokemon1.defense> activePokemon1.origDefense)
        {
            def1.setTextColor(Color.parseColor("#66BB6A"));
        }
        if(activePokemon1.defense <activePokemon1.origDefense)
        {
            def1.setTextColor(Color.parseColor("#D32F2F"));
        }

        String defMsg = "Defense: " + activePokemon1.defense;
        def1.setText(defMsg);

        if(activePokemon1.spattack> activePokemon1.origSpattack)
        {
            spatk1.setTextColor(Color.parseColor("#66BB6A"));
        }
        if(activePokemon1.spattack <activePokemon1.origSpattack)
        {
            spatk1.setTextColor(Color.parseColor("#D32F2F"));
        }

        String spatkMsg = "Sp Attack: " + activePokemon1.spattack;
        spatk1.setText(spatkMsg);

        if(activePokemon1.spdefense> activePokemon1.origSpdefense)
        {
            spdef1.setTextColor(Color.parseColor("#66BB6A"));
        }
        if(activePokemon1.spdefense <activePokemon1.origSpdefense)
        {
            spdef1.setTextColor(Color.parseColor("#D32F2F"));
        }

        String spdefMsg = "Sp Defense: " + activePokemon1.spdefense;
        spdef1.setText(spdefMsg);

        if(activePokemon1.speed> activePokemon1.origSpeed)
        {
            spd1.setTextColor(Color.parseColor("#66BB6A"));
        }
        if(activePokemon1.speed <activePokemon1.origSpeed)
        {
            spd1.setTextColor(Color.parseColor("#D32F2F"));
        }

        String spd1Msg = "Speed: " + activePokemon1.speed;
        spd1.setText(spd1Msg);

        t1.setVisibility(View.VISIBLE);
        close.setVisibility(View.VISIBLE);
        close.setClickable(true);

    }

    public void getPokemon1Info()
    {
        final TextView name1 = (TextView) findViewById(R.id.table1Name);
        final TextView ability1 = (TextView) findViewById(R.id.table1Ability);
        final TextView atk1 = (TextView) findViewById(R.id.table1Atk);
        final TextView def1 = (TextView) findViewById(R.id.table1Def);
        final TextView spatk1 = (TextView) findViewById(R.id.table1SpAtk);
        final TextView spdef1 = (TextView) findViewById(R.id.table1SpDef);
        final TextView spd1 = (TextView) findViewById(R.id.table1Spd);
        final Button close = (Button) findViewById(R.id.table1CloseBtn);
        final TableLayout t1 = (TableLayout) findViewById(R.id.table1);
        final Button b1 = (Button)findViewById(R.id.pokemon1_1battle);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            close.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            switch1(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        String nameMsg = "";
                        if(p1Pokemon[0].type2>0)
                        {
                            nameMsg = p1Pokemon[0].name.toUpperCase() + ": (" + typeToString(p1Pokemon[0].type1) + "-" + typeToString(p1Pokemon[0].type2) + ")";
                        }
                        else
                        {
                            nameMsg = p1Pokemon[0].name.toUpperCase() + ": (" + typeToString(p1Pokemon[0].type1) + ")";
                        }

                        name1.setText(nameMsg);

                        String abilityMsg = "Ability: " + p1Pokemon[0].ability.substring(0, 1).toUpperCase() + p1Pokemon[0].ability.substring(1);
                        ability1.setText(abilityMsg);

                        if(p1Pokemon[0].attack> p1Pokemon[0].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[0].attack <p1Pokemon[0].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String atkMsg = "Attack: " + p1Pokemon[0].attack;
                        atk1.setText(atkMsg);

                        if(p1Pokemon[0].defense> p1Pokemon[0].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[0].defense <p1Pokemon[0].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String defMsg = "Defense: " + p1Pokemon[0].defense;
                        def1.setText(defMsg);

                        if(p1Pokemon[0].spattack> p1Pokemon[0].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[0].spattack <p1Pokemon[0].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spatkMsg = "Sp Attack: " + p1Pokemon[0].spattack;
                        spatk1.setText(spatkMsg);

                        if(p1Pokemon[0].spdefense> p1Pokemon[0].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[0].spdefense <p1Pokemon[0].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spdefMsg = "Sp Defense: " + p1Pokemon[0].spdefense;
                        spdef1.setText(spdefMsg);

                        if(p1Pokemon[0].speed> p1Pokemon[0].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[0].speed <p1Pokemon[0].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spd1Msg = "Speed: " + p1Pokemon[0].speed;
                        spd1.setText(spd1Msg);
                        break;
                }
                return false;
            }
        });

    }

    public void getPokemon2Info()
    {
        final TextView name1 = (TextView) findViewById(R.id.table1Name);
        final TextView ability1 = (TextView) findViewById(R.id.table1Ability);
        final TextView atk1 = (TextView) findViewById(R.id.table1Atk);
        final TextView def1 = (TextView) findViewById(R.id.table1Def);
        final TextView spatk1 = (TextView) findViewById(R.id.table1SpAtk);
        final TextView spdef1 = (TextView) findViewById(R.id.table1SpDef);
        final TextView spd1 = (TextView) findViewById(R.id.table1Spd);
        final Button close = (Button) findViewById(R.id.table1CloseBtn);
        final TableLayout t1 = (TableLayout) findViewById(R.id.table1);
        final Button b1 = (Button)findViewById(R.id.pokemon2_1battle);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            close.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            switch2(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        String nameMsg = "";
                        if(p1Pokemon[1].type2>0)
                        {
                            nameMsg = p1Pokemon[1].name.toUpperCase() + ": (" + typeToString(p1Pokemon[1].type1) + "-" + typeToString(p1Pokemon[1].type2) + ")";
                        }
                        else
                        {
                            nameMsg = p1Pokemon[1].name.toUpperCase() + ": (" + typeToString(p1Pokemon[1].type1) + ")";
                        }

                        name1.setText(nameMsg);

                        String abilityMsg = "Ability: " + p1Pokemon[1].ability.substring(0, 1).toUpperCase() + p1Pokemon[1].ability.substring(1);
                        ability1.setText(abilityMsg);

                        if(p1Pokemon[1].attack> p1Pokemon[1].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[1].attack <p1Pokemon[1].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String atkMsg = "Attack: " + p1Pokemon[1].attack;
                        atk1.setText(atkMsg);

                        if(p1Pokemon[1].defense> p1Pokemon[1].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[1].defense <p1Pokemon[1].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String defMsg = "Defense: " + p1Pokemon[1].defense;
                        def1.setText(defMsg);

                        if(p1Pokemon[1].spattack> p1Pokemon[1].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[1].spattack <p1Pokemon[1].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spatkMsg = "Sp Attack: " + p1Pokemon[1].spattack;
                        spatk1.setText(spatkMsg);

                        if(p1Pokemon[1].spdefense> p1Pokemon[1].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[1].spdefense <p1Pokemon[1].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spdefMsg = "Sp Defense: " + p1Pokemon[1].spdefense;
                        spdef1.setText(spdefMsg);

                        if(p1Pokemon[1].speed> p1Pokemon[1].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[1].speed <p1Pokemon[1].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spd1Msg = "Speed: " + p1Pokemon[1].speed;
                        spd1.setText(spd1Msg);
                        break;
                }
                return false;
            }
        });

    }

    public void getPokemon3Info()
    {
        final TextView name1 = (TextView) findViewById(R.id.table1Name);
        final TextView ability1 = (TextView) findViewById(R.id.table1Ability);
        final TextView atk1 = (TextView) findViewById(R.id.table1Atk);
        final TextView def1 = (TextView) findViewById(R.id.table1Def);
        final TextView spatk1 = (TextView) findViewById(R.id.table1SpAtk);
        final TextView spdef1 = (TextView) findViewById(R.id.table1SpDef);
        final TextView spd1 = (TextView) findViewById(R.id.table1Spd);
        final Button close = (Button) findViewById(R.id.table1CloseBtn);
        final TableLayout t1 = (TableLayout) findViewById(R.id.table1);
        final Button b1 = (Button)findViewById(R.id.pokemon3_1battle);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            close.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            switch3(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        String nameMsg = "";
                        if(p1Pokemon[2].type2>0)
                        {
                            nameMsg = p1Pokemon[2].name.toUpperCase() + ": (" + typeToString(p1Pokemon[2].type1) + "-" + typeToString(p1Pokemon[2].type2) + ")";
                        }
                        else
                        {
                            nameMsg = p1Pokemon[2].name.toUpperCase() + ": (" + typeToString(p1Pokemon[2].type1) + ")";
                        }

                        name1.setText(nameMsg);

                        String abilityMsg = "Ability: " + p1Pokemon[2].ability.substring(0, 1).toUpperCase() + p1Pokemon[2].ability.substring(1);
                        ability1.setText(abilityMsg);

                        if(p1Pokemon[2].attack> p1Pokemon[2].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[2].attack <p1Pokemon[2].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String atkMsg = "Attack: " + p1Pokemon[2].attack;
                        atk1.setText(atkMsg);

                        if(p1Pokemon[2].defense> p1Pokemon[2].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[2].defense <p1Pokemon[2].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String defMsg = "Defense: " + p1Pokemon[2].defense;
                        def1.setText(defMsg);

                        if(p1Pokemon[2].spattack> p1Pokemon[2].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[2].spattack <p1Pokemon[2].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spatkMsg = "Sp Attack: " + p1Pokemon[2].spattack;
                        spatk1.setText(spatkMsg);

                        if(p1Pokemon[2].spdefense> p1Pokemon[2].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[2].spdefense <p1Pokemon[2].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spdefMsg = "Sp Defense: " + p1Pokemon[2].spdefense;
                        spdef1.setText(spdefMsg);

                        if(p1Pokemon[2].speed> p1Pokemon[2].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[2].speed <p1Pokemon[2].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spd1Msg = "Speed: " + p1Pokemon[2].speed;
                        spd1.setText(spd1Msg);
                        break;
                }
                return false;
            }
        });

    }

    public void getPokemon4Info()
    {
        final TextView name1 = (TextView) findViewById(R.id.table1Name);
        final TextView ability1 = (TextView) findViewById(R.id.table1Ability);
        final TextView atk1 = (TextView) findViewById(R.id.table1Atk);
        final TextView def1 = (TextView) findViewById(R.id.table1Def);
        final TextView spatk1 = (TextView) findViewById(R.id.table1SpAtk);
        final TextView spdef1 = (TextView) findViewById(R.id.table1SpDef);
        final TextView spd1 = (TextView) findViewById(R.id.table1Spd);
        final Button close = (Button) findViewById(R.id.table1CloseBtn);
        final TableLayout t1 = (TableLayout) findViewById(R.id.table1);
        final Button b1 = (Button)findViewById(R.id.pokemon4_1battle);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            close.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            switch4(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        String nameMsg = "";
                        if(p1Pokemon[3].type2>0)
                        {
                            nameMsg = p1Pokemon[3].name.toUpperCase() + ": (" + typeToString(p1Pokemon[3].type1) + "-" + typeToString(p1Pokemon[3].type2) + ")";
                        }
                        else
                        {
                            nameMsg = p1Pokemon[3].name.toUpperCase() + ": (" + typeToString(p1Pokemon[3].type1) + ")";
                        }

                        name1.setText(nameMsg);

                        String abilityMsg = "Ability: " + p1Pokemon[3].ability.substring(0, 1).toUpperCase() + p1Pokemon[3].ability.substring(1);
                        ability1.setText(abilityMsg);

                        if(p1Pokemon[3].attack> p1Pokemon[3].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[3].attack <p1Pokemon[3].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String atkMsg = "Attack: " + p1Pokemon[3].attack;
                        atk1.setText(atkMsg);

                        if(p1Pokemon[3].defense> p1Pokemon[3].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[3].defense <p1Pokemon[3].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String defMsg = "Defense: " + p1Pokemon[3].defense;
                        def1.setText(defMsg);

                        if(p1Pokemon[3].spattack> p1Pokemon[3].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[3].spattack <p1Pokemon[3].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spatkMsg = "Sp Attack: " + p1Pokemon[3].spattack;
                        spatk1.setText(spatkMsg);

                        if(p1Pokemon[3].spdefense> p1Pokemon[3].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[3].spdefense <p1Pokemon[3].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spdefMsg = "Sp Defense: " + p1Pokemon[3].spdefense;
                        spdef1.setText(spdefMsg);

                        if(p1Pokemon[3].speed> p1Pokemon[3].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[3].speed <p1Pokemon[3].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spd1Msg = "Speed: " + p1Pokemon[3].speed;
                        spd1.setText(spd1Msg);
                        break;
                }
                return false;
            }
        });

    }

    public void getPokemon5Info()
    {
        final TextView name1 = (TextView) findViewById(R.id.table1Name);
        final TextView ability1 = (TextView) findViewById(R.id.table1Ability);
        final TextView atk1 = (TextView) findViewById(R.id.table1Atk);
        final TextView def1 = (TextView) findViewById(R.id.table1Def);
        final TextView spatk1 = (TextView) findViewById(R.id.table1SpAtk);
        final TextView spdef1 = (TextView) findViewById(R.id.table1SpDef);
        final TextView spd1 = (TextView) findViewById(R.id.table1Spd);
        final Button close = (Button) findViewById(R.id.table1CloseBtn);
        final TableLayout t1 = (TableLayout) findViewById(R.id.table1);
        final Button b1 = (Button)findViewById(R.id.pokemon5_1battle);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            close.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            switch5(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        String nameMsg = "";
                        if(p1Pokemon[4].type2>0)
                        {
                            nameMsg = p1Pokemon[4].name.toUpperCase() + ": (" + typeToString(p1Pokemon[4].type1) + "-" + typeToString(p1Pokemon[4].type2) + ")";
                        }
                        else
                        {
                            nameMsg = p1Pokemon[4].name.toUpperCase() + ": (" + typeToString(p1Pokemon[4].type1) + ")";
                        }

                        name1.setText(nameMsg);

                        String abilityMsg = "Ability: " + p1Pokemon[4].ability.substring(0, 1).toUpperCase() + p1Pokemon[4].ability.substring(1);
                        ability1.setText(abilityMsg);

                        if(p1Pokemon[4].attack> p1Pokemon[4].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[4].attack <p1Pokemon[4].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String atkMsg = "Attack: " + p1Pokemon[4].attack;
                        atk1.setText(atkMsg);

                        if(p1Pokemon[4].defense> p1Pokemon[4].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[4].defense <p1Pokemon[4].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String defMsg = "Defense: " + p1Pokemon[4].defense;
                        def1.setText(defMsg);

                        if(p1Pokemon[4].spattack> p1Pokemon[4].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[4].spattack <p1Pokemon[4].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spatkMsg = "Sp Attack: " + p1Pokemon[4].spattack;
                        spatk1.setText(spatkMsg);

                        if(p1Pokemon[4].spdefense> p1Pokemon[4].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[4].spdefense <p1Pokemon[4].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spdefMsg = "Sp Defense: " + p1Pokemon[4].spdefense;
                        spdef1.setText(spdefMsg);

                        if(p1Pokemon[4].speed> p1Pokemon[4].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[4].speed <p1Pokemon[4].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spd1Msg = "Speed: " + p1Pokemon[4].speed;
                        spd1.setText(spd1Msg);
                        break;
                }
                return false;
            }
        });

    }

    public void getPokemon6Info()
    {
        final TextView name1 = (TextView) findViewById(R.id.table1Name);
        final TextView ability1 = (TextView) findViewById(R.id.table1Ability);
        final TextView atk1 = (TextView) findViewById(R.id.table1Atk);
        final TextView def1 = (TextView) findViewById(R.id.table1Def);
        final TextView spatk1 = (TextView) findViewById(R.id.table1SpAtk);
        final TextView spdef1 = (TextView) findViewById(R.id.table1SpDef);
        final TextView spd1 = (TextView) findViewById(R.id.table1Spd);
        final Button close = (Button) findViewById(R.id.table1CloseBtn);
        final TableLayout t1 = (TableLayout) findViewById(R.id.table1);
        final Button b1 = (Button)findViewById(R.id.pokemon6_1battle);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            close.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            switch6(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        String nameMsg = "";
                        if(p1Pokemon[5].type2>0)
                        {
                            nameMsg = p1Pokemon[5].name.toUpperCase() + ": (" + typeToString(p1Pokemon[5].type1) + "-" + typeToString(p1Pokemon[5].type2) + ")";
                        }
                        else
                        {
                            nameMsg = p1Pokemon[5].name.toUpperCase() + ": (" + typeToString(p1Pokemon[5].type1) + ")";
                        }

                        name1.setText(nameMsg);

                        String abilityMsg = "Ability: " + p1Pokemon[5].ability.substring(0, 1).toUpperCase() + p1Pokemon[5].ability.substring(1);
                        ability1.setText(abilityMsg);

                        if(p1Pokemon[5].attack> p1Pokemon[5].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[5].attack <p1Pokemon[5].origAttack)
                        {
                            atk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String atkMsg = "Attack: " + p1Pokemon[5].attack;
                        atk1.setText(atkMsg);

                        if(p1Pokemon[5].defense> p1Pokemon[5].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[5].defense <p1Pokemon[5].origDefense)
                        {
                            def1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String defMsg = "Defense: " + p1Pokemon[5].defense;
                        def1.setText(defMsg);

                        if(p1Pokemon[5].spattack> p1Pokemon[5].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[5].spattack <p1Pokemon[5].origSpattack)
                        {
                            spatk1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spatkMsg = "Sp Attack: " + p1Pokemon[5].spattack;
                        spatk1.setText(spatkMsg);

                        if(p1Pokemon[5].spdefense> p1Pokemon[5].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[5].spdefense <p1Pokemon[5].origSpdefense)
                        {
                            spdef1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spdefMsg = "Sp Defense: " + p1Pokemon[5].spdefense;
                        spdef1.setText(spdefMsg);

                        if(p1Pokemon[5].speed> p1Pokemon[5].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#66BB6A"));
                        }
                        if(p1Pokemon[5].speed <p1Pokemon[5].origSpeed)
                        {
                            spd1.setTextColor(Color.parseColor("#D32F2F"));
                        }

                        String spd1Msg = "Speed: " + p1Pokemon[5].speed;
                        spd1.setText(spd1Msg);
                        break;
                }
                return false;
            }
        });

    }

    public void closeTable1(View view)
    {
        TableLayout t1 = (TableLayout) findViewById(R.id.table1);
        Button close = (Button) findViewById(R.id.table1CloseBtn);

        TransitionManager.beginDelayedTransition(gregsLayout);

        t1.setVisibility(View.INVISIBLE);
        close.setVisibility(View.INVISIBLE);
        close.setClickable(false);
    }

    public void getMoveBackgroundColors1()
    {
        Button b1 = (Button)findViewById(R.id.p1move1);
        Button b2 = (Button)findViewById(R.id.p1move2);
        Button b3 = (Button)findViewById(R.id.p1move3);
        Button b4 = (Button)findViewById(R.id.p1move4);

        switch(activePokemon1.move1.type)
        {
            case 1:
                b1.setBackgroundResource(R.drawable.button_border_normal);
                break;
            case 2:
                b1.setBackgroundResource(R.drawable.button_border_fire);
                break;
            case 3:
                b1.setBackgroundResource(R.drawable.button_border_water);
                break;
            case 4:
                b1.setBackgroundResource(R.drawable.button_border_electric);
                break;
            case 5:
                b1.setBackgroundResource(R.drawable.button_border_grass);
                break;
            case 6:
                b1.setBackgroundResource(R.drawable.button_border_ice);
                break;
            case 7:
                b1.setBackgroundResource(R.drawable.button_border_fighting);
                break;
            case 8:
                b1.setBackgroundResource(R.drawable.button_border_poison);
                break;
            case 9:
                b1.setBackgroundResource(R.drawable.button_border_ground);
                break;
            case 10:
                b1.setBackgroundResource(R.drawable.button_border_flying);
                break;
            case 11:
                b1.setBackgroundResource(R.drawable.button_border_psychic);
                break;
            case 12:
                b1.setBackgroundResource(R.drawable.button_border_bug);
                break;
            case 13:
                b1.setBackgroundResource(R.drawable.button_border_rock);
                break;
            case 14:
                b1.setBackgroundResource(R.drawable.button_border_ghost);
                break;
            case 15:
                b1.setBackgroundResource(R.drawable.button_border_dragon);
                b1.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 16:
                b1.setBackgroundResource(R.drawable.button_border_dark);
                b1.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 17:
                b1.setBackgroundResource(R.drawable.button_border_steel);
                break;
            case 18:
                b1.setBackgroundResource(R.drawable.button_border_fairy);
                break;
        }
        switch(activePokemon1.move2.type)
        {
            case 1:
                b2.setBackgroundResource(R.drawable.button_border_normal);
                break;
            case 2:
                b2.setBackgroundResource(R.drawable.button_border_fire);
                break;
            case 3:
                b2.setBackgroundResource(R.drawable.button_border_water);
                break;
            case 4:
                b2.setBackgroundResource(R.drawable.button_border_electric);
                break;
            case 5:
                b2.setBackgroundResource(R.drawable.button_border_grass);
                break;
            case 6:
                b2.setBackgroundResource(R.drawable.button_border_ice);
                break;
            case 7:
                b2.setBackgroundResource(R.drawable.button_border_fighting);
                break;
            case 8:
                b2.setBackgroundResource(R.drawable.button_border_poison);
                break;
            case 9:
                b2.setBackgroundResource(R.drawable.button_border_ground);
                break;
            case 10:
                b2.setBackgroundResource(R.drawable.button_border_flying);
                break;
            case 11:
                b2.setBackgroundResource(R.drawable.button_border_psychic);
                break;
            case 12:
                b2.setBackgroundResource(R.drawable.button_border_bug);
                break;
            case 13:
                b2.setBackgroundResource(R.drawable.button_border_rock);
                break;
            case 14:
                b2.setBackgroundResource(R.drawable.button_border_ghost);
                break;
            case 15:
                b2.setBackgroundResource(R.drawable.button_border_dragon);
                b2.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 16:
                b2.setBackgroundResource(R.drawable.button_border_dark);
                b2.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 17:
                b2.setBackgroundResource(R.drawable.button_border_steel);
                break;
            case 18:
                b2.setBackgroundResource(R.drawable.button_border_fairy);
                break;
        }
        switch(activePokemon1.move3.type)
        {
            case 1:
                b3.setBackgroundResource(R.drawable.button_border_normal);
                break;
            case 2:
                b3.setBackgroundResource(R.drawable.button_border_fire);
                break;
            case 3:
                b3.setBackgroundResource(R.drawable.button_border_water);
                break;
            case 4:
                b3.setBackgroundResource(R.drawable.button_border_electric);
                break;
            case 5:
                b3.setBackgroundResource(R.drawable.button_border_grass);
                break;
            case 6:
                b3.setBackgroundResource(R.drawable.button_border_ice);
                break;
            case 7:
                b3.setBackgroundResource(R.drawable.button_border_fighting);
                break;
            case 8:
                b3.setBackgroundResource(R.drawable.button_border_poison);
                break;
            case 9:
                b3.setBackgroundResource(R.drawable.button_border_ground);
                break;
            case 10:
                b3.setBackgroundResource(R.drawable.button_border_flying);
                break;
            case 11:
                b3.setBackgroundResource(R.drawable.button_border_psychic);
                break;
            case 12:
                b3.setBackgroundResource(R.drawable.button_border_bug);
                break;
            case 13:
                b3.setBackgroundResource(R.drawable.button_border_rock);
                break;
            case 14:
                b3.setBackgroundResource(R.drawable.button_border_ghost);
                break;
            case 15:
                b3.setBackgroundResource(R.drawable.button_border_dragon);
                b3.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 16:
                b3.setBackgroundResource(R.drawable.button_border_dark);
                b3.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 17:
                b3.setBackgroundResource(R.drawable.button_border_steel);
                break;
            case 18:
                b3.setBackgroundResource(R.drawable.button_border_fairy);
                break;
        }
        switch(activePokemon1.move4.type)
        {
            case 1:
                b4.setBackgroundResource(R.drawable.button_border_normal);
                break;
            case 2:
                b4.setBackgroundResource(R.drawable.button_border_fire);
                break;
            case 3:
                b4.setBackgroundResource(R.drawable.button_border_water);
                break;
            case 4:
                b4.setBackgroundResource(R.drawable.button_border_electric);
                break;
            case 5:
                b4.setBackgroundResource(R.drawable.button_border_grass);
                break;
            case 6:
                b4.setBackgroundResource(R.drawable.button_border_ice);
                break;
            case 7:
                b4.setBackgroundResource(R.drawable.button_border_fighting);
                break;
            case 8:
                b4.setBackgroundResource(R.drawable.button_border_poison);
                break;
            case 9:
                b4.setBackgroundResource(R.drawable.button_border_ground);
                break;
            case 10:
                b4.setBackgroundResource(R.drawable.button_border_flying);
                break;
            case 11:
                b4.setBackgroundResource(R.drawable.button_border_psychic);
                break;
            case 12:
                b4.setBackgroundResource(R.drawable.button_border_bug);
                break;
            case 13:
                b4.setBackgroundResource(R.drawable.button_border_rock);
                break;
            case 14:
                b4.setBackgroundResource(R.drawable.button_border_ghost);
                break;
            case 15:
                b4.setBackgroundResource(R.drawable.button_border_dragon);
                b4.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 16:
                b4.setBackgroundResource(R.drawable.button_border_dark);
                b4.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 17:
                b4.setBackgroundResource(R.drawable.button_border_steel);
                break;
            case 18:
                b4.setBackgroundResource(R.drawable.button_border_fairy);
                break;
        }
    }

    public static String getTypeColor(int type)
    {
        switch (type)
        {
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

    public String typeToString(int type)
    {
        switch (type)
        {
            case 1:
                return "Normal";
            case 2:
                return "Fire";
            case 3:
                return "Water";
            case 4:
                return "Electric";
            case 5:
                return "Grass";
            case 6:
                return "Ice";
            case 7:
                return "Fighting";
            case 8:
                return "Poison";
            case 9:
                return "Ground";
            case 10:
                return "Flying";
            case 11:
                return "Psychic";
            case 12:
                return "Bug";
            case 13:
                return "Rock";
            case 14:
                return "Ghost";
            case 15:
                return "Dragon";
            case 16:
                return "Dark";
            case 17:
                return "Steel";
            case 18:
                return "Fairy";
        }
        return "";
    }

    public String categoryToString(int category)
    {
        switch (category)
        {
            case 1:
                return "Physical";
            case 2:
                return "Special";
            case 3:
                return "Status";
            default:
                return "Wtf";
        }
    }

    public void setTable1Type1Img()
    {
        ImageView type1Img = (ImageView) findViewById(R.id.table1Type1Img);
        switch (activePokemon1.type1)
        {
            case 1:
                type1Img.setImageResource(R.drawable.normal);
                break;
            case 2:
                type1Img.setImageResource(R.drawable.fire);
                break;
            case 3:
                type1Img.setImageResource(R.drawable.water);
                break;
            case 4:
                type1Img.setImageResource(R.drawable.electric);
                break;
            case 5:
                type1Img.setImageResource(R.drawable.grass);
                break;
            case 6:
                type1Img.setImageResource(R.drawable.ice);
                break;
            case 7:
                type1Img.setImageResource(R.drawable.fighting);
                break;
            case 8:
                type1Img.setImageResource(R.drawable.poison);
                break;
            case 9:
                type1Img.setImageResource(R.drawable.ground);
                break;
            case 10:
                type1Img.setImageResource(R.drawable.flying);
                break;
            case 11:
                type1Img.setImageResource(R.drawable.psychic);
                break;
            case 12:
                type1Img.setImageResource(R.drawable.bug);
                break;
            case 13:
                type1Img.setImageResource(R.drawable.rock);
                break;
            case 14:
                type1Img.setImageResource(R.drawable.ghost);
                break;
            case 15:
                type1Img.setImageResource(R.drawable.dragon);
                break;
            case 16:
                type1Img.setImageResource(R.drawable.dark);
                break;
            case 17:
                type1Img.setImageResource(R.drawable.steel);
                break;
            case 18:
                type1Img.setImageResource(R.drawable.fairy);
                break;
        }
    }

    public void setTable1Type2Img()
    {
        ImageView type2Img = (ImageView) findViewById(R.id.table1Type2Img);
        switch (activePokemon1.type2)
        {
            case 1:
                type2Img.setImageResource(R.drawable.normal);
                break;
            case 2:
                type2Img.setImageResource(R.drawable.fire);
                break;
            case 3:
                type2Img.setImageResource(R.drawable.water);
                break;
            case 4:
                type2Img.setImageResource(R.drawable.electric);
                break;
            case 5:
                type2Img.setImageResource(R.drawable.grass);
                break;
            case 6:
                type2Img.setImageResource(R.drawable.ice);
                break;
            case 7:
                type2Img.setImageResource(R.drawable.fighting);
                break;
            case 8:
                type2Img.setImageResource(R.drawable.poison);
                break;
            case 9:
                type2Img.setImageResource(R.drawable.ground);
                break;
            case 10:
                type2Img.setImageResource(R.drawable.flying);
                break;
            case 11:
                type2Img.setImageResource(R.drawable.psychic);
                break;
            case 12:
                type2Img.setImageResource(R.drawable.bug);
                break;
            case 13:
                type2Img.setImageResource(R.drawable.rock);
                break;
            case 14:
                type2Img.setImageResource(R.drawable.ghost);
                break;
            case 15:
                type2Img.setImageResource(R.drawable.dragon);
                break;
            case 16:
                type2Img.setImageResource(R.drawable.dark);
                break;
            case 17:
                type2Img.setImageResource(R.drawable.steel);
                break;
            case 18:
                type2Img.setImageResource(R.drawable.fairy);
                break;
        }
    }

    public void setTable2Type1Img()
    {
        ImageView type1Img = (ImageView) findViewById(R.id.table2Type1Img);
        switch (activePokemon2.type1)
        {
            case 1:
                type1Img.setImageResource(R.drawable.normal);
                break;
            case 2:
                type1Img.setImageResource(R.drawable.fire);
                break;
            case 3:
                type1Img.setImageResource(R.drawable.water);
                break;
            case 4:
                type1Img.setImageResource(R.drawable.electric);
                break;
            case 5:
                type1Img.setImageResource(R.drawable.grass);
                break;
            case 6:
                type1Img.setImageResource(R.drawable.ice);
                break;
            case 7:
                type1Img.setImageResource(R.drawable.fighting);
                break;
            case 8:
                type1Img.setImageResource(R.drawable.poison);
                break;
            case 9:
                type1Img.setImageResource(R.drawable.ground);
                break;
            case 10:
                type1Img.setImageResource(R.drawable.flying);
                break;
            case 11:
                type1Img.setImageResource(R.drawable.psychic);
                break;
            case 12:
                type1Img.setImageResource(R.drawable.bug);
                break;
            case 13:
                type1Img.setImageResource(R.drawable.rock);
                break;
            case 14:
                type1Img.setImageResource(R.drawable.ghost);
                break;
            case 15:
                type1Img.setImageResource(R.drawable.dragon);
                break;
            case 16:
                type1Img.setImageResource(R.drawable.dark);
                break;
            case 17:
                type1Img.setImageResource(R.drawable.steel);
                break;
            case 18:
                type1Img.setImageResource(R.drawable.fairy);
                break;
        }
    }

    public void setTable2Type2Img()
    {
        ImageView type2Img = (ImageView) findViewById(R.id.table2Type2Img);
        switch (activePokemon2.type2)
        {
            case 1:
                type2Img.setImageResource(R.drawable.normal);
                break;
            case 2:
                type2Img.setImageResource(R.drawable.fire);
                break;
            case 3:
                type2Img.setImageResource(R.drawable.water);
                break;
            case 4:
                type2Img.setImageResource(R.drawable.electric);
                break;
            case 5:
                type2Img.setImageResource(R.drawable.grass);
                break;
            case 6:
                type2Img.setImageResource(R.drawable.ice);
                break;
            case 7:
                type2Img.setImageResource(R.drawable.fighting);
                break;
            case 8:
                type2Img.setImageResource(R.drawable.poison);
                break;
            case 9:
                type2Img.setImageResource(R.drawable.ground);
                break;
            case 10:
                type2Img.setImageResource(R.drawable.flying);
                break;
            case 11:
                type2Img.setImageResource(R.drawable.psychic);
                break;
            case 12:
                type2Img.setImageResource(R.drawable.bug);
                break;
            case 13:
                type2Img.setImageResource(R.drawable.rock);
                break;
            case 14:
                type2Img.setImageResource(R.drawable.ghost);
                break;
            case 15:
                type2Img.setImageResource(R.drawable.dragon);
                break;
            case 16:
                type2Img.setImageResource(R.drawable.dark);
                break;
            case 17:
                type2Img.setImageResource(R.drawable.steel);
                break;
            case 18:
                type2Img.setImageResource(R.drawable.fairy);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AudioPlay.stopAudio();
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
        if(firstTime)
        {
            firstTime=false;
            AudioPlay.stopAudio();
            AudioPlay.playAudio(this, R.raw.battletheme);
        }
    }

    @Override
    protected void onResume()  {
        super.onResume();
        if(!AudioPlay.isplayingAudio)
            AudioPlay.playAudio(this, R.raw.battletheme);
    }

    public void restart(View view)
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }




}
