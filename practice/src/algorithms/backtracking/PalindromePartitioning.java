package algorithms.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/palindrome-partitioning/
public class PalindromePartitioning {
    /*
    Given a string s, partition s such that every substring of the partition is a palindrome.
    Return all possible palindrome partitioning of s.

    A palindrome string is a string that reads the same backward as forward.
     */
    public static void main(String[] args) {
        String s = "aab";
        for (List<String> el : partition(s)) {
            System.out.println(el);
        }
    }

    /*
    Time: O(N * 2n) where N is the length of the string. Worst case, when all the possible substrings are palindrome
    Space: O(N)
     */
    static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtracking(0, res, new LinkedList<>(), s);
        return res;
    }

    //Sequence : ->        a, a, b, ab, aa, b, aab    [a, a, b]
    private static void backtracking(int start, List<List<String>> res, LinkedList<String> currList, String s) {
        if (start >= s.length()) {
            res.add(new ArrayList<>(currList));
            return;
        } else {
            for (int end = start; end < s.length(); end++) {
                System.out.print(s.substring(start, end + 1) + ", ");
                if (isPalindrome(s, start, end)) {
                    currList.add(s.substring(start, end + 1));
                    backtracking(end + 1, res, currList, s);
                    currList.removeLast();
                }
            }
        }
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) { //If we have only one letter, we already know it is palindrome
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}

