package topInterviewQuestion.top100likedQuestions;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/jump-game-ii/
public class JumpGameII {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jumpBFS(nums));
        System.out.println(jumpGreedy(nums));

    }

    //Approach 1: BFS
    /*
    Time: O(N^2)
   Space: O(N)
     */
    static int jumpBFS(int[] nums) { //   2, 3, 1, 1, 4
        boolean[] visited = new boolean[nums.length];
        Queue<Integer> q = new LinkedList<>();
        q.add(0); //Represent the index 0
        visited[0] = true;
        int depth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int index = q.remove();
                if (index == nums.length - 1) { //We reached the last index of the array
                    return depth;
                }
                for (int j = 1; j <= nums[index]; j++) { //Try each step from that element in index i
                    int neighbor_idx = index + j;
                    if (neighbor_idx > nums.length - 1) { //We are out of bound, do not go further and break this loop
                        break;
                    }
                    if (visited[neighbor_idx]) { //Already visited, move forward
                        continue;
                    }
                    q.add(neighbor_idx);
                    visited[neighbor_idx] = true;
                }
            }
            depth++;
        }
        return -1;
    }


    //Approach 2: Greedy
    /*
    Time: O(N)
    Space: O(1)
    The main idea is based on greedy. Let's say the range of the current jump is [curBegin, curEnd],
    curFarthest is the farthest point that all points in [curBegin, curEnd] can reach.
    Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest,
    then keep the above steps, as the following:
     */
    static int jumpGreedy(int[] nums) {
        int jumps = 0, //count the number of jumps
                curEnd = 0; // mark the end of the range that we can jump to
        int curFarthest = 0; // farthest place that we can reach
        for (int i = 0; i < nums.length - 1; i++) {
            // we continuously find the how far we can reach in the current jump
            curFarthest = Math.max(curFarthest, i + nums[i]); //end is 'i + nums[i]'. Determine which place will take us the farthest in the next jump
            // if we have come to the end of the current jump,
            // we need to make another jump
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }

}
