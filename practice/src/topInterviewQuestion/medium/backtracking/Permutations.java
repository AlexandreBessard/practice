package topInterviewQuestion.medium.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/795/
public class Permutations {

    public static void main(String[] args) {
        permute(new int[]{1, 2, 3});
    }

    //Approach 1: Backtracking
    /*
    Time complexity: first level of the tree you have N options
    for each of the option, you have N-1 option
    each of these N-1 options you have another N-2 options.
    Putting them together -> N*(N-1)*(N-2).... = N!
    Space complexity: O(N!) since one has to keep N! solutions
     */
    static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new LinkedList<>();
        //Convert nums[] into a list
        List<Integer> num_lst = new ArrayList<>();
        for(int num : nums) {
            num_lst.add(num);
        }
        int n = nums.length;
        backtrack(n, num_lst, output, 0);
        return output;
    }
    private static void backtrack(int n,
                                  List<Integer> nums,
                                  List<List<Integer>> output,
                                  int first) //Represent the index
    {
        if(first == n) {
            output.add(new ArrayList<>(nums));
        }
        for(int i = first; i < n; i++) {
            Collections.swap(nums, first, i);
            backtrack(n, nums, output, first + 1);
            //backtrack
            Collections.swap(nums, first, i);
        }
    }

}
