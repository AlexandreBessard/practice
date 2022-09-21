package topInterviewQuestion.facebook.topMostFrequentQuestions;

import java.util.Arrays;

//https://leetcode.com/problems/diagonal-traverse/
public class DiagonalTraverse {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        //Output: [1,2,4,7,5,3,6,8,9]
        System.out.println(Arrays.toString(findDiagonalOrder(matrix)));
    }

    //Correction: https://leetcode.com/problems/diagonal-traverse/discuss/97712/Concise-Java-Solution
    /*
    Time: O(m * n)
    Space: 0(1)
     */
    static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new int[0];
        int m = matrix.length;
        int n = matrix[0].length;
        int[] result = new int[m * n];
        int row = 0, col = 0, d = 0;
        int[][] dirs = {
                {-1, 1}, //Diagonal up right
                {1, -1} //Diagonal down left
        };
        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            row += dirs[d][0];
            col += dirs[d][1];

            //If out of bottom border (row >= m) then row = m - 1; col += 2; change walk direction.
            if (row >= m) {
                row = m - 1;
                col += 2;
                d = 1 - d;
            } //if out of right border (col >= n) then col = n - 1; row += 2; change walk direction.
            if (col >= n) {
                col = n - 1;
                row += 2;
                d = 1 - d;
            } //if out of top border (row < 0) then row = 0; change walk direction.
            if (row < 0) {
                row = 0;
                d = 1 - d;
            }//if out of left border (col < 0) then col = 0; change walk direction
            if (col < 0) {
                col = 0;
                d = 1 - d;
            }
        }
        return result;
    }

}
