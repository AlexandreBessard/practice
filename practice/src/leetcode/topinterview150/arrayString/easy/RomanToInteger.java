package leetcode.topinterview150.arrayString.easy;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    // https://leetcode.com/problems/roman-to-integer/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        String s = "III";
        System.out.println(romanToInt(s));
        // M = 1000, CM = 900, XC = 90 and IV = 4.
        s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }

    static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int currentVal = map.get(s.charAt(i));
            // Get the next element to the current one
            int nextVal = (i < s.length() - 1) ? map.get(s.charAt(i + 1)) : 0;

            if (currentVal < nextVal) {
                result -= currentVal;
            } else {
                result += currentVal;
            }
        }
        return result;
    }

}
