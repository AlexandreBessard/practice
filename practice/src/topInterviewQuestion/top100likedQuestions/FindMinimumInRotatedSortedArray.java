package topInterviewQuestion.top100likedQuestions;

//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(findMin(nums)); //Return element 1, the array is rotated from here
    }

    //Binary Search
    /*
    Time: O(log n)
    Space: O(1)
     */
    static int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        int left = 0, right = nums.length - 1;
        // if the last element is greater than the first element then there is no
        // rotation.
        if (nums[right] > nums[0]) {
            return nums[0];
        }
        return binarySearch(nums, left, right);
    }

    private static int binarySearch(int[] nums, int left, int right) {
        int mid = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) { // If true, point of change -> [4, 5, 1]
                return nums[mid + 1];
            }
            if (nums[mid - 1] > nums[mid]) { // [5, 1, 2]
                return nums[mid];
            }
            /*
            // if the mid elements value is greater than the 0th element this means
            // the least value is still somewhere to the right as we are still dealing with
            // elements
             */
            if (nums[mid] > nums[0]) { // [3, 4, 5, 6, 1]  -> it is sorted in ascending order
                left = mid + 1;
            } else { // [7, 8, 1, 2, 3, 4, 5]
                /*
                if nums[0] is greater than the mid value then this means the smallest value
                // is somewhere to
                // the left
                 */
                right = mid - 1;
            }
        }
        return mid;
    }

}
