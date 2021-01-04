package leo.class12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Leo
 * @ClassName IsCBT
 * @DATE 2020/12/11 3:22 下午
 * @Description
 * 判断二叉树是否是完全二叉树
 * 1.如果一个树只有右节点没有左节点 直接false
 * 2.当第一次遇到左右不双全的树时,剩下的节点必须是叶节点
 */
public class IsCBT {

    static class ByQueue{
        public static boolean isCBT(Node head) {
            if (head == null) {
                return true;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.offer(head);
            boolean leaf = false;
            Node cur = null;
            Node l = null;
            Node r = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                l = cur.left;
                r = cur.right;
                if ( //是叶节点并且有子孩子
                        (leaf && (l != null || r != null))
                                ||
                                (l == null && r != null)//当没有左节点时
                ) {
                    return false;
                }
                if (l != null) {
                    queue.offer(l);
                }
                if (r != null) {
                    queue.offer(r);
                }
                if (l == null || r == null) {
                    leaf = true;
                }
            }
            return true;
        }

        public static boolean isCBT1(Node head){
            if (head == null) {
                return true;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.offer(head);
            Node cur;
            Node l = null;
            Node r = null;
            boolean leaf = false;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                l = cur.left;
                r = cur.right;
                if (l!= null) {
                    queue.offer(l);
                }
                if (r != null) {
                    queue.offer(r);
                }
                if ((leaf &&
                        (l != null || r != null))
                        || (l == null && r != null)
                ) {

                    return false;
                }
                if (l == null || r == null) {
                    leaf = true;
                }
            }
            return true;
        }
    }

    static class Recursion{
        public static boolean isCBT(Node head) {
            return process(head).isCBT;

        }
        public static Info process(Node node){
            if (node == null) {
                return new Info(true, true, 0);
            }
            Info left = process(node.left);
            Info right = process(node.right);
            int height = Math.max(left.height, right.height) + 1;
            boolean isFull = left.isFull && right.isFull && right.height == left.height;
            boolean isCBT = false;
            if (isFull) {
                isCBT = true;
            }else{
                //如果左右都不是满二叉树的情况下
                //左树是满二叉树并且右树是满二叉树
                if (left.isCBT && right.isCBT) {
                    //左树是二叉树,右树是满二叉树,左树的高度比右树高度大1
                    if (left.isCBT && right.isFull && left.height == right.height + 1) {
                        isCBT = true;
                    }
                    //左树是满二叉树,右树是满二叉树,左树的高度比右树高度大1
                    if (left.isFull && right.isFull && left.height == right.height + 1) {
                        isCBT = true;
                    }
                    //左树是满二叉树,右树是二叉树,左树高度与右树高度一样
                    if (left.isFull && right.isCBT && right.height == left.height) {
                        isCBT = true;
                    }

                }
            }
            return new Info(isFull, isCBT, height);
        }
        static class Info{
            boolean isFull;
            boolean isCBT;
            int height;
            public Info(boolean f,boolean c,int h){
                this.isFull = f;
                this.isCBT = c;
                this.height = h;
            }
        }
    }

    static class Recursion1{
        public static boolean isCBT(Node head){
            return process(head).isCBT;
        }
        public static class Info{
            public boolean isFull;
            public boolean isCBT;
            public int height;
            public Info(boolean f,boolean c,int h){
                this.isFull = f;
                this.isCBT = c;
                this.height = h;

            }
        }
        public static Info process(Node node){
            if (node == null){
                return new Info(true, true, 0);
            }
            Info left = process(node.left);
            Info right = process(node.right);
            boolean isFull = left.isFull && right.isFull && left.height == right.height;
            int height = Math.max(left.height, right.height) + 1;
            boolean isCBT = isFull;
            if (left.isCBT && right.isFull && left.height == right.height + 1) {
                isCBT = true;
            } else if (left.isFull && right.isFull && left.height == right.height + 1){
                isCBT = true;
            } else if (left.isFull && right.isCBT && left.height == right.height) {
                isCBT = true;
            }
            return new Info(isFull, isCBT, height);
        }
    }

    static class Recursion2{
        static boolean isCBT(Node head) {

            return process(head).isCBT;
        }

        static Info process(Node node){
            if (node == null) {
                return new Info(true, true, 0);
            }
            Info left = process(node.left);
            Info right = process(node.right);
            boolean isFull = left.isFull && right.isFull && right.height == left.height;
            int height = Math.max(right.height, left.height) + 1;
            boolean isCBT = isFull;
            if (left.isFull && right.isCBT && left.height == right.height) {
                isCBT = true;
            } else if (left.isCBT && right.isFull && left.height == right.height + 1) {
                isCBT = true;
            } else if (left.isFull && right.isFull && left.height == right.height + 1) {
                isCBT = true;
            }
            return new Info(isFull, isCBT, height);
        }

        static class Info {
            boolean isFull;
            boolean isCBT;
            int height;

            public Info(boolean isFull, boolean isCBT, int h) {
                this.isFull = isFull;
                this.isCBT = isCBT;
                this.height = h;
            }
        }
    }


    public static void main(String[] args){
        int level = 4;
        int range = 100;
        int testTime = 10000;
        System.out.println("start");

        for (int i = 0; i < testTime; i++) {
            Node head = generateRandomNode(level, range);
            boolean queue = ByQueue.isCBT1(head);
            boolean recursion = Recursion2.isCBT(head);
            if (queue != recursion) {
                System.out.println("fuck!!!");
                break;
            }

        }
        System.out.println("end");

    }
    static Node generateRandomNode(int n,int r){
        return generateNode(1,n,r);
    }
    static Node generateNode(int i,int n,int r){
        if (i > n || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (r * Math.random()));
        head.left = generateNode(i + 1, n, r);
        head.right = generateNode(i + 1, n, r);
        return head;
    }

}

