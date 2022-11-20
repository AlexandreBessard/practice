package leetcode.sortingAndSearching;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/806/
public class SearchA2DMatrixII {
/*
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
This matrix has the following properties:
Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
 */
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11},
                {2, 5, 8, 18},
                {3, 6, 9, 16},
                {10, 13, 14, 17},
                {18, 21, 23, 26}
        };
        System.out.println(searchMatrixBinarySearch(matrix, 5));
    }

    //Approach 2: Binary Search (Matrix is sorted)
    /*
    O(log n!)
    Space complexity: O(1)
     */
    static boolean searchMatrixBinarySearch(int[][] matrix, int target) { //Return true if we find the target
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        //iterate over matrix diagonals
        int shorterDim = Math.min(matrix.length, matrix[0].length);
        for(int i = 0; i < shorterDim; i++) {
            if(binarySearch(matrix, target, i, true)) { //Search in the current row (sorted horizontally)
                return true;
            }
            if(binarySearch(matrix, target, i, false)) { //Search in the current column (sorted vertically)
                return true;
            }
        }
        return false;
    }
    private static boolean binarySearch(int[][] matrix, int target, int start, boolean horizontal) {
        int left = start;
        int right = horizontal ? matrix[0].length - 1 : matrix.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(horizontal) { //searching inside that row
                if(matrix[start][mid] < target) {
                    left = mid + 1;
                } else if(matrix[start][mid] > target) {
                    right = mid - 1;
                } else {
                    return true;
                }
            } else { //searching inside that column
                if(matrix[mid][start] < target) { //true means we move down from that column to find a higer value
                    left = mid + 1;
                } else if(matrix[mid][start] > target) {
                    right = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    //Approach 4: Search and Space reduction
    /*
    Time complexity: O(n + m)
    Space complexity: O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length-1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) { //Check boundaries
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++; //Move forward
            } else { // found it
                return true;
            }
        }

        return false;
    }

    //Approach 1: Brut force
    /*
    Time complexity: O(nm) equal to the size of the matrix n x m
    Space complexity: O(1)
     */
    static boolean searchMatrixBrutForce(int[][] matrix, int target) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == target)
                    return true;
            }
        }
        return false;
    }

}
