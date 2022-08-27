package patternsForCodingInterviews.cyclicSort;

import java.util.ArrayList;
import java.util.List;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743734011_33Unit
public class FindDuplicateNumbers {

    public static void main(String[] args) {
        List<Integer> duplicates = findNumbers(new int[] { 3, 4, 4, 5, 5 });
        System.out.println("Duplicates are: " + duplicates);

        duplicates = findNumbers(new int[] { 5, 4, 7, 2, 3, 5, 3 });
        System.out.println("Duplicates are: " + duplicates);
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    public static List<Integer> findNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if(nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }
        List<Integer> duplicateNumbers = new ArrayList<>();
        for(i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                duplicateNumbers.add(nums[i]);
            }
        }
        return duplicateNumbers;
    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
