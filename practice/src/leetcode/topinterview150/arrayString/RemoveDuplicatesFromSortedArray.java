package leetcode.topinterview150.arrayString;

public class RemoveDuplicatesFromSortedArray {

    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        int[] nums1 = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums1));
    }

    /*
    Time: O(n), Iterate over the nums array exactly once
    Space: O(1)
     */
    static int removeDuplicates(int[] nums) {
        // If the array is empty
        if (nums.length == 0) {
            return 0;
        }
        // 'uniqueIndex' will point to the last position where we have seen a unique number.
        int uniqueIndex = 0;
        // Iterate over the numbers in the array starting from the second number
        for (int currentIndex = 1; currentIndex < nums.length; currentIndex++) {
            // If the current number is not the same as the last unique number
            if (nums[uniqueIndex] != nums[currentIndex]) {
                // Move the 'uniqueIndex' forward and place the current number there
                uniqueIndex++;
                nums[uniqueIndex] = nums[currentIndex];
            }
        }
        return uniqueIndex + 1; // index-based 1, the count of unique number
    }

}
