package algorithms.dynamicProgramming.commonPatternsInDP;

import java.util.*;

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
        String s1 = "leetcode";
        String s2 = "catsandog";
        List<String> wordDict2 = new ArrayList<>(List.of("cats", "dog", "and"));
        List<String> wordDict1 = new ArrayList<>(List.of("leet", "code"));
        List<String> wordDict = new ArrayList<>(Arrays.asList("apple", "pen"));
        var obj = new WordBreak();
        //System.out.println(obj.wordBreakBottomUp(s, wordDict));
        System.out.println(obj.wordBreakTopDown(s, wordDict));
        System.out.println("BFS -> \n" + wordBreakBFS(s, wordDict));
    }

    //Approach 3: Using BFS
    /*
    BFS because we can visualize the string as a tree where each node represents the prefix upto to index end
    Time: O(n3)
    Space: O(n) -> Queue of at most n size is needed
     */
    public static boolean wordBreakBFS(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.poll();
            if (visited[start]) {
                continue;
            }
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDictSet.contains(s.substring(start, end))) {
                    queue.add(end);
                    if (end == s.length()) {
                        return true;
                    }
                }
            }
            visited[start] = true;
        }
        return false;
    }

    private String s;
    private List<String> wordDict;
    private int[] memo;

    private boolean dp(int i) {
        if (i < 0)
            return true;
        if (memo[i] == -1) {
            for (String word : wordDict) {
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
