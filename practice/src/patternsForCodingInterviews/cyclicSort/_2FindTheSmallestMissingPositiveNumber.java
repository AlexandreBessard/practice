package patternsForCodingInterviews.cyclicSort;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743750267_35Unit
public class _2FindTheSmallestMissingPositiveNumber {

    public static void main(String[] args) {
        System.out.println(
                findNumber(new int[] { -3, 1, 5, 4, 2 }));
        System.out.println(
                findNumber(new int[] { 3, -2, 0, 1, 2 }));
        System.out.println(
                findNumber(new int[] { 3, 2, 5, 1 }));
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    public static int findNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] > 0
                    && nums[i] <= nums.length
                    && nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }
        for (i = 0; i < nums.length; i++)
            if (nums[i] != i + 1)
                return i + 1;

        return nums.length + 1;
    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
