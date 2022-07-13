package topInterviewQuestion.easy.array;

import java.util.Arrays;

public class RotateArray {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        rotate(array, 3);
        for(int i : array) {
            System.out.print(i + ", ");
        }
        // {5, 6, 7, 1, 2, 3, 4}
    }

    //Using extra array
    /*
    Approach 2
    Time complexity: O(n)
    One pass to put numbers in the new array, another pass
    to copy from array to original array.
    Space complexity: O(n), Another array is used to store values
     */
    static void rotateExtraArray(int[] nums, int k) {
        int[] a = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for(int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }

    //Brute force
    /*
    Time complexity: O(n x k) : All numbers are shifted by one step(O(n)k times
    Space complexity: O(1)
     */
    static void rotate(int[] nums, int k) {
        k %= nums.length; //3
        int temp, previous;
        for(int i = 0; i < k; i++) {
            previous = nums[nums.length - 1]; //7
            for(int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }

    }

}
