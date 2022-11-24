package leetcode.array;

//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2967/
public class _3SumClosest {
/*
Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.

Example 1:
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4};
        int target = 1;
        // Output 2 because closest to target 1: (-1 + 2 + 1 = 2)
        System.out.println(threeSumClosest(nums, target));
    }

    //See equivalent solution Grokking the coding interview:
    /** {@link patternsForCodingInterviews.twoPointers.TripletSumCloseToTarget} */


    /*
    If an interviewer asks you whether you can achieve O(1) memory complexity
    you can use the selection sort instead of a built-in sort in the
    Two Pointers approach. It will make it a bit slower,
    though the overall time complexity will be still O(n²)
     */
    //---->  Selection Sort algorithm
    //https://www.geeksforgeeks.org/selection-sort/
    /*
    Time complexity: O(N²) -> two nested loops
    Space complexity: 0(1)
     */
    private static void sort(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n - 1; i++) {
            //Find minimum element in sorted array
            int min_idx = i;
            for(int j = i + 1; j < n; j++) {
                if(arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            //Swap the found minimum element with the first element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }




    //Approach 1: Two pointers
    /*
    Time complexity: O(n²) whe have an outer and inner loops.
    Sorting an array takes O(n log n), Overall: O(n log n + n²) -> O(n²)
    Space complexity: O(log n) caused by QuickSort Arrays.sort()
     */
    static int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int sz = nums.length;
        //Arrays.sort(nums); //With space complexity: O(log n)
        sort(nums); //With space complexity O(1)
        for(int i = 0; (i < sz) && (diff != 0); i++) {
            int lo = i + 1;
            int high = sz - 1;
            while(lo < high) {
                int sum = nums[i] + nums[lo] + nums[high];
                if(Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }
                if(sum < target) {
                    lo++;
                } else {
                    high--;
                }
            }
        }
        return target - diff;
    }

}
