package topInterviewQuestion.medium.sortingAndSearching;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/802/
public class SearchForARange {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        for(int i : searchRange(nums, 8)) {
            System.out.print(i + ", ");
        }
    }

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
        int begin = 0, end = N - 1;
        while(begin <= end) {
            int mid = (begin + end) / 2;
            if(nums[mid] == target) {
                if(isFirst) {
                    //Find our lower bound
                    if(mid == begin || nums[mid - 1] != target) {
                        return mid;
                    } else {
                        //Search on left side
                        end = mid - 1;
                    }
                } else {
                    if(mid == end || nums[mid + 1] != target)
                        return mid;
                }
            } else if(nums[mid] > target) {
                //Left side
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        return -1;
    }

}
