package leetcode.array;
//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2963/
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxAreaBrutForce(height));
        System.out.println(maxAreaTwoPointers(height));
    }

    //Approach 2: Two pointers
    /*
    Time complexity: O(n)
    Space complexity: 0(1)
     */
    static int maxAreaTwoPointers(int[] height) {
        int maxArea = 0; //our result
        //Two pointers
        int left = 0; //Pointer 1
        int right = height.length - 1; //Pointer 2
        while(left < right) {
            int width = right - left; //calcul width (largeur)
            maxArea = Math.max(maxArea,
                    Math.min(height[left], height[right])  //Get the min height
                            * width); //multiply by the width (largeur)
            if(height[left] <= height[right]) {
                left++; //If left is smaller or equal move left forward
            } else {
                right--; //If right is smaller, move right backward
            }
        }
        return maxArea;
    }



    //Approach 1: Brut force
    /*
    Time complexity: O(n²)
    Space complexity: O(1)
     */
    static int maxAreaBrutForce(int[] height) {
        int maxArea = 0;
        for(int left = 0; left < height.length; left++) {
            for(int right = left + 1; right < height.length; right++) {
                int width = right - left;
                maxArea =
                        Math.max(maxArea,
                                Math.min(height[left],height[right]) * width);
            }
        }
        return maxArea;
    }


}
