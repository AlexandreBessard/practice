package topInterviewQuestion.top100likedQuestions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

//https://leetcode.com/problems/daily-temperatures/
public class DailyTemperatures {
    /*
    Given an array of integers temperatures represents the daily temperatures,
    return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
    If there is no future day for which this is possible, keep answer[i] == 0 instead.
     */
    public static void main(String[] args) {
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(temps)));
        System.out.println(Arrays.toString(dailyTemperaturesMonotonicStack(temps)));
    }

    //Monotonic Stack or Monotonic Queue can be used to find the nearest biggest or smallest elements,
    // so next time you see this kind of keyword try to apply these data structures
    //Monotonic stacks are a good option when a problem involves comparing the size of numeric elements, with their order being relevant.
    /*
    Time: O(n)
    Space: O(n)
     */
    static int[] dailyTemperaturesMonotonicStack(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); //Equivalent of Stack, contains index
        for(int currDay = 0; currDay < temperatures.length; currDay++) {
            int currentTemp = temperatures[currDay];
            // Pop until the current day's temperature is not
            // warmer than the temperature at the top of the stack
            while(!stack.isEmpty() && temperatures[stack.peek()] < currentTemp) { //Remove the smallest element from the stack
                int prevDay = stack.pop(); //Keep the biggest element in the stack
                answer[prevDay] = currDay - prevDay; //substract
            }
            stack.push(currDay);
        }
        return answer;
    }



    //Brut force
    /*
    Time: O(n²)
    Space: O(1)
     */
    static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n]; //By default, array of 0
        for(int day = 0; day < n; day++) {
            for(int futureDay  = day + 1; futureDay < n; futureDay++) {
                if(temperatures[futureDay] > temperatures[day]) { //true if next day is greater than the previous
                    answer[day] = futureDay - day;
                    break;
                }
            }
        }
        return answer;
    }

}
