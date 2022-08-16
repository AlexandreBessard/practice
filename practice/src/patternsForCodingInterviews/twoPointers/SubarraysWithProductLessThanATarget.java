package patternsForCodingInterviews.twoPointers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743479436_7Unit
public class SubarraysWithProductLessThanATarget {

    public static void main(String[] args) {
        System.out.println(findSubarrays(new int[] { 2, 5, 3, 10 }, 30));
        System.out.println(findSubarrays(new int[] { 8, 2, 6, 5 }, 50));
    }
    /*
    Time: fo-loop: O(N)
    Creating subarrays take O(N²) Therefore: O(N3)
    Space: O(N) used for temp list
     */
    public static List<List<Integer>> findSubarrays(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        double product = 1;
        int left = 0;
        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];
            while (product >= target && left < arr.length) {
                product /= arr[left++];
            }

            List<Integer> tempList = new LinkedList<>();
            for (int i = right; i >= left; i--) {
                tempList.add(0, arr[i]);
                result.add(new ArrayList<>(tempList));
            }
        }
        return result;
    }

}
