package topInterviewQuestion.facebook.topMostFrequentQuestions;

import java.util.*;

//https://leetcode.com/problems/nested-list-weight-sum/
public class NestedListWeightSum {
//TODO: Do not understand
    public static void main(String[] args) {
        //Input: nestedList = [1,[4,[6]]]
        var two = new NestedInteger(2);
        var first = new NestedInteger(1);
        var first1 = new NestedInteger(1);
        var firstMain = new NestedInteger();
        firstMain.add(first);
        firstMain.add(first1);
        two.add(firstMain);
        List<NestedInteger> list = new ArrayList<>();
        list.add(two);
        System.out.println(depthSumDFS(list)); //17
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
    static int depthSumBFS(List<NestedInteger> nestedList) { // [1,[4,[6]]]
        Queue<NestedInteger> q = new LinkedList<>(nestedList);
        int prevSum = 0, total = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; ++i){
                NestedInteger ni = q.poll();
                if(ni.isInteger()){
                    prevSum += ni.getInteger();
                }else{
                    q.addAll(ni.getList());
                }
            }
            total += prevSum;
        }
        return total;
    }
}

class NestedInteger {
    int value;
    NestedInteger ni;
    List<NestedInteger> list;

    public NestedInteger() {
        list = new ArrayList<>();
    }

    public NestedInteger(int value) {
        this.value = value;
        list = new ArrayList<>();
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return getList().isEmpty();
    }

    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return this.value;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        this.value = ni.value;
        list.add(ni);
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return this.list;
    }
}
