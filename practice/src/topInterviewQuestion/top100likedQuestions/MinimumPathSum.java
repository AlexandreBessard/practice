package topInterviewQuestion.top100likedQuestions;

//https://leetcode.com/problems/minimum-path-sum/
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] nums = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minPathSum(nums));
        System.out.println(minPathSumRecursion(nums));
        System.out.println(minPathSumRecursionMemoization(nums));
        System.out.println(minPathSumDP(nums));
    }

    /*
    The reason why DP works here (but not in an actual shortest distance problem) is because we can only move right and down through the matrix.
    If we can move in all 4 directions, DP would give the wrong answer.
     */
    //Approach DP
    /*
    Time: O(mn) We traverse the entire matrix once
    Space: O(mn)
     */
    public static int minPathSumDP(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if(row == 0 && col == 0) {
                    grid[row][col] = grid[row][col];
                }
                else if(row == 0 && col != 0)  {
                    grid[row][col] = grid[row][col] + grid[row][col - 1]; //Get element from the left
                }
                else if(col == 0 && row != 0) {
                    grid[row][col] = grid[row][col] + grid[row - 1][col]; //Get element from the top
                }
                else {
                    grid[row][col] = grid[row][col] + Math.min(grid[row - 1][col], grid[row][col - 1]); //get min from top or left
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


    //Recursion with memoization
    public static int minPathSumRecursionMemoization(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int[][] dp = new int[height + 1][width + 1];
        return min(grid, height - 1, width - 1, dp);
    }
    private static int min(int[][] grid, int row, int col, int[][] dp) {
        if (row == 0 && col == 0) { // this is the exit of the recursion
            return grid[row][col];
        }
        //Get from the cache
        if(dp[row][col] != 0) {
            return dp[row][col];
        }
        if (row == 0) {
            return grid[row][col] + min(grid, row, col - 1, dp);  // when we reach the first row, we could only move horizontally
        }
        if (col == 0) {
            return grid[row][col] + min(grid, row - 1, col, dp); //when we reach the first column, we could only move vertically
        }
        //we want the min sum path so we pick the cell with the less value
        return dp[row][col] = grid[row][col] + Math.min(
                min(grid, row - 1, col),
                min(grid, row, col - 1)
        );
    }



    //Recursion
    public static int minPathSumRecursion(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        return min(grid, height - 1, width - 1);
    }

    public static int min(int[][] grid, int row, int col) {
        if (row == 0 && col == 0) { // this is the exit of the recursion
            return grid[row][col];
        }
        if (row == 0) {
            return grid[row][col] + min(grid, row, col - 1);  // when we reach the first row, we could only move horizontally
        }
        if (col == 0) {
            return grid[row][col] + min(grid, row - 1, col); //when we reach the first column, we could only move vertically
        }
        //we want the min sum path so we pick the cell with the less value
        return grid[row][col] + Math.min(
                min(grid, row - 1, col),
                min(grid, row, col - 1)
        );
    }

    //Brute force
    /*
    Time: O(2m+n) for every move, we have atmost 2 options.
    Space: O(m + n) Recursion depth m + n
     */
    static int minPathSum(int[][] grid) {
        return calculate(grid, 0, 0); //Start from left top corner
    }

    private static int calculate(int[][] grid, int row, int col) {
        if (row == grid.length || col == grid[0].length) { //Out of bound
            return Integer.MAX_VALUE;
        }
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return grid[row][col];
        }
        return grid[row][col] + Math.min(
                calculate(grid, row + 1, col),
                calculate(grid, row, col + 1)
        );
    }


}
