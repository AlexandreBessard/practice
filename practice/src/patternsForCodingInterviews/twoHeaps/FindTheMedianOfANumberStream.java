package patternsForCodingInterviews.twoHeaps;

import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743994867_62Unit
public class FindTheMedianOfANumberStream {

    public static void main(String[] args) {
        FindTheMedianOfANumberStream medianOfAStream = new FindTheMedianOfANumberStream();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());
    }

    PriorityQueue<Integer> maxHeap; //Containing first half of numbers
    PriorityQueue<Integer> minHeap; //Containing the second half of numbers

    public FindTheMedianOfANumberStream(){
        maxHeap = new PriorityQueue<>((a, b) -> b - a); //Higher number is the priority
        minHeap = new PriorityQueue<>((a, b) -> a - b); //Smaller nuumber is the priority
    }

    /*
    Time: Due to insertion in the heap, take O(log N)
    Space: O(N) storing all the numbers
     */
    public void insertNum(int num) {
        if(maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        //either both the heaps will have equal number of elements or max-heap wil have one more element than min-heap
        if(maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if(maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    /*
    Time: O(1)
     */
    public double findMedian() {
        if(maxHeap.size() == minHeap.size()) {
            //We have even number of elements, take the average of middle two elements
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }
        //Because max-heap will have one more element than the mid-heap
        return maxHeap.peek();
    }

}
