package leetcode.strings;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/isomorphic-strings/
public class IsomorphicStrings {
    /*
    Given two strings s and t, determine if they are isomorphic.

    Two strings s and t are isomorphic if the characters in s can be replaced to get t.

    All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

    Input: s = "egg", t = "add"
    Output: true
     */
    public static void main(String[] args) {
        String s = "egg", t = "add";
        String s1 = "foo", t1 = "bar";
        String s2 = "paper", t2 = "title";
        /*
        System.out.println(isIsomorphic(s, t));
        System.out.println(isIsomorphic(s1, t1));
         */
        System.out.println(isIsomorphic(s2, t2));
    }

    //Approach 2: First occurence transformation
    /*
    Time: O(N)
    Space: O(N)
     */
    public static boolean isIsomorphic(String s, String t) {
        return transformString(s) // return 01034
                .equals(transformString(t)); //return 01034
    }

    public static String transformString(String s) {
        //Key: letter, Value: index
        Map<Character, Integer> indexMapping = new HashMap<>();
        var strBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            if (!indexMapping.containsKey(c1)) { //First time, set the value's index as value
                indexMapping.put(c1, i);
            }
            int index = indexMapping.get(c1);
            strBuilder.append(index);
        }
        return strBuilder.toString();
    }


}
