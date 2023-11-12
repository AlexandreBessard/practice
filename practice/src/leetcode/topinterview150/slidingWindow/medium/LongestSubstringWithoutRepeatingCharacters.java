package leetcode.topinterview150.slidingWindow.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    // https://leetcode.com/problems/longest-substring-without-repeating-characters/?envType=study-plan-v2&envId=top-interview-150
    /*
    Given a string s, find the length of the longest substring
    without repeating characters.
     */
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
        s = "bbbbb";
        System.out.println(lengthOfLongestSubstring(s));
        s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
        s = "abcdef";
        System.out.println(lengthOfLongestSubstring(s));
        s = "rsuvss";
        System.out.println(lengthOfLongestSubstring(s));
    }

    /*
    Sliding window
    Time: O(n)
    Space: O(n)
     */
    static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int max = 0;
        int windowStart = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char currentChar = s.charAt(windowEnd);

            // Update the window start if the current character is already in the map
            if (charIndexMap.containsKey(currentChar)) {
                // We reduce the sliding window
                // from where the last time we met this same letter
                // +1 means the next letter
                windowStart = Math.max(windowStart, charIndexMap.get(currentChar) + 1);
            }

            // Update the character index in the map
            charIndexMap.put(currentChar, windowEnd);

            // Update the maximum length
            max = Math.max(max, windowEnd - windowStart + 1);
        }

        return max;
    }
}
