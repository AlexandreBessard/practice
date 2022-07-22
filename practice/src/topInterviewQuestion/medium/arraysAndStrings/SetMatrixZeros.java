package topInterviewQuestion.medium.arraysAndStrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeros {

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
    }


    //Approach 2: O(1) Space efficient solution
    /*
    Time complexity: O(M x N)
    Space complexity: O(1)
     */
    static void setZeroesEfficientSpace(int[][] matrix) {
        boolean firstCol = false;
        boolean firstRow = false;

        //Check if firstCol is true
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }
        //Check if first Row is true
        for(int i = 0; i < matrix[0].length; i++) {
            if(matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        }
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[i].length; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if(firstCol) {
            for(int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        if(firstRow) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    //Approach 1: Additional Memory Approach
    /*
    Time complexity: O(M x N) where M and N are the number of rows and columns
    Space complexity: O(M + N) : store rows and columns into a Set
     */
    static void setZeroes(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(rows.contains(i) || cols.contains(j))
                    matrix[i][j] = 0;
            }
        }
    }

}
