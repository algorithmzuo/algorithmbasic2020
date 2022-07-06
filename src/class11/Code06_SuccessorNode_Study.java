package class11;

/**
 * 寻找后继节点
 */
public class Code06_SuccessorNode_Study {
    public static class Node{
        public Integer value;
        public Node left;
        public Node right;
        public Node parent;
        public Node(Integer _value){
            value = _value;
        }
    }

    /**
     * 如果有右树，那么后继节点就是该节点的右树的最左节点
     * @param node
     * @return
     */
    public static Node getSuccessorNode(Node node){
        if (node == null){
            return null;
        }
        if(node.right != null) {
           return getMostLeftNode(node.right);
        }else {
            // 没有右树的话，则需要找到该节点作为某个节点左子树的最右节点
            Node parent = node.parent;
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getMostLeftNode(Node node){
        if (node== null){
            return null;
        }
        while (node.left!=null){
            node = node.left;
        }
        return node;
    }


    public static void main(String[] args) {
        Node head = new  Node(6);
        head.parent = null;
        head.left = new  Node(3);
        head.left.parent = head;
        head.left.left = new  Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new  Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new  Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new  Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new  Node(9);
        head.right.parent = head;
        head.right.left = new  Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new  Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new  Node(10);
        head.right.right.parent = head.right;

         Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}
