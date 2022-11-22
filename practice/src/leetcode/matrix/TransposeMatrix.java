package leetcode.matrix;
//https://leetcode.com/problems/transpose-matrix/
public class TransposeMatrix {
    //See image to see how to transpose a matrix : 'transposeMatrix.png'
/*
The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.
Example 1:

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[1,4,7],[2,5,8],[3,6,9]]
Example 2:

Input: matrix = [[1,2,3],[4,5,6]]
Output: [[1,4],[2,5],[3,6]]
 */
    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        //Output:
        /*
        [ 1, 4, 7 ]
        [ 2, 5, 8 ]
        [ 3, 6, 9 ]
         */
    }

    //Follow Up, transpose the matrix in-place
    /*
    Time: O(n * m)
    Space: O(1)
     */
    public static int[][] transposeInPlace(int[][] matrix) {
        int rows = matrix.length;
        for(int row = 0; row < rows; row++) {
            for(int col = row + 1; col < rows; col++) { //Loop through each column
                //swap
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[row][col] = temp;
            }
        }
        return matrix;
    }

    /*
    Time: O(R * C)
    Space: O(R * C)
     */
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] res = new int[rows][cols];
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                res[col][row] = matrix[row][col];
            }
        }
        return res;
    }



}
