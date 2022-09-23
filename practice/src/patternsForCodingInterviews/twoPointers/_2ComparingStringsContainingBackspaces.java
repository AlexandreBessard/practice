package patternsForCodingInterviews.twoPointers;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743506829_10Unit
public class _2ComparingStringsContainingBackspaces {

    public static void main(String[] args) {
        System.out.println(compare("xy#z", "xzz#"));
        System.out.println(compare("xy#z", "xyz#"));
        System.out.println(compare("xp#", "xyz##"));
        System.out.println(compare("xywrrmp", "xywrrmu#p"));
    }

    public static boolean compare(String str1, String str2) {
        //Use  two pointer Approach to compare the strings
        int index1 = str1.length() - 1;
        int index2 = str2.length() - 1;
        while(index1 >= 0 || index2 >= 0) {
            int i1 = getNextValidCharIndex(str1, index1);
            int i2 = getNextValidCharIndex(str2, index2);
            if(i1 < 0 && i2 < 0) { //Reach the end of both strings
                return true;
            }
            if(i1 < 0 || i2 < 0) { // reached the end of one of the strings
                return false;
            }
            if(str1.charAt(i1) != str1.charAt(i2)) {
                return false;
            }
            index1 = i1 - 1; //Go to the next index (previous because we have started from the end)
            index2 = i2 - 1;
        }
        return true;
    }
    private static int getNextValidCharIndex(String str, int index) {
        int backspaceCount = 0;
        while(index >= 0) {
            if(str.charAt(index) == '#') { //Found backspace character
                backspaceCount++; //we aggregated all hashtag.
            }
            else if(backspaceCount > 0) { //A non-backspace character remaining, remove hashtag
                backspaceCount--;
            }
            else {
                break; //return that index
            }
            index--;
        }
        return index;
    }

}
