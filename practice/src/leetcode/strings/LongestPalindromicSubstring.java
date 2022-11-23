package leetcode.strings;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/780/
public class LongestPalindromicSubstring {
    /*
    Given a string s, return the longest palindromic substring in s.
     */
    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }

    private static int maxLengthSoFar = Integer.MIN_VALUE;
    private static int beginIndex = 0;
    //Approach 1: Expand around the center of each character
    /*
    Time: O(N^2)
    Space: O(1)
     */
    public static String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) { //true if 1 letter or below
            return s;
        }
        //Loop through each element which is the center
        for (int i = 0; i < length; i++) {
            extendPalindrome(i, i, s);// odd length
            extendPalindrome(i, i + 1, s);// even length
        }
        int endIndex = beginIndex + maxLengthSoFar;
        return s.substring(beginIndex, endIndex);
    }

    private static void extendPalindrome(int left, int right, String s) {
        while (left >= 0 && right < s.length() //Check if still in that range
                && s.charAt(left) == s.charAt(right)) { // True: expand from the middle
            left--;
            right++;
        }
        //Get the max length  so far
        if (maxLengthSoFar < (right - left) - 1) { //First occurence: (1 - (-1)) - 1) == 1
            beginIndex = left + 1;
            maxLengthSoFar = (right - left) - 1;
        }
    }
}
