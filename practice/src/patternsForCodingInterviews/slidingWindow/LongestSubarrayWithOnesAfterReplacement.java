package patternsForCodingInterviews.slidingWindow;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541045705_5Unit
public class LongestSubarrayWithOnesAfterReplacement {

    public static void main(String[] args) {
        System.out.println(
                findLength(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2));
        System.out.println(
                findLength(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3));
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    public static int findLength(int[] arr, int k) {
        int windowStart = 0;
        int maxLength = 0;
        int maxOnesCount = 0;
        //we’ll iterate through the array to add one number at a time in the window
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            if (arr[windowEnd] == 1)
                //we know that we can have a window with 1s repeating maxOnesCount
                maxOnesCount++;
            //If we have more than ‘k’ remaining 0s,
            // we should shrink the window as we are not allowed to replace more than ‘k’ 0s.
            //We remove from the window all the 1s. if stil more than k, it means we have
            //more 0s than expected, we shrink the window.
            if ((windowEnd - windowStart) + 1 - maxOnesCount > k) { //We do not count '1'
                //We have more than k'Os elements, we shrink the window
                if (arr[windowStart] == 1) {
                    maxOnesCount--; //remove 1
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, (windowEnd - windowStart) + 1);
        }
        return maxLength;
    }
}
