package leetcode.strings;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/881/
public class FirstUniqueCharacterInAString {
/*
Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 */
    public static void main(String[] args) {
        String s = "lovelee";
        //First Unique character is index 2 : 'v'
        //System.out.println(firstUniqueChar(s));
        //System.out.println(firstUniqueCharacterLinkedHashMap(s));
        System.out.println(firstUniqueCharacterWithArray(s));
    }

    //Same logic as below with one pass by using an array instead of LinkedHashMap
    static int firstUniqueCharacterWithArray(String s) {
        Set<Character> set = new HashSet<>();
        int[] pos = new int[26]; //Used to store the index
        Arrays.fill(pos, -1);
        for (int i = 0; i < s.length(); i++) { //loop through each character
            char c = s.charAt(i);
            if (!set.contains(c)) {
                set.add(c);
                //Store the index, used to get the smaller index (first non-repeating character)
                pos[c - 'a'] = i;
            } else { //Duplicate
                pos[c - 'a'] = -1; //-1 is a marker to know that we have a duplicate
            }
        }
        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {  //Loop to get the smallest index which means it is the first non-repeating character
            if (pos[i] != -1) { //-1 means it is a duplicate
                //Get min index (First non-repeating character located by the index)
                minIndex = Math.min(minIndex, pos[i]);
            }
        }
        return minIndex == Integer.MAX_VALUE ? -1 : minIndex;
    }


    /*
    One pass using LinkedHashMap
    Time: O(n)
    Space: O(n) caused by map and set
     */
    static int firstUniqueCharacterLinkedHashMap(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                if (map.containsKey(c)) {
                    map.remove(c);
                }
            } else {
                map.put(c, i);
                set.add(c);
            }
        }
        return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue();
    }


    /*
    Time complexity: O(N), since we go through the string of length N two times.
    Space complexity: O(1), Because English alphabet contains 26 letters (static num)
     */
    static int firstUniqueChar(String s) {
        Map<Character, Integer> count = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        //find the index
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (count.get(c) == 1)
                return i;
        }
        return -1;
    }

}
