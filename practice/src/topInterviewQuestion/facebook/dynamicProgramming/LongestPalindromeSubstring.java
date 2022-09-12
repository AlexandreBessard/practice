package topInterviewQuestion.facebook.dynamicProgramming;

//https://leetcode.com/explore/interview/card/facebook/55/dynamic-programming-3/3034/
public class LongestPalindromeSubstring {

    public static void main(String[] args) {
        String s1 = "forgeeksskeegfor";
        String s2 = "geekkeegfor";
        //Output : geeksskeeg
        System.out.println(longestPalindrome(s1));

        System.out.println("\nTwo Pointers :");
        System.out.println(longestPalTwoPointers(s2));
    }

    //Approach 3: Two Pointers:
    //https://leetcode.com/explore/interview/card/facebook/55/dynamic-programming-3/3034/discuss/151144/Bottom-up-DP-Two-pointers
    /*
   This approach takes O(n^2) time complexity, O(1) space complexity, where n is the length of s.
   Easiest to understand
     */
    static String longestPalTwoPointers(String str) {
        int n = str.length();
        int longest_palindrome_start = 0;
        int longest_palindrome_len = 1;
        for (int i = 0; i < n; i++) {
            int right = i;
            while (right < n && str.charAt(i) == str.charAt(right)) {
                right++;
            }
            int left = i - 1; //Left next element before ith element
            while (left >= 0 && right < n && str.charAt(left) == str.charAt(right)) {
                left--;
                right++;
            }
            int palindromeLength = right - left - 1;
            if (palindromeLength > longest_palindrome_len) {
                longest_palindrome_len = palindromeLength;
                longest_palindrome_start = left + 1;
            }
        }
        return str.substring(longest_palindrome_start, longest_palindrome_start + longest_palindrome_len);
    }

    static void printSubStr(String str, int low, int high) {
        System.out.println(str.substring(low, high + 1));
    }

    //Approach 1: Expand Around center
    /*
    Time: O(n²)
    Space: O(1)
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

}


