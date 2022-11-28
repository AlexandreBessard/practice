package patternsForCodingInterviews.cyclicSort;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743725641_32Unit
public class FindDuplicateNumber {

    public static void main(String[] args) {
        //Unsorted
        System.out.println(findNumber(new int[]{1, 4, 4, 3, 2}));
        System.out.println(findNumber(new int[]{2, 1, 3, 3, 5, 4}));
        System.out.println(findNumber(new int[]{2, 4, 1, 4, 4}));

        System.out.println("Floyd's algo -> " + findDuplicateWithoutModifyingArray(new int[]{4, 3, 4, 5, 2, 4, 1}));
        //System.out.println("Result Binary Search : " + findNumberBinarySearch(new int[]{4, 3, 4, 5, 2, 4, 1}));
        System.out.println("Result Binary Search : " + findNumberBinarySearch(new int[]{1, 4, 4, 3, 2}));

        /*
        System.out.println(findDuplicateWithoutModifyingArray(new int[] { 2, 1, 3, 3, 5, 4 }));
        System.out.println(findDuplicateWithoutModifyingArray(new int[] { 2, 4, 1, 4, 4 }));

         */
    }

    //Approach 5: Binary Search
    /*
    Time: O(n log n)
    Space: O(1)
     */
    static int findNumberBinarySearch(int[] nums) { //  1, 4, 4, 3, 2
        // 'low' and 'high' represent the range of values of the target
        int low = 1, high = nums.length - 1;
        int duplicate = -1;
        while (low <= high) {
            int pivot = (low + high) / 2; //Get the middle element
            // Count how many numbers in 'nums' are less than or equal to 'pivot'
            int count = 0;
            for (int num : nums) {
                if (num <= pivot) {
                    count++;
                }
            }
            if (count > pivot) { //If count exceed the number of point, means duplicate
                duplicate = pivot;
                high = pivot - 1;
            } else {
                low = pivot + 1;
            }
        }
        return duplicate;
    }

    /*
    Approach 2: We do not modify input array
    Floyd's algorithm
    Time: O(n)
    Space: O(1)
     */
    public static int findDuplicateWithoutModifyingArray(int[] nums) {
        // Find the intersection point of the two runners.
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Find the "entrance" to the cycle.
        slow = nums[0];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }

    private static int findStart(int[] arr, int cycleLength) {

        return 0;
    }


    /*
    Approach 1: We modify the input array
    Time: O(N)
    Space: O(1)
     */
    public static int findNumber(int[] nums) { // 1, 4, 4, 3, 2
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
