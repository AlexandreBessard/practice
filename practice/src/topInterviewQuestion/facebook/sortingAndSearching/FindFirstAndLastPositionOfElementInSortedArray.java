package topInterviewQuestion.facebook.sortingAndSearching;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/facebook/54/sorting-and-searching-3/3030/
public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        var obj = new FindFirstAndLastPositionOfElementInSortedArray();
        System.out.println(Arrays.toString(obj.searchRange(nums, target)));
    }

    //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/14734/Easy-java-O(logn)-solution
    //Binary Search
    /*
    Time: O(log N)
    Space: O(1)
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }
    //5,7,8,8,7,10
    private int findLast(int[] nums, int target) {
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(nums[mid] <= target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
            if(nums[mid] == target) idx = mid;
        }
        return idx;
    }

    private int findFirst(int[] nums, int target) {
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(nums[mid] >= target){
                end = mid - 1; //Difference with findLast
            }else{
                start = mid + 1; //Difference with findLast
            }
            if(nums[mid] == target) idx = mid;
        }
        return idx;
    }


}
