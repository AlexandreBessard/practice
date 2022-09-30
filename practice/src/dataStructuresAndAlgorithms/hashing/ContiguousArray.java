package dataStructuresAndAlgorithms.hashing;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/contiguous-array/
public class ContiguousArray {
    /*
    Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
     */
    public static void main(String[] args) {
        int[] nums = {0, 1, 0};
        int[] nums2 = {0, 1, 0, 0, 1, 1, 0};
        System.out.println(findMaxLength(nums));
        System.out.println(findMaxLengthHashMap(nums2));
    }

    //Approach 3: Hasing -> HashMap
    /*
    Time: O(n) The entire array is traversed only once
    Space: O(n) Max size of the Hashmap
     */
    static int findMaxLengthHashMap(int[] nums) {
        //Key: count, Value: index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //imply sum is 0 before we start the array summation. Array begins at 0
        int maxlen = 0, count = 0;
        for(int i = 0; i < nums.length; i++) {
            count += (nums[i] == 1 ? 1 : -1); //If 1 add 1 else subtract -1
            if(map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }



        //Brut force
    /*
    Time: (n²) we consider every possbile subarray by traversing over the complete array for every start  point possible
    Space: O(1)
     */
    static int findMaxLength(int[] nums) {
        int maxLength = Integer.MIN_VALUE;
        for(int start = 0; start < nums.length; start++) { //Loop through each subarray
            int zeroes = 0, ones = 0;
            for(int end = start; end <  nums.length; end++) {
                if(nums[end] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
                if(zeroes == ones) {
                    maxLength = Math.max(maxLength, end - start + 1);
                }
            }
        }
        return maxLength;
    }

}
