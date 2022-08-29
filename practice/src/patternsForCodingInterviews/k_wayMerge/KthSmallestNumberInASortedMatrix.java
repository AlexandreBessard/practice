package patternsForCodingInterviews.k_wayMerge;

import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744424045_110Unit
public class KthSmallestNumberInASortedMatrix {

    public static void main(String[] args) {
        int result = Integer.MIN_VALUE;
        int matrix[][] = {
                {2, 6, 8},
                {3, 7, 10},
                {5, 8, 11}
        };
        result = findKthSmallest(matrix, 5);
        System.out.print("Kth smallest number is: " + result + " \n\n");

        System.out.println("Binary Search : \n");

        matrix = new int[][]{
                {2, 6, 8},
                {3, 7, 10},
                {5, 8, 11}
        };
        result = findKthSmallestBinarySearch(matrix, 5);
        System.out.println("Kth smallest number is: " + result);

        matrix = new int[][]{ { -5 } };
        result = findKthSmallestBinarySearch(matrix, 1);
        System.out.println("Kth smallest number is: " + result);

        matrix = new int[][]{ { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
        result = findKthSmallestBinarySearch(matrix, 5);
        System.out.println("Kth smallest number is: " + result);

        matrix = new int[][]{ { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
        result = findKthSmallestBinarySearch(matrix, 8);
        System.out.println("Kth smallest number is: " + result);
    }

    public static int findKthSmallest(int[][] matrix, int k) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(
                (n1, n2) -> matrix[n1.row][n1.col] - matrix[n2.row][n2.col]
        );
        // put the 1st element of each row in the min heap
        // we don't need to push more than 'k' elements in the heap
        for (int i = 0; i < matrix.length && i < k; i++) {
            minHeap.add(new Node(i, 0));
        }
        // take the smallest (top) element form the min heap, if the running count is equal
        // to k return the number. if the row of the top element has more elements, add the
        // next element to the heap
        int numberCount = 0;
        int result = 0;
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            result = matrix[node.row][node.col];
            if (++numberCount == k) {
                break;
            }
            node.col++;
            if (matrix[0].length > node.col) {
                minHeap.add(node);
            }
        }
        return result;
    }

    static class Node {
        int row;
        int col;
        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    //Alternate approach using Binary Search
    /*
    Time: O(log(max - min)) each iteration take O(N) for counting
    Overall: O(N * log(max - min))
    Space complexity: O(1)
     */
    public static int findKthSmallestBinarySearch(int[][] matrix, int k) {
        int n = matrix.length;
        int start = matrix[0][0], end = matrix[n - 1][n - 1];
        while (start < end) {
            // the mid number is NOT necessarily an element in the matrix
            int mid = start + (end - start) / 2;
            // first number is the smallest and the second number is the largest
            // Keep track of the smallest number greater than the middle
            int[] smallLargePair = { matrix[0][0], matrix[n - 1][n - 1] };
            // count all the numbers smaller than or equal to middle in the matrix
            int count = countLessEqual(matrix, mid, smallLargePair);

            if (count == k)
                return smallLargePair[0];

            if (count < k)
                start = smallLargePair[1]; // search higher
            else
                end = smallLargePair[0]; // search lower
        }
        return start;
    }
    private static int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
        int count = 0;
        int n = matrix.length, row = n - 1, col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] > mid) {
                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;
            } else {
                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count += row + 1;
                col++;
            }
        }
        return count;
    }
}
