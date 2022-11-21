package leetcode.strings;
//https://leetcode.com/problems/remove-palindromic-subsequences/
public class RemovePalindromicSubsequences {
/*
You are given a string s consisting only of letters 'a' and 'b'.
In a single step you can remove one palindromic subsequence from s.

Return the minimum number of steps to make the given string empty.

Input: s = "ababa"
Output: 1
Explanation: s is already a palindrome, so its entirety can be removed in a single step.
 */
    public static void main(String[] args) {
        //KEEP IN MIND, String consist only with 2 letters, a and b
        String s = "ababa";
        String s1 = "abb";
        System.out.println(removePalindrome(s)+"\n");
        System.out.println(removePalindrome(s1)+"\n");
    }

    //Approach 2: Palindrome Check with Two-Pointer Technique
    public static int removePalindrome(String s) { //Input contains a and b ONLY
        //String length equals 0 -> return 0;
        if(s == null || s.length() == 0) {
            return 0;
        }
        //Two pointers
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            if(s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else { //Not the same character
                // Other cases, you can first remove all 'a' and then all 'b'
                // or vice versa (first all 'b' then all 'a') -> return 2;
                return 2;
            }
        }
        //String itself is a Palindrome -> return 1; (because you can remove them all at once)
        return 1;
    }





}
