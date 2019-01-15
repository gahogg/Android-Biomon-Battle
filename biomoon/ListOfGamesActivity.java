package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.greg.android.biomoon.CreateRoomActivtiy.roomOwner;
import static com.greg.android.biomoon.HomeActivity.madeAHost;
import static com.greg.android.biomoon.HomeActivity.madeHostEarlier;
import static com.greg.android.biomoon.HomeActivity.otherPlayersKey;
import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;

/**
 * Created by Greg on 2017-09-01.
 */

public class ListOfGamesActivity extends Activity{
    ListView listView;
    TextView textView;
    ArrayList<String> arrayListForDisplay = new ArrayList<>();
    ArrayList<Integer> gameNumberArrayList = new ArrayList<>();
    ArrayList<Boolean> availabilityArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_games_layout);

        listView = (ListView)findViewById(R.id.listView1);
        textView= (TextView)findViewById(R.id.labelTextView);
        textView.setText("Choose a game!");
        final ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, arrayListForDisplay);

        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();

        listView.setAdapter(mAdapter);

        ref1.child("Games").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(!madeHostEarlier)
                {
                    //Reset arrayLists on fetching of data
                    arrayListForDisplay.clear();
                    gameNumberArrayList.clear();
                    availabilityArrayList.clear();

                    for (DataSnapshot ds: dataSnapshot.getChildren())
                    {
                        String number = ds.getKey();
                        if(gameFullyMade(Integer.parseInt(number) , dataSnapshot))
                        {
                            boolean hostExists;

                            String available = ds.child("Available").getValue().toString();

                            String hostStr="";

                            hostExists = ds.child("Host").exists();

                            if(hostExists)
                            {
                                hostStr = ds.child("Host").getValue().toString();
                            }

                            availabilityArrayList.add(Boolean.parseBoolean(available));
                            gameNumberArrayList.add(Integer.parseInt(number));

                            //Lol complicated for checking if it is an available game xD
                            if(availabilityArrayList.get(Integer.parseInt(number)-1))
                                arrayListForDisplay.add("Game "  + number  + "    "+ " Available!    " + hostStr);
                            else
                            {
                                arrayListForDisplay.add("Game "  + number  + "  "+ " Unavailable " + hostStr);
                            }

                            mAdapter.notifyDataSetChanged();
                        }

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError e)
            {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {

                //If the selected game is available, join that game
                if (availabilityArrayList.get(myItemInt))
                {
                    gameRoomNumGlobal = myItemInt+1;
                    joinGame();
                }

            }


        });

    }

    private boolean gameFullyMade(int i, DataSnapshot dataSnapshot) {
        if(dataSnapshot.child(Integer.toString(i)).child("Available").exists() && dataSnapshot.child(Integer.toString(i)).child("Has Chosen").exists() && dataSnapshot.child(Integer.toString(i)).child("Users").exists())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void joinGame()
    {
        Intent intent = new Intent(this, ActualWaitActivity.class);
        startActivity(intent);
    }


    public void create(View view)
    {
        Intent intent = new Intent(this, CreateRoomActivtiy.class);
        startActivity(intent);
    }
}
