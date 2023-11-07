package leetcode.topinterview150.arrayString.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    //https://leetcode.com/problems/majority-element/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElementHashMap(nums));
        nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElementHashMapSimplified(nums));
        nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        //System.out.println(majorityElementHashMapMooreVotingAlgorithm(nums));
        //at least two instances of majority candidate will be adjacent to each other
        nums = new int[]{3, 2, 3, 2, 1, 2}; // will not work using this array
        nums = new int[]{3, 3, 2, 1, 2};
        System.out.println(majorityElementHashMapMooreVotingAlgorithm(nums));
    }

    /*
    Moore Voting Algorithm
    Time: O(n)
    Space: O(1)
     */
    static int majorityElementHashMapMooreVotingAlgorithm(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            // become 0, change the candidate to the current element
            if (count == 0) {
                candidate = num;
            }
            // find the same element, increase by 1
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        // Majority element
        return candidate;
    }

    /*
    HashMap simplified based on majorityElementHashMap()
    Time: O(n)
    Space: O(n)
     */
    static int majorityElementHashMapSimplified(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int middle = nums.length / 2;
        for (int num : nums) { // O(n)
            map.put(num, map.getOrDefault(num, 0) + 1);
            // return as soon as possible element superior to the middle.
            // You may assume that the majority element always exists in the array.
            if (map.get(num) > middle) {
                return num;
            }
        }

        return 0;
    }

    /*
    HashMap
    Time: O(n) + O(n) simplifies to O(n)
    Space: O(n) used by the hashmap
     */
    static int majorityElementHashMap(int[] nums) {
        int numsLength = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        // Count the number of element
        for (int i = 0; i < numsLength; i++) { // O(n)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int middle = numsLength / 2; // middle
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) { // O(n)
            if (entry.getValue() > middle) {
                return entry.getKey();
            }
        }
        return 0;
    }


    /*
    Time: O(n log n) caused by sorting algorithm
    Space: 0(1), for 'int' primitive, uses a variant of quicksort
    which does not require additional space.
     */
    static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        //This is because the majority element occurs more than n/2 times,
        // and when the array is sorted, it will occupy the middle position.
        return nums[n / 2];
    }
}
