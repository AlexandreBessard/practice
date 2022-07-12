package crackingTheCodingInterview.DP;

import java.util.*;

public class RobotInAGrid {

    public static void main(String[] args) {
        boolean[][] maze = {
                {true, true, false},
                {true, true, true},
                {true, true, true}
        };
        getPath(maze).forEach(e -> System.out.print(e));
    }

    static List<Point> getPath(boolean[][] maze) {
        List<Point> points = new ArrayList<>();
        if(maze == null || maze.length == 0)
            return points;
        Set<Point> failedPoints = new HashSet<>();
        if(getPath(maze, 0, 0, points, failedPoints))
            return points;
        else
            return null;
    }
    static boolean getPath(boolean[][] maze, int row, int col, List<Point> points,
                           Set<Point> failedPoints) {
        //Base case
        if(col >= maze[0].length || row >= maze.length || !maze[row][col])
            return false;
        Point p = new Point(row, col);
        //Already visited this cell
        if(failedPoints.contains(p))
            return false;
        boolean isArrived = (row == maze.length - 1) && (col == maze[0].length - 1);
        if(isArrived
        || getPath(maze, row, col + 1, points, failedPoints)
        || getPath(maze, row + 1, col, points, failedPoints)) {
            points.add(p);
            return true;
        }
        failedPoints.add(p); //Cache the result
        return false;
    }

    static class Point {
        int row, col;
        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public String toString(){
            return "(" + row + "," + col + ")";
        }
    }


}
