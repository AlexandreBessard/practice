package algorithms.unionFind;

import java.util.Arrays;
import java.util.Comparator;

//https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3912/
public class TheEarliestMomentWhenEveryoneBecomeFriends {

    public static void main(String[] args) {
        int[][] logs = {
                {20190101, 0, 1},
                {20190104, 3, 4},
                {20190107, 2, 3},
                {20190211, 1, 5},
                {20190224, 2, 4},
                {20190301, 0, 3},
                {20190312, 1, 2},
                {20190322, 4, 5}
        };
        int[][] logs1 = {
                {0, 2, 0},
                {1, 0, 1},
                {3, 0, 3},
                {4, 1, 2},
                {7, 3, 1}
        };
        var obj = new TheEarliestMomentWhenEveryoneBecomeFriends();
        //20190301
        System.out.println(obj.earliestAcq(logs, 6));
        //3
        System.out.println(obj.earliestAcq(logs1, 4));
    }

    public int earliestAcq(int[][] logs, int n) {
        //Must sort the events in chronological order
        Arrays.sort(logs, new Comparator<int[]>() {
            @Override
            public int compare(int[] log1, int[] log2) {
                //Increasing order
                return Integer.compare(log1[0], log2[0]);
            }
        });
        //Treat each individual as a separate group
        int groupCount = n;
        UnionFind uf = new UnionFind(n);
        for (int[] log : logs) {
            int timestamp = log[0];
            int friendA = log[1];
            int friendB = log[2];
            //We merge the groups along the way
            if (uf.union(friendA, friendB)) { //If true, we merged successfully
                groupCount -= 1;
            }
            //The moment when all individuals are connected to each other
            if (groupCount == 1) {
                return timestamp;
            }
        }
        //There are still more than one groups left
        return -1;
    }

    static class UnionFind {
        private int[] group;
        private int[] rank;

        public UnionFind(int size) {
            group = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                group[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int person) {
            if (person == group[person]) {
                return person;
            }
            return group[person] = find(group[person]);
        }

        /*
        merge the two groups that x and y belong
        return true if the groups are merged
         */
        public boolean union(int a, int b) {
            int groupA = find(a);
            int groupB = find(b);
            //Two people shared the same group
            if (groupA == groupB) {
                return false;
            }
            // Otherwise, merge the two groups.
            boolean isMerged = true;
            // Merge the lower-rank group into the higher-rank group.
            if (this.rank[groupA] > this.rank[groupB]) {
                this.group[groupB] = groupA;
            } else if (this.rank[groupA] < this.rank[groupB]) {
                this.group[groupA] = groupB;
            } else {
                this.group[groupB] = groupA;
                this.rank[groupA] += 1;
            }
            return isMerged;
        }
    }

}
