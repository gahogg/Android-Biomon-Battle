package com.greg.android.biomoon;

import java.util.Random;

import static com.greg.android.biomoon.Globals.usedMove1;
import static com.greg.android.biomoon.Globals.usedMove2;
import static com.greg.android.biomoon.Globals.p2Damage;
import static com.greg.android.biomoon.Globals.p1Damage;

/**
 *
 * @author Greg
 */
abstract public class AbstractBiomon {
    
    //Declare variables to define pokemon characteristics
    protected String name, item, ability, status;
    protected AbstractMove move1, move2, move3, move4;
    protected int dexNumber, type1, type2, hp, attack, defense, spattack, spdefense, speed, totalStats, orderNum, critStage, accuracyStage, attackStage, defenseStage, spattackStage, spdefenseStage, speedStage, evasionStage,maxHP, percentHP;
    protected boolean confused, fainted, seeded, burn, paralyze, frozen, asleep, ensuredHit, flinch, poison, badlyPoison, grounded, roosted, cursed, protectedd, detected;
    protected int origHP, origAttack , origSpattack, origDefense, origSpdefense, origSpeed;
    protected double accuracy =1;
    protected double evasion =1;
    protected int sleepTurn=0;
    protected int badlyPoisonTurn=0;
    protected boolean firstAttack;
    protected boolean lockedIn, recharging, flashFired;
    protected String lockedInStr;
    protected String statusedString=null;
    protected String statusPreviousTurn;
    protected boolean hit;
    
    public AbstractBiomon(String n, int t1, int t2, String a, String i, String stat, AbstractMove m1, AbstractMove m2, AbstractMove m3, AbstractMove m4, int dexNum, int health, int atk, int def, int spatk, int spdef, int spd, boolean conf, boolean fnt, boolean seed) {
        name = n;
        dexNumber = dexNum;
        type1 = t1;
        type2 = t2;
        ability = a;
        item = i;
        status = stat;
        move1 = m1;
        move2 = m2;
        move3 = m3;
        move4 = m4;
        hp = health;
        attack = atk;
        defense = def;
        spattack = spatk;
        spdefense = spdef;
        speed = spd;    
        confused = conf;
        fainted = fnt;
        seeded = seed;
        critStage =0;
        burn = false;
        paralyze = false;
        frozen = false;
        asleep = false;
        ensuredHit = false;
        accuracyStage =0;
        accuracy =1;
        maxHP = hp;
        badlyPoison = false;
        attackStage =0;
        defenseStage = 0;
        spattackStage =0;
        spdefenseStage =0;
        speedStage =0;
        evasionStage=0;
        evasion =1;
        origHP = hp;
        origAttack = atk;
        origSpattack = spatk;
        origDefense = def;
        origSpdefense = spdef;
        origSpeed = spd;
        grounded = false;
        flinch = false;
        roosted =false;
        cursed=false;
        protectedd=false;
        recharging =false;
        firstAttack=true;
        flashFired=false;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public AbstractMove getMove1(){
        return move1;
    }
    
    public void setMove1(AbstractMove m){
        move1 = m;
    }
    
    public AbstractMove getMove2(){
        return move2;
    }
    
    public void setMove2(AbstractMove m){
        move2 = m;
    }
    
    public AbstractMove getMove3(){
        return move3;
    }
    
    public void setMove3(AbstractMove m){
        move3 = m;
    }
    
    public AbstractMove getMove4(){
        return move4;
    }
    
    public void setMove4(AbstractMove m){
        move4 = m;
    }
    
    public int getType1() {
        return type1;
    }
    
    public void setType1(int t1) {
        type1 = t1;
    }
    
    public int getType2() {
        return type2;
    }
    
    public void setType2(int t2) {
        type2 = t2;
    }
    
    public String getAbility() {
        return ability;
    }
    
    public void setAbility(String a) {
        ability = a;
    }
    
    public String getItem() {
        return item;
    }
    
    public void setItem(String i) {
        item = i;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String stat) {
        status = stat;
    }
    
    public int getHP() {
        return hp;
    }

    public void setHP(int health) {
        hp = health;
    }
    
    public int getDexNumber() {
        return dexNumber;
    }

    public void setDexNumber(int dexNum) {
        dexNumber = dexNum;
    }
    
    public int getAttack() {
        return attack;
    }

    public void setAttack(int atk) {
        attack = atk;
    }
    
    public int getDefense() {
        return defense;
    }

    public void setDefense(int def) {
        defense = def;
    }
    
    public int getSpattack() {
        return spattack;
    }

    public void setSpattack(int spatk) {
        spattack = spatk;
    }
    
    public int getSpdefense() {
        return spdefense;
    }

    public void setSpdefense(int spdef) {
        spdefense = spdef;
    }
    
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int spd) {
        speed = spd;
    }
    
    public boolean getConfused() {
        return confused;
    }

    public void setConfused(boolean conf) {
        confused = conf;
    }
    
    public boolean getFainted() {
        return fainted;
    }

    public void setFainted(boolean fnt) {
        fainted = fnt;
    }
    
    public boolean getSeeded() {
        return seeded;
    }

    public void setSeeded(boolean seed) {
        seeded = seed;
    }

