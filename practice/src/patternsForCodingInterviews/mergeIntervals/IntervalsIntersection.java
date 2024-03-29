package patternsForCodingInterviews.mergeIntervals;

import java.util.ArrayList;
import java.util.List;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743634893_23Unit
public class IntervalsIntersection {
/*
Given two lists of intervals, find the intersection of these two lists. Each list consists of disjoint intervals sorted on their start time.
 */
    public static void main(String[] args) {
        Interval[] input1 = new Interval[] { new Interval(1, 3), new Interval(5, 6),
                new Interval(7, 9) };
        Interval[] input2 = new Interval[] { new Interval(2, 3), new Interval(5, 7) };
        Interval[] result = merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input1 = new Interval[] { new Interval(1, 3), new Interval(5, 7),
                new Interval(9, 12) };
        input2 = new Interval[] { new Interval(5, 10) };
        result = merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
    }

    public static Interval[] merge(Interval[] arr1, Interval[] arr2) {
        List<Interval> result = new ArrayList<>();
        int i = 0; //Pointer for the first array (arr1)
        int j = 0; //Pointer for the second array (arr2)
        while(i < arr1.length && j < arr2.length) {
            //Check if the interval arr[i] intersects with arr2[j]
            //Check if one of the interval's start time lies within the other interval
            if( (
                    //Make sure arr1.start  is inside (overlap) arr2
                    arr1[i].start >= arr2[j].start
                            && arr1[i].start <= arr2[j].end)

                    || (arr2[j].start >= arr1[i].start
                            && arr2[j].start <= arr1[i].end))
            {
                //Store the intersection part
                int start = Math.max(arr1[i].start, arr2[j].start); //Get the max value
                int end = Math.min(arr1[i].end, arr2[j].end); //Get the min value
                result.add(new Interval(start, end));
            }
            //Move next from the interval which is finishing first
            if(arr1[i].end < arr2[j].end) {
                i++; //Pointer for array 1
            } else {
                j++; //Pointer for array 2
            }
        }
        return result.toArray(new Interval[0]);
    }


}
