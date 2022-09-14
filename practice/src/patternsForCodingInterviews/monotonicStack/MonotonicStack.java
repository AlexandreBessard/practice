package patternsForCodingInterviews.monotonicStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MonotonicStack {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>() {
            {
                add(2);
                add(3);
                add(1);
                add(4);
                add(1);
            }
        };
        System.out.println(arr);
        int[] res = monotonicStack(arr);
        System.out.println(Arrays.toString(res));
    }

    //Order of List<Integer> arr -> 2, 3, 1, 4, 1
    //Initialize monotonic Stack
    /*
    Monotonic stack is a stack that from top to bottom, elements are monotonically increasing.
    From right to left, we can check whether the top of the stack is bigger than the one in array.
    If it is not bigger than the one in array, it cannot be the solution, since the one in array is farther than it
     */
    public static int[] monotonicStack(List<Integer> arr) {
        //initalize a monotonic stack
        Stack<Integer> s = new Stack<>(); //Stack is monotonically increasing
        int[] ans = new int[arr.size()];
        //traverse from upperbound tp lowerbound
        for (int i = arr.size() - 1; i >= 0; i--) {
            while ((!s.isEmpty()) && (s.peek() <= arr.get(i))) {
                s.pop();
            }
            if (s.isEmpty()) {
                ans[i] = 0;
            } else {
                ans[i] = s.peek();
            }
            s.push(arr.get(i));
        }
        return ans;
    }

}
