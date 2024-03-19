package topInterviewQuestion.easy;

import java.util.Stack;

public class ImplementQueueUsingStack {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.peek()); // return 1
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.empty()); // return false
    }

    static class MyQueue {
        private Stack<Integer> s1;
        private Stack<Integer> s2;

        public MyQueue() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }

        /*
        Time complexity: O(n)
        Space complexity: O(n)
         */
        public void push(int x) {
            while(!s1.isEmpty()) {
                // Transfer all numbers stored from s1 to s2
                s2.push(s1.pop());
            }
            // Tail of the Queue, number placed at the bottom
            s1.push(x);
            while (!s2.isEmpty()) {
                // Replace all numbers from s2 to s1
                // The older number stay on the top
                s1.push(s2.pop());
            }
        }

        public int pop() {
            return s1.pop();
        }

        /*
        Time complexity: O(1)
         */
        public int peek() {
            return s1.peek();
        }

        public boolean empty() {
            return s1.isEmpty();
        }

    }



}
