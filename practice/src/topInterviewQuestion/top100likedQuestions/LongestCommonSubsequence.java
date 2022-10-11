package topInterviewQuestion.top100likedQuestions;

import java.util.Arrays;
import java.util.Map;

//https://leetcode.com/problems/longest-common-subsequence/solution/
public class LongestCommonSubsequence {
    /*
    Given two strings text1 and text2, return the length of their longest common subsequence.
    If there is no common subsequence, return 0.

    A subsequence of a string is a new string generated from the original string with some characters (can be none)
    deleted without changing the relative order of the remaining characters.

    For example, "ace" is a subsequence of "abcde".    -> abcde contains 'ace' letters without changing the order of 'ace'
    A common subsequence of two strings is a subsequence that is common to both strings.
     */
    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
        String text3 = "abc", text4 = "abc";
        String text5 = "actgattag", text6 = "gtgtgatcg";
        System.out.println(longestCommonSubsequence(text1, text2));
        System.out.println(longestCommonSubsequenceBottomUp(text1, text2));
    }

    //Approach 2 Bottom Up:
    //Explanation IMPORTANT to understand:
    //https://www.youtube.com/watch?v=Ua0GhsJSlWM
    /*
    Time: O(M . N)
    Space: O(M . N)
     */
    static int longestCommonSubsequenceBottomUp(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        // Iterate up each column, starting from the last one -> Bottom Up we start from end to start
        for(int row = text1.length() - 1; row >= 0; row--) {
            for(int col = text2.length() - 1; col >= 0; col--) {
                // If the corresponding characters for this cell are the same...
                if(text1.charAt(row) == text2.charAt(col)) {
                    dp[row][col] = 1 + dp[row + 1][col + 1]; //Get the result from diagonal (see explanation video, easy to understand)
                } else { //different characters
                    dp[row][col] = Math.max(dp[row][col + 1], dp[row + 1][col]);
                }
            }
        }
        // The original problem's answer is in dp_grid[0][0]. Return it.
        return dp[0][0];
    }


    //Approach 1: Memoization
    private static int[][] memo;
    private static String text1;
    private static String text2;

    static int longestCommonSubsequence(String text1, String text2) {
        // Make the memo big enough to hold the cases where the pointers
        // go over the edges of the strings.
        memo = new int[text1.length() + 1][text2.length() + 1];
        // We need to initialise the memo array to -1's so that we know
        // whether or not a value has been filled in. Keep the base cases
        // as 0's to simplify the later code a bit.
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                memo[i][j] = -1; //The last row and last column is set to 0 use as a base case
            }
        }
        LongestCommonSubsequence.text1 = text1;
        LongestCommonSubsequence.text2 = text2;
        return memoSolve(0, 0);
    }

    private static int memoSolve(int p1, int p2) {
        // Check whether or not we've already solved this subproblem.
        // This also covers the base cases where p1 == text1.length
        // or p2 == text2.length because the last row and last column is set to 0
        if (memo[p1][p2] != -1) {
            return memo[p1][p2];
        }
        //Option 1: We do not include text1[p1] in the solution
        int option1 = memoSolve(p1 + 1, p2);
        // Option 2: We include text1[p1] in the solution, as long as
        // a match for it in text2 at or after p2 exists.
        int firstOccurence = text2.indexOf(text1.charAt(p1), p2); //Returns the index within this string of the first occurrence of the specified character, starting the search at the specified index.
        int option2 = 0;
        if (firstOccurence != -1) {
            option2 = 1 + memoSolve(p1 + 1, firstOccurence + 1); //Means 1 occurrence
        }
        //Add the best answer to the memo before returning it
        memo[p1][p2] = Math.max(option1, option2);
        return memo[p1][p2];
    }

}
