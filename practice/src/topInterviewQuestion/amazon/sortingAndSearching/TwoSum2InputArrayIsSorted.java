package topInterviewQuestion.amazon.sortingAndSearching;
//https://leetcode.com/explore/interview/card/amazon/79/sorting-and-searching/2994/
public class TwoSum2InputArrayIsSorted {

    /*
    In an interview, whenever you're given a question where the input array is sorted,
    here are some super useful things to consider:

Binary Search
Two (or three) pointers
A sliding window
Traversing from the right
     */
    public static void main(String[] args) {
        int[] nums = {2,3,12,7};
        int target = 6;
        var obj = new TwoSum2InputArrayIsSorted();
        for(int i : obj.twoSum(nums, target)) {
            System.out.print(i + ", ");
        }
    }

    //Two pointers
    /*
    Time: O(N)
    Space: O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        int lo = 0, hi = numbers.length - 1;
        while (lo <= hi) {
            int curSum = numbers[lo] + numbers[hi];
            if (curSum == target) {
                return new int[]{lo + 1, hi + 1};
            } else if (curSum > target) {
                hi--;
            } else {
                lo++;
            }
        }
        return new int[]{-1, -1};
    }



}
