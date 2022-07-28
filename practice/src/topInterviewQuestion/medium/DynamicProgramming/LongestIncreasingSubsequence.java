package topInterviewQuestion.medium.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/810/
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101};
        //Output 4:
        System.out.println(lengthOfLISBottomUp(nums));
        System.out.println(lengthOfLISBuildSequence(nums));
        System.out.println(lengthOfLISBinarySearch(nums));
    }


    //Approach 3: Improve with Binary Search
    static int lengthOfLISBinarySearch(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for(int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if(num > sub.get(sub.size() - 1)) {
                sub.add(num);
            } else {
                int j = binarySearch(sub, num);
                sub.set(j, num);
            }
        }
        return sub.size();
    }
    private static int binarySearch(List<Integer> sub, int num) {
        int left = 0;
        int right = sub.size() - 1;
        int mid = (left + right) / 2;
        while(left < right) {
            if(sub.get(mid) == num) {
                return mid;
            }
            if(sub.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }



        //Approach 2: Intelligently Build a Sequence
    /*
    Time complexity: O(N²)
    Space complexity: O(N)
     */
    static int lengthOfLISBuildSequence(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for(int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if(num > sub.get(sub.size() - 1)) {
                sub.add(num);
            } else {
                //Find the first element in sub that is greater than or equal to num
                int j = 0;
                while(num > sub.get(j)){
                    j += 1;
                }
                sub.set(j, num);
            }
        }
        return sub.size();
    }


        //Approach 1: Bottom Up
    /*
    Time complexity: O(n²)
    Space complexity: O(N)
     */
    static int lengthOfLISBottomUp(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int longest = 0;
        for(int c : dp) {
            longest = Math.max(longest, c);
        }
        return longest;
    }

}
