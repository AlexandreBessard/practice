package topInterviewQuestion.facebook.sortingAndSearching;

//https://leetcode.com/explore/interview/card/facebook/54/sorting-and-searching-3/308/
public class DivideTwoIntegers {

    public static void main(String[] args) {
        var obj = new DivideTwoIntegers();
        System.out.println(obj.divideApproach2(10, -3));
    }

    //Approach 2: Repeated Exponential Searches
    /*
    Time: O(log² n)
    Space: O(1)
     */
    private static int HALF_INT_MIN = -1073741824;
    public int divideApproach2(int dividend, int divisor) {
        // Special case: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        /* We need to convert both numbers to negatives.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }
        int quotient = 0;
        /* Once the divisor is bigger than the current dividend,
         * we can't fit any more copies of the divisor into it. */
        while (divisor >= dividend) {
            /* We know it'll fit at least once as divivend >= divisor.
             * Note: We use a negative powerOfTwo as it's possible we might have
             * the case divide(INT_MIN, -1). */
            int powerOfTwo = -1;
            int value = divisor;
            /* Check if double the current value is too big. If not, continue doubling.
             * If it is too big, stop doubling and continue with the next step */
            while (value >= HALF_INT_MIN && value + value >= dividend) {
                value += value;
                powerOfTwo += powerOfTwo;
            }
            // We have been able to subtract divisor another powerOfTwo times.
            quotient += powerOfTwo;
            // Remove value so far so that we can continue the process with remainder.
            dividend -= value;
        }
        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if (negatives != 1) {
            return -quotient;
        }
        return quotient;
    }

    //Approach 1: Repeated Subtraction -> DOES NOT COVER ALL CASES see approach 2 above
    /*
    Time: O(n)
    Space: O(1)
     */
    public int divide(int dividend, int divisor) {
        //Special case overflow
        if (dividend == Integer.MAX_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        /* We need to convert both numbers to negatives
         * for the reasons explained above.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend; //Transform dividend to a negative number
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }
        /* Count how many times the divisor has to be added
         * to get the dividend. This is the quotient. */
        int quotient = 0;
        while (dividend - divisor <= 0) {
            quotient--; //Count of many times dividend is below or equal 0
            dividend -= divisor;
        }
        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if (negatives != 1) {
            quotient = -quotient;
        }
        return quotient;
    }

}
