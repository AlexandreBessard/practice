package leetcode.matrix;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/spiral-matrix/
public class SpiralMatrix {
/*
Given an m x n matrix, return all elements of the matrix in spiral order.
 */
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix2 = {
                {1, 2},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("matrix 1 : " + spiralOrder(matrix));
        System.out.println("matrix 2 : " + spiralOrder(matrix2));
    }

    //Correction: https://leetcode.com/problems/spiral-matrix/discuss/20599/Super-Simple-and-Easy-to-Understand-Solution
    /*
    Time: O(M * N) -> we visit each element once.
    Space: O(1)
     */
    static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0) return res;
        int n = matrix.length, m = matrix[0].length;
        int up = 0, down = n - 1;
        int left = 0, right = m - 1;
        while (res.size() < n * m) { //Chekc if res is smaller than the total element in the matrix
            //(1, 2, 3)
            for (int j = left; j <= right && res.size() < n * m; j++) //From left to right
                res.add(matrix[up][j]);

            //Excluded the latest element  (6)
            //From top (excluded) to bottom right side (excluded)
            for (int i = up + 1; i <= down - 1 && res.size() < n * m; i++)
                res.add(matrix[i][right]); //Right gets the latest element from the row 'i'

            //From right to left (9, 8, 7)
            for (int j = right; j >= left && res.size() < n * m; j--)
                res.add(matrix[down][j]);

            //Exclude the first element and the latest element (4)
            for (int i = down - 1; i >= up + 1 && res.size() < n * m; i--) { // (bottom to top)
                System.out.println("matrix[i][left] --> " + matrix[i][left]);
                res.add(matrix[i][left]);
            }
            //Go to the next square (inner square if matrix.length = 4 && matrix[0].length = 4
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }

}
