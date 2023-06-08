
package GameLogic.Core;

import ECS.GameObject;
import GameLogic.Data_Lists.*;
import IO.Log;
import Linear_Math.*;

/**
 * EET218 - Java Programming
 * Author: Danny Royer
 * Created Mar 29, 2019
 * Lab #
 * Purpose: 
 */
enum Class_Type
{
    WARRIOR,
    ROGUE,
    WIZARD,
    MONSTER
}

public abstract class Creature extends GameObject{
    protected int               maxhp,          // Maximum HP
                                hp,             // HP
                                maxmp,          // Max MP
                                mp,             // MP (Mana points)
                                baseDamage,     // Base damage
                                atk,            // Attack Damage
                                ac,             // Armour Class
                                ac_offset,      // Additional AC
                                level,          // Level
                                roll,           // Roll
                                x, z;			// 2D position as integer
                                
    protected short             exp,            // Experience
                                lvl_cap;        // A variable to check for the
                                                // next level
    
    protected String            name,           // Player name
                                classTypeStr;   // Class Type (string)
    
    protected Class_Type        classType;      // Player's class type
    
    public  Data_List           inventory;      // Player inventory
    
    // Actions to take when player is kill
    public void die(int actions)
    {
        
    }
    
    // Get player attributes
    public int gethp()                  {return hp;}
    public int getmp()                  {return mp;}
    public int getMaxhp()               {return maxhp;}
    public int getMaxmp()               {return maxmp;}
    public int getLevel()               {return level;}
    public int getac()                  {return ac;}
    public int getDamage()              {return (atk + baseDamage);}
    
    public String getCharacterType()    {return classTypeStr;}
    
    // Set player attributes
    public void sethp(int hp)                           {this.hp = hp;}
    public void setmp(int mp)                           {this.mp = mp;}
    public void setMaxhp(int maxhp)                     {this.maxhp = maxhp;}
    public void setMaxmp(int maxmp)                     {this.maxmp = maxmp;}
    public void setLevel(int level)                     {this.level = level;}
    public void setac(int ac)                           {this.ac = ac;}
    
    public void setCharacterType(String classTypeStr)   {this.classTypeStr = classTypeStr;}
    
    // Default constructor
    public Creature()
    {
    	super("");
        this.exp        = 0;
        this.lvl_cap    = 4;
    }
    
    // Named constructor
    public Creature(String name)
    {
    	super(name);
        this.exp        = 0;
        this.lvl_cap    = 4;
    }
    
    // Called when the player reaches a new level
    public boolean checkExp(int xp)
    {
        checkStats();
        this.exp += xp;
        
        // The Exp curve follows the curve of a square root function, so as the
        // player's level increases, so too does the amount of xp needed to
        // level up. "exp" is just the square of the level cap.
        //int     exp_check = (int)Math.floor( Math.sqrt( (double)this.exp ) );
        boolean lvl_up = (this.exp >= this.lvl_cap * this.lvl_cap);
        
        if (lvl_up)
        {
            this.lvl_cap    *= 2;
            this.exp         = 0;
            this.level++;
        }
        
        return lvl_up;
    }
    
    // Boost player damage
    public void boostDamage(int damage)
    {
        // Applies a constant by splitting it up based on the ratio of base damage
        // to atk damage
        double  Ratio        = (double)1 / (this.atk + this.baseDamage),
                baseRatio    = (double)this.atk * Ratio,
                atkRatio     = (double)this.baseDamage * Ratio;
        
        this.baseDamage += 1 + (int)Math.floor(this.baseDamage  * baseRatio);
        this.atk        += 1 + (int)Math.floor(this.atk         * baseRatio);
    }
    
    // Simple clamp function
    int clamp(int x, int l, int u)
    {
        return (x > l) ? ((x < u) ? x : u) : l;
    }
    
    // Heals the player
    public void heal(int dHP, int dMP)
    {
        // check for pesky negative values
        dHP = Math.abs(dHP);
        dMP = Math.abs(dMP);
        
        // Apply these values
        this.hp = clamp(this.hp + dHP, 0, this.maxhp);
        this.mp = clamp(this.mp + dMP, 0, this.maxmp);
    }
    
    // Deal damage to the player
    public void dealDamage(int damage){
        hp = Math.max(0, hp - damage);
    }
    
