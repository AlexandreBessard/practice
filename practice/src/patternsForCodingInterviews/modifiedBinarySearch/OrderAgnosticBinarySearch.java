package patternsForCodingInterviews.modifiedBinarySearch;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744135675_77Unit
public class OrderAgnosticBinarySearch {

    public static void main(String[] args) {
        System.out.println(search(new int[] { 4, 6, 10 }, 10));
        System.out.println(search(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 5));
        System.out.println(search(new int[] { 10, 6, 4 }, 10));
        System.out.println(search(new int[] { 10, 6, 4 }, 4));
    }

    /*
    Time: O(LogN)
    Space: O(1)
     */
    public static int search(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        boolean isAscendingOrder = arr[start] < arr[end];
        while(start <= end) {
            // calculate the middle of the current range
            int mid = start + (end - start) / 2;
            if(key == arr[mid])
                return mid;

            if(isAscendingOrder) { //Ascending order
                if(key < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid - 1;
                }
            } else { //Descending order
                if(key > arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }

        return 0;
    }

}
