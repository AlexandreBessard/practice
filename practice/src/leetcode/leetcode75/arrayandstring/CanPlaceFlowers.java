package leetcode.leetcode75.arrayandstring;

//https://leetcode.com/problems/can-place-flowers/?envType=study-plan-v2&envId=leetcode-75
public class CanPlaceFlowers {

    /*
    You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
    Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n,
    return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
     */
    public static void main(String[] args) {
        // 0 means empty, 1 means not empty
        int[] input1 = new int[]{1, 0, 0, 0, 1};
        System.out.println(canPlaceFlowers(input1, 1));
        System.out.println(canPlaceFlowers(input1, 2));
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            //Check the current element
            if (flowerbed[i] == 0
                    &&
                    //Check the beginning of the array, avoid indexOutOfBoundException
                    (i == 0 || flowerbed[i - 1] == 0)
                    &&                           //Check the element next to it
                    (i == flowerbed.length - 1 || flowerbed[i + 1] == 0))
            {
                flowerbed[i] = 1;
                n--;
                if (n == 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
