package topInterviewQuestion.facebook.topMostFrequentQuestions;

//https://leetcode.com/problems/valid-palindrome-iii/
public class ValidPalindromeIII {

    public static void main(String[] args) {
        String s = "abcdeca";
        int k = 2;
        System.out.println(isValidPalindromeTopDown(s, k));
        System.out.println(isValidPalindromeBottomUp(s, k));
    }

    /*
    To-Down (DP) 2D
     */
    /*
    Algorithm
    How do we find the minimum characters to be removed to make it a palindrome? Let's imagine matching the characters of the string like a palindrome, from the beginning and the end with 2 pointers i and j. We may encounter the following two scenarios:

    The character at i matches character at j.
    The characters don't match each other.
    For case 1 we just increase the pointer i and decrease the pointer j, i++ and j-- respectively.

    In the second case we have 2 options:

    Remove character at j and see if the previous character matches character at i.
    Or

    Remove character at i and see if the next character matches character at j.
     */
    static Integer[][] memo;

    /*
    Time: O(n²) find result for all combination with i and j
    Space: O(n²) -> caching result in memo table
     */
    static boolean isValidPalindromeTopDown(String s, int k) { //s -> abcdeca   // k -> 2
        memo = new Integer[s.length()][s.length()];
        // Return true if the minimum cost to create a palindrome by only deleting the letters
        // is less than or equal to `k`.
        return isValidPalindrome(s, 0, s.length() - 1) <= k;
    }

    // i and j equivalent of two pointers i at the beginning and j at the end
    private static int isValidPalindrome(String s, int i, int j) {
        //Base case
        if (i == j)
            return 0;
        // Base case 2, only 2 letters remaining.
        if (i == j - 1) {
            return s.charAt(i) != s.charAt(j) ? 1 : 0;
        }
        //Return the precomputed value if exists.
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        // Case 1: Character at `i` equals character at `j`
        if (s.charAt(i) == s.charAt(j)) {
            return memo[i][j] = isValidPalindrome(s, i + 1, j - 1);
        }
        // Case 2: Character at `i` does not equal character at `j`.
        // Either delete character at `i` or delete character at `j`
        // and try to match the two pointers using recursion.
        // We need to take the minimum of the two results and add 1
        // representing the cost of deletion.
        // +1 because we already know that i and j are not equal we must delete one element, we need to remove either i or j
        return memo[i][j] = 1 + Math.min(isValidPalindrome(s, i + 1, j), isValidPalindrome(s, i, j - 1));
    }

    //Approach 2: Bottom Up
    static boolean isValidPalindromeBottomUp(String s, int k) {
        int memo[][] = new int[s.length()][s.length()];
        // Generate all combinations of `i` and `j` in the correct order.
        for (int i = s.length() - 2; i >= 0; i--)
            for (int j = i + 1; j < s.length(); j++) {
                // Case 1: Character at `i` equals character at `j`
                if (s.charAt(i) == s.charAt(j))
                    memo[i][j] = memo[i + 1][j - 1];
                    // Case 2: Character at `i` does not equal character at `j`.
                    // Either delete character at `i` or delete character at `j`
                    // and try to match the two pointers using recursion.
                    // We need to take the minimum of the two results and add 1
                    // representing the cost of deletion.
                else
                    memo[i][j] = 1 + Math.min(memo[i + 1][j], memo[i][j - 1]);
            }
        // Return true if the minimum cost to create a palindrome by only deleting the letters
        // is less than or equal to `k`.
        return memo[0][s.length() - 1] <= k;
    }

}
