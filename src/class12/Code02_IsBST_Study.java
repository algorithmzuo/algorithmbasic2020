package class12;

import util.PrintTree;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Code02_IsBST_Study {
    static class Node{
        private int value;
        private Node l;
        private Node r;
        public Node(int _value){
            this.value = _value;
        }
    }

    public static boolean isBST2(Node node){
        if (node == null) {
            return true;
        }
        List<Node> list = new ArrayList<>();
        in(node,list);
        for (int i = 1;i < list.size();i++){
            if ((list.get(i).value - list.get(i -1).value) <= 0){
                return false;
            }
        }
        return true;
    }

    public static void in(Node node,List<Node> list){
        if (node == null){
            return;
        }
        in(node.l,list);
        list.add(node);
        in(node.r,list);
    }

   static class Info{
        private boolean isBst;
        private int min;
        private int max;
        public Info(boolean isBst,int min,int max){
            this.isBst = isBst;
            this.max = max;
            this.min = min;
        };
    }

    private static boolean isBst(Node node){
        if (node == null){
            return true;
        }
        return process(node).isBst;
    }

    private static  Info process(Node node){
        if (node == null){
            return null;
        }
        Info lInfo = process(node.l);
        Info rInfo = process(node.r);
        int max = node.value;
        if (lInfo != null ){
            max = Math.max(max,lInfo.max);
        }
        if (rInfo != null){
            max = Math.max(max,rInfo.max);
        }
        int min = node.value;
        if (lInfo != null) {
            min = Math.min(min,lInfo.min);
        }
        if (rInfo != null){
            min = Math.min(min,rInfo.min);
        }
        Boolean isBST = true;
        if (lInfo != null && !lInfo.isBst){
            isBST = false;
        }
        if (rInfo != null && !rInfo.isBst){
            isBST = false;
        }
        if (lInfo != null && lInfo.max >= node.value) {
            isBST = false;
        }
        if (rInfo != null && rInfo.min <= node.value){
            isBST = false;
        }
        return new Info(isBST,min,max);
    }

    private static Node generateRandomBST(int maxValue,int maxLevvel){
        return generate(1,maxValue,maxLevvel);
    }
    private static Node generate(int curLevel,int maxValue,int maxLevel){
        if (curLevel > maxLevel || Math.random() < 0.3) {
            return null;
        }
        Node root = new Node((int)(Math.random() * maxValue + 1));
        root.l = generate(curLevel + 1,maxLevel,maxValue);
        root.r= generate(curLevel + 1,maxLevel,maxValue);
        return root;
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
        printInOrder(head.r, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.l, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int maxValue = 100;
        int maxLevel = 4;
        for (int i = 0; i < testTime;i++){
            Node root = generateRandomBST(maxValue,maxLevel);
            boolean res1 = isBst(root);
            boolean res2 = isBST2(root);
            if (res1 != res2){
                isBst(root);
                isBST2(root);
                printTree(root);
                System.out.println("res1:"+res1);
                System.out.println("res2"+res2);
                System.out.println("oop");
            }
        }
        System.out.println("success");
    }

}
