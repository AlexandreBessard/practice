package topInterviewQuestion.facebook.recursion;

import java.util.*;

//https://leetcode.com/explore/interview/card/facebook/53/recursion-3/293/
public class Permutations2 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        for (List<Integer> list : permuteUnique(nums)) {
            System.out.println(list);
        }
    }

    //Approach using BFS
    static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<List<Integer>> q = new LinkedList<>();
        q.add(new ArrayList<>());
        Arrays.sort(nums);
        for (int num : nums) { //  1, 1, 2
            int size = q.size();
            for (int i = 0; i < size; i++) {
                List<Integer> oldPermutation = q.poll();
                //this method allow us to handle duplicates
                int start = findLastEqualIndex(oldPermutation, num) + 1; //Be careful, we add 1 to avoid out of bound exception.
                for (int j = start; j <= oldPermutation.size(); j++) {
                    List<Integer> newPermutation = new ArrayList<>(oldPermutation);
                    newPermutation.add(j, num);
                    if (newPermutation.size() == nums.length) {
                        res.add(newPermutation);
                    } else {
                        q.add(newPermutation);
                    }
                }
            }
        }
        return res;
    }

    //Get the latest index which has the same value as target.k
    private static int findLastEqualIndex(List<Integer> list, int target) {
        if (list.isEmpty())
            return -1;
        int i = list.size() - 1;
        while (i >= 0 && list.get(i) != target) {
            i--;
        }
        return i;
    }
}
