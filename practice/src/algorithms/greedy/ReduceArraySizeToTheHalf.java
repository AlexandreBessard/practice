package algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/reduce-array-size-to-the-half/
public class ReduceArraySizeToTheHalf {

    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 3, 5, 5, 5, 2, 2, 7};
        System.out.println(minSetSize(arr));
    }

    //Approach 1: Sorting
    static int minSetSize(int[] arr) {
        Arrays.sort(arr); //  [2, 2, 3, 3, 3, 3, 5, 5, 5, 7] : size 10
        //Make the list of counts
        List<Integer> counts = new ArrayList<>();
        int currCount = 1;
        int currElement = arr[0];
        for(int i = 1; i < arr.length; i++) { //Count each distinct elements
            if(currElement != arr[i]) {
                counts.add(currCount);
                currCount = 1;
                currElement = arr[i];
                continue;
            }
            currCount++;
        }
        counts.add(currCount);
        counts.sort(Collections.reverseOrder()); //Sort elements in decreasing oder (bigger currCount to smaller) [4, 3, 2, 1]
        // Remove numbers until at least half are removed.
        int numberRemovedFromArray = 0;
        int size = 0;
        for(int count : counts) { //loop through each counts from the bigger count value first
            numberRemovedFromArray += count;
            size++;
            //Return the minimum size of the set so that at least half of the integers of the array are removed.
            if(numberRemovedFromArray >= arr.length / 2) {
                break;
            }
        }
        return size;
    }
}
