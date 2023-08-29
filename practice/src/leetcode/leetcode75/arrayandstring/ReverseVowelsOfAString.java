package leetcode.leetcode75.arrayandstring;

import java.util.Arrays;

//https://leetcode.com/problems/reverse-vowels-of-a-string/?envType=study-plan-v2&envId=leetcode-75
public class ReverseVowelsOfAString {

    public static void main(String[] args) {
        String input1 = "hello";
        String input2 = "leetcode";
        System.out.println(reverseVowels(input1));
        System.out.println(reverseVowels(input2));
    }

    /*
    Example 1:

    Input: s = "hello"
    Output: "holle"
    Example 2:

    Input: s = "leetcode"
    Output: "leotcede"
     */
    //Two pointers
    /*
    Time: O(n) where n is the length of the string. Each character is processed once.
    Space: O(n) because we create a character array word to store the character of the inout string.
     */
    public static String reverseVowels(String s) {
        char[] word = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        String vowels = "aeiouAEIOU";

        while (start < end) {
            //Move the start pointer until it points to a vowel
            //If it is a vowel, returns 0
            while (start < end && vowels.indexOf(word[start]) == -1) {
                start++;
            }
            while (start < end && vowels.indexOf(word[end]) == -1) {
                end--;
            }
            //Swap the vowel
            char temp = word[start];
            word[start] = word[end];
            word[end] = temp;
            // Move the pointers towards each other
            start++;
            end--;
        }
        return Arrays.toString(word);
    }

}
