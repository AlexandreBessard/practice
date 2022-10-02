package dataStructuresAndAlgorithms.greedy;

import java.util.Arrays;

//https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks/
public class MaximumOfBagsWithFullCapacityOfRocks {

    public static void main(String[] args) {
        int[] capacity = {2, 3, 4, 5};
        int[] rocks = {1, 2, 4, 4};
        int additionalRocks = 2;
        //Return bags with full capacity
        int[] capacity1 = {10,2,2};
        int[] rocks1 = {2,2,0};
        int additionalRocks1 = 100;
        System.out.println(maximumBags(capacity, rocks, additionalRocks));
    }

    //Approach 1: Greedy
    static int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int fullBags = 0;
        //Sort bags by remaining capacity
        int[] remainingCapacity = new int[n];
        for(int i = 0; i < n; i++) {
            remainingCapacity[i] = capacity[i] - rocks[i];
        }
        //Increasing order
        Arrays.sort(remainingCapacity);
        for(int i = 0; i < n; i++) {
            //If remainingCapacity[0] -> means it is a full bag
            if(additionalRocks >= remainingCapacity[i]) { //We only count the full bags
                additionalRocks -= remainingCapacity[i];
                fullBags++;
            } else {
                break;
            }
        }
        //Return full bags only
        return fullBags;
    }


}
