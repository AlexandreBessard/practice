package topInterviewQuestion.easy.sortingAndSearching;
//https://leetcode.com/explore/interview/card/top-interview-questions-easy/96/sorting-and-searching/774/
public class FirstBadVersion {

    public static void main(String[] args) {
        int n = 5;
        int bad = 4;
        System.out.println(firstBadVersion(5));
    }


    //Approach 2: Binary Search
    /*
    Time complexity: O(log n)
    Space complexity: O(1)
     */
    static int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("Check version : " + mid );
            if(isBadVersion(mid)){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    private static boolean isBadVersion(int num) {
        return num == 4;
    }



    //Approach 1: Linear Scan
    /*
    Time complexity: O(n)
    Space complexity: O(1)
     */
    static int firstBadversion(int n) {
        for(int i = 1; i < n; i++) {
            if(isBadVersion(i)){
                return i;
            }
        }
        return n;
    }
}
