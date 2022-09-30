package dataStructuresAndAlgorithms.hashing;

import com.sun.security.jgss.GSSUtil;

import java.util.*;

//https://leetcode.com/problems/find-players-with-zero-or-one-losses/
public class FindPlayersWithZeroOrOneLosses {
    /*
    You are given an integer array matches where matches[i] = [winneri, loseri]
    indicates that the player winneri defeated player loseri in a match.
    ---------------------------------------------------------------------------
    Return a list answer of size 2 where:
    answer[0] is a list of all players that have not lost any matches.
    answer[1] is a list of all players that have lost exactly one match.
     */
    public static void main(String[] args) {
        int[][] matches = {
                {1, 3},
                {2, 3},
                {3, 6},
                {5, 6},
                {5, 7},
                {4, 5},
                {4, 8},
                {4, 9},
                {10, 4},
                {10, 9}
        };
        List<List<Integer>> res = findWinners(matches);
        System.out.println("Winners : " + res.get(0)); //Not lost any match
        System.out.println("Losers : " + res.get(1)); //Lost exactly one match
    }

    static List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> losses = new HashMap<>();
        for (int[] m : matches) {
            losses.put(m[0], losses.getOrDefault(m[0], 0));
            losses.put(m[1], losses.getOrDefault(m[1], 0) + 1);
        }
        List<List<Integer>> res = new ArrayList<>(2);
        res.add(new ArrayList<>()); // 0 index -> (lost no match)
        res.add(new ArrayList<>()); // 1 index -> (lost exactly 1 match)
        for (int player : losses.keySet()) {
            if (losses.get(player) <= 1) { //If the value is 0 (lost no match) or 1 (have lost exactly 1 match)
                res.get(losses.get(player))
                        .add(player);
            }
        }
        return res;
    }
}
