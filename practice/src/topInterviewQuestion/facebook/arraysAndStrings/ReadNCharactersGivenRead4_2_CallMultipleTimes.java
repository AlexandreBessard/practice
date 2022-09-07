package topInterviewQuestion.facebook.arraysAndStrings;
//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/269/
public class ReadNCharactersGivenRead4_2_CallMultipleTimes {

    //TODO: need to be reviewed
    public static void main(String[] args) {

    }


    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];
    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                //TODO: must implement interface
                //buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr >= buffCnt) buffPtr = 0;
        }
        return ptr;
    }

}
