package leo.class08_12;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 * @ClassName MaxSubBSTSize
 * @DATE 2020/12/11 7:17 下午
 * @Description 在二叉树中,返回满足搜索二叉树条件的最多的节点个数
 * Binary Search Tree
 * 1.Left subtree is search binary tree
 * 2.right subtree is search binary tree
 * 3.max value of left subtree less than the value of head
 * 4.min value of right subtree greater than the value of head
 *
 *
 * 1.顶级节点不做头
 *  1.1 左树中最大搜索二叉树的节点个数
 *  1.2 右树中最大搜索二叉树的节点个数
 * 2.顶级节点做头
 *  2.1 左树是BST
 *  2.3 右树是BST
 *  2.4 左size+右size+1
 */
public class MaxSubBSTSize {

    static class Code{
        public static int maxSubBSTSize(Node head) {
            if (head == null) {
                return 0;
            }
            return process(head).maxSubBSTCount;
        }

        public static Info process(Node node){
            if (node == null) {
                return null;
            }
            Info left = process(node.left);
            Info right = process(node.right);
            boolean isBST = false;
            int maxSubBSTSize = 0;
            int max = node.value;
            int min = node.value;
            if (left != null) {
                max = Math.max(max, left.max);
                min = Math.min(min, left.min);
                maxSubBSTSize = left.maxSubBSTCount;
            }
            if (right != null) {
                max = Math.max(max, right.max);
                min = Math.min(min, right.min);
                maxSubBSTSize = Math.max(maxSubBSTSize, right.maxSubBSTCount);
            }
            if (
                    (left == null ? true : left.isAllBST)
                    && (right == null ? true : right.isAllBST)
                    && (left == null ? true : left.max < node.value)
                    && (right == null ? true : right.min > node.value)
            ) {
                isBST = true;
                maxSubBSTSize = (left == null ? 0 : left.maxSubBSTCount)
                        + (right == null ? 0 : right.maxSubBSTCount) + 1;
            }
            return new Info(isBST, maxSubBSTSize, max, min);
        }

        static class Info{
            boolean isAllBST;
            int maxSubBSTCount;
            int max;
            int min;

            public Info(boolean isAllBST, int maxSubBSTCount, int max, int min) {
                this.isAllBST = isAllBST;
                this.maxSubBSTCount = maxSubBSTCount;
                this.max = max;
                this.min = min;
            }
        }

    }

    static class Code1{
        public static int maxSubBSTSize(Node head) {
            if (head == null) {
                return 0;
            }
            return process(head).maxSubBSTSize;
        }
        static Info process(Node node){
            if (node == null) {
                return null;
            }
            Info left = process(node.left);
            Info right = process(node.right);
            boolean isBST = false;
            int max = node.value;
            int min = node.value;
            int maxSubBSTSize = 0;
            if (left != null) {
                max = Math.max(max, left.max);
                min = Math.min(min, left.min);
                maxSubBSTSize =  left.maxSubBSTSize;
            }
            if (right != null) {
                max = Math.max(max, right.max);
                min = Math.min(min, right.min);
                maxSubBSTSize = Math.max(maxSubBSTSize, right.maxSubBSTSize);
            }
            if (
                (left == null ? true : left.isBST) &&
                (right==null ? true : right.isBST) &&
                (left==null ? true : left.max < node.value)&&
                (right==null? true : right.min > node.value)
            ){
                isBST = true;
                maxSubBSTSize = (left == null ? 0 : left.maxSubBSTSize) +
                        (right == null ? 0 : right.maxSubBSTSize) + 1;
            }
            return new Info(isBST, maxSubBSTSize, max, min);

        }
        static class Info{
            boolean isBST;
            int maxSubBSTSize;
            int max;
            int min;
            public Info(boolean isBST, int maxSubBSTSize, int max, int min) {
                this.isBST = isBST;
                this.maxSubBSTSize = maxSubBSTSize;
                this.max = max;
                this.min = min;
            }


        }
    }
    static class Logarithm{
        public static int maxSubBSTSize(Node head){
            if (head == null) {
                return 0;
            }
            int size = getMaxSize(head);
            if (size != 0) {
                return size;
            }
            return Math.max(maxSubBSTSize(head.left), maxSubBSTSize(head.right));
        }

        public static int getMaxSize(Node node) {
            if (node == null) {
                return 0;
            }
            List<Node> list = new ArrayList<>();
            in(node,list);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).value <= list.get(i - 1).value) {
                    return 0;
                }
            }
            return list.size();
        }

        public static void in(Node node, List<Node> list) {

            if (node == null) {
                return;
            }
            in(node.left, list);
            list.add(node);
            in(node.right, list);

        }
    }


    public static void main(String[] args){
        int level = 4;
        int range = 100;
        int test = 100000;
        System.out.println("Start");
        for (int i = 0; i < test; i++) {
            Node head = generateRandomNode(level, range);
            int i1 = Code1.maxSubBSTSize(head);
            int i2 = Logarithm.maxSubBSTSize(head);
            if (i1 != i2) {
                System.out.println("fuck!!!!");
                break;
            }
        }
        System.out.println("End");

    }

    public static Node generateRandomNode(int n, int r) {
        return generateNode(1, n, r);
    }

    public static Node generateNode(int i, int n, int r) {
        if (i > n || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (r * Math.random()));
        head.left = generateNode(i + 1, n, r);
        head.right = generateNode(i + 1, n, r);
        return head;
    }
}
