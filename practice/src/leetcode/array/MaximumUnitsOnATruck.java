package leetcode.array;

import java.util.Arrays;
import java.util.PriorityQueue;

//https://leetcode.com/problems/maximum-units-on-a-truck/
public class MaximumUnitsOnATruck {
/*
Return the maximum total number of units that can be put on the truck.
 */
    public static void main(String[] args) {
        //boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
        int[][] boxTypes = {{1, 3}, {2, 2}, {3, 1}};
        //Maximum number of boxes that can be put on the truck
        int truckSize = 4;
        //Output 8
        System.out.println(maximumUnitsBrutForce(boxTypes, truckSize)+"\n");
        boxTypes = new int[][] {{1, 3}, {2, 2}, {3, 1}};
        System.out.println(maximumUnitsSort(boxTypes, truckSize)+"\n");
        boxTypes = new int[][] {{1, 3}, {2, 2}, {3, 1}};
        System.out.println(maximumUnitsPriorityQueue(boxTypes, truckSize));
    }

    //Approach 1: Brut force
    /*
    Time: O(n²) where N number of elements in array and 'findMaxUnits' iterate over all elements
    Space: O(1)
     */
    public static int maximumUnitsBrutForce(int[][] boxTypes, int truckSize) {
        int unitCount = 0;
        int remainingTruckSize = truckSize;
        while(remainingTruckSize > 0) {
            //Get the index with the higher units per box
            //We know this is the highest units we can get now
            int maxUnitBoxIndex = findMaxUnisPertBox(boxTypes);
            if(maxUnitBoxIndex == -1) { //true means all boxes are used
                break;
            }
            //find box that can be put in truck, get the minimum
            int boxCount = Math.min(remainingTruckSize, boxTypes[maxUnitBoxIndex][0]);
            unitCount += boxCount * boxTypes[maxUnitBoxIndex][1];
            remainingTruckSize -= boxCount;
            //mark box with index maxUnitBoxIndex as used
            boxTypes[maxUnitBoxIndex][1] = -1;
        }
        return unitCount;
    }

    //Used to find the highest non used units per box in the array
    private static int findMaxUnisPertBox(int[][] boxTypes) {
        int maxUnitBoxIndex = -1; //Our result to store the index where is located higher units
        int maxUnits = 0;
        for(int i = 0; i < boxTypes.length; i++) {
            if(boxTypes[i][1] > maxUnits) { //If used, will be value -1
                maxUnits = boxTypes[i][1];
                maxUnitBoxIndex = i;
            }
        }
        return maxUnitBoxIndex;
    }

    //Approach 2: Using Array Sort
    /*
    Time: O(n log n) caused by sorted algo (merge sort)
    Space: O(N) used by the algorithm merge sort
     */
    public static int maximumUnitsSort(int[][] boxTypes, int truckSize) {
        //Sort by decreasing number of units per box
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int unitCount = 0;
        for(int[] boxType : boxTypes) {
            //Get the truckSize if less space than the number of boxes (missing space in the truck)
            int boxCount = Math.min(truckSize, boxType[0]);
            unitCount += boxCount * boxType[0]; //Get the entire box
            truckSize -= boxCount; //Decrement truck size
            if(truckSize == 0) {
                break;
            }
        }
        return unitCount;
    }

    //Approach 3: Priority Queue
    /*
    Time: O(n log n), removing element from the Q take (log n)
    Space: O(n) as we use a Q of size n
     */
    public static int maximumUnitsPriorityQueue(int[][] boxTypes, int truckSize) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (a, b) -> b[1] - a[1] //Bigger units per box are the priority
        );
        queue.addAll(Arrays.asList(boxTypes));
        int unitCount = 0;
        while(!queue.isEmpty()) {
            int[] currBoxType = queue.poll();
            int boxCount = Math.min(truckSize, currBoxType[0]);
            unitCount += boxCount * currBoxType[0];
            truckSize -= boxCount;
            if(truckSize == 0) {
                break;
            }
        }
        return unitCount;
    }



}
