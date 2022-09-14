package patternsForCodingInterviews.monotonicStack.examples;

import java.util.Stack;

//https://leetcode.com/problems/trapping-rain-water/
public class TrappingRainWater {

    /**
     * For Two pointers solution : {@link topInterviewQuestion.amazon.arraysAndStrings.TrappingRainWater}
     */
    public static void main(String[] args) {
        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] heights1 = {2,1,0,1,3,2,1,2};
        //Output: 6
        System.out.println(trap(heights1));
    }

    //Approach 3: Monotonic stack
    public static int trap(int[] height) {
        int ans = 0, current = 0;
        Stack<Integer> st = new Stack<>();
        while (current < height.length) {
            while (!st.empty() && height[current] > height[st.peek()]) {
                int top = st.peek();
                st.pop();
                if (st.empty())
                    break;
                int distance = current - st.peek() - 1;
                int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            st.push(current++);
        }
        return ans;
    }
}
