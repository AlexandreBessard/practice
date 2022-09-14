package patternsForCodingInterviews.monotonicStack.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/buildings-with-an-ocean-view/
public class BuildingWithAnOceanView {

    //The ocean is to the right of the buildings
    public static void main(String[] args) {
        int[] heights = {4, 2, 3, 1};
        System.out.println(Arrays.toString(findBuildings(heights)));
    }

    //Approach 2: Monotonic stack
    /*
    Stack is not empty, which means there is at least one greater or equal height building.
    Thus the view is blocked.
     Time: O(N) -> iterate over the given array once.
     Space: O(N)
     */
    public static int[] findBuildings(int[] heights) {
        int n = heights.length;
        List<Integer> list = new ArrayList<>();
        // Monotonically decreasing stack.
        Stack<Integer> stack = new Stack<>();
        for (int current = n - 1; current >= 0; --current) {
            // If the building to the right is smaller, we can pop it.
            //height of the building on top of the stack is less than the height of the current
            //building, we pop() from the stack.
            //If stack not empty, there is at least one greater or equal height building, Thus view is blocked.
            while (!stack.isEmpty() && heights[stack.peek()] < heights[current]) {
                stack.pop(); //We keep the greater building in the stack
            }
            // If the stack is empty, it means there is no building to the right
            // that can block the view of the current building.
            if (stack.isEmpty()) {
                list.add(current);
            }
            // Push the current building in the stack.
            stack.push(current);
        }
        // Push elements from list to answer array in reverse order.
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(list.size() - 1 - i);
        }
        return answer;
    }

}
