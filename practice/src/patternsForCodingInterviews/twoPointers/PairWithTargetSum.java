package patternsForCodingInterviews.twoPointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743417172_1Unit
public class PairWithTargetSum {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 6};
        int target = 6;
        //System.out.println(Arrays.toString(search(nums, target)));
        System.out.println(Arrays.toString(searchWithHashMap(nums, target)));
    }

    /*
    Time complexity: O(N)
    Space complexity: O(N), worst case, push N numbers in the HashTable.
     */
    public static int[] searchWithHashMap(int[] arr, int targetSum) {
        //Let’s say during our iteration we are at number ‘X’,
        // so we need to find ‘Y’ such that “X + Y == TargetX+Y==Target”. We will do two things here:
        //Search for ‘Y’ (which is equivalent to “Target−X”) in the HashTable
        //Otherwise, insert “X” in the HashTable, so that we can search it for the later numbers.
        Map<Integer, Integer> nums = new HashMap<>(); // to store numbers and indices.
        for(int i = 0; i < arr.length; i++) {
            if(nums.containsKey(targetSum - arr[i])) {
                return new int[] { nums.get(targetSum - arr[i]), i};
            } else {
                nums.put(arr[i], i); // put the number and its index in the map
            }
        }
        return new int[] {-1, -1};
    }

    /*
    Time complexity: O(N) where N total number of elements
    Space complexity: O(1)
     */
    public static int[] search(int[] arr, int targetSum) {
        int left = 0;
        int right = arr.length - 1;
        while(left < right) {
            int currentSum = arr[left] + arr[right];
            if(currentSum == targetSum) {
                return new int[] { left, right };
            }
            if(targetSum > currentSum) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {-1, -1};
    }

}
