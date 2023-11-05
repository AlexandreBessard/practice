package GrokkingCodingInterviewPatterns.TwoPointers.BinarySearch;

public class OrderAgnosticBinarySearch {

    // https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639f1a8b44223ca42ca4a723

    /*
    Given a sorted array of numbers, find if a given number ‘key’ is present in the array.
    Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order.
    You should assume that the array can have duplicates
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        // Output 4 (index)
        System.out.println(search(nums, 5));
        nums = new int[]{10, 6, 4};
        System.out.println(search(nums, 4));
    }

    /*
    Time: O(log n), where n is the size of the array.
    Space: O(1)
     */
    static int search(int[] arr, int key) {
        // indexes
        int start = 0;
        int end = arr.length - 1;

        boolean isAscending = arr[start] < arr[end];

        while (start <= end) {
            // calculate the middle of the current range
            int mid = start + (end - start) / 2;
            if (key == arr[mid]) {
                return mid;
            }
            if (isAscending) { // is ascending order
                if (key < arr[mid]) {
                    // need to find a smaller element because the key is too small
                    end = mid - 1;
                } else {
                    // the key is bigger than the middle element, need a bigger element
                    start = mid + 1;
                }
            } else { // descending order
                if (key < arr[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        // Element not found
        return -1;
    }

}
