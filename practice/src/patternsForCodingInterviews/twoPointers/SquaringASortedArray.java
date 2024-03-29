package patternsForCodingInterviews.twoPointers;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743435284_3Unit
public class SquaringASortedArray {
/*
Given a sorted array, create a new array containing squares of all the numbers of the input array in the sorted order.
 */
    public static void main(String[] args) {
        int[] result = makeSquares(new int[] { -2, -1, 0, 2, 3 });
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();

        result = makeSquares(new int[] { -3, -1, 0, 1, 2 });
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();
    }

    /*
    Time: O(N)
    Space: O(N)
    Array is sorted
     */
    public static int[] makeSquares(int[] arr) {
        int n = arr.length;
        int[] squares = new int[n];
        int highestSquareIdx = n - 1; //Pointer which go backward during the execution of that method
        int left = 0, right = arr.length - 1;
        while(left <= right) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];
            if(leftSquare > rightSquare) {
                squares[highestSquareIdx--] = leftSquare;
                left++;
            } else {
                squares[highestSquareIdx--] = rightSquare;
                right--;
            }
        }
        return squares;
    }


}
