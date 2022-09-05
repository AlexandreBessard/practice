package topInterviewQuestion.facebook.arraysAndStrings;

//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/263/
public class AddBinary {

    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";
        //Output: 10101
        System.out.println(addBinary(a, b));
    }


    //Approach 1: Bit-by-bit compuation
    /*
    Time: O(max(N, M) where N and M are lengths of the input strings a and b
    Space: O(max(N, M) to keep the answer
     */
    public static String addBinary(String a, String b) {
        int n = a.length();
        int m = b.length();
        //n is always greater than m
        if (n < m)
            return addBinary(b, a);
        int L = Math.max(n, m);
        var sb = new StringBuilder();
        int carry = 0;
        int j = m - 1;
        for (int i = L - 1; i > -1; i--) {
            if (a.charAt(i) == '1')
                carry++;
            if (j > -1 && b.charAt(j--) == '1')
                carry++;
            if (carry % 2 == 1)
                sb.append('1');
            else
                sb.append('0');
            carry /= 2;
        }
        if (carry == 1)
            sb.append('1');
        sb.reverse();
        return sb.toString();
    }

}
