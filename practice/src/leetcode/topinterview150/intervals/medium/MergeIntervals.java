package leetcode.topinterview150.intervals.medium;

import leetcode.topinterview150.intervals.Interval;

import java.util.*;


public class MergeIntervals {

    // https://leetcode.com/problems/merge-intervals/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        merge(Interval.convertArrayToList(intervals))
                .forEach(interval -> System.out.print(interval.start + " -> " + interval.end + ", "));
        System.out.println();
        intervals = new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        for (int[] i : merge(intervals)) {
            if (i == null) {
                break;
            }
            System.out.print(i[0] + " -> " + i[1] + ", ");
        }
    }

    /*
    Time: O(n log n)
    Space: O(n)
     */
    static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < 2) {
            return intervals;
        }
        // sort in ascending order based on their start
        // Time complexity: O(n log n)
        Collections.sort(intervals, (a, b) -> a.start - b.start);

        List<Interval> result = new ArrayList<>();
        // Use as a pointer
        Iterator<Interval> iterator = intervals.iterator();
        // Get the first Interval
        Interval interval = iterator.next();
        int prevStart = interval.start;
        int prevEnd = interval.end;
        // Get the next interval till the end
        while (iterator.hasNext()) {
            Interval currentInterval = iterator.next();
            if (currentInterval.start <= prevEnd) {
                prevEnd = Math.max(prevEnd, currentInterval.end);
            } else {
                // merge (process) the previous interval
                result.add(new Interval(prevStart, prevEnd));
                // new interval without being merged
                prevStart = currentInterval.start;
                prevEnd = currentInterval.end;
            }
        }
        // merge the last interval
        result.add(new Interval(prevStart, prevEnd));
        return result;
    }

    /*
    Same logic as above
    Time: O(n)
    Space: O(n)
     */
    static int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }
        int[][] result = new int[intervals.length][];
        // we assume that the interval are sorted by start
        int[] interval = intervals[0];
        int prevStart = interval[0];
        int prevEnd = interval[1];
        int idx = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            if (currentInterval[0] <= prevEnd) {
                prevEnd = Math.max(prevEnd, currentInterval[1]);
            } else {
                result[idx++] = new int[]{prevStart, prevEnd};
                prevStart = currentInterval[0];
                prevEnd = currentInterval[1];
            }
        }
        result[idx] = new int[]{prevStart, prevEnd};
        return result;
    }
}
