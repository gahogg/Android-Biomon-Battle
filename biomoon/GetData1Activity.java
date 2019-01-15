package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import static com.greg.android.biomoon.Globals.name2;
import static com.greg.android.biomoon.HomeActivity.isP2CPU;
import static com.greg.android.biomoon.HomeActivity.playingOnline;

/**
 * Created by Greg on 2017-06-25.
 */

public class GetData1Activity extends Activity {
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


        if(isP2CPU)
        {
            name2 = "Joey";
            Intent intent = new Intent(this, Play1Activity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, GetData2Activity.class);
            startActivity(intent);
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
    protected void onResume()  {
        super.onResume();
        if(!AudioPlay.isplayingAudio)
        AudioPlay.playAudio(this, R.raw.openingtheme);

    }
}
