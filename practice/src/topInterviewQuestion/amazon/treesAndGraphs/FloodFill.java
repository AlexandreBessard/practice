package topInterviewQuestion.amazon.treesAndGraphs;

import java.util.Arrays;

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
        for(int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
    }

    /*
    Approach 1: DFS approach
    Time: O(N)
    Space: O(N)
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if(color != newColor) {
            dsf(image, sr, sc, color, newColor);
        }
        return image;
    }
    private void dsf(int[][] image, int r, int c, int color, int newColor) {
        if(image[r][c] == color) {
            //Replace by the new color
            image[r][c] = newColor;
            if(r >= 1)
                dsf(image, r-1, c, color, newColor); //Up
            if(c >= 1)
                dsf(image, r, c-1, color, newColor); ///Left
            if(r + 1 < image.length)
                dsf(image, r + 1, c, color, newColor); //Down
            if(c + 1 < image[0].length)
                dsf(image, r, c + 1, color, newColor); //Right
        }
    }
}
