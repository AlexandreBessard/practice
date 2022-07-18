
package topInterviewQuestion.easy.array;
//https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/770/
public class RotateImage {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        rotate(matrix);
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
    private static void transpose(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                System.out.println("i -> " + i + ", j -> " + j);
                int tmp = matrix[j][i];
                System.out.print("tmp :" + tmp);
                System.out.println();
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }
    private static void reflect(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n / 2; j++) {
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
        for(int i = 0; i < (n + 1) / 2; i++) {
            for(int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

}
