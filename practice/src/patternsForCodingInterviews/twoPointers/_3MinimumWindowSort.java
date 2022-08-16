package patternsForCodingInterviews.twoPointers;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743511885_11Unit
public class _3MinimumWindowSort {

    public static void main(String[] args) {
        System.out.println(sort(new int[] { 1, 2, 5, 3, 7, 10, 9, 12 }));
        System.out.println(sort(new int[] { 1, 3, 2, 0, -1, 7, 10 }));
        System.out.println(sort(new int[] { 1, 2, 3 }));
        System.out.println(sort(new int[] { 3, 2, 1 }));
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    public static int sort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while(low < arr.length - 1 && arr[low] <= arr[low + 1]) {
            low++;
        }
        if(low == arr.length - 1) { //The array is sorted
            return 0;
        }
        //Find the first number out of sorting from the end
        while(high > 0 && arr[high] >= arr[high - 1]) {
            high--;
        }
        //Find the maximum and minimum of the subarray
        int subarrayMax = Integer.MIN_VALUE;
        int subarrayMin = Integer.MAX_VALUE;
        for(int k = low; k <= high; k++) {
            subarrayMax = Math.max(subarrayMax, arr[k]);
            subarrayMin = Math.min(subarrayMin, arr[k]);
        }
        //Extend the subarray to include any number which is bigger than the minimum of the subarray
        while(low > 0 && arr[low - 1] > subarrayMin) {
            low--;
        }
        while(high < arr.length - 1 && arr[high + 1] < subarrayMax) {
            high++;
        }

        return high - low + 1;
    }


}
