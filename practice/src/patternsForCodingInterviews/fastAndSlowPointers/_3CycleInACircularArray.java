package patternsForCodingInterviews.fastAndSlowPointers;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743595805_19Unit
public class _3CycleInACircularArray {

    public static void main(String[] args) {
        System.out.println(loopExists(new int[] { 1, 2, -1, 2, 2 }));
        /*
        System.out.println(loopExists(new int[] { 2, 2, -1, 2 }));
        System.out.println(loopExists(new int[] { 2, 1, -1, -2 }));
         */
    }

    /*
    Time: O(N²) try to find cycle for each element
    Space: O(N)
     */
    public static boolean loopExists(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            boolean isForward = arr[i] >= 0; //If we move forward or not
            int slow = i;
            int fast = i;
            //if slow or fast becomes '-1' this means we can no find cycle for this number
            do {
                slow = findNextIndex(arr, isForward, slow); //Move one step for slow pointer
                fast = findNextIndex(arr, isForward, fast); // move one step for fast pointer
                if(fast != -1) {
                    fast = findNextIndex(arr, isForward, fast); // move another step for fast ptr
                }
            } while (slow != -1 && fast != -1 && slow != fast);
            if(slow != -1 && slow == fast) {
                return true;
            }
        }
        return false;
    }
    private static int findNextIndex(int[] arr, boolean isForward, int currentIndex) {
        boolean direction = arr[currentIndex] >= 0;
        if(isForward != direction) {
            return -1; //change in direction, return -1;
        }
        int nextIndex = (currentIndex + arr[currentIndex]) % arr.length;
        if(nextIndex < 0) {
            nextIndex += arr.length; //Wrap around for negative numbers
        }
        //One element cycle, return -1
        if(nextIndex == currentIndex) {
            nextIndex = -1;
        }
        return nextIndex;
    }


}
