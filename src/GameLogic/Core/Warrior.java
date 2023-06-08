
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
public class Warrior extends Creature {
    void init() {
    	int base                = 5;
        
        this.maxhp = this.hp    = base * 6;
        this.atk                = base;
        this.baseDamage         = (base / 2);
        this.ac                 = base;
        this.level              = 1;
        
        this.position           = new Vector3f(0, 0, 0);
        this.inventory          = new Data_List(8);
        
        setClassType("Warrior");
        
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
    
	public Warrior() {
		init();
    }    
    
    public Warrior(String string) {
    	
    }
	
    public void die()
    {
        System.out.println("Level " + this.level + " " + this.classTypeStr + " is defeated!");
    }
}
