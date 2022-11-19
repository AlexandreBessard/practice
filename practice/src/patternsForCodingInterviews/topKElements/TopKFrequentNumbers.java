package patternsForCodingInterviews.topKElements;

import java.util.*;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744303843_97Unit
public class TopKFrequentNumbers {

    public static void main(String[] args) {

        int[] nums3 = {1, 1, 1, 2, 2, 3};
        System.out.println(findTopKFrequentNumbers(nums3, 2));

        List<Integer> result = TopKFrequentNumbers.findTopKFrequentNumbers(
                new int[]{1, 3, 5, 12, 11, 12, 11}, 2);
        System.out.println("Here are the K frequent numbers: " + result);

        result =
                TopKFrequentNumbers.findTopKFrequentNumbers(new int[]{5, 12, 11, 3, 11}, 2);
        System.out.println("Here are the K frequent numbers: " + result);
    }

    /*
    Time: O(N + N * log K)
    Sapce: O(N)
     */
    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
        // find the frequency of each number
        //Key: Element, Value: Count
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();
        for (int n : nums) {
            numFrequencyMap.put(n, numFrequencyMap.getOrDefault(n, 0) + 1);
        }
        //We want the smaller element first -> we want to find to K frequent element
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>( //Smaller element are the priority
                (e1, e2) -> e1.getValue() - e2.getValue()
        );
        // go through all numbers of the numFrequencyMap and push them in the minHeap, which
        // will have  top k frequent numbers.
        // If the heap size is more than k, we remove the smallest (top) number
        for (Map.Entry<Integer, Integer> entry : numFrequencyMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); //Remove smaller element first
            }
        }
        // create a list of top k numbers
        List<Integer> topNumbers = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            topNumbers.add(minHeap.poll().getKey());
        }
        return topNumbers;
    }
}
