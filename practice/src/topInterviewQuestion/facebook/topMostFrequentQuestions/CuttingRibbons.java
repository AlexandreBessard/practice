package topInterviewQuestion.facebook.topMostFrequentQuestions;
//https://leetcode.com/problems/cutting-ribbons/
public class CuttingRibbons {

    public static void main(String[] args) {
        int[] nums = {9,7,5};
        int k = 3;
        //Output 5
        System.out.println(maxLength(nums, k));
    }

    //Binary Search
    //Correction: https://leetcode.com/problems/cutting-ribbons/discuss/1263437/Java-Simple-Binary-Search-%2B-Explanation
    //Time: O(log n)
    static int maxLength(int[] ribbons, int k) {
        int max = Integer.MIN_VALUE;
        for(int len : ribbons) {
            max = Math.max(max, len);
        }
        int n = ribbons.length;
        int low = 1;
        int high = max;
        int mid, count;
        while(low <= high) {
            mid = low + (high - low) / 2;
            count = 0;
            for(int length : ribbons) {
                count += length / mid;
            }
            if(count < k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return Math.max(high, 0); //Equivalent of -> return high > 0 ? high : 0
    }
}
