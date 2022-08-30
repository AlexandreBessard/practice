package patternsForCodingInterviews.multiThreaded;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62bcdae955936Unit
public class SameTree {

    public static void main(String[] args) {
        TreeNode p = new TreeNode(10);
        p.left = new TreeNode(4);
        p.left.left = new TreeNode(1);
        p.right = new TreeNode(15);
        p.right.left = new TreeNode(14);

        TreeNode q = new TreeNode(10);
        q.left = new TreeNode(4);
        q.left.left = new TreeNode(1);
        q.right = new TreeNode(15);
        q.right.left = new TreeNode(14);

        SameTree sameTree = new SameTree();
        System.out.println(sameTree.isSameTree(p, q));

        q.right.right = new TreeNode(20);
        System.out.println(sameTree.isSameTree(p, q));

        q.right.right = new TreeNode(20);
        p.left.val = 9;
        System.out.println(sameTree.isSameTree(p, q));
    }


    /*
    MULTITHREADED
     */
    //Declaring a variable volatile thus
    // guarantees the visibility for other threads of writes to that variable.
    private volatile boolean isSame; //update the value concurrently

    public boolean isSameTree(TreeNode p, TreeNode q) {
        isSame = true;
        int numThreads = Runtime.getRuntime().availableProcessors();
        return isSameTreeMultiThreaded(p, q, numThreads);
    }

    private boolean isSameTreeMultiThreaded(TreeNode p, TreeNode q, int numThreads) {
        if (p == null && q == null)
            return true;
        if (q == null || p == null)
            return false;
        if (p.val != q.val)
            return false;
        // if we can start more threads, we will spawn a new thread to check the
        // right subtree, otherwise we will do everything in the current thread
        if(numThreads > 0) {
            // spawn a separate thread for checking the right sub-tree
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    isSame &= isSameTreeMultiThreaded(p.right, q.right, numThreads/2);
                }
            });
            t1.start();
            // check the left sub-tree in the current thread
            isSame &= isSameTreeMultiThreaded(p.left, q.left, numThreads/2);
            try {
                t1.join(); //Wait for the thread checking the right subtree
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        } else { //do everything in the current thread
            isSame &= isSameTreeMultiThreaded(p.right, q.right, 0)
                    && isSameTreeMultiThreaded(p.left, q.left, 0);
        }
        return isSame;
    }

    /*
    Non concurrency
    Time: O(min(M, N) where M and N are the number of nodes in the given trees respectively.
    Taking minimum M and N since as soon as we see difference, we do not check the remaining trees.
    Space: O(N) for the recusion stack
     */
    public static boolean isSameTreeNonConcurrency(TreeNode p, TreeNode q) {
        // p and q are both null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        // one of p and q has different value
        if (p.val != q.val) return false;

        // check left and right subtree recursively
        return isSameTreeNonConcurrency(p.right, q.right) &&
                isSameTreeNonConcurrency(p.left, q.left);
    }

}
