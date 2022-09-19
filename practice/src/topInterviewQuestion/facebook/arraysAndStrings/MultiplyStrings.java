package topInterviewQuestion.facebook.arraysAndStrings;
//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3013/
public class MultiplyStrings {

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "23";
        // 2829
        //System.out.println(multiply(num1, num2));
        System.out.println(multiplyApproach3(num1, num2));
    }

    //Approach 3: Sum the product from all pairs of digits
    /*
    Time: O(M * N) -> During multiplication, we perform N operations for each of the M digits of the second number.
    Space: O(M + N) -> Because String immutable, need temporary data structure O(M + N) space is required to store the answer
     */
    static String multiplyApproach3(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        StringBuilder firstNumber = new StringBuilder(num1);
        StringBuilder secondNumber = new StringBuilder(num2);
        //Reverse both the numbers
        firstNumber.reverse();
        secondNumber.reverse();
        // To store the multiplication result of each digit of secondNumber with firstNumber.
        int N = firstNumber.length() + secondNumber.length();
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < N; i++) {
            answer.append(0);
        }
        for(int place2 = 0; place2 < secondNumber.length(); place2++) {
            int digit2 = secondNumber.charAt(place2) - '0';
            // For each digit in secondNumber multiply the digit by all digits in firstNumber.
            for(int place1 = 0; place1 < firstNumber.length(); place1++) {
                int digit1 = firstNumber.charAt(place1) - '0';
                // The number of zeros from multiplying to digits depends on the
                // place of digit2 in secondNumber and the place of the digit1 in firstNumber.
                int currentPos = place1 + place2;
                // The digit currently at position currentPos in the answer string
                // is carried over and summed with the current result.
                int carry = answer.charAt(currentPos) - '0'; //Get the carry
                int multiplication = digit1 * digit2 + carry;
                // Set the ones place of the multiplication result.
                answer.setCharAt(currentPos, (char)(multiplication % 10 + '0')); //Set number without carry
                // Carry the tens place of the multiplication result by
                // adding it to the next position in the answer array.
                int value = (answer.charAt(currentPos + 1) - '0') + multiplication / 10;
                answer.setCharAt(currentPos + 1, (char)(value + '0')); //Add carry to the next number
            }
        }
        // Pop excess 0 from the rear of answer.
        if(answer.charAt(answer.length() - 1) == '0') {
            answer.deleteCharAt(answer.length() - 1);
        }
        answer.reverse();
        return answer.toString();
    }

    // Correction: https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
    //Time: O(m * n)
    //Space: O(m + n)
    public static String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] pos = new int[m + n];
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul + pos[p2];
                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int p : pos) {
            if(!(stringBuilder.length() == 0 && p == 0)) { //Skip the first element if 0
                stringBuilder.append(p);
            }
        }
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }


}
