package topInterviewQuestion.facebook.recursion;

public class RegularExpressionMatching {

    public static void main(String[] args) {
        String s = "aa", p = "a";
        String s1 = "ab";
        String p1 = ".*"; //Zero or more characters
        var obj = new RegularExpressionMatching();
        System.out.println(isMathTopDown(s, p1));
        //System.out.println(obj.isMatch(s1, p1));
        //False
        //System.out.println(obj.isMatch(s, p));
        //True
        //System.out.println(obj.isMatch(s, "a*"));
    }

    //Approach 1: Recursive
    public static boolean isMatchRecursive(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatchRecursive(text, pattern.substring(2)) ||
                    (first_match && isMatchRecursive(text.substring(1), pattern)));
        } else {
            return first_match && isMatchRecursive(text.substring(1), pattern.substring(1));
        }
    }
    //Approach 2: Top-Down
    enum Result {TRUE, FALSE}
    static Result[][] memo;
    public static boolean isMathTopDown(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }
    private static boolean dp(int i, int j, String text, String pattern) {
        if(memo[i][j] != null)
            return memo[i][j] == Result.TRUE;
        boolean ans;
        if(j == pattern.length()) {
            ans = (i == text.length());
        } else {
            //Check if ith and jth letters from text and pattern respectively are matching
            boolean firstMath = (i < text.length()
                    && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
            //If next jth letter from pattern is '*'
            if(j + 1  < pattern.length() && pattern.charAt(j + 1) == '*') {
                ans = ((dp(i, j + 2, text, pattern)) || firstMath && dp(i + 1, j, text, pattern));
            } else {
                ans = firstMath && dp(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    /*
    Time: O(m*n)
    Space: O(m*n)
     */
    public boolean isMatch(String s, String p) {
        // corner case
        if(s == null || p == null)
            return false;

        // M[i][j] represents if the 1st i characters in s can match the 1st j characters in p
        boolean[][] M = new boolean[s.length() + 1][p.length() + 1];

        // initialization:
        // 1. M[0][0] = true, since empty string matches empty pattern
        M[0][0] = true;

        // 2. M[i][0] = false(which is default value of the boolean array) since empty pattern cannot match non-empty string
        // 3. M[0][j]: what pattern matches empty string ""? It should be #*#*#*#*..., or (#*)* if allow me to represent regex using regex :P,
        // and for this case we need to check manually:
        // as we can see, the length of pattern should be even && the character at the even position should be *,
        // thus for odd length, M[0][j] = false which is default. So we can just skip the odd position, i.e. j starts from 2, the interval of j is also 2.
        // and notice that the length of repeat sub-pattern #* is only 2, we can just make use of M[0][j - 2] rather than scanning j length each time
        // for checking if it matches #*#*#*#*.
        for(int j = 2; j < p.length() + 1; j +=2){
            if(p.charAt(j - 1) == '*' && M[0][j - 2]){
                M[0][j] = true;
            }
        }

        // Induction rule is very similar to edit distance, where we also consider from the end. And it is based on what character in the pattern we meet.
        // 1. if p.charAt(j) == s.charAt(i), M[i][j] = M[i - 1][j - 1]
        //    ######a(i)
        //    ####a(j)
        // 2. if p.charAt(j) == '.', M[i][j] = M[i - 1][j - 1]
        // 	  #######a(i)
        //    ####.(j)
        // 3. if p.charAt(j) == '*':
        //    1. if p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i), then b* is counted as empty. M[i][j] = M[i][j - 2]
        //       #####a(i)
        //       ####b*(j)
        //    2.if p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i):
        //       ######a(i)
        //       ####.*(j)
        //
        // 	  	 #####a(i)
        //    	 ###a*(j)
        //      2.1 if p.charAt(j - 1) is counted as empty, then M[i][j] = M[i][j - 2]
        //      2.2 if counted as one, then M[i][j] = M[i - 1][j - 2]
        //      2.3 if counted as multiple, then M[i][j] = M[i - 1][j]

        // recap:
        // M[i][j] = M[i - 1][j - 1]
        // M[i][j] = M[i - 1][j - 1]
        // M[i][j] = M[i][j - 2]
        // M[i][j] = M[i][j - 2]
        // M[i][j] = M[i - 1][j - 2]
        // M[i][j] = M[i - 1][j]
        // Observation: from above, we can see to get M[i][j], we need to know previous elements in M, i.e. we need to compute them first.
        // which determines i goes from 1 to m - 1, j goes from 1 to n + 1

        for(int i = 1; i < s.length() + 1; i++){
            for(int j = 1; j < p.length() + 1; j++){
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if(curS == curP || curP == '.'){
                    M[i][j] = M[i - 1][j - 1];
                }else if(curP == '*'){
                    char preCurP = p.charAt(j - 2);
                    if(preCurP != '.' && preCurP != curS){
                        M[i][j] = M[i][j - 2];
                    }else{
                        M[i][j] = (M[i][j - 2] || M[i - 1][j - 2] || M[i - 1][j]);
                    }
                }
            }
        }

        return M[s.length()][p.length()];
    }

}
