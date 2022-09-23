package patternsForCodingInterviews.slidingWindow;

import java.util.HashMap;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541018393_2Unit
public class FruitsIntoBaskets {

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
            fruitFrequencyMap.put(arr[windowEnd], fruitFrequencyMap.getOrDefault(arr[windowEnd], 0) + 1);
            //shrink the sliding window until we are left with '2' fruits in the frequency
            while(fruitFrequencyMap.size() > 2) {
                fruitFrequencyMap.put(arr[windowStart], fruitFrequencyMap.get(arr[windowStart]) - 1);
                if(fruitFrequencyMap.get(arr[windowStart]) == 0) {
                    fruitFrequencyMap.remove(arr[windowStart]);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, (windowEnd - windowStart) + 1);
        }
        return maxLength;
    }
}
