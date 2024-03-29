package leetcode.sortingAndSearching;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/802/
public class SearchForARange {
/*
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexit
 */
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        for(int i : searchRange(nums, 8)) {
            System.out.print(i + ", ");
        }
    }

    //Grokking the coding interview, same logic:
    /** {@link patternsForCodingInterviews.modifiedBinarySearch.NumberRange} */

    //Approach 1: Binary Search
    /*
    Time complexity: O(log N) (Binary Search)
    Space complexity: O(1)
     */
    static int[] searchRange(int[] nums, int target) {
        int firstOccurrence = findBound(nums, target, true); //Return 3
        if(firstOccurrence == -1) {
            return new int[]{-1, -1};
        }
        int lastOccurrence = findBound(nums, target, false);
        return new int[]{firstOccurrence, lastOccurrence};
    }
    private static int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int left = 0, right = N - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                if(isFirst) {
                    //Find our lower bound
                    if(mid == left || nums[mid - 1] != target) {
                        return mid;
                    } else {
                        //Search on left side
                        right = mid - 1;
                    }
                } else {
                    if(mid == right || nums[mid + 1] != target) {
                        return mid;
                    }
                }
            } else if(nums[mid] > target) {
                //Left side
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

}
