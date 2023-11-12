package leetcode.topinterview150.matrix;

import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroes {

    // https://leetcode.com/problems/set-matrix-zeroes/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        setZeroes(matrix1);
        Utils.printMatrix(matrix1);
    }

    /*
    Time: O(rows * cols + rows * cols) which simplifies to O(rows * cols)
    Space: O(rows * cols) worst case, all elements in the matrix are stored in sets.
     */
    static void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        Set<Integer> setRows = new HashSet<>();
        Set<Integer> setCols = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    setRows.add(i);
                    setCols.add(j);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // true means we have a zero contained in that row or column
                if (setRows.contains(i) || setCols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
