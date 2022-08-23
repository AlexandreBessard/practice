package patternsForCodingInterviews.topKElements;

import java.util.*;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744374063_105Unit
public class _2SchedulingTasks {

    public static void main(String[] args) {
        char[] tasks = new char[] { 'a', 'a', 'a', 'b', 'c', 'c' };
        System.out.println("Minimum intervals needed to execute all tasks: " +
                scheduleTasks(tasks, 2));

        tasks = new char[] { 'a', 'b', 'a' };
        System.out.println("Minimum intervals needed to execute all tasks: " +
                scheduleTasks(tasks, 3));
    }

    /*
    Time: O(N * logN), for each iteration, we remove a tasks from the heap which take O(log N)
    Space: O(N)
     */
    public static int scheduleTasks(char[] tasks, int k) {
        int intervalCount = 0;
        Map<Character, Integer> taskFrequencyMap = new HashMap<>();
        for(char chr : tasks) {
            taskFrequencyMap.put(chr, taskFrequencyMap.getOrDefault(chr , 0) + 1);
        }
        //Execute the highest frequency task
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (e1, e2) -> e2.getValue() - e1.getValue()
        );
        //add all tasks to the maxHeap
        maxHeap.addAll(taskFrequencyMap.entrySet());
        while( ! maxHeap.isEmpty()) {
            List<Map.Entry<Character, Integer>> waitList = new ArrayList<>();
            int n = k + 1; // try to execute as many as 'k+1' tasks from the max-heap
            for(; n > 0 && !maxHeap.isEmpty(); n--) { //Try to execute as many tasks as k + 1
                intervalCount++;
                Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
                if(currentEntry.getValue() > 1) {
                    //Decrease frequency
                    currentEntry.setValue(currentEntry.getValue() - 1);
                    //Put in in a waiting list
                    waitList.add(currentEntry);
                }
            }
            maxHeap.addAll(waitList); // put all the waiting list back on the heap
            if( ! maxHeap.isEmpty()) {
                intervalCount += n; // we'll be having 'n' idle intervals for the next iteration
            }
        }
        return intervalCount;
    }

}
