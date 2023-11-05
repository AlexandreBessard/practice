package leetcode.topinterview150.binarysearch;

public class SearchInsertPosition {

    // https://leetcode.com/problems/search-insert-position/?envType=study-plan-v2&envId=top-interview-150

    /*
    Given a sorted array of distinct integers and a target value, return the index if the target is found.
    If not, return the index where it would be if it were inserted in order.
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        // Output 2
        System.out.println(searchInsert(nums, 5));
        // Output 1, where the index where it would be if it were inserted in oder
        System.out.println(searchInsert(nums, 2));
    }

    static int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (target < nums[mid]) {
                // need to find a smaller element because the key is too small
                end = mid - 1;
            } else {
                // the key is bigger than the middle element, need a bigger element
                start = mid + 1;
            }
        }
        return start;
    }

}
