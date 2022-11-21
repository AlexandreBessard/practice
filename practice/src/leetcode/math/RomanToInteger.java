package leetcode.math;

import java.util.HashMap;
import java.util.Map;
//Solution:
//https://leetcode.com/problems/roman-to-integer/discuss/852501/Java-Easy-Solution-%3A-Runtime-5ms-Space-Linear
public class RomanToInteger {
    /*
    Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
     */
    public static void main(String[] args) {
/*
    Input: s = "LVIII"
    Output: 58
    Explanation: L = 50, V= 5, III = 3.
 */
        var obj = new RomanToInteger();
        //MCMXXV
        //System.out.println(obj.romanToInt("MCMXXV"));
        System.out.println(obj.romanToInt("III"));
        System.out.println(obj.romanToInt("MCMXCIV"));
    }

    static Map<Character, Integer> map = new HashMap<>() {
        {
            put('M', 1000);
            put('D', 500);
            put('C', 100);
            put('L', 50);
            put('X', 10);
            put('V', 5);
            put('I', 1);
        }
    };

    /*
    Approach: left to right pass
    Time complexity: O(1)
    Space complexity: 0(1)
     */
    int romanToInt(String s) { //"MCMXCIV"
        int number = 0;
        if(s.length() < 2) {//If we only have 1 Roman number
            return map.get(s.charAt(0));
        }
        for(int i = 0; i < s.length(); i++) {
            //if not out of bond, get the next one else get the current one
            char nextElement = (i < s.length() - 1) ? s.charAt(i + 1) : s.charAt(i); //avoid out of bond exception ex: III
            char currElement = s.charAt(i);
            if(i < s.length() - 1 && map.get(nextElement) > map.get(currElement)) //next element greather than current one
            {
                //Subtract ex: 'CM' -> 1000 - 100 == 900
                number += map.get(nextElement) - map.get(currElement);
                i++; //Increment i because we processed both the current and next one
            } else {
                //Addition
                number += map.get(currElement);
            }
        }
        return number;
    }
}
