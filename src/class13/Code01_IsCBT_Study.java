package class13;

import com.alibaba.fastjson2.JSON;
import jdk.nashorn.internal.parser.JSONParser;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 判断是否是完全二叉树套路
 * 1.当左子树为满二叉树，右子树为满二叉树，左高度=右高度
 * 2.当左子树为满二叉树，右子树为满二叉树，左高度=右高度+1
 * 3.当左子树为完全二叉树，右子树满二叉树，左高度=右高度+1
 * 4.当左子树为满二叉树，右子树为完全二叉树，左高度=右高度
 */
public class Code01_IsCBT_Study {
    public static class Node{
        public Integer value;
        public Node left;
        public Node right;
        public Node(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(head);
        Node left = null;
        Node right = null;
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            left = node.left;
            right = node.right;
            // 当该节点不是叶子结点，那么后面的所有节点都必须要是叶子节点
            // 当节点有右无左，则必定不是完全二叉树
            if ((leaf && (left != null || right != null)) || (left == null && right != null)) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            if (left == null || right == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static class Info{
        public boolean isFull;
        public boolean isBCT;
        public int height;
        public Info(boolean isFull,boolean isBCT,int height){
            this.isBCT = isBCT;
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static boolean isCBT2(Node head){
        return process(head).isBCT;

    }

    public static Info process(Node head) {
        if (head == null ){
            return new Info(true,true,0);
        }
        Info lInfo = process(head.left);
        Info rInfo = process(head.right);
        int height = Math.max(lInfo.height,rInfo.height) + 1;
        boolean isFull = lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height;
        boolean isBCT = false;
        // 是否为完全二叉树，这里需要分三种情况
        // 1.左边满二叉树，右边满二叉树，左高度等于右高度
        // 2.左边满二叉树，右边满二叉树，左高度等于右高度+1
        // 3.左边满二叉树，右边完全二叉树，左高度等于右高度
        // 4.左边完全二叉树，右边满二叉树，左高度等于右高度+1
        if (lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height) {
            isBCT = true;
        }
        if (lInfo.isFull && rInfo.isFull && lInfo.height == (rInfo.height + 1) ) {
            isBCT = true;
        }
        if (lInfo.isFull && rInfo.isBCT && lInfo.height == rInfo.height) {
            isBCT = true;
        }
        if (lInfo.isBCT && rInfo.isFull && lInfo.height == (rInfo.height + 1)) {
            isBCT = true;
        }
        return new Info(isFull,isBCT,height);
    }

    public static Node generateBCT(int maxLevel,int maxValue) {
        return generate(1,maxLevel,maxValue);
    }
    public static Node generate(int curLevel,int maxLevel,int maxValue) {
        if (curLevel > maxLevel || Math.random() < 0.2) {
            return null;
        }
        Node head = new Node((int)(Math.random() * maxValue + 1));
        head.left = generate(curLevel + 1,maxLevel,maxValue);
        head.right = generate(curLevel + 1,maxLevel,maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 20;
        int testTime = 100;
        for (int i = 0; i < testTime;i++) {
            Node head = generateBCT(maxLevel,maxValue);
            boolean r1 = isCBT1(head);
            boolean r2 = isCBT2(head);
            if (r1 !=r2) {
                System.out.println("oop"+" r1:"+r1+" r2:"+r2);
                System.out.println(JSON.toJSON(head));
            }
        }
    }

}