    // Attack other enemies
    public void attack(Creature opponent){
        // roll a d20 for this character (the attacker)
        // roll a d20 for the opponent
        if (!(this.classType == Class_Type.MONSTER))
            System.out.println(this.name + " Attacks!");
        else
            System.out.println(this.classTypeStr + " Attacks!");
        
        this.roll       = Dice.roll(20);
        opponent.roll   = Dice.roll(20);
        
        // attacker succeeds if their atk + Roll beats oppenent ac + Roll
        // Report results of the attack and damage dealt
        double  diff            =   (this.roll + this.atk) - 
                                    (opponent.roll + opponent.ac),
                criticalStrike  =   (Dice.roll(40) + this.level) * 0.1;
        int     attackResult    =   (int)Math.signum(diff),
                damageDealt     =   this.baseDamage + this.atk + 
                                    (int)criticalStrike;
        
        switch (attackResult)
        {
            case 0:     // Parry  
                System.out.println("Attack parried! No damage dealt!");
                break;
            case 1:     // Attack success
                System.out.format("Attack Success!\n"
                                + "Damage Dealt: %d (+%d critical)\n"
                                + "Monster health: %d\n", 
                                (this.baseDamage + this.atk),
                                (int)criticalStrike,
                                opponent.hp);
                opponent.hp -= damageDealt; 
                break;
            case -1:    // Attack fail
                double punishFactor = ((Dice.roll(100) == 100) ? 1 : 0) * .1 * this.level;
                this.hp -= (int)punishFactor;
                
                System.out.format("Attack failed! (-%d HP dealt)\n", (int)punishFactor);
                break;
        }
    }
    
    // Walk in a given direction
    public void walk(int direction)
    {
        switch (direction)
        {
            case 1: this.position.x = this.position.x - 1.0f;  break;  // Left
            case 2: this.position.y = this.position.y + 1.0f;  break;  // Forward
            case 3: this.position.x = this.position.x + 1.0f;  break;  // Right
        }
    }
    
    // Get distance from world origin
    public double getDistance()
    {
        return this.position.length();
    }
    
    // I was planning on making a more advanced spell system but ran out of time
    // so I just copied and pasted the code from the attack() function and tweaked
    // it such that the attack now uses mana, and no longer incurs a punishment
    // when the attack fails.
    public void useSpellorSkill(Creature opponent)
    {
        // roll a d20 for this character (the attacker)
        // roll a d20 for the opponent
        if (!(this.classType == Class_Type.MONSTER))
            System.out.println(this.name + " Casts a spell!");
        else
            System.out.println(this.classTypeStr + " Casts a spell!");
        
        this.roll       = Dice.roll(20);
        opponent.roll   = Dice.roll(20);
        
        // attacker succeeds if their mp + Roll beats oppenent ac + Roll
        // Report results of the attack and damage dealt
        double  diff            =   (this.roll + this.mp) - 
                                    (opponent.roll + opponent.ac);
        int     attackResult    =   (int)Math.signum(diff),
                damageDealt     =   (this.mp + 1) * 2;
        
        switch (attackResult)
        {
            case 0: case -1:    // Parry  
                System.out.println("Spell failed! No damage dealt!");
                break;
            case 1:     // Attack success
                System.out.format("Spell Success!\n"
                                + "Damage Dealt: %d\n"
                                + "Monster health: %d\n", 
                                damageDealt,
                                opponent.hp);
                opponent.hp -= damageDealt; 
                break;
        }
    }
    
    public int rest(int seconds)
    {
        hp = maxhp;
        mp = maxmp;
        
        return seconds;
    }
    public void checkStats(){
        //TODO: print a formatted message regarding this character
        Log.logfInfo("HP: %d/%d\tMP: %d/%d\nAttack: %d\tArmor: %d\t"
                        + "BaseDamage: %d\nExp:\t%d (%d needed to reach next lvl)\n",
                        this.hp,
                        this.maxhp,
                        this.mp,
                        this.maxmp,
                        this.atk,
                        this.ac,
                        this.baseDamage,
                        this.exp,
                        this.lvl_cap * this.lvl_cap);
    }

    public void checkInventory()
    {
        //OPTIONAL TODO: display and use an item from inventory
        Log.logInfo(inventory.spoolContents());
    }
    public void setName(String name)
    {
        this.name = name;
    }
    
    // Apply additional armour (TO-DO: make into a seperate field)
    public void applyAC(int ac_offset)
    {
        this.ac_offset += ac_offset;
    }
    
    // Get the class type associated with a string
    public void setClassType(String id)
    {
        this.classTypeStr = id;
        
        if      (id.equals("Warrior"))  {   this.classType = Class_Type.WARRIOR;    }
        else if (id.equals("Rogue"))    {   this.classType = Class_Type.ROGUE;      }
        else if (id.equals("Wizard"))   {   this.classType = Class_Type.WIZARD;     }
        else if (id.equals("Monster"))  {   this.classType = Class_Type.MONSTER;    }
    }
    
    // Get the class type as a string
    public String getClassName(Class_Type type)
    {
        String name = "???";
        switch (type)
        {
            case WARRIOR:   name = "Warrior";   return name;
            case ROGUE:     name = "Rogue";     return name;
            case WIZARD:    name = "Wizard";    return name;
            case MONSTER:   name = "Monster";   return name;
        }
        return name;
    }
    
    public boolean checkClassType(Class_Type type)
    {
        return (this.classType == type);
    }
    
    public void setPositionInt() 
    {
    	x = (int)Math.floor(this.position.x);
    	z = (int)Math.floor(this.position.z);
    }
    
    public Vector3f getPositionInt() 
    {
    	return new Vector3f(this.x, this.position.y, this.z);
    }
}
