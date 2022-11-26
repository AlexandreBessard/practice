package leetcode.matrix;

//https://leetcode.com/problems/image-overlap/
public class ImageOverlap {
    /*
    You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.

    We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.

    Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.

    Return the largest possible overlap.
     */
    public static void main(String[] args) {
        int[][] img1 = {
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        int[][] img2 = {
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 1}
        };
        System.out.println(largestOverlap(img1, img2));
    }
    //TODO: must do
    public static int largestOverlap(int[][] img1, int[][] img2) {

        return 0;
    }

}
