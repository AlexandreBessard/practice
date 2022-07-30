package topInterviewQuestion.medium.others;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/114/others/825/
public class TaskScheduler {

    public static void main(String[] args) {
        char[] tasks = {'A', 'B', 'A', 'A', 'B', 'C', 'A', 'A'};
        //Output: 13
        System.out.println(leastInterval(tasks, 2));
    }


    //Approach 1: Greedy
    /*
    Time complexity: O(N) : N: number of tasks to be executed
    Space complexity: O(1)
     */
    static int leastInterval(char[] tasks, int n) {
        // frequencies of the tasks
        int[] frequencies = new int[26];
        for (int t : tasks) {
            frequencies[t - 'A']++;
        }
        Arrays.sort(frequencies);
        // max frequency
        int f_max = frequencies[25];
        int idle_time = (f_max - 1) * n;// 8
        for (int i = frequencies.length - 2; i >= 0 && idle_time > 0; --i) {
            idle_time -= Math.min(f_max - 1, frequencies[i]);
        }
        idle_time = Math.max(0, idle_time);
        return idle_time + tasks.length;
    }

}
