package leetcode.topinterview150.hashmap.easy;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {

    //https://leetcode.com/problems/ransom-note/?envType=study-plan-v2&envId=top-interview-150

    /*
    Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using
    the letters from magazine and false otherwise.

    Each letter in magazine can only be used once in ransomNote.
     */
    public static void main(String[] args) {
        System.out.println(canConstruct("aa", "ab"));
        System.out.println(canConstruct("aa", "aab"));
        System.out.println(canConstructHashMap("aa", "ab"));
        System.out.println(canConstructHashMap("aa", "aab"));
    }

    /*
    HashMap
    Time: O(m + n) where m is the length of the ransomNote and n is the length of the magazine.
    Space: O(1) because alphabetCounter array is fixed, constant space.
     */
    static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        int[] alphabetCounter = new int[26];

        // Count the frequency of each character in the magazine
        for (char c : magazine.toCharArray()) {
            alphabetCounter[c - 'a']++;
        }

        // Check if the ransomNote can be constructed from the magazine
        for (char c : ransomNote.toCharArray()) {
            if (alphabetCounter[c - 'a'] > 0) {
                alphabetCounter[c - 'a']--;
            } else {
                return false; // If a character is not available in the magazine, return false
            }
        }
        return true;
    }

    /*
    HashMap
    Time: O(m + n)
    Space: O(1), hashmap will contain 26 elements which is constant.
     */
    static boolean canConstructHashMap(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        Map<Character, Integer> charCount = new HashMap<>(26);
        for (char c : magazine.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            if (charCount.containsKey(c) && charCount.get(c) > 0) {
                charCount.put(c, charCount.get(c) - 1);
            } else {
                return false;
            }
        }
        return true;
    }
}
