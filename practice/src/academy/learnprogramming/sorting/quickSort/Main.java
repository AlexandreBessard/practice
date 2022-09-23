package academy.learnprogramming.sorting.quickSort;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };

        quickSort(intArray, 0, intArray.length);

        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + ", ");
        }
    }

    /*
    The average time complexity of quick sort is O(N log(N)).
    The derivation is based on the following notation: T(N) = Time Complexity of Quick Sort for input of size N.
    At each step, the input of size N is broken into two parts say J and N-J.
    Space complexity: O(1)
     */
    //Divid and conquer group of algorithm and it is an in-place
    /*
     non-stable (doesn't guarantee relative order of same-value elements after sorting) sorting algorithm
     */
    public static void quickSort(int[] input, int start, int end) {
        if (end - start < 2) { //If it remains 2 elements to sort, stop here
            return;
        }
        int pivotIndex = partition(input, start, end);
        quickSort(input, start, pivotIndex); //one sub list with element less than pivot
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]); //one sublist with element greater than pivot
        }
        quickSort(input, pivotIndex + 1, end);
    }

    public static int partition(int[] input, int start, int end) {
        // This is using the first element as the pivot
        int pivot = input[start];
        int i = start;
        int j = end;

        while (i < j) {
            // NOTE: empty loop body
            while (i < j && input[--j] >= pivot){ //if element of right is equal or bigger than pivot, this element stay on the right side
                //Body is empty
            }
            if (i < j) {
                input[i] = input[j];
            }
            // NOTE: empty loop body
            while (i < j && input[++i] <= pivot){
                //Body is empty
            }
            if (i < j) {
                input[j] = input[i];
            }

        }
        input[j] = pivot;
        return j;
    }
}
