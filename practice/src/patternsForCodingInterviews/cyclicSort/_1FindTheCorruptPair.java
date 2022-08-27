package patternsForCodingInterviews.cyclicSort;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743744011_34Unit
public class _1FindTheCorruptPair {

    public static void main(String[] args) {
        int[] nums = findNumbers(new int[] { 3, 1, 2, 5, 2 });
        System.out.println(nums[0] + ", " + nums[1]);
        nums = findNumbers(new int[] { 3, 1, 2, 3, 6, 4 });
        System.out.println(nums[0] + ", " + nums[1]);
    }

    /*
    Time: O(n)
    Space: O(1)
     */
    public static int[] findNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[i] - 1) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                return new  int[] {nums[i], i + 1};
            }
        }
        return new int[]{ -1, -1};

    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
