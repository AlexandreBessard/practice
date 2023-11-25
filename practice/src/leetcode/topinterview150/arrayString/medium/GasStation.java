package leetcode.topinterview150.arrayString.medium;

public class GasStation {

    //https://leetcode.com/problems/gas-station/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        int[] gas = new int[]{7, 1, 0, 11, 4};
        int[] cos = new int[]{5, 9, 1, 2, 5};
        System.out.println(canCompleteCircuit(gas, cos));
    }

    /*
    Time: O(n)
    Space: O(1)
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int gasLength = gas.length;
        int total_surplus = 0;
        int surplus = 0;
        int start = 0;
        for (int i = 0; i < gasLength; i++) {
            total_surplus += gas[i] - cost[i];
            // Actual surplus
            surplus += gas[i] - cost[i];
            // running out of fuel
            if (surplus < 0) {
                surplus = 0;
                // start form the next station
                start = i + 1;
            }
        }
        // total_surplus positive, we have a way to loop entirely through the array
        return (total_surplus < 0) ? -1 : start;
    }

}
