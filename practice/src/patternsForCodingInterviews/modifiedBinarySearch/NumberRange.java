package patternsForCodingInterviews.modifiedBinarySearch;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744155307_80Unit
public class NumberRange {

    public static void main(String[] args) {
        int[] result = findRange(new int[] { 4, 6, 6, 6, 9 }, 6);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = findRange(new int[] { 1, 3, 8, 10, 15 }, 10);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = findRange(new int[] { 1, 3, 8, 10, 15 }, 12);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    }


    /*
    Time: O(logN)
    Space: O(1)
     */
    public static int[] findRange(int[] arr, int key) {
        int[] result = new int[] {-1, -1};
        result[0] = search(arr, key, false);
        if(result[0] != -1) { // no need to search, if 'key' is not present in the input array
            result[1] = search(arr, key, true);
        }
        return result;
    }

    // modified Binary Search
    private static int search(int[] arr, int key, boolean findMaxIndex) {
        int keyIndex = -1; //If element not found, we return -1 by default
        int left = 0;
        int right = arr.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(key < arr[mid]) { //Get a smaller element to match with the key
                right = mid - 1;
            } else if(key > arr[mid]) { //Get a greater element to match with the key
                left = mid + 1;
            } else { // key == arr[mid] -> same element as the key
                keyIndex = mid;
                if(findMaxIndex) {
                    left = mid + 1; // search last index of 'key'
                } else {
                    right = mid - 1; // search first index of 'key'
                }
            }
        }
        return keyIndex;
    }


}
