package topInterviewQuestion.medium.backtracking;

import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/796/
public class Subsets {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> list = subsetsBacktracking(nums);
        System.out.println(list);
    }

    //Approach 2: Backtracking
    /*

     */
    private static List<List<Integer>> output = new ArrayList<>();
    private static int n, k;
    private static void backtrackBacktracking(int first, ArrayList<Integer> curr, int[] nums) {
        // if the combination is done
        if (curr.size() == k) {
            output.add(new ArrayList(curr));
            return;
        }
        for (int i = first; i < n; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrackBacktracking(i + 1, curr, nums);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }

    static List<List<Integer>> subsetsBacktracking(int[] nums) {
        n = nums.length;
        for (k = 0; k < n + 1; ++k) {
            System.out.println("k " + k);
            backtrackBacktracking(0, new ArrayList<Integer>(), nums);
        }
        return output;
    }


    //Approach 1: Cascading
    /*
    Time complexity: O(N x 2n) to generate all subsets and then copy them into output list.
    Space complexity: O(N x 2n) The number of solutions for subsets multiplied by the number N of elements to keep
    for each subset.
     */
    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<>());
        for(int num: nums) {
            List<List<Integer>> newSubsets = new ArrayList<>();
            for(List<Integer> curr : output) {
                newSubsets.add(new ArrayList<>(curr) {
                    {
                        add(num);
                    }
                });
            }
            for(List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }
}
