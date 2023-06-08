/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Murmurhash3;

/* Murmurhash3 implementation */
public class Murmurhash3 {
    
    /* Rotate left 13 bits */
    static int ROL13(int input)
    {   
        return Integer.rotateLeft(input, 13);
    }

    /* Multiplies by a magic number, Bitwise Rolls left by 15 bits, then multiplies by another magic number */
    static int mmh3_scramble_ROL15(int input)
    {
        /* Voodoo Magic Constants I don't understand */
        int c1 = 0xCC9E2D51,
            c2 = 0x1B873593,
            m  = 5,
            n  = 0xE6546B64;
            
        input   *=  c1;
        input   =   Integer.rotateLeft(input, 15);
        input   *=  c2;

        return input;
    }
        
    /* MurmurHash3 algorithm, ala https://en.wikipedia.org/wiki/MurmurHash */
    public static int hash(long seed, byte[] UTF8_String)
    {
        /* Voodoo Magic Constants I don't understand */
        int c1                  = 0xCC9E2D51,
            c2                  = 0x1B873593,
            m                   = 5,
            n                   = 0xE6546B64,
            length              = UTF8_String.length;
            
        /* length aligned on 4 byte boundaries */
        int length_4b           = (length >> 2) * 4,
            length_remainder    = length & 3;

	/* Initialize the hash with a seed */
        int hash                =  (int) seed,
            remaining_bytes     =   0,
            k;

        /* Scan all 4-byte chunks and scramble according to the algorithm mmh3_scramble_ROL15 */
        for (int i = 0; i < length_4b; i += 4)
        {
            k = (   (UTF8_String[i + 3] << 24) |
                    (UTF8_String[i + 2] << 16) |
                    (UTF8_String[i + 1] <<  8) | 
                    (UTF8_String[i + 0]));
            
            k = mmh3_scramble_ROL15(k);
            hash ^= k;
            
            hash = ROL13(hash);
            hash = (hash * m) + n;
        }

        /* If the length doesn't quite fit in a set of 4-byte chunks, do some bit shuffling wizardy */
        if (length_remainder > 0)
        {
            for (int i = (int)length_remainder; i > 0; i--)
            {
                remaining_bytes <<= 8;
                remaining_bytes |= UTF8_String[length_4b + i - 1];
            }

            remaining_bytes = mmh3_scramble_ROL15(remaining_bytes);
        }
            
        /* More shuffling voodoo black magick, but it works ^-^ */
        hash ^= remaining_bytes;
            
        hash ^= length;
        hash ^= (hash >>> 16);
        hash *= 0x85EBCA6B;
        hash ^= (hash >>> 13);
        hash *= 0xC2B2AE35;
        hash ^= (hash >>> 16);
            
        return hash;
    }
}
