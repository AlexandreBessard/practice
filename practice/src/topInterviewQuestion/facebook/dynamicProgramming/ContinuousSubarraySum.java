package topInterviewQuestion.facebook.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/facebook/55/dynamic-programming-3/3038/
public class ContinuousSubarraySum {

    public static void main(String[] args) {
        int[] nums1 = {23, 2, 4, 6, 7};
        int[] nums2 = {23, 2, 6, 4, 7};
        int k2 = 13;
        int k = 6;
        System.out.println(checkSubarraySum(nums2, 6));
    }

    //HashMap approach
    static boolean checkSubarraySum(int[] nums, int k) {
        // initialize the hash map with index 0 for sum 0
        Map<Integer, Integer> hashMap = new HashMap<>(Map.of(0, 0));
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // if the remainder sum % k occurs for the first time
            if (!hashMap.containsKey(sum % k))
                hashMap.put(sum % k, i + 1);
                // if the subarray size is at least two
            else if (hashMap.get(sum % k) < i)
                return true;
        }
        return false;
    }


}
