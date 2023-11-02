package leetcode.topinterview150.intervals;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    //https://leetcode.com/problems/summary-ranges/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 4, 5, 7};
        System.out.println(summaryRangesRefactored(nums));
        nums = new int[]{0, 2, 3, 4, 6, 8, 9};
        System.out.println(summaryRangesRefactored(nums));
    }

    /*
    Time: O(n)
    Space: O(m) where m is the distinct ranges in the input array
     */
    static List<String> summaryRangesRefactored(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        // initialise range
        int start = nums[0];
        int end = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // check the previous element with the latest current range element.
            if (nums[i] == end + 1) { // true means we are still in the range.
                end = nums[i];
            } else { // we found an end range which needs to added to the List
                if (start == end) { // true means we only have 1 element in that range
                    result.add(Integer.toString(start));
                } else {
                    // one interval
                    result.add(start + "->" + end);
                }
                // Initialize the new interval
                start = end = nums[i];
            }
        }
        if (start == end) { // true means we have only one element
            result.add(Integer.toString(start));
        } else {
            result.add(start + "->" + end);
        }
        return result;
    }

}
