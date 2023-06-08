/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Data_Lists;

enum TRAIT_TYPE
{
    HEALTH,
    MANA,
    ARMOUR,
    WEAPON,
    NULL
}

/* Items are treated as a node in a tree (inventory, chests, etc) */

    /*
    Items:      Collectables that can add perks to your hero
    
    items: [Descriptor][ID|Byte Array|Description(UTF8 string)]
    
    Contents:   Name (string), 
                ID (hashed string + index), 
                pointer to next block
*/
public class Data_Node {
    protected int       itemCount,  // Item count
                        ID;         // Hashed name
    protected short     tag;        // Tag ID, from https://minecraft.fandom.com/wiki/NBT_format
    protected String    name,       // Name of the object
                        description;// Description
    protected byte[]    contents;   // The raw contents within
    public TRAIT_TYPE   type;       // Type of trait
    
    // Get trait type by index
    public static TRAIT_TYPE getType(int id)
    {
        TRAIT_TYPE type = TRAIT_TYPE.NULL;
        switch (id)
        {
            case 0:     type = TRAIT_TYPE.HEALTH;   break;
            case 1:     type = TRAIT_TYPE.MANA;     break;
            case 2:     type = TRAIT_TYPE.ARMOUR;   break;
            case 3:     type = TRAIT_TYPE.WEAPON;   break;
        }
        return type;
    }
    
    // Get the name
    public String getName()
    {
        return this.name;
    }
    
    // Constructor for a predefined item
    public Data_Node(TRAIT_TYPE type, String name, String description)
    {   
        this.type           = type;
        this.name           = name;
        this.description    = description;
    }
    
    // Empty constructor
    public Data_Node()
    {
        // Empty just like my soul
    }
    
    // Get byte from item type enum
    public byte getTypeByte()
    {
        byte bType = 0x00;
        switch (type)
        {
            case HEALTH:        bType = 0x01;   break;
            case MANA:          bType = 0x02;   break;
            case ARMOUR:        bType = 0x03;   break;
        }
        return bType;
    }
    
    public float getFloat()
    {
        int bFloat =    (   (contents[3] << 24) | 
                            (contents[2] << 16) | 
                            (contents[1] << 8)  | 
                            (contents[0]));
        
        return Float.intBitsToFloat(bFloat);
    }
    
    public double getDouble()
    {
        long bDouble =   (  (contents[7] << 56) | 
                            (contents[6] << 48) | 
                            (contents[5] << 40) | 
                            (contents[4] << 32) |
                            (contents[3] << 24) | 
                            (contents[2] << 16) | 
                            (contents[1] << 8)  | 
                            (contents[0]));
        
        return Double.longBitsToDouble(bDouble);
    }
    
    public int getInt()
    {
        int bInt =    (     (contents[3] << 24) | 
                            (contents[2] << 16) | 
                            (contents[1] << 8)  | 
                            (contents[0]));
        
        return bInt;
    }
    
    public long getLong()
    {
        long bLong =   (    (contents[7] << 56) | 
                            (contents[6] << 48) | 
                            (contents[5] << 40) | 
                            (contents[4] << 32) |
                            (contents[3] << 24) | 
                            (contents[2] << 16) | 
                            (contents[1] << 8)  | 
                            (contents[0]));
        
        return bLong;
    }
    
    // Get a float from the byte array 
    public float getFloat(int index)
    {
        int bFloat =    (   (contents[index + 3] << 24) | 
                            (contents[index + 2] << 16) | 
                            (contents[index + 1] << 8)  | 
                            (contents[index + 0]));
        
        return Float.intBitsToFloat(bFloat);
    }
    
    // Get a double from the byte array 
    public double getDouble(int index)
    {
        long bDouble =   (  (contents[index + 7] << 56) | 
                            (contents[index + 6] << 48) | 
                            (contents[index + 5] << 40) | 
                            (contents[index + 4] << 32) |
                            (contents[index + 3] << 24) | 
                            (contents[index + 2] << 16) | 
                            (contents[index + 1] << 8)  | 
                            (contents[index + 0]));
        
        return Double.longBitsToDouble(bDouble);
    }
    
    // Get an integer from the byte array 
    public int getInt(int index)
    {
        int bInt =    (     (contents[index + 3] << 24) | 
                            (contents[index + 2] << 16) | 
                            (contents[index + 1] << 8)  | 
                            (contents[index + 0]));
        
        return bInt;
    }
    
    // Get a long from the byte array 
    public long getLong(int index)
    {
        long bLong =   (    (contents[index + 7] << 56) | 
                            (contents[index + 6] << 48) | 
                            (contents[index + 5] << 40) | 
                            (contents[index + 4] << 32) |
                            (contents[index + 3] << 24) | 
                            (contents[index + 2] << 16) | 
                            (contents[index + 1] << 8)  | 
                            (contents[index + 0]));
        
        return bLong;
    }
    
    // Store a float in the byte array
    public void setFloat(float input, int index)
    {
        // If the byte array is currently empty, allocate memory for it
        if (contents == null)
            contents = new byte[4];
        
        int bFloat = Float.floatToRawIntBits(input);
        
        contents[index]     = (byte)(bFloat & 0xFF);
        contents[index + 1] = (byte)(bFloat >>> 8);
        contents[index + 2] = (byte)(bFloat >>> 16);
        contents[index + 3] = (byte)(bFloat >>> 24);
    }
    
    // Store a double in the byte array
    public void setDouble(double input, int index)
    {
        // If the byte array is currently empty, allocate memory for it
        if (contents == null)
            contents = new byte[8];
        
        long bDouble = Double.doubleToRawLongBits(input);
        
        contents[index]     = (byte)(bDouble & 0xFF);
        contents[index + 1] = (byte)(bDouble >>> 8);
        contents[index + 2] = (byte)(bDouble >>> 16);
        contents[index + 3] = (byte)(bDouble >>> 24);
        contents[index + 4] = (byte)(bDouble >>> 32);
        contents[index + 5] = (byte)(bDouble >>> 40);
        contents[index + 6] = (byte)(bDouble >>> 48);
        contents[index + 7] = (byte)(bDouble >>> 56);
    }
    
    // Store an integer in the byte array
    public void setInt(int input, int index)
    {
        // If the byte array is currently empty, allocate memory for it
        if (contents == null)
            contents = new byte[4];
        
        contents[index]     = (byte)(input & 0xFF);
        contents[index + 1] = (byte)(input >>> 8);
        contents[index + 2] = (byte)(input >>> 16);
        contents[index + 3] = (byte)(input >>> 24);
    }
    
    // Store a Long in the byte array
    public void setLong(long input, int index)
    {
        // If the byte array is currently empty, allocate memory for it
        if (contents == null)
            contents = new byte[8];
        
        contents[index]     = (byte)(input & 0xFF);
        contents[index + 1] = (byte)(input >>> 8);
        contents[index + 2] = (byte)(input >>> 16);
        contents[index + 3] = (byte)(input >>> 24);
        contents[index + 4] = (byte)(input >>> 32);
        contents[index + 5] = (byte)(input >>> 40);
        contents[index + 6] = (byte)(input >>> 48);
        contents[index + 7] = (byte)(input >>> 56);
    }
 
    // Store a short in the byte array
    public void setShort(short input, int index)
    {
        // If the byte array is currently empty, allocate memory for it
        if (contents == null)
            contents = new byte[2];
        
        contents[index]     = (byte)(input);
        contents[index + 1] = (byte)(input >>> 8);
    }
}
