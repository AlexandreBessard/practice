package patternsForCodingInterviews.slidingWindow;

import java.util.HashMap;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541037657_4Unit
public class LongestSubstringWithSameLettersAfterReplacement {

    public static void main(String[] args) {
        System.out.println(findLength("aabccbb", 2));
        //System.out.println(findLength("abbcb", 1));
        //System.out.println(findLength("abccde", 1));
    }

    public static int findLength(String str, int k) {
        int windowStart = 0, maxLength = 0, maxRepeatLetterCount = 0;
        Map<Character, Integer> letterFrequencyMap = new HashMap<>();
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            letterFrequencyMap.put(rightChar,
                    letterFrequencyMap.getOrDefault(rightChar, 0) + 1);
            // we don't need to place the maxRepeatLetterCount under the below 'if', see the
            // explanation in the 'Solution' section above.
            maxRepeatLetterCount =
                    Math.max(maxRepeatLetterCount,
                            letterFrequencyMap.get(rightChar)
                    );
            // current window size is from windowStart to windowEnd, overall we have a letter
            // which is repeating 'maxRepeatLetterCount' times, this means we can have a window
            // which has one letter repeating 'maxRepeatLetterCount' times and the remaining
            // letters we should replace. If the remaining letters are more than 'k', it is the
            // time to shrink the window as we are not allowed to replace more than 'k' letters
            if (windowEnd - windowStart + 1 - maxRepeatLetterCount > k) {
                char leftChar = str.charAt(windowStart);
                letterFrequencyMap.put(leftChar,
                        letterFrequencyMap.get(leftChar) - 1
                );
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }
}
