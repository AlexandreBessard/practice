package leetcode.array;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3019/
public class SubarraySumEqualsK {
/*
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.
Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2
 */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 3;
        var obj = new SubarraySumEqualsK();
        System.out.println(obj.subarraySum(nums, k));
        System.out.println(obj.subarraySumHashMap(nums, k));
        System.out.println(subarraySumWithoutSpace(nums, k));
    }

    //Approach 3: Without Space:
    /*
    Time: O(n²)
    Space: O(1)
     */
    public static int subarraySumWithoutSpace(int[] nums, int k) {
        int count = 0;
        for(int left = 0; left < nums.length; left++) {
            int sum = 0;
            for(int right = left; right < nums.length; right++) {
                sum += nums[right]; //count each element from left included to right (until the end)
                if(sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    //Approach 4: Using Hash-map
    /*
    Time: O(n) -> entire nums array is traversed only once.
    Space: O(n) to store the HashMap.
     */
    public int subarraySumHashMap(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        //Key: PreSum, Value: count
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); //Key represents the (sum - k) and the value is the count (+1)
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }


    //Approach 1: Brut force
    //Time: O(n3)
    //Space: O(1)
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}
