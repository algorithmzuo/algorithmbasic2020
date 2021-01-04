package leo.class11;


import java.util.LinkedList;
import java.util.Queue;

/**
 *  &author Leo
 *  &ClassName LevelTraversalBT
 *  &DATE 2020/12/9 2:11 下午
 *  &Description 二叉树宽度(按层)遍历 queue
 */
public class LevelTraversalBT {
    static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

    }


    public static void level(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        System.out.println();

    }

    public static void level1(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Node cur = head;
        queue.offer(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        System.out.println();
    }


    public static void level2(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Node cur = head;
        queue.offer(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        System.out.println();
    }

    public static void level3(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Node cur = head;
        queue.offer(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        System.out.println();
    }

    public static void level4(Node head){
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Node cur = head;
        queue.offer(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.value+" ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        System.out.println();
    }

    public static void level5(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        Node cur = null;
        Node l = null;
        Node r = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            l = cur.left;
            r = cur.right;
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            }
            System.out.print(cur.value+" ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        Node node = randomTreeNode(10);
        level5(node);
    }


    public static Node randomTreeNode(int size) {
        if (size == 0) {
            return null;
        }
        int v = 1;
        Node head = new Node(v++);
        if (size == 1) {
            return head;
        }
        Node cur = head;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(cur);
        while (size != 0) {
            Node left = null;
            Node right = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                left = new Node(v++);
                right = new Node(v++);
                cur.left = left;
                cur.right = right;
            }
            queue.offer(left);
            queue.offer(right);
            size--;
        }

        return head;
    }
}


