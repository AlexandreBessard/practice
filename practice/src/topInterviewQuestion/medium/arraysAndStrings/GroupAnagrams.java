package topInterviewQuestion.medium.arraysAndStrings;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/778/
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        //Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
        /*
        An Anagram is a word or phrase formed by rearranging the letters of a
        different word or phrase, typically using all the original letters exactly once.
         */

    }

    //Approach 2: Categorize by Count
    /*
    Time complexity: O(NK), we go through each strs element  and count every each string.
    Space complexity: O(NK), Total info contained in ans (Map)
     */
    static List<List<String>> groupAnagramsByCount(String[] strs) {
        if(strs.length == 0)
            return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        int[] count = new int[26];
        for(String s : strs) {
            Arrays.fill(count, 0);
            for(char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            var sb = new StringBuilder();
            for(int i = 0; i < 26; i++) {
                sb.append('#').append(count[i]);
            }
            String key = sb.toString();
            if(!ans.containsKey(key)) {
                ans.put(key, new ArrayList<>());
            }
            ans.get(key).add(sb.toString());
        }
        return new ArrayList<>(ans.values());
    }


    //Approach 1: Categorize by sorted String
    /*
    Time complexity: O(NK log K) where N is the length of strs
    K is max length of string strs, sort each string in O(K log k) time.
    Space complexity: 0(NK) total info stored in ans (Map)
     */
    static List<List<String>> groupAnagrams(String[] strs) { //"eat","tea","tan","ate","nat","bat"
        if(strs.length == 0)
            return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        for(String s : strs) {
            char[] ca  = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if(!ans.containsKey(key))
                ans.put(key, new ArrayList());
            ans.get(key).add(Arrays.toString(ca));
        }
        return new ArrayList<>(ans.values());
    }
}
