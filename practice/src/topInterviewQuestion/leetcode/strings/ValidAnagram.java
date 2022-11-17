package topInterviewQuestion.leetcode.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    /*
    Given two strings s and t, return true if t is an anagram of s, and false otherwise.

    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
    typically using all the original letters exactly once.
     */

    /*
    We can use SLIDING WINDOW
     */
    public static void main(String[] args) {
        /*
        An Anagram is a word or phrase formed by rearranging the letters
        of a different word or phrase, typically using all the original
        letters exactly once.
         */
        String s = "anagram", t = "nagaram";
        //Output true
        System.out.println(isAnagram(s, t));
        System.out.println(isAnagramFrequencyCounter(s, t));
        System.out.println(isAnagramFrequencyCounterV2(s, t));
        System.out.println(isAnagramSlidingWindow(s, t));
    }

    //Approach: Sliding Window
    /*
    Time: O(n)
    Space: O(n)
     */
    public static boolean isAnagramSlidingWindow(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        int matched = 0;
        Map<Character, Integer> charFreqMap = new HashMap<>();
        for(char chr : s1.toCharArray()) { //count each element and insert it to the map
            charFreqMap.put(chr, charFreqMap.getOrDefault(chr, 0) + 1);
        }
        for(int windowEnd = 0; windowEnd < s1.length(); windowEnd++) {
            char rightChar = s1.charAt(windowEnd);
            if(charFreqMap.containsKey(rightChar)) {
                charFreqMap.put(rightChar, charFreqMap.get(rightChar) - 1); //Decrement by 1
                if(charFreqMap.get(rightChar) < 0) {
                    return false;
                }
                matched++;
            }
            if(matched == charFreqMap.size()) {
                return true;
            }
        }
        return false;
    }

    /*
    FOLLOW UP:
    What if the inputs contain unicode characters?
    How would you adapt your solution to such case?
    Answer:
    Use a hash table instead of a fixed size counter.
    Imagine allocating a large size array to fit
    the entire range of unicode characters, which could go up to more
    than 1 million. A hash table is a more generic
    solution and could adapt to any range of characters.
     */
    static boolean isAnagramFrequencyCounterFOLLOWUP(String s,
                                                     String t) {
        HashMap<Character, Integer> smap = new HashMap<>();
        int sl = s.length();
        int tl = t.length();
        if(sl != tl)
            return false;
        for(int i = 0; i < sl; i++) {
            smap.put(s.charAt(i), smap.getOrDefault(s.charAt(i), 0) + 1);
            smap.put(t.charAt(i), smap.getOrDefault(t.charAt(i), 0) - 1);
        }
        for(char c : smap.keySet()) {
            if(smap.get(c) != 0)
                return false;
        }
        return true;
    }

    //Approach 2: return false right after value is below 0
    static boolean isAnagramFrequencyCounterV2(String s, String t) {
        if(s.length() != t.length())
            return false;
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'a']--;
            if(count[t.charAt(i) - 'a'] < 0)
                return false;
        }
        return true;
    }

    //Approach 2: Frequency Counter
    static boolean isAnagramFrequencyCounter(String s, String t) {
        if(s.length() != t.length())
            return false;
        int[] counter = new int[26];
        for(int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for(int count: counter) {
            if(count != 0) {
                return false;
            }
        }
        return true;
    }

    //Approach 1: Sorting
    /*
    Time complexity: O(n log n) caused by sorting
    Comparing two strings cost O(n). Overall it is O(n log n)
     */
    static boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

}
