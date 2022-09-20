package topInterviewQuestion.facebook.topMostFrequentQuestions;

//https://leetcode.com/problems/moving-average-from-data-stream/
public class MovingAverageFromDataStream {

    public static void main(String[] args) {
        var obj = new MovingAverage(3);
        MovingAverage movingAverage = new MovingAverage(3);
        movingAverage.next(1); // return 1.0 = 1 / 1
        movingAverage.next(10); // return 5.5 = (1 + 10) / 2
        movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
        movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
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
        private int[] window;
        private int n, insert;
        private long sum;

        MovingAverage(int size) {
            window = new int[size];
            insert = 0;
            sum = 0;
        }

        //The idea is to keep the sum so far and update the sum just by replacing the oldest number with the new entry.
        /*
        Time: O(1)
         */
        public void next(int val) {
            if (n < window.length) {
                n++;
            }
            sum -= window[insert]; //Substract older value inserted (LIFO) Last in First Out
            sum += val;
            window[insert] = val; //Insert the value, replacing the oldest number with the new entry after a complete loop.
            //Avoid indexOutOfBoundException with the modulo
            insert = (insert + 1) % window.length; //When reach the end of index, go back to the beginning (loop).
            double res = (double) sum / n;
            System.out.println(res);
        }
    }

}
