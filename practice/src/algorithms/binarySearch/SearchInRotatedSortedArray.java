package algorithms.binarySearch;
//https://leetcode.com/explore/learn/card/binary-search/125/template-i/952/
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search(nums, 5));
    }

    /*
    //Template #1
    Time: O(log N)
    Space O(1)
     */
    public static int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
            {
                return mid;
            }
            //If the target is located in the non-rotated subarray
            else if (nums[mid] >= nums[start])
            {
                //If the target is between the range
                if (target >= nums[start] && target < nums[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
            else
            {
                //If the target is located in the non-rotated subarray, go to the right
                if (target <= nums[end] && target > nums[mid])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}

