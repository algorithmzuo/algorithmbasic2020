package class11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code01_LevelTraversalBT_Study2 {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }

    /**
     * 按层遍历思路：
     * 利用队列，每个元素入队列，然后取头元素出来
     * 判断当前元素是否有左右子树，按照先左后右的顺序把数据入队列
     * 以此类推，直到遍历完所有数据
     */
    public void levelTraversalBT(Node root){
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }
            System.out.println(node.value);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        Code01_LevelTraversalBT_Study2 clazz = new Code01_LevelTraversalBT_Study2();
        clazz.levelTraversalBT(root);

    }


}
