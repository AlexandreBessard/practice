package topInterviewQuestion.facebook.treesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/3026/
public class ShortestDistanceFromAllBuildings {
    /*
    each 0 marks an empty land that you can pass by freely,
    each 1 marks a building that you cannot pass through, and
    each 2 marks an obstacle that you cannot pass through.

    You want to build a house on an empty land that reaches all buildings
    in the shortest total travel distance. You can only move up, down, left, and right.

    Return the shortest travel distance for such a house. If it is not possible
    to build such a house according to the above rules, return -1.
     */
    public static void main(String[] args) {
        int[][] grid = {
                {1, 0, 2, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };
        var obj = new ShortestDistanceFromAllBuildings();
        //Output 7: is minimal -> 3 + 3 + 1 = 7
        System.out.println(obj.shortestDistanceEmptyLand(grid));
    }

    //TODO: need to do the approach 3

    //Approach 2: BFS from Houses to Empty Land
    /*
    To visualize a 3 dimensional array:
    https://stackoverflow.com/questions/22699399/cant-understand-3d-arrays
     */
    public int shortestDistanceEmptyLand(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        int rows = grid.length;
        int cols = grid[0].length;
        int totalHouses = 0;
        // Store { total_dist, houses_count } for each cell.
        int[][][] distances = new int[rows][cols][2];
        // Count houses and start bfs from each house.
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (grid[row][col] == 1) {
                    totalHouses++;
                    bfs(grid, distances, row, col);
                }
            }
        }
        // Check all empty lands (0) with houses count equal to total houses and find the min ans.
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (distances[row][col][1] == totalHouses) {
                    minDistance = Math.min(minDistance, distances[row][col][0]);
                }
            }
        }
        // If we haven't found a valid cell return -1.
        if (minDistance == Integer.MAX_VALUE) {
            return -1;
        }
        return minDistance;
    }

    private void bfs(int[][] grid, int[][][] distances, int row, int col) {
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; //directions
        int rows = grid.length;
        int cols = grid[0].length;
        // Use a queue to do a bfs, starting from each cell located at (row, col).
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ row, col });
        // Keep track of visited cells.
        boolean[][] vis = new boolean[rows][cols];
        vis[row][col] = true;
        int steps = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                int[] curr = q.poll();
                row = curr[0];
                col = curr[1];
                // If we reached an empty cell, then add the distance
                // and increment the count of houses reached at this cell.
                if(grid[row][col] == 0) {
                    //For each houses, we count the distances from this house until we reach a building '1'
                    distances[row][col][0] += steps; // total distance sum from all houses to this empty land
                    distances[row][col][1] += 1; // number of houses that can reach this empty land.
                }
                // Traverse the next cells which is not a blockage.
                for (int[] dir : dirs) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];
                    if (nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols) {
                        if (!vis[nextRow][nextCol] && grid[nextRow][nextCol] == 0) {
                            vis[nextRow][nextCol] = true;
                            q.offer(new int[]{ nextRow, nextCol });
                        }
                    }
                }
            }
            // After traversing one level cells, increment the steps by 1.
            steps++;
        }
    }

    /******************************/

    /*
    Our graph is not weighted. We can consider each edge to have the same weight of 1.
    Since the graph is unweighted, BFS can be used to find the shortest path between a starting cell and any other reachable cell.
     */
    //BFS
    /*
    Time: O(N² * M²)
    Space: O(N * M)
     */
    public int shortestDistance(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        int rows = grid.length;
        int cols = grid[0].length;
        int totalHouses = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    totalHouses++;
                }
            }
        }
        //Find the min distance sum for each empty cell
        //Check for every 0 cell to see if the path is shorter
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 0) {
                    minDistance = Math.min(minDistance, bfs(grid, row, col, totalHouses));
                }
            }
        }
        // If it is impossible to reach all houses from any empty cell, then return -1.
        if(minDistance == Integer.MAX_VALUE) {
            return -1;
        }
        return minDistance;
    }

    private int bfs(int[][] grid, int row, int col, int totalHouses) {
        //Next four directions
        int dirs[][] = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        int rows = grid.length;
        int cols = grid[0].length;
        int distanceSum = 0;
        int housesReached = 0;
        //Queue to do a bfs, starting from (row, col) cell
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});
        //Keep track of visited cells
        boolean[][] vis = new boolean[rows][cols];
        vis[row][col] = true;
        int steps = 0;
        while(!q.isEmpty() && housesReached != totalHouses) {
            for(int i = q.size(); i > 0; i--) {
                int[] curr = q.poll();
                row = curr[0];
                col = curr[1];
                // If this cell is a house, then add the distance from source to this cell
                // and we go past from this cell.
                if(grid[row][col] == 1) {
                    distanceSum += steps;
                    housesReached++;
                    continue; //Continue by decrementing the i from the for loop
                }
                // This cell was empty cell, hence traverse the next cells which is not a blockage.
                for(int[] dir : dirs) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];
                    if(nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols) {
                        if(!vis[nextRow][nextCol] && grid[nextRow][nextCol] != 2) {
                            vis[nextRow][nextCol] = true;
                            q.add(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
            // After traversing one level of cells, increment the steps by 1 to reach to next level.
            steps++;
        }
        // If we did not reach all houses, then any cell visited also cannot reach all houses.
        // Set all cells visted to 2 so we do not check them again and return MAX_VALUE.
        if(housesReached != totalHouses) {
            for(row = 0; row < rows; row++) {
                for(col = 0; col < cols; col++) {
                    if(grid[row][col] == 0 && vis[row][col]) {
                        grid[row][col] = 2; //Allow us to by pass the next iteration because we can not reach all houses. (Invalid)
                    }
                }
            }
            return Integer.MAX_VALUE;
        }
        //If we reached all houses then return the total distance calculated.
        return distanceSum;
    }

}
