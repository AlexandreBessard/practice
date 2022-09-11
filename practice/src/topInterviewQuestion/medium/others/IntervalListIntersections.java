package topInterviewQuestion.medium.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/explore/interview/card/facebook/57/others-3/3043/
public class IntervalListIntersections {

    public static void main(String[] args) {
        int[][] firstList = {
                {0, 2},
                {5, 10},
                {13, 23},
                {24, 25}
        };
        int[][] secondList = {
                {1, 5},
                {8, 12},
                {15, 24},
                {25, 26}
        };
        for(int[] el : intervalIntersection(firstList, secondList)) {
            System.out.println(Arrays.toString(el));
        }
    }

    /**
     * {@link patternsForCodingInterviews.mergeIntervals.IntervalsIntersection}
     * Exactly the same logic but without using Interval object
     */
    //See Example 1 picture for a better understanding, it is easy to understand link below:
    //https://leetcode.com/explore/interview/card/facebook/57/others-3/3043/
    /*
    Time: O(M + N) where N and M are the lengths of A and B respectively.
    Space: O(M + N) the maximum size of the answer.
     */
    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList<>();
        int idxFirstArray = 0;
        int idxSecondArray = 0;
        while(idxFirstArray < A.length && idxSecondArray < B.length) {
            if( (A[idxFirstArray][0] >= B[idxSecondArray][0]
                    && A[idxFirstArray][0] <= B[idxSecondArray][1]
            || (B[idxSecondArray][0] >= A[idxFirstArray][0]
                    && B[idxSecondArray][0] <= A[idxFirstArray][1])))
            {
                int start = Math.max(A[idxFirstArray][0], B[idxSecondArray][0]);
                int end = Math.min(A[idxFirstArray][1], B[idxSecondArray][1]);
                result.add(new int[]{start, end});
            }
            //Move next interval which is finishing first
            if(A[idxFirstArray][1] < B[idxSecondArray][1]) {
                idxFirstArray++;
            } else {
                idxSecondArray++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

}
