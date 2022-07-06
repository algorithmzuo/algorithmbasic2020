package class13;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取最大搜索二叉树的距离
 * 搜索二叉树的判断：
 * 1.左子树，右子树均为搜索二叉树
 * 2.左子树的max小于当前节点，右子树的min大于当前节点
 */
public class Code02_MaxSubBSTHead_Study {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
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

    /**
     * 获取节点的搜索二叉树大小
     * 这里中序遍历树，判断节点的value是否一直增长，若是则为搜索二叉树，若否则不是
     *
     * @param head
     */
    public static Integer getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        List<Node> list = new ArrayList<>();
        in(head, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).value <= list.get(i - 1).value) {
                return 0;
            }
        }
        return list.size();
    }

    public static void in(Node head, List<Node> list) {
        if (head == null) {
            return;
        }
        in(head.left, list);
        list.add(head);
        in(head.right, list);
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        // 遍历每个节点，如果该节点的是搜索二叉树，那么他的搜索二叉树大小就不为0
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node l = maxSubBSTHead1(head.left);
        Node r = maxSubBSTHead1(head.right);
        return getBSTSize(l) >= getBSTSize(r) ? l : r;
    }

    /*
    递归套路解决
    获取最大的搜索二叉树子树，搜索二叉树判断信息
    X作为节点加入计算
    1.左右子树的最大搜索二叉树高度
    2.左右子树的最大值，最小值
    3.左右子树最大搜索二叉树的节点
    X不作为节点加入计算
    1.左右子树的最大搜索二叉树高度 相加再加1
    2.左右子树的最大值，最小值
    3.左右子树最大搜索二叉树的节点
     */

    public static class Info {
        public Node maxSubBSTHead;
        public Integer maxSubBSTSize;
        public Integer maxValue;
        public Integer minVlaue;

        public Info(Node maxSubBSTHead, Integer maxSubBSTSize, Integer maxValue, Integer minVlaue) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBSTSize = maxSubBSTSize;
            this.maxValue = maxValue;
            this.minVlaue = minVlaue;
        }
    }

    public static Node maxSubBSTHead2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).maxSubBSTHead;
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        // 1.获取左右子树的信息
        Info l = process(x.left);
        Info r = process(x.right);
        // 2.比较左右子树的信息，然后组成当前节点的信息
        Integer min = x.value;
        Integer max = x.value;
        Integer maxSubBSTSize = 0;
        Node maxSubBSTHead = null;

        if (l != null) {
            min = Math.min(l.minVlaue, min);
            max = Math.max(l.maxValue, max);
            maxSubBSTHead = l.maxSubBSTHead;
            maxSubBSTSize = l.maxSubBSTSize;
        }
        if (r != null) {
            min = Math.min(r.minVlaue, min);
            max = Math.max(r.maxValue, max);
            if (r.maxSubBSTSize > maxSubBSTSize) {
                maxSubBSTSize = r.maxSubBSTSize;
                maxSubBSTHead = r.maxSubBSTHead;
            }
        }
        // 如果X加入判断，那么要判断该节点和左右子树组合后是否是搜索二叉树
        if ((l == null ? true : (l.maxSubBSTHead == x.left && l.maxValue < x.value)) &&
                (r == null ? true : (r.maxSubBSTHead == x.right && r.minVlaue > x.value))) {
            maxSubBSTHead = x;
            maxSubBSTSize = (l == null ? 0 : l.maxSubBSTSize) + (r == null ? 0 : r.maxSubBSTSize) + 1;
        }
        return new Info(maxSubBSTHead, maxSubBSTSize, max, min);
    }

    public static Node generateRandomBST(int maxValue, int maxLevel) {
        return generate(1, maxValue, maxLevel);
    }

    public static Node generate(int curLevel,int maxValue,int maxLevel){
        if (curLevel > maxLevel || Math.random() < 0.2) {
            return null;
        }
        Node head = new Node((int)(Math.random() * maxValue+1));
        head.left = generate(curLevel + 1,maxValue,maxLevel);
        head.right = generate(curLevel + 1,maxValue,maxLevel);
        return head;
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int maxValue = 10;
        int maxLevel = 4;
        for (int i = 0 ;i < testTime;i++){
            Node head = generateRandomBST(maxValue,maxLevel);
            Node head1 = maxSubBSTHead1(head);
            Node head2 = maxSubBSTHead2(head);
            if (head1 != head2) {
                System.out.println("oop");
                System.out.println("head:"+JSON.toJSON(head));
                System.out.println("head1:"+JSON.toJSON(head1));
                System.out.println("head2:"+JSON.toJSON(head2));
            }
        }
        System.out.println("finsh");
    }

}
