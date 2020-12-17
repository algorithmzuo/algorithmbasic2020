package leo.class09_13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Leo
 * @ClassName MaxSubBSTHead
 * @DATE 2020/12/15 10:33 下午
 * @Description
 * 给定一棵二叉树的头节点head，
 * 返回这颗二叉树中最大的二叉搜索子树的头节点
 */
public class MaxSubBSTHead {
    static class Code {
        public static Node maxSubBSTHead(Node head) {
            if (head == null) {
                return null;
            }
            return process(head).maxSubSizeHead;
        }

        public static Info process(Node node){
            if (node == null) {
                return null;
            }
            Info left = process(node.left);
            Info right = process(node.right);
            Node maxSubBSTHead = null;
            int maxSubSize = 0;
            int max = node.value;
            int min = node.value;
            if (left != null) {
                max = Math.max(max, left.max);
                min = Math.min(min, left.min);
                maxSubSize = left.maxSubSize;
                maxSubBSTHead = left.maxSubSizeHead;
            }
            if (right != null) {
                max = Math.max(max, right.max);
                min = Math.min(min, right.min);
                if (right.maxSubSize > maxSubSize) {
                    maxSubSize = right.maxSubSize;
                    maxSubBSTHead = right.maxSubSizeHead;
                }

            }
            if (
                    (left == null ? true : left.max < node.value) &&
                    (right == null ? true : right.min > node.value) &&
                    (left == null ? true : left.maxSubSizeHead == node.left) &&
                    (right == null ? true : right.maxSubSizeHead == node.right)
            ) {

                maxSubSize = (left == null ? 0 : left.maxSubSize) + (right == null ? 0 : right.maxSubSize) + 1;
                maxSubBSTHead = node;
            }
            return new Info(maxSubSize, maxSubBSTHead, max, min);
        }

        static class Info{
            int maxSubSize;
            Node maxSubSizeHead;
            int max ;
            int min;

            public Info(int maxSubSize, Node maxSubSizeHead, int max, int min) {
                this.maxSubSize = maxSubSize;
                this.maxSubSizeHead = maxSubSizeHead;
                this.max = max;
                this.min = min;
            }
        }
    }

    static class Code1 {

        static Node maxSubBSTHead(Node head) {
            if (head == null) {
                return null;
            }
            return process(head).maxSubHead;
        }

        public static Info process(Node node) {
            if (node == null) {
                return null;
            }
            Info left = process(node.left);
            Info right = process(node.right);
            Node maxSubHead = null;
            int maxSubSize = 0;
            int max = node.value;
            int min = node.value;
            if (left != null) {
                max = Math.max(max, left.max);
                min = Math.min(min, left.min);
                maxSubSize = left.maxSubSize;
                maxSubHead = left.maxSubHead;
            }
            if (right != null) {
                max = Math.max(max, right.max);
                min = Math.min(min, right.min);
                if (right.maxSubSize>maxSubSize){
                    maxSubHead = right.maxSubHead;
                    maxSubSize = right.maxSubSize;
                }
            }
            if ((left == null ? true : left.max < node.value) &&
                    (right == null ? true : right.min > node.value) &&
                    (left == null ? true : left.maxSubHead == node.left) &&
                    (right == null ? true : right.maxSubHead == node.right)
            ) {
                maxSubHead  = node;
                maxSubSize = (left == null ? 0 : left.maxSubSize) + (right == null ? 0 : right.maxSubSize) + 1;
            }
            Map<Character,String> set = new HashMap<Character,String>();
            return new Info(maxSubHead, maxSubSize, max, min);

        }
        static class Info {
            Node maxSubHead;
            int maxSubSize;
            int max ;
            int min;

            public Info(Node maxSubHead, int maxSubSize, int max, int min) {
                this.maxSubHead = maxSubHead;
                this.maxSubSize = maxSubSize;
                this.max = max;
                this.min = min;
            }
        }
    }

    static class Logarithm {
        public static int getBSTSize(Node head) {
            if (head == null) {
                return 0;
            }
            ArrayList<Node> arr = new ArrayList<>();
            in(head, arr);
            for (int i = 1; i < arr.size(); i++) {
                if (arr.get(i).value <= arr.get(i - 1).value) {
                    return 0;
                }
            }
            return arr.size();
        }

        public static void in(Node head, ArrayList<Node> arr) {
            if (head == null) {
                return;
            }
            in(head.left, arr);
            arr.add(head);
            in(head.right, arr);
        }

        public static Node maxSubBSTHead(Node head) {
            if (head == null) {
                return null;
            }
            if (getBSTSize(head) != 0) {
                return head;
            }
            Node leftAns = maxSubBSTHead(head.left);
            Node rightAns = maxSubBSTHead(head.right);
            return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
        }
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (Code1.maxSubBSTHead(head) != Logarithm.maxSubBSTHead(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
