package topInterviewQuestion.medium.sortingAndSearching;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/798/
public class SortColors {

    public static void main(String[] args) {
        //2,0,2,1,1,0
        //Output: 0,0,1,1,2,2
    }


    //Approach 1: One pass, Three pointers
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    //Two pointers
    static void sortColors(int[] nums) {
        //We have 3 pointers in total
        int right = nums.length - 1;
        int left = 0;
        for (int i = 0; i <= right; ) { // '<=' for this edge case: [2, 0, 1]
            if (nums[i] == 2) { // Case of 2, we swap to the end
                swap(nums, i, right);
                right--;
            } else if (nums[i] == 0) { // case 0 we swap with left and i
                swap(nums, i, left);
                i++;
                left++;
            } else { //case if 1, move forward
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
