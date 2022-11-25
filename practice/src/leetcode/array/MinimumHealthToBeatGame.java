package leetcode.array;
//https://leetcode.com/problems/minimum-health-to-beat-game/
public class MinimumHealthToBeatGame {
/*
You are playing a game that has n levels numbered from 0 to n - 1. You are given a 0-indexed integer array damage where damage[i] is the amount of health you will lose to complete the ith level.

You are also given an integer armor. You may use your armor ability at most once during the game on any level which will protect you from at most armor damage.

You must complete the levels in order and your health must be greater than 0 at all times to beat the game.

Return the minimum health you need to start with to beat the game.
 */

    public static void main(String[] args) {
        int[] damage = {2,7,4,3};
        int armor = 4;
        System.out.println(minimumHealth(damage, armor));
    }

    //Correction: https://leetcode.com/problems/minimum-health-to-beat-game/discuss/1881706/Simple-Java-Solution-O(n)-time-O(1)-space-with-explanation
    /*
    Time: O(N)
    Space: O(N)
     */
    public static long minimumHealth(int[] damage, int armor) {
        int max = 0;
        long health = 1; //we need to be greater than 0 to beat the game at all times
        for(int i = 0; i < damage.length; i++) {
            //Add all damages to the result
            health += damage[i];
            //Find the max number out of damage array
            max = Math.max(max, damage[i]);
        }
        //to optionally use the armor use it againts that level
        // that inflicts the most damage
        health -= Math.min(max, armor);
        //Return the minimum health you need to start with to beat the game
        return health;
    }


}
