package patternsForCodingInterviews.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743445291_4Unit
public class TripletSumToZero {

    public static void main(String[] args) {
        System.out.println(searchTriplets(
                new int[] { -3, 0, 1, 2, -1, 1, -2 }));
        //System.out.println(searchTriplets(new int[] { -5, 2, -1, -2, 3 }));
    }

    /*
    Time: Sorting the array will take: O(N * Log N)
    searchPair(): take O(N) and it is calling for every number in the input
    array.
    Total: O(N * log N + N²)  ---> O(N²)
    Space: O(N) caused by sorted array
     */
    public static List<List<Integer>> searchTriplets(int[] arr) {
        Arrays.sort(arr); //Array must be sorted to apply this algorithm
        List<List<Integer>> triplets = new ArrayList<>();
        for(int i = 0; i < arr.length - 2; i++) {
            //Skip same element to avoid duplicate triplets
            if(i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            searchPair(arr, -arr[i], i + 1, triplets);
        }
        return triplets;
    }
    private static void searchPair(int[] arr, int targetSum,
                                   int left,
                                   List<List<Integer>> triplets)
    {
        int right = arr.length - 1;
        while(left < right) {
            int currentSum = arr[left] + arr[right];
            if(currentSum == targetSum) {
                //Find a triplet
                triplets.add(Arrays.asList(-targetSum, arr[left], arr[right]));
                left++;
                right--;
                while(left < right && arr[left] == arr[left - 1]) {
                    //Skip same element to avoid duplicate triplets
                    left++;
                }
                while(left < right && arr[right] == arr[right + 1]){
                    //Skip same element to avoid duplciate
                    right--;
                }
            } else if(targetSum > currentSum) {
                //Need to pair with a bigger sum, arr is sorted
                left++;
            } else {
                //Need to pair with smaller sum, arr is sorted
                right--;
            }
        }
    }
}
