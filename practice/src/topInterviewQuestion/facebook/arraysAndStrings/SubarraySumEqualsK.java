package topInterviewQuestion.facebook.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3019/
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 3;
        var obj = new SubarraySumEqualsK();
        System.out.println(obj.subarraySum(nums, k));
        System.out.println(obj.subarraySumHashMap(nums, k));

    }


    //Approach 4: Using Hash-map
    /*
    Time: O(n) -> entire nums array is traversed only once.
    Space: O(n) to store the HashMap.
     */
    public int subarraySumHashMap(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
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
