package topInterviewQuestion.facebook.sortingAndSearching;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/facebook/54/sorting-and-searching-3/3030/
public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) throws InterruptedException {
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
    public int[] searchRange(int[] nums, int target) throws InterruptedException {
        int[] result = new int[2];
        Thread t1 = new Thread(() -> result[0] = findFirst(nums, target));
        Thread t2 = new Thread(() -> result[1] = findLast(nums, target));
        t1.start();
        t2.start();
        t1.join(); // wait until t1 Thread is finished
        t2.join(); //wait until t2 Thread is finised
        return result; // To return the result must wait until both t1 and t2 are finished to have our final result
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
            if(nums[mid] >= target){ //Difference with findLast
                end = mid - 1; //Difference with findLast
            }else{
                start = mid + 1; //Difference with findLast
            }
            if(nums[mid] == target) idx = mid;
        }
        return idx;
    }


}
