package class10;

/**
 * 二叉树的前，中，后序遍历
 */
public class Code02_RecursiveTraversalBT_Study {
    public static class Node{
        private int value;
        public Node left;
        public Node right;
        Node(int data){
            value = data;
        }
    }

    /**
     * 前序遍历
     * @param head
     */
    public static void pre(Node head){
        if (head == null){
            return;
        }
        System.out.print(head.value + ",");
        pre(head.left);
        pre(head.right);
    }

    /**
     * 中序遍历
     * @param head
     */
    public static void in(Node head){
        if (head == null){
            return;
        }
        in(head.left);
        System.out.print(head.value + ",");
        in(head.right);
    }

    public static void post(Node head){
        if (head == null){
            return;
        }
        post(head.left);
        post(head.right);
        System.out.print(head.value + ",");
    }




    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        pre(head);
        System.out.println();
        System.out.println("---------前序遍历--------");
        in(head);
        System.out.println();
        System.out.println("---------中序遍历--------");
        post(head);
        System.out.println();
        System.out.println("---------后序遍历--------");
    }

}
