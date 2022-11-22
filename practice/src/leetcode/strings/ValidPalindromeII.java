package leetcode.strings;
//https://leetcode.com/problems/valid-palindrome-ii/
public class ValidPalindromeII {
/*
Given a string s, return true if the s can be palindrome after deleting at most one character from it.
Example 1:
Input: s = "aba"
Output: true

Example 2:
Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.

Example 3:
Input: s = "abc"
Output: false
*/
    public static void main(String[] args) {
        String s = "aba";
        String s1 = "abca";
        String s2 = "abc";
        String s3 = "abcda";
        /*
        System.out.println(validPalindrome(s));
        System.out.println(validPalindrome(s1));
        System.out.println(validPalindrome(s2));
         */
        System.out.println(validPalindrome(s3));
    }

    //After deleting at most one character
    /*
    Time: O(N)
    Space: O(N)
     */
    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) { //True, we have different letter
                //try both deletions, the first one and last one and see without the letter if still a palindrome
                return isPalindrome(s, left + 1, right)
                        || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindrome(String s, int left, int right) {
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
