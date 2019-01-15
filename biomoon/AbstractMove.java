/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greg.android.biomoon;

/**
 *
 * @author Norvil
 */



public class AbstractMove {
    
    protected String name;
    protected int number;
    protected int type;
    protected int category;
    protected int PP;
    protected int power;
    protected int accuracy;
    protected boolean fail = false;
    protected boolean miss = false;
    protected int priority;
    
    public AbstractMove(int m, String n, int t, int cat, int pp, int pow, int acc)
    {
        number = m;
        name =n;
        type = t;
        category = cat;
        PP = pp;
        power = pow;
        accuracy = acc;

        if(name.equals("helping hand") )
        {
            priority = 5;
        }
        else if(name.equals("baneful bunker") || name.equals("detect") || name.equals("endure") || name.equals("king's shield") || name.equals("magic coat") || name.equals("protect") ||
                name.equals("spiky shield") || name.equals("snatch") )
        {
            priority = 4;
        }
        else if(name.equals("crafty shield") || name.equals("fake out") || name.equals("quick guard") || name.equals("wide guard") || name.equals("spotlight"))
        {
            priority =3;
        }
        else if( name.equals("ally switch") || name.equals("extreme speed") || name.equals("feint") || name.equals("first impression")
                || name.equals("follow me") || name.equals("rage powder"))
        {
            priority =2;
        }
        else if(name.equals("accelerock") || name.equals("aqua jet") || name.equals("baby-doll eyes") || name.equals("bide") || name.equals("bullet punch")
            || name.equals("ice shard") || name.equals("ion deluge") || name.equals("mach punch") || name.equals("powder")
            || name.equals("quick attack") || name.equals("shadow sneak") || name.equals("sucker punch") || name.equals("vacuum wave") || name.equals("water shuriken"))
        {
            priority=1;
        }
        else if(name.equals("vital throw"))
        {
            priority=-1;
        }
        else if( name.equals("beak blast") || name.equals("focus punch") || name.equals("shell trap"))
        {
            priority=-3;
        }
        else if( name.equals("avalanche") || name.equals("revenge"))
        {
            priority=-4;
        }
        else if( name.equals("counter") || name.equals("mirror coat"))
        {
            priority=-5;
        }
        else if( name.equals("circle throw") || name.equals("dragon tail") || name.equals("roar") || name.equals("whirlwind"))
        {
            priority=-6;
        }
        else if( name.equals("trick room") )
        {
            priority =-6;
        }
        else
        {
            priority=0;
        }
/*
        Crafty Shield, Fake Out, Quick Guard, Wide Guard, Spotlight
            +2 	Ally Switch, Extreme Speed, Feint, First Impression, Follow Me, Rage Powder
            +1 	Accelerock, Aqua Jet, Baby-Doll Eyes, Bide, Bullet Punch, Ice Shard, Ion Deluge, Mach Punch,
            Powder, Quick Attack, Shadow Sneak, Sucker Punch, Vacuum Wave, Water Shuriken
        0 	All other moves
            -1 	Vital Throw
        -2 	None
            -3 	Beak Blast, Focus Punch, Shell Trap
            -4 	Avalanche, Revenge
            -5 	Counter, Mirror Coat
            -6 	Circle Throw, Dragon Tail, Roar, Whirlwind
            -7 	Trick Room, fleeing*/
    }
    public int getMoveNumber(){return number;}
    public String getName(){
    return name;
}
    public void setName(String n){
        name =n;
    }
    public int getType(){
        return type;
    }
    public void setType(int t){
        type =t;
    }
    public int getCategory(){
        return category;
    }
    public void setCategory(int cat){
        category = cat;
    }
    public int getPP(){
        return PP;
    }
    public void setPP(int p){
        PP = p;
    }
    public int getPower(){
        return power;
    }
    public void setPower(int pow){
        power = pow;
    }
    public int getAccuracy(){
        return accuracy;
    }
    public void setAccuracy(int a){
        accuracy = a;
    }
}
