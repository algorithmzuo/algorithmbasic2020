package leo.class07_11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author Leo
 * @ClassName TreeMaxWidth
 * @DATE 2020/12/9 7:28 下午
 * @Description 二叉树的最大宽度
 */
public class TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> map = new HashMap<>();
        map.put(head, 1);
        queue.offer(head);
        int curLevel = 1;
        int curLevelCount = 0;
        int max = 0;
        Node cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            Integer curNodeLevel = map.get(cur);
            if (cur.left != null) {
                map.put(cur.left, curNodeLevel + 1);
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                map.put(cur.right, curNodeLevel + 1);
                queue.offer(cur.right);
            }
            if (curLevel == curNodeLevel) {
                curLevelCount++;
            }else{
                max = Math.max(curLevelCount, max);
                curLevel++;
                curLevelCount = 1;
            }
        }
        max = Math.max(max, curLevelCount);
        return max;
    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        int max = 0;
        int curMax = 0;
        Node curEnd = head;
        Node nextEnd = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                nextEnd = cur.right;
            }
            curMax++;
            if (cur == curEnd) {
                curEnd = nextEnd;
                max = Math.max(max, curMax);
                curMax = 0;
            }
        }
        return max;
    }


    public static void main(String[] args){

        int maxSize = 10;
        int range = 100;
        int test = 1000;
        System.out.println("start!");

        for (int i = 0; i < test; i++) {
            Node head = generateRandomNode(maxSize, range);
            int i1 = maxWidthUseMap(head);
            int i2 = maxWidthNoMap(head);
            if (i1 != i2) {
                System.out.println("i1: " + i1 + " i2: " + i2);
                break;
            }
        }
        System.out.println("end!");

    }

    public static Node generateRandomNode(int maxSize, int range) {
        if (maxSize == 0) {
            return null;
        }
        return generateNode(1, maxSize, range);

    }

    private static Node generateNode(int i, int maxSize, int range) {
        if (i > maxSize || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (range * Math.random()));
        head.left = generateNode(i + 1, maxSize, range);
        head.right = generateNode(i + 1, maxSize, range);
        return head;
    }
}
