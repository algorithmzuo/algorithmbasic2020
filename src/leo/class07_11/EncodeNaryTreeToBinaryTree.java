package leo.class07_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 * @ClassName EncodeNaryTreeToBinaryTree
 * @DATE 2020/12/9 5:30 下午
 * @Description
 * 本题测试链接：https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
 * 将多个子节点树序列化和反序列化为二叉树
 */
public class EncodeNaryTreeToBinaryTree {

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Codec {
        // Encodes an n-ary tree to a binary tree.
        public static TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            head.left = en(root.children);
            return head;
        }

        private static TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode tNode = new TreeNode(child.val);
                if (head == null) {
                    head = tNode;
                } else {
                    cur.right = tNode;
                }
                cur = tNode;
                cur.left = en(child.children);
            }
            return head;
        }



        // Decodes your binary tree to an n-ary tree.
        public static Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        public static List<Node> de(TreeNode root) {
            List<Node> children = new ArrayList<>();
            while (root != null) {
                Node cur = new Node(root.val, de(root.left));
                children.add(cur);
                root = root.right;
            }
            return children;
        }

    }

    static  class Codec1 {

        public static  TreeNode encode(Node root){
            if (root == null) {
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            head.left = encode(root.children);
            return head;
        }

        private static  TreeNode encode(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode tNode = new TreeNode(child.val);
                if (head == null) {
                    head = tNode;
                }else {
                    cur.right = tNode;
                }
                cur = tNode;
                cur.left = encode(child.children);
            }
            return head;
        }

        public static  Node decode(TreeNode root){
            if (root == null) {
                return null;
            }
            Node head = new Node(root.val);
            head.children = de(root.left);
            return head;
        }

        private static  List<Node> de (TreeNode root) {
            List<Node> children = new ArrayList<>();
            while (root != null) {
                Node node = new Node(root.val, de(root.left));
                children.add(node);
                root = root.right;
            }
            return children;

        }

    }

    static class Codec2{
        public static TreeNode encode(Node root){
            if (root == null) {
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            head.left = encode(root.children);
            return head;
        }

        private static TreeNode encode(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode node = new TreeNode(child.val);
                if (head == null) {
                    head = node;
                }else {
                    cur.right = node;
                }
                cur = node;
                cur.left = encode(child.children);
            }
            return head;
        }

        public static Node decode(TreeNode root){
            if (root == null) {
                return null;
            }
            Node head = new Node(root.val);
            head.children = de(root.left);
            return head;
        }
        public static List<Node> de(TreeNode root){
            if (root == null) {
                return null;
            }
            List<Node> children = new ArrayList<>();

            while (root != null) {
                Node node = new Node(root.val, de(root.left));
                children.add(node);
                root = root.right;
            }
            return children;

        }
    }

    static class EncodeNaryTreeToBinaryTree_Main {

        public static void main(String[] args){
            int maxLevel = 10;
            int range = 50;
            int test = 100000;
            System.out.println("start");
            for (int i = 0; i < test; i++) {
                TreeNode node = generateRandomNode(maxLevel, range);
                Node decodeOrigin = Codec1.decode(node);
                TreeNode encodeOrigin = Codec1.encode(decodeOrigin);
                Node decode = Codec1.decode(encodeOrigin);
                TreeNode treeNode = Codec1.encode(decode);
                if (!isEqualsNode(treeNode, encodeOrigin)) {
                    System.out.println("fuck!");
                }

            }
            System.out.println("end");
        }


        public static TreeNode generateRandomNode(int maxLevel, int range) {
            return generate(1, maxLevel, range);
        }

        public static TreeNode generate(int curLevel, int maxLevel, int range) {
            if (curLevel > maxLevel || Math.random() < 0.5) {
                return null;
            }
            TreeNode head = new TreeNode((int) (range * Math.random() ));
            head.left = generate(curLevel + 1, maxLevel, range);
            head.right = generate(curLevel + 1, maxLevel, range);
            return head;
        }

        public static boolean isEqualsNode(TreeNode head1, TreeNode head2) {
            if (head1 != null && head2 == null) {
                return false;
            }
            if (head1 == null && head2 != null) {
                return false;
            }
            if (head1 == null && head2 == null) {
                return true;
            }
            if (head1.val != head2.val) {
                return false;
            }
            return isEqualsNode(head1.left, head2.left) && isEqualsNode(head1.right, head2.right);
        }
    }
}

