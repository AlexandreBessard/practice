package topInterviewQuestion.top100likedQuestions;

public class UniqueBinarySearchTrees {

    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    //Approach 1: Dynamic Programming
    //Explanation: https://www.youtube.com/watch?v=Ox0TenN3Zpg
    /*
    Time: O(N²)
    Space: O(N)
     */
    static int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

}
