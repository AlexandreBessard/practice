package leetcode.leetcode75.twoPointers.designGurus;

//https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63dda4ad488110f74a93371d
public class RemoveDuplicates {


    /*
    Two pointers.
    Remove duplicates without using extra space. Space complexity must be O(1).
    Return the length of the array after removing the duplicate element.

    Move all the unique elements at the beginning of the array
    and after moving return the length of the sub-array that has no duplicate in it.
     */
    public static void main(String[] args) {
        int[] input1 = new int[]{2, 3, 3, 3, 6, 9, 9};
        //Expect 4
        System.out.println(remove(input1));
        input1 = new int[]{3, 2, 3, 6, 3, 10, 9, 3};
        System.out.println(removeUsingKey(input1, 3));
    }

    /*
    Given an unsorted array of numbers and a target ‘key’,
    remove all instances of ‘key’ in-place and return the new length of the array.

    The array can not be necessarily in ascending order
    Input: [3, 2, 3, 6, 3, 10, 9, 3], Key=3
    Output: 4

    Time: O(N) where N is the total number of elements.
    Space: O(1)
     */
    public static int removeUsingKey(int[] arr, int key) {
        int nextElement = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != key) {
                arr[nextElement] = arr[i];
                nextElement++;
            }
        }
        return nextElement;
    }

    /*
    Input: [2, 3, 3, 3, 6, 9, 9]
    Output: 4

    Time: O(N) N is the total number of elements in the given array.
    Space: O(1)
     */
    public static int remove(int[] arr) {
        //Start at index 1
        //Used for placing the non-duplicate number.
        int nextNonDuplicate = 1;
        //Loop through each element to check if duplicates or not
        for (int i = 0; i < arr.length; i++) {
            //Compare the current element with the non-duplicate one
            int previous = nextNonDuplicate - 1;
            if (arr[previous] != arr[i]) { //If true means no duplicate
                arr[nextNonDuplicate] = arr[i];
                nextNonDuplicate++;
            }
        }
        return nextNonDuplicate;
    }

}
