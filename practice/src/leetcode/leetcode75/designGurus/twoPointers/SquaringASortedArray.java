package leetcode.leetcode75.designGurus.twoPointers;

import java.util.Arrays;

//https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63ddacd4fcc4ca873d5fbfbc
public class SquaringASortedArray {

    public static void main(String[] args) {
        int[] input1 = new int[]{-2, -1, 0, 2, 3};
        int[] input2 = new int[]{-3, -1, 0, 1, 2};
        System.out.println(Arrays.toString(makeSquares(input1)));
        System.out.println(Arrays.toString(makeSquares(input2)));
    }

    /*
    Given a sorted array, create a new array containing squares of all the numbers
    of the input array in the sorted order.
    The array is sorted.
    Input: [-2, -1, 0, 2, 3]
    Output: [0, 1, 4, 4, 9]

    Time: O(N) we are iterating the input array only once.
    Space: O(N) used for the result. output array.
     */
    public static int[] makeSquares(int[] arr) {
        int[] squares = new int[arr.length];
        int highestSquareIndex = arr.length - 1;
        //Two pointers
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            //We do not know which side has a bigger square.
            //Calculate both side.
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];
            //Decide which side has the bigger square.
            if (leftSquare > rightSquare) {
                squares[highestSquareIndex--] = leftSquare;
                left++;
            } else {
                squares[highestSquareIndex--] = rightSquare;
                right--;
            }
        }
        return squares;
    }


}
