package leetcode.array;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/99/others/601/
public class PascalsTriangle {

    /*
    Given an integer numRows, return the first numRows of Pascal's triangle.
     */
    public static void main(String[] args) {
        generate(5);
    }

    /*
    Time complexity: O(N²) (2 loops)
    Space complexity: 0(1), the input and output does not count towards the space complexity
     */
    static List<List<Integer>> generate(int numRows) {
        //Each List represents a level.
        List<List<Integer>> triangle = new ArrayList<>();
        //Base case:
        //First row is always 1
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1); //first level (at the top)
        for(int rowNum = 1; rowNum < numRows; rowNum++) { //loop through each level
            List<Integer> currRow = new ArrayList<>();// initialize a currRow for this level
            List<Integer> prevRow = triangle.get(rowNum - 1); //Go to the previous level
            //The first currRow elements always 1
            currRow.add(1);
            for(int j = 1; j < rowNum; j++) {
                //Addition 2 elements by 2 elements
                currRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            currRow.add(1); //add 1 at the end of that row
            triangle.add(currRow);
        }
        return triangle;
    }
}
