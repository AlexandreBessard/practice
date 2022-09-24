package algorithms.binarySearch;
//https://leetcode.com/explore/learn/card/binary-search/125/template-i/952/
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int[] nums1 = {4, 5, 7, 9, 10, -1, 2};
        int target2 = 2;
        int target = 0;
        System.out.println(search(nums1, target2));
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
            //If the target is located in the non-rotated subarray (left side)
            else if (nums[mid] >= nums[start]) //Ascending order
            {
                //If the target is between the range, Check betwen start end mid (left)
                if (target >= nums[start] && target < nums[mid])
                    end = mid - 1;
                else //Right from mid to end
                    start = mid + 1;
            }
            else //Right side
            {
                //If the target is located in the non-rotated subarray, go to the right
                //true if target is between mid and end
                if (target <= nums[end] && target > nums[mid]) //true means between right side
                    start = mid + 1;
                else // if target is between mid and start
                    end = mid - 1;
            }
        }
        return -1;
    }
}

