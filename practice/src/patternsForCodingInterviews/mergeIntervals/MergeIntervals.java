package patternsForCodingInterviews.mergeIntervals;

import java.util.*;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743622133_21Unit
public class MergeIntervals {
/*
Given a list of intervals, merge all the overlapping intervals to produce a list that has only mutually exclusive intervals.
 */
    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        /*

        for (Interval interval : merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

         */
    }

    /*
    Time: O(N * log N)
    Space: O(N) caused by Timsort algo
     */
    public static List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() < 2) {
            return intervals;
        }
        //Sort the intervals by start time
        //O(N * log N)
        intervals.sort((a, b) -> a.start - b.start); //This implementation is a stable, adaptive, iterative mergesort
        List<Interval> mergedIntervals = new LinkedList<>();
        Iterator<Interval> intervalItr = intervals.iterator();
        Interval interval = intervalItr.next();
        int start = interval.start;
        int end = interval.end;
        while(intervalItr.hasNext()) {
            interval = intervalItr.next();
            if(interval.start <= end) { //Overlapping interval, adjust the 'end'
                end = Math.max(interval.end, end);
            } else { // non-overlapping interval, add the previous interval and reset
                mergedIntervals.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        //Add the latest interval
        mergedIntervals.add(new Interval(start, end));
        return mergedIntervals;
    }
}
