package algorithms.binarySearch.templates;

//https://leetcode.com/explore/learn/card/binary-search/126/template-ii/937/
public class Template2 {

    /*
    Template #2 is an advanced form of Binary Search. It is used to search for an element or condition
    which requires accessing
    the current index and its immediate right neighbor's index in the array
     */
    public static void main(String[] args) {

    }

    /*
    Time: O(log N) where N is the total elements in the given array
    Space: O(1)
     */
    public static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length;
        while (left < right) {
            //Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) { // Look at the right
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        //Post-processing
        //End condition: left == right
        if (left != nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }

}




