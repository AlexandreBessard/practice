package topInterviewQuestion.facebook.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/explore/interview/card/facebook/57/others-3/3041/
public class PermutationInString {

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        var obj = new PermutationInString();
        System.out.println(obj.checkInclusion(s1, s2));
    }

    /**
     * {@link patternsForCodingInterviews.slidingWindow._1PermutationInAString}
     */
    //Sliding windows, same logic but optimized below using array
    /*
    Time: O(N)
    Space: O(1)
     */
    public boolean checkInclusion(String s1, String s2) {
        int ns = s1.length(), np = s2.length();
        if (ns < np)
            return checkInclusion(s2, s1);
        int [] pCount = new int[26];
        int [] sCount = new int[26];
        // build reference array using string p
        for (char ch : s2.toCharArray()) {
            pCount[ch - 'a']++;
        }
        // sliding window on the string s
        for (int i = 0; i < ns; ++i) {
            // add one more letter
            // on the right side of the window
            sCount[s1.charAt(i) - 'a']++;
            // remove one letter
            // from the left side of the window
            if (i >= np) {
                sCount[s1.charAt(i - np) - 'a']--;
            }
            // compare array in the sliding window
            // with the reference array
            if (Arrays.equals(pCount, sCount)) {
                return true;
            }
        }
        return false;
    }

}
