package topInterviewQuestion.topInterviewQuestions.dynamicProgramming;

public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int[] nums2 = {-2,1,-3,4,-1};
        //Output 6
        //contiguous subarray containing at least one number.
        //Explanation: [4,-1,2,1] has the largest sum = 6.
        maxSubArrayDivideAndConquer(nums2);
        System.out.println(maxSubArrayKadaneAlgo(new int[]{-2,1,-3,4,-1}));
        System.out.println(maxSubArrayKadaneAlgo(new int[]{-2,1,-3,4,-1,2,1,-5,4}));

    }

    private static int[] numsArray;
    //Approach 3: Divide and conquer (Advanced)
    /*
    Time complexity: O(N log N ) since that's how many time an array can be split
    in half.
    Space complexity: O(log N) Is occupied by the stack
     */
    static int maxSubArrayDivideAndConquer(int[] nums) {
        numsArray = nums;
        return findBestArray(0, numsArray.length - 1);
    }
    private static int findBestArray(int left, int right) {
        if(left > right)
            return Integer.MIN_VALUE;
        int mid = Math.floorDiv(left + right, 2); //Take the mid equivalent of (left + right) / 2;
        int curr = 0;
        int bestLeftSum = 0;
        int bestRightSum = 0;
        //Iterate from middle to the  beginning
        for(int i  = mid - 1; i >= left; i--) {
            curr += numsArray[i];
            bestLeftSum = Math.max(bestLeftSum, curr);
        }
        //Reset curr and iterate from mid to the end
        curr = 0;
        for(int i = mid + 1; i <= right; i++) {
            curr += numsArray[i];
            bestRightSum = Math.max(bestRightSum, curr);
        }
        int bestCombinedSum = numsArray[mid] + bestLeftSum + bestRightSum;
        //Find best subarray possible from both halves
        int leftHalf = findBestArray(left, mid - 1);
        int rightHalf = findBestArray(mid + 1, right);
        return Math.max(bestCombinedSum, Math.max(leftHalf, rightHalf));
    }

    //Approach 2: Dynamic Programming Kadane Algo
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static int maxSubArrayKadaneAlgo(int[] nums) {
        int currentSubarray = nums[0];
        int maxSubarray = nums[0];
        for(int i = 1; i < nums.length; i++) {
            currentSubarray = currentSubarray + nums[i];

            if(currentSubarray > maxSubarray) {
                maxSubarray = currentSubarray;
            }
            if(currentSubarray < 0) { //if true reinitialize, new subarray
                currentSubarray = 0;
            }
        }
        return maxSubarray;
    }

    //Approach 1: Burt force
    /*
    Time complexity: O(N²)
    Space complexity: O(1)
     */
    static int maxSubArray(int[] nums) {
        int maxSubarray = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            int currentSubarray = 0;
            //Loop through each element of that current subarray 'i'
            for(int j = i; j < nums.length; j++) {
                currentSubarray += nums[j];
                maxSubarray = Math.max(maxSubarray, currentSubarray);
            }
        }
        return maxSubarray;
    }


}
