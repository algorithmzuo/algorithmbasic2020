package class11;

import java.util.LinkedList;
import java.util.Queue;

public class Code01_LevelTraversalBT_Study {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        Node(int data){
            this.value = data;
        }
    }

    /**
     * 层级遍历
     * 使用对列作为容器，从头节点开始遍历
     * 有左入左，有右入右
     * 弹出时打印
     * @param head
     * @return
     */
    public static void levelTraversalBT(Node head){
        Queue<Node> queue = new LinkedList<>();
        if (head == null){
            return;
        }
        queue.add(head);
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.value);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        levelTraversalBT(head);
        System.out.println("========");
    }

}
