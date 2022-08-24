package patternsForCodingInterviews.modifiedBinarySearch;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744190772_84Unit
public class _1SearchBitonicArray {

    public static void main(String[] args) {
        System.out.println(search(new int[]{1, 3, 8, 4, 3}, 4));
        System.out.println(search(new int[]{3, 8, 3, 1}, 8));
        System.out.println(search(new int[]{1, 3, 8, 12}, 12));
        System.out.println(search(new int[]{10, 9, 8}, 10));
    }

    /*
    Time: O(LogN)
    Space: O(1)
     */
    public static int search(int[] arr, int key) {
        int maxIndex = findMax(arr);
        //Now, we can break the array into two sub-arrays:
        //Array from index ‘0’ to maxIndex, sorted in ascending order.
        int keyIndex = binarySearch(arr, key, 0, maxIndex);
        if (keyIndex != -1) { //Allow us to find the key with the smaller index first
            return keyIndex;
        }
        //Array from index maxIndex+1 to array_length-1, sorted in descending order.
        return binarySearch(arr, key, maxIndex + 1, arr.length - 1);
    }

    // order-agnostic binary search
    private static int binarySearch(int[] arr, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key == arr[mid])
                return mid;
            if (arr[start] < arr[end]) { //Ascending order
                if (key < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else { //Descending order
                if (key > arr[mid]) {
                    end = mid - 1;
                } else { // key < arr[mid]
                    start = mid + 1;
                }
            }
        }
        return -1;
    }


    //Find index of the maximum value in a bitonic array
    private static int findMax(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > arr[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        // at the end of the while loop, 'start == end'
        return start;
    }

}
