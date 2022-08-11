package topInterviewQuestion.easy.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/674/
public class IntersectionOfTwoArrays2 {

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9,4,9,8,4};
        //Output: [4, 9]
        for(int i : intersectSort(nums1, nums2)) {
            System.out.print(i + ", ");
        }
    }

    //Approach 2: Sort
    /*
    Time complexity: O(n log n + m log m) -> we sort two arrays independently.
    Space complexity: O(log n + log m)
     */
    static int[] intersectSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                ++i;
            } else if (nums1[i] > nums2[j]) {
                ++j;
            } else {
                nums1[k++] = nums1[i++];
                ++j;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }

        //Approach 1: Hash Table
    /*
    Time complexity: O(n + m) -> where n and m are the lengths of the arrays.
    Iterate through the first and second array.
    Look up and insert in hashmap is O(1)
    Space complexity: O(min(n, m), we use hashmap to store numbers from
    the smaller array
     */
    static int[] intersectHashMap(int[] nums1, int[] nums2) {
        //nums1 is always the smallest array
        if (nums1.length > nums2.length) {
            return intersectHashMap(nums2, nums1);
        }
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int n : nums1) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }
        int k = 0;
        for (int n : nums2) {
            int cnt = m.getOrDefault(n, 0);
            if (cnt > 0) {
                nums1[k++] = n;
                m.put(n, cnt - 1);
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }
}
