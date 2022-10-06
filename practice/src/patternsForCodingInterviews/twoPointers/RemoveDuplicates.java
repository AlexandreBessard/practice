package patternsForCodingInterviews.twoPointers;

public class RemoveDuplicates {
/*
Given an array of sorted numbers, remove all duplicate number instances from it in-place,
such that each element appears only once. The relative order of the elements should be kept the same and
you should not use any extra space so that that the solution have a space complexity of O(1).

Move all the unique elements at the beginning of the array and after moving return the length of the subarray that has
no duplicate in it.
 */
    public static void main(String[] args) {
        int[] nums = {2, 3, 3, 3, 6, 9, 9};
        System.out.println(remove(nums));
        int[] nums1 = {3, 2, 3, 6, 3, 10, 9, 3};
        int key = 3;
        System.out.println(removeUnsortedArray(nums1, key));
    }


    /*
    Similar Questions:
    Problem 1: Given an unsorted array of numbers and a target ‘key’,
    remove all instances of ‘key’ in-place and return the new length of the array.
     */
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int removeUnsortedArray(int[] arr, int key) {
        int nextElement = 0; // index of the next element which is not 'key'
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != key) {
                arr[nextElement] = arr[i];
                nextElement++;
            }
        }
        return nextElement;
    }


    /*
    Approach 1:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int remove(int[] arr) {
        int nextNonDuplicate = 1; // Index of the next element non-duplicate
        for(int i = 0; i < arr.length; i++) {
            if(arr[nextNonDuplicate - 1] != arr[i]) {
                arr[nextNonDuplicate] = arr[i];
                nextNonDuplicate++;
            }
        }
        return nextNonDuplicate;
    }
}
