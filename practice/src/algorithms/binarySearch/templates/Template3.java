package algorithms.binarySearch.templates;
//https://leetcode.com/explore/learn/card/binary-search/135/template-iii/936/
public class Template3 {

    /*
    Template #3 is another unique form of Binary Search. It is used to search for an element
    or condition which requires
    accessing the current index and its immediate left and right neighbor's index in the array.
     */

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int target = 2;
        System.out.println(binarySearch(nums, target));
    }

    public static int binarySearch(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        while(left + 1 < right) {
            //Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] < target)
                left = mid;
            else
                right = mid;
        }
        //Post-processing
        // End Condition: left + 1 == right
        if(nums[left] == target)
            return left;
        if(nums[right] == target)
            return right;
        return -1;
    }


}
