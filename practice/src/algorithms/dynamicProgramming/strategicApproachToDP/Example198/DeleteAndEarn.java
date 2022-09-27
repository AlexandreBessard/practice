package algorithms.dynamicProgramming.strategicApproachToDP.Example198;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4147/
public class DeleteAndEarn {

    public static void main(String[] args) {
        int[] nums = {3, 4, 2};
        int[] nums2 = {2, 2, 3, 3, 3, 4};
        System.out.println(deleteAndEarnTopDown(nums2));
        System.out.println(deleteAndEarnBottomUp(nums2));
    }

    static Map<Integer, Integer> points = new HashMap<>();
    static Map<Integer, Integer> cache = new HashMap<>();
    //Time: O(N + K) we will solve k unique sub-problems so, this recursion will cost O(k)O(k) time and Iterate for points -> O(n)
    //Space: O(N + k) -> points & recursion
    static int deleteAndEarnTopDown(int[] nums) {
        int maxNumber = 0;
        //Precompute how many points we gain from taking an element
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }
        return maxPoints(maxNumber);
    }

    private static int maxPoints(int num) {
        //Base case
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return points.getOrDefault(num, 0);
        }
        //Get from cache
        if (cache.containsKey(num)) {
            return cache.get(num);
        }
        //Recurrence
        int gain = points.getOrDefault(num, 0); //gain equals to x times the number of times x occurs in num
        cache.put(num, Math.max(
                //We choose not to take x so 'gain' is not added
                maxPoints(num - 1), //Because we start with the largest, we can not take x - 1
                //We chose to take x (we can not take x - 1) ->  you must delete every element equal to nums[i] - 1
                maxPoints(num - 2) + gain
        ));
        return cache.get(num);
    }

    //Bottom Up
    /*
    Time: O(N + k)
    Space: O(N + k)
     */
    static int deleteAndEarnBottomUp(int[] nums) {
        HashMap<Integer, Integer> points = new HashMap<>();
        int maxNumber = 0;
        // Precompute how many points we gain from taking an element
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }
        // Declare our array along with base cases
        int[] maxPoints = new int[maxNumber + 1];
        maxPoints[1] = points.getOrDefault(1, 0); //Take of 1 because can not go below (see top down base case)
        for (int i = 2; i  < maxPoints.length; i++) {
            // Apply recurrence relation
            int gain = points.getOrDefault(i, 0);
            maxPoints[i] = Math.max(maxPoints[i - 1], maxPoints[i - 2] + gain);
        }
        return maxPoints[maxNumber];
    }
}
