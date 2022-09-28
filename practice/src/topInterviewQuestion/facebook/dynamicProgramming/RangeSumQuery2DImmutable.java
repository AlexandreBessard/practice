package topInterviewQuestion.facebook.dynamicProgramming;

//https://leetcode.com/explore/interview/card/facebook/55/dynamic-programming-3/3037/
public class RangeSumQuery2DImmutable {
    public static void main(String[] args) {
        int matrix[][] = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5},
        };
        int[][] smallMatrix = {
                {3, 0, 1},
                {5, 6, 3},
                {1, 2, 0}
        };
        var obj = new NumMatrixBrutForce(matrix);
        int param_1 = obj.sumRegionBrutForce(1, 1, 2, 2);
        var obj1 = new NumMatrixCachingRows(matrix);
        System.out.println(obj1.sumRegionCachingRows(1, 1, 2, 2));
        var obj2 = new NumMatrixCachingSmarter(smallMatrix);
        System.out.println(obj2.sumRegionCachingSmarter(
                1, 1, //upper left corner
                2, 2)); // lower right corner
        System.out.println(param_1);
    }
}

//Expected answer because it is asked to solve it O(1) time complexity for the method ONLY
//Help to understand the concept: https://computersciencesource.wordpress.com/2010/09/03/computer-vision-the-integral-image/
class NumMatrixCachingSmarter {
    //Space: O(mn)
    private int[][] dp;

    //Time: O(mn) for the pre-computation
    NumMatrixCachingSmarter(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                dp[r + 1][c + 1] = dp[r + 1][c]  //add value from the left side
                        + dp[r][c + 1]  //Add value from above
                        + matrix[r][c] //Add current value from the matrix
                        - dp[r][c]; //Subtract upper left corner from dp
            }
        }
        System.out.println("Done");
    }

    //Time: O(1)
    //See the pattern with a screenshot of 'dp' for better understanding (easy)
    public int sumRegionCachingSmarter(int row1, int col1, int row2, int col2) {
        System.out.println("dp[row2 + 1][col2 + 1] : " + dp[row2 + 1][col2 + 1]);
        System.out.println("dp[row1][col2 + 1] : " + dp[row1][col2 + 1]);
        System.out.println("dp[row2 + 1][col1] : " + dp[row2 + 1][col1]);
        System.out.println("dp[row1][col1] : " + dp[row1][col1]);
        return dp[row2 + 1][col2 + 1] // Lower right corner (highest value)
                - dp[row1][col2 + 1] // Subtract -> Upper right corner
                - dp[row2 + 1][col1] // Subtract -> Lower left corner
                + dp[row1][col1]; // Add -> upper left  left corner
    }
}

//More optimized with caching rows result
class NumMatrixCachingRows {
    //Space: O(mn)
    private int[][] dp;

    //Pre-computation in the constructor takes O(mn)
    NumMatrixCachingRows(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                dp[r][c + 1] = dp[r][c] + matrix[r][c];
            }
        }
    }

    /*
    Time: O(m)
     */
    public int sumRegionCachingRows(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int row = row1; row <= row2; row++) {
            System.out.println("dp[row][col2 + 1] -> " + dp[row][col2 + 1]);
            System.out.println("dp[row][col1] -> " + dp[row][col1]);
            //Take the end of this row with col2 + 1 and subtract to the beginning
            //of that row : col1
            sum += dp[row][col2 + 1] - dp[row][col1];
        }
        return sum;
    }
}

//Approach 1: Non optimized Brut force
class NumMatrixBrutForce {
    private int[][] data;

    NumMatrixBrutForce(int[][] matrix) {
        data = matrix;
    }

    //Approach 1 : Brut force approach
    /*
    Time: O(mn) assume m and n represents the number of rows and columns respectively
    Space: O(1) -> Note that data is a reference to matrix, it is NOT a copy
     */
    public int sumRegionBrutForce(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int r = row1; r <= row2; r++) {
            for (int c = col1; c <= col2; c++) {
                sum += data[r][c];
            }
        }
        return sum;
    }

}
