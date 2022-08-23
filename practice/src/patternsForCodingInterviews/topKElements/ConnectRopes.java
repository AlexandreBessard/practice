package patternsForCodingInterviews.topKElements;

import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744296940_96Unit
public class ConnectRopes {

    public static void main(String[] args) {
        int result = 0;
        result = ConnectRopes.minimumCostToConnectRopes(new int[] { 1, 3, 11, 5 });
        System.out.println("Minimum cost to connect ropes: " + result);
        /*
        result = ConnectRopes.minimumCostToConnectRopes(new int[] { 3, 4, 5, 6 });
        System.out.println("Minimum cost to connect ropes: " + result);
        result = ConnectRopes.minimumCostToConnectRopes(new int[] { 1, 3, 11, 5, 2 });
        System.out.println("Minimum cost to connect ropes: " + result);

         */
    }

    /*
    Time: O(N * logN)
    Space: O(N)
     */
    public static int minimumCostToConnectRopes(int[] ropeLengths) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> n1 - n2);
        //Add all ropes to the min heap
        for(int i = 0; i < ropeLengths.length; i++) {
            minHeap.add(ropeLengths[i]);
        }
        // go through the values of the heap, in each step take top (lowest) rope lengths
        // from the min heap connect them and push the result back to the min heap.
        // keep doing this until the heap is left with only one rope
        int result = 0;
        int temp = 0;
        while(minHeap.size() > 1) {
            temp = minHeap.poll() + minHeap.poll();
            result += temp;
            minHeap.add(temp);
        }
        return result;
    }


}
