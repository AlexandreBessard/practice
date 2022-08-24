package patternsForCodingInterviews.modifiedBinarySearch;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744174253_82Unit
public class MinimumDifferenceElement {

    public static void main(String[] args) {
        System.out.println(
                searchMinDiffElement(new int[]{4, 6, 10}, 7));
        System.out.println(
                searchMinDiffElement(new int[]{4, 6, 10}, 4));
        System.out.println(
                searchMinDiffElement(new int[]{1, 3, 8, 10, 15}, 12));
        System.out.println(
                searchMinDiffElement(new int[]{4, 6, 10}, 17));
    }

    /*
    Time: O(logN)
    Space: O(1)
     */
    public static int searchMinDiffElement(int[] arr, int key) {
        if (key < arr[0])
            return arr[0];
        if (key > arr[arr.length - 1])
            return arr[arr.length - 1];
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else { //If key == arr[mid]
                return arr[mid];
            }
        }
        // at the end of the while loop, 'start == end+1'
        // we are not able to find the element in the given array
        // return the element which is closest to the 'key'
        if ((arr[start] - key) < (key - arr[end])) {
            return arr[start];
        }
        return arr[end];
    }


}
