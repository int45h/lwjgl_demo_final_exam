package GameLogic.Data_Lists;

// Item Node 

import GameLogic.Core.Dice;

public class Item_Node extends Data_Node
{
    // Type of item
    public enum ITEM_TYPE
    {
        POTION,
        ARMOUR,
        SWORD
    }
    
    public ITEM_TYPE    item_type;  // Type of item

    // Constructor for a predefined item
    Item_Node(TRAIT_TYPE type, String name, String description)
    {   
        this.type           = type;
        this.name           = name;
        this.description    = description;
    }
    
    // Generate a random item
    public static Item_Node generateItem()
    {
        String[] itemStrings    = new String[]{
            "potion",
            "chestplate",
            "sword"
        };

        String[] itemType       = new String[]{
            "health",
            "mana",
            "flimsy",
            "sturdy"
        };
        
        String[] itemModifier   = new String[]
        {
            "Lesser",
            "Greater"
        };
        
        // Randomly select a item type and attribute
        int     item_string     = Dice.roll(2),
                item_type_ID    = Dice.roll(3),
                item_modifier   = Dice.roll(1);
        
        TRAIT_TYPE item_trait;
        
        StringBuilder itemName = new StringBuilder();
        itemName.append(itemStrings[item_string]);
        
        // Randomly select an item string
        switch (item_string)
        {
            case 0: 
                itemName.insert(0, itemType[item_type_ID & 1] + " ");
                item_trait = (item_type_ID == 0) ? TRAIT_TYPE.HEALTH : TRAIT_TYPE.MANA;
                itemName.insert(0, itemModifier[item_modifier] +  " ");
                break;
            case 1: 
                itemName.insert(0, itemType[(item_type_ID & 1) + 2] + " ");
                item_trait = TRAIT_TYPE.ARMOUR;
                itemName.insert(0, itemModifier[item_modifier] +  " ");
                break;
            case 2: 
                itemName.insert(0, itemType[(item_type_ID & 1) + 2] + " ");
                item_trait = TRAIT_TYPE.ARMOUR;
                break;
        }
        
        Item_Node item = new Item_Node(TRAIT_TYPE.HEALTH, itemName.toString(), "Flavor text");
        
        // Apply a random modifier
        switch (item_modifier)
        {
            case 0: item.setInt(20 + Dice.roll(10), 0); break;
            case 1: item.setInt(50 + Dice.roll(30), 0); break;
        }
        
        return item;
    }
}
