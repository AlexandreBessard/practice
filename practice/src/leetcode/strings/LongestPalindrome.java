package leetcode.strings;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/longest-palindrome/description/
 */
public class LongestPalindrome {

    public static void main(String[] args) {

        var s = "abccccdd";
        System.out.println(longestPalindrome(s));
    }


    public static int longestPalindrome(String s) {
        // Map to store character counts
        Map<Character, Integer> charCount = new HashMap();
        // Count characters
        for(char c: s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        int palindromeLength = 0;
        boolean hasOddCount = false;
        // Calculate palindrome length
        // Considering even counts of characters (which contribute to pairs of characters in the palindrome),
        for(int count: charCount.values()) {
            palindromeLength += count / 2 * 2;
            // Odd count
            if (count % 2 == 1) {
                hasOddCount = true;
            }
        }
        // Add one if there's at least one character with odd count
        return palindromeLength + (hasOddCount ? 1 : 0);
    }

}
