package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.greg.android.biomoon.Globals.*;
import static com.greg.android.biomoon.Globals.name1;
import static com.greg.android.biomoon.Globals.p1Pokemon;
import static com.greg.android.biomoon.HomeActivity.host;
import static com.greg.android.biomoon.HomeActivity.isP2CPU;
import static com.greg.android.biomoon.HomeActivity.otherPlayersKey;
import static com.greg.android.biomoon.HomeActivity.playingOnline;
import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;

/**
 * Created by Greg on 2017-06-25.
 */

public class Play1ActivityOnline extends Activity{
    private static final String TAG = "Play1ActivityOnline";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play1_layout);
        activePokemon2 = p2Pokemon[0];

        Button b1a = (Button) findViewById(R.id.pokemon1_1);
        b1a.setText(p1Pokemon[0].name);
        Button b2a = (Button) findViewById(R.id.pokemon2_1);
        b2a.setText(p1Pokemon[1].name);
        Button b3a = (Button) findViewById(R.id.pokemon3_1);
        b3a.setText(p1Pokemon[2].name);
        Button b4a = (Button) findViewById(R.id.pokemon4_1);
        b4a.setText(p1Pokemon[3].name);
        Button b5a = (Button) findViewById(R.id.pokemon5_1);
        b5a.setText(p1Pokemon[4].name);
        Button b6a = (Button) findViewById(R.id.pokemon6_1);
        b6a.setText(p1Pokemon[5].name);

        TextView tvpokemon1 = (TextView) findViewById(R.id.tvpokemon1);
        String msg3 = name1 + " will be leading...";
        tvpokemon1.setText(msg3);

        getPokemon1Info();
        getPokemon2Info();
        getPokemon3Info();
        getPokemon4Info();
        getPokemon5Info();
        getPokemon6Info();

        Button b1 = (Button) findViewById(R.id.pokemon1_1);
        Button b2 = (Button) findViewById(R.id.pokemon2_1);
        Button b3 = (Button) findViewById(R.id.pokemon3_1);
        Button b4 = (Button) findViewById(R.id.pokemon4_1);
        Button b5 = (Button) findViewById(R.id.pokemon5_1);
        Button b6 = (Button) findViewById(R.id.pokemon6_1);


        TextView t = (TextView)findViewById(R.id.biomonlogo) ;
        TextView t1 = (TextView)findViewById(R.id.table1Name) ;
        TextView t2 = (TextView)findViewById(R.id.table1Atk) ;
        TextView t3 = (TextView)findViewById(R.id.table1SpAtk) ;
        TextView t4 = (TextView)findViewById(R.id.table1Def) ;
        TextView t5 = (TextView)findViewById(R.id.table1SpDef) ;
        TextView t6 = (TextView)findViewById(R.id.table1Spd) ;
        TextView t7 = (TextView)findViewById(R.id.table1Ability) ;
        TextView t8 = (TextView)findViewById(R.id.keyTextView) ;

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/turtles.otf");
        t.setTypeface(custom_font);
        t1.setTypeface(custom_font);
        t2.setTypeface(custom_font);
        t3.setTypeface(custom_font);
        t4.setTypeface(custom_font);
        t5.setTypeface(custom_font);
        t6.setTypeface(custom_font);
        t7.setTypeface(custom_font);
        b1.setTypeface(custom_font);
        b2.setTypeface(custom_font);
        b3.setTypeface(custom_font);
        b4.setTypeface(custom_font);
        b5.setTypeface(custom_font);
        b6.setTypeface(custom_font);
        tvpokemon1.setTypeface(custom_font);

    }

    public void Choose1Lead1(View view) {
        activePokemon1 = p1Pokemon[0];
        waitForOpponentToLead();

    }

    public void Choose1Lead2(View view) {
        activePokemon1 = p1Pokemon[1];
        waitForOpponentToLead();

    }

    public void Choose1Lead3(View view) {
        activePokemon1 = p1Pokemon[2];
        waitForOpponentToLead();

    }

    public void Choose1Lead4(View view) {
        activePokemon1 = p1Pokemon[3];
        waitForOpponentToLead();
    }

    public void Choose1Lead5(View view) {
        activePokemon1 = p1Pokemon[4];
        waitForOpponentToLead();
    }

    public void Choose1Lead6(View view) {
        activePokemon1 = p1Pokemon[5];
        waitForOpponentToLead();

    }

    public void waitForOpponentToLead()
    {
        //Just some Initializing
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Switching In").setValue("false");

        Intent intent = new Intent(this, WaitForLeadActivity.class);
        startActivity(intent);
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
        final Button b1 = (Button)findViewById(R.id.pokemon1_1);
        final ImageView pokemon = (ImageView) findViewById(R.id.gifp1);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            pokemon.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            Choose1Lead1(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        pokemon.setVisibility(View.VISIBLE);
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

                        setImage1();
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
        final Button b1 = (Button)findViewById(R.id.pokemon2_1);
        final ImageView pokemon = (ImageView) findViewById(R.id.gifp1);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            pokemon.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            Choose1Lead2(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        pokemon.setVisibility(View.VISIBLE);
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
                        setImage2();
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
        final Button b1 = (Button)findViewById(R.id.pokemon3_1);
        final ImageView pokemon = (ImageView) findViewById(R.id.gifp1);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            close.setVisibility(View.INVISIBLE);
                            pokemon.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            Choose1Lead3(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        pokemon.setVisibility(View.VISIBLE);
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
                        setImage3();
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
        final Button b1 = (Button)findViewById(R.id.pokemon4_1);
        final ImageView pokemon = (ImageView) findViewById(R.id.gifp1);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            close.setVisibility(View.INVISIBLE);
                            pokemon.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            Choose1Lead4(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        pokemon.setVisibility(View.VISIBLE);
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
                        setImage4();
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
        final Button b1 = (Button)findViewById(R.id.pokemon5_1);
        final ImageView pokemon = (ImageView) findViewById(R.id.gifp1);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            close.setVisibility(View.INVISIBLE);
                            pokemon.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            Choose1Lead5(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        pokemon.setVisibility(View.VISIBLE);
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
                        setImage5();
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
        final Button b1 = (Button)findViewById(R.id.pokemon6_1);
        final ImageView pokemon = (ImageView) findViewById(R.id.gifp1);

        b1.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;    // Variable rect to hold the bounds of the view

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            // User moved outside bounds
                            t1.setVisibility(View.INVISIBLE);
                            close.setVisibility(View.INVISIBLE);
                            pokemon.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            Choose1Lead6(v);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        t1.setVisibility(View.VISIBLE);
                        pokemon.setVisibility(View.VISIBLE);
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
                        setImage6();
                        break;
                }
                return false;
            }
        });

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

    public void setImage1()
    {
        ImageView pokemon= (ImageView) findViewById(R.id.gifp1);

        if(p1Pokemon[0].dexNumber == 1)
        {
            pokemon.setImageResource(R.drawable.a1);
        }

        if(p1Pokemon[0].dexNumber == 2)
        {
            pokemon.setImageResource(R.drawable.a2);
        }


        if(p1Pokemon[0].dexNumber == 3)
        {
            pokemon.setImageResource(R.drawable.a3);
        }


        if(p1Pokemon[0].dexNumber == 4)
        {
            pokemon.setImageResource(R.drawable.a4);
        }

        if(p1Pokemon[0].dexNumber == 5)
        {
            pokemon.setImageResource(R.drawable.a5);
        }


        if(p1Pokemon[0].dexNumber == 6)
        {
            pokemon.setImageResource(R.drawable.a6);
        }


        if(p1Pokemon[0].dexNumber == 7)
        {
            pokemon.setImageResource(R.drawable.a7);
        }

        if(p1Pokemon[0].dexNumber == 8)
        {
            pokemon.setImageResource(R.drawable.a8);
        }


        if(p1Pokemon[0].dexNumber == 9)
        {
            pokemon.setImageResource(R.drawable.a9);
        }


        if(p1Pokemon[0].dexNumber == 10)
        {
            pokemon.setImageResource(R.drawable.a10);
        }

        if(p1Pokemon[0].dexNumber == 11)
        {
            pokemon.setImageResource(R.drawable.a11);
        }


        if(p1Pokemon[0].dexNumber == 12)
        {
            pokemon.setImageResource(R.drawable.a12);
        }


        if(p1Pokemon[0].dexNumber == 13)
        {
            pokemon.setImageResource(R.drawable.a13);
        }


        if(p1Pokemon[0].dexNumber == 14)
        {
            pokemon.setImageResource(R.drawable.a14);
        }


        if(p1Pokemon[0].dexNumber == 15)
        {
            pokemon.setImageResource(R.drawable.a15);
        }


        if(p1Pokemon[0].dexNumber == 16)
        {
            pokemon.setImageResource(R.drawable.a16);
        }


        if(p1Pokemon[0].dexNumber == 17)
        {
            pokemon.setImageResource(R.drawable.a17);
        }


        if(p1Pokemon[0].dexNumber == 18)
        {
            pokemon.setImageResource(R.drawable.a18);
        }


        if(p1Pokemon[0].dexNumber == 19)
        {
            pokemon.setImageResource(R.drawable.a19);
        }


        if(p1Pokemon[0].dexNumber == 20)
        {
            pokemon.setImageResource(R.drawable.a20);
        }


        if(p1Pokemon[0].dexNumber == 21)
        {
            pokemon.setImageResource(R.drawable.a21);
        }


        if(p1Pokemon[0].dexNumber == 22)
        {
            pokemon.setImageResource(R.drawable.a22);
        }


        if(p1Pokemon[0].dexNumber == 23)
        {
            pokemon.setImageResource(R.drawable.a23);
        }


        if(p1Pokemon[0].dexNumber == 24)
        {
            pokemon.setImageResource(R.drawable.a24);
        }

        if(p1Pokemon[0].dexNumber == 25)
        {
            pokemon.setImageResource(R.drawable.a25);
        }


        if(p1Pokemon[0].dexNumber == 26)
        {
            pokemon.setImageResource(R.drawable.a26);
        }


        if(p1Pokemon[0].dexNumber == 27)
        {
            pokemon.setImageResource(R.drawable.a27);
        }


        if(p1Pokemon[0].dexNumber == 28)
        {
            pokemon.setImageResource(R.drawable.a28);
        }


        if(p1Pokemon[0].dexNumber == 29)
        {
            pokemon.setImageResource(R.drawable.a29);
        }

        if(p1Pokemon[0].dexNumber == 30)
        {
            pokemon.setImageResource(R.drawable.a30);
        }


        if(p1Pokemon[0].dexNumber == 31)
        {
            pokemon.setImageResource(R.drawable.a31);
        }

        if(p1Pokemon[0].dexNumber == 32)
        {
            pokemon.setImageResource(R.drawable.a32);
        }


        if(p1Pokemon[0].dexNumber == 33)
        {
            pokemon.setImageResource(R.drawable.a33);
        }


        if(p1Pokemon[0].dexNumber == 34)
        {
            pokemon.setImageResource(R.drawable.a34);
        }


        if(p1Pokemon[0].dexNumber == 35)
        {
            pokemon.setImageResource(R.drawable.a35);
        }


        if(p1Pokemon[0].dexNumber == 36)
        {
            pokemon.setImageResource(R.drawable.a36);
        }

        if(p1Pokemon[0].dexNumber == 37)
        {
            pokemon.setImageResource(R.drawable.a37);
        }


        if(p1Pokemon[0].dexNumber == 38)
        {
            pokemon.setImageResource(R.drawable.a38);
        }

        if(p1Pokemon[0].dexNumber == 39)
        {
            pokemon.setImageResource(R.drawable.a39);
        }


        if(p1Pokemon[0].dexNumber == 40)
        {
            pokemon.setImageResource(R.drawable.a40);
        }


        if(p1Pokemon[0].dexNumber == 41)
        {
            pokemon.setImageResource(R.drawable.a41);
        }

        if(p1Pokemon[0].dexNumber == 42)
        {
            pokemon.setImageResource(R.drawable.a42);
        }


        if(p1Pokemon[0].dexNumber == 43)
        {
            pokemon.setImageResource(R.drawable.a43);
        }


        if(p1Pokemon[0].dexNumber == 44)
        {
            pokemon.setImageResource(R.drawable.a44);
        }

        if(p1Pokemon[0].dexNumber == 45)
        {
            pokemon.setImageResource(R.drawable.a45);
        }


        if(p1Pokemon[0].dexNumber == 46)
        {
            pokemon.setImageResource(R.drawable.a46);
        }


        if(p1Pokemon[0].dexNumber == 47)
        {
            pokemon.setImageResource(R.drawable.a47);
        }


        if(p1Pokemon[0].dexNumber == 48)
        {
            pokemon.setImageResource(R.drawable.a48);
        }


        if(p1Pokemon[0].dexNumber == 49)
        {
            pokemon.setImageResource(R.drawable.a49);
        }


        if(p1Pokemon[0].dexNumber == 50)
        {
            pokemon.setImageResource(R.drawable.a50);
        }

        if(p1Pokemon[0].dexNumber == 51)
        {
            pokemon.setImageResource(R.drawable.a51);
        }

        if(p1Pokemon[0].dexNumber == 52)
        {
            pokemon.setImageResource(R.drawable.a52);
        }

        if(p1Pokemon[0].dexNumber == 53)
        {
            pokemon.setImageResource(R.drawable.a53);
        }


        if(p1Pokemon[0].dexNumber == 54)
        {
            pokemon.setImageResource(R.drawable.a54);
        }


        if(p1Pokemon[0].dexNumber == 55)
        {
            pokemon.setImageResource(R.drawable.a55);
        }


        if(p1Pokemon[0].dexNumber == 56)
        {
            pokemon.setImageResource(R.drawable.a56);
        }


        if(p1Pokemon[0].dexNumber == 57)
        {
            pokemon.setImageResource(R.drawable.a57);
        }


        if(p1Pokemon[0].dexNumber == 58)
        {
            pokemon.setImageResource(R.drawable.a5);
        }

        if(p1Pokemon[0].dexNumber == 59)
        {
            pokemon.setImageResource(R.drawable.a59);
        }


        if(p1Pokemon[0].dexNumber == 60)
        {
            pokemon.setImageResource(R.drawable.a60);
        }

        if(p1Pokemon[0].dexNumber == 61)
        {
            pokemon.setImageResource(R.drawable.a61);
        }


        if(p1Pokemon[0].dexNumber == 62)
        {
            pokemon.setImageResource(R.drawable.a62);
        }


        if(p1Pokemon[0].dexNumber == 63)
        {
            pokemon.setImageResource(R.drawable.a63);
        }


        if(p1Pokemon[0].dexNumber == 64)
        {
            pokemon.setImageResource(R.drawable.a64);
        }

        if(p1Pokemon[0].dexNumber == 65)
        {
            pokemon.setImageResource(R.drawable.a65);
        }

        if(p1Pokemon[0].dexNumber == 66)
        {
            pokemon.setImageResource(R.drawable.a66);
        }

        if(p1Pokemon[0].dexNumber == 67)
        {
            pokemon.setImageResource(R.drawable.a67);
        }


        if(p1Pokemon[0].dexNumber == 68)
        {
            pokemon.setImageResource(R.drawable.a68);
        }

        if(p1Pokemon[0].dexNumber == 69)
        {
            pokemon.setImageResource(R.drawable.a69);
        }


        if(p1Pokemon[0].dexNumber == 70)
        {
            pokemon.setImageResource(R.drawable.a70);
        }

        if(p1Pokemon[0].dexNumber == 71)
        {
            pokemon.setImageResource(R.drawable.a71);
        }


        if(p1Pokemon[0].dexNumber == 72)
        {
            pokemon.setImageResource(R.drawable.a72);
        }


        if(p1Pokemon[0].dexNumber == 73)
        {
            pokemon.setImageResource(R.drawable.a73);
        }


        if(p1Pokemon[0].dexNumber == 74)
        {
            pokemon.setImageResource(R.drawable.a74);
        }

        if(p1Pokemon[0].dexNumber == 75)
        {
            pokemon.setImageResource(R.drawable.a75);
        }


        if(p1Pokemon[0].dexNumber == 76)
        {
            pokemon.setImageResource(R.drawable.a76);
        }


        if(p1Pokemon[0].dexNumber == 77)
        {
            pokemon.setImageResource(R.drawable.a77);
        }


        if(p1Pokemon[0].dexNumber == 78)
        {
            pokemon.setImageResource(R.drawable.a78);
        }


        if(p1Pokemon[0].dexNumber == 79)
        {
            pokemon.setImageResource(R.drawable.a79);
        }


        if(p1Pokemon[0].dexNumber == 80)
        {
            pokemon.setImageResource(R.drawable.a80);
        }


        if(p1Pokemon[0].dexNumber == 81)
        {
            pokemon.setImageResource(R.drawable.a81);
        }


        if(p1Pokemon[0].dexNumber == 82)
        {
            pokemon.setImageResource(R.drawable.a82);
        }


        if(p1Pokemon[0].dexNumber == 83)
        {
            pokemon.setImageResource(R.drawable.a83);
        }


        if(p1Pokemon[0].dexNumber == 84)
        {
            pokemon.setImageResource(R.drawable.a84);
        }


        if(p1Pokemon[0].dexNumber == 85)
        {
            pokemon.setImageResource(R.drawable.a85);
        }


        if(p1Pokemon[0].dexNumber == 86)
        {
            pokemon.setImageResource(R.drawable.a86);
        }

        if(p1Pokemon[0].dexNumber == 87)
        {
            pokemon.setImageResource(R.drawable.a87);
        }


        if(p1Pokemon[0].dexNumber == 88)
        {
            pokemon.setImageResource(R.drawable.a88);
        }


        if(p1Pokemon[0].dexNumber == 89)
        {
            pokemon.setImageResource(R.drawable.a89);
        }


        if(p1Pokemon[0].dexNumber == 90)
        {
            pokemon.setImageResource(R.drawable.a90);
        }

        if(p1Pokemon[0].dexNumber == 91)
        {
            pokemon.setImageResource(R.drawable.a91);
        }


        if(p1Pokemon[0].dexNumber == 92)
        {
            pokemon.setImageResource(R.drawable.a92);
        }


        if(p1Pokemon[0].dexNumber == 93)
        {
            pokemon.setImageResource(R.drawable.a93);
        }


        if(p1Pokemon[0].dexNumber == 94)
        {
            pokemon.setImageResource(R.drawable.a94);
        }


        if(p1Pokemon[0].dexNumber == 95)
        {
            pokemon.setImageResource(R.drawable.a95);
        }


        if(p1Pokemon[0].dexNumber == 96)
        {
            pokemon.setImageResource(R.drawable.a96);
        }


        if(p1Pokemon[0].dexNumber == 97)
        {
            pokemon.setImageResource(R.drawable.a97);
        }


        if(p1Pokemon[0].dexNumber == 98)
        {
            pokemon.setImageResource(R.drawable.a98);
        }


        if(p1Pokemon[0].dexNumber == 99)
        {
            pokemon.setImageResource(R.drawable.a99);
        }

    }

    public void setImage2()
    {
        ImageView pokemon= (ImageView) findViewById(R.id.gifp1);

        if(p1Pokemon[1].dexNumber == 1)
        {
            pokemon.setImageResource(R.drawable.a1);
        }

        if(p1Pokemon[1].dexNumber == 2)
        {
            pokemon.setImageResource(R.drawable.a2);
        }


        if(p1Pokemon[1].dexNumber == 3)
        {
            pokemon.setImageResource(R.drawable.a3);
        }


        if(p1Pokemon[1].dexNumber == 4)
        {
            pokemon.setImageResource(R.drawable.a4);
        }

        if(p1Pokemon[1].dexNumber == 5)
        {
            pokemon.setImageResource(R.drawable.a5);
        }


        if(p1Pokemon[1].dexNumber == 6)
        {
            pokemon.setImageResource(R.drawable.a6);
        }


        if(p1Pokemon[1].dexNumber == 7)
        {
            pokemon.setImageResource(R.drawable.a7);
        }

        if(p1Pokemon[1].dexNumber == 8)
        {
            pokemon.setImageResource(R.drawable.a8);
        }


        if(p1Pokemon[1].dexNumber == 9)
        {
            pokemon.setImageResource(R.drawable.a9);
        }


        if(p1Pokemon[1].dexNumber == 10)
        {
            pokemon.setImageResource(R.drawable.a10);
        }

        if(p1Pokemon[1].dexNumber == 11)
        {
            pokemon.setImageResource(R.drawable.a11);
        }


        if(p1Pokemon[1].dexNumber == 12)
        {
            pokemon.setImageResource(R.drawable.a12);
        }


        if(p1Pokemon[1].dexNumber == 13)
        {
            pokemon.setImageResource(R.drawable.a13);
        }


        if(p1Pokemon[1].dexNumber == 14)
        {
            pokemon.setImageResource(R.drawable.a14);
        }


        if(p1Pokemon[1].dexNumber == 15)
        {
            pokemon.setImageResource(R.drawable.a15);
        }


        if(p1Pokemon[1].dexNumber == 16)
        {
            pokemon.setImageResource(R.drawable.a16);
        }


        if(p1Pokemon[1].dexNumber == 17)
        {
            pokemon.setImageResource(R.drawable.a17);
        }


        if(p1Pokemon[1].dexNumber == 18)
        {
            pokemon.setImageResource(R.drawable.a18);
        }


        if(p1Pokemon[1].dexNumber == 19)
        {
            pokemon.setImageResource(R.drawable.a19);
        }


        if(p1Pokemon[1].dexNumber == 20)
        {
            pokemon.setImageResource(R.drawable.a20);
        }


        if(p1Pokemon[1].dexNumber == 21)
        {
            pokemon.setImageResource(R.drawable.a21);
        }


        if(p1Pokemon[1].dexNumber == 22)
        {
            pokemon.setImageResource(R.drawable.a22);
        }


        if(p1Pokemon[1].dexNumber == 23)
        {
            pokemon.setImageResource(R.drawable.a23);
        }


        if(p1Pokemon[1].dexNumber == 24)
        {
            pokemon.setImageResource(R.drawable.a24);
        }

        if(p1Pokemon[1].dexNumber == 25)
        {
            pokemon.setImageResource(R.drawable.a25);
        }


        if(p1Pokemon[1].dexNumber == 26)
        {
            pokemon.setImageResource(R.drawable.a26);
        }


        if(p1Pokemon[1].dexNumber == 27)
        {
            pokemon.setImageResource(R.drawable.a27);
        }


        if(p1Pokemon[1].dexNumber == 28)
        {
            pokemon.setImageResource(R.drawable.a28);
        }


        if(p1Pokemon[1].dexNumber == 29)
        {
            pokemon.setImageResource(R.drawable.a29);
        }

        if(p1Pokemon[1].dexNumber == 30)
        {
            pokemon.setImageResource(R.drawable.a30);
        }


        if(p1Pokemon[1].dexNumber == 31)
        {
            pokemon.setImageResource(R.drawable.a31);
        }

        if(p1Pokemon[1].dexNumber == 32)
        {
            pokemon.setImageResource(R.drawable.a32);
        }


        if(p1Pokemon[1].dexNumber == 33)
        {
            pokemon.setImageResource(R.drawable.a33);
        }


        if(p1Pokemon[1].dexNumber == 34)
        {
            pokemon.setImageResource(R.drawable.a34);
        }


        if(p1Pokemon[1].dexNumber == 35)
        {
            pokemon.setImageResource(R.drawable.a35);
        }


        if(p1Pokemon[1].dexNumber == 36)
        {
            pokemon.setImageResource(R.drawable.a36);
        }

        if(p1Pokemon[1].dexNumber == 37)
        {
            pokemon.setImageResource(R.drawable.a37);
        }


        if(p1Pokemon[1].dexNumber == 38)
        {
            pokemon.setImageResource(R.drawable.a38);
        }

        if(p1Pokemon[1].dexNumber == 39)
        {
            pokemon.setImageResource(R.drawable.a39);
        }


        if(p1Pokemon[1].dexNumber == 40)
        {
            pokemon.setImageResource(R.drawable.a40);
        }


        if(p1Pokemon[1].dexNumber == 41)
        {
            pokemon.setImageResource(R.drawable.a41);
        }

        if(p1Pokemon[1].dexNumber == 42)
        {
            pokemon.setImageResource(R.drawable.a42);
        }


        if(p1Pokemon[1].dexNumber == 43)
        {
            pokemon.setImageResource(R.drawable.a43);
        }


        if(p1Pokemon[1].dexNumber == 44)
        {
            pokemon.setImageResource(R.drawable.a44);
        }

        if(p1Pokemon[1].dexNumber == 45)
        {
            pokemon.setImageResource(R.drawable.a45);
        }


        if(p1Pokemon[1].dexNumber == 46)
        {
            pokemon.setImageResource(R.drawable.a46);
        }


        if(p1Pokemon[1].dexNumber == 47)
        {
            pokemon.setImageResource(R.drawable.a47);
        }


        if(p1Pokemon[1].dexNumber == 48)
        {
            pokemon.setImageResource(R.drawable.a48);
        }


        if(p1Pokemon[1].dexNumber == 49)
        {
            pokemon.setImageResource(R.drawable.a49);
        }


        if(p1Pokemon[1].dexNumber == 50)
        {
            pokemon.setImageResource(R.drawable.a50);
        }

        if(p1Pokemon[1].dexNumber == 51)
        {
            pokemon.setImageResource(R.drawable.a51);
        }

        if(p1Pokemon[1].dexNumber == 52)
        {
            pokemon.setImageResource(R.drawable.a52);
        }

        if(p1Pokemon[1].dexNumber == 53)
        {
            pokemon.setImageResource(R.drawable.a53);
        }


        if(p1Pokemon[1].dexNumber == 54)
        {
            pokemon.setImageResource(R.drawable.a54);
        }


        if(p1Pokemon[1].dexNumber == 55)
        {
            pokemon.setImageResource(R.drawable.a55);
        }


        if(p1Pokemon[1].dexNumber == 56)
        {
            pokemon.setImageResource(R.drawable.a56);
        }


        if(p1Pokemon[1].dexNumber == 57)
        {
            pokemon.setImageResource(R.drawable.a57);
        }


        if(p1Pokemon[1].dexNumber == 58)
        {
            pokemon.setImageResource(R.drawable.a5);
        }

        if(p1Pokemon[1].dexNumber == 59)
        {
            pokemon.setImageResource(R.drawable.a59);
        }


        if(p1Pokemon[1].dexNumber == 60)
        {
            pokemon.setImageResource(R.drawable.a60);
        }

        if(p1Pokemon[1].dexNumber == 61)
        {
            pokemon.setImageResource(R.drawable.a61);
        }


        if(p1Pokemon[1].dexNumber == 62)
        {
            pokemon.setImageResource(R.drawable.a62);
        }


        if(p1Pokemon[1].dexNumber == 63)
        {
            pokemon.setImageResource(R.drawable.a63);
        }


        if(p1Pokemon[1].dexNumber == 64)
        {
            pokemon.setImageResource(R.drawable.a64);
        }

        if(p1Pokemon[1].dexNumber == 65)
        {
            pokemon.setImageResource(R.drawable.a65);
        }

        if(p1Pokemon[1].dexNumber == 66)
        {
            pokemon.setImageResource(R.drawable.a66);
        }

        if(p1Pokemon[1].dexNumber == 67)
        {
            pokemon.setImageResource(R.drawable.a67);
        }


        if(p1Pokemon[1].dexNumber == 68)
        {
            pokemon.setImageResource(R.drawable.a68);
        }

        if(p1Pokemon[1].dexNumber == 69)
        {
            pokemon.setImageResource(R.drawable.a69);
        }


        if(p1Pokemon[1].dexNumber == 70)
        {
            pokemon.setImageResource(R.drawable.a70);
        }

        if(p1Pokemon[1].dexNumber == 71)
        {
            pokemon.setImageResource(R.drawable.a71);
        }


        if(p1Pokemon[1].dexNumber == 72)
        {
            pokemon.setImageResource(R.drawable.a72);
        }


        if(p1Pokemon[1].dexNumber == 73)
        {
            pokemon.setImageResource(R.drawable.a73);
        }


        if(p1Pokemon[1].dexNumber == 74)
        {
            pokemon.setImageResource(R.drawable.a74);
        }

        if(p1Pokemon[1].dexNumber == 75)
        {
            pokemon.setImageResource(R.drawable.a75);
        }


        if(p1Pokemon[1].dexNumber == 76)
        {
            pokemon.setImageResource(R.drawable.a76);
        }


        if(p1Pokemon[1].dexNumber == 77)
        {
            pokemon.setImageResource(R.drawable.a77);
        }


        if(p1Pokemon[1].dexNumber == 78)
        {
            pokemon.setImageResource(R.drawable.a78);
        }


        if(p1Pokemon[1].dexNumber == 79)
        {
            pokemon.setImageResource(R.drawable.a79);
        }


        if(p1Pokemon[1].dexNumber == 80)
        {
            pokemon.setImageResource(R.drawable.a80);
        }


        if(p1Pokemon[1].dexNumber == 81)
        {
            pokemon.setImageResource(R.drawable.a81);
        }


        if(p1Pokemon[1].dexNumber == 82)
        {
            pokemon.setImageResource(R.drawable.a82);
        }


        if(p1Pokemon[1].dexNumber == 83)
        {
            pokemon.setImageResource(R.drawable.a83);
        }


        if(p1Pokemon[1].dexNumber == 84)
        {
            pokemon.setImageResource(R.drawable.a84);
        }


        if(p1Pokemon[1].dexNumber == 85)
        {
            pokemon.setImageResource(R.drawable.a85);
        }


        if(p1Pokemon[1].dexNumber == 86)
        {
            pokemon.setImageResource(R.drawable.a86);
        }

        if(p1Pokemon[1].dexNumber == 87)
        {
            pokemon.setImageResource(R.drawable.a87);
        }


        if(p1Pokemon[1].dexNumber == 88)
        {
            pokemon.setImageResource(R.drawable.a88);
        }


        if(p1Pokemon[1].dexNumber == 89)
        {
            pokemon.setImageResource(R.drawable.a89);
        }


        if(p1Pokemon[1].dexNumber == 90)
        {
            pokemon.setImageResource(R.drawable.a90);
        }

        if(p1Pokemon[1].dexNumber == 91)
        {
            pokemon.setImageResource(R.drawable.a91);
        }


        if(p1Pokemon[1].dexNumber == 92)
        {
            pokemon.setImageResource(R.drawable.a92);
        }


        if(p1Pokemon[1].dexNumber == 93)
        {
            pokemon.setImageResource(R.drawable.a93);
        }


        if(p1Pokemon[1].dexNumber == 94)
        {
            pokemon.setImageResource(R.drawable.a94);
        }


        if(p1Pokemon[1].dexNumber == 95)
        {
            pokemon.setImageResource(R.drawable.a95);
        }


        if(p1Pokemon[1].dexNumber == 96)
        {
            pokemon.setImageResource(R.drawable.a96);
        }


        if(p1Pokemon[1].dexNumber == 97)
        {
            pokemon.setImageResource(R.drawable.a97);
        }


        if(p1Pokemon[1].dexNumber == 98)
        {
            pokemon.setImageResource(R.drawable.a98);
        }


        if(p1Pokemon[1].dexNumber == 99)
        {
            pokemon.setImageResource(R.drawable.a99);
        }

    }

    public void setImage3()
    {
        ImageView pokemon= (ImageView) findViewById(R.id.gifp1);

        if(p1Pokemon[2].dexNumber == 1)
        {
            pokemon.setImageResource(R.drawable.a1);
        }

        if(p1Pokemon[2].dexNumber == 2)
        {
            pokemon.setImageResource(R.drawable.a2);
        }


        if(p1Pokemon[2].dexNumber == 3)
        {
            pokemon.setImageResource(R.drawable.a3);
        }


        if(p1Pokemon[2].dexNumber == 4)
        {
            pokemon.setImageResource(R.drawable.a4);
        }

        if(p1Pokemon[2].dexNumber == 5)
        {
            pokemon.setImageResource(R.drawable.a5);
        }


        if(p1Pokemon[2].dexNumber == 6)
        {
            pokemon.setImageResource(R.drawable.a6);
        }


        if(p1Pokemon[2].dexNumber == 7)
        {
            pokemon.setImageResource(R.drawable.a7);
        }

        if(p1Pokemon[2].dexNumber == 8)
        {
            pokemon.setImageResource(R.drawable.a8);
        }


        if(p1Pokemon[2].dexNumber == 9)
        {
            pokemon.setImageResource(R.drawable.a9);
        }


        if(p1Pokemon[2].dexNumber == 10)
        {
            pokemon.setImageResource(R.drawable.a10);
        }

        if(p1Pokemon[2].dexNumber == 11)
        {
            pokemon.setImageResource(R.drawable.a11);
        }


        if(p1Pokemon[2].dexNumber == 12)
        {
            pokemon.setImageResource(R.drawable.a12);
        }


        if(p1Pokemon[2].dexNumber == 13)
        {
            pokemon.setImageResource(R.drawable.a13);
        }


        if(p1Pokemon[2].dexNumber == 14)
        {
            pokemon.setImageResource(R.drawable.a14);
        }


        if(p1Pokemon[2].dexNumber == 15)
        {
            pokemon.setImageResource(R.drawable.a15);
        }


        if(p1Pokemon[2].dexNumber == 16)
        {
            pokemon.setImageResource(R.drawable.a16);
        }


        if(p1Pokemon[2].dexNumber == 17)
        {
            pokemon.setImageResource(R.drawable.a17);
        }


        if(p1Pokemon[2].dexNumber == 18)
        {
            pokemon.setImageResource(R.drawable.a18);
        }


        if(p1Pokemon[2].dexNumber == 19)
        {
            pokemon.setImageResource(R.drawable.a19);
        }


        if(p1Pokemon[2].dexNumber == 20)
        {
            pokemon.setImageResource(R.drawable.a20);
        }


        if(p1Pokemon[2].dexNumber == 21)
        {
            pokemon.setImageResource(R.drawable.a21);
        }


        if(p1Pokemon[2].dexNumber == 22)
        {
            pokemon.setImageResource(R.drawable.a22);
        }


        if(p1Pokemon[2].dexNumber == 23)
        {
            pokemon.setImageResource(R.drawable.a23);
        }


        if(p1Pokemon[2].dexNumber == 24)
        {
            pokemon.setImageResource(R.drawable.a24);
        }

        if(p1Pokemon[2].dexNumber == 25)
        {
            pokemon.setImageResource(R.drawable.a25);
        }


        if(p1Pokemon[2].dexNumber == 26)
        {
            pokemon.setImageResource(R.drawable.a26);
        }


        if(p1Pokemon[2].dexNumber == 27)
        {
            pokemon.setImageResource(R.drawable.a27);
        }


        if(p1Pokemon[2].dexNumber == 28)
        {
            pokemon.setImageResource(R.drawable.a28);
        }


        if(p1Pokemon[2].dexNumber == 29)
        {
            pokemon.setImageResource(R.drawable.a29);
        }

        if(p1Pokemon[2].dexNumber == 30)
        {
            pokemon.setImageResource(R.drawable.a30);
        }


        if(p1Pokemon[2].dexNumber == 31)
        {
            pokemon.setImageResource(R.drawable.a31);
        }

        if(p1Pokemon[2].dexNumber == 32)
        {
            pokemon.setImageResource(R.drawable.a32);
        }


        if(p1Pokemon[2].dexNumber == 33)
        {
            pokemon.setImageResource(R.drawable.a33);
        }


        if(p1Pokemon[2].dexNumber == 34)
        {
            pokemon.setImageResource(R.drawable.a34);
        }


        if(p1Pokemon[2].dexNumber == 35)
        {
            pokemon.setImageResource(R.drawable.a35);
        }


        if(p1Pokemon[2].dexNumber == 36)
        {
            pokemon.setImageResource(R.drawable.a36);
        }

        if(p1Pokemon[2].dexNumber == 37)
        {
            pokemon.setImageResource(R.drawable.a37);
        }


        if(p1Pokemon[2].dexNumber == 38)
        {
            pokemon.setImageResource(R.drawable.a38);
        }

        if(p1Pokemon[2].dexNumber == 39)
        {
            pokemon.setImageResource(R.drawable.a39);
        }


        if(p1Pokemon[2].dexNumber == 40)
        {
            pokemon.setImageResource(R.drawable.a40);
        }


        if(p1Pokemon[2].dexNumber == 41)
        {
            pokemon.setImageResource(R.drawable.a41);
        }

        if(p1Pokemon[2].dexNumber == 42)
        {
            pokemon.setImageResource(R.drawable.a42);
        }


        if(p1Pokemon[2].dexNumber == 43)
        {
            pokemon.setImageResource(R.drawable.a43);
        }


        if(p1Pokemon[2].dexNumber == 44)
        {
            pokemon.setImageResource(R.drawable.a44);
        }

        if(p1Pokemon[2].dexNumber == 45)
        {
            pokemon.setImageResource(R.drawable.a45);
        }


        if(p1Pokemon[2].dexNumber == 46)
        {
            pokemon.setImageResource(R.drawable.a46);
        }


        if(p1Pokemon[2].dexNumber == 47)
        {
            pokemon.setImageResource(R.drawable.a47);
        }


        if(p1Pokemon[2].dexNumber == 48)
        {
            pokemon.setImageResource(R.drawable.a48);
        }


        if(p1Pokemon[2].dexNumber == 49)
        {
            pokemon.setImageResource(R.drawable.a49);
        }


        if(p1Pokemon[2].dexNumber == 50)
        {
            pokemon.setImageResource(R.drawable.a50);
        }

        if(p1Pokemon[2].dexNumber == 51)
        {
            pokemon.setImageResource(R.drawable.a51);
        }

        if(p1Pokemon[2].dexNumber == 52)
        {
            pokemon.setImageResource(R.drawable.a52);
        }

        if(p1Pokemon[2].dexNumber == 53)
        {
            pokemon.setImageResource(R.drawable.a53);
        }


        if(p1Pokemon[2].dexNumber == 54)
        {
            pokemon.setImageResource(R.drawable.a54);
        }


        if(p1Pokemon[2].dexNumber == 55)
        {
            pokemon.setImageResource(R.drawable.a55);
        }


        if(p1Pokemon[2].dexNumber == 56)
        {
            pokemon.setImageResource(R.drawable.a56);
        }


        if(p1Pokemon[2].dexNumber == 57)
        {
            pokemon.setImageResource(R.drawable.a57);
        }


        if(p1Pokemon[2].dexNumber == 58)
        {
            pokemon.setImageResource(R.drawable.a5);
        }

        if(p1Pokemon[2].dexNumber == 59)
        {
            pokemon.setImageResource(R.drawable.a59);
        }


        if(p1Pokemon[2].dexNumber == 60)
        {
            pokemon.setImageResource(R.drawable.a60);
        }

        if(p1Pokemon[2].dexNumber == 61)
        {
            pokemon.setImageResource(R.drawable.a61);
        }


        if(p1Pokemon[2].dexNumber == 62)
        {
            pokemon.setImageResource(R.drawable.a62);
        }


        if(p1Pokemon[2].dexNumber == 63)
        {
            pokemon.setImageResource(R.drawable.a63);
        }


        if(p1Pokemon[2].dexNumber == 64)
        {
            pokemon.setImageResource(R.drawable.a64);
        }

        if(p1Pokemon[2].dexNumber == 65)
        {
            pokemon.setImageResource(R.drawable.a65);
        }

        if(p1Pokemon[2].dexNumber == 66)
        {
            pokemon.setImageResource(R.drawable.a66);
        }

        if(p1Pokemon[2].dexNumber == 67)
        {
            pokemon.setImageResource(R.drawable.a67);
        }


        if(p1Pokemon[2].dexNumber == 68)
        {
            pokemon.setImageResource(R.drawable.a68);
        }

        if(p1Pokemon[2].dexNumber == 69)
        {
            pokemon.setImageResource(R.drawable.a69);
        }


        if(p1Pokemon[2].dexNumber == 70)
        {
            pokemon.setImageResource(R.drawable.a70);
        }

        if(p1Pokemon[2].dexNumber == 71)
        {
            pokemon.setImageResource(R.drawable.a71);
        }


        if(p1Pokemon[2].dexNumber == 72)
        {
            pokemon.setImageResource(R.drawable.a72);
        }


        if(p1Pokemon[2].dexNumber == 73)
        {
            pokemon.setImageResource(R.drawable.a73);
        }


        if(p1Pokemon[2].dexNumber == 74)
        {
            pokemon.setImageResource(R.drawable.a74);
        }

        if(p1Pokemon[2].dexNumber == 75)
        {
            pokemon.setImageResource(R.drawable.a75);
        }


        if(p1Pokemon[2].dexNumber == 76)
        {
            pokemon.setImageResource(R.drawable.a76);
        }


        if(p1Pokemon[2].dexNumber == 77)
        {
            pokemon.setImageResource(R.drawable.a77);
        }


        if(p1Pokemon[2].dexNumber == 78)
        {
            pokemon.setImageResource(R.drawable.a78);
        }


        if(p1Pokemon[2].dexNumber == 79)
        {
            pokemon.setImageResource(R.drawable.a79);
        }


        if(p1Pokemon[2].dexNumber == 80)
        {
            pokemon.setImageResource(R.drawable.a80);
        }


        if(p1Pokemon[2].dexNumber == 81)
        {
            pokemon.setImageResource(R.drawable.a81);
        }


        if(p1Pokemon[2].dexNumber == 82)
        {
            pokemon.setImageResource(R.drawable.a82);
        }


        if(p1Pokemon[2].dexNumber == 83)
        {
            pokemon.setImageResource(R.drawable.a83);
        }


        if(p1Pokemon[2].dexNumber == 84)
        {
            pokemon.setImageResource(R.drawable.a84);
        }


        if(p1Pokemon[2].dexNumber == 85)
        {
            pokemon.setImageResource(R.drawable.a85);
        }


        if(p1Pokemon[2].dexNumber == 86)
        {
            pokemon.setImageResource(R.drawable.a86);
        }

        if(p1Pokemon[2].dexNumber == 87)
        {
            pokemon.setImageResource(R.drawable.a87);
        }


        if(p1Pokemon[2].dexNumber == 88)
        {
            pokemon.setImageResource(R.drawable.a88);
        }


        if(p1Pokemon[2].dexNumber == 89)
        {
            pokemon.setImageResource(R.drawable.a89);
        }


        if(p1Pokemon[2].dexNumber == 90)
        {
            pokemon.setImageResource(R.drawable.a90);
        }

        if(p1Pokemon[2].dexNumber == 91)
        {
            pokemon.setImageResource(R.drawable.a91);
        }


        if(p1Pokemon[2].dexNumber == 92)
        {
            pokemon.setImageResource(R.drawable.a92);
        }


        if(p1Pokemon[2].dexNumber == 93)
        {
            pokemon.setImageResource(R.drawable.a93);
        }


        if(p1Pokemon[2].dexNumber == 94)
        {
            pokemon.setImageResource(R.drawable.a94);
        }


        if(p1Pokemon[2].dexNumber == 95)
        {
            pokemon.setImageResource(R.drawable.a95);
        }


        if(p1Pokemon[2].dexNumber == 96)
        {
            pokemon.setImageResource(R.drawable.a96);
        }


        if(p1Pokemon[2].dexNumber == 97)
        {
            pokemon.setImageResource(R.drawable.a97);
        }


        if(p1Pokemon[2].dexNumber == 98)
        {
            pokemon.setImageResource(R.drawable.a98);
        }


        if(p1Pokemon[2].dexNumber == 99)
        {
            pokemon.setImageResource(R.drawable.a99);
        }

    }

    public void setImage4()
    {
        ImageView pokemon= (ImageView) findViewById(R.id.gifp1);

        if(p1Pokemon[3].dexNumber == 1)
        {
            pokemon.setImageResource(R.drawable.a1);
        }

        if(p1Pokemon[3].dexNumber == 2)
        {
            pokemon.setImageResource(R.drawable.a2);
        }


        if(p1Pokemon[3].dexNumber == 3)
        {
            pokemon.setImageResource(R.drawable.a3);
        }


        if(p1Pokemon[3].dexNumber == 4)
        {
            pokemon.setImageResource(R.drawable.a4);
        }

        if(p1Pokemon[3].dexNumber == 5)
        {
            pokemon.setImageResource(R.drawable.a5);
        }


        if(p1Pokemon[3].dexNumber == 6)
        {
            pokemon.setImageResource(R.drawable.a6);
        }


        if(p1Pokemon[3].dexNumber == 7)
        {
            pokemon.setImageResource(R.drawable.a7);
        }

        if(p1Pokemon[3].dexNumber == 8)
        {
            pokemon.setImageResource(R.drawable.a8);
        }


        if(p1Pokemon[3].dexNumber == 9)
        {
            pokemon.setImageResource(R.drawable.a9);
        }


        if(p1Pokemon[3].dexNumber == 10)
        {
            pokemon.setImageResource(R.drawable.a10);
        }

        if(p1Pokemon[3].dexNumber == 11)
        {
            pokemon.setImageResource(R.drawable.a11);
        }


        if(p1Pokemon[3].dexNumber == 12)
        {
            pokemon.setImageResource(R.drawable.a12);
        }


        if(p1Pokemon[3].dexNumber == 13)
        {
            pokemon.setImageResource(R.drawable.a13);
        }


        if(p1Pokemon[3].dexNumber == 14)
        {
            pokemon.setImageResource(R.drawable.a14);
        }


        if(p1Pokemon[3].dexNumber == 15)
        {
            pokemon.setImageResource(R.drawable.a15);
        }


        if(p1Pokemon[3].dexNumber == 16)
        {
            pokemon.setImageResource(R.drawable.a16);
        }


        if(p1Pokemon[3].dexNumber == 17)
        {
            pokemon.setImageResource(R.drawable.a17);
        }


        if(p1Pokemon[3].dexNumber == 18)
        {
            pokemon.setImageResource(R.drawable.a18);
        }


        if(p1Pokemon[3].dexNumber == 19)
        {
            pokemon.setImageResource(R.drawable.a19);
        }


        if(p1Pokemon[3].dexNumber == 20)
        {
            pokemon.setImageResource(R.drawable.a20);
        }


        if(p1Pokemon[3].dexNumber == 21)
        {
            pokemon.setImageResource(R.drawable.a21);
        }


        if(p1Pokemon[3].dexNumber == 22)
        {
            pokemon.setImageResource(R.drawable.a22);
        }


        if(p1Pokemon[3].dexNumber == 23)
        {
            pokemon.setImageResource(R.drawable.a23);
        }


        if(p1Pokemon[3].dexNumber == 24)
        {
            pokemon.setImageResource(R.drawable.a24);
        }

        if(p1Pokemon[3].dexNumber == 25)
        {
            pokemon.setImageResource(R.drawable.a25);
        }


        if(p1Pokemon[3].dexNumber == 26)
        {
            pokemon.setImageResource(R.drawable.a26);
        }


        if(p1Pokemon[3].dexNumber == 27)
        {
            pokemon.setImageResource(R.drawable.a27);
        }


        if(p1Pokemon[3].dexNumber == 28)
        {
            pokemon.setImageResource(R.drawable.a28);
        }


        if(p1Pokemon[3].dexNumber == 29)
        {
            pokemon.setImageResource(R.drawable.a29);
        }

        if(p1Pokemon[3].dexNumber == 30)
        {
            pokemon.setImageResource(R.drawable.a30);
        }


        if(p1Pokemon[3].dexNumber == 31)
        {
            pokemon.setImageResource(R.drawable.a31);
        }

        if(p1Pokemon[3].dexNumber == 32)
        {
            pokemon.setImageResource(R.drawable.a32);
        }


        if(p1Pokemon[3].dexNumber == 33)
        {
            pokemon.setImageResource(R.drawable.a33);
        }


        if(p1Pokemon[3].dexNumber == 34)
        {
            pokemon.setImageResource(R.drawable.a34);
        }


        if(p1Pokemon[3].dexNumber == 35)
        {
            pokemon.setImageResource(R.drawable.a35);
        }


        if(p1Pokemon[3].dexNumber == 36)
        {
            pokemon.setImageResource(R.drawable.a36);
        }

        if(p1Pokemon[3].dexNumber == 37)
        {
            pokemon.setImageResource(R.drawable.a37);
        }


        if(p1Pokemon[3].dexNumber == 38)
        {
            pokemon.setImageResource(R.drawable.a38);
        }

        if(p1Pokemon[3].dexNumber == 39)
        {
            pokemon.setImageResource(R.drawable.a39);
        }


        if(p1Pokemon[3].dexNumber == 40)
        {
            pokemon.setImageResource(R.drawable.a40);
        }


        if(p1Pokemon[3].dexNumber == 41)
        {
            pokemon.setImageResource(R.drawable.a41);
        }

        if(p1Pokemon[3].dexNumber == 42)
        {
            pokemon.setImageResource(R.drawable.a42);
        }


        if(p1Pokemon[3].dexNumber == 43)
        {
            pokemon.setImageResource(R.drawable.a43);
        }


        if(p1Pokemon[3].dexNumber == 44)
        {
            pokemon.setImageResource(R.drawable.a44);
        }

        if(p1Pokemon[3].dexNumber == 45)
        {
            pokemon.setImageResource(R.drawable.a45);
        }


        if(p1Pokemon[3].dexNumber == 46)
        {
            pokemon.setImageResource(R.drawable.a46);
        }


        if(p1Pokemon[3].dexNumber == 47)
        {
            pokemon.setImageResource(R.drawable.a47);
        }


        if(p1Pokemon[3].dexNumber == 48)
        {
            pokemon.setImageResource(R.drawable.a48);
        }


        if(p1Pokemon[3].dexNumber == 49)
        {
            pokemon.setImageResource(R.drawable.a49);
        }


        if(p1Pokemon[3].dexNumber == 50)
        {
            pokemon.setImageResource(R.drawable.a50);
        }

        if(p1Pokemon[3].dexNumber == 51)
        {
            pokemon.setImageResource(R.drawable.a51);
        }

        if(p1Pokemon[3].dexNumber == 52)
        {
            pokemon.setImageResource(R.drawable.a52);
        }

        if(p1Pokemon[3].dexNumber == 53)
        {
            pokemon.setImageResource(R.drawable.a53);
        }


        if(p1Pokemon[3].dexNumber == 54)
        {
            pokemon.setImageResource(R.drawable.a54);
        }


        if(p1Pokemon[3].dexNumber == 55)
        {
            pokemon.setImageResource(R.drawable.a55);
        }


        if(p1Pokemon[3].dexNumber == 56)
        {
            pokemon.setImageResource(R.drawable.a56);
        }


        if(p1Pokemon[3].dexNumber == 57)
        {
            pokemon.setImageResource(R.drawable.a57);
        }


        if(p1Pokemon[3].dexNumber == 58)
        {
            pokemon.setImageResource(R.drawable.a5);
        }

        if(p1Pokemon[3].dexNumber == 59)
        {
            pokemon.setImageResource(R.drawable.a59);
        }


        if(p1Pokemon[3].dexNumber == 60)
        {
            pokemon.setImageResource(R.drawable.a60);
        }

        if(p1Pokemon[3].dexNumber == 61)
        {
            pokemon.setImageResource(R.drawable.a61);
        }


        if(p1Pokemon[3].dexNumber == 62)
        {
            pokemon.setImageResource(R.drawable.a62);
        }


        if(p1Pokemon[3].dexNumber == 63)
        {
            pokemon.setImageResource(R.drawable.a63);
        }


        if(p1Pokemon[3].dexNumber == 64)
        {
            pokemon.setImageResource(R.drawable.a64);
        }

        if(p1Pokemon[3].dexNumber == 65)
        {
            pokemon.setImageResource(R.drawable.a65);
        }

        if(p1Pokemon[3].dexNumber == 66)
        {
            pokemon.setImageResource(R.drawable.a66);
        }

        if(p1Pokemon[3].dexNumber == 67)
        {
            pokemon.setImageResource(R.drawable.a67);
        }


        if(p1Pokemon[3].dexNumber == 68)
        {
            pokemon.setImageResource(R.drawable.a68);
        }

        if(p1Pokemon[3].dexNumber == 69)
        {
            pokemon.setImageResource(R.drawable.a69);
        }


        if(p1Pokemon[3].dexNumber == 70)
        {
            pokemon.setImageResource(R.drawable.a70);
        }

        if(p1Pokemon[3].dexNumber == 71)
        {
            pokemon.setImageResource(R.drawable.a71);
        }


        if(p1Pokemon[3].dexNumber == 72)
        {
            pokemon.setImageResource(R.drawable.a72);
        }


        if(p1Pokemon[3].dexNumber == 73)
        {
            pokemon.setImageResource(R.drawable.a73);
        }


        if(p1Pokemon[3].dexNumber == 74)
        {
            pokemon.setImageResource(R.drawable.a74);
        }

        if(p1Pokemon[3].dexNumber == 75)
        {
            pokemon.setImageResource(R.drawable.a75);
        }


        if(p1Pokemon[3].dexNumber == 76)
        {
            pokemon.setImageResource(R.drawable.a76);
        }


        if(p1Pokemon[3].dexNumber == 77)
        {
            pokemon.setImageResource(R.drawable.a77);
        }


        if(p1Pokemon[3].dexNumber == 78)
        {
            pokemon.setImageResource(R.drawable.a78);
        }


        if(p1Pokemon[3].dexNumber == 79)
        {
            pokemon.setImageResource(R.drawable.a79);
        }


        if(p1Pokemon[3].dexNumber == 80)
        {
            pokemon.setImageResource(R.drawable.a80);
        }


        if(p1Pokemon[3].dexNumber == 81)
        {
            pokemon.setImageResource(R.drawable.a81);
        }


        if(p1Pokemon[3].dexNumber == 82)
        {
            pokemon.setImageResource(R.drawable.a82);
        }


        if(p1Pokemon[3].dexNumber == 83)
        {
            pokemon.setImageResource(R.drawable.a83);
        }


        if(p1Pokemon[3].dexNumber == 84)
        {
            pokemon.setImageResource(R.drawable.a84);
        }


        if(p1Pokemon[3].dexNumber == 85)
        {
            pokemon.setImageResource(R.drawable.a85);
        }


        if(p1Pokemon[3].dexNumber == 86)
        {
            pokemon.setImageResource(R.drawable.a86);
        }

        if(p1Pokemon[3].dexNumber == 87)
        {
            pokemon.setImageResource(R.drawable.a87);
        }


        if(p1Pokemon[3].dexNumber == 88)
        {
            pokemon.setImageResource(R.drawable.a88);
        }


        if(p1Pokemon[3].dexNumber == 89)
        {
            pokemon.setImageResource(R.drawable.a89);
        }


        if(p1Pokemon[3].dexNumber == 90)
        {
            pokemon.setImageResource(R.drawable.a90);
        }

        if(p1Pokemon[3].dexNumber == 91)
        {
            pokemon.setImageResource(R.drawable.a91);
        }


        if(p1Pokemon[3].dexNumber == 92)
        {
            pokemon.setImageResource(R.drawable.a92);
        }


        if(p1Pokemon[3].dexNumber == 93)
        {
            pokemon.setImageResource(R.drawable.a93);
        }


        if(p1Pokemon[3].dexNumber == 94)
        {
            pokemon.setImageResource(R.drawable.a94);
        }


        if(p1Pokemon[3].dexNumber == 95)
        {
            pokemon.setImageResource(R.drawable.a95);
        }


        if(p1Pokemon[3].dexNumber == 96)
        {
            pokemon.setImageResource(R.drawable.a96);
        }


        if(p1Pokemon[3].dexNumber == 97)
        {
            pokemon.setImageResource(R.drawable.a97);
        }


        if(p1Pokemon[3].dexNumber == 98)
        {
            pokemon.setImageResource(R.drawable.a98);
        }


        if(p1Pokemon[3].dexNumber == 99)
        {
            pokemon.setImageResource(R.drawable.a99);
        }

    }

    public void setImage5()
    {
        ImageView pokemon= (ImageView) findViewById(R.id.gifp1);

        if(p1Pokemon[4].dexNumber == 1)
        {
            pokemon.setImageResource(R.drawable.a1);
        }

        if(p1Pokemon[4].dexNumber == 2)
        {
            pokemon.setImageResource(R.drawable.a2);
        }


        if(p1Pokemon[4].dexNumber == 3)
        {
            pokemon.setImageResource(R.drawable.a3);
        }


        if(p1Pokemon[4].dexNumber == 4)
        {
            pokemon.setImageResource(R.drawable.a4);
        }

        if(p1Pokemon[4].dexNumber == 5)
        {
            pokemon.setImageResource(R.drawable.a5);
        }


        if(p1Pokemon[4].dexNumber == 6)
        {
            pokemon.setImageResource(R.drawable.a6);
        }


        if(p1Pokemon[4].dexNumber == 7)
        {
            pokemon.setImageResource(R.drawable.a7);
        }

        if(p1Pokemon[4].dexNumber == 8)
        {
            pokemon.setImageResource(R.drawable.a8);
        }


        if(p1Pokemon[4].dexNumber == 9)
        {
            pokemon.setImageResource(R.drawable.a9);
        }


        if(p1Pokemon[4].dexNumber == 10)
        {
            pokemon.setImageResource(R.drawable.a10);
        }

        if(p1Pokemon[4].dexNumber == 11)
        {
            pokemon.setImageResource(R.drawable.a11);
        }


        if(p1Pokemon[4].dexNumber == 12)
        {
            pokemon.setImageResource(R.drawable.a12);
        }


        if(p1Pokemon[4].dexNumber == 13)
        {
            pokemon.setImageResource(R.drawable.a13);
        }


        if(p1Pokemon[4].dexNumber == 14)
        {
            pokemon.setImageResource(R.drawable.a14);
        }


        if(p1Pokemon[4].dexNumber == 15)
        {
            pokemon.setImageResource(R.drawable.a15);
        }


        if(p1Pokemon[4].dexNumber == 16)
        {
            pokemon.setImageResource(R.drawable.a16);
        }


        if(p1Pokemon[4].dexNumber == 17)
        {
            pokemon.setImageResource(R.drawable.a17);
        }


        if(p1Pokemon[4].dexNumber == 18)
        {
            pokemon.setImageResource(R.drawable.a18);
        }


        if(p1Pokemon[4].dexNumber == 19)
        {
            pokemon.setImageResource(R.drawable.a19);
        }


        if(p1Pokemon[4].dexNumber == 20)
        {
            pokemon.setImageResource(R.drawable.a20);
        }


        if(p1Pokemon[4].dexNumber == 21)
        {
            pokemon.setImageResource(R.drawable.a21);
        }


        if(p1Pokemon[4].dexNumber == 22)
        {
            pokemon.setImageResource(R.drawable.a22);
        }


        if(p1Pokemon[4].dexNumber == 23)
        {
            pokemon.setImageResource(R.drawable.a23);
        }


        if(p1Pokemon[4].dexNumber == 24)
        {
            pokemon.setImageResource(R.drawable.a24);
        }

        if(p1Pokemon[4].dexNumber == 25)
        {
            pokemon.setImageResource(R.drawable.a25);
        }


        if(p1Pokemon[4].dexNumber == 26)
        {
            pokemon.setImageResource(R.drawable.a26);
        }


        if(p1Pokemon[4].dexNumber == 27)
        {
            pokemon.setImageResource(R.drawable.a27);
        }


        if(p1Pokemon[4].dexNumber == 28)
        {
            pokemon.setImageResource(R.drawable.a28);
        }


        if(p1Pokemon[4].dexNumber == 29)
        {
            pokemon.setImageResource(R.drawable.a29);
        }

        if(p1Pokemon[4].dexNumber == 30)
        {
            pokemon.setImageResource(R.drawable.a30);
        }


        if(p1Pokemon[4].dexNumber == 31)
        {
            pokemon.setImageResource(R.drawable.a31);
        }

        if(p1Pokemon[4].dexNumber == 32)
        {
            pokemon.setImageResource(R.drawable.a32);
        }


        if(p1Pokemon[4].dexNumber == 33)
        {
            pokemon.setImageResource(R.drawable.a33);
        }


        if(p1Pokemon[4].dexNumber == 34)
        {
            pokemon.setImageResource(R.drawable.a34);
        }


        if(p1Pokemon[4].dexNumber == 35)
        {
            pokemon.setImageResource(R.drawable.a35);
        }


        if(p1Pokemon[4].dexNumber == 36)
        {
            pokemon.setImageResource(R.drawable.a36);
        }

        if(p1Pokemon[4].dexNumber == 37)
        {
            pokemon.setImageResource(R.drawable.a37);
        }


        if(p1Pokemon[4].dexNumber == 38)
        {
            pokemon.setImageResource(R.drawable.a38);
        }

        if(p1Pokemon[4].dexNumber == 39)
        {
            pokemon.setImageResource(R.drawable.a39);
        }


        if(p1Pokemon[4].dexNumber == 40)
        {
            pokemon.setImageResource(R.drawable.a40);
        }


        if(p1Pokemon[4].dexNumber == 41)
        {
            pokemon.setImageResource(R.drawable.a41);
        }

        if(p1Pokemon[4].dexNumber == 42)
        {
            pokemon.setImageResource(R.drawable.a42);
        }


        if(p1Pokemon[4].dexNumber == 43)
        {
            pokemon.setImageResource(R.drawable.a43);
        }


        if(p1Pokemon[4].dexNumber == 44)
        {
            pokemon.setImageResource(R.drawable.a44);
        }

        if(p1Pokemon[4].dexNumber == 45)
        {
            pokemon.setImageResource(R.drawable.a45);
        }


        if(p1Pokemon[4].dexNumber == 46)
        {
            pokemon.setImageResource(R.drawable.a46);
        }


        if(p1Pokemon[4].dexNumber == 47)
        {
            pokemon.setImageResource(R.drawable.a47);
        }


        if(p1Pokemon[4].dexNumber == 48)
        {
            pokemon.setImageResource(R.drawable.a48);
        }


        if(p1Pokemon[4].dexNumber == 49)
        {
            pokemon.setImageResource(R.drawable.a49);
        }


        if(p1Pokemon[4].dexNumber == 50)
        {
            pokemon.setImageResource(R.drawable.a50);
        }

        if(p1Pokemon[4].dexNumber == 51)
        {
            pokemon.setImageResource(R.drawable.a51);
        }

        if(p1Pokemon[4].dexNumber == 52)
        {
            pokemon.setImageResource(R.drawable.a52);
        }

        if(p1Pokemon[4].dexNumber == 53)
        {
            pokemon.setImageResource(R.drawable.a53);
        }


        if(p1Pokemon[4].dexNumber == 54)
        {
            pokemon.setImageResource(R.drawable.a54);
        }


        if(p1Pokemon[4].dexNumber == 55)
        {
            pokemon.setImageResource(R.drawable.a55);
        }


        if(p1Pokemon[4].dexNumber == 56)
        {
            pokemon.setImageResource(R.drawable.a56);
        }


        if(p1Pokemon[4].dexNumber == 57)
        {
            pokemon.setImageResource(R.drawable.a57);
        }


        if(p1Pokemon[4].dexNumber == 58)
        {
            pokemon.setImageResource(R.drawable.a5);
        }

        if(p1Pokemon[4].dexNumber == 59)
        {
            pokemon.setImageResource(R.drawable.a59);
        }


        if(p1Pokemon[4].dexNumber == 60)
        {
            pokemon.setImageResource(R.drawable.a60);
        }

        if(p1Pokemon[4].dexNumber == 61)
        {
            pokemon.setImageResource(R.drawable.a61);
        }


        if(p1Pokemon[4].dexNumber == 62)
        {
            pokemon.setImageResource(R.drawable.a62);
        }


        if(p1Pokemon[4].dexNumber == 63)
        {
            pokemon.setImageResource(R.drawable.a63);
        }


        if(p1Pokemon[4].dexNumber == 64)
        {
            pokemon.setImageResource(R.drawable.a64);
        }

        if(p1Pokemon[4].dexNumber == 65)
        {
            pokemon.setImageResource(R.drawable.a65);
        }

        if(p1Pokemon[4].dexNumber == 66)
        {
            pokemon.setImageResource(R.drawable.a66);
        }

        if(p1Pokemon[4].dexNumber == 67)
        {
            pokemon.setImageResource(R.drawable.a67);
        }


        if(p1Pokemon[4].dexNumber == 68)
        {
            pokemon.setImageResource(R.drawable.a68);
        }

        if(p1Pokemon[4].dexNumber == 69)
        {
            pokemon.setImageResource(R.drawable.a69);
        }


        if(p1Pokemon[4].dexNumber == 70)
        {
            pokemon.setImageResource(R.drawable.a70);
        }

        if(p1Pokemon[4].dexNumber == 71)
        {
            pokemon.setImageResource(R.drawable.a71);
        }


        if(p1Pokemon[4].dexNumber == 72)
        {
            pokemon.setImageResource(R.drawable.a72);
        }


        if(p1Pokemon[4].dexNumber == 73)
        {
            pokemon.setImageResource(R.drawable.a73);
        }


        if(p1Pokemon[4].dexNumber == 74)
        {
            pokemon.setImageResource(R.drawable.a74);
        }

        if(p1Pokemon[4].dexNumber == 75)
        {
            pokemon.setImageResource(R.drawable.a75);
        }


        if(p1Pokemon[4].dexNumber == 76)
        {
            pokemon.setImageResource(R.drawable.a76);
        }


        if(p1Pokemon[4].dexNumber == 77)
        {
            pokemon.setImageResource(R.drawable.a77);
        }


        if(p1Pokemon[4].dexNumber == 78)
        {
            pokemon.setImageResource(R.drawable.a78);
        }


        if(p1Pokemon[4].dexNumber == 79)
        {
            pokemon.setImageResource(R.drawable.a79);
        }


        if(p1Pokemon[4].dexNumber == 80)
        {
            pokemon.setImageResource(R.drawable.a80);
        }


        if(p1Pokemon[4].dexNumber == 81)
        {
            pokemon.setImageResource(R.drawable.a81);
        }


        if(p1Pokemon[4].dexNumber == 82)
        {
            pokemon.setImageResource(R.drawable.a82);
        }


        if(p1Pokemon[4].dexNumber == 83)
        {
            pokemon.setImageResource(R.drawable.a83);
        }


        if(p1Pokemon[4].dexNumber == 84)
        {
            pokemon.setImageResource(R.drawable.a84);
        }


        if(p1Pokemon[4].dexNumber == 85)
        {
            pokemon.setImageResource(R.drawable.a85);
        }


        if(p1Pokemon[4].dexNumber == 86)
        {
            pokemon.setImageResource(R.drawable.a86);
        }

        if(p1Pokemon[4].dexNumber == 87)
        {
            pokemon.setImageResource(R.drawable.a87);
        }


        if(p1Pokemon[4].dexNumber == 88)
        {
            pokemon.setImageResource(R.drawable.a88);
        }


        if(p1Pokemon[4].dexNumber == 89)
        {
            pokemon.setImageResource(R.drawable.a89);
        }


        if(p1Pokemon[4].dexNumber == 90)
        {
            pokemon.setImageResource(R.drawable.a90);
        }

        if(p1Pokemon[4].dexNumber == 91)
        {
            pokemon.setImageResource(R.drawable.a91);
        }


        if(p1Pokemon[4].dexNumber == 92)
        {
            pokemon.setImageResource(R.drawable.a92);
        }


        if(p1Pokemon[4].dexNumber == 93)
        {
            pokemon.setImageResource(R.drawable.a93);
        }


        if(p1Pokemon[4].dexNumber == 94)
        {
            pokemon.setImageResource(R.drawable.a94);
        }


        if(p1Pokemon[4].dexNumber == 95)
        {
            pokemon.setImageResource(R.drawable.a95);
        }


        if(p1Pokemon[4].dexNumber == 96)
        {
            pokemon.setImageResource(R.drawable.a96);
        }


        if(p1Pokemon[4].dexNumber == 97)
        {
            pokemon.setImageResource(R.drawable.a97);
        }


        if(p1Pokemon[4].dexNumber == 98)
        {
            pokemon.setImageResource(R.drawable.a98);
        }


        if(p1Pokemon[4].dexNumber == 99)
        {
            pokemon.setImageResource(R.drawable.a99);
        }

    }

    public void setImage6()
    {
        ImageView pokemon= (ImageView) findViewById(R.id.gifp1);

        if(p1Pokemon[5].dexNumber == 1)
        {
            pokemon.setImageResource(R.drawable.a1);
        }

        if(p1Pokemon[5].dexNumber == 2)
        {
            pokemon.setImageResource(R.drawable.a2);
        }


        if(p1Pokemon[5].dexNumber == 3)
        {
            pokemon.setImageResource(R.drawable.a3);
        }


        if(p1Pokemon[5].dexNumber == 4)
        {
            pokemon.setImageResource(R.drawable.a4);
        }

        if(p1Pokemon[5].dexNumber == 5)
        {
            pokemon.setImageResource(R.drawable.a5);
        }


        if(p1Pokemon[5].dexNumber == 6)
        {
            pokemon.setImageResource(R.drawable.a6);
        }


        if(p1Pokemon[5].dexNumber == 7)
        {
            pokemon.setImageResource(R.drawable.a7);
        }

        if(p1Pokemon[5].dexNumber == 8)
        {
            pokemon.setImageResource(R.drawable.a8);
        }


        if(p1Pokemon[5].dexNumber == 9)
        {
            pokemon.setImageResource(R.drawable.a9);
        }


        if(p1Pokemon[5].dexNumber == 10)
        {
            pokemon.setImageResource(R.drawable.a10);
        }

        if(p1Pokemon[5].dexNumber == 11)
        {
            pokemon.setImageResource(R.drawable.a11);
        }


        if(p1Pokemon[5].dexNumber == 12)
        {
            pokemon.setImageResource(R.drawable.a12);
        }


        if(p1Pokemon[5].dexNumber == 13)
        {
            pokemon.setImageResource(R.drawable.a13);
        }


        if(p1Pokemon[5].dexNumber == 14)
        {
            pokemon.setImageResource(R.drawable.a14);
        }


        if(p1Pokemon[5].dexNumber == 15)
        {
            pokemon.setImageResource(R.drawable.a15);
        }


        if(p1Pokemon[5].dexNumber == 16)
        {
            pokemon.setImageResource(R.drawable.a16);
        }


        if(p1Pokemon[5].dexNumber == 17)
        {
            pokemon.setImageResource(R.drawable.a17);
        }


        if(p1Pokemon[5].dexNumber == 18)
        {
            pokemon.setImageResource(R.drawable.a18);
        }


        if(p1Pokemon[5].dexNumber == 19)
        {
            pokemon.setImageResource(R.drawable.a19);
        }


        if(p1Pokemon[5].dexNumber == 20)
        {
            pokemon.setImageResource(R.drawable.a20);
        }


        if(p1Pokemon[5].dexNumber == 21)
        {
            pokemon.setImageResource(R.drawable.a21);
        }


        if(p1Pokemon[5].dexNumber == 22)
        {
            pokemon.setImageResource(R.drawable.a22);
        }


        if(p1Pokemon[5].dexNumber == 23)
        {
            pokemon.setImageResource(R.drawable.a23);
        }


        if(p1Pokemon[5].dexNumber == 24)
        {
            pokemon.setImageResource(R.drawable.a24);
        }

        if(p1Pokemon[5].dexNumber == 25)
        {
            pokemon.setImageResource(R.drawable.a25);
        }


        if(p1Pokemon[5].dexNumber == 26)
        {
            pokemon.setImageResource(R.drawable.a26);
        }


        if(p1Pokemon[5].dexNumber == 27)
        {
            pokemon.setImageResource(R.drawable.a27);
        }


        if(p1Pokemon[5].dexNumber == 28)
        {
            pokemon.setImageResource(R.drawable.a28);
        }


        if(p1Pokemon[5].dexNumber == 29)
        {
            pokemon.setImageResource(R.drawable.a29);
        }

        if(p1Pokemon[5].dexNumber == 30)
        {
            pokemon.setImageResource(R.drawable.a30);
        }


        if(p1Pokemon[5].dexNumber == 31)
        {
            pokemon.setImageResource(R.drawable.a31);
        }

        if(p1Pokemon[5].dexNumber == 32)
        {
            pokemon.setImageResource(R.drawable.a32);
        }


        if(p1Pokemon[5].dexNumber == 33)
        {
            pokemon.setImageResource(R.drawable.a33);
        }


        if(p1Pokemon[5].dexNumber == 34)
        {
            pokemon.setImageResource(R.drawable.a34);
        }


        if(p1Pokemon[5].dexNumber == 35)
        {
            pokemon.setImageResource(R.drawable.a35);
        }


        if(p1Pokemon[5].dexNumber == 36)
        {
            pokemon.setImageResource(R.drawable.a36);
        }

        if(p1Pokemon[5].dexNumber == 37)
        {
            pokemon.setImageResource(R.drawable.a37);
        }


        if(p1Pokemon[5].dexNumber == 38)
        {
            pokemon.setImageResource(R.drawable.a38);
        }

        if(p1Pokemon[5].dexNumber == 39)
        {
            pokemon.setImageResource(R.drawable.a39);
        }


        if(p1Pokemon[5].dexNumber == 40)
        {
            pokemon.setImageResource(R.drawable.a40);
        }


        if(p1Pokemon[5].dexNumber == 41)
        {
            pokemon.setImageResource(R.drawable.a41);
        }

        if(p1Pokemon[5].dexNumber == 42)
        {
            pokemon.setImageResource(R.drawable.a42);
        }


        if(p1Pokemon[5].dexNumber == 43)
        {
            pokemon.setImageResource(R.drawable.a43);
        }


        if(p1Pokemon[5].dexNumber == 44)
        {
            pokemon.setImageResource(R.drawable.a44);
        }

        if(p1Pokemon[5].dexNumber == 45)
        {
            pokemon.setImageResource(R.drawable.a45);
        }


        if(p1Pokemon[5].dexNumber == 46)
        {
            pokemon.setImageResource(R.drawable.a46);
        }


        if(p1Pokemon[5].dexNumber == 47)
        {
            pokemon.setImageResource(R.drawable.a47);
        }


        if(p1Pokemon[5].dexNumber == 48)
        {
            pokemon.setImageResource(R.drawable.a48);
        }


        if(p1Pokemon[5].dexNumber == 49)
        {
            pokemon.setImageResource(R.drawable.a49);
        }


        if(p1Pokemon[5].dexNumber == 50)
        {
            pokemon.setImageResource(R.drawable.a50);
        }

        if(p1Pokemon[5].dexNumber == 51)
        {
            pokemon.setImageResource(R.drawable.a51);
        }

        if(p1Pokemon[5].dexNumber == 52)
        {
            pokemon.setImageResource(R.drawable.a52);
        }

        if(p1Pokemon[5].dexNumber == 53)
        {
            pokemon.setImageResource(R.drawable.a53);
        }


        if(p1Pokemon[5].dexNumber == 54)
        {
            pokemon.setImageResource(R.drawable.a54);
        }


        if(p1Pokemon[5].dexNumber == 55)
        {
            pokemon.setImageResource(R.drawable.a55);
        }


        if(p1Pokemon[5].dexNumber == 56)
        {
            pokemon.setImageResource(R.drawable.a56);
        }


        if(p1Pokemon[5].dexNumber == 57)
        {
            pokemon.setImageResource(R.drawable.a57);
        }


        if(p1Pokemon[5].dexNumber == 58)
        {
            pokemon.setImageResource(R.drawable.a5);
        }

        if(p1Pokemon[5].dexNumber == 59)
        {
            pokemon.setImageResource(R.drawable.a59);
        }


        if(p1Pokemon[5].dexNumber == 60)
        {
            pokemon.setImageResource(R.drawable.a60);
        }

        if(p1Pokemon[5].dexNumber == 61)
        {
            pokemon.setImageResource(R.drawable.a61);
        }


        if(p1Pokemon[5].dexNumber == 62)
        {
            pokemon.setImageResource(R.drawable.a62);
        }


        if(p1Pokemon[5].dexNumber == 63)
        {
            pokemon.setImageResource(R.drawable.a63);
        }


        if(p1Pokemon[5].dexNumber == 64)
        {
            pokemon.setImageResource(R.drawable.a64);
        }

        if(p1Pokemon[5].dexNumber == 65)
        {
            pokemon.setImageResource(R.drawable.a65);
        }

        if(p1Pokemon[5].dexNumber == 66)
        {
            pokemon.setImageResource(R.drawable.a66);
        }

        if(p1Pokemon[5].dexNumber == 67)
        {
            pokemon.setImageResource(R.drawable.a67);
        }


        if(p1Pokemon[5].dexNumber == 68)
        {
            pokemon.setImageResource(R.drawable.a68);
        }

        if(p1Pokemon[5].dexNumber == 69)
        {
            pokemon.setImageResource(R.drawable.a69);
        }


        if(p1Pokemon[5].dexNumber == 70)
        {
            pokemon.setImageResource(R.drawable.a70);
        }

        if(p1Pokemon[5].dexNumber == 71)
        {
            pokemon.setImageResource(R.drawable.a71);
        }


        if(p1Pokemon[5].dexNumber == 72)
        {
            pokemon.setImageResource(R.drawable.a72);
        }


        if(p1Pokemon[5].dexNumber == 73)
        {
            pokemon.setImageResource(R.drawable.a73);
        }


        if(p1Pokemon[5].dexNumber == 74)
        {
            pokemon.setImageResource(R.drawable.a74);
        }

        if(p1Pokemon[5].dexNumber == 75)
        {
            pokemon.setImageResource(R.drawable.a75);
        }


        if(p1Pokemon[5].dexNumber == 76)
        {
            pokemon.setImageResource(R.drawable.a76);
        }


        if(p1Pokemon[5].dexNumber == 77)
        {
            pokemon.setImageResource(R.drawable.a77);
        }


        if(p1Pokemon[5].dexNumber == 78)
        {
            pokemon.setImageResource(R.drawable.a78);
        }


        if(p1Pokemon[5].dexNumber == 79)
        {
            pokemon.setImageResource(R.drawable.a79);
        }


        if(p1Pokemon[5].dexNumber == 80)
        {
            pokemon.setImageResource(R.drawable.a80);
        }


        if(p1Pokemon[5].dexNumber == 81)
        {
            pokemon.setImageResource(R.drawable.a81);
        }


        if(p1Pokemon[5].dexNumber == 82)
        {
            pokemon.setImageResource(R.drawable.a82);
        }


        if(p1Pokemon[5].dexNumber == 83)
        {
            pokemon.setImageResource(R.drawable.a83);
        }


        if(p1Pokemon[5].dexNumber == 84)
        {
            pokemon.setImageResource(R.drawable.a84);
        }


        if(p1Pokemon[5].dexNumber == 85)
        {
            pokemon.setImageResource(R.drawable.a85);
        }


        if(p1Pokemon[5].dexNumber == 86)
        {
            pokemon.setImageResource(R.drawable.a86);
        }

        if(p1Pokemon[5].dexNumber == 87)
        {
            pokemon.setImageResource(R.drawable.a87);
        }


        if(p1Pokemon[5].dexNumber == 88)
        {
            pokemon.setImageResource(R.drawable.a88);
        }


        if(p1Pokemon[5].dexNumber == 89)
        {
            pokemon.setImageResource(R.drawable.a89);
        }


        if(p1Pokemon[5].dexNumber == 90)
        {
            pokemon.setImageResource(R.drawable.a90);
        }

        if(p1Pokemon[5].dexNumber == 91)
        {
            pokemon.setImageResource(R.drawable.a91);
        }


        if(p1Pokemon[5].dexNumber == 92)
        {
            pokemon.setImageResource(R.drawable.a92);
        }


        if(p1Pokemon[5].dexNumber == 93)
        {
            pokemon.setImageResource(R.drawable.a93);
        }


        if(p1Pokemon[5].dexNumber == 94)
        {
            pokemon.setImageResource(R.drawable.a94);
        }


        if(p1Pokemon[5].dexNumber == 95)
        {
            pokemon.setImageResource(R.drawable.a95);
        }


        if(p1Pokemon[5].dexNumber == 96)
        {
            pokemon.setImageResource(R.drawable.a96);
        }


        if(p1Pokemon[5].dexNumber == 97)
        {
            pokemon.setImageResource(R.drawable.a97);
        }


        if(p1Pokemon[5].dexNumber == 98)
        {
            pokemon.setImageResource(R.drawable.a98);
        }


        if(p1Pokemon[5].dexNumber == 99)
        {
            pokemon.setImageResource(R.drawable.a99);
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
}
