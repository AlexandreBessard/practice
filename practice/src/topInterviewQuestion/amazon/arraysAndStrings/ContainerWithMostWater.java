package topInterviewQuestion.amazon.arraysAndStrings;
//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2963/
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxAreaBrutForce(height));
        System.out.println(maxAreaTwoPointers(height));
    }


    //Approach 2: Two pointer
    /*
    Time complexity: O(n)
    Space complexity: 0(1)
     */
    static int maxAreaTwoPointers(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right) {
            int width = right - left;
            maxArea = Math.max(maxArea,
                    Math.min(height[left], height[right]) * width);
            if(height[left] <= height[right]) {
                left++;
            } else {
                right--;
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
