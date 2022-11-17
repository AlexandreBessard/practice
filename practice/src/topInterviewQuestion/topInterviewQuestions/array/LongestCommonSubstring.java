package topInterviewQuestion.topInterviewQuestions.array;

public class LongestCommonSubstring {
    /*
    Find the max length of common substring from 2 strings
     */
    public static void main(String[] args) {
        String s1 = "ABCXYZAY";
        String s2 = "XYZABCB";
        //Output 4 -> Common substring: 'XYZA'
        System.out.println(lcsubstr(s1, s2));
        System.out.println("DP : ");
        System.out.println(LCSubStr(s2.toCharArray(), s1.toCharArray(), s2.length(), s1.length()));
    }

    /*
    Approach 2: Dynamic programming
    https://www.interviewbit.com/blog/longest-common-substring/
    Time: O(n * m)
    Space: O(n * m)
     */
    static int LCSubStr(char[] str1, char[] str2, int N, int M) {
        int[][] LCStuff = new int[N + 1][M + 1];
        int mx = 0;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (i == 0 || j == 0)
                    LCStuff[i][j] = 0;
                else if (str1[i - 1] == str2[j - 1]) {
                    LCStuff[i][j]
                            = LCStuff[i - 1][j - 1] + 1;
                    mx = Integer.max(mx,
                            LCStuff[i][j]);
                } else
                    LCStuff[i][j] = 0;
            }
        }
        return mx;
    }


    /*
    Approach 1: Iterative
    Time: O(n^2 * m) where n and m are the lengths of sequences.
    Space: O(1)
    Can be resolved with Dynamic programming
     */
    public static int lcsubstr(String str1, String str2) {
        int ans = 0;

        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                int k = 0;
                //k is used to increment by one to have access to the next element
                while ((i + k) < str1.length() && (j + k) < str2.length()
                        && str1.charAt(i + k) == str2.charAt(j + k)) {
                    k = k + 1;
                }

                ans = Math.max(ans, k);
            }
        }
        return ans;
    }

}
