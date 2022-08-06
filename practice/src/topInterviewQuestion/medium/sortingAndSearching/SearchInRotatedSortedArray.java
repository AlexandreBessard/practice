package topInterviewQuestion.medium.sortingAndSearching;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/804/
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        //Array not rotated below
        //int[] nums = {2, 5, 6, 7, 0, 1, 4};
        //Array rotated:
        int[] nums2 = {4,5,6,7,0,1,2};
        System.out.println(search(nums2, 0));
    }


    //Approach 2: BinarySearch One pass
    /*
    Time complexity: O(log N)
    Space complexity: O(1)
     */
    public int searchOnePass(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while(start <= end)
        {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target)
            {
                return mid;
            }
            else if (nums[mid] >= nums[start])
            {
                if(target >= nums[start] && target < nums[mid])
                {
                    end = mid - 1;
                }
                else
                {
                    start = mid + 1;
                }
            }
            else
            {
                if(target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                }
                else
                {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }


    private static int[] nums;
    private static int target;
    private static int find_rotate_index(int left, int right) {
        if (nums[left] < nums[right])
            return 0;

        while (left <= right) {
            int pivot = (left + right) / 2;
            if (nums[pivot] > nums[pivot + 1])
                return pivot + 1;
            else {
                if (nums[pivot] < nums[left])
                    right = pivot - 1;
                else
                    left = pivot + 1;
            }
        }
        return 0;
    }

    private static int search(int left, int right) {
    /*
    Binary search
    */
        while (left <= right) {
            int pivot = (left + right) / 2;
            if (nums[pivot] == target)
                return pivot;
            else {
                if (target < nums[pivot])
                    right = pivot - 1;
                else
                    left = pivot + 1;
            }
        }
        return -1;
    }

    //Approach 1: Binary Search
    /*
    Time complexity: O(log n) because Binary Search
    Space complexity: O(1)
     */
    static int search(int[] nums, int target) {
        SearchInRotatedSortedArray.nums = nums;
        SearchInRotatedSortedArray.target = target;
        int n = nums.length;
        if (n == 1)
            return nums[0] == target ? 0 : -1;
        // rotation_index splits array in two parts.
        // Compare nums[0] and target to identify in which part one has to look for target.
        int rotate_index = find_rotate_index(0, n - 1);
        // if target is the smallest element
        if (nums[rotate_index] == target)
            return rotate_index;
        // if array is not rotated, search in the entire array
        if (rotate_index == 0)
            return searchEntireArray(0, n - 1);
        if (target < nums[0])
            // search in the right side
            return search(rotate_index, n - 1);
        // search in the left side
        return search(0, rotate_index);
    }

    private static int searchEntireArray(int left, int right) {
        while (left <= right) {
            if(nums[left] == target) {
                return left;
            } else if (nums[right] == target) {
                return right;
            } else {
                left++;
                right++;
            }
        }
        return -1;
    }
}
