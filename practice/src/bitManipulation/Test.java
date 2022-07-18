package bitManipulation;

public class Test {

    /*
    Exclusive OR (XOR) results in 1 only if both the compared
    bits have a different value, otherwise, it results in 0.
     */
    public static void main(String[] args) {
        int[] nums = {4, 2, 1, 2, 1, 3};
        int a = 0;
        for(int i : nums) {
            System.out.println(a ^= i);
        }
    }
}
