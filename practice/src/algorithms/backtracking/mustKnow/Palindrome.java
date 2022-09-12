package algorithms.backtracking.mustKnow;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/palindrome-partitioning/
public class Palindrome {
    /*
    Given a string s, partition s such that every substring of the partition is a palindrome.
    Return all possible palindrome partitioning of s.
    A palindrome string is a string that reads the same backward as forward.

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
     */
    public static void main(String[] args) {
        String s = "aab";
        var obj = new Palindrome();
        List<List<String>> res = obj.partition(s);
        for (List<String> l : res) {
            System.out.println(l);
        }
    }

    //Palindrome Partitioning : https://leetcode.com/problems/palindrome-partitioning/
    /*
    Time: O(N * 2n), O(N) time to generate substring and determine if it is a palindrome or not.
    Space: O(N)
     */
    public List<List<String>> partition(String s) {
        List<List<String>> output = new ArrayList<>();
        backtrack(output, new ArrayList<>(), s, 0);
        return output;
    }

    private void backtrack(List<List<String>> output,
                           List<String> currList,
                           String s,
                           int start) {
        if (start == s.length()) {
            //Find a solution
            output.add(new ArrayList<>(currList));
        } else {
            //Try with these candidates
            for (int i = start; i < s.length(); i++) {
                if (isPalindrome(s, start, i)) {
                    currList.add(s.substring(start, i + 1));
                    //Next candidate
                    backtrack(output, currList, s, i + 1);
                    //Backtrack
                    currList.remove(currList.size() - 1);
                }
            }
        }
    }

    private boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--))
                return false;
        }
        return true;
    }
}
