package patternsForCodingInterviews.mergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743628532_22Unit
public class InsertInterval {

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        for (Interval interval : insert(input, new Interval(4, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        /*
        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        for (Interval interval : insert(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        for (Interval interval : insert(input, new Interval(1, 4)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

         */
    }

    /*
    Time: O(N)
    Space: O(N)
     */
    public static List<Interval> insert(List<Interval> intervals,
                                        Interval newInterval)
    {
        if(intervals == null || intervals.isEmpty())
            return Arrays.asList(newInterval);
        List<Interval> mergedIntervals = new ArrayList<>();
        int i = 0;
        //Skip (and add to output) all intervals that come before the
        // 'newInterval'
        while(i < intervals.size()
                && intervals.get(i).end < newInterval.start)
        {
            mergedIntervals.add(intervals.get(i++));
        }
        //Merge all intervals that overlap with 'newInterval'
        while(i < intervals.size()
                && intervals.get(i).start <= newInterval.end)
        {
            newInterval.start = Math.min(intervals.get(i).start,
                    newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end,
                    newInterval.end);
            i++;
        }
        //Insert the newInternal
        mergedIntervals.add(newInterval);
        //Add all the remaining intervals to the output
        while(i < intervals.size()) {
            mergedIntervals.add(intervals.get(i++));
        }
        return mergedIntervals;
    }
}