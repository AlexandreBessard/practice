package leetcode.others;

import java.util.Random;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/114/others/825/
public class FindTheCelebrity {
    /*
    Suppose you are at a party with n people labeled from 0 to n - 1 and among them, there may exist one celebrity.
    The definition of a celebrity is that all the other n - 1 people know the celebrity,
    but the celebrity does not know any of them.
     */
    public static void main(String[] args) {
        int[][] graph = {{1,1,0},{0,1,0},{1,1,1}};
        System.out.println(Relation.knows(1, 2));
        //System.out.println(findCelebrity());
        SolutionLogicalDeduction.findCelebrity(3);
    }


    //Approach 1: Brut Force
    static int findCelebrity(int n) {

        return 0;
    }


    //Approach 2: Logical Deduction
    static class SolutionLogicalDeduction extends Relation {
        private static int numberOfPeople;

        public static int findCelebrity(int n) {
            numberOfPeople = n;
            int celebrityCandidate = 0;
            for(int i = 0; i < n; i++) {
                if(knows(celebrityCandidate, i)){
                    celebrityCandidate = i;
                }
            }
            if(isCelebrity(celebrityCandidate)) {
                return celebrityCandidate;
            }
            return -1;
        }
        private static boolean isCelebrity(int i) {
            for(int j = 0; j < numberOfPeople; j++) {
                if(i == j)
                    continue;
                if(knows(i, j) || !knows(j, i)){
                    return false;
                }
            }
            return true;
        }
    }



    //Approach 1: brut force
    /*
    Time complexity: O(n²)
    Space complexity: O(1)
     */
    static class SolutionBrutForce extends Relation {
        private static int numberOfPeople;
        public static int findCelebrity(int n) {
            numberOfPeople = n;
            for(int i = 0; i < n; i++) {
                if(isCelebrity(i)) {
                    return i;
                }
            }
            return -1;
        }
        private static boolean isCelebrity(int i) {
            for(int j = 0; j < numberOfPeople; j++) {
                if(i == j) //Do not ask if they know themselves
                    continue;
                if(knows(i, j)) {
                    return false;
                }
            }
            return true;
        }
    }


    /*
    function that tells you whether A knows B.
    bool knows(a, b)
     */
    static class Relation {
        static Random random = new Random();
        protected static boolean knows(int a, int b) {
            //impl
            int val = random.nextInt(2);
            return val > 0;
        }
    }

}
