package topInterviewQuestion.top100likedQuestions;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/rotting-oranges/
public class RottingOranges {
    /*
    You are given an m x n grid where each cell can have one of three values:

    0 representing an empty cell,
    1 representing a fresh orange, or
    2 representing a rotten orange.
    Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

    Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1
     */
    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println(orangeRotting(grid));
    }

    /*
    Time: O(NM) matrix
    Space: O(NM)
     */
    static int orangeRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>(); //Contains rotten orange
        int countFresh = 0;
        //Put position of all rotten oranges in Queue
        //count all fresh orange
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int currOrange = grid[i][j];
                //rotten orange
                if (currOrange == 2) {
                    queue.add(new int[]{i, j});
                } else if (currOrange == 1) { //fresh orange
                    countFresh++;
                }
            }
        }
        //If count of fresh orange == 0 -> return 0
        if (countFresh == 0) return 0;
        int count = 0; //Count how many minutes until we all oranges are rotten
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        //BFS from initially rotten oranges
        while (!queue.isEmpty()) {
            count++; //Add 1 minutes
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int dir[] : dirs) { //Loop through each directions
                    int nextX = point[0] + dir[0];
                    int nextY = point[1] + dir[1];
                    if (isOutOfBounds_OR_isEmptyCell_OR_isRottenOrange(grid, nextX, nextY)) continue;
                    //mark orange has rotten
                    grid[nextX][nextY] = 2;
                    //put the rotten orange in the Q
                    queue.add(new int[]{nextX, nextY});
                    //decrease fresh orange
                    countFresh--;
                }
            }
        }
        return countFresh == 0 ? count - 1 : -1;
    }

    private static boolean isOutOfBounds_OR_isEmptyCell_OR_isRottenOrange(int[][] grid, int row, int col) {
        return isOutOfBounds(grid, row, col) || isEmptyCell(grid, row, col) || isRottenOrange(grid, row, col);
    }

    private static boolean isRottenOrange(int[][] grid, int row, int col) {
        return grid[row][col] == 2;
    }

    private static boolean isEmptyCell(int[][] grid, int row, int col) {
        return grid[row][col] == 0;
    }

    private static boolean isOutOfBounds(int[][] grid, int row, int col) {
        return row < 0 || col < 0 || row >= grid.length || col >= grid[0].length;
    }

}
