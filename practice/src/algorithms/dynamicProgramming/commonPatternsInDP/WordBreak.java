package algorithms.dynamicProgramming.commonPatternsInDP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/explore/learn/card/dynamic-programming/632/common-patterns-in-dp-problems/4112/
public class WordBreak {

    public static void main(String[] args) {
        /*
        //https://leetcode.com/problems/word-break/
        Input: s = "applepenapple", wordDict = ["apple","pen"]
        Output: true
        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
        Note that you are allowed to reuse a dictionary word.
         */
        String s = "applepenapple";
        List<String> wordDict = new ArrayList<>(Arrays.asList("apple","pen"));
        var obj = new WordBreak();
        //System.out.println(obj.wordBreakBottomUp(s, wordDict));
        System.out.println(obj.wordBreakTopDown(s, wordDict));

    }

    private String s;
    private List<String> wordDict;
    private int[] memo;

    private boolean dp(int i) {
        if (i < 0)
            return true;
        if (memo[i] == -1) {
            for (String word: wordDict) {
                if (i >= word.length() - 1 && dp(i - word.length())) {
                    if (s.substring(i - word.length() + 1, i + 1).equals(word)) {
                        memo[i] = 1;
                        break;
                    }
                }
            }
        }
        if (memo[i] == -1) {
            memo[i] = 0;
        }
        return memo[i] == 1;
    }

    public boolean wordBreakTopDown(String s, List<String> wordDict) {
        this.s = s;
        this.wordDict = wordDict;
        this.memo = new int[s.length()];
        Arrays.fill(this.memo, -1);
        return dp(s.length() - 1);
    }


    public boolean wordBreakBottomUp(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (String word : wordDict) {
                // Make sure to stay in bounds while checking criteria
                if (i >= word.length() - 1 && (i == word.length() - 1 || dp[i - word.length()])) {
                    if (s.substring(i - word.length() + 1, i + 1).equals(word)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }



}
