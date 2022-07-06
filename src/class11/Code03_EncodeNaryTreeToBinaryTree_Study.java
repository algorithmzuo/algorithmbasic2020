package class11;

import java.util.*;

public class Code03_EncodeNaryTreeToBinaryTree_Study {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };


    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    /**
     * 将n叉树转为二叉树思路：
     * 把节点X的子节点放在x节点的左子树的右边界
     * @param root
     * @return
     */
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root == null){
            return null;
        }
        TreeNode treeNode = new TreeNode(root.val);
        treeNode.left = en(root.children);
        return treeNode;
    }
    public TreeNode en(List<Node> subList){
//        if (subList == null || subList.size() == 0) {
//            return null;
//        }
        TreeNode head = null;
        TreeNode cur = null;
        for (Node node : subList){
            TreeNode newNode = new TreeNode(node.val);
            if (head == null){
                head = newNode;
            }else {
                cur.right = newNode;
            }
            cur = newNode;
            cur.left = en(node.children);
        }
        return head;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null){
            return null;
        }
        return new Node(root.val,de(root.left));
    }

    /**
     * de方法是需要把自己的兄弟集合起来返回给自己的父节点
     * @param root
     * @return
     */
    public List<Node> de(TreeNode root){
        List<Node> list = new ArrayList<>();
        while(root != null) {
            // 深度优先遍历，先处理完自己的孩子，在把自己和兄弟汇合
            Node cur = new Node(root.val,de(root.left));
            // 和兄弟汇合
            list.add(cur);
            root = root.right;
        }
        return list;
    }



    public TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

}
