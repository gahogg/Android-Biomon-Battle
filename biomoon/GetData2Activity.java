package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Greg on 2017-06-25.
 */

public class GetData2Activity extends Activity{
    boolean stayingInApp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_data2_layout);
        stayingInApp=false;

        Button b = (Button) findViewById(R.id.button);
        TextView t = (TextView)findViewById(R.id.tv) ;
        EditText e = (EditText)findViewById(R.id.name2Box);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/turtles.otf");
        b.setTypeface(custom_font);
        t.setTypeface(custom_font);
        e.setTypeface(custom_font);
    }

    public void OpenPlay1Activity (View v)  {
        stayingInApp =true;
        EditText edit = (EditText)findViewById(R.id.name2Box);
        Globals.name2 = edit.getText().toString();

        Intent intent = new Intent(this, Play1Activity.class);
        startActivity(intent);
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


}
