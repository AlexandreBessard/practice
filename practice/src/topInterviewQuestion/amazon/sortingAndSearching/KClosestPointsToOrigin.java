package topInterviewQuestion.amazon.sortingAndSearching;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

//https://leetcode.com/explore/interview/card/amazon/79/sorting-and-searching/2996/
public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        int[][] points = {
                {1, 3},
                {-2, 2},
                {1, 4},
                {3, 3}
        };
        int k = 2; //k closest points to the origin (0, 0)
        for(int[] el : kClosestDivideAndConquer(points, k)) {
            System.out.print("[ ");
            for(int i : el) {
                System.out.print(i + ",");
            }
            System.out.print(" ] \n");
        }
    }


    //Approach 2: Divide and Conquer
    /*
    Time: O(N)
    Space: O(N)
     */
    private static int[][] points;
    public static int[][] kClosestDivideAndConquer(int[][] points, int k) {
        KClosestPointsToOrigin.points = points;
        sort(0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }

    private static void sort(int left, int right, int K) {
        if(left >= right) {
            return;
        }
        //we choose some random element
        int k = ThreadLocalRandom.current().nextInt(left, right);
        swap(left, k); //Quickselect by some pivot.
        //QuickSelect by a random pivot
        int mid = partition(left, right);
        int leftLength = mid - left + 1;
        //Split the array in two buckets
        if(K < leftLength) {
            sort(left, mid - 1, K);
        } else if (K > leftLength) {
            sort(mid + 1, right, K - leftLength);
        }
    }

    private static int partition(int left, int right) {
        int oi = left;
        int pivot = dist(left);
        left++; //Increment because we already calculated dist(left)
        //Put elements less than pivot to the left
        while(true) {
            //Smallest element than pivot, keep on the left side
            while(left < right && dist(left) < pivot) { //Stay in place
                left++; //Move on to the next element, it is in the correct place
            }
            //
            while(left <= right && dist(right) > pivot) { //Stay in place
                right--; //Move on to the previous, the element is in the correct place
            }
            if(left >= right)
                break;
            swap(left, right);
        }
        swap(oi, right);
        return right;
    }

    private static int dist(int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    private static void swap(int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }


    //Approach 1: Sort
    /*
    Time: O(N log N)
    Space: O(N) caused by sorts algorithm
     */
    public static int[][] kClosest(int[][] points, int k) {
        int N = points.length;
        int[] dists = new int[N];
        for(int i = 0; i < N; i++) {
            dists[i] = dist(points[i]);
        }
        Arrays.sort(dists);
        int distK = dists[k - 1];
        int[][] ans = new int[k][2];
        int t = 0;
        for(int i = 0; i < N; i++) {
            if(dist(points[i]) <= distK) {
                ans[t++] = points[i];
            }
        }
        return ans;
    }
    private static int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

}
