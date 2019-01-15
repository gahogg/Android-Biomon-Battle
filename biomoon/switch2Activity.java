package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.greg.android.biomoon.Globals.activePokemon2;
import static com.greg.android.biomoon.Globals.name2;
import static com.greg.android.biomoon.Globals.p2Pokemon;

/**
 * Created by Greg on 2017-07-04.
 */

public class switch2Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch2activity);

        TextView tv1 = (TextView)findViewById(R.id.tv1);
        String msg1 = name2 + ", who are you going to switch in?";
        tv1.setText(msg1);

        Button b1a = (Button)findViewById(R.id.pokemon1_1);
        b1a.setText(p2Pokemon[0].name);
        Button b2a = (Button)findViewById(R.id.pokemon2_1);
        b2a.setText(p2Pokemon[1].name);
        Button b3a = (Button)findViewById(R.id.pokemon3_1);
        b3a.setText(p2Pokemon[2].name);
        Button b4a = (Button)findViewById(R.id.pokemon4_1);
        b4a.setText(p2Pokemon[3].name);
        Button b5a = (Button)findViewById(R.id.pokemon5_1);
        b5a.setText(p2Pokemon[4].name);
        Button b6a = (Button)findViewById(R.id.pokemon6_1);
        b6a.setText(p2Pokemon[5].name);

        if(p2Pokemon[0].fainted)
        {
            b1a.setEnabled(false);
        }
        if(p2Pokemon[1].fainted)
        {
            b2a.setEnabled(false);
        }
        if(p2Pokemon[2].fainted)
        {
            b3a.setEnabled(false);
        }
        if(p2Pokemon[3].fainted)
        {
            b4a.setEnabled(false);
        }
        if(p2Pokemon[4].fainted)
        {
            b5a.setEnabled(false);
        }
        if(p2Pokemon[5].fainted)
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
        p2Pokemon[0].resetPokemon();
        activePokemon2 = p2Pokemon[0];
        activePokemon2.fainted = false;

        Intent intent = new Intent(this, Battle1Activity.class);
        startActivity(intent);

    }

    public void Choose1Lead2(View view) {
        p2Pokemon[1].resetPokemon();
        activePokemon2 = p2Pokemon[1];
        activePokemon2.fainted = false;

        Intent intent = new Intent(this, Battle1Activity.class);
        startActivity(intent);

    }

    public void Choose1Lead3(View view) {
        p2Pokemon[2].resetPokemon();

        activePokemon2 = p2Pokemon[2];
        activePokemon2.fainted = false;

        Intent intent = new Intent(this, Battle1Activity.class);
        startActivity(intent);

    }

    public void Choose1Lead4(View view) {
        p2Pokemon[3].resetPokemon();
        activePokemon2 = p2Pokemon[3];
        activePokemon2.fainted = false;

        Intent intent = new Intent(this, Battle1Activity.class);
        startActivity(intent);
    }

    public void Choose1Lead5(View view) {
        p2Pokemon[4].resetPokemon();
        activePokemon2 = p2Pokemon[4];
        activePokemon2.fainted = false;

        Intent intent = new Intent(this, Battle1Activity.class);
        startActivity(intent);
    }

    public void Choose1Lead6(View view) {
        p2Pokemon[5].resetPokemon();
        activePokemon2 = p2Pokemon[5];
        activePokemon2.fainted = false;

        Intent intent = new Intent(this, Battle1Activity.class);
        startActivity(intent);

    }
}
