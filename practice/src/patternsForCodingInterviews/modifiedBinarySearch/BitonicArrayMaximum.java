package patternsForCodingInterviews.modifiedBinarySearch;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744182053_83Unit
public class BitonicArrayMaximum {

    public static void main(String[] args) {
        System.out.println(findMax(new int[] { 1, 3, 8, 12, 4, 2 }));
        System.out.println(findMax(new int[] { 3, 8, 3, 1 }));
        System.out.println(findMax(new int[] { 1, 3, 8, 12 }));
        System.out.println(findMax(new int[] { 10, 9, 8 }));
    }

    /*
    Time: O(logN)
    Space: O(1)
     */
    public static int findMax(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while(start < end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] > arr[mid + 1]) { // we are in the second (descending) part of the bitonic array
                //Therefore, our required number could either be pointed out by middle or will be before middle
                end = mid;
            } else { // arr[middle] < arr[middle + 1]
                //we are in the first (ascending) part of the bitonic array
                //Therefore, the required number will be after middle
                start = mid + 1;
            }
        }
        // at the end of the while loop, 'start == end'
        return arr[start];
    }

}
