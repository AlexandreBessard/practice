package patternsForCodingInterviews.modifiedBinarySearch;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744165331_81Unit
public class SearchInASortedInfiniteArray {

    public static void main(String[] args) {
        ArrayReader reader = new ArrayReader(
                new int[]{4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30});
        System.out.println(search(reader, 16));
        System.out.println(search(reader, 11));
        reader = new ArrayReader(new int[]{1, 3, 8, 10, 15});
        System.out.println(search(reader, 15));
        System.out.println(search(reader, 200));
    }

    /*
    Time: O(logN) for the first step increasing the bounds and binarySearch O(LogN) O(logN + logN) which is O(logN)
    Space: O(1)
     */
    public static int search(ArrayReader reader, int key) {
        //Find the proper bounds first
        int start = 0;
        int end = 1;
        while (reader.get(end) < key) { //ArrayReader.get(index) will return the number at index (sorted array)
            int newStart = end + 1;
            end += (end - start) * 2; //Increase to double the bounds size
            start = newStart;
        }
        return binarySearch(reader, key, start, end);
    }

    private static int binarySearch(ArrayReader reader, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < reader.get(mid)) {
                end = mid - 1;
            } else if (key > reader.get(mid)) {
                start = mid + 1;
            } else { //Found the key
                return mid;
            }
        }
        return -1;
    }


    static class ArrayReader {
        int[] arr;

        ArrayReader(int[] arr) {
            this.arr = arr;
        }

        public int get(int index) {
            if (index >= arr.length) {
                return Integer.MAX_VALUE;
            } else {
                return arr[index];
            }
        }
    }

}
