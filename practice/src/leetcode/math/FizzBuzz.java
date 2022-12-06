package leetcode.math;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/102/math/743/
public class FizzBuzz {

    /*
    Given an integer n, return a string array answer (1-indexed) where:

    answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
    answer[i] == "Fizz" if i is divisible by 3.
    answer[i] == "Buzz" if i is divisible by 5.
    answer[i] == i (as a string) if none of the above conditions are true.
         */
    public static void main(String[] args) {
        int n = 5;
        //Output: ["1","2","Fizz","4","Buzz"]
        System.out.println(fizzBuzzWithoutModulo(28745321));
    }

    static List<String> fizzBuzzWithoutModulo(int n) {
        List<String> ret = new ArrayList<>(n);
        for (int i = 1, fizz = 0, buzz = 0; i <= n; i++) {
            fizz++;
            buzz++;
            if (fizz == 3 && buzz == 5) { // i num is divisible by 3 or 5
                ret.add("FizzBuzz");
                fizz = 0;
                buzz = 0;
            } else if (fizz == 3) { // i num is Divisible by 3
                ret.add("Fizz");
                fizz = 0;
            } else if (buzz == 5) { //i num is Divisible by 5
                ret.add("Buzz");
                buzz = 0;
            } else { //(as a string) if none of the above conditions are true
                ret.add(String.valueOf(i));
            }
        }
        return ret;
    }

    //Approach 3: Hash it
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static List<String> fizzBuzzHashIt(int n) {
        List<String> ans = new ArrayList<>();
        Map<Integer, String> fizzBizzDict = new HashMap<>() {
            {
                put(3, "Fizz");
                put(5, "Bizz");
            }
        };
        //List of divisors
        List<Integer> divisors = new ArrayList<>(Arrays.asList(3, 5));
        for (int num = 1; num <= n; num++) {
            var numAnsStr = new StringBuilder();
            for (Integer key : divisors) {
                //If num divisible by key
                if (num % key == 0) {
                    numAnsStr.append(fizzBizzDict.get(key));
                }
            }
            if (numAnsStr.equals("")) {
                numAnsStr.append(Integer.toString(num));
            }
            ans.add(numAnsStr.toString());
        }
        return ans;
    }


    //Approach 2: String concatenation
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public List<String> fizzBuzzStringConcatenation(int n) {
        // ans list
        List<String> ans = new ArrayList<String>();
        for (int num = 1; num <= n; num++) {
            boolean divisibleBy3 = (num % 3 == 0);
            boolean divisibleBy5 = (num % 5 == 0);
            String numAnsStr = "";
            if (divisibleBy3) {
                // Divides by 3, add Fizz
                numAnsStr += "Fizz";
            }
            if (divisibleBy5) {
                // Divides by 5, add Buzz
                numAnsStr += "Buzz";
            }
            if (numAnsStr.equals("")) {
                // Not divisible by 3 or 5, add the number
                numAnsStr += Integer.toString(num);
            }
            // Append the current answer str to the ans list
            ans.add(numAnsStr);
        }
        return ans;
    }

    //Approach 1: Naive approach
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int num = 1; num <= n; num++) {
            boolean divisibleBy3 = (num % 3 == 0);
            boolean divisibleBy5 = (num % 5 == 0);
            if (divisibleBy3 && divisibleBy5)
                ans.add("FizzBuzz");
            else if (divisibleBy3) {
                ans.add("Fizz");
            } else if (divisibleBy5) {
                ans.add("Buzz");
            } else {
                ans.add(Integer.toString(num));
            }
        }
        return ans;
    }

    //Question asked during a real interview
    static void fizzBuzz() {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 7 == 0) {
                System.out.println(i + " Multiple of 3 and 7");
            } else if (i % 3 == 0) {
                System.out.println(i + " Multiple of 3");
            } else if (i % 7 == 0) {
                System.out.println(i + " Multiple of 7");
            }
        }
    }
}
