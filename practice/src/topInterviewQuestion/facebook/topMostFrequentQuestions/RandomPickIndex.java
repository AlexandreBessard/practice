package topInterviewQuestion.facebook.topMostFrequentQuestions;

import java.util.*;

//https://leetcode.com/problems/random-pick-index/
public class RandomPickIndex {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3};
        var obj = new Solution(nums);
        System.out.println(obj.pick(3));
        System.out.println(obj.pick(1));
        System.out.println(obj.pick(1));
    }

    static class Solution {
        //Space: O(n)
        private static Map<Integer, List<Integer>> indices;
        private static Random random;
        Solution(int[] nums) {
            random = new Random();
            indices = new HashMap<>();
            for(int i = 0; i < nums.length; i++) {
                if(!indices.containsKey(nums[i])) {
                    indices.put(nums[i], new ArrayList<>());
                }
                indices.get(nums[i]).add(i);
            }
            System.out.println();
        }
        /*
        Picks a random index i from nums where nums[i] == target.
        If there are multiple valid i's, then each index should have an equal probability of returning.
         */
        //Approach 2: Caching result using hashmap
        //Time: O(1)
        public int pick(int target) {
            int l = indices.get(target).size();
            // pick an index at random
            return indices.get(target).get(random.nextInt(l));
        }
    }

}
