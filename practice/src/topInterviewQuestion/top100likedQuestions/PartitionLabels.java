package topInterviewQuestion.top100likedQuestions;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/partition-labels/
public class PartitionLabels {
    /*
    You are given a string s. We want to partition the string into as
    many parts as possible so that each letter appears in at most one part.

    Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
     */
//Partition so that each letter appears in at most one part.
    //Explanation: https://www.youtube.com/watch?v=B7m8UmZE-vw&ab_channel=NeetCode
    public static void main(String[] args) {
        String s = "ababcbacadefegde";
        String s2 = "ababcbacadefegdehijhklij";
        String s1 = "eccbbbbdec";
        System.out.println(partitionLabels(s));
        //System.out.println(partitionLabels(s));
    }

    //Greedy
    /*
    Time: O(N) where N is the length of string
    Space: O(1) to keep 26 characters
     */
    static List<Integer> partitionLabels(String s) {
        int[] last = new int[26]; //Can be done with hashmap : Map<Character, Integer> map = new HashMap(26);
        for(int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i; //Put the latest index seen in the array
        }
        int max = 0;
        int prevSubarray = 0; //Represents the previous subarray's index.
        List<Integer> ans = new ArrayList<>();
        for(int index = 0; index < s.length(); index++) {
            max = Math.max(max, last[s.charAt(index) - 'a']);
            if(index == max) {
                //Partition time
                ans.add(index - prevSubarray + 1);
                prevSubarray = index + 1;
            }
        }
        return ans;
    }

}
