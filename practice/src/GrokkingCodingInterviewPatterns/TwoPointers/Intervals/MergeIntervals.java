package GrokkingCodingInterviewPatterns.TwoPointers.Intervals;

import java.util.*;

public class MergeIntervals {

    // https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63923e6de4cb724ea719007a

    public static void main(String[] args) {
        var interval1 = new Interval(1, 4);
        var interval2 = new Interval(2, 5);
        var interval3 = new Interval(7, 9);
        List<Interval> intervalList = new ArrayList<>(Arrays.asList(interval1, interval2, interval3));
        List<Interval> mergedIntervals = merge(intervalList);
        for (Interval interval : mergedIntervals) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }
    }

    static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < 2) {
            return intervals;
        }
        // sort interval by the start date
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        List<Interval> mergedIntervals = new ArrayList<>();
        Interval previousInterval = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval currentInterval = intervals.get(i);
            if (currentInterval.start <= previousInterval.end) { // overlapping interval, adjust the end
                previousInterval.end = Math.max(previousInterval.end, currentInterval.end);
            } else { // non-overlapping, add the previous interval and reset
                mergedIntervals.add(previousInterval);
                previousInterval = currentInterval;
            }
        }
        // add the last interval
        mergedIntervals.add(previousInterval);
        return mergedIntervals;
    }
}
