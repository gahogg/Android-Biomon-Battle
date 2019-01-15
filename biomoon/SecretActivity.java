package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Greg on 2017-08-31.
 */

public class SecretActivity extends Activity{
    private EditText passwordField;
    private Button resetBtn;
    private Button returnBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secret_activity_layout);

        passwordField = (EditText)findViewById(R.id.passwordEditText);
        resetBtn = (Button)findViewById(R.id.resetBtn);
        returnBtn = (Button)findViewById(R.id.returnBtn);

    }


    public void resetMethod(View view) {

        resetBtn.setClickable(true);

        //Get rid of entire Games Directory
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
        ref1.child("Games").removeValue();

        //Add back in a game
        ref1.child("Games").child("1").child("Available").setValue("false");
        ref1.child("Games").child("1").child("Users").child("Default").setValue("0");
        ref1.child("Games").child("1").child("Has Chosen").child("Default").setValue("true");

    }

    public void enterMethod(View view) {
        if(isCorrectPassword())
        resetBtn.setClickable(true);
    }



    private boolean isCorrectPassword() {
        String str = passwordField.getText().toString().trim();
        if(str.equals("Apples123"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void returnMethod(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
