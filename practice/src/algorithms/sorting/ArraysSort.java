package algorithms.sorting;

import java.util.Arrays;
import java.util.Comparator;
//https://leetcode.com/explore/learn/card/sorting/693/introduction/4431/
public class ArraysSort {

    //Fundamentals of Sorting
    public static void main(String[] args) {
        String[] arr = {"yes", "no", "expected", "b", "ok", "best", "a"};
        Arrays.sort(arr, new StringCompare()); //Sort based on the length only
        System.out.println(Arrays.toString(arr));
    }

    static class StringCompare implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            if (s1.length() > s2.length()) {
                return 1;
            } else if (s1.length() < s2.length()) {
                return -1;
            }
            return 0;
        }
    }
}
