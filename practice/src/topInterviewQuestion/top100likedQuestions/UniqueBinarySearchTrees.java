package topInterviewQuestion.top100likedQuestions;

public class UniqueBinarySearchTrees {

    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    //Approach 1: Dynamic Programming
    //Explanation: https://www.youtube.com/watch?v=Ox0TenN3Zpg
    /** {@link patternsForCodingInterviews.subsets._3CountOfStructurallyUniqueBinarySearchTrees} */
    /*
    Time: O(N²)
    Space: O(N)
     */
    static int numTrees(int n) {
        int[] G = new int[n + 1];
        //Base cases, there is only one combination to construct a BST out of a sequence of length 1 (only root) or nothing (empty tree)
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) { //'i' -> Pick up as a root
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

}
