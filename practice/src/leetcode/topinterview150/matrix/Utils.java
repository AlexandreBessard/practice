package leetcode.topinterview150.matrix;

public class Utils {

    public static void printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            System.out.println("Empty matrix");
            return;
        }
        // Represents the rows
        int rows = matrix.length;
        // represents the length of the first row
        int cols = matrix[0].length;
        System.out.println("Matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

}
