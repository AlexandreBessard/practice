package topInterviewQuestion.medium.arraysAndStrings;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/780/
public class LongestPalindromicSubstring {


    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }


    //Approach 1: Brut force
    /*
    Time complexity: O(n²)
     */
    static String longestPalindrome(String s) {
        if(s == null || s.length() < 1)
            return "";
        int start = 0;
        int end = 0;
        for(int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenters(s, i, i);
            //Palindrome can be in between 2 letters.
            int len2 = expandAroundCenters(s, i, i + 1);
            int len = Math.max(len1, len2);
            if(len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    private static int expandAroundCenters(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

}
