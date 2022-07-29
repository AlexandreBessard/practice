package topInterviewQuestion.medium.design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/112/design/812/
public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        one.left = new TreeNode(2);
        one.right = new TreeNode(3);
        one.right.left = new TreeNode(4);
        one.right.right = new TreeNode(5);
        var obj = new Codec();
        String res = obj.serialize(one);
        TreeNode res2 = obj.deserialize(res);
        String res3 = obj.serializeBFS(one);
        TreeNode res4 = obj.deserializeBFS(res3);
    }

    //DSF because the linkage amoung the adjacent nodes is
    //naturally encoded in the order. (Preorder strategy)


    static class Codec {

        /*
        BFS
         */
        public String serializeBFS(TreeNode root) {
            if(root == null)
                return "";
            Queue<TreeNode> qu = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            qu.offer(root);
            sb.append(String.valueOf(root.val));
            sb.append(' ');
            while(!qu.isEmpty()) {
                TreeNode x = qu.poll();
                if(x.left == null) {
                    sb.append("null ");
                } else {
                    qu.offer(x.left);
                    sb.append(String.valueOf(x.left.val));
                    sb.append(' ');
                }
                if(x.right == null) {
                    sb.append("null ");
                } else {
                    qu.offer(x.right);
                    sb.append(String.valueOf(x.right.val));
                    sb.append(' ');
                }
            }
            return sb.toString();
        }


        public TreeNode deserializeBFS(String data) {
            if(data.length() == 0)
                return null;
            String[] node = data.split(" ");
            Queue<TreeNode> qu = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(node[0]));
            qu.offer(root);
            int i = 1;
            while(!qu.isEmpty()) {
                //Queue<TreeNode> nextQu = new LinkedList<>();
                //while(!qu.isEmpty()) {
                    TreeNode x = qu.poll();
                    if(node[i].equals("null"))
                        x.left = null;
                    else {
                        x.left = new TreeNode(Integer.parseInt(node[i]));
                        qu.offer(x.left);
                    }
                    i++;
                    if(node[i].equals("null"))
                        x.right = null;
                    else {
                        x.right = new TreeNode(Integer.parseInt(node[i]));
                        qu.offer(x.right);
                    }
                    i++;
                //}
                //qu = nextQu;
            }
            return root;
        }


        /*
        DFS
         */
        //DECODE
        public TreeNode deserialize(String data) {
            String[] data_array = data.split(",");
            LinkedList<String> data_list = new LinkedList<>(Arrays.asList(data_array));
            return rdeserialize(data_list);
        }
        private TreeNode rdeserialize(LinkedList<String> l) {
            if(l.get(0).equals("null")){
                l.pollFirst();
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(l.pollFirst()));
            root.left = rdeserialize(l);
            root.right = rdeserialize(l);
            return root;
        }



        //ENCODE
        public String serialize(TreeNode root) {
            return rserialize(root, new StringBuilder()).toString();
        }
        private StringBuilder rserialize(TreeNode root, StringBuilder str) {
            if(root == null)
                str.append("null,");
            else {
                str.append(root.val).append(",");
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }
    }


    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) {
            val = x;
        }
    }


}
