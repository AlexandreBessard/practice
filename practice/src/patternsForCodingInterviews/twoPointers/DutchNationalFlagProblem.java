package patternsForCodingInterviews.twoPointers;
////https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743488620_8Unit
public class DutchNationalFlagProblem {

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 0, 2, 1, 0 };
        sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    public static void sort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        for(int i = 0; i <= high;) {
            if(arr[i] == 0) {
                swap(arr, i, low);
                i++;
                low++;
            }
            else if (arr[i] == 1) {
                i++;
            }
            else { //The case for arr[i] == 2
                swap(arr, i, high);
                high--;
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
