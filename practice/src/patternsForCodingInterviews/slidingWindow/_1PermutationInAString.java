package patternsForCodingInterviews.slidingWindow;

import java.util.HashMap;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541055153_6Unit
public class _1PermutationInAString {

    public static void main(String[] args) {
            /*
        System.out.println("Permutation exist: "
                + findPermutation("oidbcaf", "abc"));



        System.out.println(findPermutation("odicf", "dc"));
        System.out.println(findPermutation("ocidf", "dc"));


        System.out.println("Permutation exist: "
                + findPermutation("bcdxabcdy", "bcdyabcdx"));

         */
        //System.out.println(findPermutation("aaacb", "abc"));
        System.out.println(findPermutation("acaab", "abc"));

    }

    /*
    Time: O(N + M) N: number of characters in the input and pattern respectively.
    Space: O(M) hashMap
     */
    public static boolean findPermutation(String str, String pattern) {
        int windowStart = 0;
        int matched = 0;
        Map<Character, Integer> mapPattern = new HashMap<>();
        for (char chr : pattern.toCharArray())
            mapPattern.put(chr, mapPattern.getOrDefault(chr, 0) + 1);
        // our goal is to match all the characters from the 'charFrequencyMap' with the
        // current window try to extend the range [windowStart, windowEnd]
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if(mapPattern.containsKey(rightChar)) {
                // decrement the frequency of the matched characte
                mapPattern.put(rightChar, mapPattern.get(rightChar) - 1);
                if(mapPattern.get(rightChar) == 0) { // character is completely matched
                    matched++;
                }
            }
            if(matched == mapPattern.size()) {
                return true;
            }
            //Shrink window by one character
            if(windowEnd >= pattern.length() - 1) {
                char leftChar = str.charAt(windowStart++);
                //Check if permutation
                if(mapPattern.containsKey(leftChar)) {
                    if(mapPattern.get(leftChar) == 0) {
                        matched--; // before putting the char back, decrement the matched count
                    }
                    // put the character back for matching
                    mapPattern.put(leftChar, mapPattern.get(leftChar) + 1);
                }
            }
        }
        return false;
    }

}
