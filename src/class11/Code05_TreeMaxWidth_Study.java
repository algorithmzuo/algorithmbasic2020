package class11;

import util.PrintTree;
import util.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Code05_TreeMaxWidth_Study {
    public class Node{
        public Integer value;
        public Node left;
        public Node right;
        public Node(Integer _val){
            value = _val;
        }
    }

    /**
     * 获取二叉树醉倒宽度
     * 这里借助Map完成，思路如下：
     * 1.
     * @param head
     * @return
     */
    public Integer getMaxWidthWithMap(Node head){
        if (head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Map<Node,Integer> levelMap = new HashMap<>();
        Integer curLevel = 1;
        levelMap.put(head,1);
        int max = 0;
        int curLevelWidth = 0;
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int curNodeLevel = levelMap.get(node);
            if (node.left != null){
                queue.add(node.left);
                levelMap.put(node.left,curNodeLevel+1);
            }
            if (node.right != null){
                queue.add(node.right);
                levelMap.put(node.right,curNodeLevel+1);
            }
            // 统计当前层元素数量大小
            if (curLevel == curNodeLevel){
                curLevelWidth++;
            }else {
                max = Math.max(max,curLevelWidth);
                curLevel++;
                curLevelWidth = 1;
            }
        }
        max = Math.max(max,curLevelWidth);
        return max;
    }

    private Integer getMaxWidthNoMap(Node head){
        if (head == null ){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curNode = head;// 当前层最右节点
        Node nextNode = null;
        int curNodeNum = 0;
        int max = 0;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            curNodeNum++;
            if (node.left != null){
                queue.add(node.left);
                nextNode = node.left;
            }
            if (node.right != null){
                queue.add(node.right);
                nextNode = node.right;
            }
            if (node == curNode){
                max = Math.max(max,curNodeNum);
                curNode = nextNode;
                curNodeNum = 0;
            }
        }
        return max;
    }

    public Node generateRandomBST(int maxLevel,int maxValue){
        return generate(1,maxLevel,maxValue);
    }
    public Node generate(int level,int maxLevel,int maxValue){
        if (level > maxLevel || Math.random() < 0.2){
            return null;
        }
        Node node = new Node((int)(Math.random()*maxValue+1));
        node.left = generate(level + 1,maxLevel,maxValue);
        node.right = generate(level + 1,maxLevel,maxValue);
        return node;
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int maxValue = 100;
        int maxLevel = 3;
        Code05_TreeMaxWidth_Study maxWidthStudy = new Code05_TreeMaxWidth_Study();
        for (int i = 0 ;i < testTime;i++){
            Node node = maxWidthStudy.generateRandomBST(maxLevel,maxValue);
//            printTree(node);
            int nomap = maxWidthStudy.getMaxWidthNoMap(node);
            int map =  maxWidthStudy.getMaxWidthWithMap(node);
//            System.out.println("nomap"+nomap);
//            System.out.println("map:"+map);
            if (nomap !=map){
                System.out.println("oop!");
            }
        }
        System.out.println("finsh");
    }


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
