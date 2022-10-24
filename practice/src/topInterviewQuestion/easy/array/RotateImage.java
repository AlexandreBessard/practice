
package topInterviewQuestion.easy.array;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/770/
public class RotateImage {

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
    Result after transpose:
                {1,4,7},
                {2,5,8},
                {3,6,9}
     */
    private static void transpose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }

    private static void reflect(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) { //rows
            for (int j = 0; j < n / 2; j++) { //cols
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
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
