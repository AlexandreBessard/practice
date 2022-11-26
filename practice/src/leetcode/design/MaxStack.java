package leetcode.design;

import java.util.Stack;
import java.util.TreeSet;

public class MaxStack {
/*
Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.

Implement the MaxStack class:

MaxStack() Initializes the stack object.
void push(int x) Pushes element x onto the stack.
int pop() Removes the element on top of the stack and returns it.
int top() Gets the element on the top of the stack without removing it.
int peekMax() Retrieves the maximum element in the stack without removing it.
int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.

Example 1:
Input
["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
[[], [5], [1], [5], [], [], [], [], [], []]
Output
[null, null, null, null, 5, 5, 1, 5, 1, 5]
 */

    public static void main(String[] args) {
        MaxStack stk = new MaxStack();
        stk.push(10);
        stk.push(5);   // [5] the top of the stack and the maximum number is 5.
        stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
        stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
        System.out.println(stk.top());     // return 5, [5, 1, 5] the stack did not change.
        System.out.println(stk.popMax());  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
        System.out.println(stk.top());     // return 1, [5, 1] the stack did not change.
        System.out.println(stk.peekMax()); // return 5, [5, 1] the stack did not change.
        System.out.println(stk.pop());     // return 1, [5] the top of the stack and the max element is now 5.
        System.out.println(stk.top());     // return 5, [5] the stack did not change.
    }

    Stack<Integer> stack;
    Stack<Integer> maxStack;
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        pushHelper(x);
    }

    private void pushHelper(int x) {
        int tempMax = maxStack.isEmpty()
                ? Integer.MIN_VALUE  //get min value to initialize our tempMax
                : maxStack.peek(); //get max value from the stack
        if(x  > tempMax) { //true means new higher value
            tempMax = x;
        }
        //ex: stack: 10, 5, 1, 5
        //ex: max: 10, 10, 10, 10
        stack.push(x);
        maxStack.push(tempMax);
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    //Return from the stack
    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    //Remove the top element
    public int popMax() {
        int max = maxStack.peek(); //maximum element
        Stack<Integer> buffer = new Stack<>();
        while(stack.peek() != max) { //Get the element from the stack which is the same as max
            buffer.push(stack.pop());
            maxStack.pop();
        }
        stack.pop(); //same value as maxStack
        maxStack.pop();
        while(!buffer.isEmpty()) { //get values from the buffer and push it to see our new maxHeap
            int x = buffer.pop();
            pushHelper(x);
        }
        return max;
    }
}
