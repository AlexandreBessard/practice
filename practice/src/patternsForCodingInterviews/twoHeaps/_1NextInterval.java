package patternsForCodingInterviews.twoHeaps;

import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744015026_65Unit
public class _1NextInterval {

    /*
    Explanation: (Same logic with example 2)
    Example 1:
      O       1       2
    [2, 3], [3, 4]; [5, 6]
    Output:
    For index 0, the next interval is 1
    For index 1, the next interval is 2
    For index 2, the next interval is -1 (no interval available
    [1, 2, -1]
     */
    public static void main(String[] args) {
        Interval[] intervals;
        intervals = new Interval[] { new Interval(2, 3), new Interval(3, 4),
                new Interval(5, 6) };
        int[] result = findNextInterval(intervals);
        System.out.print("Next interval indices are: ");
        for (int index : result)
            System.out.print(index + " ");
        System.out.println();

        /*
        intervals = new Interval[] { new Interval(3, 4), new Interval(1, 5),
                new Interval(4, 6) };
        result = findNextInterval(intervals);
        System.out.print("Next interval indices are: ");
        for (int index : result)
            System.out.print(index + " ");

         */
    }

    /*
    Time: O(N log N)
    Space: O(N)
     */
    public static int[] findNextInterval(Interval[] intervals) {
        int n = intervals.length;
        //Heap for finding the maximum start
        PriorityQueue<Integer> maxStartHeap = new PriorityQueue<>(
                n, (a, b) -> intervals[b].start - intervals[a].start);
        //Heap for finding the minimum end
        PriorityQueue<Integer> maxEndHeap = new PriorityQueue<>(
                n, (a, b) -> intervals[b].end - intervals[a].end
        );
        int[] result = new int[n];
        for(int i = 0; i < n; i++) {
            maxStartHeap.offer(i);
            maxEndHeap.offer(i);
        }
        //go through all the intervals to find each intervals's next interval
        for(int i = 0; i < n; i++) {
            // let's find the next interval of the interval which has the highest 'end'
            //Take out the top (having highest end) interval from the maxEndHeap to find its next interval.
            // Let’s call this interval topEnd.
            int topEnd = maxEndHeap.poll();
            // If we can’t find the next interval, add ‘-1’ in the result array.
            result[topEnd] = -1; // defaults to -1
            //Find an interval in the maxStartHeap with the closest start greater than or
            // equal to the start of topEnd. Since maxStartHeap is sorted by ‘start’ of
            // intervals, it is easy to find the interval with the highest ‘start’.
            // Let’s call this interval topStart.
            if(intervals[maxStartHeap.peek()].start >= intervals[topEnd].end) { //Interval
                int topStart = maxStartHeap.poll();
                // find the interval that has the closest 'start'
                while( ! maxStartHeap.isEmpty()
                        && intervals[maxStartHeap.peek()].start >= intervals[topEnd].end)
                {
                    topStart = maxStartHeap.poll();
                }
                // Add the index of topStart in the result array as the next interval of topEnd.
                result[topEnd] = topStart;
                // put the interval back as it could be the next interval of other intervals
                //Put the topStart back in the maxStartHeap, as it could be the next interval of other intervals.
                maxStartHeap.add(topStart);
            }
        }
        return result;
    }




}
