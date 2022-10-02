package dataStructuresAndAlgorithms.greedy;

public class Maximum69Number {
    /*
    You are given a positive integer num consisting only of digits 6 and 9.
    Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
     */
    public static void main(String[] args) {
        int num = 9669;
        System.out.println(maximum69Number(num));
    }

    /*
    Time: O(L)  -> input number 'num' has up to most L digits
    Space: O(L)
     */
    static int maximum69Number(int num) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append(num);
        for(int i = 0; i < stringBuilder.length(); i++) {
            if(stringBuilder.charAt(i) == '6') {
                stringBuilder.setCharAt(i, '9');
                break;
            }
        }
        return Integer.parseInt(stringBuilder.toString());
    }

}
