package leetcode.design;

import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/explore/interview/card/amazon/81/design/495/
public class FindMedianFromDataStream {
/*
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

Example 1:
Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]
 */
    public static void main(String[] args) {
        var obj = new MedianFinder();
        obj.addNum(1);
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
    }

    //Two heaps
    static class MedianFinder {

        //Length of the larger half is either n / 2 or n / 2 + 1 depend on n's parity
        PriorityQueue<Double> max = new PriorityQueue<>(Comparator.reverseOrder());
        //Length of smaller half is kept to be n / 2 at all time
        PriorityQueue<Double> min = new PriorityQueue<>(); //Smallest element is the priority

        /*
        Time: 3 O(log n) heap operations
         */
        public void addNum(double num) {
            //min heap has always his length equal or equal + 1 than max heap
            min.offer(num);
            max.offer(min.poll());
            if(min.size() < max.size()) {
                min.offer(max.poll());
            }
        }

        //Only need to peek the two heap's to number to calculate median
        /*
        Time: O(1)
         */
        public double findMedian() {
            //even elements, pick up two elements from each half
            if(min.size() == max.size()) {
                return (min.peek() + max.peek()) / 2;
                //odd elements, pick one from the smaller half
            } else {
                return min.peek();
            }
        }

    }

}
