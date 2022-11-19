package leetcode.sortingAndSearching;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/798/
public class TopKFrequentElements {
/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 */
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] nums2 = {1, 1, 1, 2, 2, 2, 3, 3, 3};
        int[] res = topKFrequentQuickSelect(nums, 2);
        System.out.println(Arrays.toString(res));
        /*
        for(int i : topKFrequentQuickSelect(nums, 2)) {
            System.out.print(i + ", ");
        }

         */
    }
    //Other approach easier to understand using MinHeap:
    /**
     * {@link patternsForCodingInterviews.topKElements.TopKFrequentNumbers}
     */

    //https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort
    //Approach 3: Bucket Sort
    public static List<Integer> topKFrequentBucketSorted(int[] nums, int k) {
        LinkedList<Integer>[] bucket = new LinkedList[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }
        for (int key : frequencyMap.keySet()) {
            //Key based on the most frequent element
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new LinkedList<>();
            }
            bucket[frequency].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int pos = bucket.length - 1; pos >= 0; pos--) {
            if (bucket[pos] != null) {
                LinkedList<Integer> elements = bucket[pos];
                int idx = elements.size() - 1;
                while(res.size() < k && idx >= 0) {
                    res.add(elements.get(idx--));
                }
            }
        }
        return res;
    }


    //Approach 2: Quickselect
    /*
    Quickselect is a selection algorithm to find the k-th smallest
    element in an unordered list. It is related to the quick sort sorting algorithm.
     */
    static int[] unique;
    static Map<Integer, Integer> count;
    private static void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }
    private static int partition(int left, int right, int pivot_index) {
        int pivot_frequency = count.get(unique[pivot_index]);
        // 1. move pivot to end
        swap(pivot_index, right);
        int store_index = left;
        // 2. move all less frequent elements to the left
        for (int i = left; i <= right; i++) {
            if (count.get(unique[i]) < pivot_frequency) {
                swap(store_index, i);
                store_index++;
            }
        }
        // 3. move pivot to its final place
        swap(store_index, right);
        return store_index;
    }
    private static void quickselect(int left, int right, int k_smallest) {
        /*
        Sort a list within left..right till kth less frequent element
        takes its place.
        */
        // base case: the list contains only one element
        if (left == right) return;
        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);
        // find the pivot position in a sorted list
        pivot_index = partition(left, right, pivot_index);
        // if the pivot is in its final sorted position
        if (k_smallest == pivot_index) {
            return;
        } else if (k_smallest < pivot_index) {
            // go left
            quickselect(left, pivot_index - 1, k_smallest);
        } else {
            // go right
            quickselect(pivot_index + 1, right, k_smallest);
        }
    }
    public static int[] topKFrequentQuickSelect(int[] nums, int k) {
        // build hash map : character and how often it appears
        count = new HashMap<Integer, Integer>();
        for (int num: nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // array of unique elements
        int n = count.size();
        unique = new int[n];
        int i = 0;
        for (int num: count.keySet()) {
            unique[i] = num;
            i++;
        }
        // kth top frequent element is (n - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till
        // (n - k)th less frequent element takes its place (n - k) in a sorted array.
        // All element on the left are less frequent.
        // All the elements on the right are more frequent.
        quickselect(0, n - 1, n - k);
        // Return top k frequent elements
        return Arrays.copyOfRange(unique, n - k, n);
    }


    //Approach 1: Heap
    /*
    Time complexity: O(n log k) caused by the heap using 'poll()' method.
    Space complexity: O(N + k) to store hashmap with N elements and heap with k elements
     */
    static int[] topKFrequent(int[] nums, int k) {
        if(k == nums.length)
            return nums;
        Map<Integer, Integer> count = new HashMap<>();
        for(int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }
        //Get the smallest count element 3 : 1 -> poll() -> 3
        Queue<Integer> heap = new PriorityQueue<>(
                (n1, n2) -> count.get(n1) - count.get(n2)
        );
        for(int n : count.keySet()) {
            heap.add(n);
            if(heap.size() > k)
                heap.poll();
        }
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; i--) {
            top[i] = heap.poll();
        }
        return top;
    }

}
