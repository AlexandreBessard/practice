package patternsForCodingInterviews.multiThreaded;

import java.util.Stack;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62ba9052bfabbUnit
public class BinarySearchTreeIterator {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(14);
        root.right.right = new TreeNode(19);
        root.right.right.right = new TreeNode(20);

        BSTIteratorMultiThreaded itr = new BSTIteratorMultiThreaded(root);
        System.out.println("hasNext() -> " + itr.hasNext());
        System.out.println("next() -> " + itr.next());
        System.out.println("next() -> " + itr.next());
        System.out.println("hasNext() -> " + itr.hasNext());
        System.out.println("next() -> " + itr.next());
        System.out.println("next() -> " + itr.next());
        System.out.println("next() -> " + itr.next());
        System.out.println("next() -> " + itr.next());
        System.out.println("next() -> " + itr.next());
        System.out.println("hasNext() -> " + itr.hasNext());
    }


    static class BSTIteratorMultiThreaded {

        private Stack<TreeNode> stack = new Stack<>();
        private Thread t1 = null;

        public BSTIteratorMultiThreaded(TreeNode root) {
            traverseLeft(root);
        }

        synchronized public boolean hasNext() {
            checkThread();
            return !stack.isEmpty();
        }

        synchronized public int next() {
            checkThread();
            TreeNode tmpNode = stack.pop();
            traverseLeft(tmpNode.right); //Different thread
            return tmpNode.val; //required node available right away
        }

        //If the previous thread is active, wait before it finishes
        private void checkThread() {
            if (t1 != null && t1.isAlive()) {
                try {
                    t1.join(); //Wait for this thread to die
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }

        private void traverseLeft(TreeNode node) {
            //spawn new thread to process the left-subtree
            t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    TreeNode tmp = node;
                    while (tmp != null) {
                        stack.push(tmp);
                        tmp = tmp.left;
                    }
                }
            });
            t1.start();
        }
    }


    static class BSTIterator {
        //We can not stop recursion in the middle to return the required element.
        //To transform a recursive solution to make it iterative, we use a STACK.
        //We can save the state of the in-order traversal of the BST in the stack.
        Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            traverseleft(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        //Return the smallest number
        //Thread safe, only one thread is allowed to process next() at any time
        synchronized public int next() {
            TreeNode tmpNode = stack.pop();
            traverseleft(tmpNode.right);
            return tmpNode.val;
        }

        //traverse the left subtree to push all the node on the stack
        //(Inorder traversal using stack)
        private void traverseleft(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }


}
