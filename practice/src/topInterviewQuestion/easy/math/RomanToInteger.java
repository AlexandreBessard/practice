package topInterviewQuestion.easy.math;

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
        System.out.println(obj.romanToInt("MCMXXV"));
    }

    static Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
    }

    /*
    Approach: left to right pass
    Time complexity: O(1)
    Space complexity: 0(1)
     */
    int romanToInt(String s) { //"MCMXXV"
        int number = 0;
        if(s.length() < 2)
            return map.get(s.charAt(0));
        for(int i = 0; i < s.length(); i++) {
            if(i < s.length() - 1 && map.get(s.charAt(i + 1)) > map.get(s.charAt(i)))
            {
                number += map.get(s.charAt(i + 1)) - map.get(s.charAt(i));
                i++;
            } else {
                number += map.get(s.charAt(i));
            }
        }
        return number;
    }
}
