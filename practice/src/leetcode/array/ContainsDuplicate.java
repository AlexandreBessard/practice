package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/578/
public class ContainsDuplicate {
/*
Given an integer array nums, return true if any value appears at least twice in the array,
and return false if every element is distinct.
 */
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(containsDuplicateHashTable(nums));
        System.out.println(containsDuplicateHashTable(new int[]{1, 2, 3, 4}));
    }


    //Approach 3: HashTable
    /*
    Time complexity: O(n) (search and insert takes constant time for set)
    Space complexity: O(n)
     */
    static boolean containsDuplicateHashTable(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int x : nums) {
            if(set.contains(x)) { //true if we have a duplicate
                return true;
            }
            set.add(x); //We do not have a duplicate
        }
        return false;
    }

    //Approach 2: Sorting
    /*
    Time complexity: O(n log n)
    Space complexity: O(1)
     */
    static boolean containsDuplicateSorting(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i + 1])
                return true;
        }
        return false;
    }

    //Approach 1: Naive linear Search
    /*
    Time complexity: O(n²)
    Space complexity: O(1)
     */
    static boolean containsDuplicate(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] == nums[j])
                    return true;
            }
        }
        return false;
    }

}
