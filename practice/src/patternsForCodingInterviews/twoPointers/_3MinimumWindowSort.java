package patternsForCodingInterviews.twoPointers;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743511885_11Unit
public class _3MinimumWindowSort {
/*
Given an array, find the length of the smallest subarray in it which when sorted will sort the whole array.
 */
    public static void main(String[] args) {
        //System.out.println(sort(new int[] { 1, 2, 5, 3, 7, 10, 9, 12 }));
        System.out.println(sort(new int[]{1, -1, 0, 2, 3, 7, 10}));
        /*
        System.out.println(sort(new int[] { 1, 3, 2, 0, -1, 7, 10 }));
        System.out.println(sort(new int[] { 1, 2, 3 }));
        System.out.println(sort(new int[] { 3, 2, 1 }));

         */
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    public static int sort(int[] arr) {
        int low = 0, high = arr.length - 1; //Two pointers
        while(low < arr.length - 1 && arr[low] <= arr[low + 1]) { //Check if increasing order
            low++;
        }
        if(low == arr.length - 1) { //The array is sorted
            return 0;
        }
        //Find the first number out of sorting from the end
        while(high > 0 && arr[high] >= arr[high - 1]) { //Check if decreasing order
            high--;
        }
        //Find the maximum and minimum of the subarray
        int subarrayMax = Integer.MIN_VALUE;
        int subarrayMin = Integer.MAX_VALUE;
        for(int k = low; k <= high; k++) {
            //get min and max value from that subarray
            subarrayMax = Math.max(subarrayMax, arr[k]); //max value of that subarray
            subarrayMin = Math.min(subarrayMin, arr[k]); //min value of that subarray
        }
        //Extend the subarray to include any number which is bigger than the minimum of the subarray
        //these conditions for this edge case for example: [ 1, -1, 0, 2, 3, 7, 10 ]
        while(low > 0 && arr[low - 1] > subarrayMin) { //Expand to the left
            low--;
        }
        while(high < arr.length - 1 && arr[high + 1] < subarrayMax) { //Expand to the right
            high++;
        }

        return high - low + 1;
    }


}