    public void ridVolStatus()
    {
        burn=false;
        frozen=false;
        poison=false;
        badlyPoison=false;
        badlyPoisonTurn=0;
        sleepTurn=0;
        paralyze=false;
        asleep=false;
    }
    public boolean isNonVolStatused()
    {
        if(burn || paralyze || asleep || poison || badlyPoison || frozen)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void getStatused(String statusEffect)
    {
        if(!isNonVolStatused())
        {
            if(statusEffect.equals("burn"))
            {
                burn = true;
                statusedString = "was burned!";
            }
            else if(statusEffect.equals("paralyze"))
            {
                paralyze = true;
                statusedString = "was paralyzed!";
                speed /=2;
            }
            else if(statusEffect.equals("frozen"))
            {
                frozen = true;
                statusedString = "was frozen!";
            }
            else if(statusEffect.equals("poison"))
            {
                poison = true;
                statusedString = "was poisoned!";

            }
            if(statusEffect.equals("badlyPoison"))
            {
                badlyPoison = true;
                badlyPoisonTurn=0;
                statusedString = "was badly poisoned!";
            }
            if(statusEffect.equals("asleep"))
            {
                asleep = true;
                statusedString = "fell asleep!";
            }
        }

    }

    public String statusMethod()
    {
        if(burn)
        {
            return "burn";
        }
        else if(paralyze)
        {
            return "paralyze";
        }
        else if(asleep)
        {
            return "asleep";
        }
        else if(frozen)
        {
            return "frozen";
        }
        else if(poison)
        {
            return "poison";
        }
        else if(badlyPoison)
        {
            return "badlyPoison";
        }

        return "";
    }

    public String statusMessage(String status)
    {
        switch(status)
        {
            case "burn":
                return " was burned!";
            case "paralyze":
                return " was paralyzed!";
            case "frozen":
                return " was frozen solid!";
            case "poison":
                return " was poisoned!";
            case "badlyPoison":
                return " was badly poisoned!";
            default:
                return "";
        }
    }

    public double getAttackMod()
    {
        return (double)attack/ (double)origAttack;
    }
    public double getSpattackMod()
    {
        return (double)spattack/(double)origSpattack;
    }
    public double getDefenseMod()
    {
        return (double)defense/(double)origDefense;
    }
    public double getSpdefenseMod()
    {
        return (double)spdefense/(double)origSpdefense;
    }
    public double getSpeedMod()
    {
        return (double)speed/(double)origSpeed;
    }
    public double getHealthMod()
    {
        return (double)hp/(double)origHP;
    }

    public String hpPercent()
    {
        return "" + getHealthMod() *100;
    }
    public int hpPercentAsInt(){return (int)((double)hp/(double)origHP*100);}

    public void resetPokemon()
    {
        attack = origAttack;
        defense = origDefense;
        spattack = origSpattack;
        spdefense = origSpdefense;
        speed = origSpeed;
        accuracy=1;
        evasion=1;
        attackStage = 0;
        defenseStage=0;
        spattackStage =0;
        spdefenseStage=0;
        speedStage=0;
        accuracyStage=0;
        evasionStage=0;
        confused=false;
        badlyPoisonTurn =0;
        recharging=false;
        firstAttack=true;
        flashFired=false;
    }

    public void resetPokemonStats()
    {
        attack = origAttack;
        defense = origDefense;
        spattack = origSpattack;
        spdefense = origSpdefense;
        speed = origSpeed;
        accuracy=1;
        evasion=1;
        attackStage = 0;
        defenseStage=0;
        spattackStage =0;
        spdefenseStage=0;
        speedStage=0;
        accuracyStage=0;
        evasionStage=0;
    }

    public boolean cantMoveCuzParalyze()
    {
        int n;
        Random rand = new Random();
        n = rand.nextInt(100);

        if(n<=25)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean cantMoveCuzFrozen()
    {
        int n;
        Random rand = new Random();
        n = rand.nextInt(100);

        if(n<=20)
        {
            frozen=false;
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean cantMoveCuzAsleep()
    {
        sleepTurn++;
        int n;
        Random rand = new Random();
        n = rand.nextInt(100);

        if(sleepTurn<4)
        {
            if(n<=33)
            {
                asleep=false;
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            asleep=false;
            return false;
        }

    }

    public void attackReset(int player)
    {
        if(player ==1)
        {
            usedMove1.fail = false;
        }
        if(player ==2)
        {
            usedMove2.fail =false;
        }
        p1Damage=0;
        p2Damage=0;
        flinch =false;
        ensuredHit = false;
        roosted =false;
        protectedd=false;
        detected=false;
        hit = true;

    }

    public boolean hasType(int t)
    {
        if(type1 ==t || type2 ==t)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void healPercentage(double percent)
    {
        int healAmount = (int) (Math.round(origHP * (percent/100)));
        hp += healAmount;
        if(hp> origHP)
        {
            hp = origHP;
        }
    }

    public void heal(double amount)
    {
        hp += amount;
        if(hp>origHP)
        {
            hp =origHP;
        }
    }

    public int fractionHP(double denominator)
    {
        int amount = (int) (Math.round(origHP/denominator));
        return amount;
    }

    public void burnDamage()
    {
        hp -= origHP/ 16;
    }
    public void poisonDamage()
    {
        hp -= origHP/ 8;
    }
    public void badPoisonDamage()
    {
        hp -= badlyPoisonTurn * Math.round(origHP/16);
        badlyPoisonTurn++;
    }

    public void statusDamage()
    {
        if(statusMethod().equals("burn"))
        {
            burnDamage();
        }
        if(statusMethod().equals("poison"))
        {
            poisonDamage();
        }
        if(statusMethod().equals("badlyPoison"))
        {
            badPoisonDamage();
        }
    }

    public String cantMoveCuzStatusMessage()
    {
        switch (statusMethod())
        {
            case "paralyze":
                return name + " was unable to move from paralysis!";
            case "frozen":
                return name + " is still frozen!";
            case "asleep":
                return name + " is still sleeping!";
        }
        return "";
    }
}
