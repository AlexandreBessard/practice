package patternsForCodingInterviews.mergeIntervals;

import java.util.*;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743662085_26Unit
public class _2MaximumCPULoad {

    public static void main(String[] args) {
        List<Job> input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 3),
                new Job(2, 5, 4), new Job(7, 9, 6)));
        System.out.println("Maximum CPU load at any time: " +
                findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11),
                new Job(8, 12, 15)));
        System.out.println("Maximum CPU load at any time: " +
                findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1),
                new Job(3, 6, 5)));
        System.out.println("Maximum CPU load at any time: " +
                findMaxCPULoad(input));
    }

    /*
    Time: O(N * log N) caused by sorting algo and O(log N) caused by the heap -> 'poll()' method
    Space: O(N) caused by sorting and heap
     */
    public static int findMaxCPULoad(List<Job> jobs) {
        //Sort the jobs by the start
        jobs.sort((a, b) -> a.start - b.start);
        int maxCPULoad = 0;
        int currentCPULoad = 0;
        PriorityQueue<Job> minHeap = new PriorityQueue<>(
                jobs.size(), (a, b) -> a.end - b.end
        );
        for(Job job : jobs) {
            //Remove all the job that have ended
            while( ! minHeap.isEmpty() && job.start > minHeap.peek().end) {
                currentCPULoad -= minHeap.poll().cpuLoad;
            }
            minHeap.add(job);
            currentCPULoad += job.cpuLoad;
            maxCPULoad = Math.max(maxCPULoad, currentCPULoad);
        }
        return maxCPULoad;
    }

    static class Job {
        int start;
        int end;
        int cpuLoad;
        public Job(int start, int end, int cpuLoad) {
            this.start = start;
            this.end = end;
            this.cpuLoad = cpuLoad;
        }
    }

}
