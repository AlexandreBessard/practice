package algorithms.dividAndConquer;

import java.util.Arrays;
//https://leetcode.com/explore/learn/card/recursion-ii/470/divide-and-conquer/2868/
public class MergeSort {

    public static void main(String[] args) {

        int[] nums = {1, 5, 3, 2, 8, 7};
        int[] result = merge_sort(nums);
        for(int i: result) {
            System.out.print(i + ", ");
        }
    }

    /*
    Divide and Conquer technique
    Time: O(n log n)
    Space: O(N)
     */
    static int[] merge_sort(int[] input) {
        if(input.length <= 1)
            return input;
        //Calculate the middle index for the subarray into two halves
        int pivot = input.length / 2; //Divide
        //we recursively sort the sublists in the previous step (Conquer)
        int[] left_list = merge_sort(Arrays.copyOfRange(input, 0, pivot));
        int[] right_list = merge_sort(Arrays.copyOfRange(input, pivot, input.length));
        //Merge the sorted sublists in the above step (Combine)
        return merge(left_list, right_list);
    }
    private static int[] merge(int[] left_list, int[] right_list) {
        //Create a new array to place our elements in order to be sorted
        int[] ret = new int[left_list.length + right_list.length];
        int left_cursor = 0;
        int right_cursor = 0;
        int ret_cursor = 0;
        while(left_cursor < left_list.length
                && right_cursor < right_list.length) {
            //Smaller placed to the left side of this new array 'ret'
            if(left_list[left_cursor] < right_list[right_cursor]) {
                ret[ret_cursor++] = left_list[left_cursor++];
            } else {
                ret[ret_cursor++] = right_list[right_cursor++];
            }
        }
        //Append what is remain the above list
        while(left_cursor < left_list.length) {
            ret[ret_cursor++] = left_list[left_cursor++];
        }
        while(right_cursor < right_list.length) {
            ret[ret_cursor++] = right_list[right_cursor++];
        }
        return ret;
    }

}
