package patternsForCodingInterviews.slidingWindow;

import java.util.HashMap;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541018393_2Unit
public class FruitsIntoBaskets {
/*
You are visiting a farm to collect fruits. The farm has a single row of fruit trees. You will be given two baskets,
and your goal is to pick as many fruits as possible to be placed in the given baskets.

You will be given an array of characters where each character represents a fruit tree. The farm has following restrictions:

Each basket can have only one type of fruit. There is no limit to how many fruit a basket can hold.
You can start with any tree, but you can’t skip a tree once you have started.
You will pick exactly one fruit from every tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.
Write a function to return the maximum number of fruits in both baskets.
 */
    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " +
                findLength(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        //Explanation: 2 baskets, we must have fruits as much as possible. Each basckets can contain 1 type of fruit
        System.out.println("Maximum number of fruits: " +
                findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
    }

    /*
    Time: O(N) where N is the number of characters in the input array.
    Space: O(1), there can be a maximum of three types of fruits stored in the
    frequency map.
     */
    public static int findLength(char[] arr) {
        int windowStart = 0;
        int maxLength = 0;
        Map<Character, Integer> fruitFrequencyMap = new HashMap<>();
        //Try to extend the range [windowStart, windowsEnd]
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            char rightFruit = arr[windowEnd];
            fruitFrequencyMap.put(arr[windowEnd], fruitFrequencyMap.getOrDefault(rightFruit, 0) + 1);
            //shrink the sliding window until we are left with '2' fruits in the frequency
            while(fruitFrequencyMap.size() > 2) {
                char leftFruit = arr[windowStart];
                fruitFrequencyMap.put(leftFruit, fruitFrequencyMap.get(leftFruit) - 1);
                if(fruitFrequencyMap.get(leftFruit) == 0) {
                    fruitFrequencyMap.remove(leftFruit);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, (windowEnd - windowStart) + 1);
        }
        return maxLength;
    }
}
