package topInterviewQuestion.amazon.treesAndGraphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2987/
public class FloodFill {

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        var obj = new FloodFill();
        //1 and 1 represents the center of the image
        int[][] res = obj.floodFill(image, 1, 1, 2);
        System.out.println("DFS : ");
        for (int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
        System.out.println("\n");
        int[][] image1 = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        System.out.println("BFS : ");
        int[][] res1 = floodFillDFS(image1, 1, 1, 2);
        for (int[] el : res1) {
            System.out.println(Arrays.toString(el));
        }
    }

    //Approach 2
    //BFS
    static int[][] floodFillDFS(int[][] image, int sr, int sc, int newColor) {
        //BFS to find connected component
        int m = image.length;
        int n = image[0].length;
        int[][] visited = new int[m][n];

        int dir[][] = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc});
        int color = image[sr][sc];
        visited[sr][sc] = 1;
        image[sr][sc] = newColor;

        while (!q.isEmpty()) {
            int pos[] = q.poll();
            int row = pos[0];
            int col = pos[1];
            for (int i = 0; i < 4; i++) {
                int newRow = row + dir[i][0];
                int newCol = col + dir[i][1];
                if (isOutOfBound(newRow, newCol, image)) {
                    continue;
                }
                if (visited[newRow][newCol] == 1) {
                    continue;
                }
                if (image[newRow][newCol] != color) { //True if we have a 0 for example
                    continue;
                }
                q.offer(new int[]{newRow, newCol});
                visited[newRow][newCol] = 1;
                image[newRow][newCol] = newColor;
            }
        }
        return image;
    }
    private static boolean isOutOfBound(int row, int col, int[][] image) {
        return row < 0 || col < 0 || row >= image.length || col >= image[0].length;
    }

    /*
    Approach 1: DFS approach
    Time: O(N)
    Space: O(N)
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color != newColor) {
            dsf(image, sr, sc, color, newColor);
        }
        return image;
    }

    private void dsf(int[][] image, int r, int c, int color, int newColor) {
        if (image[r][c] == color) {
            //Replace by the new color
            image[r][c] = newColor;
            if (r >= 1)
                dsf(image, r - 1, c, color, newColor); //Up
            if (c >= 1)
                dsf(image, r, c - 1, color, newColor); ///Left
            if (r + 1 < image.length)
                dsf(image, r + 1, c, color, newColor); //Down
            if (c + 1 < image[0].length)
                dsf(image, r, c + 1, color, newColor); //Right
        }
    }
}
