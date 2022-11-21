package leetcode.array;
//https://leetcode.com/problems/remove-duplicates-from-sorted-array/
public class RemoveDuplicatesFromSortedArray {
/*
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
 */
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        //Output: 2 (elements) -> {1, 2, _}
        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums2));
    }

    /*
    Time: O(n)
    Space: O(1)
     */
    static int removeDuplicates(int[] nums) {
        if (nums.length == 0) { //true if empty
            return 0;
        }
        int i = 0; //Pointer to know where is the first non repeating number
        for (int j = 1; j < nums.length; j++) { //Loop through each letter
            if (nums[j] != nums[i]) {
                i++; //We know from i that this is the first non repeating number
                nums[i] = nums[j];
            }
        }
        return i + 1; //Latest index where non-repeating + 1
    }
}
