package algorithms.kadanes;
//https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
public class Kadane {

    public static void main(String[] args) {
        int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(maxSubArraySum(a));
        maxSubArraySumPrintSubarray(a, a.length);
    }

    //Given an array arr[] of size N. The task is to find the sum of the contiguous subarray
    // within nums arr[] with the largest sum.
    /*
    Time : O(n)
    space: O(1)
     */
    static int maxSubArraySum(int[] nums) {
        int max_so_far = Integer.MIN_VALUE; // store the max sum of contiguous subarray SO FAR
        int max_ending_here = 0; //store max sum contiguous subarray at current index
        for (int i = 0; i < nums.length; i++)
        {
            max_ending_here = max_ending_here + nums[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0; //Reinitialize value
        }
        return max_so_far;
    }

    //To print the subarray with the maximum sum,
    static void maxSubArraySumPrintSubarray(int a[], int size)
    {
        int max_so_far = Integer.MIN_VALUE,
                max_ending_here = 0,
                start = 0,
                end = 0,
                s = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here += a[i];

            if (max_so_far < max_ending_here)
            {
                max_so_far = max_ending_here;
                start = s;
                end = i;
            }
            if (max_ending_here < 0)
            {
                max_ending_here = 0;
                s = i + 1;
            }
        }
        System.out.println("Maximum contiguous sum is "
                + max_so_far);
        System.out.println("Starting index " + start);
        System.out.println("Ending index " + end);
    }
}
