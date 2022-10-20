package topInterviewQuestion.top100likedQuestions;
//https://leetcode.com/problems/palindromic-substrings/
public class PalindromicSubstrings {
//A palindrome is a sequence of characters that read the same, forwards and backwards
    public static void main(String[] args) {
        String s = "abc";
        String s1 = "kayak";
        System.out.println(countSubstrings(s1));
    }

    /*
    Time: O(n²)
    Space: O(1)
     */
    static int countSubstrings(String s) {
        int count = 0;
        if(s == null || s.length() == 0) return 0;
        for(int i = 0; i < s.length(); i++) {
            count += expandPalindrome(s, i, i); //odd length
            count += expandPalindrome(s, i, i + 1);
        }
        return count;
    }
    private static int expandPalindrome(String s, int left, int right) {
        int count = 0;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

}
