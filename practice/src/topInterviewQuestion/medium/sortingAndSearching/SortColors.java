package topInterviewQuestion.medium.sortingAndSearching;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/798/
public class SortColors {

    public static void main(String[] args) {
        //2,0,2,1,1,0
        //Output: 0,0,1,1,2,2
    }


    //Approach 1: One pass
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static void sortColors(int[] nums) {
        int p0 = 0, curr = 0;
        int p2 = nums.length - 1;
        int tmp;
        while(curr <= p2) {
            if(nums[curr] == 0) {
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            } else if(nums[curr] == 2) {
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            } else {
                curr++;
            }
        }
    }


}
