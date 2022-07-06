package class13;

import java.util.*;

/**
 * 对于一个二叉树，给出两个节点，判断两个节点的最低公共节点
 */
public class Code03_lowestAncestor_Study {
    public static class Node{
        public Integer value;
        public Node left;
        public Node right;
        public Node(Integer value) {
            this.value = value;
        }
    }

    /***
     * 遍历每个节点，将每个节点都和其父节点关联起来，利用set求公共节点
     * @param head
     * @param o1
     * @param o2
     * @return
     */
    public static Node lowestAncestor1(Node head,Node o1,Node o2) {
        if (head == null) {
            return null;
        }
        HashMap<Node,Node> parent = new HashMap<>();
        parent.put(head,null);
        fillParent(head,parent);
        Set<Node> o1Set = new HashSet<>();
        o1Set.add(o1);
        Node cur = o1;
        while (parent.get(cur) != null) {
            cur = parent.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parent.get(cur);
        }
        return cur;
    }

    public static void fillParent(Node head,HashMap<Node,Node> parentList) {
        if (head.left != null) {
            parentList.put(head.left,head);
            fillParent(head.left,parentList);
        }
        if (head.right != null) {
            parentList.put(head.right,head);
            fillParent(head.right,parentList);
        }
    }

    /*
    * 递归需要的信息，节点的子树中是否存在节点a,节点b，同时是否存在公共点
    * */
    public static class Info {
        public boolean findA;
        public boolean findB;
        public Node ans;
        public Info(boolean findA,boolean findB,Node ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    public static Node lowestAncestor2(Node head,Node a ,Node b) {
        return process(head,a,b).ans;
    }

    public static Info process(Node head,Node a,Node b) {
        if (head == null) {
            return new Info(false,false,null);
        }
        Info leftInfo = process(head.left,a,b);
        Info rightInfo = process(head.right,a,b);
        boolean findA = head == a || leftInfo.findA|| rightInfo.findA;
        boolean findB = head == b || leftInfo.findB || rightInfo.findB;

        Node ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        }else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        }else {
            if (findA && findB) {
                ans = head;
            }
        }
        return new Info(findA,findB,ans);
    }

    public static Node generateRandomBST(int maxValue,int maxLevel) {
        return generate(1,maxValue,maxLevel);
    }

    public static Node generate(int curLevel,int maxValue,int maxLevel) {
        if (curLevel > maxLevel || Math.random() < 0.2) {
            return null;
        }
        Node head = new Node((int)(Math.random() * maxValue + 1));
        head.left = generate(curLevel + 1,maxValue,maxLevel);
        head.right = generate(curLevel + 1,maxValue,maxLevel);
        return head;
    }

    public static Node randomPickNode(Node head) {
        if (head == null) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        fillPreList(head,list);
        int randomIndex = (int)(Math.random() * list.size());
        return list.get(randomIndex);
    }

    public static void fillPreList(Node head,List<Node> list) {
        if (head == null) {
            return;
        }
        list.add(head);
        fillPreList(head.left,list);
        fillPreList(head.right,list);
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int maxValue = 10;
        int maxLevel = 4;
        for (int i = 0;i < testTime;i++) {
            Node head = generateRandomBST(maxValue,maxLevel);
            Node o1 = randomPickNode(head);
            Node o2 = randomPickNode(head);
            if (lowestAncestor1(head,o1,o2) != lowestAncestor2(head,o1,o2)) {
                System.out.println("oop");
            }
        }
        System.out.println("finsh");
    }
}
