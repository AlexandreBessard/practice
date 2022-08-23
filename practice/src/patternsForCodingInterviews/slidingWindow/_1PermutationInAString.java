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
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        // our goal is to match all the characters from the 'charFrequencyMap' with the
        // current window try to extend the range [windowStart, windowEnd]
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if(charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched characte
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if(charFrequencyMap.get(rightChar) == 0) { // character is completely matched
                    matched++;
                }
            }
            if(matched == charFrequencyMap.size()) {
                return true;
            }
            //Shrink window by one character
            if(windowEnd >= pattern.length() - 1) {
                char leftChar = str.charAt(windowStart++);
                //Check if permutation
                if(charFrequencyMap.containsKey(leftChar)) {
                    if(charFrequencyMap.get(leftChar) == 0) {
                        matched--; // before putting the char back, decrement the matched count
                    }
                    // put the character back for matching
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }
        return false;
    }

}
