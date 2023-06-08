package GameLogic.Core;

// Game state as enum
enum State_Enum
{
    NULL,       // Invalid state
    MONSTER,    // Monster encounter
    EXPLORE,    // Exploring
    REST,       // Resting
    GAME_OVER   // Game over
}

// Defines an object that determines game state
public class GameState {
    public String       state_str;
    public State_Enum   state;
    
    // Get the game state string (prevents breaking)
    public String toString()
    {
        return getStateString();
    }
    
    // Check if equal to a state defined in an enum
    public boolean equals(State_Enum state_val)
    {
        return (this.state == state_val);
    }
    
    // Check if equal to a state defined in a string
    public boolean equals(String state_val)
    {
        return state_val.equals(state_str);
    }
    
    // Set game state based on enum
    public void setState(State_Enum state_val)
    {
        this.state = state_val;
        getStateString();
    }
    
    // Set game state based on string
    public void setState(String state_val)
    {
        this.state_str = state_val;
        
        if      (state_val.equals("monster"))   { this.state = State_Enum.MONSTER;  }
        else if (state_val.equals("explore"))   { this.state = State_Enum.EXPLORE;  }
        else if (state_val.equals("rest"))      { this.state = State_Enum.REST;     }
        else if (state_val.equals("gameover"))  { this.state = State_Enum.GAME_OVER;}
        else                                    { this.state = State_Enum.NULL;     }
    }
    
    // Get the game state string
    public String getStateString()
    {
        switch(state)
        {
            case MONSTER:   state_str = "monster";  break;
            case EXPLORE:   state_str = "explore";  break;
            case REST:      state_str = "rest";     break;
            case GAME_OVER: state_str = "gameover"; break;
        }
        return state_str;
    }
    
    // Initialize based on string contents
    GameState(String state_val)
    {
        setState(state_val);
    }
    
    // Initialize based on state val
    GameState(State_Enum state_val)
    {
        this.state      = state_val;
        this.state_str  = getStateString(); 
    }
    
    // Initialize based on integer
    GameState(int state_val)
    {
        switch(state_val)
        {
            case 1:     
                state       = State_Enum.MONSTER;
                state_str   = "monster";  
            break;
            case 2:
                state       = State_Enum.MONSTER;
                state_str   = "explore";  
            break;
            case 3:
                state       = State_Enum.MONSTER;
                state_str   = "rest";     
            break;
            case 4:
                state       = State_Enum.MONSTER;
                state_str   = "gameover"; 
            break;
        }
    }
}
