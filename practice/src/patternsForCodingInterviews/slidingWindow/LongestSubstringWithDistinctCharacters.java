package patternsForCodingInterviews.slidingWindow;

import java.util.HashMap;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541027921_3Unit
public class LongestSubstringWithDistinctCharacters {

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: "
                + findLength("aabccbb"));
        System.out.println("Length of the longest substring: "
                + findLength("abbbb"));
        System.out.println("Length of the longest substring: "
                + findLength("abccde"));
    }

    public static int findLength(String str) {
        int windowStart = 0;
        int maxLength = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        //Try to extend the range [windowStart, windowEnd]
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            // if the map already contains the 'rightChar', shrink the window from the
            // beginning so that we have only one occurrence of 'rightChar'
            if(charIndexMap.containsKey(rightChar)) {
                // this is tricky; in the current window, we will not have any 'rightChar' after
                // its previous index and if 'windowStart' is already ahead of the last index of
                // 'rightChar', we'll keep 'windowStart'
                windowStart = Math.max(
                        windowStart, charIndexMap.get(rightChar) + 1
                );
            }
            //Insert the 'rightChar' into the map
            charIndexMap.put(rightChar, windowEnd);
            //remember the maximum length so far
            maxLength = Math.max(maxLength, (windowEnd - windowStart) + 1);
        }
        return maxLength;
    }
}
