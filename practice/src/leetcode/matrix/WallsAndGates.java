package leetcode.matrix;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/walls-and-gates/
public class WallsAndGates {
    /*
    You are given an m x n grid rooms initialized with these three possible values.

    -1 A wall or an obstacle.
    0 A gate.
    INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
    Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
     */
    public static void main(String[] args) {
        //INF: empty room, -1 is a wall and 0 is a gate
        int[][] rooms = {
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}
        };
        wallAndGates(rooms);
        for (int row = 0; row < rooms.length; row++) {
            for (int col = 0; col < rooms[0].length; col++) {
                System.out.print(rooms[row][col] + ", ");
            }
            System.out.println("\n");
        }
    }

    private static final int[][] DIRECTIONS = {
            {1, 0}, //Up
            {-1, 0}, // Down
            {0, 1}, // Right
            {0, -1} // Left
    };

    //Approach 2: BFS
    public static void wallAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        int rows = rooms.length;
        int cols = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (rooms[row][col] == 0) { //True means it is a  gate
                    q.add(new int[]{row, col});
                }
            }
        }
        while (!q.isEmpty()) {
            int[] point = q.poll(); //Start BFS from that gate
            int curRow = point[0];
            int currCol = point[1];
            for (int[] direction : DIRECTIONS) { //Up, Down, Right, Left direction
                //Next direction
                int nextRow = curRow + direction[0];
                int nextCol = currCol + direction[1];
                if (isOutOfBound(rooms, nextRow, nextCol)) { //true means the cell is not in the matrix
                    continue;
                }
                if (rooms[nextRow][nextCol] != Integer.MAX_VALUE) { //true means the cell is either -1: wall or 0: gate or already visited
                    continue;
                }
                rooms[nextRow][nextCol] = rooms[curRow][currCol] + 1; //Gate is represented as 0, get the current cell + 1 for next one
                q.add(new int[]{nextRow, nextCol}); //we know it is an empty cell
            }
        }

    }

    private static boolean isOutOfBound(int[][] matrix, int row, int col) {
        return row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length;
    }

    private static void BFS(int[][] rooms) {

    }
}
