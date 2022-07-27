package topInterviewQuestion.medium.sortingAndSearching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/803/
public class MergeIntervals {

    public static void main(String[] args) {
        int[][] nums = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        for(int[] el : merge(nums)) {
            System.out.println();
            for(int e : el) {
                System.out.print(e + ", ");
            }
        }
    }

    //Approach 2: Sorting
    /*
    Time complexity: O(n log n) caused by Arrays sort
    Space complexity: O(log n) caused by sorting itself or O(n)..
     */
    static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        LinkedList<int[]> merged = new LinkedList<>();
        for(int[] interval : intervals) {
            if(merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                //merge
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


}
