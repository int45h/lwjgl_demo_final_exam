/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*



              --NOTE--
    
    unimplemented, ignore this file





*/

package GameLogic.Core;
import GameLogic.Data_Lists.Data_Node;
import GameLogic.Murmurhash3.Murmurhash3;
import java.util.ArrayList;
import java.util.HashMap;

/*
    Based on the skill tree from Path of Exile

    strength    | dexterity | intelligence
    ------------+-----------+-----------------
    AD,         | ES,       | MP, 
    life        | evasion   | energy shield
*/
class Skill_Node extends Data_Node
{
    public Skill_Node   left,
                        front, 
                        right;
    protected boolean   isUnlocked;
}

/* The actual tree itself */
/*
public class Skill_Tree extends Data_Lists.Data_List
{
    // Constructor with items
    public Skill_Tree(Data_Node[] item)
    {
        // Allocate memory for the hashmap
        this.contents   = new HashMap<Long, Data_Node>();
        this.IDs        = new HashMap<Integer, Short>();
        this.ID_index   = new ArrayList<Long>(0);
                
        // Put all items into the inventory
        for (int i = 0; i < item.length; i++)
            put(item[i].name, item[i]);
        
        // Set some arbitrary maximum length, to be expanded later
        this.max_length = item.length * 2;
    }
    
    public Skill_Tree(int size)
    {
        // Allocate memory for the hashmap
        this.contents   = new HashMap<Long, Data_Node>();
        this.IDs        = new HashMap<Integer, Short>();
        this.ID_index   = new ArrayList<Long>(0);
        
        // Set the max size of the inventory
        this.max_length = size;
    }
}
*/