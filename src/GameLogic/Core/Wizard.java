
package GameLogic.Core;

import GameLogic.Data_Lists.Data_List;
import GameLogic.Data_Lists.Data_Node;
import Linear_Math.Vector2f;
import Linear_Math.Vector3f;

/**
 * EET218 - Java Programming
 * Author: Danny Royer
 * Created Mar 29, 2019
 * Lab #
 * Purpose: 
 */
public class Wizard extends Creature{
    public Wizard(){
        int base                = 5;
        
        this.maxhp = this.hp    = base * 3;
        this.atk                = base;
        this.baseDamage         = (base / 2);
        this.ac                 = base;
        this.level              = 1;
        
        this.maxmp = this.mp    = base * 6;
        
        this.position           = new Vector3f(0, 0, 0);
        this.inventory          = new Data_List(8);
        
        setClassType("Wizard");
        
        // Give the warrior a lesser health potion
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
