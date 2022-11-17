package topInterviewQuestion.leetcode.math;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/102/math/744/
public class CountPrimes {
/*
Given an integer n, return the number of prime numbers that are strictly less than n.
 */
    public static void main(String[] args) {
        int n = 10;
        //Output 4
        //Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

        /*
        Prime number because:
        5 / 2 == 2 (rest 1) because rest 1: prime number
        7 / 2 == 3 (rest 1)
        8 / 2 == 4 (rest 0) 8 is not prime number because rest is 0
         */
        System.out.println(countPrimesNumber(10));
    }

    /*
    For example, 5 is prime because the only
    ways of writing it as a product, 1 × 5 or 5 × 1, involve 5 itself.
     */

    //Solution:https://leetcode.com/explore/featured/card/top-interview-questions-easy/102/math/744/discuss/57588/My-simple-Java-solution
    static int countPrimesNumber(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if ( ! notPrime[i]) {
                count++;
                for(int j = 2; (i*j) < n; j++) {
                    notPrime[i*j] = true; //Set prime
                }
            }
        }
        return count;
    }


    static int countPrimes(int n) {
        if (n <= 2)
            return n;
        boolean[] numbers = new boolean[n];
        for (int p = 2; p <= (int) Math.sqrt(n); p++) { //Math.sqrt(10) == 3.1622776601683795
            if (numbers[p] == false) {
                for (int j = p * p; j < n; j += p) {
                    numbers[j] = true;
                }
            }
        }
        int numberOfPrimes = 0;
        for (int i = 2; i < n; i++) {
            if (numbers[i] == false) {
                ++numberOfPrimes;
            }
        }
        return numberOfPrimes;
    }

}
