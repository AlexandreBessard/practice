package academy.learnprogramming.sorting.mergeSort;

public class Main {

    public static void main(String[] args) {
        int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };

        mergeSort(intArray, 0, intArray.length);

        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }

    // { 20, 35, -15, 7, 55, 1, -22 }
    //Call recursively
    public static void mergeSort(int[] input, int start, int end) {
        //One element array
        if (end - start < 2) {
            //Stop recursion with only 1 element array. By definition one element is sorted.
            return;
        }

        int mid = (start + end) / 2;
        //Splitting phase (Preparation phase) We do not create array instance
        //Always get index + 1 has mid
        mergeSort(input, start, mid); // Left partition (mid is one greater)
        mergeSort(input, mid, end); // Right partition (mid is one greater)
        //Sort
        merge(input, start, mid, end);
    }

    // { 20, 35, -15, 7, 55, 1, -22 }
    //Merge left and right
    //Merge from bottom the top
    public static void merge(int[] input, int start, int mid, int end) {

        //We always merge sorted arrays
        //If left partition is sorted
        //mid is the first element on the right array (right side)
        //mid - 1 -> is the last element in the left array (left side)
        if (input[mid - 1] <= input[mid]) {
            return;
        }
        //Must sort
        int i = start;
        int j = mid;
        int tempIndex = 0; //track index for temporary array when we try to copy
        //Create temporary array
        int[] temp = new int[end - start];
        while (i < mid && j < end) {
            temp[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++];
        }

        //See other solution to have another way of doing this
        /*
        We use a temporary array for merging
        Handling remaining element on left partition, we have to copy in temp array.
        Remaining right partition, we do not have to do anything.
         */
        System.arraycopy(input, i, input, start + tempIndex, mid - i);
        System.arraycopy(temp, 0, input, start, tempIndex);


    }

}
