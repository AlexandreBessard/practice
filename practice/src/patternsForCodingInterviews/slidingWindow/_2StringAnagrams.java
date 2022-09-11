package patternsForCodingInterviews.slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541063154_7Unit
public class _2StringAnagrams {

    public static void main(String[] args) {
        System.out.println(findStringAnagrams("ppqp", "pq"));
        System.out.println(findStringAnagrams("abbcabc", "abc"));
        System.out.println(findStringAnagrams("abab", "ab"));
    }

    public static List<Integer> findStringAnagrams(String str, String pattern) {
        int windowStart = 0;
        int matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for(char chr : pattern.toCharArray()) {
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
        }
        List<Integer> resultIndices = new ArrayList<>();
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            //decrement the frequency of the matched character
            if(charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if(charFrequencyMap.get(rightChar) == 0) {
                    matched++;
                }
            }
            if(matched == charFrequencyMap.size()) { //We have found an anagram
                resultIndices.add(windowStart);
            }
            if(windowEnd >= pattern.length() - 1) { //Shrink the window
                //Decide what to do with the element after we shrank the window
                char leftChar = str.charAt(windowStart);
                windowStart++; //Increment windows start to shrink the window
                if(charFrequencyMap.containsKey(leftChar)) {
                    if(charFrequencyMap.get(leftChar) == 0) {
                        matched--; // before putting the character back, decrement the matched count
                    }
                    charFrequencyMap.put(rightChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }
        return resultIndices;
    }

}
