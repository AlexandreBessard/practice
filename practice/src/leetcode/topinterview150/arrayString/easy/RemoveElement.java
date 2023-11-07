package leetcode.topinterview150.arrayString.easy;

public class RemoveElement {

    // https://leetcode.com/problems/remove-element/?envType=study-plan-v2&envId=top-interview-150

    /*
    Given an integer array nums and an integer val,
    remove all occurrences of val in nums in-place.
    The order of the elements may be changed.
    Then return the number of elements in nums which are not equal to val.
     */
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        System.out.println(removeElement(nums, 3));
        nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeElement(nums, 2));
    }

    /*
    Time: O(n)
    Space: O(1)
     */
    static int removeElement(int[] nums, int val) {
        // Used to know where is the latest different to 'val'
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // if the current element is the same as 'val', move forward
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

}
