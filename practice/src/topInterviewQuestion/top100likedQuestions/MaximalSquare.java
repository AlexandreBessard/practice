package topInterviewQuestion.top100likedQuestions;

import java.util.Arrays;

//https://leetcode.com/problems/maximal-square/
public class MaximalSquare {
    /*
    Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     */
    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        char[][] matrix2 = {
                {'1', '0', '1'},
                {'1', '1', '0'},
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'}
        };
        char[][] matrix3 = {
                {'1', '1', '1'},
                {'1', '1', '1'}
        };
        //System.out.println(maximalSquare(matrix3));
        System.out.println(maximalSquareDP(matrix2));
        System.out.println(maximalDPTopDown(matrix2));
    }

    //Explanation: https://www.youtube.com/watch?v=6X7Ha2PrDmM
    //Recursive: Top down
    static int ROWS;
    static int COLS;
    static char[][] matrix;
    static int maxLength = 0;
    //Key: row, Value: column
    static int[][] cache;

    static int maximalDPTopDown(char[][] matrix) {
        MaximalSquare.matrix = matrix;
        ROWS = matrix.length;
        COLS = matrix[0].length;
        cache = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            Arrays.fill(cache[i], -1);
        }
        helper(0, 0); //top left element
        return maxLength * maxLength;
    }

    static int helper(int row, int col) {
        //Base case
        if (row >= ROWS || col >= COLS) {
            return 0;
        }
        if (cache[row][col] == -1) { //True if NOT in the cache
            int down = helper(row + 1, col); //Check Down
            int right = helper(row, col + 1); //Right position
            int diag = helper(row + 1, col + 1); //Check Diagonally
            cache[row][col] = 0;
            if (matrix[row][col] == '1') { //
                //Takes the minimum off all of these 3 values: down, right and diag
                cache[row][col] = 1 + Math.min(down, Math.min(right, diag));
                //Get the max length so far
                maxLength = Math.max(cache[row][col], maxLength);
            }
        }
        return cache[row][col];
    }

    //Approach 2: DP
    /*
    Time: O(mn)
    Space: O(n)
     */
    static int maximalSquareDP(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        // for convenience, we add an extra all zero column and row
        // outside of the actual dp table, to simpify the transition
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(
                            Math.min(dp[i][j - 1],//Check on the left side
                                    dp[i - 1][j]), //Check on the top
                            dp[i - 1][j - 1]) + 1; //Diagonal + 1 (Prev)
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    //Brut force
    /*
    Time: O((mn)²)
    Space: O(1)
     */
    static int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;
        int maxSquareLen = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                //Top left corner
                if (matrix[row][col] == '1') { //Check element from top left of the square
                    int squareLen = 1;
                    boolean flag = true; //If false means we do not have a square (0 element in that square)
                    while ((squareLen + row) < rows && (squareLen + col) < cols && flag) {
                        for (int k = col; k <= squareLen + col; k++) { //It true, ensure it is a square check
                            if (matrix[row + squareLen][k] == '0') { //Check element below from top
                                flag = false;
                                break;
                            }
                        }
                        for (int k = row; k <= squareLen + row; k++) { //Check the right next element
                            if (matrix[k][col + squareLen] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag)
                            squareLen++; //If we have square, increment by 1. At the end we are going multiply by itself.
                        //exmple: We have one square: 2 * 2 == 4
                        /*
                        1, 1
                        1, 1
                         */
                    }
                    if (maxSquareLen < squareLen) {
                        maxSquareLen = squareLen;
                    }
                }
            }
        }
        return maxSquareLen * maxSquareLen;
    }

}
