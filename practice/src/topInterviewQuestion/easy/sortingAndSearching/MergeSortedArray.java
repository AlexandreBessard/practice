package topInterviewQuestion.easy.sortingAndSearching;

import java.util.Arrays;

public class MergeSortedArray {

    public static void main(String[] args) {

    }


    //Approach 3: Three pointers (Start from the end)
    //
    static void mergeThreePointersStartFromTheEnd(int[] nums1,
                                                  int m,
                                                  int[] nums2,
                                                  int n)
    {
        //Set pointer at the end of the array
        int p1 = m - 1;
        int p2 = n - 1;
        for(int p = m + n - 1; p >= 0; p--) {
            if(p2 < 0)
                break;
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1--];
            } else {
                nums1[p] = nums2[p2--];
            }
        }
    }


    //Approach 2: Three pointers (Start from the beginning)
    /*
    Time complexity: O(n + m)
    We are performing n + 2 reads and m + 2 write
    Space complexity: O(m) for the additional array
     */
    static void mergeThreePointers(int[] nums1,
                                   int m,
                                   int[] nums2,
                                   int n)
    {
        int[] nums1Copy = new int[m];
        for(int i = 0; i < m; i++) {
            nums1Copy[i] = nums1[i];
        }
        //Pointer for nums1Copy
        int p1 = 0;
        //Pointer for nums2
        int p2 = 0;
        for(int p = 0; p < m + n; p++) {
            // We also need to ensure that p1 and p2 aren't over the boundaries
            // of their respective arrays.
            if(p2 >= n || (p1 < m && nums1Copy[p1] < nums2[p2]))
                nums1[p] = nums1Copy[p1++];
            else
                nums1[p] = nums2[p2++];
        }
    }


    //Approach 1: Merge and sort
    /*
    Time complexity: O(n + m)log(n + m)
    We're sorting a list of length n + m

    Space complexity: O(n) caused by built-in sorting algo
     */
    static void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }
        Arrays.sort(nums1);
    }

}
