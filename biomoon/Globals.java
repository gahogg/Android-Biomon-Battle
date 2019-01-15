package com.greg.android.biomoon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import static com.greg.android.biomoon.HomeActivity.healthAfterHit2;

import static android.media.CamcorderProfile.get;
import static com.greg.android.biomoon.HomeActivity.host;
import static com.greg.android.biomoon.HomeActivity.playingOnline;
import static com.greg.android.biomoon.HomeActivity.otherPlayersKey;
import static com.greg.android.biomoon.WaitActivity.gameRoomNumGlobal;
import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;
//import static com.greg.android.biomoon.WaitActivity.nameKeyOurs;


/**
 * Created by Greg on 2017-06-25.
 */

 public class Globals extends Activity {
    private static final String TAG = "Globals";
    static String name1 = null;
    static String name2 = null;
    static String user1Lead = null;
    static String user2Lead = null;
    static int hasChosenLead1 = 0;
    static int hasChosenLead2 = 0;

    public static String reasonForError1, reasonForError2, moveUse1, moveUse2, statusMove1, statusMove2,
            pokemonStatused1, pokemonStatused2, effectiveness1, effectiveness2, fainted1, fainted2 =null;

    public static int healthAfterHit1 , healthAfterRecoil1, healthAfterRecoil2,
            healthAfterStatusDamage1, healthAfterStatusDamage2, healthAfterWeatherEffects1, getHealthAfterWeatherEffects2 =0;

    //Easy way to print all the data
    private static String makeMovePhrase = "";

    //Arraylists for each pokemon specification
    public static ArrayList<String> name = new ArrayList();
    public static ArrayList<Integer> dexNumber = new ArrayList();
    public static ArrayList<String> ability = new ArrayList();
    public static ArrayList<Integer> type1 = new ArrayList();
    public static ArrayList<Integer> type2 = new ArrayList();
    public static ArrayList<Integer> health = new ArrayList();
    public static ArrayList<Integer> attack = new ArrayList();
    public static ArrayList<Integer> defense = new ArrayList();
    public static ArrayList<Integer> spAttack = new ArrayList();
    public static ArrayList<Integer> spDefense = new ArrayList();
    public static ArrayList<Integer> speed = new ArrayList();
    public static ArrayList<Integer> move1 = new ArrayList();
    public static ArrayList<Integer> move2 = new ArrayList();
    public static ArrayList<Integer> move3 = new ArrayList();
    public static ArrayList<Integer> move4 = new ArrayList();
    public static ArrayList<String> item = new ArrayList();

    //Arraylists for each move specification
    public static ArrayList<Integer> moveNumber = new ArrayList();
    public static ArrayList<String> moveName = new ArrayList();
    public static ArrayList<Integer> moveType = new ArrayList();
    public static ArrayList<Integer> moveCategory = new ArrayList();
    public static ArrayList<Integer> movePP = new ArrayList();
    public static ArrayList<Integer> movePower = new ArrayList();
    public static ArrayList<Integer> moveAccuracy = new ArrayList();

    public static AbstractBiomon[] p1Pokemon = new AbstractBiomon[6];
    public static AbstractBiomon[] p2Pokemon = new AbstractBiomon[6];
    public static AbstractBiomon[] pokemon = new AbstractBiomon[260];
    public static AbstractMove[] moves = new AbstractMove[430];
    public static AbstractMove m1;
    public static AbstractMove m2;
    public static AbstractMove m3;
    public static AbstractMove m4;
    public static AbstractBiomon activePokemon1;
    public static AbstractMove usedMove1;
    public static AbstractMove usedMove2;
    public static AbstractBiomon activePokemon2;

    public static double p1Damage = 0.0;
    public static double p2Damage = 0.0;

    public static boolean p1Switch = false;
    public static boolean p2Switch = false;

    public static boolean p1LS = false;
    public static boolean p1Ref = false;
    public static boolean p2LS = false;
    public static boolean p2Ref = false;
    public static int p1LSTurnsLeft;
    public static int p2LSTurnsLeft;
    public static int p1RefTurnsLeft;
    public static int p2RefTurnsLeft;

    public static String reason1;
    public static String reason2;

    // 0 is no weather, 1 is sun, 2 is rain, 3 is sandstorm, 4 is hail
    public static int weather = 0;
    public static int weatherTurnsLeft;

    public static int pokemonP2Sees = 0;
    public static int start1=0;
    public static int start2=0;
    public static int absoluteStart1=0;
    public static int absoluteStart2=0;
    public static int winner =0;

    public static int absoluteOrderOfMoves=0;
    public static double p1Effectiveness=0;
    public static double p2Effectiveness=0;
    public static int healthBeforeHealing1=0;
    public static int healthBeforeHealing2=0;
    public static int healthAfterHealing1=0;
    public static int healthAfterHealing2=0;

    public static boolean player1Healed=false;
    public static boolean player2Healed=false;

    public static boolean p1Critt  =false;
    public static boolean p2Critt  =false;

    //Just for the client (duh) it is because before online stuff
    //I didn't have hit boolean variables, only functions,
    //so these are read in from the database to use instead of
    //the functions
    public static boolean clientHit1  =false;
    public static boolean clientHit2  =false;

    public static int clientAttackStages1  = 0;
    public static int clientDefenseStages1  = 0;
    public static int clientSpdefenseStages1  = 0;
    public static int clientSpattackStages1  = 0;
    public static int clientSpeedStages1  = 0;
    public static int clientAccuracyStages1  = 0;
    public static int clientEvasionStages1  = 0;
    public static int clientAttackStages2  = 0;
    public static int clientDefenseStages2  = 0;
    public static int clientSpdefenseStages2  = 0;
    public static int clientSpattackStages2  = 0;
    public static int clientSpeedStages2  = 0;
    public static int clientAccuracyStages2  = 0;
    public static int clientEvasionStages2  = 0;

    public static ArrayList<String> allTheKeys = new ArrayList<>();

    public static void getMoves(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
        int i = 0;
        String mLine = null;

        boolean eof = false;

        while (!eof) {
            mLine = reader.readLine();
            if (mLine == null) {
                eof = true;
            } else {

                int tempInt = Integer.parseInt(mLine);
                moveNumber.add(tempInt);
                makeMovePhrase(mLine);

                mLine = reader.readLine();
                moveName.add(mLine);
                makeMovePhrase(mLine);

                mLine = reader.readLine();
                moveType.add(Integer.parseInt(mLine));
                makeMovePhrase(mLine);

                mLine = reader.readLine();
                moveCategory.add(Integer.parseInt(mLine));
                makeMovePhrase(mLine);

                mLine = reader.readLine();
                movePP.add(Integer.parseInt(mLine));
                makeMovePhrase(mLine);

                mLine = reader.readLine();
                movePower.add(Integer.parseInt(mLine));
                makeMovePhrase(mLine);

                mLine = reader.readLine();
                moveAccuracy.add(Integer.parseInt(mLine));
                makeMovePhrase(mLine);

                moves[i] = new AbstractMove(moveNumber.get(i), moveName.get(i), moveType.get(i), moveCategory.get(i), movePP.get(i), movePower.get(i), moveAccuracy.get(i)) {
                };
                i++;
            }
        }

    }

    public static void getPokemon(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
        int i = 0;
        String mLine = null;

        boolean eof = false;

        while (!eof) {
            mLine = reader.readLine();
            if (mLine == null) {
                eof = true;
            } else {

                name.add(mLine);
                makePokemonPhrase(mLine);

                //read in pokemon dex number
                mLine = reader.readLine();
                dexNumber.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in ability
                mLine = reader.readLine();
                ability.add(mLine);
                makePokemonPhrase(mLine);

                //read in type 1
                mLine = reader.readLine();
                type1.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in type 2
                mLine = reader.readLine();
                type2.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in health
                mLine = reader.readLine();
                health.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in attack
                mLine = reader.readLine();
                attack.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in defense
                mLine = reader.readLine();
                defense.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in special attack
                mLine = reader.readLine();
                spAttack.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in special defense
                mLine = reader.readLine();
                spDefense.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in speed
                mLine = reader.readLine();
                speed.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in move 1
                mLine = reader.readLine();
                move1.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in move 2
                mLine = reader.readLine();
                move2.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in move 3
                mLine = reader.readLine();
                move3.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in move 4
                mLine = reader.readLine();
                move4.add(Integer.parseInt(mLine));
                makePokemonPhrase(mLine);

                //read in item
                mLine = reader.readLine();
                item.add(mLine);
                makePokemonPhrase(mLine);

                //if the move number = move 1, make a pokemon with those moves
                for (int x = 0; x < 417; x++) {
                    if (x == move1.get(i)) {
                        m1 = moves[x];

                    }
                    if (x == move2.get(i)) {
                        m2 = moves[x];

                    }
                    if (x == move3.get(i)) {
                        m3 = moves[x];

                    }
                    if (x == move4.get(i)) {
                        m4 = moves[x];

                    }
                }

                pokemon[i] = new AbstractBiomon(name.get(i), type1.get(i), type2.get(i), ability.get(i), item.get(i), "healthy", m1, m2, m3, m4, dexNumber.get(i), health.get(i), attack.get(i), defense.get(i), spAttack.get(i), spDefense.get(i), speed.get(i), false, false, false) {
                };

                i++;
            }
        }

        Object[] indices1 = numsNoDuplicates(6, 98).toArray();

        Random rand = new Random();
        Set<Integer> numbers = new HashSet<Integer>();
        int n;

        while(numbers.size()<6 )
        {
            boolean addIt=true;
            n= rand.nextInt(98);

            for(int a=0; a<6; a++)
            {
                if(n == (int)indices1[a])
                {
                    addIt=false;
                }
            }

            if(addIt)
            {
                numbers.add(n);
            }
        }

        Object[] indices2 = numbers.toArray();

        for (int q = 0; q < 6; q++)
        {
            p1Pokemon[q] = pokemon[(int)indices1[q]];
            p1Pokemon[q].orderNum = q;

            p2Pokemon[q] = pokemon[(int)indices2[q]];
            p2Pokemon[q].orderNum = q;
        }

        //these need to be the same!!! P1 on the one phone has to read P1's Pokemon on the other phone, so that
        //they can see them as p2 on their phone
        if(playingOnline)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            //Putting p1s Pokemon on the database
            for(int a=0; a<6; a++)
            {
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Pokemon").child(Integer.toString(a)).setValue(p1Pokemon[a].dexNumber);
            }

            //Now we need to get the key of the other player
            getOtherPlayerKey();

            //Put our name on the database
            giveDatabaseOurName();

        }

    }

    private static void giveDatabaseOurName() {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Name").setValue(name1);
    }

    public static void getOtherPlayerKey() {
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref2, ref3, ref4;
        ref2 = ref1.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users");

        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                long count = dataSnapshot.getChildrenCount();
                Log.d(TAG, "onDataChange: " + count);

                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    String value = ds.getKey().toString();
                    allTheKeys.add(value); //add result into array list
                }

                for(int i=0;i<3; i++)
                {
                    if(!allTheKeys.get(i).equals(nameKeyOurs) && !allTheKeys.get(i).equals("Default"))
                    {
                        otherPlayersKey = allTheKeys.get(i);
                    }
                }

                Log.d(TAG, "Other Player's Key: " + otherPlayersKey);

            }

            @Override
            public void onCancelled(DatabaseError e)
            {

            }
        });


    }

    public static boolean isMove1(String s) {
        return usedMove1.name.equals(s);
    }

    public static boolean isMove2(String s) {
        return usedMove2.name.equals(s);
    }

    public static void move1DirectlyAfterAttackingEffects() {
        int n = 0;

        if (isMove1("fire punch")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                activePokemon2.getStatused("burn");
                p2Pokemon[activePokemon2.orderNum].getStatused("burn");
            }
        } else if (isMove1("ice punch")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                activePokemon2.getStatused("frozen");
                p2Pokemon[activePokemon2.orderNum].getStatused("frozen");
            }
        } else if (isMove1("fake out") && p1Damage != 0) {
            activePokemon2.flinch = true;
            reason2 = "Flinched";
        } else if (isMove1("thunder punch")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                activePokemon2.getStatused("paralyze");
                p2Pokemon[activePokemon2.orderNum].getStatused("paralyze");
            }
        } else if (isMove1("rolling kick")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 30) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("sand attack")) {
            statChange("accuracy", 1, -1);
        } else if (isMove1("headbutt") || isMove1("bite")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 30) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("body slam")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.getStatused("paralyze");
                p2Pokemon[activePokemon2.orderNum].getStatused("paralyze");
            }
        } else if (isMove1("poison sting") || isMove1("poison jab")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 30) {
                activePokemon2.getStatused("poison");
                p2Pokemon[activePokemon2.orderNum].getStatused("poison");
            }
        } else if (isMove1("acid")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                statChange("spdefense", 2, -1);
            }
        } else if (isMove1("flamethrower")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                activePokemon2.getStatused("burn");
                p2Pokemon[activePokemon2.orderNum].getStatused("burn");
            }
        } else if (isMove1("poison sting") || isMove1("poison jab")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 30) {
                activePokemon2.getStatused("poison");
                p2Pokemon[activePokemon2.orderNum].getStatused("poison");
            }
        } else if (isMove1("ice beam") || isMove1("blizzard")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                activePokemon2.getStatused("frozen");
                p2Pokemon[activePokemon2.orderNum].getStatused("frozen");
            }
        } else if (isMove1("psybeam")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.confused = true;
            }
        } else if (isMove1("bubble beam")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                statChange("speed", 2, -1);
            }
        } else if (isMove1("aurora beam")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("attack", 2, -1);
            }
        } else if (isMove1("thunder shock") || isMove1("thunderbolt")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.getStatused("paralyze");
                p2Pokemon[activePokemon2.orderNum].getStatused("paralyze");
            }
        } else if (isMove1("thunder")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.getStatused("paralyze");
                p2Pokemon[activePokemon2.orderNum].getStatused("paralyze");
            }
        } else if (isMove1("confusion")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.confused = true;
            }
        } else if (isMove1("psychic")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("spdefense", 2, -1);
            }
        } else if (isMove1("lick")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.getStatused("paralyze");
                p2Pokemon[activePokemon2.orderNum].getStatused("paralyze");
            }
        } else if (isMove1("smog") || isMove1("sludge")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 40) {
                activePokemon2.getStatused("poison");
                p2Pokemon[activePokemon2.orderNum].getStatused("poison");
            }
        } else if (isMove1("bone club")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("fire blast")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.getStatused("burn");
                p2Pokemon[activePokemon2.orderNum].getStatused("burn");
            }
        } else if (isMove1("waterfall")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("dizzy punch")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon2.confused = true;
            }
        } else if (isMove1("rock slide")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("tri attack")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 7) {
                activePokemon2.getStatused("burn");
                p2Pokemon[activePokemon2.orderNum].getStatused("burn");
            } else if (n < 14) {
                activePokemon2.getStatused("paralyze");
                p2Pokemon[activePokemon2.orderNum].getStatused("paralyze");
            } else if (n < 21) {
                activePokemon2.getStatused("frozen");
                p2Pokemon[activePokemon2.orderNum].getStatused("frozen");
            }
        } else if (isMove1("thief")) {
            activePokemon1.item = activePokemon2.item;
            activePokemon2.item = "dropped";
        } else if (isMove1("flame wheel")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.getStatused("burn");
                p2Pokemon[activePokemon2.orderNum].getStatused("burn");
            }
        } else if (isMove1("snore")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.flinch = true;
            }

        } else if (isMove1("sludge bomb")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.getStatused("poison");
                p2Pokemon[activePokemon2.orderNum].getStatused("poison");
            }
        } else if (isMove1("sludge wave")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.getStatused("poison");
                p2Pokemon[activePokemon2.orderNum].getStatused("poison");
            }
        } else if (isMove1("mud-slap")) {
            statChange("accuracy", 2, -1);
        } else if (isMove1("octazooka")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 50) {
                statChange("accuracy", 2, -1);
            }
        } else if (isMove1("zap cannon")) {
            activePokemon2.getStatused("paralyze");
            p2Pokemon[activePokemon2.orderNum].getStatused("paralyze");
        } else if (isMove1("icy wind")) {
            statChange("speed", 2, -1);
        } else if (isMove1("steel wing")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("defense", 2, -1);
            }
        } else if (isMove1("sacred fire")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 50) {
                activePokemon2.getStatused("burn");
                p2Pokemon[activePokemon2.orderNum].getStatused("burn");
            }
        } else if (isMove1("dynamic punch")) {
            activePokemon2.confused = true;
        } else if (isMove1("dragon breath")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.getStatused("paralyze");
                p2Pokemon[activePokemon2.orderNum].getStatused("paralyze");
            }
        } else if (isMove1("iron tail")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                statChange("defense", 2, -1);
            }
        } else if (isMove1("metal claw")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("attack", 1, 1);
            }
        } else if (isMove1("twister")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("crunch")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                statChange("defense", 2, -1);
            }
        } else if (isMove1("ancient power")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("attack", 1, 1);
                statChange("defense", 1, 1);
                statChange("spdefense", 1, 1);
                statChange("spattack", 1, 1);
                statChange("speed", 1, 1);
            }
        } else if (isMove1("shadow ball")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                statChange("spdefense", 2, -1);
            }
        } else if (isMove1("rock smash")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 50) {
                statChange("defense", 2, -1);
            }
        } else if (isMove1("heat wave")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.getStatused("burn");
                p2Pokemon[activePokemon2.orderNum].getStatused("burn");
            }
        } else if (isMove1("superpower")) {
            statChange("attack", 1, -1);
            statChange("defense", 1, -1);
        } else if (isMove1("knock off")) {
            activePokemon2.item = "dropped";
        } else if (isMove1("blaze kick")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.getStatused("burn");
                p2Pokemon[activePokemon2.orderNum].getStatused("burn");
            }
        } else if (isMove1("poison fang")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 50) {
                activePokemon2.getStatused("poison");
                p2Pokemon[activePokemon2.orderNum].getStatused("poison");
            }
        } else if (isMove1("crush claw")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 50) {
                statChange("defense", 2, -1);
            }
        } else if (isMove1("meteor mash")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                statChange("attack", 1, 1);
            }
        } else if (isMove1("power-up punch")) {
                statChange("attack", 1, 1);
        }

        else if (isMove1("overheat") || isMove1("draco meteor") || isMove1("leaf storm")) {
            statChange("spattack", 1, -2);
        } else if (isMove1("rock tomb") || isMove1("mud shot")) {
            statChange("speed", 2, -1);
        } else if (isMove1("silver wind") || isMove1("ominous wind")) {
            statChange("attack", 1, 1);
            statChange("defense", 1, 1);
            statChange("spattack", 1, 1);
            statChange("spdefense", 1, 1);
            statChange("speed", 1, 1);
        } else if (isMove1("signal beam")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.confused = true;
            }
        } else if (isMove1("shadow punch") || isMove1("aerial ace")) {
            activePokemon1.ensuredHit = true;
        } else if (isMove1("extrasensory")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("muddy water")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                statChange("accuracy", 2, -1);
            }
        } else if (isMove1("poison tail")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.getStatused("poison");
                p2Pokemon[activePokemon2.orderNum].getStatused("poison");
            }
        } else if (isMove1("water pulse")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon2.confused = true;
            }
        } else if (isMove1("psycho boost")) {
            statChange("spattack", 1, -2);
        } else if (isMove1("wake-up slap")) {
            if (activePokemon2.asleep) {
                activePokemon2.asleep = false;
                p2Pokemon[activePokemon2.orderNum].asleep = false;
            }

        } else if (isMove1("hammer arm")) {
            statChange("speed", 1, -1);
        } else if (isMove1("gyro ball")) {
            usedMove1.power = 25 * (activePokemon2.speed / activePokemon1.speed);
            if (usedMove1.power > 150) {
                usedMove1.power = 150;
            }
        } else if (isMove1("brine")) {
            if (activePokemon2.percentHP < .5) {
                usedMove1.power = 130;
            }
        } else if (isMove1("close combat")) {
            statChange("defense", 1, -1);
            statChange("spdefense", 1, -1);
        } else if (isMove1("dark pulse")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("air slash")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("bug buzz") || isMove1("focus blast") || isMove1("energy ball") || isMove1("earth power") || isMove1("flash cannon")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("spdefense", 2, -1);
            }
        } else if (isMove1("dragon rush")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("thunder fang")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.flinch = true;
            }
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.getStatused("paralyze");
                p2Pokemon[activePokemon2.orderNum].getStatused("paralyze");
            }
        } else if (isMove1("fire fang")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.flinch = true;
            }
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.getStatused("burn");
                p2Pokemon[activePokemon2.orderNum].getStatused("burn");
            }
        } else if (isMove1("ice fang")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.flinch = true;
            }
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.getStatused("frozen");
                p2Pokemon[activePokemon2.orderNum].getStatused("frozen");
            }
        } else if (isMove1("zen headbutt")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("discharge")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.getStatused("paralyze");
                p2Pokemon[activePokemon2.orderNum].getStatused("paralyze");
            }
        } else if (isMove1("lava plume")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.getStatused("burn");
                p2Pokemon[activePokemon2.orderNum].getStatused("burn");
            }
        } else if (isMove1("cross poison")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon2.getStatused("poison");
                p2Pokemon[activePokemon2.orderNum].getStatused("poison");
            }
        } else if (isMove1("gunk shot")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.getStatused("poison");
                p2Pokemon[activePokemon2.orderNum].getStatused("poison");
            }
        } else if (isMove1("iron head")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.flinch = true;
            }
        } else if (isMove1("charge beam")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 70) {
                statChange("spattack", 1, 1);
            }
        } else if (isMove1("seed flare")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 40) {
                statChange("spdefense", 2, -2);
            }
        } else if (isMove1("flame charge")) {
            statChange("speed", 1, 1);
        } else if (isMove1("moonblast")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                statChange("spattack", 2, -1);
            }
        } else if (isMove1("play rough")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("attack", 2, -1);
            }
        } else if (isMove1("acid spray")) {
            statChange("spdefense", 2, -2);
        } else if (isMove1("scald")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon2.getStatused("burn");
                p2Pokemon[activePokemon2.orderNum].burn = true;
            }
        } else if (isMove1("hyper beam")) {
            if (!activePokemon1.recharging) {
                activePokemon1.recharging = true;
            }

        }
    }

    public static void move2DirectlyAfterAttackingEffects() {
        int n = 0;

        if (isMove2("fire punch")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                activePokemon1.getStatused("burn");
                p1Pokemon[activePokemon1.orderNum].getStatused("burn");
            }
        } else if (isMove2("fake out") && p2Damage != 0) {
            activePokemon1.flinch = true;
            reason1 = "Flinched";
        } else if (isMove2("ice punch")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                activePokemon1.getStatused("frozen");
                p1Pokemon[activePokemon1.orderNum].getStatused("frozen");
            }
        } else if (isMove2("thunder punch")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                activePokemon1.getStatused("paralyze");
                p1Pokemon[activePokemon1.orderNum].getStatused("paralyze");
            }
        } else if (isMove2("rolling kick")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 30) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("sand attack")) {
            statChange("accuracy", 1, -1);
        } else if (isMove2("headbutt") || isMove2("bite")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 30) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("body slam")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.getStatused("paralyze");
                p1Pokemon[activePokemon1.orderNum].getStatused("paralyze");
            }
        } else if (isMove2("poison sting") || isMove2("poison jab")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 30) {
                activePokemon1.getStatused("poison");
                p1Pokemon[activePokemon1.orderNum].getStatused("poison");
            }
        } else if (isMove2("acid")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                statChange("spdefense", 1, -1);
            }
        } else if (isMove2("flamethrower")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                activePokemon1.getStatused("burn");
                p1Pokemon[activePokemon1.orderNum].getStatused("burn");
            }
        } else if (isMove2("poison sting") || isMove2("poison jab")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 30) {
                activePokemon1.getStatused("poison");
                p1Pokemon[activePokemon1.orderNum].getStatused("poison");
            }
        } else if (isMove2("ice beam") || isMove2("blizzard")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                activePokemon1.getStatused("frozen");
                p1Pokemon[activePokemon1.orderNum].getStatused("frozen");
            }
        } else if (isMove2("psybeam")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.confused = true;
            }
        } else if (isMove2("bubble beam")) {
            Random rand = new Random();
            n = rand.nextInt(100);
            if (n < 10) {
                statChange("speed", 1, -1);
            }
        } else if (isMove2("aurora beam")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("attack", 1, -1);
            }
        } else if (isMove2("thunder shock") || isMove2("thunderbolt")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.getStatused("paralyze");
                p1Pokemon[activePokemon1.orderNum].getStatused("paralyze");
            }
        } else if (isMove2("thunder")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.getStatused("paralyze");
                p1Pokemon[activePokemon1.orderNum].getStatused("paralyze");
            }
        } else if (isMove2("confusion")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.confused = true;
            }
        } else if (isMove2("psychic")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("spdefense", 1, -1);
            }
        } else if (isMove2("lick")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.getStatused("paralyze");
                p1Pokemon[activePokemon1.orderNum].getStatused("paralyze");
            }
        } else if (isMove2("smog") || isMove2("sludge")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 40) {
                activePokemon1.getStatused("poison");
                p1Pokemon[activePokemon1.orderNum].getStatused("poison");
            }
        } else if (isMove2("bone club")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("fire blast")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.getStatused("burn");
                p1Pokemon[activePokemon1.orderNum].getStatused("burn");
            }
        } else if (isMove2("waterfall")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("dizzy punch")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon1.confused = true;
            }
        } else if (isMove2("rock slide")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("tri attack")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 7) {
                activePokemon1.getStatused("burn");
                p1Pokemon[activePokemon1.orderNum].getStatused("burn");
            } else if (n < 14) {
                activePokemon1.getStatused("paralyze");
                p1Pokemon[activePokemon1.orderNum].getStatused("paralyze");
            } else if (n < 21) {
                activePokemon1.getStatused("frozen");
                p1Pokemon[activePokemon1.orderNum].getStatused("frozen");
            }
        } else if (isMove2("thief")) {
            activePokemon2.item = activePokemon1.item;
            activePokemon1.item = "dropped";
        } else if (isMove2("flame wheel")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.getStatused("burn");
                p1Pokemon[activePokemon1.orderNum].getStatused("burn");
            }
        } else if (isMove2("snore")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.flinch = true;
            }

        } else if (isMove2("sludge bomb")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.getStatused("poison");
                p1Pokemon[activePokemon1.orderNum].getStatused("poison");
            }
        } else if (isMove2("sludge wave")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.getStatused("poison");
                p1Pokemon[activePokemon1.orderNum].getStatused("poison");
            }
        } else if (isMove2("mud-slap")) {
            statChange("accuracy", 1, -1);
        } else if (isMove2("octazooka")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 50) {
                statChange("accuracy", 1, -1);
            }
        } else if (isMove2("zap cannon")) {
            activePokemon1.getStatused("paralyze");
            p1Pokemon[activePokemon1.orderNum].getStatused("paralyze");
        } else if (isMove2("icy wind")) {
            statChange("speed", 1, -1);
        } else if (isMove2("steel wing")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("defense", 1, -1);
            }
        } else if (isMove2("sacred fire")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 50) {
                activePokemon1.getStatused("burn");
                p1Pokemon[activePokemon1.orderNum].getStatused("burn");
            }
        } else if (isMove2("dynamic punch")) {
            activePokemon1.confused = true;
        } else if (isMove2("dragon breath")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.getStatused("paralyze");
                p1Pokemon[activePokemon1.orderNum].getStatused("paralyze");
            }
        } else if (isMove2("iron tail")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                statChange("defense", 1, -1);
            }
        } else if (isMove2("metal claw")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("attack", 2, 1);
            }
        } else if (isMove2("twister")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("crunch")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                statChange("defense", 1, -1);
            }
        } else if (isMove2("ancient power")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("attack", 2, 1);
                statChange("defense", 2, 1);
                statChange("spdefense", 2, 1);
                statChange("spattack", 2, 1);
                statChange("speed", 2, 1);
            }
        } else if (isMove2("shadow ball")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                statChange("spdefense", 1, -1);
            }
        } else if (isMove2("rock smash")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 50) {
                statChange("defense", 1, -1);
            }
        } else if (isMove2("heat wave")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.getStatused("burn");
                p1Pokemon[activePokemon1.orderNum].getStatused("burn");
            }
        } else if (isMove2("superpower")) {
            statChange("attack", 2, -1);
            statChange("defense", 2, -1);
        } else if (isMove2("knock off")) {
            activePokemon1.item = "dropped";
        } else if (isMove2("blaze kick")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.getStatused("burn");
                p1Pokemon[activePokemon1.orderNum].getStatused("burn");
            }
        } else if (isMove2("poison fang")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 50) {
                activePokemon1.getStatused("poison");
                p1Pokemon[activePokemon1.orderNum].getStatused("poison");
            }
        } else if (isMove2("crush claw")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 50) {
                statChange("defense", 1, -1);
            }
        } else if (isMove2("meteor mash")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                statChange("attack", 2, 1);
            }
        }
        else if (isMove2("power-up punch")) {
            statChange("attack", 2, 1);
        }
        else if (isMove2("overheat") || isMove2("draco meteor") || isMove2("leaf storm")) {
            statChange("spattack", 2, -2);
        } else if (isMove2("rock tomb") || isMove2("mud shot")) {
            statChange("speed", 1, -1);
        } else if (isMove2("silver wind") || isMove2("ominous wind")) {
            statChange("attack", 2, 1);
            statChange("defense", 2, 1);
            statChange("spattack", 2, 1);
            statChange("spdefense", 2, 1);
            statChange("speed", 2, 1);
        } else if (isMove2("signal beam")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.confused = true;
            }
        } else if (isMove2("shadow punch") || isMove2("aerial ace")) {
            activePokemon1.ensuredHit = true;
        } else if (isMove2("extrasensory")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("muddy water")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                statChange("accuracy", 1, -1);
            }
        } else if (isMove2("poison tail")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.getStatused("poison");
                p1Pokemon[activePokemon1.orderNum].getStatused("poison");
            }
        } else if (isMove2("water pulse")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon1.confused = true;
            }
        } else if (isMove2("psycho boost")) {
            statChange("spattack", 2, -2);
        } else if (isMove2("wake-up slap")) {
            if (activePokemon1.asleep) {
                activePokemon1.asleep = false;
                p1Pokemon[activePokemon1.orderNum].asleep = false;
            }

        } else if (isMove2("hammer arm")) {
            statChange("speed", 2, -1);
        } else if (isMove2("gyro ball")) {
            usedMove2.power = 25 * (activePokemon1.speed / activePokemon2.speed);
            if (usedMove2.power > 150) {
                usedMove2.power = 150;
            }
        } else if (isMove2("brine")) {
            if (activePokemon1.percentHP < .5) {
                usedMove2.power = 130;
            }
        } else if (isMove2("close combat")) {
            statChange("defense", 2, -1);
            statChange("spdefense", 2, -1);
        } else if (isMove2("dark pulse")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("air slash")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("bug buzz") || isMove2("focus blast") || isMove2("energy ball") || isMove2("earth power") || isMove2("flash cannon")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("spdefense", 1, -1);
            }
        } else if (isMove2("dragon rush")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("thunder fang")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.flinch = true;
            }
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.getStatused("paralyze");
                p1Pokemon[activePokemon1.orderNum].getStatused("paralyze");
            }
        } else if (isMove2("fire fang")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.flinch = true;
            }
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.getStatused("burn");
                p1Pokemon[activePokemon1.orderNum].getStatused("burn");
            }
        } else if (isMove2("ice fang")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.flinch = true;
            }
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.getStatused("frozen");
                p1Pokemon[activePokemon1.orderNum].getStatused("frozen");
            }
        } else if (isMove2("zen headbutt")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 20) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("discharge")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.getStatused("paralyze");
                p1Pokemon[activePokemon1.orderNum].getStatused("paralyze");
            }
        } else if (isMove2("lava plume")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.getStatused("burn");
                p1Pokemon[activePokemon1.orderNum].getStatused("burn");
            }
        } else if (isMove2("cross poison")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                activePokemon1.getStatused("poison");
                p1Pokemon[activePokemon1.orderNum].getStatused("poison");
            }
        } else if (isMove2("gunk shot")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.getStatused("poison");
                p1Pokemon[activePokemon1.orderNum].getStatused("poison");
            }
        } else if (isMove2("iron head")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.flinch = true;
            }
        } else if (isMove2("charge beam")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 70) {
                statChange("spattack", 2, 1);
            }
        } else if (isMove2("seed flare")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 40) {
                statChange("spdefense", 1, -2);
            }
        } else if (isMove2("flame charge")) {
            statChange("speed", 2, 1);
        } else if (isMove2("moonblast")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                statChange("spattack", 1, -1);
            }
        } else if (isMove2("play rough")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 10) {
                statChange("attack", 1, -1);
            }
        } else if (isMove2("acid spray")) {
            statChange("spdefense", 1, -2);
        } else if (isMove2("scald")) {
            Random rand = new Random();
            n = rand.nextInt(100);

            if (n < 30) {
                activePokemon1.getStatused("burn");
                p1Pokemon[activePokemon1.orderNum].burn = true;
            }
        } else if (isMove2("hyper beam")) {
            activePokemon2.recharging = true;
        }

    }

    public static void move1DirectlyAfterAttackingEffectsClientVersion() {
        //Taking care of stat changes on client side
        statChange("accuracy", 1, clientAccuracyStages1);
        statChange("evasion", 1, clientEvasionStages1);
        statChange("attack", 1, clientAttackStages1);
        statChange("defense", 1, clientDefenseStages1);
        statChange("spdefense", 1, clientSpdefenseStages1);
        statChange("spattack", 1, clientSpattackStages1);
        statChange("speed", 1, clientSpeedStages1);

        statChange("accuracy", 2, clientAccuracyStages2);
        statChange("evasion", 2, clientEvasionStages2);
        statChange("attack", 2, clientAttackStages2);
        statChange("defense", 2, clientDefenseStages2);
        statChange("spdefense", 2, clientSpdefenseStages2);
        statChange("spattack", 2, clientSpattackStages2);
        statChange("speed", 2, clientSpeedStages2);
    }

    public static void move1DirectlyBeforeAttackingEffectsClientVersion()
    {
        //Taking care of stat changes on client side
        statChange("accuracy", 1, clientAccuracyStages1);
        statChange("evasion", 1, clientEvasionStages1);
        statChange("attack", 1, clientAttackStages1);
        statChange("defense", 1, clientDefenseStages1);
        statChange("spdefense", 1, clientSpdefenseStages1);
        statChange("spattack", 1, clientSpattackStages1);
        statChange("speed", 1, clientSpeedStages1);

        statChange("accuracy", 2, clientAccuracyStages2);
        statChange("evasion", 2, clientEvasionStages2);
        statChange("attack", 2, clientAttackStages2);
        statChange("defense", 2, clientDefenseStages2);
        statChange("spdefense", 2, clientSpdefenseStages2);
        statChange("spattack", 2, clientSpattackStages2);
        statChange("speed", 2, clientSpeedStages2);
    }

    public static void move2DirectlyBeforeAttackingEffectsClientVersion()
    {
        //Taking care of stat changes on client side
        statChange("accuracy", 1, clientAccuracyStages1);
        statChange("evasion", 1, clientEvasionStages1);
        statChange("attack", 1, clientAttackStages1);
        statChange("defense", 1, clientDefenseStages1);
        statChange("spdefense", 1, clientSpdefenseStages1);
        statChange("spattack", 1, clientSpattackStages1);
        statChange("speed", 1, clientSpeedStages1);

        statChange("accuracy", 2, clientAccuracyStages2);
        statChange("evasion", 2, clientEvasionStages2);
        statChange("attack", 2, clientAttackStages2);
        statChange("defense", 2, clientDefenseStages2);
        statChange("spdefense", 2, clientSpdefenseStages2);
        statChange("spattack", 2, clientSpattackStages2);
        statChange("speed", 2, clientSpeedStages2);
    }

    public static void move2DirectlyAfterAttackingEffectsClientVersion() {

        //Taking care of stat changes on client side
        statChange("accuracy", 1, clientAccuracyStages1);
        statChange("evasion", 1, clientEvasionStages1);
        statChange("attack", 1, clientAttackStages1);
        statChange("defense", 1, clientDefenseStages1);
        statChange("spdefense", 1, clientSpdefenseStages1);
        statChange("spattack", 1, clientSpattackStages1);
        statChange("speed", 1, clientSpeedStages1);

        statChange("accuracy", 2, clientAccuracyStages2);
        statChange("evasion", 2, clientEvasionStages2);
        statChange("attack", 2, clientAttackStages2);
        statChange("defense", 2, clientDefenseStages2);
        statChange("spdefense", 2, clientSpdefenseStages2);
        statChange("spattack", 2, clientSpattackStages2);
        statChange("speed", 2, clientSpeedStages2);

    }

    public static void move1DirectlyBeforeAttackingEffects() {

        //Directly before attacking
        if (isMove1("brick break")) {
            p2LS = false;
            p2Ref = false;
        } else if (isMove1("facade")) {
            if (activePokemon1.burn || activePokemon1.paralyze || activePokemon1.poison) {
                usedMove1.power = 140;
            } else {
                usedMove1.power = 70;
            }
        } else if (isMove1("cross chop")) {
            activePokemon1.critStage++;
        } else if (isMove1("sacred fire")) {
            if (activePokemon1.frozen = true) {
                activePokemon1.frozen = false;
                p1Pokemon[activePokemon1.orderNum].frozen = false;
            }
        } else if (isMove1("reversal")) {
            long percentHealth = activePokemon1.hp / activePokemon1.maxHP;
            if (percentHealth < 0.417) {
                usedMove1.power = 200;
            } else if (percentHealth < 0.1042) {
                usedMove1.power = 150;
            } else if (percentHealth < 0.2083) {
                usedMove1.power = 100;
            } else if (percentHealth < 0.3542) {
                usedMove1.power = 80;
            } else if (percentHealth < 0.6875) {
                usedMove1.power = 40;
            } else {
                usedMove1.power = 20;
            }
        } else if (isMove1("aeroblast")) {
            activePokemon1.critStage++;
        }

        if (isMove1("karate chop") || isMove1("psycho cut") || isMove1("cross poison") || isMove1("stone edge")) {
            activePokemon1.critStage++;
        } else if (isMove1("guillotine") || isMove1("horn drill") || isMove1("sheer cold") || isMove1("fissure")) {
            p1Damage = 1000;
        } else if (isMove1("stomp") || isMove1("shock wave") || isMove1("aura sphere")) {
            activePokemon1.ensuredHit = true;
        } else if (isMove1("razor leaf")) {
            activePokemon1.critStage += 2;
        } else if (isMove1("crabhammer") || isMove1("night slash")) {
            activePokemon1.critStage++;
        } else if (isMove1("slash") || isMove1("shadow claw")) {
            activePokemon1.critStage++;
        } else if (isMove1("flame wheel")) {
            if (activePokemon1.frozen) {
                activePokemon1.frozen = false;
                p1Pokemon[activePokemon1.orderNum].frozen = false;
            }
        } else if (isMove1("snore")) {
            if (activePokemon1.asleep) {
                usedMove1.fail = false;
            }
        } else if (isMove1("eruption") || isMove1("water spout")) {
            usedMove1.power = (150) * (activePokemon1.hp / activePokemon1.maxHP);
        } else if (isMove1("blaze kick") || isMove1("poison tail") || isMove1("leaf blade")) {
            activePokemon1.critStage++;
        } else if (isMove1("wake-up slap")) {
            if (activePokemon2.asleep) {
                usedMove1.power = 140;
            } else {
                usedMove1.power = 70;
            }

        } else if (isMove1("venoshock")) {
            if (activePokemon2.poison || activePokemon2.badlyPoison) {
                usedMove1.power = 130;
            } else {
                usedMove1.power = 65;
            }
        } else if (isMove1("hex")) {
            if (activePokemon2.isNonVolStatused()) {
                usedMove1.power = 130;
            } else {
                usedMove1.power = 65;
            }
        } else if (isMove1("hyper beam")) {
            if (activePokemon1.recharging) {
                //activePokemon1.recharging =false;
                reason1 = "Recharging";
            }
        }
    }

    public static void move2DirectlyBeforeAttackingEffects() {

        //Directly before attacking
        if (isMove2("brick break")) {
            p2LS = false;
            p2Ref = false;
        } else if (isMove2("facade")) {
            if (activePokemon2.burn || activePokemon2.paralyze || activePokemon2.poison) {
                usedMove2.power = 140;
            } else {
                usedMove2.power = 70;
            }
        } else if (isMove2("cross chop")) {
            activePokemon2.critStage++;
        } else if (isMove2("sacred fire")) {
            if (activePokemon2.frozen = true) {
                activePokemon2.frozen = false;
                p1Pokemon[activePokemon2.orderNum].frozen = false;
            }
        } else if (isMove2("reversal")) {
            long percentHealth = activePokemon2.hp / activePokemon2.maxHP;
            if (percentHealth < 0.417) {
                usedMove2.power = 200;
            } else if (percentHealth < 0.1042) {
                usedMove2.power = 150;
            } else if (percentHealth < 0.2083) {
                usedMove2.power = 100;
            } else if (percentHealth < 0.3542) {
                usedMove2.power = 80;
            } else if (percentHealth < 0.6875) {
                usedMove2.power = 40;
            } else {
                usedMove2.power = 20;
            }
        } else if (isMove2("aeroblast")) {
            activePokemon2.critStage++;
        }

        if (isMove2("karate chop") || isMove2("psycho cut") || isMove2("cross poison") || isMove2("stone edge")) {
            activePokemon2.critStage++;
        } else if (isMove2("guillotine") || isMove2("horn drill") || isMove2("sheer cold") || isMove2("fissure")) {
            p2Damage = 1000;
        } else if (isMove2("stomp") || isMove2("shock wave") || isMove2("aura sphere")) {
            activePokemon2.ensuredHit = true;
        } else if (isMove2("razor leaf")) {
            activePokemon2.critStage += 2;
        } else if (isMove2("crabhammer") || isMove2("night slash")) {
            activePokemon2.critStage++;
        } else if (isMove2("slash") || isMove2("shadow claw")) {
            activePokemon2.critStage++;
        } else if (isMove2("flame wheel")) {
            if (activePokemon2.frozen) {
                activePokemon2.frozen = false;
                p1Pokemon[activePokemon2.orderNum].frozen = false;
            }
        } else if (isMove2("snore")) {
            if (activePokemon2.asleep) {
                usedMove2.fail = false;
            }
        } else if (isMove2("eruption") || isMove2("water spout")) {
            usedMove2.power = (150) * (activePokemon2.hp / activePokemon2.maxHP);
        } else if (isMove2("blaze kick") || isMove2("poison tail") || isMove2("leaf blade")) {
            activePokemon2.critStage++;
        } else if (isMove2("wake-up slap")) {
            if (activePokemon1.asleep) {
                usedMove2.power = 140;
            } else {
                usedMove2.power = 70;
            }

        } else if (isMove2("venoshock")) {
            if (activePokemon1.poison || activePokemon1.badlyPoison) {
                usedMove2.power = 130;
            } else {
                usedMove2.power = 65;
            }
        } else if (isMove2("hex")) {
            if (activePokemon1.isNonVolStatused()) {
                usedMove2.power = 130;
            } else {
                usedMove2.power = 65;
            }
        } else if (isMove2("hyper beam")) {
            if (activePokemon2.recharging) {
                //activePokemon2.recharging =false;
                reason2 = "Recharging";
            }
        }
    }

    public static void statChange(String s, int player, int stages) {
        if(host)
        {
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            switch (player)
            {
                case 1:
                    myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child(s).setValue(stages);
                    break;
                case 2:
                    myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child(s).setValue(stages);
            }
        }



        if (player == 1) {
            if (s.equals("accuracy")) {
                if (activePokemon1.accuracyStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.accuracy = .25;
                            break;
                        case -1:
                            activePokemon1.accuracy = .25;
                            break;
                        case 0:
                            activePokemon1.accuracy = .25;
                            break;
                        case 1:
                            activePokemon1.accuracy = .33;
                            break;
                        case 2:
                            activePokemon1.accuracy = .50;
                    }
                }

                if (activePokemon1.accuracyStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.accuracy = .25;
                            break;
                        case -1:
                            activePokemon1.accuracy = .25;
                            break;
                        case 0:
                            activePokemon1.accuracy = .33;
                            break;
                        case 1:
                            activePokemon1.accuracy = .50;
                            break;
                        case 2:
                            activePokemon1.accuracy = .67;
                    }
                }

                if (activePokemon1.accuracyStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.accuracy = .25;
                            break;
                        case -1:
                            activePokemon1.accuracy = .33;
                            break;
                        case 0:
                            activePokemon1.accuracy = .50;
                            break;
                        case 1:
                            activePokemon1.accuracy = .67;
                            break;
                        case 2:
                            activePokemon1.accuracy = 1;
                    }
                }

                if (activePokemon1.accuracyStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.accuracy = .33;
                            break;
                        case -1:
                            activePokemon1.accuracy = .50;
                            break;
                        case 0:
                            activePokemon1.accuracy = .67;
                            break;
                        case 1:
                            activePokemon1.accuracy = 1.5;
                            break;
                        case 2:
                            activePokemon1.accuracy = 2;
                    }
                }

                if (activePokemon1.accuracyStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon1.accuracy = .50;
                            break;
                        case -1:
                            activePokemon1.accuracy = .67;
                            break;
                        case 0:
                            activePokemon1.accuracy = 1;
                            break;
                        case 1:
                            activePokemon1.accuracy = 1.5;
                            break;
                        case 2:
                            activePokemon1.accuracy = 2;
                    }
                }

                if (activePokemon1.accuracyStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.accuracy = .67;
                            break;
                        case -1:
                            activePokemon1.accuracy = 1;
                            break;
                        case 0:
                            activePokemon1.accuracy = 1.5;
                            break;
                        case 1:
                            activePokemon1.accuracy = 2;
                            break;
                        case 2:
                            activePokemon1.accuracy = 2.5;
                    }
                }

                if (activePokemon1.accuracyStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.accuracy = 1;
                            break;
                        case -1:
                            activePokemon1.accuracy = 1.5;
                            break;
                        case 0:
                            activePokemon1.accuracy = 2;
                            break;
                        case 1:
                            activePokemon1.accuracy = 2.5;
                            break;
                        case 2:
                            activePokemon1.accuracy = 3;
                    }
                }

                if (activePokemon1.accuracyStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.accuracy = 1.5;
                            break;
                        case -1:
                            activePokemon1.accuracy = 2;
                            break;
                        case 0:
                            activePokemon1.accuracy = 2.5;
                            break;
                        case 1:
                            activePokemon1.accuracy = 3;
                            break;
                        case 2:
                            activePokemon1.accuracy = 3.5;
                    }
                }

                if (activePokemon1.accuracyStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.accuracy = 2;
                            break;
                        case -1:
                            activePokemon1.accuracy = 2.5;
                            break;
                        case 0:
                            activePokemon1.accuracy = 3;
                            break;
                        case 1:
                            activePokemon1.accuracy = 3.5;
                            break;
                        case 2:
                            activePokemon1.accuracy = 4;
                    }
                }

                if (activePokemon1.accuracyStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon1.accuracy = 2.5;
                            break;
                        case -1:
                            activePokemon1.accuracy = 3;
                            break;
                        case 0:
                            activePokemon1.accuracy = 3.5;
                            break;
                        case 1:
                            activePokemon1.accuracy = 4;
                            break;
                        case 2:
                            activePokemon1.accuracy = 4;
                    }
                }

                if (activePokemon1.accuracyStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon1.accuracy = 3;
                            break;
                        case -1:
                            activePokemon1.accuracy = 3.5;
                            break;
                        case 0:
                            activePokemon1.accuracy = 4;
                            break;
                        case 1:
                            activePokemon1.accuracy = 4;
                            break;
                        case 2:
                            activePokemon1.accuracy = 4;
                    }
                }

                activePokemon1.accuracyStage += stages;
                if (activePokemon1.accuracyStage > 6) {
                    activePokemon1.accuracyStage = 6;
                }
                if (activePokemon1.accuracyStage < -4) {
                    activePokemon1.accuracyStage = -4;
                }

            } else if (s.equals("evasion")) {
                if (activePokemon1.evasionStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.evasion = .25;
                            break;
                        case -1:
                            activePokemon1.evasion = .25;
                            break;
                        case 0:
                            activePokemon1.evasion = .25;
                            break;
                        case 1:
                            activePokemon1.evasion = .33;
                            break;
                        case 2:
                            activePokemon1.evasion = .50;
                    }
                }

                if (activePokemon1.evasionStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.evasion = .25;
                            break;
                        case -1:
                            activePokemon1.evasion = .25;
                            break;
                        case 0:
                            activePokemon1.evasion = .33;
                            break;
                        case 1:
                            activePokemon1.evasion = .50;
                            break;
                        case 2:
                            activePokemon1.evasion = .67;
                    }
                }

                if (activePokemon1.evasionStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.evasion = .25;
                            break;
                        case -1:
                            activePokemon1.evasion = .33;
                            break;
                        case 0:
                            activePokemon1.evasion = .50;
                            break;
                        case 1:
                            activePokemon1.evasion = .67;
                            break;
                        case 2:
                            activePokemon1.evasion = 1;
                    }
                }

                if (activePokemon1.evasionStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.evasion = .33;
                            break;
                        case -1:
                            activePokemon1.evasion = .50;
                            break;
                        case 0:
                            activePokemon1.evasion = .67;
                            break;
                        case 1:
                            activePokemon1.evasion = 1.5;
                            break;
                        case 2:
                            activePokemon1.evasion = 2;
                    }
                }

                if (activePokemon1.evasionStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon1.evasion = .50;
                            break;
                        case -1:
                            activePokemon1.evasion = .67;
                            break;
                        case 0:
                            activePokemon1.evasion = 1;
                            break;
                        case 1:
                            activePokemon1.evasion = 1.5;
                            break;
                        case 2:
                            activePokemon1.evasion = 2;
                    }
                }

                if (activePokemon1.evasionStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.evasion = .67;
                            break;
                        case -1:
                            activePokemon1.evasion = 1;
                            break;
                        case 0:
                            activePokemon1.evasion = 1.5;
                            break;
                        case 1:
                            activePokemon1.evasion = 2;
                            break;
                        case 2:
                            activePokemon1.evasion = 2.5;
                    }
                }

                if (activePokemon1.evasionStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.evasion = 1;
                            break;
                        case -1:
                            activePokemon1.evasion = 1.5;
                            break;
                        case 0:
                            activePokemon1.evasion = 2;
                            break;
                        case 1:
                            activePokemon1.evasion = 2.5;
                            break;
                        case 2:
                            activePokemon1.evasion = 3;
                    }
                }

                if (activePokemon1.evasionStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.evasion = 1.5;
                            break;
                        case -1:
                            activePokemon1.evasion = 2;
                            break;
                        case 0:
                            activePokemon1.evasion = 2.5;
                            break;
                        case 1:
                            activePokemon1.evasion = 3;
                            break;
                        case 2:
                            activePokemon1.evasion = 3.5;
                    }
                }

                if (activePokemon1.evasionStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.evasion = 2;
                            break;
                        case -1:
                            activePokemon1.evasion = 2.5;
                            break;
                        case 0:
                            activePokemon1.evasion = 3;
                            break;
                        case 1:
                            activePokemon1.evasion = 3.5;
                            break;
                        case 2:
                            activePokemon1.evasion = 4;
                    }
                }

                if (activePokemon1.evasionStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon1.evasion = 2.5;
                            break;
                        case -1:
                            activePokemon1.evasion = 3;
                            break;
                        case 0:
                            activePokemon1.evasion = 3.5;
                            break;
                        case 1:
                            activePokemon1.evasion = 4;
                            break;
                        case 2:
                            activePokemon1.evasion = 4;
                    }
                }

                if (activePokemon1.evasionStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon1.evasion = 3;
                            break;
                        case -1:
                            activePokemon1.evasion = 3.5;
                            break;
                        case 0:
                            activePokemon1.evasion = 4;
                            break;
                        case 1:
                            activePokemon1.evasion = 4;
                            break;
                        case 2:
                            activePokemon1.evasion = 4;
                    }
                }

                activePokemon1.evasionStage += stages;
                if (activePokemon1.evasionStage > 6) {
                    activePokemon1.evasionStage = 6;
                }
                if (activePokemon1.evasionStage < -4) {
                    activePokemon1.evasionStage = -4;
                }

            } else if (s.equals("attack")) {
                if (activePokemon1.attackStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .25);
                            break;
                        case -1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .25);
                            break;
                        case 0:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .25);
                            break;
                        case 1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .33);
                            break;
                        case 2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .50);
                    }
                }

                if (activePokemon1.attackStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .25);
                            break;
                        case -1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .25);
                            break;
                        case 0:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .33);
                            break;
                        case 1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .50);
                            break;
                        case 2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .67);
                    }
                }

                if (activePokemon1.attackStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .25);
                            break;
                        case -1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .33);
                            break;
                        case 0:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .50);
                            break;
                        case 1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .67);
                            break;
                        case 2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 1);
                    }
                }

                if (activePokemon1.attackStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .33);
                            break;
                        case -1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .50);
                            break;
                        case 0:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .67);
                            break;
                        case 1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 1.5);
                            break;
                        case 2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 2);
                    }
                }

                if (activePokemon1.attackStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .50);
                            break;
                        case -1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .67);
                            break;
                        case 0:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 1);
                            break;
                        case 1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 1.5);
                            break;
                        case 2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 2);
                    }
                }

                if (activePokemon1.attackStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * .67);
                            break;
                        case -1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 1);
                            break;
                        case 0:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 1.5);
                            break;
                        case 1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 2);
                            break;
                        case 2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 2.5);
                    }
                }

                if (activePokemon1.attackStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 1);
                            break;
                        case -1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 1.5);
                            break;
                        case 0:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 2);
                            break;
                        case 1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 2.5);
                            break;
                        case 2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 3);
                    }
                }

                if (activePokemon1.attackStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 1.5);
                            break;
                        case -1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 2);
                            break;
                        case 0:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 2.5);
                            break;
                        case 1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 3);
                            break;
                        case 2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 3.5);
                    }
                }

                if (activePokemon1.attackStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 2);
                            break;
                        case -1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 2.5);
                            break;
                        case 0:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 3);
                            break;
                        case 1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 3.5);
                            break;
                        case 2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 4);
                    }
                }

                if (activePokemon1.attackStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 2.5);
                            break;
                        case -1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 3);
                            break;
                        case 0:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 3.5);
                            break;
                        case 1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 4);
                            break;
                        case 2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 4);
                    }
                }

                if (activePokemon1.attackStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 3);
                            break;
                        case -1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 3.5);
                            break;
                        case 0:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 4);
                            break;
                        case 1:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 4);
                            break;
                        case 2:
                            activePokemon1.attack = (int) Math.round(activePokemon1.origAttack * 4);
                    }
                }

                activePokemon1.attackStage += stages;
                if (activePokemon1.attackStage > 6) {
                    activePokemon1.attackStage = 6;
                }
                if (activePokemon1.attackStage < -4) {
                    activePokemon1.attackStage = -4;
                }
            } else if (s.equals("defense")) {
                if (activePokemon1.defenseStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .25);
                            break;
                        case -1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .25);
                            break;
                        case 0:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .25);
                            break;
                        case 1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .33);
                            break;
                        case 2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .50);
                    }
                }

                if (activePokemon1.defenseStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .25);
                            break;
                        case -1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .25);
                            break;
                        case 0:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .33);
                            break;
                        case 1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .50);
                            break;
                        case 2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .67);
                    }
                }

                if (activePokemon1.defenseStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .25);
                            break;
                        case -1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .33);
                            break;
                        case 0:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .50);
                            break;
                        case 1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .67);
                            break;
                        case 2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 1);
                    }
                }

                if (activePokemon1.defenseStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .33);
                            break;
                        case -1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .50);
                            break;
                        case 0:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .67);
                            break;
                        case 1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 1.5);
                            break;
                        case 2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 2);
                    }
                }

                if (activePokemon1.defenseStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .50);
                            break;
                        case -1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .67);
                            break;
                        case 0:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 1);
                            break;
                        case 1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 1.5);
                            break;
                        case 2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 2);
                    }
                }

                if (activePokemon1.defenseStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * .67);
                            break;
                        case -1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 1);
                            break;
                        case 0:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 1.5);
                            break;
                        case 1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 2);
                            break;
                        case 2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 2.5);
                    }
                }

                if (activePokemon1.defenseStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 1);
                            break;
                        case -1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 1.5);
                            break;
                        case 0:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 2);
                            break;
                        case 1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 2.5);
                            break;
                        case 2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 3);
                    }
                }

                if (activePokemon1.defenseStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 1.5);
                            break;
                        case -1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 2);
                            break;
                        case 0:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 2.5);
                            break;
                        case 1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 3);
                            break;
                        case 2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 3.5);
                    }
                }

                if (activePokemon1.defenseStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 2);
                            break;
                        case -1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 2.5);
                            break;
                        case 0:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 3);
                            break;
                        case 1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 3.5);
                            break;
                        case 2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 4);
                    }
                }

                if (activePokemon1.defenseStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 2.5);
                            break;
                        case -1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 3);
                            break;
                        case 0:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 3.5);
                            break;
                        case 1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 4);
                            break;
                        case 2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 4);
                    }
                }

                if (activePokemon1.defenseStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 3);
                            break;
                        case -1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 3.5);
                            break;
                        case 0:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 4);
                            break;
                        case 1:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 4);
                            break;
                        case 2:
                            activePokemon1.defense = (int) Math.round(activePokemon1.origDefense * 4);
                    }
                }

                activePokemon1.defenseStage = activePokemon1.defenseStage + stages;

                if (activePokemon1.defenseStage > 6) {
                    activePokemon1.defenseStage = 6;
                }
                if (activePokemon1.defenseStage < -4) {
                    activePokemon1.defenseStage = -4;
                }
            } else if (s.equals("spattack")) {
                if (activePokemon1.spattackStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .25);
                            break;
                        case -1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .25);
                            break;
                        case 0:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .25);
                            break;
                        case 1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .33);
                            break;
                        case 2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .50);
                    }
                }

                if (activePokemon1.spattackStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .25);
                            break;
                        case -1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .25);
                            break;
                        case 0:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .33);
                            break;
                        case 1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .50);
                            break;
                        case 2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .67);
                    }
                }

                if (activePokemon1.spattackStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .25);
                            break;
                        case -1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .33);
                            break;
                        case 0:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .50);
                            break;
                        case 1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .67);
                            break;
                        case 2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 1);
                    }
                }

                if (activePokemon1.spattackStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .33);
                            break;
                        case -1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .50);
                            break;
                        case 0:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .67);
                            break;
                        case 1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 1.5);
                            break;
                        case 2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 2);
                    }
                }

                if (activePokemon1.spattackStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .50);
                            break;
                        case -1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .67);
                            break;
                        case 0:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 1);
                            break;
                        case 1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 1.5);
                            break;
                        case 2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 2);
                    }
                }

                if (activePokemon1.spattackStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * .67);
                            break;
                        case -1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 1);
                            break;
                        case 0:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 1.5);
                            break;
                        case 1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 2);
                            break;
                        case 2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 2.5);
                    }
                }

                if (activePokemon1.spattackStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 1);
                            break;
                        case -1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 1.5);
                            break;
                        case 0:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 2);
                            break;
                        case 1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 2.5);
                            break;
                        case 2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 3);
                    }
                }

                if (activePokemon1.spattackStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 1.5);
                            break;
                        case -1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 2);
                            break;
                        case 0:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 2.5);
                            break;
                        case 1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 3);
                            break;
                        case 2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 3.5);
                    }
                }

                if (activePokemon1.spattackStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 2);
                            break;
                        case -1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 2.5);
                            break;
                        case 0:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 3);
                            break;
                        case 1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 3.5);
                            break;
                        case 2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 4);
                    }
                }

                if (activePokemon1.spattackStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 2.5);
                            break;
                        case -1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 3);
                            break;
                        case 0:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 3.5);
                            break;
                        case 1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 4);
                            break;
                        case 2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 4);
                    }
                }

                if (activePokemon1.spattackStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 3);
                            break;
                        case -1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 3.5);
                            break;
                        case 0:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 4);
                            break;
                        case 1:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 4);
                            break;
                        case 2:
                            activePokemon1.spattack = (int) Math.round(activePokemon1.origSpattack * 4);
                    }
                }

                activePokemon1.spattackStage += stages;
                if (activePokemon1.spattackStage > 6) {
                    activePokemon1.spattackStage = 6;
                }
                if (activePokemon1.spattackStage < -4) {
                    activePokemon1.spattackStage = -4;
                }
            } else if (s.equals("spdefense")) {
                if (activePokemon1.spdefenseStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .25);
                            break;
                        case -1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .25);
                            break;
                        case 0:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .25);
                            break;
                        case 1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .33);
                            break;
                        case 2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .50);
                    }
                }

                if (activePokemon1.spdefenseStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .25);
                            break;
                        case -1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .25);
                            break;
                        case 0:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .33);
                            break;
                        case 1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .50);
                            break;
                        case 2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .67);
                    }
                }

                if (activePokemon1.spdefenseStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .25);
                            break;
                        case -1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .33);
                            break;
                        case 0:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .50);
                            break;
                        case 1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .67);
                            break;
                        case 2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 1);
                    }
                }

                if (activePokemon1.spdefenseStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .33);
                            break;
                        case -1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .50);
                            break;
                        case 0:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .67);
                            break;
                        case 1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 1.5);
                            break;
                        case 2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 2);
                    }
                }

                if (activePokemon1.spdefenseStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .50);
                            break;
                        case -1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .67);
                            break;
                        case 0:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 1);
                            break;
                        case 1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 1.5);
                            break;
                        case 2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 2);
                    }
                }

                if (activePokemon1.spdefenseStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * .67);
                            break;
                        case -1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 1);
                            break;
                        case 0:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 1.5);
                            break;
                        case 1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 2);
                            break;
                        case 2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 2.5);
                    }
                }

                if (activePokemon1.spdefenseStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 1);
                            break;
                        case -1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 1.5);
                            break;
                        case 0:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 2);
                            break;
                        case 1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 2.5);
                            break;
                        case 2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 3);
                    }
                }

                if (activePokemon1.spdefenseStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 1.5);
                            break;
                        case -1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 2);
                            break;
                        case 0:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 2.5);
                            break;
                        case 1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 3);
                            break;
                        case 2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 3.5);
                    }
                }

                if (activePokemon1.spdefenseStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 2);
                            break;
                        case -1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 2.5);
                            break;
                        case 0:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 3);
                            break;
                        case 1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 3.5);
                            break;
                        case 2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 4);
                    }
                }

                if (activePokemon1.spdefenseStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 2.5);
                            break;
                        case -1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 3);
                            break;
                        case 0:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 3.5);
                            break;
                        case 1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 4);
                            break;
                        case 2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 4);
                    }
                }

                if (activePokemon1.spdefenseStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 3);
                            break;
                        case -1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 3.5);
                            break;
                        case 0:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 4);
                            break;
                        case 1:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 4);
                            break;
                        case 2:
                            activePokemon1.spdefense = (int) Math.round(activePokemon1.origSpdefense * 4);
                    }
                }
                activePokemon1.spdefenseStage += stages;
                if (activePokemon1.spdefenseStage > 6) {
                    activePokemon1.spdefenseStage = 6;
                }
                if (activePokemon1.spdefenseStage < -4) {
                    activePokemon1.spdefenseStage = -4;
                }
            } else if (s.equals("speed")) {
                if (activePokemon1.speedStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .25);
                            break;
                        case -1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .25);
                            break;
                        case 0:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .25);
                            break;
                        case 1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .33);
                            break;
                        case 2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .50);
                    }
                }

                if (activePokemon1.speedStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .25);
                            break;
                        case -1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .25);
                            break;
                        case 0:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .33);
                            break;
                        case 1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .50);
                            break;
                        case 2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .67);
                    }
                }

                if (activePokemon1.speedStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .25);
                            break;
                        case -1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .33);
                            break;
                        case 0:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .50);
                            break;
                        case 1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .67);
                            break;
                        case 2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 1);
                    }
                }

                if (activePokemon1.speedStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .33);
                            break;
                        case -1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .50);
                            break;
                        case 0:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .67);
                            break;
                        case 1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 1.5);
                            break;
                        case 2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 2);
                    }
                }

                if (activePokemon1.speedStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .50);
                            break;
                        case -1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .67);
                            break;
                        case 0:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 1);
                            break;
                        case 1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 1.5);
                            break;
                        case 2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 2);
                    }
                }

                if (activePokemon1.speedStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * .67);
                            break;
                        case -1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 1);
                            break;
                        case 0:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 1.5);
                            break;
                        case 1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 2);
                            break;
                        case 2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 2.5);
                    }
                }

                if (activePokemon1.speedStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 1);
                            break;
                        case -1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 1.5);
                            break;
                        case 0:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 2);
                            break;
                        case 1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 2.5);
                            break;
                        case 2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 3);
                    }
                }

                if (activePokemon1.speedStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 1.5);
                            break;
                        case -1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 2);
                            break;
                        case 0:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 2.5);
                            break;
                        case 1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 3);
                            break;
                        case 2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 3.5);
                    }
                }

                if (activePokemon1.speedStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 2);
                            break;
                        case -1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 2.5);
                            break;
                        case 0:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 3);
                            break;
                        case 1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 3.5);
                            break;
                        case 2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 4);
                    }
                }

                if (activePokemon1.speedStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 2.5);
                            break;
                        case -1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 3);
                            break;
                        case 0:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 3.5);
                            break;
                        case 1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 4);
                            break;
                        case 2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 4);
                    }
                }

                if (activePokemon1.speedStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 3);
                            break;
                        case -1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 3.5);
                            break;
                        case 0:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 4);
                            break;
                        case 1:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 4);
                            break;
                        case 2:
                            activePokemon1.speed = (int) Math.round(activePokemon1.origSpeed * 4);
                    }
                }

                activePokemon1.speedStage += stages;
                if (activePokemon1.speedStage > 6) {
                    activePokemon1.speedStage = 6;
                }
                if (activePokemon1.speedStage < -4) {
                    activePokemon1.speedStage = -4;
                }
            }

        }

        if (player == 2) {
            if (s.equals("accuracy")) {
                if (activePokemon2.accuracyStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.accuracy = .25;
                            break;
                        case -1:
                            activePokemon2.accuracy = .25;
                            break;
                        case 0:
                            activePokemon2.accuracy = .25;
                            break;
                        case 1:
                            activePokemon2.accuracy = .33;
                            break;
                        case 2:
                            activePokemon2.accuracy = .50;
                    }
                }

                if (activePokemon2.accuracyStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.accuracy = .25;
                            break;
                        case -1:
                            activePokemon2.accuracy = .25;
                            break;
                        case 0:
                            activePokemon2.accuracy = .33;
                            break;
                        case 1:
                            activePokemon2.accuracy = .50;
                            break;
                        case 2:
                            activePokemon2.accuracy = .67;
                    }
                }

                if (activePokemon2.accuracyStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.accuracy = .25;
                            break;
                        case -1:
                            activePokemon2.accuracy = .33;
                            break;
                        case 0:
                            activePokemon2.accuracy = .50;
                            break;
                        case 1:
                            activePokemon2.accuracy = .67;
                            break;
                        case 2:
                            activePokemon2.accuracy = 1;
                    }
                }

                if (activePokemon2.accuracyStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.accuracy = .33;
                            break;
                        case -1:
                            activePokemon2.accuracy = .50;
                            break;
                        case 0:
                            activePokemon2.accuracy = .67;
                            break;
                        case 1:
                            activePokemon2.accuracy = 1.5;
                            break;
                        case 2:
                            activePokemon2.accuracy = 2;
                    }
                }

                if (activePokemon2.accuracyStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon2.accuracy = .50;
                            break;
                        case -1:
                            activePokemon2.accuracy = .67;
                            break;
                        case 0:
                            activePokemon2.accuracy = 1;
                            break;
                        case 1:
                            activePokemon2.accuracy = 1.5;
                            break;
                        case 2:
                            activePokemon2.accuracy = 2;
                    }
                }

                if (activePokemon2.accuracyStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.accuracy = .67;
                            break;
                        case -1:
                            activePokemon2.accuracy = 1;
                            break;
                        case 0:
                            activePokemon2.accuracy = 1.5;
                            break;
                        case 1:
                            activePokemon2.accuracy = 2;
                            break;
                        case 2:
                            activePokemon2.accuracy = 2.5;
                    }
                }

                if (activePokemon2.accuracyStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.accuracy = 1;
                            break;
                        case -1:
                            activePokemon2.accuracy = 1.5;
                            break;
                        case 0:
                            activePokemon2.accuracy = 2;
                            break;
                        case 1:
                            activePokemon2.accuracy = 2.5;
                            break;
                        case 2:
                            activePokemon2.accuracy = 3;
                    }
                }

                if (activePokemon2.accuracyStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.accuracy = 1.5;
                            break;
                        case -1:
                            activePokemon2.accuracy = 2;
                            break;
                        case 0:
                            activePokemon2.accuracy = 2.5;
                            break;
                        case 1:
                            activePokemon2.accuracy = 3;
                            break;
                        case 2:
                            activePokemon2.accuracy = 3.5;
                    }
                }

                if (activePokemon2.accuracyStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.accuracy = 2;
                            break;
                        case -1:
                            activePokemon2.accuracy = 2.5;
                            break;
                        case 0:
                            activePokemon2.accuracy = 3;
                            break;
                        case 1:
                            activePokemon2.accuracy = 3.5;
                            break;
                        case 2:
                            activePokemon2.accuracy = 4;
                    }
                }

                if (activePokemon2.accuracyStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon2.accuracy = 2.5;
                            break;
                        case -1:
                            activePokemon2.accuracy = 3;
                            break;
                        case 0:
                            activePokemon2.accuracy = 3.5;
                            break;
                        case 1:
                            activePokemon2.accuracy = 4;
                            break;
                        case 2:
                            activePokemon2.accuracy = 4;
                    }
                }

                if (activePokemon2.accuracyStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon2.accuracy = 3;
                            break;
                        case -1:
                            activePokemon2.accuracy = 3.5;
                            break;
                        case 0:
                            activePokemon2.accuracy = 4;
                            break;
                        case 1:
                            activePokemon2.accuracy = 4;
                            break;
                        case 2:
                            activePokemon2.accuracy = 4;
                    }
                }

                activePokemon2.accuracyStage += stages;
                if (activePokemon2.accuracyStage > 6) {
                    activePokemon2.accuracyStage = 6;
                }
                if (activePokemon2.accuracyStage < -4) {
                    activePokemon2.accuracyStage = -4;
                }
            } else if (s.equals("evasion")) {
                if (activePokemon2.evasionStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.evasion = .25;
                            break;
                        case -1:
                            activePokemon2.evasion = .25;
                            break;
                        case 0:
                            activePokemon2.evasion = .25;
                            break;
                        case 1:
                            activePokemon2.evasion = .33;
                            break;
                        case 2:
                            activePokemon2.evasion = .50;
                    }
                }

                if (activePokemon2.evasionStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.evasion = .25;
                            break;
                        case -1:
                            activePokemon2.evasion = .25;
                            break;
                        case 0:
                            activePokemon2.evasion = .33;
                            break;
                        case 1:
                            activePokemon2.evasion = .50;
                            break;
                        case 2:
                            activePokemon2.evasion = .67;
                    }
                }

                if (activePokemon2.evasionStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.evasion = .25;
                            break;
                        case -1:
                            activePokemon2.evasion = .33;
                            break;
                        case 0:
                            activePokemon2.evasion = .50;
                            break;
                        case 1:
                            activePokemon2.evasion = .67;
                            break;
                        case 2:
                            activePokemon2.evasion = 1;
                    }
                }

                if (activePokemon2.evasionStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.evasion = .33;
                            break;
                        case -1:
                            activePokemon2.evasion = .50;
                            break;
                        case 0:
                            activePokemon2.evasion = .67;
                            break;
                        case 1:
                            activePokemon2.evasion = 1.5;
                            break;
                        case 2:
                            activePokemon2.evasion = 2;
                    }
                }

                if (activePokemon2.evasionStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon2.evasion = .50;
                            break;
                        case -1:
                            activePokemon2.evasion = .67;
                            break;
                        case 0:
                            activePokemon2.evasion = 1;
                            break;
                        case 1:
                            activePokemon2.evasion = 1.5;
                            break;
                        case 2:
                            activePokemon2.evasion = 2;
                    }
                }

                if (activePokemon2.evasionStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.evasion = .67;
                            break;
                        case -1:
                            activePokemon2.evasion = 1;
                            break;
                        case 0:
                            activePokemon2.evasion = 1.5;
                            break;
                        case 1:
                            activePokemon2.evasion = 2;
                            break;
                        case 2:
                            activePokemon2.evasion = 2.5;
                    }
                }

                if (activePokemon2.evasionStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.evasion = 1;
                            break;
                        case -1:
                            activePokemon2.evasion = 1.5;
                            break;
                        case 0:
                            activePokemon2.evasion = 2;
                            break;
                        case 1:
                            activePokemon2.evasion = 2.5;
                            break;
                        case 2:
                            activePokemon2.evasion = 3;
                    }
                }

                if (activePokemon2.evasionStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.evasion = 1.5;
                            break;
                        case -1:
                            activePokemon2.evasion = 2;
                            break;
                        case 0:
                            activePokemon2.evasion = 2.5;
                            break;
                        case 1:
                            activePokemon2.evasion = 3;
                            break;
                        case 2:
                            activePokemon2.evasion = 3.5;
                    }
                }

                if (activePokemon2.evasionStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.evasion = 2;
                            break;
                        case -1:
                            activePokemon2.evasion = 2.5;
                            break;
                        case 0:
                            activePokemon2.evasion = 3;
                            break;
                        case 1:
                            activePokemon2.evasion = 3.5;
                            break;
                        case 2:
                            activePokemon2.evasion = 4;
                    }
                }

                if (activePokemon2.evasionStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon2.evasion = 2.5;
                            break;
                        case -1:
                            activePokemon2.evasion = 3;
                            break;
                        case 0:
                            activePokemon2.evasion = 3.5;
                            break;
                        case 1:
                            activePokemon2.evasion = 4;
                            break;
                        case 2:
                            activePokemon2.evasion = 4;
                    }
                }

                if (activePokemon2.evasionStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon2.evasion = 3;
                            break;
                        case -1:
                            activePokemon2.evasion = 3.5;
                            break;
                        case 0:
                            activePokemon2.evasion = 4;
                            break;
                        case 1:
                            activePokemon2.evasion = 4;
                            break;
                        case 2:
                            activePokemon2.evasion = 4;
                    }
                }

                activePokemon2.evasionStage += stages;
                if (activePokemon2.evasionStage > 6) {
                    activePokemon2.evasionStage = 6;
                }
                if (activePokemon2.evasionStage < -4) {
                    activePokemon2.evasionStage = -4;
                }
            } else if (s.equals("attack")) {
                if (activePokemon2.attackStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .25);
                            break;
                        case -1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .25);
                            break;
                        case 0:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .25);
                            break;
                        case 1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .33);
                            break;
                        case 2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .50);
                    }
                }

                if (activePokemon2.attackStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .25);
                            break;
                        case -1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .25);
                            break;
                        case 0:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .33);
                            break;
                        case 1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .50);
                            break;
                        case 2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .67);
                    }
                }

                if (activePokemon2.attackStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .25);
                            break;
                        case -1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .33);
                            break;
                        case 0:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .50);
                            break;
                        case 1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .67);
                            break;
                        case 2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 1);
                    }
                }

                if (activePokemon2.attackStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .33);
                            break;
                        case -1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .50);
                            break;
                        case 0:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .67);
                            break;
                        case 1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 1.5);
                            break;
                        case 2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 2);
                    }
                }

                if (activePokemon2.attackStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .50);
                            break;
                        case -1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .67);
                            break;
                        case 0:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 1);
                            break;
                        case 1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 1.5);
                            break;
                        case 2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 2);
                    }
                }

                if (activePokemon2.attackStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * .67);
                            break;
                        case -1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 1);
                            break;
                        case 0:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 1.5);
                            break;
                        case 1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 2);
                            break;
                        case 2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 2.5);
                    }
                }

                if (activePokemon2.attackStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 1);
                            break;
                        case -1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 1.5);
                            break;
                        case 0:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 2);
                            break;
                        case 1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 2.5);
                            break;
                        case 2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 3);
                    }
                }

                if (activePokemon2.attackStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 1.5);
                            break;
                        case -1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 2);
                            break;
                        case 0:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 2.5);
                            break;
                        case 1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 3);
                            break;
                        case 2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 3.5);
                    }
                }

                if (activePokemon2.attackStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 2);
                            break;
                        case -1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 2.5);
                            break;
                        case 0:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 3);
                            break;
                        case 1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 3.5);
                            break;
                        case 2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 4);
                    }
                }

                if (activePokemon2.attackStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 2.5);
                            break;
                        case -1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 3);
                            break;
                        case 0:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 3.5);
                            break;
                        case 1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 4);
                            break;
                        case 2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 4);
                    }
                }

                if (activePokemon2.attackStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 3);
                            break;
                        case -1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 3.5);
                            break;
                        case 0:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 4);
                            break;
                        case 1:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 4);
                            break;
                        case 2:
                            activePokemon2.attack = (int) Math.round(activePokemon2.origAttack * 4);
                    }
                }

                activePokemon2.attackStage += stages;
                if (activePokemon2.attackStage > 6) {
                    activePokemon2.attackStage = 6;
                }
                if (activePokemon2.attackStage < -4) {
                    activePokemon2.attackStage = -4;
                }
            } else if (s.equals("defense")) {
                if (activePokemon2.defenseStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .25);
                            break;
                        case -1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .25);
                            break;
                        case 0:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .25);
                            break;
                        case 1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .33);
                            break;
                        case 2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .50);
                    }
                }

                if (activePokemon2.defenseStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .25);
                            break;
                        case -1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .25);
                            break;
                        case 0:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .33);
                            break;
                        case 1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .50);
                            break;
                        case 2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .67);
                    }
                }

                if (activePokemon2.defenseStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .25);
                            break;
                        case -1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .33);
                            break;
                        case 0:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .50);
                            break;
                        case 1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .67);
                            break;
                        case 2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 1);
                    }
                }

                if (activePokemon2.defenseStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .33);
                            break;
                        case -1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .50);
                            break;
                        case 0:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .67);
                            break;
                        case 1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 1.5);
                            break;
                        case 2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 2);
                    }
                }

                if (activePokemon2.defenseStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .50);
                            break;
                        case -1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .67);
                            break;
                        case 0:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 1);
                            break;
                        case 1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 1.5);
                            break;
                        case 2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 2);
                    }
                }

                if (activePokemon2.defenseStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * .67);
                            break;
                        case -1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 1);
                            break;
                        case 0:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 1.5);
                            break;
                        case 1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 2);
                            break;
                        case 2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 2.5);
                    }
                }

                if (activePokemon2.defenseStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 1);
                            break;
                        case -1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 1.5);
                            break;
                        case 0:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 2);
                            break;
                        case 1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 2.5);
                            break;
                        case 2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 3);
                    }
                }

                if (activePokemon2.defenseStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 1.5);
                            break;
                        case -1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 2);
                            break;
                        case 0:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 2.5);
                            break;
                        case 1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 3);
                            break;
                        case 2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 3.5);
                    }
                }

                if (activePokemon2.defenseStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 2);
                            break;
                        case -1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 2.5);
                            break;
                        case 0:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 3);
                            break;
                        case 1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 3.5);
                            break;
                        case 2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 4);
                    }
                }

                if (activePokemon2.defenseStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 2.5);
                            break;
                        case -1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 3);
                            break;
                        case 0:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 3.5);
                            break;
                        case 1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 4);
                            break;
                        case 2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 4);
                    }
                }

                if (activePokemon2.defenseStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 3);
                            break;
                        case -1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 3.5);
                            break;
                        case 0:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 4);
                            break;
                        case 1:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 4);
                            break;
                        case 2:
                            activePokemon2.defense = (int) Math.round(activePokemon2.origDefense * 4);
                    }
                }

                activePokemon2.defenseStage += stages;
                if (activePokemon2.defenseStage > 6) {
                    activePokemon2.defenseStage = 6;
                }
                if (activePokemon2.defenseStage < -4) {
                    activePokemon2.defenseStage = -4;
                }
            } else if (s.equals("spattack")) {
                if (activePokemon2.spattackStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .25);
                            break;
                        case -1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .25);
                            break;
                        case 0:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .25);
                            break;
                        case 1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .33);
                            break;
                        case 2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .50);
                    }
                }

                if (activePokemon2.spattackStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .25);
                            break;
                        case -1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .25);
                            break;
                        case 0:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .33);
                            break;
                        case 1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .50);
                            break;
                        case 2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .67);
                    }
                }

                if (activePokemon2.spattackStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .25);
                            break;
                        case -1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .33);
                            break;
                        case 0:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .50);
                            break;
                        case 1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .67);
                            break;
                        case 2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 1);
                    }
                }

                if (activePokemon2.spattackStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .33);
                            break;
                        case -1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .50);
                            break;
                        case 0:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .67);
                            break;
                        case 1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 1.5);
                            break;
                        case 2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 2);
                    }
                }

                if (activePokemon2.spattackStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .50);
                            break;
                        case -1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .67);
                            break;
                        case 0:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 1);
                            break;
                        case 1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 1.5);
                            break;
                        case 2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 2);
                    }
                }

                if (activePokemon2.spattackStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * .67);
                            break;
                        case -1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 1);
                            break;
                        case 0:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 1.5);
                            break;
                        case 1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 2);
                            break;
                        case 2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 2.5);
                    }
                }

                if (activePokemon2.spattackStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 1);
                            break;
                        case -1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 1.5);
                            break;
                        case 0:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 2);
                            break;
                        case 1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 2.5);
                            break;
                        case 2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 3);
                    }
                }

                if (activePokemon2.spattackStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 1.5);
                            break;
                        case -1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 2);
                            break;
                        case 0:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 2.5);
                            break;
                        case 1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 3);
                            break;
                        case 2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 3.5);
                    }
                }

                if (activePokemon2.spattackStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 2);
                            break;
                        case -1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 2.5);
                            break;
                        case 0:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 3);
                            break;
                        case 1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 3.5);
                            break;
                        case 2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 4);
                    }
                }

                if (activePokemon2.spattackStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 2.5);
                            break;
                        case -1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 3);
                            break;
                        case 0:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 3.5);
                            break;
                        case 1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 4);
                            break;
                        case 2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 4);
                    }
                }

                if (activePokemon2.spattackStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 3);
                            break;
                        case -1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 3.5);
                            break;
                        case 0:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 4);
                            break;
                        case 1:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 4);
                            break;
                        case 2:
                            activePokemon2.spattack = (int) Math.round(activePokemon2.origSpattack * 4);
                    }
                }

                activePokemon2.spattackStage += stages;
                if (activePokemon2.spattackStage > 6) {
                    activePokemon2.spattackStage = 6;
                }
                if (activePokemon2.spattackStage < -4) {
                    activePokemon2.spattackStage = -4;
                }
            } else if (s.equals("spdefense")) {
                if (activePokemon2.spdefenseStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .25);
                            break;
                        case -1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .25);
                            break;
                        case 0:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .25);
                            break;
                        case 1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .33);
                            break;
                        case 2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .50);
                    }
                }

                if (activePokemon2.spdefenseStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .25);
                            break;
                        case -1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .25);
                            break;
                        case 0:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .33);
                            break;
                        case 1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .50);
                            break;
                        case 2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .67);
                    }
                }

                if (activePokemon2.spdefenseStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .25);
                            break;
                        case -1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .33);
                            break;
                        case 0:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .50);
                            break;
                        case 1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .67);
                            break;
                        case 2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 1);
                    }
                }

                if (activePokemon2.spdefenseStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .33);
                            break;
                        case -1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .50);
                            break;
                        case 0:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .67);
                            break;
                        case 1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 1.5);
                            break;
                        case 2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 2);
                    }
                }

                if (activePokemon2.spdefenseStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .50);
                            break;
                        case -1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .67);
                            break;
                        case 0:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 1);
                            break;
                        case 1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 1.5);
                            break;
                        case 2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 2);
                    }
                }

                if (activePokemon2.spdefenseStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * .67);
                            break;
                        case -1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 1);
                            break;
                        case 0:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 1.5);
                            break;
                        case 1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 2);
                            break;
                        case 2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 2.5);
                    }
                }

                if (activePokemon2.spdefenseStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 1);
                            break;
                        case -1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 1.5);
                            break;
                        case 0:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 2);
                            break;
                        case 1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 2.5);
                            break;
                        case 2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 3);
                    }
                }

                if (activePokemon2.spdefenseStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 1.5);
                            break;
                        case -1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 2);
                            break;
                        case 0:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 2.5);
                            break;
                        case 1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 3);
                            break;
                        case 2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 3.5);
                    }
                }

                if (activePokemon2.spdefenseStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 2);
                            break;
                        case -1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 2.5);
                            break;
                        case 0:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 3);
                            break;
                        case 1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 3.5);
                            break;
                        case 2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 4);
                    }
                }

                if (activePokemon2.spdefenseStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 2.5);
                            break;
                        case -1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 3);
                            break;
                        case 0:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 3.5);
                            break;
                        case 1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 4);
                            break;
                        case 2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 4);
                    }
                }

                if (activePokemon2.spdefenseStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 3);
                            break;
                        case -1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 3.5);
                            break;
                        case 0:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 4);
                            break;
                        case 1:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 4);
                            break;
                        case 2:
                            activePokemon2.spdefense = (int) Math.round(activePokemon2.origSpdefense * 4);
                    }
                }

                activePokemon2.spdefenseStage += stages;
                if (activePokemon2.spdefenseStage > 6) {
                    activePokemon2.spdefenseStage = 6;
                }
                if (activePokemon2.spdefenseStage < -4) {
                    activePokemon2.spdefenseStage = -4;
                }
            } else if (s.equals("speed")) {
                if (activePokemon2.speedStage == -4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .25);
                            break;
                        case -1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .25);
                            break;
                        case 0:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .25);
                            break;
                        case 1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .33);
                            break;
                        case 2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .50);
                    }
                }

                if (activePokemon2.speedStage == -3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .25);
                            break;
                        case -1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .25);
                            break;
                        case 0:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .33);
                            break;
                        case 1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .50);
                            break;
                        case 2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .67);
                    }
                }

                if (activePokemon2.speedStage == -2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .25);
                            break;
                        case -1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .33);
                            break;
                        case 0:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .50);
                            break;
                        case 1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .67);
                            break;
                        case 2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 1);
                    }
                }

                if (activePokemon2.speedStage == -1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .33);
                            break;
                        case -1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .50);
                            break;
                        case 0:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .67);
                            break;
                        case 1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 1.5);
                            break;
                        case 2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 2);
                    }
                }

                if (activePokemon2.speedStage == 0) {
                    switch (stages) {
                        case -2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .50);
                            break;
                        case -1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .67);
                            break;
                        case 0:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 1);
                            break;
                        case 1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 1.5);
                            break;
                        case 2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 2);
                    }
                }

                if (activePokemon2.speedStage == 1) {
                    switch (stages) {
                        case -2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * .67);
                            break;
                        case -1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 1);
                            break;
                        case 0:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 1.5);
                            break;
                        case 1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 2);
                            break;
                        case 2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 2.5);
                    }
                }

                if (activePokemon2.speedStage == 2) {
                    switch (stages) {
                        case -2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 1);
                            break;
                        case -1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 1.5);
                            break;
                        case 0:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 2);
                            break;
                        case 1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 2.5);
                            break;
                        case 2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 3);
                    }
                }

                if (activePokemon2.speedStage == 3) {
                    switch (stages) {
                        case -2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 1.5);
                            break;
                        case -1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 2);
                            break;
                        case 0:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 2.5);
                            break;
                        case 1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 3);
                            break;
                        case 2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 3.5);
                    }
                }

                if (activePokemon2.speedStage == 4) {
                    switch (stages) {
                        case -2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 2);
                            break;
                        case -1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 2.5);
                            break;
                        case 0:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 3);
                            break;
                        case 1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 3.5);
                            break;
                        case 2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 4);
                    }
                }

                if (activePokemon2.speedStage == 5) {
                    switch (stages) {
                        case -2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 2.5);
                            break;
                        case -1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 3);
                            break;
                        case 0:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 3.5);
                            break;
                        case 1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 4);
                            break;
                        case 2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 4);
                    }
                }

                if (activePokemon2.speedStage == 6) {
                    switch (stages) {
                        case -2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 3);
                            break;
                        case -1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 3.5);
                            break;
                        case 0:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 4);
                            break;
                        case 1:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 4);
                            break;
                        case 2:
                            activePokemon2.speed = (int) Math.round(activePokemon2.origSpeed * 4);
                    }
                }

                activePokemon2.speedStage += stages;
                if (activePokemon2.speedStage > 6) {
                    activePokemon2.speedStage = 6;
                }
                if (activePokemon2.speedStage < -4) {
                    activePokemon2.speedStage = -4;
                }
            }

        }
    }

    public static double getEffectiveness(AbstractMove move, AbstractBiomon p, int type) {
        int t;

        if (type == 1) {
            t = p.type1;
        } else {
            t = p.type2;
        }

        /*
    Types and their numbers:

    Normal: 1       Fighting: 7     Rock: 13
    Fire: 2         Poison: 8       Ghost: 14
    Water: 3        Ground: 9       Dragon: 15
    Electric: 4     Flying: 10     Dark: 16
    Grass: 5        Psychic: 11     Steel: 17
    Ice: 6          Bug: 12         Fairy: 18

    Incase of any additional confusion on typing and weaknesses, resistances
    and immunities please reference this link:
    https://img.pokemondb.net/images/typechart.png
     */

        //number to keep track of the move's effectiveness
        double num = 1;

        if (t < 1) {
            return num;
        }

        int m = move.type;


        //Entire type chart for move effectiveness regarding the Pokemon's type1
        switch (m) {
            case 1:
                switch (t) {
                    case 13:
                    case 17:
                        num *= .5;
                        break;
                    case 14:
                        num *= 0;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 2:
                switch (t) {
                    case 2:
                    case 3:
                    case 13:
                    case 15:
                        num *= .5;
                        break;
                    case 5:
                    case 6:
                    case 12:
                    case 17:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 3:
                switch (t) {
                    case 3:
                    case 5:
                    case 15:
                        num *= .5;
                        break;
                    case 13:
                    case 9:
                    case 2:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 4:
                switch (t) {
                    case 4:
                    case 5:
                    case 15:
                        num *= .5;
                        break;
                    case 9:
                        num *= 0;
                        break;
                    case 3:
                    case 10:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 5:
                switch (t) {
                    case 2:
                    case 5:
                    case 8:
                    case 10:
                    case 12:
                    case 15:
                    case 17:
                        num *= .5;
                        break;
                    case 3:
                    case 9:
                    case 13:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 6:
                switch (t) {
                    case 2:
                    case 3:
                    case 6:
                    case 17:
                        num *= .5;
                        break;
                    case 5:
                    case 9:
                    case 10:
                    case 15:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 7:
                switch (t) {
                    case 8:
                    case 10:
                    case 11:
                    case 12:
                    case 18:
                        num *= .5;
                        break;
                    case 14:
                        num *= 0;
                        break;
                    case 1:
                    case 6:
                    case 13:
                    case 16:
                    case 17:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 8:
                switch (t) {
                    case 8:
                    case 9:
                    case 13:
                    case 14:
                        num *= .5;
                        break;
                    case 17:
                        num *= 0;
                        break;
                    case 5:
                    case 18:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 9:
                switch (t) {
                    case 5:
                    case 12:
                        num *= .5;
                        break;
                    case 10:
                        if (p.roosted) {
                            num *= 1;
                        } else {
                            num *= 0;
                        }
                        break;
                    case 2:
                    case 4:
                    case 8:
                    case 13:
                    case 17:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 10:
                switch (t) {
                    case 4:
                    case 13:
                    case 17:
                        num *= .5;
                        break;
                    case 5:
                    case 7:
                    case 12:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 11:
                switch (t) {
                    case 11:
                    case 17:
                        num *= .5;
                        break;
                    case 16:
                        num *= 0;
                        break;
                    case 7:
                    case 8:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 12:
                switch (t) {
                    case 2:
                    case 7:
                    case 8:
                    case 10:
                    case 14:
                    case 17:
                    case 18:
                        num *= .5;
                        break;
                    case 5:
                    case 11:
                    case 16:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 13:
                switch (t) {
                    case 7:
                    case 9:
                    case 17:
                        num *= .5;
                        break;
                    case 2:
                    case 6:
                    case 10:
                    case 12:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 14:
                switch (t) {
                    case 16:
                        num *= .5;
                        break;
                    case 1:
                        num *= 0;
                        break;
                    case 11:
                    case 14:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 15:
                switch (t) {
                    case 17:
                        num *= .5;
                        break;
                    case 18:
                        num *= 0;
                        break;
                    case 15:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 16:
                switch (t) {
                    case 7:
                    case 16:
                    case 18:
                        num *= .5;
                        break;
                    case 11:
                    case 14:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 17:
                switch (t) {
                    case 2:
                    case 3:
                    case 4:
                    case 17:
                        num *= .5;
                        break;
                    case 6:
                    case 13:
                    case 18:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
            case 18:
                switch (t) {
                    case 2:
                    case 8:
                    case 17:
                        num *= .5;
                        break;
                    case 7:
                    case 15:
                    case 16:
                        num *= 2;
                        break;
                    default:
                        num *= 1;
                        break;
                }
                break;
        }
        return num;
    }

    public static void calcP1Damage() {
        move1DirectlyBeforeAttackingEffects();

        boolean stab1 = false;
        boolean p1Crit = false;

        Random rand = new Random();

        if (!hasAbility(2, "battle armor")) {
            if (activePokemon1.critStage == 0) {
                int n = rand.nextInt(10000);

                if (n < 625) {
                    p1Crit = true;
                }
            } else if (activePokemon1.critStage == 1) {
                int n = rand.nextInt(10000);

                if (n < 1250) {
                    p1Crit = true;
                }
            } else if (activePokemon1.critStage == 2) {
                int n = rand.nextInt(1000);

                if (n < 500) {
                    p1Crit = true;
                }
            } else {
                p1Crit = true;
            }
        }


        int defenseUsed1;

        if (usedMove1.type == activePokemon1.type1 || usedMove1.type == activePokemon1.type2) {
            stab1 = true;
        }

        if (usedMove1.category == 1) {
            defenseUsed1 = activePokemon2.defense;
        } else {
            defenseUsed1 = activePokemon2.spdefense;
        }

        if (isMove1("psyshock") || isMove1("secret sword")) {
            defenseUsed1 = activePokemon2.defense;
        }

        if (hasAbility(1, "aerilate")) {
            if (usedMove1.type == 1) {
                usedMove1.power *= 1.3;
                usedMove1.type = 10;
            }
        }
        if (hasAbility(1, "blaze") && activePokemon1.hp < activePokemon1.fractionHP(3) && usedMove1.type == 2) {
            usedMove1.power *= 1.5;
        }
        if (hasAbility(1, "swarm") && activePokemon1.hp < activePokemon1.fractionHP(3) && usedMove1.type == 12) {
            usedMove1.power *= 1.5;
        }
        if (hasAbility(1, "flare boost") && activePokemon1.burn && usedMove1.category == 2) {
            usedMove1.power *= 1.5;
        }
        if (activePokemon1.flashFired && usedMove1.type == 2) {
            usedMove1.power *= 1.5;
        }

        double effectiveness1 = 1;
        effectiveness1 *= getEffectiveness(usedMove1, activePokemon2, 1);
        effectiveness1 *= getEffectiveness(usedMove1, activePokemon2, 2);

        p1Effectiveness = effectiveness1;

        int A = 100;

        int B;

        if (usedMove1.category == 1) {
            B = activePokemon1.attack;
            if (activePokemon1.burn) {
                if (!activePokemon1.ability.equals("guts")) {
                    B /= 2;
                }
            }
        } else {
            B = activePokemon1.spattack;
        }

        //Lowering The Damage!!!
        usedMove1.power-=20;
        int C = usedMove1.power;
        usedMove1.power+=20;

        int D = defenseUsed1;
        double X = 1.0;
        double Y = effectiveness1 * 10;


        if (stab1 == true) {
            X = 1.5;
            if (hasAbility(1, "adaptibility")) {
                X = 2;
            }
        }

        if (p1Crit == true) {

            A = 200;
            p1Critt =true;

        }

        Random rand2 = new Random();



        int Z = rand2.nextInt(38) + 217;

        p1Damage = ((((((((2 * A / 5 + 2) * B * C) / D) / 50) + 2) * X) * (Y / 10) * Z) / 255);


        if (!(hasAbility(1, "air lock") || hasAbility(2, "air lock") || hasAbility(1, "cloud nine") || hasAbility(2, "cloud nine"))) {
            if (weather == 1 && usedMove1.type == 2) {
                p1Damage *= 1.5;
            } else if (weather == 2 && usedMove1.type == 3) {
                p1Damage *= 1.5;
            }
        }

        if (activePokemon1.speed < activePokemon2.speed) {
            usedMove1.power *= 1.3;
        }
        if (p2Ref && usedMove1.category == 1) {
            p1Damage /= 2;
        }
        if (p2LS && usedMove1.category == 2) {
            p1Damage /= 2;
        }

        if (activePokemon2.protectedd || activePokemon2.detected) {
            p1Damage = 0;
        }
/*
        A = attacker's Level
        B = attacker's Attack or Special
        C = attack Power
            D = defender's Defense or Special
        X = same-Type attack bonus (1 or 1.5)
        Y = Type modifiers (40, 20, 10, 5, 2.5, or 0)
        Z = a random number between 217 and 255*/

    }

    public static void calcP1DamageHost() {
        move1DirectlyBeforeAttackingEffects();

        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        boolean stab1 = false;
        boolean p1Crit = false;

        Random rand = new Random();

        if (!hasAbility(2, "battle armor")) {
            if (activePokemon1.critStage == 0) {
                int n = rand.nextInt(10000);

                if (n < 625) {
                    p1Crit = true;
                }
            } else if (activePokemon1.critStage == 1) {
                int n = rand.nextInt(10000);

                if (n < 1250) {
                    p1Crit = true;
                }
            } else if (activePokemon1.critStage == 2) {
                int n = rand.nextInt(1000);

                if (n < 500) {
                    p1Crit = true;
                }
            } else {
                p1Crit = true;
            }
        }


        int defenseUsed1;

        if (usedMove1.type == activePokemon1.type1 || usedMove1.type == activePokemon1.type2) {
            stab1 = true;
        }

        if (usedMove1.category == 1) {
            defenseUsed1 = activePokemon2.defense;
        } else {
            defenseUsed1 = activePokemon2.spdefense;
        }

        if (isMove1("psyshock") || isMove1("secret sword")) {
            defenseUsed1 = activePokemon2.defense;
        }

        if (hasAbility(1, "aerilate")) {
            if (usedMove1.type == 1) {
                usedMove1.power *= 1.3;
                usedMove1.type = 10;
            }
        }
        if (hasAbility(1, "blaze") && activePokemon1.hp < activePokemon1.fractionHP(3) && usedMove1.type == 2) {
            usedMove1.power *= 1.5;
        }
        if (hasAbility(1, "swarm") && activePokemon1.hp < activePokemon1.fractionHP(3) && usedMove1.type == 12) {
            usedMove1.power *= 1.5;
        }
        if (hasAbility(1, "flare boost") && activePokemon1.burn && usedMove1.category == 2) {
            usedMove1.power *= 1.5;
        }
        if (activePokemon1.flashFired && usedMove1.type == 2) {
            usedMove1.power *= 1.5;
        }

        double effectiveness1 = 1;
        effectiveness1 *= getEffectiveness(usedMove1, activePokemon2, 1);
        effectiveness1 *= getEffectiveness(usedMove1, activePokemon2, 2);

        p1Effectiveness = effectiveness1;

        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Effectiveness").setValue(p1Effectiveness);

        int A = 100;

        int B;

        if (usedMove1.category == 1) {
            B = activePokemon1.attack;
            if (activePokemon1.burn) {
                if (!activePokemon1.ability.equals("guts")) {
                    B /= 2;
                }
            }
        } else {
            B = activePokemon1.spattack;
        }

        //Lowering The Damage!!!
        usedMove1.power-=20;
        int C = usedMove1.power;
        usedMove1.power+=20;

        int D = defenseUsed1;
        double X = 1.0;
        double Y = effectiveness1 * 10;


        if (stab1 == true) {
            X = 1.5;
            if (hasAbility(1, "adaptibility")) {
                X = 2;
            }
        }

        if (p1Crit == true) {

            A = 200;
            p1Critt =true;
            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Crit").setValue("true");

        }

        Random rand2 = new Random();



        int Z = rand2.nextInt(38) + 217;

        p1Damage = ((((((((2 * A / 5 + 2) * B * C) / D) / 50) + 2) * X) * (Y / 10) * Z) / 255);


        if (!(hasAbility(1, "air lock") || hasAbility(2, "air lock") || hasAbility(1, "cloud nine") || hasAbility(2, "cloud nine"))) {
            if (weather == 1 && usedMove1.type == 2) {
                p1Damage *= 1.5;
            } else if (weather == 2 && usedMove1.type == 3) {
                p1Damage *= 1.5;
            }
        }

        if (activePokemon1.speed < activePokemon2.speed) {
            usedMove1.power *= 1.3;
        }
        if (p2Ref && usedMove1.category == 1) {
            p1Damage /= 2;
        }
        if (p2LS && usedMove1.category == 2) {
            p1Damage /= 2;
        }

        if (activePokemon2.protectedd || activePokemon2.detected) {
            p1Damage = 0;
        }

        p1Damage = (int)Math.round(p1Damage);

        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Damage").setValue(p1Damage);

/*
        A = attacker's Level
        B = attacker's Attack or Special
        C = attack Power
            D = defender's Defense or Special
        X = same-Type attack bonus (1 or 1.5)
        Y = Type modifiers (40, 20, 10, 5, 2.5, or 0)
        Z = a random number between 217 and 255*/

    }

    public static void calcP2Damage() {
        move2DirectlyBeforeAttackingEffects();
        boolean stab = false;
        boolean Crit = false;

        Random rand = new Random();

        if (activePokemon2.critStage == 0) {
            int n = rand.nextInt(10000);

            if (n < 625) {
                Crit = true;
            }
        } else if (activePokemon2.critStage == 1) {
            int n = rand.nextInt(10000);

            if (n < 1250) {
                Crit = true;
            }
        } else if (activePokemon2.critStage == 2) {
            int n = rand.nextInt(1000);

            if (n < 500) {
                Crit = true;
            }
        } else {
            Crit = true;
        }

        int defenseUsed;

        if (usedMove2.type == activePokemon2.type1 || usedMove2.type == activePokemon2.type2) {
            stab = true;
        }

        if (usedMove2.category == 1) {
            defenseUsed = activePokemon1.defense;
        } else {
            defenseUsed = activePokemon1.spdefense;
        }
        if (isMove1("psyshock") || isMove1("secret sword")) {
            defenseUsed = activePokemon1.defense;
        }

        double effectiveness = getEffectiveness(usedMove2, activePokemon1, 1);
        effectiveness *= getEffectiveness(usedMove2, activePokemon1, 2);

        p2Effectiveness = effectiveness;


        int A = 100;
        int B;

        if (usedMove2.category == 1) {
            B = activePokemon2.attack;
            if (activePokemon2.burn) {
                if (!activePokemon2.ability.equals("guts")) {
                    B /= 2;
                }
            }
        } else {
            B = activePokemon2.spattack;
        }

        //Lowering The Damage!!!
        usedMove2.power-=20;
        int C = usedMove2.power;
        usedMove2.power+=20;

        int D = defenseUsed;
        double X = 1.0;
        double Y = effectiveness * 10;


        if (stab == true) {
            X = 1.5;
        }

        if (Crit == true) {
            A = 200;
            p2Critt =true;
        }

        Random rand2 = new Random();




        int Z = rand2.nextInt(38) + 217;

        p2Damage = ((((((((2 * A / 5 + 2) * B * C) / D) / 50) + 2) * X) * (Y / 10) * Z) / 255);


        if (p1Ref && usedMove2.category == 1) {
            p2Damage /= 2;
        }
        if (p1LS && usedMove2.category == 2) {
            p2Damage /= 2;
        }

        if (activePokemon1.protectedd || activePokemon1.detected) {
            p2Damage = 0;
        }
/*
        A = attacker's Level
        B = attacker's Attack or Special
        C = attack Power
            D = defender's Defense or Special
        X = same-Type attack bonus (1 or 1.5)
        Y = Type modifiers (40, 20, 10, 5, 2.5, or 0)
        Z = a random number between 217 and 255*/

    }

    public static void calcP2DamageHostVersion() {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        move2DirectlyBeforeAttackingEffects();
        boolean stab = false;
        boolean Crit = false;

        Random rand = new Random();

        if (activePokemon2.critStage == 0) {
            int n = rand.nextInt(10000);

            if (n < 625) {
                Crit = true;
            }
        } else if (activePokemon2.critStage == 1) {
            int n = rand.nextInt(10000);

            if (n < 1250) {
                Crit = true;
            }
        } else if (activePokemon2.critStage == 2) {
            int n = rand.nextInt(1000);

            if (n < 500) {
                Crit = true;
            }
        } else {
            Crit = true;
        }

        int defenseUsed;

        if (usedMove2.type == activePokemon2.type1 || usedMove2.type == activePokemon2.type2) {
            stab = true;
        }

        if (usedMove2.category == 1) {
            defenseUsed = activePokemon1.defense;
        } else {
            defenseUsed = activePokemon1.spdefense;
        }
        if (isMove1("psyshock") || isMove1("secret sword")) {
            defenseUsed = activePokemon1.defense;
        }

        double effectiveness = getEffectiveness(usedMove2, activePokemon1, 1);
        effectiveness *= getEffectiveness(usedMove2, activePokemon1, 2);

        p2Effectiveness = effectiveness;
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Effectiveness").setValue(p2Effectiveness);

        int A = 100;
        int B;

        if (usedMove2.category == 1) {
            B = activePokemon2.attack;
            if (activePokemon2.burn) {
                if (!activePokemon2.ability.equals("guts")) {
                    B /= 2;
                }
            }
        } else {
            B = activePokemon2.spattack;
        }

        //Lowering The Damage!!!
        usedMove2.power-=20;
        int C = usedMove2.power;
        usedMove2.power+=20;

        int D = defenseUsed;
        double X = 1.0;
        double Y = effectiveness * 10;


        if (stab == true) {
            X = 1.5;
        }

        if (Crit == true) {
            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Crit").setValue("true");
            A = 200;
            p2Critt =true;
        }

        Random rand2 = new Random();

        int Z = rand2.nextInt(38) + 217;

        p2Damage = ((((((((2 * A / 5 + 2) * B * C) / D) / 50) + 2) * X) * (Y / 10) * Z) / 255);


        if (p1Ref && usedMove2.category == 1) {
            p2Damage /= 2;
        }
        if (p1LS && usedMove2.category == 2) {
            p2Damage /= 2;
        }

        if (activePokemon1.protectedd || activePokemon1.detected) {
            p2Damage = 0;
        }

        p2Damage = (int)Math.round(p2Damage);

        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Damage").setValue(p2Damage);

/*
        A = attacker's Level
        B = attacker's Attack or Special
        C = attack Power
            D = defender's Defense or Special
        X = same-Type attack bonus (1 or 1.5)
        Y = Type modifiers (40, 20, 10, 5, 2.5, or 0)
        Z = a random number between 217 and 255*/

    }

    public static boolean p1Hit() {
        if (hasAbility(1, "compound eyes")) {
            usedMove1.accuracy *= 1.3;
        }
        boolean Hit = false;

        int n;
        Random rand = new Random();
        n = rand.nextInt(100);

        if (n < usedMove1.accuracy) {
            Hit = true;
        } else {
        }

        activePokemon1.hit=Hit;
        return Hit;
    }

    public static boolean p1HitHost() {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        if (hasAbility(1, "compound eyes")) {
            usedMove1.accuracy *= 1.3;
        }
        boolean Hit = false;

        int n;
        Random rand = new Random();
        n = rand.nextInt(100);

        if (n < usedMove1.accuracy) {
            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Hit").setValue("true");
            Hit = true;
        } else {
            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Hit").setValue("false");
        }

        activePokemon1.hit=Hit;
        return Hit;
    }

    public static boolean p2Hit() {

        boolean Hit = false;
        int n;
        Random rand = new Random();
        n = rand.nextInt(100);

        if (n < usedMove2.accuracy) {
            Hit = true;
        }

        activePokemon2.hit=Hit;
        return Hit;
    }

    public static boolean p2HitHost() {

        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        boolean Hit = false;
        int n;
        Random rand = new Random();
        n = rand.nextInt(100);

        if (n < usedMove2.accuracy) {
            Hit = true;
            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Hit").setValue("true");
        } else {
            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Hit").setValue("false");
        }



        activePokemon2.hit=Hit;
        return Hit;
    }

    //returns 1 if p1 moves first, 2 if p2 moves first
    public static int orderOfMoves() {

        int first = 0;

        if (p1Switch) {
            usedMove1.priority = -10;
        } else if (p2Switch) {
            usedMove2.priority = -10;
        }

        if (usedMove1.priority > usedMove2.priority) {
            first = 1;
        } else if (usedMove1.priority < usedMove2.priority) {
            first = 2;
        } else {
            if (activePokemon1.speed > activePokemon2.speed) {
                first = 1;
            } else if (activePokemon1.speed < activePokemon2.speed) {
                first = 2;
            } else {
                Random rand = new Random();
                int n = rand.nextInt(100);

                if (n < 50) {
                    first = 1;
                } else {
                    first = 2;
                }
            }
        }

        absoluteOrderOfMoves=first;

        if(playingOnline)
        {
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            //Giving opposite because client is mirrored
            if(first==1)
            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Absolute Order of Moves").setValue("2");
            else
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Absolute Order of Moves").setValue("1");
        }


        return first;

    }

    //Pretty sure this can be done without involving the database (other than getting the damage and hp and stuff)
    public static void dealDamage1() {
        activePokemon2.hp -= p1Damage;
        p2Pokemon[activePokemon2.orderNum].hp -= p1Damage;

        if (activePokemon2.hp <= 0) {
            activePokemon2.hp = 0;
            activePokemon2.fainted = true;
            p2Pokemon[activePokemon2.orderNum].fainted = true;
        }

        if(host || !playingOnline)
        healthAfterHit2= activePokemon2.hpPercentAsInt();

        if(host)
        {
            //Sending database these numbers
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Health After Hit").setValue(healthAfterHit2);

        }

        if(playingOnline && !host)
        {
            move1DirectlyAfterAttackingEffectsClientVersion();
        }
        else
        {
            move1DirectlyAfterAttackingEffects();
        }

    }

    public static void dealDamage2() {
        activePokemon1.hp -= p2Damage;
        p1Pokemon[activePokemon1.orderNum].hp -= p2Damage;

        if (activePokemon1.hp <= 0)
        {
            activePokemon1.hp = 0;
            activePokemon1.fainted = true;
            p1Pokemon[activePokemon1.orderNum].fainted = true;
        }

        if(host || !playingOnline)
        healthAfterHit1= activePokemon1.hpPercentAsInt();

        if(host && playingOnline)
        {
            //Sending database these numbers
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Health After Hit").setValue(healthAfterHit1);

        }

        if(playingOnline && !host)
        {
            move2DirectlyAfterAttackingEffectsClientVersion();
        }
        else
        {
            move2DirectlyAfterAttackingEffects();
        }
    }

    public static void randomSwitch(int player) {
        int n;
        if (player == 1) {
            do {
                Random rand = new Random();
                n = rand.nextInt(5);
            } while ((n == activePokemon1.orderNum) || (p1Pokemon[n].fainted));
            p1Pokemon[n].resetPokemon();
            ;
            activePokemon1 = p1Pokemon[n];
        } else if (player == 2) {
            do {
                Random rand = new Random();
                n = rand.nextInt(5);
            } while ((n == activePokemon2.orderNum) || (p2Pokemon[n].fainted));
            p2Pokemon[n].resetPokemon();
            ;
            activePokemon2 = p2Pokemon[n];
        }
    }

    public static void changeWeather(int player) {
        if (player == 1) {
            if (isMove1("sunny day")) {
                if (weather != 1) {
                    weather = 1;
                    if (activePokemon1.item.equals("heat rock")) {
                        weatherTurnsLeft = 8;
                    } else {
                        weatherTurnsLeft = 5;
                    }
                } else {
                    usedMove1.fail = true;
                }
            }
            if (isMove1("rain dance")) {
                if (weather != 2) {
                    weather = 2;
                    if (activePokemon1.item.equals("damp rock")) {
                        weatherTurnsLeft = 8;
                    } else {
                        weatherTurnsLeft = 5;
                    }
                } else {
                    usedMove1.fail = true;
                }
            }
            if (isMove1("sandstorm")) {
                if (weather != 3) {
                    weather = 3;
                    if (activePokemon1.item.equals("smooth rock")) {
                        weatherTurnsLeft = 8;
                    } else {
                        weatherTurnsLeft = 5;
                    }
                } else {
                    usedMove1.fail = true;
                }
            }
            if (isMove1("hail")) {
                if (weather != 4) {
                    weather = 4;
                    if (activePokemon1.item.equals("icy rock")) {
                        weatherTurnsLeft = 8;
                    } else {
                        weatherTurnsLeft = 5;
                    }
                } else {
                    usedMove1.fail = true;
                }
            }

        }
        if (player == 2) {
            if (isMove2("sunny day")) {
                if (weather != 1) {
                    weather = 1;
                    if (activePokemon2.item.equals("heat rock")) {
                        weatherTurnsLeft = 8;
                    } else {
                        weatherTurnsLeft = 5;
                    }
                } else {
                    usedMove2.fail = true;
                }
            }
            if (isMove2("rain dance")) {
                if (weather != 2) {
                    weather = 2;
                    if (activePokemon2.item.equals("damp rock")) {
                        weatherTurnsLeft = 8;
                    } else {
                        weatherTurnsLeft = 5;
                    }
                } else {
                    usedMove2.fail = true;
                }
            }
            if (isMove2("sandstorm")) {
                if (weather != 3) {
                    weather = 3;
                    if (activePokemon2.item.equals("smooth rock")) {
                        weatherTurnsLeft = 8;
                    } else {
                        weatherTurnsLeft = 5;
                    }
                } else {
                    usedMove2.fail = true;
                }
            }
            if (isMove2("hail")) {
                if (weather != 4) {
                    weather = 4;
                    if (activePokemon2.item.equals("icy rock")) {
                        weatherTurnsLeft = 8;
                    } else {
                        weatherTurnsLeft = 5;
                    }
                } else {
                    usedMove2.fail = true;
                }
            }

        }
    }

    static void p1StatusMove() {
        healthBeforeHealing1= activePokemon1.hpPercentAsInt();
        if (isMove1("swords dance")) {
            statChange("attack", 1, 2);
        } else if (isMove1("quiver dance")) {
            statChange("spattack", 1, 1);
            statChange("spdefense", 1, 1);
            statChange("speed", 1, 1);
        } else if (isMove1("dragon dance")) {
            statChange("attack", 1, 1);
            statChange("speed", 1, 1);
        } else if (isMove1("coil")) {
            statChange("attack", 1, 1);
            statChange("defense", 1, 1);
            statChange("accuracy", 1, 1);
        } else if (isMove1("nasty plot")) {
            statChange("spattack", 1, 2);
        } else if (isMove1("calm mind")) {
            statChange("spattack", 1, 1);
            statChange("spdefense", 1, 1);
        } else if (isMove1("cosmic power")) {
            statChange("defense", 1, 1);
            statChange("spdefense", 1, 1);
        } else if (isMove1("bulk up")) {
            statChange("defense", 1, 1);
            statChange("attack", 1, 1);
        } else if (isMove1("string shot")) {
            statChange("speed", 2, -1);
        } else if (isMove1("cotton guard")) {
            statChange("defense", 1, 3);
        } else if (isMove1("meditate")) {
            statChange("attack", 1, 1);
        } else if (isMove1("agility") || isMove1("rock polish") || isMove1("automize")) {
            statChange("speed", 1, 2);
        } else if (isMove1("harden") || isMove1("defense curl")) {
            statChange("defense", 1, 1);
        } else if (isMove1("iron defense") || isMove1("acid armor")) {
            statChange("defense", 1, 2);
        } else if (isMove1("amnesia")) {
            statChange("spdefense", 1, 2);
        } else if (isMove1("glare")) {
            activePokemon2.getStatused("paralyze");
        } else if (isMove1("recover") || isMove1("soft-boiled") || isMove1("moonlight") || isMove1("slack off") ||
                isMove1("roost") || isMove1("synthesis") || isMove1("milk drink") || isMove1("morning sun") || isMove1("rest")) {
            healer(1);
        } else if (isMove1("roar") || isMove1("whirlwind")) {
            randomSwitch(2);
        } else if (isMove1("leech seed")) {
            activePokemon2.seeded = true;
        } else if (isMove1("heal bell") || isMove1("aromatherapy")) {
            activePokemon1.ridVolStatus();
            for (int i = 0; i < 6; i++) {
                p1Pokemon[i].ridVolStatus();
            }
        } else if (isMove1("poison powder")) {
            activePokemon2.getStatused("poison");
        } else if (isMove1("sleep powder") || isMove1("hypnosis") || isMove1("lovely kiss") || isMove1("spore")) {
            activePokemon2.getStatused("asleep");
        } else if (isMove1("stun spore") || isMove1("thunder wave")) {
            activePokemon2.getStatused("paralyze");
        } else if (isMove1("toxic")) {
            activePokemon2.getStatused("badlyPoison");
        } else if (isMove1("glare")) {
            activePokemon2.getStatused("paralyze");
        } else if (isMove1("confuse ray") || isMove1("sweet kiss")) {
            activePokemon2.confused = true;
        } else if (isMove1("barrier")) {
            statChange("defense", 1, 2);
        } else if (isMove1("shell smash")) {
            statChange("attack", 1, 2);
            statChange("spattack", 1, 2);
            statChange("speed", 1, 2);
            statChange("defense", 1, -1);
            statChange("spdefense", 1, -1);
        } else if (isMove1("reflect")) {
            if (p1Ref) {
                usedMove1.fail = true;
            } else {
                p1Ref = true;

                if (activePokemon1.item.equals("light clay")) {
                    p1RefTurnsLeft = 8;
                } else {
                    p1RefTurnsLeft = 5;
                }
            }
        } else if (isMove1("light screen")) {
            if (p1LS) {
                usedMove1.fail = true;
            } else {
                p1LS = true;

                if (activePokemon1.item.equals("light clay")) {
                    p1LSTurnsLeft = 8;
                } else {
                    p1LSTurnsLeft = 5;
                }
            }
        } else if (isMove1("haze")) {
            activePokemon1.resetPokemonStats();
            activePokemon2.resetPokemonStats();
        } else if (isMove1("focus energy")) {
            activePokemon1.critStage += 2;
        } else if (isMove1("curse")) {
            hurtItself(1);
        } else if (isMove1("cotton spore")) {
            statChange("speed", 2, -2);
        } else if (isMove1("protect")) {
            activePokemon1.protectedd = true;
        } else if (isMove1("belly drum")) {
            hurtItself(1);
        } else if (isMove1("sunny day")) {
            changeWeather(1);
        } else if (isMove1("rain dance")) {
            changeWeather(1);
        } else if (isMove1("sandstorm")) {
            changeWeather(1);
        } else if (isMove1("hail")) {
            changeWeather(1);
        }

        healthAfterHealing1= activePokemon1.hpPercentAsInt();

        if(host)
        {
            //Sending database these numbers
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Health After Healing").setValue(healthAfterHealing1);

        }
    }

    static void p2StatusMove() {
        healthBeforeHealing2= activePokemon2.hpPercentAsInt();
        if (isMove2("swords dance")) {
            statChange("attack", 2, 2);
        } else if (isMove2("dragon dance")) {
            statChange("attack", 2, 1);
            statChange("speed", 2, 1);
        } else if (isMove2("quiver dance")) {
            statChange("spattack", 2, 1);
            statChange("spdefense", 2, 1);
            statChange("speed", 2, 1);
        } else if (isMove2("coil")) {
            statChange("attack", 2, 1);
            statChange("defense", 2, 1);
            statChange("accuracy", 2, 1);
        } else if (isMove2("nasty plot")) {
            statChange("spattack", 2, 2);
        } else if (isMove2("calm mind")) {
            statChange("spattack", 2, 1);
            statChange("spdefense", 2, 1);
        } else if (isMove2("cosmic power")) {
            statChange("defense", 2, 1);
            statChange("spdefense", 2, 1);
        } else if (isMove2("bulk up")) {
            statChange("defense", 2, 1);
            statChange("attack", 2, 1);
        } else if (isMove2("string shot")) {
            statChange("speed", 2, -1);
        } else if (isMove2("cotton guard")) {
            statChange("defense", 2, 3);
        } else if (isMove2("meditate")) {
            statChange("attack", 2, 1);
        } else if (isMove2("agility") || isMove2("rock polish") || isMove2("automize")) {
            statChange("speed", 2, 2);
        } else if (isMove2("harden") || isMove2("defense curl")) {
            statChange("defense", 2, 1);
        } else if (isMove2("iron defense") || isMove2("acid armor")) {
            statChange("defense", 2, 2);
        } else if (isMove2("amnesia")) {
            statChange("spdefense", 2, 2);
        } else if (isMove2("glare")) {
            activePokemon1.getStatused("paralyzed");
        } else if (isMove2("recover") || isMove2("soft-boiled") || isMove2("moonlight") || isMove2("slack off") ||
                isMove2("roost") || isMove2("synthesis") || isMove2("milk drink") || isMove2("morning sun") || isMove2("rest")) {
            healer(2);
        } else if (isMove2("roar") || isMove2("whirlwind")) {
            randomSwitch(2);
        } else if (isMove2("leech seed")) {
            activePokemon1.seeded = true;
        } else if (isMove2("poison powder")) {
            activePokemon1.getStatused("poison");
        } else if (isMove2("sleep powder") || isMove2("hypnosis") || isMove2("lovely kiss") || isMove2("spore")) {
            activePokemon1.getStatused("asleep");
        } else if (isMove2("stun spore") || isMove2("thunder wave")) {
            activePokemon1.getStatused("paralyze");
        } else if (isMove2("toxic")) {
            activePokemon1.getStatused("badlyPoison");
        } else if (isMove2("heal bell") || isMove2("aromatherapy")) {
            activePokemon2.ridVolStatus();
            for (int i = 0; i < 6; i++) {
                p2Pokemon[i].ridVolStatus();
            }
        } else if (isMove2("glare")) {
            activePokemon1.getStatused("paralyze");
        } else if (isMove2("confuse ray") || isMove2("sweet kiss")) {
            activePokemon1.confused = true;
        } else if (isMove2("barrier")) {
            statChange("defense", 2, 2);
        } else if (isMove2("shell smash")) {
            statChange("attack", 2, 2);
            statChange("spattack", 2, 2);
            statChange("speed", 2, 2);
            statChange("defense", 2, -1);
            statChange("spdefense", 2, -1);
        } else if (isMove2("reflect")) {
            if (p2Ref) {
                usedMove2.fail = true;
            } else {
                p2Ref = true;

                if (activePokemon2.item.equals("light clay")) {
                    p2RefTurnsLeft = 8;
                } else {
                    p2RefTurnsLeft = 5;
                }
            }
        } else if (isMove2("light screen")) {
            if (p2LS) {
                usedMove2.fail = true;
            } else {
                p2LS = true;

                if (activePokemon2.item.equals("light clay")) {
                    p2LSTurnsLeft = 8;
                } else {
                    p2LSTurnsLeft = 5;
                }
            }
        } else if (isMove2("haze")) {
            activePokemon1.resetPokemonStats();
            activePokemon2.resetPokemonStats();
        } else if (isMove2("focus energy")) {
            activePokemon2.critStage += 2;
        } else if (isMove2("curse")) {
            hurtItself(2);
        } else if (isMove2("cotton spore")) {
            statChange("speed", 1, -2);
        } else if (isMove2("protect")) {
            activePokemon2.protectedd = true;
        } else if (isMove2("belly drum")) {
            hurtItself(2);
        } else if (isMove2("sunny day")) {
            changeWeather(2);
        } else if (isMove2("rain dance")) {
            changeWeather(2);
        } else if (isMove2("sandstorm")) {
            changeWeather(2);
        } else if (isMove2("hail")) {
            changeWeather(2);
        }

        healthAfterHealing2= activePokemon2.hpPercentAsInt();

        if(host)
        {
            //Sending database these numbers
            //add Firebase Database stuff
            FirebaseDatabase mFirebaseDatabase;
            DatabaseReference myRef;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Health After Healing").setValue(healthAfterHealing2);

        }
    }

    public static void hurtItself(int player) {
        if (player == 1) {
            if (isMove1("curse")) {
                if (activePokemon1.type1 == 14 || activePokemon1.type2 == 14) {
                    activePokemon1.hp -= (activePokemon1.origHP / 4);
                    activePokemon2.cursed = true;
                } else {
                    statChange("attack", 1, 1);
                    statChange("defense", 1, 1);
                    statChange("speed", 1, -1);
                }
            }

            if (isMove1("belly drum")) {
                if (activePokemon1.hp < (activePokemon1.origHP / 2)) {
                    usedMove1.fail = true;
                } else {
                    activePokemon1.hp -= (activePokemon1.origHP / 2);
                    statChange("attack", 1, 6);
                }
            }

            if (activePokemon1.hp < 0) {
                faint(1);
            }
        }
        if (player == 2) {
            if (isMove2("curse")) {
                if (activePokemon2.type2 == 14 || activePokemon2.type2 == 14) {
                    activePokemon2.hp -= (activePokemon2.origHP / 4);
                    activePokemon2.cursed = true;
                } else {
                    statChange("attack", 2, 1);
                    statChange("defense", 2, 1);
                    statChange("speed", 2, -1);
                }
            }

            if (isMove2("belly drum")) {
                if (activePokemon2.hp < (activePokemon2.origHP / 2)) {
                    usedMove2.fail = true;
                } else {
                    activePokemon2.hp -= (activePokemon2.origHP / 2);
                    statChange("attack", 2, 6);
                }
            }

            if (activePokemon2.hp < 0) {
                faint(2);
            }
        }
    }

    public static void faint(int player) {
        if (player == 1) {
            activePokemon1.hp = 0;
            activePokemon1.fainted = true;
            p1Pokemon[activePokemon1.orderNum].fainted = true;
        } else if (player == 2) {
            activePokemon2.hp = 0;
            activePokemon2.fainted = true;
            p2Pokemon[activePokemon2.orderNum].fainted = true;
        }
    }

    static void healer(int player) {
        if (player == 1) {
            player1Healed =true;
            if (isMove1("recover") || isMove1("soft-boiled") || isMove1("slack off") || isMove1("milk drink")) {
                activePokemon1.hp += (activePokemon1.origHP / 2);
            } else if (isMove1("moonlight") || isMove1("synthesis") || isMove1("morning sun")) {
                switch (weather) {
                    case 0:
                        activePokemon1.hp += (activePokemon1.origHP / 2);
                        break;
                    case 1:
                        activePokemon1.hp += ((2 * activePokemon1.origHP) / 3);
                        break;
                    default:
                        activePokemon1.hp += (activePokemon1.origHP / 4);
                        break;
                }
            } else if (isMove1("roost")) {
                activePokemon1.hp += (activePokemon1.origHP / 2);
                activePokemon1.roosted = true;
            } else if (isMove1("rest")) {
                if (activePokemon1.hp == activePokemon1.origHP) {
                    usedMove1.fail = true;
                } else {
                    activePokemon1.hp = activePokemon1.origHP;
                    activePokemon1.ridVolStatus();
                    activePokemon1.getStatused("asleep");
                }

            }

            if (activePokemon1.hp > activePokemon1.origHP) {
                activePokemon1.hp = activePokemon1.origHP;
            }

        } else if (player == 2) {
            player2Healed =true;
            if (isMove2("recover") || isMove2("soft-boiled") || isMove2("slack off") || isMove2("milk drink")) {
                activePokemon2.hp += (activePokemon2.origHP / 2);
            } else if (isMove2("moonlight") || isMove2("synthesis") || isMove2("morning sun")) {
                switch (weather) {
                    case 0:
                        activePokemon2.hp += (activePokemon2.origHP / 2);
                        break;
                    case 2:
                        activePokemon2.hp += ((2 * activePokemon2.origHP) / 3);
                        break;
                    default:
                        activePokemon2.hp += (activePokemon2.origHP / 4);
                        break;
                }
            } else if (isMove2("roost")) {
                activePokemon2.hp += (activePokemon2.origHP / 2);
                activePokemon2.roosted = true;
            } else if (isMove2("rest")) {
                if (activePokemon2.hp == activePokemon2.origHP) {
                    usedMove2.fail = true;
                } else {
                    activePokemon2.hp = activePokemon2.origHP;
                    activePokemon2.ridVolStatus();
                    activePokemon2.getStatused("asleep");
                }

            }
            if (activePokemon2.hp > activePokemon2.origHP) {
                activePokemon2.hp = activePokemon2.origHP;
            }
        }

        p1Pokemon[activePokemon1.orderNum].hp = activePokemon1.hp;
        p2Pokemon[activePokemon2.orderNum].hp = activePokemon2.hp;

    }

    public void win(boolean winner) {
        String str = "Congrats, ";

        if (winner) {
            str += name1 + "!";
        } else {
            str += name2 + "!";
        }

        str += " You have won! Wanna play another?";


    }

    //Function for grouping all the data into a string
    public static void makeMovePhrase(String a) {
        movePhrase += a + "\n";
    }

    public static void makePokemonPhrase(String a) {
        pokemonPhrase += a + "\n";
    }

    //Easy way to print all the data
    public static String movePhrase = "";
    public static String pokemonPhrase = "";

    public static boolean cantMoveCuzStatus1() {
        String condition = activePokemon1.statusMethod();
        if (condition.equals("paralyze")) {
            if (activePokemon1.cantMoveCuzParalyze()) {
                reason1 = name1 + " couldn't move from paralysis";
                return true;
            }
        } else if (condition.equals("frozen")) {
            if (activePokemon1.cantMoveCuzFrozen()) {
                reason1 = name1 + " is still frozen";
                return true;
            } else {
                reason1 = name1 + " unthawed";
            }
        } else if (condition.equals("asleep")) {
            if (activePokemon1.cantMoveCuzAsleep()) {
                reason1 = name1 + " is still sleeping";
                return true;
            } else {
                reason1 = name1 + " woke up";
            }
        }

        return false;
    }

    public static boolean cantMoveCuzStatus2() {
        String condition = activePokemon2.statusMethod();

        if (condition.equals("paralyze")) {
            if (activePokemon2.cantMoveCuzParalyze()) {
                reason2 = name2 + " couldn't move from paralysis";
                return true;
            }
        } else if (condition.equals("frozen")) {
            if (activePokemon2.cantMoveCuzFrozen()) {
                reason2 = name2 + " is still frozen";
                return true;
            } else {
                reason2 = name2 + " unthawed";
            }
        } else if (condition.equals("asleep")) {
            if (activePokemon2.cantMoveCuzAsleep()) {
                reason2 = name2 + " is still sleeping";
                return true;
            } else {
                reason2 = name2 + " woke up";
            }
        }

        return false;
    }

    public static boolean cantMoveCuzStatus1HostVersion() {
        String condition = activePokemon1.statusMethod();

        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        if (condition.equals("paralyze")) {
            if (activePokemon1.cantMoveCuzParalyze()) {
                reason1 = name1 + " couldn't move from paralysis";
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Reason to not move").setValue(reason1);
                return true;
            }
        } else if (condition.equals("frozen")) {
            if (activePokemon1.cantMoveCuzFrozen()) {
                reason1 = name1 + " is still frozen";
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Reason to not move").setValue(reason1);
                return true;
            } else {
                reason1 = name1 + " unthawed";
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Reason to not move").setValue(reason1);
            }
        } else if (condition.equals("asleep")) {
            if (activePokemon1.cantMoveCuzAsleep()) {
                reason1 = name1 + " is still sleeping";
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Reason to not move").setValue(reason1);
                return true;
            } else {
                reason1 = name1 + " woke up";
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Reason to not move").setValue(reason1);
            }
        }

        return false;
    }

    public static boolean cantMoveCuzStatus2HostVersion() {
        String condition = activePokemon2.statusMethod();
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        if (condition.equals("paralyze")) {
            if (activePokemon2.cantMoveCuzParalyze()) {
                reason2 = name2 + " couldn't move from paralysis";
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Reason to not move").setValue(reason2);
                return true;
            }
        } else if (condition.equals("frozen")) {
            if (activePokemon2.cantMoveCuzFrozen()) {
                reason2 = name2 + " is still frozen";
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Reason to not move").setValue(reason2);

                return true;
            } else {
                reason2 = name2 + " unthawed";
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Reason to not move").setValue(reason2);
            }
        } else if (condition.equals("asleep")) {
            if (activePokemon2.cantMoveCuzAsleep()) {
                reason2 = name2 + " is still sleeping";
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Reason to not move").setValue(reason2);

                return true;
            } else {
                reason2 = name2 + " woke up";
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Reason to not move").setValue(reason2);

            }
        }

        return false;
    }

    public static void p1Attack() {
        if (usedMove1.category == 1 || usedMove1.category == 2) {
            if (p1Hit() || activePokemon1.ensuredHit) {
                if (activePokemon1.flinch) {
                    reason1 = name1 + " Flinched";
                } else {
                    if (!cantMoveCuzStatus1()) {
                        if (!usedMove1.fail) {
                            calcP1Damage();
                            if (p1Damage != 0) {
                                dealDamage1();
                            }
                        } else {
                            reason1 = "Move Failed";
                        }

                    }
                }
            } else {
                reason1 = name1 + "Missed";
            }
        } else {
            if (p1Hit() || activePokemon1.ensuredHit) {
                if (activePokemon1.flinch) {
                    reason1 = name1 + " Flinched";
                } else {
                    if (!cantMoveCuzStatus1()) {
                        if (!usedMove1.fail) {
                            p1StatusMove();
                        } else {
                            reason1 = "Move Failed";
                        }

                    }
                }
            } else {
                reason1 = "P1 missed their " + usedMove1.name;
            }

        }
    }

    public static void p2Attack() {
        if (usedMove2.category == 1 || usedMove2.category == 2) {
            if (p2Hit() || activePokemon2.ensuredHit) {
                if (activePokemon2.flinch) {

                    reason2 = name2 + " Flinched";
                } else {
                    if (!cantMoveCuzStatus2()) {
                        if (!usedMove2.fail) {
                            calcP2Damage();
                            if (p2Damage != 0) {
                                dealDamage2();
                            }
                        } else {
                            reason2 = "Move Failed";
                        }
                    }
                }
            } else {
                reason2 = name2 + "Missed";
            }
        } else {
            if (p2Hit() || activePokemon2.ensuredHit) {
                if (activePokemon2.flinch) {
                    reason2 = name2 + " Flinched";
                } else {
                    if (!cantMoveCuzStatus2()) {
                        if (!usedMove2.fail) {
                            p2StatusMove();
                        } else {
                            reason2 = "Move Failed";
                        }
                    }
                }
            } else {
                reason2 = name2 + " missed";
            }

        }
    }

    public static void p1AttackHostVersion() {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        if (usedMove1.category == 1 || usedMove1.category == 2) {
            if (p1HitHost() || activePokemon1.ensuredHit) {
                if (activePokemon1.flinch) {
                    myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Flinch").setValue("true");
                    reason1 = name1 + " Flinched";
                } else {
                    if (!cantMoveCuzStatus1HostVersion()) {
                        if (!usedMove1.fail) {
                            calcP1DamageHost();
                            if (p1Damage != 0) {
                                dealDamage1();
                            }
                        } else {
                            reason1 = "Move Failed";
                            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Failed").setValue("true");
                        }

                    }
                }
            } else {
                reason1 = name1 + "Missed";
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Missed").setValue("true");
            }
        } else {
            if (p1HitHost() || activePokemon1.ensuredHit) {
                if (activePokemon1.flinch) {
                    reason1 = name1 + " Flinched";
                    myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Flinch").setValue("true");
                } else {
                    if (!cantMoveCuzStatus1HostVersion()) {
                        if (!usedMove1.fail) {
                            p1StatusMove();
                        } else {
                            reason1 = "Move Failed";
                            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Failed").setValue("true");
                        }

                    }
                }
            } else {
                reason1 = "P1 missed their " + usedMove1.name;
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Missed").setValue("true");
            }

        }
    }

    public static void p1AttackClientVersion() {

        if (usedMove1.category == 1 || usedMove1.category == 2) {
            if (clientHit1 || activePokemon1.ensuredHit) {
                if (activePokemon1.flinch) {
                    reason1 = name1 + " Flinched";
                } else {
                    if (!cantMoveCuzStatus1()) {
                        if (!usedMove1.fail) {
                            move1DirectlyBeforeAttackingEffectsClientVersion();
                            if (p1Damage != 0) {
                                dealDamage1();
                            }
                        } else {
                            reason1 = "Move Failed";
                        }

                    }
                }
            } else {
                reason1 = name1 + "Missed";
            }
        } else {
            if (clientHit1 || activePokemon1.ensuredHit) {
                if (activePokemon1.flinch) {
                    reason1 = name1 + " Flinched";
                } else {
                    if (!cantMoveCuzStatus1()) {
                        if (!usedMove1.fail) {
                            p1StatusMove();
                        } else {
                            reason1 = "Move Failed";
                        }

                    }
                }
            } else {
                reason1 = "P1 missed their " + usedMove1.name;
            }

        }
    }

    public static void p2AttackClientVersion() {

        if (usedMove2.category == 1 || usedMove2.category == 2) {
            if (clientHit2 || activePokemon2.ensuredHit)
            {
                if (activePokemon2.flinch)
                {
                    reason2 = name2 + " Flinched";
                } else
                    {
                    if (!cantMoveCuzStatus2()) {
                        if (!usedMove2.fail) {
                            if (p2Damage != 0) {
                                move2DirectlyBeforeAttackingEffectsClientVersion();
                                dealDamage2();
                            }
                        } else {
                            reason2 = "Move Failed";
                        }
                    }
                }
            } else {
                reason2 = name2 + "Missed";
            }
        } else {
            if (clientHit2 || activePokemon2.ensuredHit) {
                if (activePokemon2.flinch) {
                    reason2 = name2 + " Flinched";
                } else {
                    if (!cantMoveCuzStatus2()) {
                        if (!usedMove2.fail) {
                            p2StatusMove();
                        } else {
                            reason2 = "Move Failed";
                        }
                    }
                }
            } else {
                reason2 = name2 + " missed";
            }

        }
    }

    public static void p2AttackHostVersion() {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        if (usedMove2.category == 1 || usedMove2.category == 2) {
            if (p2HitHost() || activePokemon2.ensuredHit) {
                if (activePokemon2.flinch) {

                    //Tell Database that the other player flinched
                    myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Flinch").setValue("true");

                    reason2 = name2 + " Flinched";
                } else {
                    if (!cantMoveCuzStatus2HostVersion()) {
                        if (!usedMove2.fail) {
                            calcP2DamageHostVersion();
                            if (p2Damage != 0) {
                                dealDamage2();
                            }
                        } else {
                            //Tell Database that the other player's move failed
                            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("fail").setValue("true");
                            reason2 = "Move Failed";
                        }
                    }
                }
            } else {
                //Tell Database that the other player flinched
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Missed").setValue("true");
                reason2 = name2 + "Missed";
            }
        } else {
            if (p2HitHost() || activePokemon2.ensuredHit) {
                if (activePokemon2.flinch) {
                    //Tell Database that the other player flinched
                    myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Flinch").setValue("true");
                    reason2 = name2 + " Flinched";
                } else {
                    if (!cantMoveCuzStatus2HostVersion()) {
                        if (!usedMove2.fail) {
                            p2StatusMove();
                        } else {
                            //Tell Database that the other player flinched
                            myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Failed").setValue("true");
                            reason2 = "Move Failed";
                        }
                    }
                }
            } else {
                //Tell Database that the other player flinched
                myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Missed").setValue("true");
                reason2 = name2 + " missed";
            }

        }
    }

    public static void possibleP1MoveFails() {
        if (isMove1("sucker punch")) {
            if (!((usedMove2.category == 1 || usedMove2.category == 2) && p2Damage == 0)) {
                usedMove1.fail = true;
                reason1 = "fail";
            } else {
                if (p2Switch) {
                    usedMove1.fail = true;
                    reason1 = "fail";
                }
            }
        }
        if (activePokemon1.recharging) {
            usedMove1.fail = true;
            activePokemon1.recharging = false;
        }
        if (isMove1("fake out")) {
            if (!activePokemon1.firstAttack) {
                usedMove1.fail = true;
            }
        }
    }

    public static void possibleP2MoveFails() {
        if (isMove2("sucker punch")) {
            if (!((usedMove1.category == 1 || usedMove1.category == 2) && p1Damage == 0)) {
                usedMove2.fail = true;
                reason2 = "fail";
            } else {
                if (p1Switch) {
                    usedMove2.fail = true;
                    reason2 = "fail";
                }
            }
        }
        if (activePokemon2.recharging) {
            usedMove2.fail = true;
            activePokemon2.recharging = false;
        }
        if (isMove2("fake out")) {
            if (!activePokemon2.firstAttack) {
                usedMove2.fail = true;
            }
        }
    }

    public static void doAlmostEverythingClientVersion() {
        p1Effectiveness = (getEffectiveness(usedMove1, activePokemon2, 1) * getEffectiveness(usedMove1, activePokemon2, 2));
        p2Effectiveness = (Globals.getEffectiveness(usedMove2, activePokemon1, 1) * Globals.getEffectiveness(usedMove2, activePokemon1, 2));

        if (activePokemon1.firstAttack == true) {
            activePokemon1.firstAttack = false;
        }
        if (p1Switch) {
            activePokemon1.firstAttack = true;
        }
        if (activePokemon2.firstAttack == true) {
            activePokemon2.firstAttack = false;
        }
        if (p2Switch) {
            activePokemon2.firstAttack = true;
        }

        //If both Switched
        if ((p1Switch) && (p2Switch)) {
            endTurnEffects();
        } else {
            //Just p1 Switched
            if (p1Switch) {
                p2AttackClientVersion();
            }
            //Just p2 Switched
            else if (p2Switch) {
                p1AttackClientVersion();
            }
            //NO ONE SWITCHED
            else {
                //P1 is attacking first
                if (absoluteOrderOfMoves == 1) {
                    p1AttackClientVersion();

                    //If p1 didn't faint p2's pokemon
                    if (!activePokemon2.fainted) {
                        p2AttackClientVersion();
                    }

                }
                //Player 2 is using a move first
                else {
                    p2AttackClientVersion();

                    //If p2 didn't faint p1's pokemon
                    if (!activePokemon1.fainted) {
                        p1AttackClientVersion();
                    }
                }
            }
            endTurnEffects();
        }
    }

    public static void doAlmostEverything() {
        start1 = (int)Math.round(Double.parseDouble(activePokemon1.hpPercent()));
        start2 = (int)Math.round(Double.parseDouble(activePokemon2.hpPercent()));

        reason1 = "";
        reason2 = "";
        activePokemon1.attackReset(1);
        possibleP1MoveFails();

        activePokemon2.attackReset(2);
        possibleP2MoveFails();

        if (activePokemon1.firstAttack == true) {
            activePokemon1.firstAttack = false;
        }
        if (p1Switch) {
            activePokemon1.firstAttack = true;
        }
        if (activePokemon2.firstAttack == true) {
            activePokemon2.firstAttack = false;
        }
        if (p2Switch) {
            activePokemon2.firstAttack = true;
        }

        //If both Switched
        if ((p1Switch) && (p2Switch)) {
            endTurnEffects();
        } else {
            //Just p1 Switched
            if (p1Switch) {
                p2Attack();
            }
            //Just p2 Switched
            else if (p2Switch) {
                p1Attack();
            }
            //NO ONE SWITCHED
            else {
                //P1 is attacking first
                if (orderOfMoves() == 1) {
                    p1Attack();

                    //If p1 didn't faint p2's pokemon
                    if (!activePokemon2.fainted) {
                        p2Attack();
                    }

                }
                //Player 2 is using a move first
                else {
                    p2Attack();

                    //If p2 didn't faint p1's pokemon
                    if (!activePokemon1.fainted) {
                        p1Attack();
                    }
                }
            }
            endTurnEffects();
        }
    }

    public static void weatherEndTurn() {
        if (weather != 0) {
            weatherTurnsLeft--;
        }

        if (weatherTurnsLeft <= 0) {
            weatherTurnsLeft = 0;
            weather = 0;
        }
    }

    public static void sandstormDamage() {
        if (!(activePokemon1.hasType(9) || activePokemon1.hasType(13) || activePokemon1.hasType(17))) {
            activePokemon1.hp -= (activePokemon1.origHP / 16);
        }
        if (!(activePokemon2.hasType(9) || activePokemon2.hasType(13) || activePokemon2.hasType(17))) {
            activePokemon2.hp -= (activePokemon2.origHP / 16);
        }

        if (activePokemon1.hp <= 0) {
            faint(1);
        }
        if (activePokemon2.hp <= 0) {
            faint(2);
        }

    }

    public static void hailDamage() {
        if (!activePokemon1.hasType(6)) {
            activePokemon1.hp -= (activePokemon1.origHP / 16);
        }
        if (!activePokemon2.hasType(6)) {
            activePokemon2.hp -= (activePokemon2.origHP / 16);
        }

        if (activePokemon1.hp <= 0) {
            faint(1);
        }
        if (activePokemon2.hp <= 0) {
            faint(2);
        }
    }

    public static void endTurnEffects() {

        weatherEndTurn();
/*
        if (weather == 3) {
            sandstormDamage();
        } else if (weather == 4) {
            hailDamage();
        }


        if (!activePokemon1.fainted) {
            if (hasItem(1, "leftovers")) {
                activePokemon1.healPercentage(6);
            }
        }

        if (!activePokemon2.fainted) {
            if (hasItem(2, "leftovers")) {
                activePokemon2.healPercentage(6);
            }
        }

        if (!activePokemon1.fainted) {
            if (activePokemon1.seeded) {
                int amount = 0;
                if (activePokemon1.hp < (activePokemon1.fractionHP(8))) {
                    amount = activePokemon1.hp;
                    faint(1);
                } else {
                    amount = activePokemon1.fractionHP(8);
                }
                activePokemon2.heal(amount);
            }
        }

        if (!activePokemon2.fainted) {
            if (activePokemon2.seeded) {
                int amount = 0;
                if (activePokemon2.hp < (activePokemon2.fractionHP(8))) {
                    amount = activePokemon2.hp;
                    faint(2);
                } else {
                    amount = activePokemon2.fractionHP(8);
                }
                activePokemon1.heal(amount);
            }
        }
        if (!activePokemon1.fainted) {
            activePokemon1.statusDamage();
        }

        if (activePokemon1.hp <= 0) {
            faint(1);
        }

        if (!activePokemon2.fainted) {
            activePokemon2.statusDamage();
        }

        if (activePokemon2.hp <= 0) {
            faint(2);
        }
        */

        //wish
        /*
        5.1 Shed Skin, Hydration, Healer
        5.2 Leftovers, Black Sludge

        6.0 Aqua Ring

        7.0 Ingrain

        8.0 Leech Seed

        9.0 (bad) poison damage, burn damage, Poison Heal
        9.1 Nightmare

        10.0 Curse (from a Ghost-type)

        11.0 Bind, Wrap, Fire Spin, Clamp, Whirlpool, Sand Tomb, Magma Storm

        12.0 Taunt ends

        13.0 Encore ends

        14.0 Disable ends, Cursed Body ends

        15.0 Magnet Rise ends

        16.0 Telekinesis ends

        17.0 Heal Block ends

        18.0 Embargo ends

        19.0 Yawn

        20.0 Perish Song

        21.0 Reflect ends
        21.1 Light Screen ends
        21.2 Safeguard ends
        21.3 Mist ends
        21.4 Tailwind ends
        21.5 Lucky Chant ends
        21.6 Water Pledge + Fire Pledge ends, Fire Pledge + Grass Pledge ends, Grass Pledge + Water Pledge ends

        22.0 Gravity ends

        23.0 Trick Room ends

        24.0 Wonder Room ends

        25.0 Magic Room ends

        26.0 Uproar message
        26.1 Speed Boost, Bad Dreams, Harvest, Moody
        26.2 Toxic Orb activation, Flame Orb activation, Sticky Barb

        27.0 Zen Mode

        28.0 Pokémon is switched in (if previous Pokémon fainted)
        28.1 Healing Wish, Lunar Dance
        28.2 Spikes, Toxic Spikes, Stealth Rock (hurt in the order they are first used)

        29.0 Slow Start

        40.0 Roost*/

    }

    public static boolean hasAbility(int player, String abil) {
        if (player == 1) {
            if (activePokemon1.ability.equals(abil)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (activePokemon2.ability.equals(abil)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean hasItem(int player, String item) {
        if (player == 1) {
            if (activePokemon1.item.equals(item)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (activePokemon2.item.equals(item)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void CPUStuff() {
            p2Switch = false;
            usedMove2 = chooseMove();
            doAlmostEverything();
    }

    public static void CPUSwitch()
    {
        boolean chosen = false;
        for(int i=0; i<6; i++)
        {
            if(!p2Pokemon[i].fainted && CPUHasEffectiveMove(p2Pokemon[i]) && (activePokemon2.dexNumber!= p2Pokemon[i].dexNumber))
            {
                p2Pokemon[i].resetPokemon();
                activePokemon2 = p2Pokemon[i];
                activePokemon2.fainted=false;
                chosen =true;
            }
        }
        if(!chosen)
        {
            for(int i=0; i<6; i++)
            {
                if(!p2Pokemon[i].fainted && (activePokemon2.dexNumber!= p2Pokemon[i].dexNumber))
                {
                    p2Pokemon[i].resetPokemon();
                    activePokemon2 = p2Pokemon[i];
                    activePokemon2.fainted=false;
                    chosen =true;
                }
            }
        }
        if(!chosen)
        {
            reason1 = "p2 is out of usable pokemon";
        }
    }

    public static boolean CPUHasEffectiveMove(AbstractBiomon p) {
        double a = getEffectiveness(p.move1, p, 1) * getEffectiveness(p.move1, p, 2);
        if(p.move1.category == 3)
        {
            a=0;
        }
        double b = getEffectiveness(p.move2, p, 1) * getEffectiveness(p.move2, p, 2);

        if(p.move2.category == 3)
        {
            b=0;
        }
        double c = getEffectiveness(p.move3, p, 1) * getEffectiveness(p.move3, p, 2);
        if(p.move3.category == 3)
        {
            c=0;
        }
        double d = getEffectiveness(p.move4, p, 1) * getEffectiveness(p.move4, p, 2);
        if(p.move4.category == 3)
        {
            d=0;
        }
        if (a >= 2 || b >= 2 || c >= 2 || d >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static AbstractMove chooseMove()
    {
        double arr[] = new double[4];
         arr[0] = getEffectiveness(activePokemon2.move1, activePokemon2, 1) * getEffectiveness(activePokemon2.move1, activePokemon2, 2);
        if(activePokemon2.move1.category == 3)
        {
            arr[0] = 0;
        }
         arr[1] = getEffectiveness(activePokemon2.move2, activePokemon2, 1) * getEffectiveness(activePokemon2.move2, activePokemon2, 2);
        if(activePokemon2.move2.category == 3)
        {
            arr[1] = 0;
        }
        arr[2] = getEffectiveness(activePokemon2.move3, activePokemon2, 1) * getEffectiveness(activePokemon2.move3, activePokemon2, 2);
        if(activePokemon2.move3.category == 3)
        {
            arr[2] = 0;
        }
        arr[3] = getEffectiveness(activePokemon2.move4, activePokemon2, 1) * getEffectiveness(activePokemon2.move4, activePokemon2, 2);
        if(activePokemon2.move4.category == 3)
        {
            arr[3] = 0;
        }

        double max=0.0;
        for(int i=0; i<4; i++)
        {
            if(arr[i] > max)
            {
                max = arr[i];
            }
        }

        int num=0;

        for(int i=0; i<4; i++)
        {
            if(arr[i] == max)
            {
                num =i;
            }
        }

        switch(num)
        {
            case 0:
                return activePokemon2.move1;
            case 1:
                return activePokemon2.move2;
            case 2:
                return activePokemon2.move3;
            case 3:
                return activePokemon2.move4;
            default:
                return activePokemon2.move1;
        }

    }

    public static HashSet numsNoDuplicates(int arraySize, int max)
    {
        Random rand = new Random();
        Set<Integer> numbers = new HashSet<Integer>();
        int n;
        while(numbers.size()<arraySize){
            n= rand.nextInt(max);
            numbers.add(n);
        }
        return (HashSet) numbers;
    }

    //Resets the battle info numbers
   public static void initializeDatabaseForAlmostEverything()
    {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        //Initialize database
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Damage").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Crit").setValue("false");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Hit").setValue("true");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Flinch").setValue("false");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Damage").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Start").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Health After Healing").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Health After Hit").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Fail").setValue("false");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Effectiveness").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("attack").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("defense").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("spattack").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("spdefense").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("speed").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("accuracy").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("evasion").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Switching In").setValue("false");

        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Damage").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Crit").setValue("false");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Hit").setValue("true");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Flinch").setValue("false");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Damage").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Start").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Health After Healing").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Health After Hit").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Fail").setValue("false");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Effectiveness").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("attack").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("defense").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("spattack").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("spdefense").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("speed").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("accuracy").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("evasion").setValue("0");
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Switching In").setValue("false");
    }

    //Uploads its results as it goes
    public static void doAlmostEverythingHostVersion()
    {
        //add Firebase Database stuff
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        //Resets the battle info numbers
        initializeDatabaseForAlmostEverything();

        start1 = (int)Math.round(Double.parseDouble(activePokemon1.hpPercent()));
        start2 = (int)Math.round(Double.parseDouble(activePokemon2.hpPercent()));
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(nameKeyOurs).child("Battle Info").child("Start").setValue(start1);
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Users").child(otherPlayersKey).child("Battle Info").child("Start").setValue(start2);

        reason1 = "";
        reason2 = "";
        activePokemon1.attackReset(1);
        possibleP1MoveFails();

        activePokemon2.attackReset(2);
        possibleP2MoveFails();

        if (activePokemon1.firstAttack == true) {
            activePokemon1.firstAttack = false;
        }
        if (p1Switch) {
            activePokemon1.firstAttack = true;
        }
        if (activePokemon2.firstAttack == true) {
            activePokemon2.firstAttack = false;
        }
        if (p2Switch) {
            activePokemon2.firstAttack = true;
        }

        //If both Switched
        if ((p1Switch) && (p2Switch)) {
            endTurnEffects();
        } else {
            //Just p1 Switched
            if (p1Switch) {
                p2AttackHostVersion();
            }
            //Just p2 Switched
            else if (p2Switch) {
                p1AttackHostVersion();
            }
            //NO ONE SWITCHED
            else {
                //P1 is attacking first
                if (orderOfMoves() == 1) {
                    p1AttackHostVersion();

                    //If p1 didn't faint p2's pokemon
                    if (!activePokemon2.fainted) {
                        p2AttackHostVersion();
                    }

                }
                //Player 2 is using a move first
                else {
                    p2AttackHostVersion();

                    //If p2 didn't faint p1's pokemon
                    if (!activePokemon1.fainted) {
                        p1AttackHostVersion();
                    }
                }
            }
            endTurnEffects();
        }

        //tell other player we are done everything so we can carry on
        myRef.child("Games").child(Integer.toString(gameRoomNumGlobal)).child("Ready to Proceed").setValue("true");
    }
}





