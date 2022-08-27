package patternsForCodingInterviews.cyclicSort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743754602_36Unit
public class _3FindTheFirstKMissingPositiveNumbers {

    public static void main(String[] args) {
        List<Integer> missingNumbers = findNumbers(
                new int[] { 3, -1, 4, 5, 5 }, 3);
        System.out.println("Missing numbers: " + missingNumbers);

        /*
        missingNumbers = findNumbers(new int[] { 2, 3, 4 }, 3);
        System.out.println("Missing numbers: " + missingNumbers);

        missingNumbers = findNumbers(new int[] { -2, -3, 4 }, 2);
        System.out.println("Missing numbers: " + missingNumbers);

         */
    }

    /*
    Time: O(n + k) as the last two loops will run for O(n) and O(k) times respectively
    Space: O(k) space to store extraNumbers
     */
    public static List<Integer> findNumbers(int[] nums, int k) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] > 0
                    && nums[i] <= nums.length
                    && nums[i] != nums[nums[i] - 1])
            {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }
        List<Integer> missingNumbers = new ArrayList<>();
        Set<Integer> extraNumbers = new HashSet<>();
        for (i = 0; i < nums.length && missingNumbers.size() < k; i++) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i + 1);
                extraNumbers.add(nums[i]);
            }
        }
        // add the remaining missing numbers
        for (i = 1; missingNumbers.size() < k; i++) {
            int candidateNumber = i + nums.length;
            // ignore if the array contains the candidate number
            if (!extraNumbers.contains(candidateNumber)) {
                missingNumbers.add(candidateNumber);
            }
        }
        return missingNumbers;
    }

    private static void swap(int[] nums, int i, int j) {
       int temp = nums[i];
       nums[i] = nums[j];
       nums[j] = temp;
    }

}
