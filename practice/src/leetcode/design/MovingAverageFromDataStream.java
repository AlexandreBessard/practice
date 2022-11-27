package leetcode.design;

//https://leetcode.com/problems/moving-average-from-data-stream/
public class MovingAverageFromDataStream {
/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
Implement the MovingAverage class:
MovingAverage(int size) Initializes the object with the size of the window size.
double next(int val) Returns the moving average of the last size values of the stream.
Example 1:
Input
["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
Output
[null, 1.0, 5.5, 4.66667, 6.0]

Explanation
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 */
    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1)); // return 1.0 = 1 / 1
        System.out.println(movingAverage.next(10)); // return 5.5 = (1 + 10) / 2
        System.out.println(movingAverage.next(3)); // return 4.66667 = (1 + 10 + 3) / 3
        System.out.println(movingAverage.next(5)); // return 6.0 = (10 + 3 + 5) / 3
    }

    /*
    Explanation
    MovingAverage movingAverage = new MovingAverage(3);
    movingAverage.next(1); // return 1.0 = 1 / 1
    movingAverage.next(10); // return 5.5 = (1 + 10) / 2
    movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
    movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
     */
    static class MovingAverage {
        //Space: O(N) depending of the size of the array
        private final int[] window;
        private int numberOfMovies = 0, insert = 0;
        private long sum = 0;

        MovingAverage(int size) {
            window = new int[size];
        }

        //The idea is to keep the sum so far and update the sum just by replacing the oldest number with the new entry.
        /*
        Time: O(1)
         */
        public double next(int val) {
            if (numberOfMovies < window.length) {
                numberOfMovies++;
            }
            //At the beginning when the array is not completely full, we subtract nothing (0)
            sum -= window[insert]; //Substract older value inserted (LIFO) Last in First Out
            sum += val; //Aggregate
            window[insert] = val; //Insert the value, replacing the oldest number with the new entry after a complete loop.
            //Avoid indexOutOfBoundException with the modulo
            //Increment insert
            insert = (insert + 1) % window.length; //When reach the end of index, go back to the beginning (loop).
            double res = (double) sum / numberOfMovies;
            return res;
        }
    }

}
