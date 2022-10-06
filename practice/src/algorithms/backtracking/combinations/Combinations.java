package algorithms.backtracking.combinations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/combinations/
public class Combinations {
/*
Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
You may return the answer in any order.
 */
    public static void main(String[] args) {
        int n = 4; // 1, 2, 3, 4
        int k = 2;
        for(List<Integer> el : combine(n, k)) {
            System.out.println(el);
        }
    }

    static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new LinkedList<>(), n, k, 1);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, LinkedList<Integer> currList, int n, int k, int start) {
        if(currList.size() == k) {
            res.add(new ArrayList<>(currList));
        } else {
            for (int i = start; i < n + 1; i++) {
                currList.add(i);
                backtrack(res, currList, n, k, i + 1);
                currList.removeLast();
            }
        }
    }

}
