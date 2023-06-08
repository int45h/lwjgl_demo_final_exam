/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Data_Lists;

import GameLogic.Murmurhash3.*;
import java.util.*;
/*
Inventory is a list of items (NBT Nodes)
Items have a:
    name            (string), 
    type            (NBT_Short),
    count           (NBT_Short),
    list[           (NBT_List)
        attribute   (NBT_Float/Int/Double/Long/Short)
    ],
    description (NBT_String)
*/
public class Data_List {
    public int                              length,     // Size of inventory
                                            max_length; // Maximum length
    protected HashMap<Long, Data_Node>      contents;   // Contents of inventory
    protected HashMap<Integer, Short>       IDs;        // All IDs in the inventory
    protected ArrayList<Long>               ID_index;
    
    // Update the entry of an ID and keep track of all instances of that ID
    short updateIDs(int ID)
    {
        short offset = 0;
        
        if (!IDs.containsKey(ID)){
            IDs.put(ID, (short)1);
        }
        else
            offset = IDs.put(ID, (short)Math.min(65535, IDs.get(ID) + 1));
        
        return offset;
    }
    
    // Generate the item ID ( 32-bit hash combined with 16-bit counter for handling duplicate IDs )
    public long generateItemID(int seed, String name)
    {
        int     item_hash   = Murmurhash3.hash(seed, name.getBytes());
        long    ID          = ((long)item_hash << 16) + ((long)updateIDs(item_hash));
        
        return ID;
    }
    
    // Update the inventory by putting things into it
    public Data_Node put(String item_ID, Data_Node item)
    {
        long ID = generateItemID(0, item.name);
        contents.put(ID, item);
        ID_index.add(ID);
        
        length++;
        return item;
    }
    
    // Get item by index
    public Data_Node getByIndex(int i)
    {
        long ID = ID_index.get(i);
        return contents.get(ID);
    }
    
    // Empty constructor
    public Data_List()
    {
        // Empty just like my soul
    }
    
    // Constructor with items
    public Data_List(Data_Node[] item)
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
    
    public Data_List(int size)
    {
        // Allocate memory for the hashmap
        this.contents   = new HashMap<Long, Data_Node>();
        this.IDs        = new HashMap<Integer, Short>();
        this.ID_index   = new ArrayList<Long>(0);
        
        // Set the max size of the inventory
        this.max_length = size;
    }
    
    // Add an item to the list ONLY if it doesn't exceed the maximum size
    public boolean addItem(Data_Node item)
    {
        boolean canFit = (length + 1 > max_length);
        
        if(canFit)
            put(item.name, item);
        
        return canFit;
    }
    
    // Remove an item from the list
    public Data_Node remove(int i)
    {
        long    ID      = ID_index.remove(i);
        int     ID_hash = (int)(ID >>> 16);
        
        // If there are duplicates, subtract 1 from the total # of them
        if (IDs.get(ID_hash) > 0)
            IDs.put(ID_hash, (short)(IDs.get(ID_hash) - 1));
        else
            IDs.remove(ID_hash);
        
        length--;
        
        return contents.remove(ID);
    }
    
    // Print inventory contents
    public String spoolContents()
    {
        // Inventory items will look like [ item1, item2, ... ]
        StringBuilder inv_contents = new StringBuilder("[");
    
        // For every ID in the set of keys, add a new item to the string by name
        if (this.length >= 1)
        {
            for (int i = 0; i < this.length; i++)
            {
                inv_contents.append(" ");
                inv_contents.append(this.getByIndex(i).name);
                inv_contents.append(",");
            }
        
            // Replace ending comma with a space, then add the end square brace
            inv_contents.setCharAt(inv_contents.length() - 1, ' ');
        }
        inv_contents.append(']');
        
        // Return the newly built string
        return inv_contents.toString();
    }
}
