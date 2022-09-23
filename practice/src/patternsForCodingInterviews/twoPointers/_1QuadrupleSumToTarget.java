package patternsForCodingInterviews.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743502365_9Unit
public class _1QuadrupleSumToTarget {

    public static void main(String[] args) {
        System.out.println(searchQuadruplets(new int[] { 4, 1, 2, -1, 1, -3 }, 1));
        //System.out.println(searchQuadruplets(new int[] { 2, 0, -1, 1, -2, 2 }, 2));
    }

    /*
    Time: O(N * logN + N3) --> O(N3)
    Space: O(N)
     */
    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        Arrays.sort(arr); //sorting the array will take O(N * logN)
        List<List<Integer>> quadruplets = new ArrayList<>();
        for(int i = 0; i < arr.length - 3; i++) {
            //Avoid duplicate quadruplets
            if(i > 0 && arr[i - 1] == arr[i]) { //check if previous number is the same, avoid duplicate
                continue;
            }
            for(int j = i + 1; j < arr.length - 2; j++) {
                if(j > i + 1 && arr[j] == arr[j - 1]) {
                    continue;
                }
                searchPairs(arr, target, i, j, quadruplets);
            }
            System.out.println();
        }
        return quadruplets;
    }
    private static void searchPairs(int[] arr,
                                    int targetSum,
                                    int first,
                                    int second,
                                    List<List<Integer>> quadruplets)
    {
        int left = second + 1;
        int right = arr.length - 1;
        while(left < right) {
            int sum = arr[first] + arr[second] + arr[left] + arr[right];
            if(targetSum == sum) {
                quadruplets.add(Arrays.asList(arr[first], arr[second], arr[left], arr[right]));
                left++;
                right--;
                while(left < right && arr[left] == arr[left - 1]){
                    left++;
                }
                while(left < right && arr[right] == arr[right + 1]){
                    right--;
                }
            } else if(sum < targetSum) {
                left++;
            } else {
                right--;
            }
        }
    }

}
