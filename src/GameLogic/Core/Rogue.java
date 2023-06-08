
package GameLogic.Core;

import GameLogic.Data_Lists.*;
import Linear_Math.*;

/**
 * EET218 - Java Programming
 * Author: Danny Royer
 * Created Mar 29, 2019
 * Lab #
 * Purpose: 
 */
public class Rogue extends Creature{
    public Rogue(){
        int base                = 5;
        
        this.maxhp = this.hp    = base * 4;
        this.atk                = base;
        this.baseDamage         = (base / 3);
        this.ac                 = base;
        this.maxmp = this.mp    = base * 2;
        this.level              = 1;
        
        this.position           = new Vector3f(0, 0, 0);
        this.inventory          = new Data_List(0);
        
        setClassType("Rogue");
        
        // Give the rogue a lesser health potion
        Data_Node starter = new Data_Node(  Data_Node.getType(0), 
                                            "Lesser health potion",
                                            "A health potion that will grant "
                                          + "20% health back to the user\n");
        // This will heal ~20% of their current health pool
        starter.setInt((int)(this.maxhp / 5), 0);
        // Put it in their inventory
        inventory.put("Lesser health potion", starter);
    }    
    public void die()
    {
        System.out.println("Level " + this.level + " " + this.classTypeStr + " is defeated!");
    }
}
