package topInterviewQuestion.medium.sortingAndSearching;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/806/
public class SearchA2DMatrixII {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };
        System.out.println(searchMatrixBinarySearch(matrix, 5));
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

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }

        return false;
    }

    //Approach 2: Binary Search (Matrix is sorted)
    /*
    O(log n!)
    Space complexity: O(1)
     */
    static boolean searchMatrixBinarySearch(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0)
            return false;
        //iterate over matrix diagonals
        int shorterDim = Math.min(matrix.length, matrix[0].length);
        for(int i = 0; i < shorterDim; i++) {
            if(binarySearch(matrix, target, i, true))
                return true;
            if(binarySearch(matrix, target, i, false))
                return true;
        }
        return false;
    }
    private static boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
        int lo = start;
        int hi = vertical ? matrix[0].length - 1 : matrix.length - 1;
        while(hi >= lo) {
            int mid = (lo + hi) / 2;
            if(vertical) { //searching a column
                if(matrix[start][mid] < target) {
                    lo = mid + 1;
                } else if(matrix[start][mid] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            } else { //searching a row
                if(matrix[mid][start] < target) {
                    lo = mid + 1;
                } else if(matrix[mid][start] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
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
