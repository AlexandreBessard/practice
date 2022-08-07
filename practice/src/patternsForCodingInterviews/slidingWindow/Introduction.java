package patternsForCodingInterviews.slidingWindow;

import java.util.Arrays;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1627871350324_0Unit
public class Introduction {

    public static void main(String[] args) {
        int[] array = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        int k = 5;
        System.out.println(Arrays.toString(findAverage(k, array)));
        System.out.println(Arrays.toString(findAverageSlidingWindow(k, array)));

    }


    //SLIDING WINDOW
    public static double[] findAverageSlidingWindow(int k, int[] arr) {
        double[] result = new double[arr.length - k + 1];
        double windowSum = 0;
        int windowStart = 0;
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd]; //add the next element
            //Slide the window, we do not need to slide if we have not hit the required window
            //size of 'k'
            if(windowEnd >= k - 1) {
                result[windowStart] = windowSum / k; //Calculate the average
                windowSum -= arr[windowStart]; //Substract element going out
                windowStart++; // slide the window ahead
            }
        }
        return result;
    }

    /*
    Brut Force
    Time complexity: O(N * k) where  N is the number of elements in the input array.
     */
    public static double[] findAverage(int k, int[] arr){
        double[] result = new double[arr.length - k  + 1];
        for(int i = 0; i <=  arr.length - k; i++) {
            double sum = 0;
            for(int j = i; j < i + k; j++) {
                sum += arr[j];
            }
            result[i] = sum / k; //Calculate the average
        }
        return result;
    }



}
