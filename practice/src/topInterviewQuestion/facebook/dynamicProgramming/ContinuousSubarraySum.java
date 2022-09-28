package topInterviewQuestion.facebook.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/facebook/55/dynamic-programming-3/3038/
public class ContinuousSubarraySum {

    public static void main(String[] args) {
        int[] nums1 = {23, 2, 4, 6, 7};
        int[] nums2 = {23, 2, 6, 4, 7};
        int[] nums3 = {5, 0, 0, 0};
        int k3 = 3;
        int k2 = 13;
        int k = 6;
        System.out.println(checkSubarraySum(nums3, k3));
    }

    //HashMap approach
    /*
    Time: O(nums.length) -> O(n)
    Space: O(mint{nums.length, k})
     */
    static boolean checkSubarraySum(int[] nums, int k) {
        // initialize the hash map with index 0 for sum 0
        Map<Integer, Integer> hashMap = new HashMap<>(Map.of(0, 0));
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // if the remainder sum % k occurs for the first time
            if (!hashMap.containsKey(sum % k)) //same key means there are some values such that their sum is divisible by k.
                hashMap.put(sum % k, i + 1);
                // if the subarray size is at least two
            //If same keyValue (sum%k) is repeating then there are some values such that their sum is divisible by k
            //If map already contains that value, check if (currIndex - map.get(sum) > 1 because of size constraint
            else if (hashMap.get(sum % k) < i) //Use [5, 0, 0] for better understanding
                return true;
        }
        return false;
    }
}
