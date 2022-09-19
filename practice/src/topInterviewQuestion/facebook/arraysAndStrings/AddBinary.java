package topInterviewQuestion.facebook.arraysAndStrings;

//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/263/
public class AddBinary {

    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";
        //Output: 10101
        System.out.println(addBinaryOtherApproach(a, b));
    }

    //Other Approach
    /*
    Time: O(N)
    Space: O(MN) caused by data strucutre for reverse a string (string is immutable) need to allocate space after reversing the new string for the result
     */
    static String addBinaryOtherApproach(String a, String b) {
        StringBuilder sb = new StringBuilder(); //Google immutability or string vs stringbuilder if you don't know why we use this instead of regular string
        int i = a.length() - 1, j = b.length() -1, carry = 0; //two pointers starting from the back, just think of adding two regular ints from you add from back
        while (i >= 0 || j >= 0) {
            int sum = carry; //if there is a carry from the last addition, add it to carry
            if (j >= 0) sum += b.charAt(j--) - '0'; //we subtract '0' to get the int value of the char from the ascii
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2); //if sum==2 or sum==0 append 0 cause 1+1=0 in this case as this is base 2 (just like 1+9 is 0 if adding ints in columns)
            carry = sum / 2; //if sum==2 we have a carry, else no carry 1/2 rounds down to 0 in integer arithematic
        }
        if (carry != 0) sb.append(carry); //leftover carry, add it
        return sb.reverse().toString();
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
