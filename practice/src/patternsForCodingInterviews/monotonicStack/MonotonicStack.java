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
        int[] res = monotonicStack(arr);
        System.out.println(Arrays.toString(res));
    }

    //Initialize monotonic Stack
    public static int[] monotonicStack(List<Integer> arr) {
        //initalize a monotonic stack
        Stack<Integer> s = new Stack<>();
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
