package leetcode.topinterview150.twoPointers;

public class IsSubsequence {

    // https://leetcode.com/problems/is-subsequence/?envType=study-plan-v2&envId=top-interview-150
    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
        s = "axc";
        t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }

    /*
    Two pointers
    Time: O(n)
    Space: O(1)
     */
    static boolean isSubsequence(String s, String t) {
        // both pointers start by the beginning, one pointer for each input
        int idxS = 0;
        int idxT = 0;
        while (idxS < s.length() && idxT < t.length()) {
            // true if letters match between the 2 inputs string
            if (s.charAt(idxS) == t.charAt(idxT)) {
                // move forward
                idxS++;
            }
            idxT++;
        }
        return idxS == s.length();
    }
}
