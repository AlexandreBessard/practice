package topInterviewQuestion.amazon.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/902/
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String s1 = "CODEBANC";

        String t = "ABC";
        //Output: BANC
        //Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
        System.out.println(minWindow(s1, t));
        System.out.println(minWindowGrokkingCodingInterview(s1, t));
    }


    //Same as Approach 1 but using grokking coding interview pattern
    //https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541068682_8Unit
    public static String minWindowGrokkingCodingInterview(String s, String t) {
        int windowStart = 0;
        int minLengthRequired = s.length() + 1;
        int minLength = Integer.MAX_VALUE;
        int matched = 0;
        int subStrStart = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(char chr : t.toCharArray()) {
            map.put(chr, map.getOrDefault(chr, 0) + 1);
        }
        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char chr = s.charAt(windowEnd);
            if(map.containsKey(chr)) {
                map.put(chr, map.get(chr) - 1);
                if(map.get(chr) >= 0) { //Count every matched
                    matched++;
                }
            }
            //Shrink window
            while(matched == t.length()) {
                //Get the minimum actual length
                if(minLength > windowEnd - windowStart + 1) {
                    minLength = windowEnd - windowStart + 1;
                    subStrStart = windowStart;
                }
                char chrLeft = s.charAt(windowStart++);
                if(map.containsKey(chrLeft)) {
                    if(map.get(chrLeft) == 0) {
                        matched--;
                    }
                    map.put(chrLeft, map.get(chrLeft) + 1);
                }
            }
        }
        return minLength > s.length() ? "" : s.substring(subStrStart, subStrStart + minLength);
    }



    //Approach 1: Sliding Window (same logic as above)
    /*
    Time: O(S + T) where S and T represent the lengths of Strings S and T
    Space: O(S + T)
     */
    public static String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> dictT = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            char chr = t.charAt(i);
            dictT.put(chr, dictT.getOrDefault(chr, 0) + 1);
        }
        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();
        // Left and Right pointer
        int l = 0, r = 0;
        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;
        // Dictionary which keeps a count of all the unique characters in the current window.
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();
        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};
        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);
            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.get(c) != null && (windowCounts.get(c).intValue() == dictT.get(c).intValue())) {
                formed++;
            }
            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s.charAt(l);
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);

                if (dictT.get(c) != null && (windowCounts.get(c) < dictT.get(c))) {
                    formed--;
                }
                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }
            // Keep expanding the window once we are done contracting.
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

}
