package patternsForCodingInterviews.k_wayMerge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744436924_112Unit
public class _1KPairsWithLargestSums {

    public static void main(String[] args) {
        int[] l1 = new int[] { 9, 8, 2 };
        int[] l2 = new int[] { 6, 3, 1 };
        List<int[]> result = findKLargestPairs(l1, l2, 3);
        System.out.print("Pairs with largest sum are: ");
        for (int[] pair : result)
            System.out.print("[" + pair[0] + ", " + pair[1] + "] ");
    }

    /*
    Time: O(N * M * logK) where N and M are the total number of elements in both arrays.
    Space: O(K) by storing K largest pairs.
     */
    public static List<int[]> findKLargestPairs(int[] nums1, int[] nums2, int k)
    {
        //smaller sum
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (p1, p2) -> (p1[0] + p1[1]) - (p2[0] + p2[1])
        );
        //Keep in mind, the array is in decreasing order
        for(int i = 0; i < nums1.length && i < k; i++) {
            for(int j = 0; j < nums2.length && j < k; j++) {
                if(minHeap.size() < k) {
                    minHeap.add(new int[]{nums1[i], nums2[j]});
                } else {
                    // if the sum of the two numbers from the two arrays is smaller than the
                    // smallest (top) element of the heap, we can 'break' here. Since the arrays
                    // are sorted in the descending order, we'll not be able to find a pair with a
                    // higher sum moving forward.
                    if(nums1[i] + nums2[j] < minHeap.peek()[0] + minHeap.peek()[1]) {
                        break;
                    } else {
                        //else: we've a pair with a larger sum, remove top and insert this pair in heap
                        minHeap.poll();
                        minHeap.add(new int[] {nums1[i], nums2[j]});
                    }
                }
            }
        }
        return new ArrayList<>(minHeap);
    }
}
