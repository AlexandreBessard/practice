package leetcode.array;

//https://leetcode.com/problems/maximum-length-of-repeated-subarray/
public class MaximumLengthOfRepeatedSubarray {
    /*
    Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
     Example 1:
     Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
    Output: 3
    Explanation: The repeated subarray with maximum length is [3,2,1].

    Example 2:
    Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
    Output: 5
    Explanation: The repeated subarray with maximum length is [0,0,0,0,0].

     */
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 2, 1};
        int[] nums2 = {3, 2, 1, 4, 7};
        System.out.println(findLength(nums1, nums2));
        System.out.println(findLength(nums1, nums2));
        int[] nums3 = {2, 3, 4, 5, 7};
        int[] nums4 = {3, 4, 5, 8};
        System.out.println(findLengthDFS(nums3, nums4));
    }

    //Correction: https://leetcode.com/problems/maximum-length-of-repeated-subarray/discuss/1325431/Easy-Java-DFS
    /*
    Approach 2: DFS
    Time: O(MN)
    Space: O(MN)
     */
    public static int findLengthDFS(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        boolean[][] state = new boolean[m][n];
/*
    0 0 0 0
	1 0 0 0
	0 1 0 0
	0 0 1 0
	0 0 0 0

	1 : means true
 */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                state[i][j] = nums1[i] == nums2[j];
            }
        }

        // DFS to find the max length the true diagnally
        //Find the longest diagonal true in the array (matrix)
        int res = 0;
        //Loop through each element to find true value
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (state[i][j]) {
                    //Find the longest diagnal true
                    res = Math.max(res, dfs(state, i, j));
                }
            }
        }
        return res;
    }

    private static int dfs(boolean[][] state, int i, int j) {
        //Base case if out of bound or not same letter
        //True if out of bound
        if (i < 0 || i >= state.length || j < 0 || j >= state[0].length
                || !state[i][j])  { //not same letter
            return 0;
        }
        else {
            state[i][j] = false; //Not necessary line of code
            //Count by checking diagnally the next element
            //Recursion
            int count = dfs(state, i + 1, j + 1);
            return count + 1;
        }
    }


    // The initial position and the directions in which we slide. One step means shifting the top array by one position (index) to the right, or the the bottom array by one position (index) to the left:
    //          [1,2,3,2,1]   -->
    //           <--    [3,2,1,4,7]

    //          [1,2,3,2,1]
    //                [3,2,1,4,7]

    //          [1,2,3,2,1]      -->
    //     <--      [3,2,1,4,7]

    //  and so on
    //https://leetcode.com/problems/maximum-length-of-repeated-subarray/discuss/109059/O(mn)-time-O(1)-space-solution
    /*
    Sliding window
     */
    public static int findLength(int[] nums1, int[] nums2) {
        int result = 0;
        int latestElementWithArrayCombined =
                (nums1.length + nums2.length) - 1;
        for (int i = 0; i < latestElementWithArrayCombined - 1; i++) {
            // The current overlapping window is A[aStart, Math.min(A.length, B.length)] and B[bStart, Math.min(A.length, B.length)].
            int aStart = Math.max(0, nums1.length - 1 - i);
            int bStart = Math.max(0, i - (nums1.length - 1));
            //System.out.println("aStart -> " + aStart + " bStart -> " + bStart);
            int numConsecutiveMatches = 0;
            for (int aIdx = aStart, bIdx = bStart;
                 aIdx < nums1.length && bIdx < nums2.length;
                 aIdx++, bIdx++) {
                // Maintain number of equal consecutive elements in the current window (overlap) and the max number ever computed.
                numConsecutiveMatches =
                        nums1[aIdx] == nums2[bIdx] ? numConsecutiveMatches + 1 : 0;
                result = Math.max(result, numConsecutiveMatches);
            }
        }
        return result;
    }


}
