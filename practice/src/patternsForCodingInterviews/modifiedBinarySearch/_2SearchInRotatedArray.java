package patternsForCodingInterviews.modifiedBinarySearch;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744196413_85Unit
public class _2SearchInRotatedArray {

    public static void main(String[] args) {
        System.out.println(search(new int[]{10, 15, 1, 3, 8}, 15));
        System.out.println(search(
                new int[]{4, 5, 7, 9, 10, -1, 2}, 10));
        System.out.println(searchDuplicate(new int[]{3, 7, 3, 3, 3}, 7));
        System.out.println("-> " + search(new int[]{2, 1, 0, 4, 5, 6, 7}, 0));

    }

    /*
    IF Array has duplicate
    Time: O(LogN)
    Space: O(1)
     */
    public static int searchDuplicate(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            // the only difference from the previous solution,
            // if numbers at indexes left, mid, and right are same, we can't choose a side
            // the best we can do, is to skip one number from both ends as key != arr[mid]
            if ((arr[left] == arr[mid]) && (arr[right] == arr[mid])) {
                ++left; //Move to the next element
                --right;
            //Decide which part of the array is sorted
            } else if (arr[left] <= arr[mid]) { // left side is sorted in ascending order (left part)
                if (key >= arr[left] && key < arr[mid]) { //Check if it is in the range
                    right = mid - 1;
                } else { //key > arr[mid]
                    left = mid + 1;
                }
            } else { // right side is sorted in ascending order (right part)
                if (key > arr[mid] && key <= arr[right]) { //check if it is in the range
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        // we are not able to find the element in the given array
        return -1;
    }

    /*
    IF Array Does not have duplicate
    Time: O(LogN)
    Space: O(1)
     */
    public static int search(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[start] <= arr[mid]) { // left side is sorted in ascending order
                if (key >= arr[start] && key < arr[mid]) { //Between that ascending order range
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else { // right side is sorted in ascending order
                if (key > arr[mid] && key <= arr[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        // we are not able to find the element in the given array
        return -1;
    }

}
