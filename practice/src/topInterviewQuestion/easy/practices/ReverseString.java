package topInterviewQuestion.easy.practices;

public class ReverseString {

    public static void main(String[] args) {
        char[] c = {'H', 'e', 'l', 'l', 'o'};
        char[] c1 = {'H', 'e', 'l', 'l', 'o'};
        //Output: o l l e H
        reverseString(c);
        for(char el : c) {
            System.out.print(el + ", ");
        }
        System.out.println();
        reserveStringRecursion(c1);
        for(char el : c1) {
            System.out.print(el + ", ");
        }
    }

    //Recursive approach
    /*
    Time complexity: 0(N)
    Space complexity: 0(N) recursion stack.
     */
    static void reserveStringRecursion(char[] s) {
        helper(0, s.length - 1, s);
    }
    static void helper(int left, int right, char[] s) {
        //base case
        if(left == right)
            return;
        char tmp = s[left];
        s[left] = s[right];
        s[right] = tmp;
        helper(left + 1, right - 1, s);
    }


    /*
    Time complexity: O(n)
    Space complexity: 0(1)
     */
    static void reverseString(char[] s) {
        if(s.length < 2)
            return;
        int left = 0;
        int right = s.length - 1;
        while(left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }

}
