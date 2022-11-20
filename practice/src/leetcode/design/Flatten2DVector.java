package leetcode.design;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/112/design/811/
public class Flatten2DVector {
/*
Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.
 */
    public static void main(String[] args) {
        int[][] vectors = {
                {1, 2},
                {3},
                {4}
        };
        var obj = new Vector2DTwoPointers(vectors);
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.hasNext());
        System.out.println(obj.next());
        System.out.println(obj.hasNext());


    }

    //Approach 2: Two Pointers: (Must DO)
    //Approach 1 below is BAD
    /*
    Space complexity: O(1)
     */
    static class Vector2DTwoPointers {
        private final int[][] vector;
        private int inner = 0;
        private int outer = 0;
        //Time complexity: O(1)
        public Vector2DTwoPointers(int[][] v){
            vector = v;
        }
        //Time complexity: O(1)
        public int next() {
            if(!hasNext()) { //false means out of bound exception
                throw new NoSuchElementException();
            }
            return vector[outer][inner++];
        }
        //Time complexity: O(1)
        public boolean hasNext() {
            advanceToNext();
            return outer < vector.length;
        }
        private void advanceToNext() {
            while(outer < vector.length && inner == vector[outer].length) { //true, initialize inner and outer
                inner = 0;
                outer++;
            }
        }
    }


    //Approach 1: Flatten List in Constructor (Bad approach because
    // it creates a new data structure instead of simply iterating over the given one)
    class Vector2D {
        private List<Integer> nums = new ArrayList<>();
        private int position = 0;
        //Time complexity: O(N + V) N: length of 2D array, V: each vectors
        //Space complexity: O(N)
        public Vector2D(int[][] v) {
            for(int[] innerVector: v) {
                for(int num: innerVector) {
                    nums.add(num);
                }
            }
        }
        //Time complexity: O(1)
        public int next() {
            if(!hasNext())
                throw new NoSuchElementException();
            int result = nums.get(position);
            position++;
            return result;
        }
        //Time complexity: 0(1)
        public boolean hasNext() {
            return position < nums.size();
        }

    }

}
