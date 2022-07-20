package topInterviewQuestion.easy.others;

public class HammingDistance {

    /*
    Input: x = 1, y = 4
    Output: 2
Explanation:
    1   (0 0 0 1)
    4   (0 1 0 0)
           ↑   ↑
The above arrows point to positions where the corresponding bits are different
     */
    public static void main(String[] args) {
        hammingDistance(1, 4);
    }

    static int hammingDistance(int x, int y) {
        int sum = 0;
        for(int i = 0; i < 32; i++) {
            if((x & 1) != (y & 1)) {
                sum++;
            }
            x = x >> 1; //move bits to the right
            y = y >> 1;
        }
        return sum;
    }



}
