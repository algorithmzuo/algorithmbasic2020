package leo.class07_10;


import java.util.Stack;

/**
 * @author Leo
 * @ClassName RecursiveTraversalBT
 * @DATE 2020/12/4 4:41 下午
 * @Description 递归遍历二叉树
 * 先序:头左右
 * 中序:左头右
 * 后续:左右头
 *
 */
public class TraversalBT {

    public static void main(String[] args){
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        Recursive.pre1(head);
        System.out.println();
        UnRecursive.pre2(head);
        System.out.println("--------");

        Recursive.in1(head);
        System.out.println();
        UnRecursive.in3(head);
        System.out.println("---------");

        Recursive.pos1(head);
        System.out.println();
        UnRecursive.pos2(head);
        System.out.println("---------");

    }

}


/**
 * 递归序,
 * 每个节点都会遍历三次
 */
class Recursive{

    public static void pre(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value+" ");
        pre(node.left);
        pre(node.right);
    }

    public static void pre1(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value+" ");
        pre1(node.left);
        pre1(node.right);
    }

    public static void in(TreeNode node) {
        if (node == null) {
            return;
        }
        in(node.left);
        System.out.print(node.value+" ");
        in(node.right);
    }
    public static void in1(TreeNode node){
        if (node == null) {
            return;
        }
        in1(node.left);
        System.out.print(node.value+" ");
        in1(node.right);
    }

    public static void pos(TreeNode node) {
        if (node == null) {
            return;
        }
        pos(node.left);
        pos(node.right);
        System.out.print(node.value+" ");
    }

    public static void pos1(TreeNode node) {
        if (node == null) {
            return;
        }
        pos1(node.left);
        pos1(node.right);
        System.out.print(node.value + " ");
    }
}


class UnRecursive{


    /**
     * 前序:头左右
     */
    public static void pre(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.println("pre-order: ");
        Stack<TreeNode> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.value + " ");
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
        System.out.println();
    }

    public static void pre1(TreeNode head){
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            System.out.print(cur.value+" ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }

        }
        System.out.println();
    }

    public static void pre2(TreeNode head){
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = head;
        stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            System.out.print(cur.value + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        System.out.println();

    }

    /**
     * 中序:左头右
     */
    public static void in(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.println("in-order: ");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = head;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();

    }

    public static void in1(TreeNode head){
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = head;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }

        }
        System.out.println();

    }

    public static void in2(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = head;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }

    public static void in3(TreeNode head){
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = head;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }




    /**
     * 左右头
     */
    public static void pos(TreeNode head){
        if (head == null) {
            return;
        }
        System.out.println("pos-order: ");
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        TreeNode cur = head;
        s1.push(cur);
        while (!s1.isEmpty()) {
            cur = s1.pop();
            s2.push(cur);
            if (cur.left != null) {
                s1.push(cur.left);
            }
            if (cur.right != null) {
                s1.push(cur.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().value + " ");
        }
        System.out.println();

    }

    public static void pos1(TreeNode head){
        if (head == null) {
            return;
        }
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        TreeNode cur = head;
        s1.push(cur);
        while (!s1.isEmpty()) {
            cur = s1.pop();
            s2.push(cur);
            if (cur.left != null) {
                s1.push(cur.left);
            }
            if (cur.right != null) {
                s1.push(cur.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().value + " ");
        }
        System.out.println();
    }

    public static void pos2(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        TreeNode cur = head;
        s1.push(cur);
        while (!s1.isEmpty()) {
            cur = s1.pop();
            s2.push(cur);
            if (cur.left != null) {
                s1.push(cur.left);
            }
            if (cur.right != null) {
                s1.push(cur.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().value + " ");
        }
        System.out.println();

    }
}




