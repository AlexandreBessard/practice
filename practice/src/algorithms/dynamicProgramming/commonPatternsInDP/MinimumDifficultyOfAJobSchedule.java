package algorithms.dynamicProgramming.commonPatternsInDP;

import java.util.Arrays;

//https://leetcode.com/explore/learn/card/dynamic-programming/632/common-patterns-in-dp-problems/4109/
public class MinimumDifficultyOfAJobSchedule {

    /*
    https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
    You have to finish at least one task every day. The difficulty of a job schedule is the sum of
    difficulties of each
    day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
     */
    public static void main(String[] args) {
        int[] jobDifficulty = {6,5,4,3,2,1};
        int[] jobDifficulty1 = {6, 5, 10, 3, 2, 1};
        int _3days = 3;
        int d = 2;
        var obj = new MinimumDifficultyOfAJobSchedule();
        System.out.println(obj.minDifficulty(jobDifficulty1, _3days));
    }

    //Let's use one state variable i, where i is the index of the first job that will be done on the current day.
    //Let's use another state variable \text{day}day, where \text{day}day indicates what day it currently is.
    private int n, d;
    private int[][] memo;
    private int[] jobDifficulty;
    private int[] hardestJobRemaining;

    //i is the index of the first job that will be done on the current day
    //Let's use another state variable \text{day}day, where \text{day}day
    // indicates what day it currently is
    private int dp(int i, int day) {
        // Base case, it's the last day so we need to finish all the jobs
        if (day == d) {
            //We can precompute an array \text{hardestJobRemaining}hardestJobRemaining
            // where \text{hardestJobRemaining[i]}hardestJobRemaining[i] represents
            // the difficulty of the hardest job on or after day \text{i}i, so that this base case
            // is handled in constant time
            return hardestJobRemaining[i];
        }
        if (memo[i][day] == -1) {
            int best = Integer.MAX_VALUE;
            int hardest = 0;
            // Iterate through the options and choose the best
            //On each day, we try all the options - do only one job, then two jobs, etc
            System.out.println("i :" + i);
            for (int j = i; j < n - (d - day); j++) {
                System.out.println("j : " + j);
                //Define the difficulty of the day
                hardest = Math.max(hardest, jobDifficulty[j]); //Keep track of the hardest job done today
                // Recurrence relation
                //We add hardest to the next state which is the next day, starting with the next job
                //Whatever our choice, find the difficulty of the job shedule, it is the current day's difficulty
                // + the next state which would be the next day starting with the job index.
                best = Math.min(best, hardest + dp(j + 1, day + 1));
            }
            memo[i][day] = best;
        }
        return memo[i][day];
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        n = jobDifficulty.length;
        // If we cannot schedule at least one job per day,
        // it is impossible to create a schedule
        if (n < d) {
            return -1;
        }
        hardestJobRemaining = new int[n];
        int hardestJob = 0;
        for (int i = n - 1; i >= 0; i--) {
            hardestJob = Math.max(hardestJob, jobDifficulty[i]);
            hardestJobRemaining[i] = hardestJob;
        }
        // Initialize memo array with value of -1.
        memo = new int[n][d + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        this.d = d;
        this.jobDifficulty = jobDifficulty;
        return dp(0, 1); //dp(0, 1), since we start on the first day with no jobs done yet.
    }

}
