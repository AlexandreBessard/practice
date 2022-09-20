package topInterviewQuestion.facebook.arraysAndStrings;

//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/289/
public class ValidPalindrome2 {

    //Given a string s, return true if the s can be palindrome after deleting at most one character from it.
    public static void main(String[] args) {
        String s = "abca";
        String s1 = "abcqba";
        String s2 = "abbbbbbbbbbc";
        var obj = new ValidPalindrome2();
        //Output true
        System.out.println(obj.validPalindrome(s1));
    }

    //Approach 1: Two pointers
    //Time: O(N)
    private boolean checkPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            // Found a mismatched pair - try both deletions
            //do not match, we must spend our deletion on one of these characters
            if (s.charAt(i) != s.charAt(j)) {
                return (checkPalindrome(s, i, j - 1) || checkPalindrome(s, i + 1, j));
            }
            i++;
            j--;
        }
        return true;
    }


}
