package patternsForCodingInterviews.modifiedBinarySearch;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744148867_79Unit
public class NextLetter {

    public static void main(String[] args) {
        /*
        System.out.println(NextLetter.searchNextLetter(
                new char[] { 'a', 'c', 'f', 'h' }, 'f'));

         */
        System.out.println(NextLetter.searchNextLetter(
                new char[] { 'a', 'c', 'f', 'h' }, 'b'));
        System.out.println(NextLetter.searchNextLetter(
                new char[] { 'a', 'c', 'f', 'h' }, 'm'));
        System.out.println(NextLetter.searchNextLetter(
                new char[] { 'a', 'c', 'f', 'h' }, 'h'));
    }


    /*
    Time: O(logN)
    Space: O(1)
     */
    public static char searchNextLetter(char[] letters, char key) {
        int n = letters.length;
        int start = 0;
        int end = n - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(key < letters[mid]) {
                end = mid - 1;
            } else { //if (key >= letters[mid]) {
                start = mid + 1;
            }
        }
        // since the loop is running until 'start <= end', so at the end of the while loop,
        // 'start == end+1'
        return letters[start % n];
    }

}
