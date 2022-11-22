package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/114/others/824/
public class MajorityElement {
/*
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2
 */
    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElementBrutForce(nums)+"\n");
        System.out.println("--> " + majorityElementHashMap(nums));
        //System.out.println(majorityElementSorting(nums));
        System.out.println("Divide and Conquer :"
                + majorityElementDivideAndConquer(nums));
        System.out.println(majorityElementBoyerMoore(nums));

    }

    //Approach 6: Boyer-Moore Voting Algorithm
    /*
    Time: O(n) -> Linear time
    Space: O(1)
     */
    static int majorityElementBoyerMoore(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate != null ? candidate : -1;
    }

    //Approach 5: Divide and conquer
    /*
    Time complexity: O(n log n)
    Space complexity: O(log n) caused by recursion.
     */
    static int majorityElementDivideAndConquer(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private static int majorityElementRec(int[] nums, int lo, int hi) {
        //base case
        if (lo == hi) {
            return nums[lo];
        }
        //Recurse left and right halves of this slice
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);
        //If two halves agree on the majority element, return it
        if (left == right) {
            return left;
        }
        //Otherwise count each element and return the winner
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    private static int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }


    //Approach 3: Sorting:
    /*
    Time complexity: O(n log n) caused by sorting method
    Space complexity: O(n) caused by sorting method, we spend linear additional space
    on a copy of nums and sort the copy instead.
     */
    static int majorityElementSorting(int[] nums) {
        //QuickSort implementation
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    //Approach 2: HashMap
    /*
    Time complexity: O(n) -> Iterate though array, insertion in the hashmap in
    constant time, overall: O(n)
    Space complexity: O(n) caused by the hashMap
     */
    static int majorityElementHashMap(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums); //count each numbers
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null
                    || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        return majorityEntry.getKey();
    }

    private static Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        return counts;
    }


    //Approach 1: Brut force
    /*
    Time complexity: O(n²) because two nested loops
    Space complexity: O(1)
     */
    static int majorityElementBrutForce(int[] nums) {
        int majorityCount = nums.length / 2;
        for (int num : nums) {
            int count = 0;
            for (int element : nums) {
                if (element == num) {
                    count += 1;
                }
            }
            if (count > majorityCount) {
                return num;
            }
        }
        return -1;
    }

}
