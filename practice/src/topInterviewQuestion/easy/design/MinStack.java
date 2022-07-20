package topInterviewQuestion.easy.design;

import java.util.Stack;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/98/design/562/
public class MinStack {


}

//Approach 3: Improved Two Stacks
/*
Time complexity: O(1)
Space complexity: O(N)
 */
class MinStackApproach3 {
    private Stack<Integer> stack = new Stack<>();
    private Stack<int[]> minStack = new Stack<>();
    public MinStackApproach3() {}
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x < minStack.peek()[0])
            minStack.push(new int[]{x, 1});
        else if(x == minStack.peek()[0]){
            minStack.peek()[1]++;
        }
    }
    public void pop() {
        if (stack.peek().equals(minStack.peek()[0])) {
            minStack.peek()[1]--;
        }
        if(minStack.peek()[0] == 0) {
            minStack.pop();
        }
        stack.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return minStack.peek()[0];
    }
}


//Approach 2: Two stacks
/*
Time complexity: O(1)
Space complexity: O(N)
 */
class MinStackApproach2 {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();
    public MinStackApproach2 () {}
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() ||x <= minStack.peek()) {
            minStack.push(x);
        }
    }
    public void pop() {
        if(stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return minStack.peek();
    }
}

//Approach 1: Stack of value / minimum Pairs
//Time complexity: O(1) for all operations
class MinStackApproach1 {
    private Stack<int[]> stack = new Stack<>();
    public MinStackApproach1() {}
    public void push(int x) {
        if(stack.isEmpty()) {
            stack.push(new int[] {x, x});
            return;
        }
        int currentMin = stack.peek()[1];
        //Keep the minimum values [12, 12], [6, 6], [10, 6]
        stack.push(new int[]{x, Math.min(x, currentMin)});
    }
    public void pop() {
        stack.pop();
    }
    public int top() {
        return stack.peek()[0];
    }
    public int getMin() {
        return stack.peek()[1];
    }

}
