package com.greg.android.biomoon;
public interface BiomonInterface {
    //return the name of the pokemon
    public String getName();
    //change the name of the pokemon
    public void setName(String n);
    //return the first move
    public AbstractMove getMove1();
    //change the first move
    public void setMove1(AbstractMove m1);
    //return the second move
    public String getMove2();
    //change the second move
    public void setMove2(String m2);
    //return the third move
    public String getMove3();
    //change the third move
    public void setMove3(String m3);
    //return the fourth move
    public String getMove4();
    //change the fourth move
    public void setMove4(String m4);
    //return the first type
    public String getType1();
    //change the first type
    public void setType1(int t1);
    //return the second type
    public String getType2();
    //change the second type
    public void setType2(int t2);
    //return the ability
    public String getAbility();
    //change the ability
    public void setAbility(String a);
    //return the item
    public String getItem();
    //change the item
    public void setItem(String i);
    //return the status
    public String getStatus();
    //change the status
    public void setStatus(String stat);
    //get the dex number
    public int getDexNumber();
    //change the dex number
    public void setDexNumber(int dexNum);
    //get the health points
    public int getHP();
    //change the health points
    public void setHP(int health);
    //get the attack stat
    public int getAttack();
    //change the attack stat
    public void setAttack(int atk);
    //get the defense stat
    public int getDefense();
    //change the defense stat
    public void setDefense(int def);
    //get the special attack stat
    public int getSpattack();
    //change the special attack stat
    public void setSpattack(int spatk);
    //get the special defense stat
    public int getSpdefense();
    //change the special defense stat
    public void setSpdefense(int spdef);
    //get the speed stat
    public int getSpeed();
    //change the speed stat
    public void setSpeed(int spd);
    //get the happiness
    public int getHappiness();
    //change the happiness
    public void setHappiness(int hap);
    //get the boolean to see if the pokemon is confused
    public boolean getConfused();
    //change theboolean to see if the pokemon is confused
    public void setConfused(int conf);
    //get the boolean to see if the pokemon is fainted
    public boolean getFainted();
    //change theboolean to see if the pokemon is fainted
    public void setFainted(int fnt);
    //get the boolean to see if the pokemon is seeded
    public boolean getSeeded();
    //change theboolean to see if the pokemon is seeded
    public void setSeeded(int seed);
}
