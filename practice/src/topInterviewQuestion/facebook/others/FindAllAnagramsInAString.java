package topInterviewQuestion.facebook.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/explore/interview/card/facebook/57/others-3/3040/
public class FindAllAnagramsInAString {

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String s1 = "babacd";
        String p = "abc";
        System.out.println(findAnagrams(s1, p));
    }

    //Same pattern, see grokking interview

    /**
     * {@link  patternsForCodingInterviews.slidingWindow._2StringAnagrams}
     **/
    //Approach 2: Sliding window with array
    /*
    Hashmap is quite complex structure, with known performance issues in Java.
    Let's implement approach 1 using 26-elements array instead of hashmap:
     */
    //Time: O(N) -> N represents length of String 's'
    //Space: O(K) -> pCount & sCount contain K elements with 26 elements which can be considered O(1) space.
    public static List<Integer> findAnagrams(String s, String p) {
        int ns = s.length(), np = p.length();
        if (ns < np) return new ArrayList<>();

        int[] pCount = new int[26];
        int[] sCount = new int[26];
        // build reference array using string p
        for (char ch : p.toCharArray()) {
            pCount[ch - 'a']++;
        }
        List<Integer> output = new ArrayList<>();
        // sliding window on the string s
        for (int i = 0; i < ns; ++i) {
            // add one more letter
            // on the right side of the window
            sCount[s.charAt(i) - 'a']++;
            // remove one letter
            // from the left side of the window
            if (i >= np) {
                sCount[s.charAt(i - np) - 'a']--;
            }
            // compare array in the sliding window
            // with the reference array
            if (Arrays.equals(pCount, sCount)) {
                output.add(i - np + 1);
            }
        }
        return output;
    }

}
