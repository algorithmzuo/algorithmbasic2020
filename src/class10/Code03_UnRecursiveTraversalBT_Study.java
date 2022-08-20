package class10;

import java.util.Stack;

/**
 * 非递归方式打印前，中，低序遍历
 */
public class Code03_UnRecursiveTraversalBT_Study {
    public static class Node{
        private int value;
        public Node left;
        public Node right;
        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 前序遍历
     * 容器使用堆栈，有头结点先入头结点
     * 栈弹出记作cur
     * 先入右节点，再入左节点
     */
    private static void pre(Node head){
        System.out.println("pre-order");
        if (head != null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()){
                head = stack.pop();
                System.out.println(head.value+" ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 使用非递归实现中序遍历
     *  把节点的整个左树存入
     * @param head
     */
    public static void in(Node head){
        System.out.println("in sort------");
        if (head != null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty() || head != null){
                // 把左树都放入到栈里面去
                if (head != null){
                    stack.push(head);
                    head = head.left;
                }else {
                    head = stack.pop();
                    System.out.print(head.value + ",");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }


    /**
     * 遍历打印后节点
     * 打印前序 前>左>右的时候，入栈顺序时，头 > 右 > 左
     * 如果想打印 前 > 右 > 左的时候，入栈顺序 头 > 左 > 右
     * 然后把头右左的节点遍历放到栈2中
     * 把栈2打印出来，就是后序遍历，即左 > 右 > 头
     * @param head
     */
    public static void pos(Node head){
        if (head != null){
            Stack<Node> stack1= new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            stack1.push(head);
            while (!stack1.isEmpty()){
                head = stack1.pop();
                stack2.push(head);
                if (head.left != null){
                    stack1.push(head.left);
                }
                if (head.right!= null){
                    stack1.push(head.right);
                }
            }
            while (!stack2.isEmpty()){
                System.out.println(stack2.pop().value + "");
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new  Node(2);
        head.right = new  Node(3);
        head.left.left = new  Node(4);
        head.left.right = new  Node(5);
        head.right.left = new  Node(6);
        head.right.right = new  Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos(head);
        System.out.println("========");
//        pos2(head);
//        System.out.println("========");
    }
}
