package topInterviewQuestion.medium.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/779/
public class LongestSubstringWithoutRepeatingCharacter {

    public static void main(String[] args) {
        String s = "pwwkew";
        //Output: 3
        lengthOfLongestSubstringSlidingWindowOptimized(s);
    }

    //Approach 3: Sliding Window Optimized
    static int lengthOfLongestSubstringSlidingWindowOptimized(String s) { // pwwkew
        int n = s.length();
        int ans = 0;
        //Integer inside map represents current index of j
        Map<Character, Integer> map = new HashMap<>();
        for(int j = 0, i = 0; j < n; j++) {
            if(map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

        //Approach 2: Sliding Window
    /*
    Time complexity: O(N) in worst case scenario like 'AAAAAAAAAAAB'
    Space compelexity:O(1)
    -> Given a string s, find the length of the longest substring without repeating characters.
    Input: s = "pwwkew"
    Output: 3
     */
    static int lengthOfLongestSubstringSlidingWindow(String s) { // pwwkew
        int[] chars = new int[128];
        int left = 0;
        int right = 0;
        int res = 0;
        while(right < s.length()) {
            char r = s.charAt(right);
            chars[r]++;
            while(chars[r] > 1) {
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    //Approach 1: Brut force
    /*
    Time complexity: O(n3)
    Space complexity: O(k)
     */
    static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(checkRepetitions(s, i, j)) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }
    private static boolean checkRepetitions(String s,
                                            int start,
                                            int end)
    {
        int[] chars = new int[128]; //Contains all characters (not only letter)
        for(int i = start; i <= end; i++) {
            char c = s.charAt(i);
            chars[c]++;
            if(chars[c] > 1)
                return false;
        }
        return true;
    }

}
