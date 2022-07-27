package topInterviewQuestion.medium.sortingAndSearching;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/805/
public class MeetingsRoomsII {

    public static void main(String[] args) {
        int[][] nums = {
                {0, 30},
                {5, 10},
                {15, 20}
        };
        System.out.println(minMeetingRooms(nums));
    }


    //Approach 1: PriorityQueues
    /*
    Time complexity: O(n log n) for sorting arrays & heap O(log n)
    Space complexity: O(N) for the heap
     */
    static int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0)
            return 0;
        //Min heap
        PriorityQueue<Integer> allocator = new PriorityQueue<>(
                intervals.length,
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer a, Integer b) {
                        return a - b;
                    }
                }
        );
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        allocator.add(intervals[0][1]);

        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }
            allocator.add(intervals[i][1]);
        }
        return allocator.size();
    }

}
