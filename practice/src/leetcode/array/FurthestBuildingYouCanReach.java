package leetcode.array;

import java.util.PriorityQueue;

//https://leetcode.com/problems/furthest-building-you-can-reach/
public class FurthestBuildingYouCanReach {
/*
You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.

You start your journey from building 0 and move to the next building by possibly using bricks or ladders.

While moving from building i to building i+1 (0-indexed),

If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
 */
    public static void main(String[] args) {
        int[] heights = {4,2,7,6,9,14,12};
        int bricks = 5; // next build height - current building -> num of bricks required
        int ladders = 1; //unlimited jump
        System.out.println(fursthestBuilding(heights, bricks, ladders));
    }

    //Approach 1: Max-Heap
    /*
    Time: O(N log N) for the heap
    Space: O(N)
     */
    public static int fursthestBuilding(int[] heights, int bricks, int ladders) {
        // Create a priority queue with a comparator that makes it behave as a max-heap.
        PriorityQueue<Integer> brickAllocations =
                new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < heights.length - 1; i++) {
            int climb = heights[i + 1] - heights[i];
            // If this is actually a "jump down", skip it.
            if (climb <= 0) {
                continue;
            }
            brickAllocations.add(climb);
            bricks -= climb; //Use the brick
            //No bricks or ladder remaining
            if (bricks < 0 && ladders == 0) {
                return i;
            }
            //True means we do not have enough bricks to jump, use a ladder instead for the highest jump
            if (bricks < 0) {
                //We use a ladder
                bricks += brickAllocations.remove(); //We do not use the bricks, for the highest jump, use ladder
                ladders--; //We use 1 ladder, and move forward
            }
        }
        // If we got to here, this means we had enough materials to cover every climb.
        return heights.length - 1;
    }

}
