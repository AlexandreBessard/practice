package patternsForCodingInterviews.subsets;

import java.util.*;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744061506_69Unit
public class Permutations {

    public static void main(String[] args) {
        List<List<Integer>> result = findPermutations(new int[] { 1, 3, 5 });
        //List<List<Integer>> result2 = generatePermutationsRecursive(new int[]{1, 3, 5});
        System.out.println("Here are all the permutations: Iterative " + result + "\n");
        //System.out.println("Here are all the permutations: Recursive " + result2);

        List<List<Integer>> resultBacktracking  = subsetsWithDup(new int[]{1, 1, 3});
        for(List<Integer> l : resultBacktracking) {
            System.out.println(l);
        }
    }

    //https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    //Approach 1: BackTracking Recursive (Subsets 2 containing duplicates)
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        Arrays.sort(nums); //Must be sorted
        backtrack(output, new ArrayList<>(), nums, 0);
        return output;
    }

    private static void backtrack(List<List<Integer>> output,
                                  List<Integer> tempList, int [] nums, int start) {
        output.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(output, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /*
    Iterative, like a BFS
    O(N)
    Space: O(N * N)
     */
    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> permutationsQueue = new LinkedList<>();
        permutationsQueue.add(new ArrayList<>());
        for (int currentNumber : nums) { // Loop through each number
            // we will take all existing permutations and add the current number to create
            // new permutations
            int n = permutationsQueue.size();
            for (int i = 0; i < n; i++) { //Loop through each permutation from the queue
                List<Integer> oldPermutation = permutationsQueue.poll();
                // create a new permutation by adding the current number at every position
                for (int j = 0; j <= oldPermutation.size(); j++) {
                    List<Integer> newPermutation = new ArrayList<Integer>(oldPermutation);
                    newPermutation.add(j, currentNumber); //Add element in each index
                    if (newPermutation.size() == nums.length)
                        result.add(newPermutation);
                    else
                        permutationsQueue.add(newPermutation);
                }
            }
        }
        return result;
    }

    /*
    Recursive
    Time: O(N∗N!)
    Space: O(N∗N!)
     */
    public static List<List<Integer>> generatePermutationsRecursive(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generatePermutationsRecursive(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private static void generatePermutationsRecursive(int[] nums, int index,
                                                      List<Integer> currentPermutation, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(currentPermutation);
        } else {
            // create a new permutation by adding the current number at every position
            for (int i = 0; i <= currentPermutation.size(); i++) {
                List<Integer> newPermutation = new ArrayList<Integer>(currentPermutation);
                newPermutation.add(i, nums[index]);
                generatePermutationsRecursive(nums, index + 1, newPermutation, result);
            }
        }
    }

}
