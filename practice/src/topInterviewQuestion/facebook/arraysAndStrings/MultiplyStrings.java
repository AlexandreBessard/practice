package topInterviewQuestion.facebook.arraysAndStrings;
//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3013/
public class MultiplyStrings {

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "23";
        // 2829
        System.out.println(multiply(num1, num2));
    }

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
