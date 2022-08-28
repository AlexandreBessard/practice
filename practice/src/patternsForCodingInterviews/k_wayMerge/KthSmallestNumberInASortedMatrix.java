package patternsForCodingInterviews.k_wayMerge;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744424045_110Unit
public class KthSmallestNumberInASortedMatrix {

    public static void main(String[] args) {
        int matrix[][] = {
                { 2, 6, 8 },
                { 3, 7, 10 },
                { 5, 8, 11 }
        };
        int result = findKthSmallest(matrix, 5);
        System.out.print("Kth smallest number is: " + result);
    }

    public static int findKthSmallest(int[][] matrix, int k) {

        return 0;
    }

}
