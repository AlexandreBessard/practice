package algorithms.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class TopDown {

    public static void main(String[] args) {
        System.out.println(fibonacci(3));
    }

    static Map<Integer, Integer> memo = new HashMap<>();
    static int fibonacci(int num) {
        //Base case
        if(num == 0 || num == 1) {
            return num;
        }
        //Get from the cache
        if(memo.containsKey(num)) {
            return memo.get(num);
        }
        //Recursion
        memo.put(num, fibonacci(num - 1)
                + fibonacci(num - 2));
        //Return from the cache
        return memo.get(num);
    }
}
