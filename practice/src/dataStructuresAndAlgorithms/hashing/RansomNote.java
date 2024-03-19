package dataStructuresAndAlgorithms.hashing;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/ransom-note/
public class RansomNote {
    /*
    Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters
    from magazine and false otherwise.
    Each letter in magazine can only be used once in ransomNote.
     */
    public static void main(String[] args) {
        String ransomNote = "a", magazine = "b";
        String ransomNote1 = "aa", magazine1 = "aab";
        System.out.println(canConstructTwoHashMaps(ransomNote1, magazine1));
        System.out.println(canConstructOneHashMap(ransomNote1, magazine1));
    }

    //Approach 1: One HashMap
    /*
    Time: O(m)
    Space: O(k) / O(1)


    true -> means ransomNote can be constructed by using the letters from magazine.
     */
    static boolean canConstructOneHashMap(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        // Count the number of letters from magazine.
        Map<Character, Integer> magazineCounts = makeCountsMap(magazine);
        for (char c : ransomNote.toCharArray()) {
            int countInMagazine = magazineCounts.getOrDefault(c, 0);
            //If there are none of c left, return false
            if (countInMagazine == 0) {
                return false;
            }
            // Decrease the number of letter from magazine.
            magazineCounts.put(c, countInMagazine - 1);
        }
        return true;
    }


    //Approach 2: Two HashMaps
    /*
    Time: O(m)
    Space: O(k) / 0(1) -> build hashmap O(k) space. k is never more than 26 which is constant : O(1)
     */
    static boolean canConstructTwoHashMaps(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        Map<Character, Integer> ransomNoteCounts = makeCountsMap(ransomNote);
        Map<Character, Integer> magazineCounts = makeCountsMap(magazine);
        for (char c : ransomNoteCounts.keySet()) {
            int countMagazine = magazineCounts.getOrDefault(c, 0);
            int countInRansomNote = ransomNoteCounts.get(c);
            if (countMagazine < countInRansomNote) {//If true means we do not have enough letter in magazine to construct our ransom Note
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Integer> makeCountsMap(String s) {
        Map<Character, Integer> counts = new HashMap<>(26); //26 because we only have letters
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        return counts;
    }


}
