package patternsForCodingInterviews.cyclicSort;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743725641_32Unit
public class FindDuplicateNumber {

    public static void main(String[] args) {
        System.out.println(findNumber(new int[]{1, 4, 4, 3, 2}));
        System.out.println(findNumber(new int[]{2, 1, 3, 3, 5, 4}));
        System.out.println(findNumber(new int[]{2, 4, 1, 4, 4}));

        System.out.println(findDuplicateWithoutModifyingArray(new int[] { 1, 4, 4, 3, 2 }));
        /*
        System.out.println(findDuplicateWithoutModifyingArray(new int[] { 2, 1, 3, 3, 5, 4 }));
        System.out.println(findDuplicateWithoutModifyingArray(new int[] { 2, 4, 1, 4, 4 }));

         */
    }

    //TODO: Need to be reviewed
    /*
    Approach 2: We do not modify input array

     */
    public static int findDuplicateWithoutModifyingArray(int[] arr) {
         int slow = 0;
         int fast = 0;
         do {
             slow = arr[slow];
             fast = arr[arr[fast]];
         } while (slow != fast);
         //Find cycle length
        int current = arr[slow];
        int cycleLength = 0;
        do {
            current = arr[current];
            cycleLength++;
        } while (current != arr[slow]);
        return 0;
    }
    private static int findStart(int[] arr, int cycleLength) {

        return 0;
    }


    /*
    Approach 1: We modify the input array
    Time: O(N)
    Space: O(1)
     */
    public static int findNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i + 1) {
                if (nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i] - 1);
                } else {
                    return nums[i];
                }
            } else {
                i++;
            }

        }
        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
