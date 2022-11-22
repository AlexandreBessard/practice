package leetcode.array;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/contains-duplicate-ii/
public class ContainsDuplicateII {
    /*
    Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
     Input: nums = [1,2,3,1], k = 3
    Output: true
    Input: nums = [1,0,1,1], k = 1
    Output: true
    Input: nums = [1,2,3,1,2,3], k = 2
    Output: false
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(containsNearbyDuplicateHashTable(nums, 3));
        System.out.println("\n");
        nums = new int[]{1, 0, 1, 1};
        System.out.println(containsNearbyDuplicateHashTable(nums, 1));
        /*
        nums = new int[]{1, 0, 1, 1};
        System.out.println(containsNearbyDuplicate(nums, 1));
        System.out.println("\n");
        nums = new int[]{1, 2, 3, 1, 2, 3};
        System.out.println(containsNearbyDuplicate(nums, 2));
         */
    }

    //Approach 2: Hash table
    /*
    it is like a sliding window logic.
    Keep a sliding window of k elements using Hash Table.
    If in this range we have same num, return true.
     */
    public static boolean containsNearbyDuplicateHashTable(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if(set.size() > k) { //k indicate the range
                set.remove(nums[i - k]); //remove the kth first one from the set
            }
        }
        return false;
    }

    //Approach: Linear Search, Time Limit Exceeded
    /*
    Time: O(n min(k, n))
    Space: O(1)
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = Math.max(i - k, 0); j < i; j++) {
                System.out.println("start: " + j);
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

}
