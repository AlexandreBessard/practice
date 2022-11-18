
package leetcode.array;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/770/
public class RotateImage {
/*
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
DO NOT allocate another 2D matrix and do the rotation.
 */
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        //rotate(matrix);
        System.out.println(matrix);
        rotateApproach2(matrix);
    }


    //Approach 2: Reverse on Diagonal and then reverse left to Right
    /*
    Time complexity: O(M)
    Space complexity: 0(1)
     */
    static void rotateApproach2(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }
    /*
                {1,2,3},
                {4,5,6},
                {7,8,9}
    Result after transpose:
                {1,4,7},
                {2,5,8},
                {3,6,9}
     */
    private static void transpose(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = row + 1; col < matrix.length; col++) {
                int tmp = matrix[col][row];
                matrix[col][row] = matrix[row][col];
                matrix[row][col] = tmp;
            }
        }
    }
/*
                {7,4,1},
                {8,5,2},
                {9,6,3}
 */
    private static void reflect(int[][] matrix) {
        int n = matrix.length;
        for (int row = 0; row < n; row++) { //rows
            for (int col = 0; col < n / 2; col++) { //cols
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[row][n - col - 1];
                matrix[row][n - col - 1] = tmp;
            }
        }
    }

    //Approach 1 : Rotate Groups of Four Cells
    /*
    Time complexity: O(M)
    Space complexity: O(1)
     */
    static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i++) { //rows
            for (int j = 0; j < n / 2; j++) { //cols
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

}
