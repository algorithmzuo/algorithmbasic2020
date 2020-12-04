package leo.class06_09;

import com.sun.org.apache.regexp.internal.REUtil;

import java.util.HashMap;

/**
 * @author Leo
 * @ClassName CopyListWithRandom
 * @DATE 2020/12/4 9:22 上午
 * @Description
 * every node have two pointer,it's next and random
 * copy they and return new node
 */
public class CopyListWithRandom {
    static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int v) {
            this.value = v;
        }

    }

    public static Node copyRandomNodeByMap(Node head) {

        if (head == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyRandomNode(Node head) {

        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand == null ? null : cur.rand.next;
            cur = next;
        }

        cur = head;
        Node copyHead = head.next;
        while (cur != null) {
            curCopy = cur.next;
            next = cur.next.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return copyHead;
    }



    public static void main(String[] args){
        int testTime = 1000;
        int maxSize = 30;
        int range = 100;
        System.out.println("Start!");

        for (int i = 0; i < testTime; i++) {
            Node node = randomNode(maxSize, range);
            Node headMap = copyRandomNodeByMap(node);
            Node head = copyRandomNode(node);
            Node curNode = node;
            Node curHeadMap = headMap;
            Node curHead = head;
            while (curNode != null) {
                if (curHead.value!=curNode.value|| curHeadMap.value!=curNode.value) {
                    System.out.println("value");
                    System.out.println(curNode.value);
                    System.out.println(curHead.value);
                    System.out.println(curHeadMap.value);
                    System.out.println("fuck");
                    break;
                }
                if (curNode.rand != null) {
                    if (curHead.rand.value!=curNode.rand.value|| curHeadMap.rand.value!=curNode.rand.value) {
                        System.out.println("rand");
                        System.out.println(curNode.rand.value);
                        System.out.println(curHead.rand.value);
                        System.out.println(curHeadMap.rand.value);
                        System.out.println("rand fuck");
                        break;
                    }
                }
                curNode = curNode.next;
                curHead = curHead.next;
                curHeadMap = curHeadMap.next;
            }
        }
        System.out.println("End!");

    }

    public static Node randomNode(int maxSize, int range) {
        Node head = new Node(randomInt(range));
        Node cur = head;
        int size = (int) (maxSize * Math.random() + 1);
        for (int i = 0; i < size; i++) {
            cur.next = new Node(randomInt(range));
            cur = cur.next;
        }
        cur = head;
        while (cur != null && size > 0) {
            double rand = Math.random();
            if (rand < 0.5) {
                double v = size * Math.random() + 1;
                Node temp = head;
                for (int i = 0; i < v; i++) {
                    temp = temp.next;
                    size--;
                }
                cur.rand = temp;
            }
            cur = cur.next;

        }
        return head;
    }

    public static int randomInt(int range) {
        return (int) ((range * Math.random() + 1) - (range * Math.random() + 1));
    }
}




