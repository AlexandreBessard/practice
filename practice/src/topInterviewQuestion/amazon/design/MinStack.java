package topInterviewQuestion.amazon.design;

import java.util.Stack;

//Approach 3: Improved Two Stacks
public class MinStack {

    public static void main(String[] args) {

    }

    //Space: O(N)
    Stack<Integer> stack = new Stack<>(); //Normal stack
    //[ (smallest) element, count ]
    Stack<int[]> minStack = new Stack<>(); //Keep the smallest element in the stack, use to get the Min element O(1) time.

    /*
    Time O(1)
     */
    public void push(int x) {
        stack.push(x); //Place element in the stack

        if(minStack.isEmpty() || x < minStack.peek()[0]) { //Keep the smallest element from this other stack
            minStack.push(new int[]{x, 1});
        } else if (x == minStack.peek()[0]) {
            minStack.peek()[1]++;
        }
    }

    //Time: O(1)
    public void pop() {
        // If the top of min stack is the same as the top of stack
        // then we need to decrement the count at the top by 1.
        if(stack.peek().equals(minStack.peek()[0])) {
            minStack.peek()[1]--;
        }
        // If the count at the top of min stack is now 0, then remove
        // that value as we're done with it.
        if(minStack.peek()[1] == 0) {
            minStack.pop();
        }
        stack.pop();
    }

    //Time: O(1)
    public int top() {
        return stack.peek();
    }

    //Time: O(1)
    public int getMin() {
        return minStack.peek()[0];
    }

}
