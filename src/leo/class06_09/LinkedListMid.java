package leo.class06_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 * @ClassName class06_09
 * @DATE 2020/12/2 6:42 下午
 * @Description 找链表中点
 */
class LinkedListMid {


    //奇数长度返回中点，偶数长度返回上中点
    static class FindMidOrUpMidNode{
        public static Node findMid(Node head){
            if (head == null || head.next == null || head.next.next == null) {
                return head;
            }
            Node slow = head;
            Node fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    //奇数长度返回中点，偶数长度返回下中点
    static class FindMidOrDownMidNode{
        public static Node findMid(Node head) {
            if (head == null || head.next == null) {

                return head;
            }
            Node slow = head.next;
            Node fast = head.next;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

    }

    //奇数长度返回中点前一个，偶数长度返回上中点前一个
    static class FindMidOrUpMidPreNode {
        public static Node findMid(Node head) {
            if (head==null||head.next==null||head.next.next==null){
                return head;
            }
            Node slow = head;
            Node fast = head.next.next;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    //奇数长度返回中点前一个，偶数长度返回下中点前一个
    static class FindMidOrDownMidPreNode {
        public static Node findMid(Node head) {
            if (head == null || head.next == null || head.next.next == null) {
                return head;
            }
            Node slow = head;
            Node fast = head.next;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

    }


}

class Right{
    //奇数长度返回中点，偶数长度返回上中点
    public static Node findMidOrUpMidNode(Node head) {
        if (head == null) {
            return head;
        }
        List<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size()-1) /2);
    }

    //奇数长度返回中点，偶数长度返回下中点
    public static Node findMidOrDownMidNode(Node head) {
        if (head == null) {
            return head;
        }
        List<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get(list.size()/2);
    }
    //奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node findMidOrUpMidPreNode(Node head){
        if (head == null) {
            return head;
        }
        List<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size()-3)/2);

    }
    //奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node findMidOrDownMidPreNode(Node head){
        if (head == null) {
            return head;
        }
        List<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size()-2)/2);

    }
}

class LinkedListMidMain{


    public static Node randomNode(int maxSize, int range) {
        int length = (int)(maxSize * Math.random()) + 1;
        Node head = new Node((int) (range * Math.random()) + 1 - (int) (range * Math.random()) + 1);
        Node cur = head;
        for (int i = 0; i < length; i++) {
            Node node = new Node((int) (range * Math.random()) + 1 - (int) (range * Math.random()) + 1);
            cur.next = node;
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args){
        int testTime = 100000;
        int maxSize = 80;
        int range = 100;
        Node node;
        Node nodeTest;
        System.out.println("start!");

        for (int i = 0; i < testTime; i++) {
            Node head = randomNode(maxSize, range);
            node = LinkedListMid.FindMidOrUpMidNode.findMid(head);
            nodeTest = Right.findMidOrUpMidNode(head);
            if (!node.equals(nodeTest)){
                System.out.println("findMidOrUpMidNode");
                System.out.println(node.toString());
                System.out.println(nodeTest.toString());
                break;
            }
            node = LinkedListMid.FindMidOrDownMidNode.findMid(head);
            nodeTest = Right.findMidOrDownMidNode(head);
            if (!node.equals(nodeTest)){
                System.out.println("findMidOrDownMidNode");
                System.out.println(node.toString());
                System.out.println(nodeTest.toString());
                break;
            }
            node = LinkedListMid.FindMidOrUpMidPreNode.findMid(head);
            nodeTest = Right.findMidOrUpMidPreNode(head);
            if (!node.equals(nodeTest)){
                System.out.println("findMidOrUpMidPreNode");
                System.out.println(node.toString());
                System.out.println(nodeTest.toString());
                break;
            }
            node = LinkedListMid.FindMidOrDownMidPreNode.findMid(head);
            nodeTest = Right.findMidOrDownMidPreNode(head);
            if (!node.equals(nodeTest)){
                System.out.println("findMidOrDownMidPreNode");
                System.out.println(node.toString());
                System.out.println(nodeTest.toString());
                break;
            }
        }
        System.out.println("end!");

    }






}
