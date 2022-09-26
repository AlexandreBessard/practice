package algorithms.dividAndConquer;
//https://leetcode.com/explore/learn/card/recursion-ii/470/divide-and-conquer/2872/
public class Search2DMatrixII_DivideAndConquer {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        int target = 5;
        System.out.println(searchMatrix(matrix, target));
    }

    //Divide and Conquer approach
    static int[][] matrix;
    static int target;
    //We can partition a sorted two-dimensional matrix into four sorted submatrices,
    // two of which might contain target and two of which definitely do not.
    static boolean searchMatrix(int[][] matrix, int target) {
        Search2DMatrixII_DivideAndConquer.matrix = matrix;
        Search2DMatrixII_DivideAndConquer.target = target;
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        return search(0, 0, matrix[0].length - 1, matrix.length - 1);
    }
    private static boolean search(int left, int up, int right, int down) {
        // 2 Base cases -> array has zero area (no element) AND target smaller than the array's smallest
        //element (found in the top-left corner) or larger than array's largest element (found in bottom right corner)
        if(left > right || up > down) { //Array has 0 element
            return false;
        } else if(target < matrix[up][left] || target > matrix[down][right]) { //top left and bottom right corners
            return false;
        }
        //Divide
        int mid = left + (right - left) / 2; //Take the mid of the current row
        // Locate `row` such that matrix[row-1][mid] < target < matrix[row][mid]
        int row = up;
        while(row <= down && matrix[row][mid] <= target) {
            if(matrix[row][mid] == target) {
                return true;
            }
            row++;
        }
        return search(left, row, mid - 1, down) //Look on the left side
                || search(mid + 1, up, right, row - 1);
    }



}
