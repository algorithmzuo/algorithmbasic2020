package leo.class07_11;

/**
 * @author Leo
 * @ClassName SuccessorNode
 * @DATE 2020/12/9 10:00 下午
 * @Description 找到某个几点的后继节点
 * 后继节点:在中序遍历中的下个节点就是后继节点.
 */
public class SuccessorNode {


    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            //在右子树下找最左侧的节点
            Node right = node.right;
            while (right.left != null) {
                right = right.left;
            }
            return right;
        }else{
            //没有右子树,找顶级父节点
            Node parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getSuccessorNode1(Node node){
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            Node cur = node.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }else{
            Node parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getSuccessorNode2(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            Node cur = node.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }else{
            Node parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode2(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode2(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode2(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode2(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode2(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode2(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode2(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode2(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode2(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode2(test));
    }


}
