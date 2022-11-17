package topInterviewQuestion.leetcode.array;

//https://leetcode.com/problems/rotate-array/
public class RotateArray {
    /*
    Input: nums = [1,2,3,4,5,6,7], k = 3
    Output: [5,6,7,1,2,3,4]
    Explanation:
    rotate 1 steps to the right: [7,1,2,3,4,5,6]
    rotate 2 steps to the right: [6,7,1,2,3,4,5]
    rotate 3 steps to the right: [5,6,7,1,2,3,4]
     */
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        rotate(array, 3);
        for (int i : array) {
            System.out.print(i + ", ");
        }
        // {5, 6, 7, 1, 2, 3, 4}
        array = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotateExtraArrayOptimized(array, 3);
        for (int i : array) {
            System.out.print(i + ", ");
        }
    }

    //Approach 3:
    /*
    Correction: https://leetcode.com/problems/rotate-array/discuss/54250/Easy-to-read-Java-solution
    Time: O(n)
    Space: O(1)
     */
    static void rotateExtraArrayOptimized(int[] nums, int k) {
        k %= nums.length; // 3 % 7 -> 3  // Use for this case example : k = 10 and [1, 2, 3, 4, 5, 6, 7] -> index 3
        reverse(nums, 0, nums.length - 1); // [7, 6, 5, 4, 3, 2, 1] reverse the entire array
        reverse(nums, 0, k - 1); // [5, 6, 7, 4, 3, 2, 1] reverse only the concerned part before the 'k' element
        reverse(nums, k, nums.length - 1); //[5, 6, 7, 1, 2, 3, 4] reverse the other remaining part after the 'k' element
    }

    private static void reverse(int[] nums, int start, int end) {
        //Reverse the start to the end and the end to the start placement
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
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
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
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
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1]; //7
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }

    }

}
