package GameLogic.Core;

import GameLogic.Data_Lists.Item_Node;

/**
 * EET218 - Java Programming
 * Author: Danny Royer
 * Created Mar 29, 2019
 * Lab # 7
 * Purpose: the Monster class is a subclass of Hero - though pure evil
 */
public class Monster extends Creature{
    public Item_Node item;
    public Monster(int oppLevel){
        // initialize the stats for the Monster based on level of opponent
        int base                = 5;
        int lvlMultiplier       = oppLevel * 2;
        
        this.maxhp = this.hp    = base + lvlMultiplier;
        this.atk                = base + oppLevel;
        this.baseDamage         = (base / 3) + oppLevel;
        this.ac                 = base + oppLevel;
        this.level              = oppLevel;
        this.classType          = Class_Type.MONSTER;
        this.classTypeStr       = getClassName(this.classType);
    }
    public void die()
    {
        System.out.println("Level " + this.level + " " + this.classTypeStr + " is defeated!");
    }
    
}
