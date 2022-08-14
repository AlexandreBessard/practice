package algorithms.binarySearch.templates;
//https://leetcode.com/explore/learn/card/binary-search/125/template-i/938/
public class Template1 {

    /*
    Template #1 is used to search for an element
    or condition which can be determined by accessing a single index in the array.
     */

    /*
    Most basic and elementary form of Binary Search
Search Condition can be determined without comparing to the element's neighbors
(or use specific elements around it)
No post-processing required because at each step, you are checking to see if the element has been found.
If you reach the end, then you know the element is not found
     */

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int target = 5;
        System.out.println(binarySearch(nums, target));
    }

    //Template #1:
    public static int binarySearch(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            //Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] < target) // Look on right
                left = mid + 1;
            else // Look on left
                right = mid - 1;
        }
        //End condition left > right
        return -1;
    }

}
