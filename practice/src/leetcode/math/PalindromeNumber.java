package leetcode.math;
//https://leetcode.com/problems/palindrome-number/
public class PalindromeNumber {
/*
Given an integer x, return true if x is a palindrome, and false otherwise.
 */
    public static void main(String[] args) {
        int x = 121; //true
        int x1 = -121; //false
        int x2 = 10; // false
        System.out.println(isPalindrome(x));
        System.out.println(isPalindrome(x1));
        System.out.println(isPalindrome(x2));
    }

    //Approach 1 convert num into a String
    /*
    Time: O(N)
    Space: O(N)
     */
    public static boolean isPalindrome(int x) {
        String str = String.valueOf(x); //Convert into string
        int left = 0; //Left pointer
        int right = str.length() - 1; //Right pointer
        while(left < right) {
            if(str.charAt(left++) != str.charAt(right--)) { //true if not same letter
                return false;
            }
        }
        return true;
    }

}
