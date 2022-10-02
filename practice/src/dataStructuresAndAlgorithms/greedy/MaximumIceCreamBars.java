package dataStructuresAndAlgorithms.greedy;

import java.util.Arrays;

//https://leetcode.com/problems/maximum-ice-cream-bars/
public class MaximumIceCreamBars {

    public static void main(String[] args) {
        int[] costs = {1, 3, 2, 4, 1};
        int coins = 7;
        //Output 4: can buy 1 , 3, 2, 1, -> 7
        System.out.println(maxIceCream(costs, coins));
    }

    static int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        for (int i = 0; i < costs.length; i++) {
            if (coins >= costs[i]) { //Buy the cheapest and repeat until you can not buy anymore
                coins -= costs[i];
            } else {
                return i; //In case of not enough coins
            }
        }
        return costs.length; //If we bought all bars
    }

}
