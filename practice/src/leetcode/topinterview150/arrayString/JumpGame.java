package leetcode.topinterview150.arrayString;

public class JumpGame {

    //https://leetcode.com/problems/jump-game/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(canJump(nums));
        nums = new int[]{3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
        nums = new int[]{10, 2, 3};
        System.out.println(canJump(nums));
    }

    /*
    Time: O(n)
    Space: O(1)
     */
    static boolean canJump(int[] nums) {
        int indexReachable = 0;
        for (int currentIndex = 0; currentIndex < nums.length
                && (indexReachable < nums.length - 1); currentIndex++) {
            if (currentIndex > indexReachable) {
                return false;
            }
            indexReachable = Math.max(indexReachable, nums[currentIndex] + currentIndex);
        }
        return true;
    }
}
