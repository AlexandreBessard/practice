package leetcode.array;

public class FindDuplicate {

    //Sorted array
    public static void main(String[] args) {
        int[] nums = {1, 2, 10, 3, 4, 5, 5};
        System.out.println(findDuplicate(nums));
    }

    static int findDuplicate(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }
}
