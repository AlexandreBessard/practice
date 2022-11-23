package leetcode.sortingAndSearching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/803/
public class MergeIntervals {
/*
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
    public static void main(String[] args) {
        int[][] nums = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        for(int[] el : mergeIntervals(nums)) {
            System.out.println();
            for(int e : el) {
                System.out.print(e + ", ");
            }
        }
    }

    //Grokking the coding interview, same logic:
    /** {@link patternsForCodingInterviews.mergeIntervals.MergeIntervals} */

    /*
    Time: O(N log N) caused by sorted algo
    Space: O(N) caused by merge sort algo
     */
    public static int[][] mergeIntervals(int[][] intervals) {
        if(intervals.length < 2) { //Must contains 2 elements minimum
            return intervals;
        }
        List<int[]> tempResult = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); //sort by start, (merge sort algo) time: O(nLogn), space: O(n)
        int prevStart = intervals[0][0];
        int prevEnd = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            //Get the current values in order to be compared with the previous value.
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];
            //Check the intervals with the previous and the current one
            if(currStart <= prevEnd) { //true if overlapping
                prevEnd = Math.max(currEnd, prevEnd);
            } else { // non-overlapping, add to the result
                tempResult.add(new int[]{prevStart, prevEnd});
                prevStart = currStart; //Next element
                prevEnd = currEnd; //Next element
            }
        }
        //Do not forget to Merge the latest interval
        tempResult.add(new int[]{prevStart, prevEnd});
        //Convert list to an array
        final int[][] finalResult = new int[tempResult.size()][]; //Initialize 2d array
        for(int i = 0; i < tempResult.size(); i++) {
            finalResult[i] = tempResult.get(i);
        }
        return finalResult;
    }

    //Approach 2: Sorting
    /*
    Time complexity: O(n log n) caused by Arrays sort
    Space complexity: O(log n) caused by sorting itself or O(n)..
    Dont even bother to look at 1st solution, 2nd one is better and more reliable during interviews
     */
    static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); //must be sorted by start, a and b represent an array 1 dimension
        LinkedList<int[]> merged = new LinkedList<>();
        for(int[] currInterval : intervals) {
            if(merged.isEmpty() || merged.getLast()[1] < currInterval[0]) {
                merged.add(currInterval);
            } else {
                //merge
                merged.getLast()[1] = Math.max(merged.getLast()[1], currInterval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


}
