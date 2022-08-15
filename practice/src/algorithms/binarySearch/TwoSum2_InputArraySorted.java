package algorithms.binarySearch;

import java.util.Arrays;

//https://leetcode.com/explore/learn/card/binary-search/144/more-practices/1035/
public class TwoSum2_InputArraySorted {

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 5};
        int target = 6;
        var obj = new TwoSum2_InputArraySorted();
        Arrays.stream(obj.twoSum(nums, target)).forEach(e -> System.out.print(e + ", "));
    }

    /*
    Time: O(N log N) but need to be confirmed !!!
    Space: O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left + 1 < right) {
            int cur = numbers[left] + numbers[right];
            if (cur == target) {
                return new int[]{left + 1, right + 1};
            }
            if (cur < target) {
                // move end forward to the last value that numbers[end] <= target - numbers[start]
                left = smallestLargerOrEqual(numbers, target - numbers[right], left + 1, right);
            } else {
                // move start backward to the first value that numbers[start] >= target - numbers[end]
                right = largestSmallerOrEqual(numbers, target - numbers[left], left, right - 1);
            }
        }
        return new int[]{left + 1, right + 1};
    }
    private int smallestLargerOrEqual(int[] num, int target, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (num[mid] == target) {
                return mid;
            }
            if (num[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    private int largestSmallerOrEqual(int[] num, int target, int left, int right) {
        while (left < right) {
            int mid = left + (right + 1 - left) / 2;
            if (num[mid] == target) {
                return mid;
            }
            if (num[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

}
