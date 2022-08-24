package algorithms.backtracking;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/explore/learn/card/recursion-ii/472/backtracking/2794/
public class RobotRoomCleaner {

    public static void main(String[] args) {
        int[][] room = {
                {1,1,1,1,1,0,1,1},
                {1,1,1,1,1,0,1,1},
                {1,0,1,1,1,1,1,1},
                {0,0,0,1,0,0,0,0},
                {1,1,1,1,1,1,1,1}
        };
        var obj = new RobotRoomCleaner();
    }


    //Approach 1: Spiral Backtracking

    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    int[][] directions = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    Set<Map.Entry<Integer, Integer>> visited = new HashSet<>();
    Robot robot;

    public void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    /*
    Time: O(N - M) where N is the number of cell and M number of obstacles
    At each visit, we will check 4 directions around the cell.
    Therefore, the total number of operations would be 4 (N - M).
    Space: O(N - M)
    We employed a hashtable
    to keep track of whether a non-obstacle cell is visited or not.
     */
    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backTrack(0, 0, 0);
    }

    private void backTrack(int row, int col, int d) {
        visited.add(Pair.of(row, col));
        robot.clean();
        //Explore 4 directions : up, right, down, and left
        // (the order is important since the idea is always to turn right) :
        for (int i = 0; i < 4; i++) {
            int newD = (d + i) % 4;
            int newRow = row + directions[newD][0];
            int newCol = col + directions[newD][1];
            if (!visited.contains(Pair.of(newRow, newCol)) && robot.move()) {
                backTrack(newRow, newCol, newD);
                goBack();
            }
            //Turn the robot following chosen direction : clockwise
            robot.turnRight();
        }
    }

    static class Pair {
        public static <T, U> Map.Entry<T, U> of(T first, U second) {
            return new AbstractMap.SimpleEntry<>(first, second);
        }
    }

    interface Robot {
        //return tru  if the cell in front  is open and robot moves
        //into the cell
        //Return false if the cell in front is blocked and robot stays in the
        //current cell
        public boolean move();

        //Robot stay in the same cell after calling turnLeft/turnRight
        //Each turn will be 90 degres
        public void turnLeft();

        public void turnRight();

        //Clean the current cell
        public void clean();
    }
}
