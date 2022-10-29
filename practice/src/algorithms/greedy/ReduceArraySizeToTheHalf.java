package algorithms.greedy;

import java.util.*;

//https://leetcode.com/problems/reduce-array-size-to-the-half/
public class ReduceArraySizeToTheHalf {

    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 3, 5, 5, 5, 2, 2, 7};
        System.out.println(minSetSize(arr));
        //System.out.println(minSetSizeHashingAndBucketSort(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7}));
        System.out.println(minSetSizeHashingAndBucketSort(new int[]{3, 3, 2, 2, 7, 7, 6, 6}));

    }

    //Approach 3: Hashing & bucket sort
    static int minSetSizeHashingAndBucketSort(int[] arr) {
        Map<Integer, Integer> counts = new HashMap<>();
        int maxCount = 0;
        for (int num : arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1); //Count each distinct value
            maxCount = Math.max(maxCount, counts.get(num)); //Get the highest value (num which appears the most in the array)
        }
        //Put the counts into buckets
        int[] buckets = new int[maxCount + 1]; //+1 because we do not count 0 from counts, each index represent counts associated
        for (int count : counts.values()) { //Do not need to sort them
            buckets[count] += count; //Means each index represents the number of counts
        }
        int bucket = maxCount; //start with the biggest bucket (index)
        int half = (arr.length / 2) - 1;
        int size = 0;
        while (half >= 0) {
            if (buckets[bucket] <= 0) { //True if 'empty bucket'
                bucket--; //Move to the next bucket (decreasing because we start from the biggest bucket (right side)
            } else {
                size++;
                buckets[bucket] -= bucket;  //Remove the element from the bucket
                half -= bucket; //Decrease half
            }
        }
        return size;
    }

    //Approach 1: Sorting
    /*
    Time: O(n log n) due to sorting algorithm
    Space: O(n)
     */
    static int minSetSize(int[] arr) {
        Arrays.sort(arr); //  [2, 2, 3, 3, 3, 3, 5, 5, 5, 7] : size 10
        //Make the list of counts
        List<Integer> counts = new ArrayList<>();
        int currCount = 1;
        int currElement = arr[0];
        for (int i = 1; i < arr.length; i++) { //Count each distinct elements
            if (currElement != arr[i]) {
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
        for (int count : counts) { //loop through each counts from the bigger count value first
            numberRemovedFromArray += count;
            size++;
            //Return the minimum size of the set so that at least half of the integers of the array are removed.
            if (numberRemovedFromArray >= arr.length / 2) {
                break;
            }
        }
        return size;
    }
}
