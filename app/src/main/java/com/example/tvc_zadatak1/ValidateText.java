package com.example.tvc_zadatak1;

import android.content.Context;
import android.widget.Toast;

public class ValidateText {

    /*
       Provjera je zadovoljena ako:
       ◦ broj znakova u tekstu je veći od 4 i manji od 13
       ◦ slovo ‘A’, ili veliko ili malo, se pojavljuje barem 2 puta
       ◦ ima točno jedan broj 7
       ◦ nema znak ‘?’
    */
    public static boolean validateText(String str, Context context)
    {
        if(str.length() <= 4 ||  str.length() >= 13)
        {
            return false;
        }
        int numOfA = 0;
        int numOf7 = 0;

        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == '?')
            {
                return false;
            }
            if(str.charAt(i) == '7') {
                numOf7++;
                if(numOf7 > 1)
                {
                    return false;
                }
            }
            if(str.charAt(i) == 'a' || str.charAt(i) == 'A') {
                numOfA++;
            }
        }
        if(numOfA < 2 || numOf7 != 1)
        {
            return false;
        }
        return true;
    }
}
