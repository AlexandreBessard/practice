package patternsForCodingInterviews.slidingWindow;

import java.util.HashMap;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541068682_8Unit
public class _3SmallestWindowContainingSubstring {

    public static void main(String[] args) {
        System.out.println(findSubstring("aabdec", "abc"));
        System.out.println(findSubstring("aabdec", "abac"));
        System.out.println(findSubstring("abdbca", "abc"));
        System.out.println(findSubstring("adcad", "abc"));
    }

    public static String findSubstring(String str, String pattern) {
        int windowStart = 0;
        int matched = 0;
        int minLength = str.length() + 1;
        int subStrStart = 0;
        Map<Character, Integer> charFrequency = new HashMap<>();
        for(char chr : pattern.toCharArray()) {
            charFrequency.put(chr, charFrequency.getOrDefault(chr, 0) + 1);
        }
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if(charFrequency.containsKey(rightChar)) {
                charFrequency.put(rightChar, charFrequency.get(rightChar) - 1);
                if(charFrequency.get(rightChar) == 0) { //Count every matching of a character
                    matched++;
                }
            }
            //Shrink the window if we can, finish as soon as we remove a matched character
            while(matched == pattern.length()) {
                if(minLength > windowEnd - windowStart + 1) { //get min length of the smallest substring
                    minLength = windowEnd - windowStart + 1;
                    subStrStart = windowStart;
                }
                char leftChar = str.charAt(windowStart++);
                if(charFrequency.containsKey(leftChar)) {
                    if(charFrequency.get(leftChar) == 0){
                        matched--;
                    }
                    charFrequency.put(leftChar, charFrequency.get(leftChar) + 1);
                }
            }
        }
        return minLength > str.length() ? "" : str.substring(subStrStart, subStrStart + minLength);
    }

}
