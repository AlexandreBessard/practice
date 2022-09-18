package topInterviewQuestion.facebook.top10MostFrequentQuestions;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/nested-list-weight-sum/
public class NestedListWeightSum {

    public static void main(String[] args) {
        //Input: nestedList = [1,[4,[6]]]
    }

    //DFS
    /*
    Time: O(N)
    Space: O(N)
     */
    public static int depthSumDFS(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private static int dfs(List<NestedInteger> list, int depth) {
        int total = 0;
        for (NestedInteger nested : list) {
            if (nested.isInteger()) {
                total += nested.value * depth;
            } else {
                total += dfs(nested.list, depth + 1);
            }
        }
        return total;
    }

    //BFS
    /*
    Time: O(N)
    Space: O(N)
     */
    static int depthSumBFS(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        int sum = 0;
        int level = 1;
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //Each number is : NestedInteger object, These are NOT integer
                NestedInteger ni = queue.poll();
                if (ni.isInteger()) {
                    sum += ni.getInteger() * level;
                } else {
                    queue.addAll(ni.getList());
                }
            }
            level++;
        }
        return sum;
    }
}

class NestedInteger {
    int value;
    NestedInteger ni;
    List<NestedInteger> list;

    public NestedInteger() {
    }

    public NestedInteger(int value) {
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return false;
    }

    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return null;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        this.ni = ni;
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return null;
    }
}
