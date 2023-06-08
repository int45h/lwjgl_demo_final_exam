
package GameLogic.Core;

/**
 * EET218 - Java Programming
 * Author: Danny Royer
 * Created Mar 29, 2019
 * Lab #
 * Purpose: 
 */
public class Dice {
    public static int roll(int numSidedDie)
    {
        return new java.util.Random().nextInt(numSidedDie) + 1;
    }
}
