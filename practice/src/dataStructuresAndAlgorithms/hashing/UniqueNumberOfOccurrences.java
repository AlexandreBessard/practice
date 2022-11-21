package dataStructuresAndAlgorithms.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/unique-number-of-occurrences/
public class UniqueNumberOfOccurrences {
    /*
    Given an array of integers arr, return true if the number of occurrences of each value in the array is unique, or false otherwise.
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 1, 1, 3};
        int[] nums2 = {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};
        System.out.println(uniqueOccurrences(nums));
    }

    /*
    Time: O(n)
    Space: O(n)
     */
    static boolean uniqueOccurrences(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for(int num : nums) { //Count each element
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        Set<Integer> set = new HashSet<>();
        for(int freq : count.values()) {
            // If this set already contains the element, the call leaves the set unchanged and returns false else return true
            if(!set.add(freq)) { //Put freq on the set. if it is contained, return false
                return false;
            }
        }
        return true;
    }


}
