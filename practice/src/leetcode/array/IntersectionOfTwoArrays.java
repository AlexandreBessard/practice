package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/explore/interview/card/facebook/54/sorting-and-searching-3/3033/
public class IntersectionOfTwoArrays {
/*
Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.
 Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
 */
    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        var obj = new IntersectionOfTwoArrays();
        System.out.println(Arrays.toString(obj.intersectionOptimized(nums1, nums2)));

    }

    //Approach 3: (Optimized)
    //Resolved in O(n) time and O(1) space
    public int[] intersectionOptimized(int[] nums1, int[] nums2) {
        //Assume two arrays are sorted (increasing order)
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int nums1Pointer = 0, nums2Pointer = 0;
        int k = 0; //Pointer used to know where the array ends
        while (nums1Pointer < nums1.length && nums2Pointer < nums2.length) {
            if (nums1[nums1Pointer] == nums2[nums2Pointer]) { //Same element
                //True if k == 0 OR same element as previous one
                if (k == 0 || nums1[nums1Pointer] != nums1[k - 1]) {
                    nums1[k++] = nums1[nums1Pointer]; //Set value at the beginning of the array
                }
                nums1Pointer++;
                nums2Pointer++;
            } else if (nums1[nums1Pointer] < nums2[nums2Pointer]) {
                nums1Pointer++;
            } else {
                nums2Pointer++;
            }
        }
        return Arrays.copyOf(nums1, k);
    }

    //Approach 2: Built-in Set Intersection
    //Hashing method
    /*
    Time: O(n + m)
     */
    public int[] intersectionNonOptimized(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int n : nums1) {
            set1.add(n);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int n : nums2) {
            set2.add(n);
        }
        //Retains only the elements in this set (set1) that are contained in the specified collection (set2) (optional operation)
        set1.retainAll(set2);
        int[] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) {
            output[idx++] = s;
        }
        return output;
    }
}
