package leetcode.leetcode75.arrayandstring;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/?envType=study-plan-v2&envId=leetcode-75
public class KidsWithTheGreatestNumberOfCandies {
    /*
    Example 1:

    Input: candies = [2,3,5,1,3], extraCandies = 3
    Output: [true,true,true,false,true]
    Explanation: If you give all extraCandies to:
    - Kid 1, they will have 2 + 3 = 5 candies, which is the greatest among the kids.
    - Kid 2, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
    - Kid 3, they will have 5 + 3 = 8 candies, which is the greatest among the kids.
    - Kid 4, they will have 1 + 3 = 4 candies, which is not the greatest among the kids.
    - Kid 5, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
     */
    public static void main(String[] args) {
        int[] candies = {2, 3, 5, 1, 3};
        int extraCandies = 3;
        System.out.println(kidsWithCandies(candies, extraCandies));
    }

    /*
    Time: O(n) since we iterate through the array twice.
    Space: O(n) since we store the result in a list of length n and returned as result.
     */
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;
        //Get the max candy stored from the array obtained by a kid.
        for (int candie : candies) {
            maxCandies = Math.max(maxCandies, candie);
        }
        List<Boolean> result = new ArrayList<>();
        for (int candy : candies) {
            //Check if with the extra candies, the kid has equal or more candies amongst all kids.
            if (candy + extraCandies >= maxCandies) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

}
