package patternsForCodingInterviews.subsets;

import java.util.ArrayList;
import java.util.List;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744042826_67Unit
public class Subsets {

    public static void main(String[] args) {
        List<List<Integer>> result = findSubsets(new int[]{1, 3});
        System.out.println("Here is the list of subsets: " + result);

        result = findSubsets(new int[]{1, 5, 3});
        System.out.println("Here is the list of subsets: " + result);
    }

    /*
    Time: O(2n), each step, the number of subsets double where N total number of elements
    in the input O(N * 2N)
    Space: O(N)
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        //Start by adding the empty subset
        subsets.add(new ArrayList<>());
        for (int currentNumber : nums) {
            //We will take all existing subsets and insert the current number in them to create
            //new subsets
            int n = subsets.size();
            for (int i = 0; i < n; i++) {
                //Create a new subset from the existing subset and insert the current
                // element to it
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(currentNumber);
                subsets.add(set);
            }
        }
        return subsets;
    }

}
