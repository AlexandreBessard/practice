package topInterviewQuestion.facebook.topMostFrequentQuestions;

//https://leetcode.com/problem-list/top-facebook-questions/?sorting=W3sic29ydE9yZGVyIjoiREVTQ0VORElORyIsIm9yZGVyQnkiOiJGUkVRVUVOQ1kifV0%3D
public class AddStrings {

    public static void main(String[] args) {
        //Sum of 2 strings
        String num1 = "456";
        String num2 = "77";
        //Output : 533
        System.out.println(addStrings(num1, num2));
    }

    /*
    Time: O(max(N1, N2)) correspond to num1 and num2
    Space: O(max(N1, N2)) because the length of new string
     */
    static String addStrings(String num1, String num2) {
        var res = new StringBuilder();
        int carry = 0;
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        while (p1 >= 0 || p2 >= 0) {
            int x1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int x2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0;
            int value = (x1 + x2 + carry) % 10;
            carry = (x1 + x2 + carry) / 10;
            res.append(value);
            p1--;
            p2--;
        }
        if (carry != 0) {
            res.append(carry);
        }
        return res.toString();
    }


}
