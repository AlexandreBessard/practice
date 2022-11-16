package patternsForCodingInterviews.cyclicSort;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743705616_30Unit
public class FindTheMissingNumber {

    public static void main(String[] args) {
        System.out.println(findMissingNumber(new int[] { 4, 0, 3, 1 }));
        System.out.println(findMissingNumber(
                new int[] { 8, 3, 5, 2, 4, 6, 0, 1 }));
    }

    /*
    Time: O(N) + O(n -1) which is O(N)
    Space: O(1)
     */
    public static int findMissingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if(nums[i] < nums.length && nums[i] != nums[nums[i]]) {
                swap(nums, i, nums[i]); //Do not increment i
            } else {
                i++;
            }
        }
        // find the first number missing from its index, that will be our required number
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
