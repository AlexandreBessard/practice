package leetcode.strings;

import java.util.Stack;

//https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
public class RemoveAllAdjacentDuplicatesInStringII {
/*
You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation:
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
 */
    public static void main(String[] args) {
        //String s1 = "abcd";
        String s2 = "deeedbbcccbdaa";
        //System.out.println(removeDuplicate(s1, 2));
        System.out.println(removeDuplicateBrutForce(s2, 3));
        System.out.println(removeDuplicatesStack(s2, 3));
    }


    //Approach 3: Stack
    /*
    Time: O(n) where n is a string length, we process each character once
    Space: O(n) for the stack
     */
    public static String removeDuplicatesStack(String s, int k) {
        var sb = new StringBuilder(s);
        //FIFO: store the count of each number
        Stack<Integer> counts = new Stack<>();
        for(int currIndex = 0; currIndex < sb.length(); currIndex++) {
            if(currIndex == 0 //true if first letter
                    //true if previous letter is NOT the same
                    || sb.charAt(currIndex) != sb.charAt(currIndex - 1)) {
                counts.push(1);
            } else { //same letter with the previous one
                //Retrieve count from the previous one, add one (current one)
                int incremented = counts.pop() + 1;
                if(incremented == k) { //If previous + current counts == k
                    final int start = currIndex - k + 1;
                    final int end = currIndex + 1;
                    sb.delete(start, end); //Delete the range
                    currIndex = currIndex - incremented; //subtract to get the new index after removing that range
                } else {
                    //push the new updated count
                    counts.push(incremented);
                }

            }
        }
        return sb.toString();
    }

    //Approach 1: Brut force
    /*
    Time: O(n²/k) whee n is a string length
    Space: O(1)
     */
    public static String removeDuplicateBrutForce(String s, int k) {
        //Initialize with the string from the input
        var strBuilder = new StringBuilder(s);
        int length = -1;
        while(length != strBuilder.length()) {
            //Reinitialise with the new length after deleting letters
            length = strBuilder.length();
            for(int currIndex = 0, count = 0; currIndex < strBuilder.length(); currIndex++, count++) {
                if(currIndex == 0 || //true if first letter
                        //Check if same letter between the previous and current one
                        strBuilder.charAt(currIndex) != strBuilder.charAt(currIndex - 1)) {
                    //Reinitialise to 1
                    count = 1; //Count how many same subsequence letter
                    //Be careful, increment count by 1 for each iteration if above condition is NOT true
                } else if(count == k) {
                    int start = currIndex - k + 1; //inclusive
                    int end = currIndex + 1; //exclusive
                    strBuilder.delete(start, end);
                    break; //Exit the for loop
                }
            }
        }
        return strBuilder.toString();
    }



}
