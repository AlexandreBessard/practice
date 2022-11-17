package topInterviewQuestion.topInterviewQuestions.array;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int[] nums2 = {2, 7, 11, 15};
        int target = 6;
        int[] res = twoSumHashTableOnePass(nums2, 9);
        //Output: [1, 2] -> 2 + 4 == 6
        if(res != null) {
            for(int i : res) {
                System.out.print(i + ", ");
            }
        }
    }


    //Approach 3: One-pass Hash Table
    /*
    Time complexity: O(n)
    Space complexity: O(n)
     */
    static int[] twoSumHashTableOnePass(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }

    //Approach 2: Two pass HashTable
    /*
    Time complexity: O(n), hashtable reduce lookup to O(1)
    Space complexity: O(n)
     */
    static int[] twoSumHashTable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for(int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement)
                    && map.get(complement) != i) {
                return new int[] {i, map.get(complement)};
            }
        }
        return null;
    }

    //Approach 1: Brut force
    /*
    Time complexity: O(n²) loop through arrays
    Space complexity: O(1)
     */
    static int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] == target - nums[i])
                    return new int[] {i, j};
            }
        }
        return null;
    }


}
