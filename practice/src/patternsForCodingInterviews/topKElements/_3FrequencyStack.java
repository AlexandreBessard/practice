package patternsForCodingInterviews.topKElements;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744377975_106Unit
public class _3FrequencyStack {

    public static void main(String[] args) {
        var frequencyStack = new _3FrequencyStack();
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(3);
        frequencyStack.push(2);
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(5);
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
    }

    int sequenceNumber = 0;
    PriorityQueue<Element> maxHeap = new PriorityQueue<>(new ElementComparator());
    Map<Integer, Integer> frequencyMap = new HashMap<>();

    /*
    Time: O(logN)
    Space: O(N)
     */
    public void push(int num) {
        frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        maxHeap.offer(new Element(num, frequencyMap.get(num), sequenceNumber++));
    }

    /*
    Time: O(logN)
     */
    public int pop() {
        int num = maxHeap.poll().number;
        //Decrement the frequency or remove if this is the last number
        if(frequencyMap.get(num) > 1) {
            frequencyMap.put(num, frequencyMap.get(num) - 1);
        } else {
            frequencyMap.remove(num);
        }
        return num;
    }

    static class ElementComparator implements Comparator<Element> {
        public int compare(Element e1, Element e2) {
            if(e1.frequency != e2.frequency) {
                return e2.frequency - e1.frequency;
            }
            // if both elements have same frequency, return the one that was pushed later
            // If there is a tie, return the number which was pushed later.
            return e2.sequenceNumber - e1.sequenceNumber;
        }
    }

    static class Element {
        int number;
        int frequency;
        int sequenceNumber;
        Element(int number, int frequency, int sequenceNumber) {
            this.number = number;
            this.frequency = frequency;
            this.sequenceNumber = sequenceNumber;
        }
    }


}
