package leo.class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Leo
 * @ClassName SerializeAndReconstructTree
 * @DATE 2020/12/9 4:33 下午
 * @Description 序列化二叉树
 * 先序和后序序列化和反序列化都可以,中序不行,中序有歧义.
 */
public class SerializeAndReconstructTree {
    static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

    }

    /**
     * 先序方式序列化为队列
     */
    public static Queue<String> preSerial(Node node) {
        Queue<String> queue = new LinkedList<>();
        preSerial(node, queue);
        return queue;
    }

    public static void preSerial(Node node, Queue<String> queue) {
        if (node == null) {
            queue.offer(null);
        }else{
            queue.offer(String.valueOf(node.value));
            preSerial(node.left, queue);
            preSerial(node.right, queue);
        }
    }


    public static Queue<String> preSerial1(Node head) {
        if (head == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        preSerial1(head, queue);
        return queue;
    }

    public static void preSerial1(Node node, Queue<String> queue) {
        if (node == null) {
            queue.offer(null);
        }else{
            queue.offer(String.valueOf(node.value));
            preSerial(node.left, queue);
            preSerial(node.right, queue);
        }
    }

    public static Queue<String> preSerial2(Node head) {
        if (head == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        preSerial2(head, queue);
        return queue;
    }

    static void preSerial2(Node node, Queue<String> queue) {
        if (node == null) {
            queue.offer(null);
            return;
        }
        queue.offer(String.valueOf(node.value));
        preSerial2(node.left, queue);
        preSerial2(node.right, queue);
    }


    /**
     * 先序方式反序列化
     */
    public static Node preDeSerial(Queue<String> queue) {
        if (queue.isEmpty() || queue == null) {
            return null;
        }
        return preDeSerialProcess(queue);
    }

    public static Node preDeSerialProcess(Queue<String> queue) {
        String v = queue.poll();
        if (v == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(v));
        head.left = preDeSerialProcess(queue);
        head.right = preDeSerialProcess(queue);
        return head;
    }


    public static Node preDeSerial1(Queue<String> queue) {
        if (queue == null) {
            return null;
        }
        Node head = preDeSerialProcess1(queue);
        return head;
    }

    public static Node preDeSerialProcess1(Queue<String> queue) {
        String nodeValue = queue.poll();
        if (nodeValue == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(nodeValue));
        head.left = preDeSerialProcess(queue);
        head.right = preDeSerialProcess(queue);
        return head;
    }

    static Node preDeSerial2(Queue<String> queue) {
        if (queue == null) {
            return null;
        }
        return preDeSerialProcess2(queue);
    }

    static Node preDeSerialProcess2(Queue<String> queue) {
        String cur = queue.poll();

        if (cur == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(cur));
        head.left = preDeSerialProcess2(queue);
        head.right = preDeSerialProcess2(queue);
        return head;

    }

    /**
     * 后续方式序列化队列
     */
    public static Queue<String> posSerial(Node node) {
        Queue<String> queue = new LinkedList<>();
        posSerial(node, queue);
        return queue;
    }

    public static void posSerial(Node node, Queue<String> queue) {
        if (node == null) {
            queue.offer(null);
        }else{
            posSerial(node.left, queue);
            posSerial(node.right, queue);
            queue.offer(String.valueOf(node.value));
        }
    }

    public static Queue<String> posSerial1(Node node) {
        if (node == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        posSerial1(node, queue);
        return queue;
    }

    public static void posSerial1(Node node, Queue<String> queue) {
        if (node == null) {
            queue.offer(null);
        }else{
            posSerial1(node.left, queue);
            posSerial1(node.right, queue);
            queue.offer(String.valueOf(node.value));
        }

    }

    static Queue<String> posSerial2(Node head) {
        if (head == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        posSerial2(head, queue);
        return queue;
    }

    static void posSerial2(Node node, Queue<String> queue) {
        if (node == null) {
            queue.offer(null);
        }else {
            posSerial2(node.left, queue);
            posSerial2(node.right, queue);
            queue.offer(String.valueOf(node.value));
        }

    }
    /**
     * 后续方式反序列化
     * 左右头
     */
    public static Node posDeSerial(Queue<String> queue) {
        if (queue.isEmpty() || queue == null) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }

        return posDeSerialProcess(stack);
    }

    public static Node posDeSerialProcess(Stack<String> stack) {
        String v = stack.pop();
        if (v == null ) {
            return null;
        }
        Node head = new Node(Integer.valueOf(v));
        head.right = posDeSerialProcess(stack);
        head.left = posDeSerialProcess(stack);
        return head;
    }

    public static Node posDeSerial1(Queue<String> queue) {
        if (queue == null) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return posDeSerialProcess1(stack);

    }

    public static Node posDeSerialProcess1(Stack<String> stack) {
        String v = stack.pop();
        if (v == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(v));
        head.right = posDeSerialProcess1(stack);
        head.left = posDeSerialProcess1(stack);
        return head;

    }

    static Node posDeSerial2(Queue<String> queue) {

        if (queue == null) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return posDeSerialProcess2(stack);
    }

    static Node posDeSerialProcess2(Stack<String> stack) {
        String cur = stack.pop();
        if (cur == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(cur));
        head.right = posDeSerialProcess2(stack);
        head.left = posDeSerialProcess2(stack);
        return head;
    }

    /**
     * 按层序列化
     */
    public static Queue<String> levelSerial(Node node) {
        Queue<String> ans = new LinkedList<>();
        if (node == null) {
            ans.offer(null);
        }else{
            ans.offer(String.valueOf(node.value));
            Queue<Node> queue = new LinkedList<>();
            queue.offer(node);
            while (!queue.isEmpty()) {
                node = queue.poll();
                if (node.left != null) {
                    ans.offer(String.valueOf(node.left.value));
                    queue.offer(node.left);
                }else{
                    ans.offer(null);
                }
                if (node.right != null) {
                    ans.offer(String.valueOf(node.right.value));
                    queue.offer(node.right);
                }else{
                    ans.offer(null);
                }
            }
        }
        return ans;
    }
    /**
     * 按层反序列化
     */
    public static Node levelDeSerial(Queue<String> list) {
        if (list.isEmpty() || list == null || list.size() == 0) {
            return null;
        }
        Node head = generateNode(list.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            cur.left = generateNode(list.poll());
            cur.right = generateNode(list.poll());
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return head;
    }

    public static Queue<String> levelSerial1(Node node) {
        Queue<String> ans = new LinkedList<>();
        if (node == null) {
            ans.offer(null);

        }else{
            Node cur = node;
            ans.offer(String.valueOf(cur.value));
            Queue<Node> queue = new LinkedList<>();
            queue.offer(cur);
            while (!queue.isEmpty()) {
                cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                    ans.offer(String.valueOf(cur.left.value));
                }else{
                    ans.offer(null);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    ans.offer(String.valueOf(cur.right.value));

                }else{
                    ans.offer(null);
                }
            }

        }

        return ans;
    }

    public static Node levelDeSerial1(Queue<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        Node head = generateNode1(list.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            cur.left = generateNode1(list.poll());
            cur.right = generateNode1(list.poll());
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return head;
    }


    public static Node generateNode(String v) {
        if (v == null) {
            return null;
        }
        Node node = new Node(Integer.valueOf(v));
        return node;
    }

    public static Node generateNode1(String v) {
        if (v == null) {
            return null;
        }
        Node node = new Node(Integer.valueOf(v));
        return node;
    }


    /**
     * 功能描述 : 测试
     * @author Leo
     * @date 2020/12/9 8:11 下午
     * @param args
     * @return void
     */
    public static void main(String[] args){
        int maxLevel = 10;
        int range = 100;
        int testTime = 10000;
        System.out.println("start");
        for (int i = 0; i < testTime; i++) {
            Node head = generateRandomNode(maxLevel, range);
            Queue<String> preSerial = preSerial2(head);
            Queue<String> posSerial = posSerial2(head);
            Node posDeSerial = posDeSerial2(posSerial);
            Queue<String> levelSerial = levelSerial1(head);
            Node levelDeSerial = levelDeSerial1(levelSerial);

            Node preDeSerial = preDeSerial2(preSerial);
            if (!isEqualsNode(preDeSerial, head)) {
                System.out.println("preDeSerial ==> fuck");
                break;
            }
            if (!isEqualsNode(posDeSerial, head)) {
                System.out.println("posDeSerial ==> fuck");
                break;
            }
            if (!isEqualsNode(levelDeSerial, head)) {
                System.out.println("levelDeSerial ==> fuck");
                break;
            }
        }
        System.out.println("end");


    }

    public static Node generateRandomNode(int maxLevel, int range) {
        return generate(1, maxLevel, range);
    }

    public static Node generate(int curLevel, int maxLevel, int range) {
        if (curLevel > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (range * Math.random() ));
        head.left = generate(curLevel + 1, maxLevel, range);
        head.right = generate(curLevel + 1, maxLevel, range);
        return head;
    }

    public static boolean isEqualsNode(Node head1, Node head2) {
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isEqualsNode(head1.left, head2.left) && isEqualsNode(head1.right, head2.right);
    }
}


