package topInterviewQuestion.easy.array;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/674/
public class IntersectionOfTwoArrays2 {

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        //Output: [4, 9]
        for (int i : intersectSort(nums1, nums2)) {
            System.out.print(i + ", ");
        }
    }

    //Approach 2: In case if the arrays are sorted
    /*
    Time complexity: O(max(N, M))
     */
    static int[] intersectSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length, m = nums2.length;
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < n && j < m) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] ret = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            ret[k] = list.get(k);
        }
        return ret;
    }

    //Approach 1: Hash Table IF the array is NOT sorted
    /*
    Time complexity: O(n + m) -> where n and m are the lengths of the arrays.
    Iterate through the first and second array.
    Look up and insert in hashmap is O(1)
    Space complexity: O(min(n, m), we use hashmap to store numbers from
    the smaller array
     */
    static int[] intersectHashMap(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (map.get(i) != null && map.get(i) > 0) {
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}
