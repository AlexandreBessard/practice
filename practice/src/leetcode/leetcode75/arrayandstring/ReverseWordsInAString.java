package leetcode.leetcode75.arrayandstring;

import java.util.Arrays;

//https://leetcode.com/problems/reverse-words-in-a-string/?envType=study-plan-v2&envId=leetcode-75
public class ReverseWordsInAString {

    public static void main(String[] args) {
        String input1 = "the sky is blue";
        String input2 = "   hello world  ";
        String input3 = "a good      example";
        System.out.println(reverseWords(input1));
        System.out.println(reverseWords(input2));
        System.out.println(reverseWords(input3));

/*        char[] input1Char = input1.toCharArray();
        System.out.println(reverseWords(input1Char));*/
        char[] input2Char = input2.toCharArray();
        System.out.println(reverseWords(input2Char));
    }

    /*
    Example 1:

    Input: s = "the sky is blue"
    Output: "blue is sky the"
    Example 2:

    Input: s = "  hello world  "
    Output: "world hello"
    Explanation: Your reversed string should not contain leading or trailing spaces.
     */
    //Two pointer
    public static String reverseWords(String s) {
        //Split between one or more spaces.
        // trim -> leading and trailing space removed.
        String[] words = s.trim().split("\\s+");
        reverseArray(words);
        return Arrays.toString(words);
    }

    //Two pointers
    private static void reverseArray(String[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            // Swap the words
            String temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    //Follow-up: If the string data type is mutable char[] in your language,
    // It is not needed to trim leading and trailing white space.
    // can you solve it in-place with O(1) extra space?
    public static String reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        //Two pointers
        int start = 0;
        for (int end = 0; end < s.length; end++) {
            //True if we met a white space which means we have a word
            if (s[end] == ' ') {
                // - 1 because we do not include the white space.
                reverse(s, start, end - 1);
                //Beginning of the new word after that white space.
                start = end + 1;
            }
        }
        //Reverse the last word
        reverse(s, start, s.length - 1);
        return Arrays.toString(s);
    }

    private static void reverse(char[] s, int left, int right) {
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

}
