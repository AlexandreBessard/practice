package leetcode.sortingAndSearching;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/805/
public class MeetingsRoomsII {
/*
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
return the minimum number of conference rooms required.
 */
    public static void main(String[] args) {
        int[][] nums = {
                {0, 30},
                {5, 10},
                {15, 20}
        };
        System.out.println(minMeetingRooms(nums));
        System.out.println(minMeetingRoomsChronologicalOrdering(nums));
    }
    //Other approach solution :
    /** {@link patternsForCodingInterviews.mergeIntervals._1MinimumMeetingRooms} */

    //Approach 1: PriorityQueues
    /*
    Time complexity: O(n log n) for sorting arrays & heap O(log n)
    Space complexity: O(N) for the heap
     */
    static int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length < 1) return 0; //null or empty return 0 room
        if(intervals.length == 1) { // Only 1 element, return 1 room
            return 1;
        }
        //Min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b); //Smallest element are the priority
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); //sort by start
        minHeap.add(intervals[0][1]);
        for(int i = 1; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];
            if(currentStart >= minHeap.peek()) { //true means not overlapping with the smallest element
                minHeap.poll(); //Remove the meeting which end earlier
            }
            minHeap.add(currentEnd);
        }
        return minHeap.size();
    }

    //Approach 2: Chronological Ordering
    /*
    Time: O(N log N) caused by sorted array
    Space: O(N) caused by array size
     */
    public static int minMeetingRoomsChronologicalOrdering(int[][] intervals) {
        if(intervals.length == 0) {
            return 0;
        }
        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];

        for(int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(end, (a, b) -> a - b);
        Arrays.sort(start, (a, b) -> a - b);
        //The two pointers in the algo
        int startPointer = 0;
        int endPointer = 0;
        //Keep track of max number of rooms used
        int usedRooms = 0;
        //Iterate
        while (startPointer < intervals.length) {
            // If there is a meeting that has ended by the
            // time the meeting at `start_pointer` starts
            if(start[startPointer] >= end[endPointer]) {
                usedRooms--;
                endPointer--;
            }
            usedRooms++;
            startPointer++;
        }
        return usedRooms;
    }
}
