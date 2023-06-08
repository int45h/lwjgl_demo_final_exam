/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Data_Lists;

import GameLogic.Core.Creature;

public class ItemHandler {
    
    // Check the item type and apply status effects based on it
    public static void checkItem(Creature player, int index)
    {
        Data_Node item = player.inventory.getByIndex(index);
        switch(item.type)
        {
            case HEALTH:    
                player.heal(item.getInt(0), 0);
                player.inventory.remove(index);
                break;
            case MANA:      
                player.heal(0, item.getInt(0));
                player.inventory.remove(index);
                break;
            case ARMOUR:        
                player.applyAC(item.getInt(0));
                break;
        }
    }
    
    // TO-DO: generate a random item around the player
}
