package leetcode.matrix;
//https://leetcode.com/problems/richest-customer-wealth/
public class RichestCustomerWealth {
/*
A customer's wealth is the amount of money they have in all their bank accounts. The richest customer is the customer that has the maximum wealth.
Input: accounts = [[1,2,3],[3,2,1]]
Output: 6
Explanation:
1st customer has wealth = 1 + 2 + 3 = 6
2nd customer has wealth = 3 + 2 + 1 = 6
Both customers are considered the richest with a wealth of 6 each, so return 6.
 */
    public static void main(String[] args) {
        int[][] accounts1 = {
                {1, 2, 3}, //Customer 1 has first bank 1, second bank 2, third 3
                {3, 2, 1} //Customer 2 hass first bank 3, second bank 2.....
        };
        int[][] accounts2 = {
                {1, 5},
                {7, 3},
                {3, 5}
        };
        System.out.println(maximumWealth(accounts1));
        System.out.println(maximumWealth(accounts2));
    }

    /*
    Time: O(M * N) where M customers and N number of banks
    Space: O(1)
     */
    public static int maximumWealth(int[][] accounts) {
        int maxWealthSoFar = 0; //Our result
        //Iterate over accounts
        for(int[] account : accounts) {
            //for each account, initialize the sum to 0
            int currCustomerWealth = 0;
            //Add money in each bank
            for(int money : account) {
                currCustomerWealth += money;
            }
            //Update the max wealth seen so far
            maxWealthSoFar = Math.max(maxWealthSoFar, currCustomerWealth);
        }
        return maxWealthSoFar;
    }



}
