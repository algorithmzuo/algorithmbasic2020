package class12;

import class07.Heap;
import util.PrintTree;
import util.TreeNode;

import java.util.LinkedList;

/**
 * 判断是否是完全二叉树
 * 1.如果节点有右子树，无左子树，那么必定不是完全二叉树
 * 2.如果节点有一个左右不双全的，那么后面的节点必都是叶子结点
 */
public class Code01_IsCBT_Study {
    public static class Node{
        public Integer value;
        private Node left;
        private Node right;
        public Node(Integer _value){
            value = _value;
        }
    }

    public static boolean isCBT(Node root){
        if (root == null){
            return true;
        }
        // 按层遍历使用双端队列
        LinkedList<Node> linkedList = new LinkedList();
        // 判断是否有遇到过左右不双全的节点
        boolean leaf = false;
        linkedList.add(root);
        while (!linkedList.isEmpty()){
            Node node = linkedList.poll();
            Node left = node.left;
            Node right = node.right;
            if ((left == null && right != null) || (leaf && (right != null || left != null))){
                return false;
            }
            if (left != null) {
                linkedList.add(left);
            }
            if (right != null){
                linkedList.add(right);
            }
            // 如果该节点是叶子节点，那么leaf为true
            if (left == null || right == null){
                leaf = true;
            }
        }
        return true;
    }

    public static Node generateRandomBST(int maxValue,int maxLevel){
        return generate(1,maxValue,maxLevel);
    }
    public static Node generate(int curLevel,int maxValue,int maxLevel){
        if (curLevel > maxLevel || Math.random() < 0.2){
            return null;
        }
        Node head = new Node((int)(Math.random() * maxValue +1)) ;
        head.left = generate(curLevel + 1,maxValue,maxLevel);
        head.right = generate(curLevel + 1,maxValue,maxLevel);
        return head;
    }

    public static void main(String[] args) {
        int testTime = 1;
        int maxValue = 100;
        int maxLevel = 5;
        for (int i = 0; i< testTime;i++){
            Node head = generateRandomBST(maxValue,maxLevel);
            printTree(head);

            System.out.println(isCBT(head));
        }
    }


    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

}
