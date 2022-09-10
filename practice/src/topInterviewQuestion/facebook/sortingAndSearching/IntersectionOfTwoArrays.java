package topInterviewQuestion.facebook.sortingAndSearching;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/explore/interview/card/facebook/54/sorting-and-searching-3/3033/
public class IntersectionOfTwoArrays {

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        var obj = new IntersectionOfTwoArrays();
        System.out.println(Arrays.toString(obj.intersectionOptimized(nums1, nums2)));

    }

    //Approach 3: (Optimized)
    //Resolved in O(n) time and O(1) space
    public int[] intersectionOptimized(int[] nums1, int[] nums2) {
        //Assume two arrays are sorted
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (k == 0 || nums1[i] != nums1[k - 1]) {
                    nums1[k++] = nums1[i]; //Set value at the beginning of the array
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOf(nums1, k);
    }

    //Approach 2: Built-in Set Intersection
    /*
    Time: O(n + m)
     */
    public int[] intersectionNonOptimized(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int n : nums1)
            set1.add(n);
        Set<Integer> set2 = new HashSet<>();
        for (int n : nums2) {
            set2.add(n);
        }
        //removes from this set (set1) all of its elements that are not contained in the specified collection.
        set1.retainAll(set2);
        int[] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) {
            output[idx++] = s;
        }
        return output;
    }
}
