package topInterviewQuestion.top100likedQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/spiral-matrix/
public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix2 = {
                {1, 2},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("matrix 1 : " + spiralOrder(matrix));
        System.out.println("matrix 2 : " + spiralOrder(matrix2));
    }

    //Correction: https://leetcode.com/problems/spiral-matrix/discuss/20599/Super-Simple-and-Easy-to-Understand-Solution
    /*
    Time: O(M * N) -> we visit each element once.
    Space: O(1)
     */
    static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        int up = 0, down = n - 1;
        int left = 0, right = m - 1;
        while (res.size() < n * m) {
            for (int j = left; j <= right && res.size() < n * m; j++) //From left to right
                res.add(matrix[up][j]);

            for (int i = up + 1; i <= down - 1 && res.size() < n * m; i++) //From top to bottom right side
                res.add(matrix[i][right]); //Right gets the latest element from the row 'i'

            for (int j = right; j >= left && res.size() < n * m; j--)
                res.add(matrix[down][j]);

            for (int i = down - 1; i >= up + 1 && res.size() < n * m; i--) { //Get only elements from the left side (bottom to top)
                System.out.println("matrix[i][left] --> " + matrix[i][left]);
                res.add(matrix[i][left]);
            }
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }

}
