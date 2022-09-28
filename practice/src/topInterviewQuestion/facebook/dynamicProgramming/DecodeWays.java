package topInterviewQuestion.facebook.dynamicProgramming;

import com.sun.security.jgss.GSSUtil;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/facebook/55/dynamic-programming-3/264/
public class DecodeWays {
    /*
    Input: s = "12"
    Output: 2
    Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
    Input: s = "226"
    Output: 3
    Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     */
    public static void main(String[] args) {
        String s = "12";
        String s1 = "226";
        System.out.println(numDecoding(s1));
        System.out.println(numDecodingBottomUp(s1));
    }

    //Bottom Up approach
    /*
    Time: O(N)
    Space: O(N)
     */
    public static int numDecodingBottomUp(String s) {
        // DP array to store the subproblem results
        int[] dp = new int[s.length() + 1];
        dp[0] = 1; //0 represent the possible way to decode an empty String which is only 1 possible way
        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for(int i = 2; i < dp.length; i++) {
            // Check if successful single digit decode is possible.
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }
            // Check if successful two digit decode is possible.
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    //Approach 1: Recursive approach with memoization
    private static Map<Integer, Integer> memo = new HashMap<>();

    public static int numDecoding(String s) {
        return recursiveWithMemo(0, s);
    }

    private static int recursiveWithMemo(int index, String str) {
        // Have we already seen this substring?
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        // If you reach the end of the string
        // Return 1 for success.
        if (index == str.length()) {
            return 1;
        }
        // If the string starts with a zero, it can't be decoded
        if (str.charAt(index) == '0') {
            return 0;
        }
        if (index == str.length() - 1) {
            return 1;
        }
        int ans = recursiveWithMemo(index + 1, str);
        if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
            ans += recursiveWithMemo(index + 2, str);
        }
        // Save for memoization
        memo.put(index, ans);
        return ans;
    }


}
