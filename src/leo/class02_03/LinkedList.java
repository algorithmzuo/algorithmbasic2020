package leo.class02_03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 * @ClassName LinkedList
 * @DATE 2020/11/18 3:24 下午
 * @Description 链表练习
 * 单向链表
 * 双向链表
 * 链表翻转
 *
 */
public class LinkedList {

    public static class Node{
        int value;
        Node next;


        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleNode{
        int value;
        DoubleNode pre;
        DoubleNode next;
        public DoubleNode(int value) {
            this.value = value;
        }
    }

    /**
     * 功能描述 : 单链表翻转
     * @author Leo
     * @date 2020/11/18 3:42 下午
     * @param head
     * @return leo.class02.LinkedList.Node
     */
    public static Node reverseNode(Node head) {

        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseNode1(Node head) {
        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseNode2(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseNode3(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }

    public static Node reverseNode4(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseNode5(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {

            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseNode6(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseNode7(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseNode8(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseNode9(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseNode10(Node head) {
        if (head == null) {
            return null;
        }
        Node pre = null;
        Node next = null;
        while (head != null) {
            next  = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseNode11(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseNode12(Node head) {
        if (head == null) {
            return null;
        }
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleNode(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next;
        while (head != null) {
            next = head.next;
            head.pre = next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleNode1(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.pre = next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleNode2(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.pre = next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoublerNode3(DoubleNode head) {
        DoubleNode pre = head;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = head.next;
            pre = head;
            head = next;
        }
        return pre;

    }

    public static DoubleNode reverseDoubleNode4(DoubleNode head) {
        DoubleNode pre = head;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleNode5(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleNode6(DoubleNode head) {
        if (head == null) {
            return null;
        }
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.pre = next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }


    public static DoubleNode reverseDoubleNode7(DoubleNode head){
        if (head == null || head.next == null) {
            return head;
        }
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.pre = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 功能描述 : 随机生成单链表
     * @author Leo
     * @date 2020/11/18 6:43 下午
     * @param sizeMax
     * @param range
     * @return leo.class02.LinkedList.Node
     */
    public static Node randomNode(int sizeMax, int range) {
        int size = (int) ((sizeMax + 1) * Math.random());
        if (size == 0) {
            return null;
        }
        Node head = new Node(randomInt(range));
        Node pre = head;
        size--;
        while (size != 0) {
            Node cur = new Node(randomInt(range));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;

    }

    /**
     * 功能描述 : 随机生成双向链表
     * @author Leo
     * @date 2020/11/19 10:39 上午
     * @param sizeMix
     * @param range
     * @return leo.class02.LinkedList.DoubleNode
     */
    public static DoubleNode randomDoubleNode(int sizeMix, int range) {

        int length = randomInt(sizeMix);
        if (length == 0) {
            return null;
        }
        DoubleNode head = new DoubleNode(randomInt(range));
        DoubleNode pre = head;
        length--;
        while (length != 0) {
            DoubleNode cur = new DoubleNode(randomInt(range));
            pre.next = cur;
            cur.pre = pre;
            pre = cur;
            length--;
        }
        return head;

    }

    public static int randomInt(int range) {
        return (int) (Math.random() * (range + 1));
    }

    /**
     * 功能描述 : 验证单链表
     * @author Leo
     * @date 2020/11/18 9:40 下午
     * @param nodeList
     * @param node
     * @return boolean
     */
    public static boolean verifyReverseListAndNode(List<Node> nodeList, Node node) {
        Node cur = node;
        for (int i = nodeList.size() - 1; i >= 0; i--) {
            if (cur == null || !cur.equals(nodeList.get(i))) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * 功能描述 : node转list
     * @author Leo
     * @date 2020/11/18 10:11 下午
     * @param node
     * @return java.util.List<leo.class02.LinkedList.Node>
     */
    public static List<Node> nodeToList(Node node) {
        List<Node> list = new ArrayList<>();
        Node cur = node;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list;
    }

    /**
     * 功能描述 : doubleNode转list
     * @author Leo
     * @date 2020/11/19 10:42 上午
     * @param doubleNode
     * @return java.util.List<leo.class02.LinkedList.DoubleNode>
     */
    public static List<DoubleNode> DoubleNodeToList(DoubleNode doubleNode) {
        List<DoubleNode> list = new ArrayList<>();
        DoubleNode cur = doubleNode;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list;
    }

    public static boolean verifyReverseListAndDoubleNode(List<DoubleNode> doubleNodeList, DoubleNode doubleNode) {
        DoubleNode cur = doubleNode;
        for (int i = doubleNodeList.size() - 1; i >= 0; i--) {
            if (cur == null || !cur.equals(doubleNodeList.get(i))) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public static void main(String[] args){
        int maxSize = 5;
        int range = 90;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node head = randomNode(maxSize, range);
            List<Node> nodeList = nodeToList(head);
            Node node = reverseNode12(head);
            if (!verifyReverseListAndNode(nodeList, node)) {
                System.out.println("nodeFuck!!");
                break;
            }
            DoubleNode doubleNodeHead = randomDoubleNode(maxSize, range);
            List<DoubleNode> doubleNodeList = DoubleNodeToList(doubleNodeHead);
            DoubleNode doubleNode = reverseDoubleNode7(doubleNodeHead);
            if (!verifyReverseListAndDoubleNode(doubleNodeList, doubleNode)) {
                System.out.println("doubleNodeFuck!!");
                break;
            }
        }
        System.out.println("测试结束");



    }

}
