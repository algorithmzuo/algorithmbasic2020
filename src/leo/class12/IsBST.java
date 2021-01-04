package leo.class12;

import java.util.ArrayList;

/**
 * @author Leo
 * @ClassName IsBST
 * @DATE 2020/12/11 6:05 下午
 * @Description 判读是否是搜索二叉树
 * 每棵树的左部分比自己小, 右部分比自己大
 * 经典搜索二叉树没有重复值
 * 1.Left subtree is Binary Search Tree
 * 2.right subtree is Binary Search Tree
 * 3.max value of left subtree less than the value of head
 * 4.min value of right subtree greater than the value of head
 */
public class IsBST {

    static class Code {
        public static boolean isBST(Node head){
            if (head == null) {
                return true;
            }
            return process(head).is;
        }


        public static Info process(Node node) {
            if (node == null) {
                return null;
            }
            Info left = process(node.left);
            Info right = process(node.right);

            boolean is = true;
            int max = node.value;
            int min = node.value;
            if (left != null) {
                max = Math.max(max, left.max);
                min = Math.min(min, left.min);
            }
            if (right != null) {
                min = Math.min(min, right.min);
                max = Math.max(max, right.max);
            }
            if (left != null && !left.is) {
                is = false;
            }
            if (right != null && !right.is) {
                is = false;
            }
            if (left != null && left.max >= node.value) {
                is = false;
            }
            if (right != null && right.min <= node.value) {
                is = false;
            }
            return new Info(is, max, min);
        }

        static class Info{
            boolean is;
            int max;
            int min;
            public Info(boolean is,int max,int min){
                this.is = is;
                this.max = max;
                this.min = min;
            }
        }


    }

    static  class Code1{
        public static boolean isBST(Node head){
            if (head == null) {
                return true;
            }

            return process(head).isBST;
        }

        public static Info process(Node node){
            if (node == null) {
                return null;
            }

            Info left = process(node.left);
            Info right = process(node.right);
            boolean isBST = true;
            int max = node.value;
            int min = node.value;
            if (left != null) {
                max = Math.max(max, left.max);
                min = Math.min(min, left.min);

            }
            if (right != null) {
                max = Math.max(max, right.max);
                min = Math.min(min, right.min);
            }
            if (left != null && !left.isBST) {
                isBST = false;
            }
            if (right != null && !right.isBST) {
                isBST = false;
            }
            if (left != null && left.max >= node.value) {
                isBST = false;
            }
            if (right != null && right.min <= node.value) {
                isBST = false;
            }
            return new Info(isBST, max, min);
        }



        static class Info{
            boolean isBST;
            int max;
            int min;
            public Info(boolean is,int ma,int mi){
                this.isBST = is;
                this.max = ma;
                this.min = mi;
            }
        }

    }

    static class Code2{
        public static boolean isBST(Node head) {
            if (head == null) {
                return true;
            }
            return process(head).isBST;
        }
        public static Info process(Node node){
            if (node == null) {
                return null;
            }
            Info left = process(node.left);
            Info right = process(node.right);
            boolean isBST = false;
            int max = node.value;
            int min = node.value;
            if (left != null) {
                max = Math.max(max, left.max);
                min = Math.min(min, left.min);
            }
            if (right != null) {
                max = Math.max(max, right.max);
                min = Math.min(min, right.min);
            }
            if (
                    (left == null ? true : left.isBST)
                    && (right == null ? true : right.isBST)
                    && (left == null ? true : left.max < node.value)
                    && (right == null ? true : right.min > node.value)
            ) {
                isBST = true;
            }
            return new Info(isBST, max, min);
        }


        static class Info{
            boolean isBST;
            int max;
            int min;
            public Info(boolean is,int ma,int mi){
                this.isBST = is;
                this.max = ma;
                this.min = mi;
            }

        }
    }


    static class Logarithm{
        public static boolean isBST(Node head) {
            if (head == null) {
                return true;
            }
            ArrayList<Node> list = new ArrayList<>();
            in(head, list);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).value <= list.get(i - 1).value) {
                    return false;
                }
            }

            return true;
        }

        public static void in(Node node, ArrayList<Node> list) {
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
        System.out.println("start!");
        for (int i = 0; i < test; i++) {
            Node head = generateRandomNode(level, range);
            boolean codec = Code2.isBST(head);
            boolean logarithm = Logarithm.isBST(head);
            if (codec != logarithm) {
                System.out.println("fuck!");
                break;
            }
        }
        System.out.println("end!");
    }

    public static Node generateRandomNode(int level, int range) {
        return generate(1, level, range);
    }

    private static Node generate(int i, int n, int range) {
        if (i > n || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (range * Math.random()));
        head.left = generate(i + 1, n, range);
        head.right = generate(i + 1, n, range);
        return head;
    }
}
