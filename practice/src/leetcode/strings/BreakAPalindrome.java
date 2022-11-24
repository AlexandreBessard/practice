package leetcode.strings;
//https://leetcode.com/problems/break-a-palindrome/
public class BreakAPalindrome {
/*
Given a palindromic string of lowercase English letters palindrome, replace exactly one character with any lowercase English letter so that the resulting string is not a palindrome and that it is the lexicographically smallest one possible.

Return the resulting string. If there is no way to replace a character to make it not a palindrome, return an empty string.

A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, a has a character strictly smaller than the corresponding character in b. For example, "abcc" is lexicographically smaller than "abcd" because the first position they differ is at the fourth character, and 'c' is smaller than 'd'.
Example 1:
Input: palindrome = "abccba"
Output: "aaccba"
Explanation: There are many ways to make "abccba" not a palindrome, such as "zbccba", "aaccba", and "abacba".
Of all the ways, "aaccba" is the lexicographically smallest.
 */
    public static void main(String[] args) {
        String s = "abccba";
        System.out.println(breakPalindrome(s));
    }

    public static String breakPalindrome(String palindrome) {
        int length = palindrome.length();
        if(length == 1) {
            return "";
        }
        //Strings are immutable in Java, convert it into an array
        char[] palindromeArray = palindrome.toCharArray();
        for(int i = 0; i < length / 2; i++) {
            if(palindromeArray[i] != 'a') {
                //lexicographically smallest one possible
                palindromeArray[i] = 'a';
                return String.valueOf(palindromeArray);
            }
        }
        //lexicographically smallest one possible
        palindromeArray[length - 1] = 'b'; //Replaced latest letter with b
        return String.valueOf(palindromeArray);
    }
}
