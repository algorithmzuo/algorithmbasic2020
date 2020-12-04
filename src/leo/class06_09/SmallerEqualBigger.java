package leo.class06_09;

/**
 * @author Leo
 * @ClassName SmallerEqualBigger
 * @DATE 2020/12/3 5:56 下午
 * @Description 将链表根据给定值,按小左大右放置
 */
public class SmallerEqualBigger {



    static class NodePartitionByArray{

        public static Node nodePartitionByArray(Node head, int target) {
            if (head == null || head.next == null) {
                return head;
            }
            int i = 0;
            Node cur = head;
            while (cur != null) {
                cur = cur.next;
                i++;
            }
            Node[] nodes = new Node[i];
            cur = head;
            for (i = 0; i < nodes.length; i++) {
                nodes[i] = cur;
                cur = cur.next;
            }
            partitionNode(nodes, target);
            for (i = 1; i < nodes.length; i++) {
                nodes[i - 1].next = nodes[i];
            }
            nodes[i - 1].next = null;
            return nodes[0];
        }

        public static void partitionNode(Node[] nodes, int target) {
            int small = -1;
            int big = nodes.length;
            int index = 0;
            while (index < big) {
                if (nodes[index].value < target) {
                    swapNodeArray(nodes, index++, ++small);
                } else if (nodes[index].value > target) {
                    swapNodeArray(nodes, index, --big);
                }else {
                    index++;
                }
            }
        }

        public static void swapNodeArray(Node[] nodes, int i, int j) {

            if (i == j) {
                return;
            }
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }
    }

    public static Node listPartition(Node head, int target) {
        if (head == null || head.next == null) {
            return head;
        }
        Node sH = null;
        Node sT = null;
        Node bH = null;
        Node bT = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < target) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                }else{
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value > target) {
                if (bH == null) {
                    bH = head;
                    bT = head;
                }else {
                    bT.next = head;
                    bT = head;
                }

            } else if (head.value == target) {
                if (bH == null) {
                    bH = head;
                    bT = head;
                }else{
                    head.next = bH;
                    bH = head;
                }
            }
            head = next;
        }
        if (sT == null) {
            return bH;
        } else {
            sT.next = bH;
            return sH;
        }
    }
}

class SmallerEqualBigger_Main{
    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int maxSize = 5;
        int range = 100;
        Node originNode = generateRandomNode(maxSize, range);
        printLinkedList(originNode);
        // head1 = listPartition1(head1, 4);
        int i = randomInt(range);
        System.out.println(i);
        originNode = SmallerEqualBigger.listPartition(originNode, i);
        printLinkedList(originNode);

    }

    public static  Node generateRandomNode(int maxSize, int range) {
        Node head = new Node(randomInt(range));
        Node cur = head;
        for (int i = 0; i < maxSize; i++) {
            Node node = new Node(randomInt(range));
            cur.next = node;
            cur = cur.next;
        }
        if (Math.random() > 0.5) {
            return head;
        }
        Node copyNode = copyNode(head);
        copyNode = reverseNode(copyNode);
        if (copyNode.next != null && Math.random() > 0.5) {
            cur.next = copyNode.next;
        }else{
            cur.next = copyNode;
        }
        return head;
    }

    private static Node copyNode(Node head) {
        Node copyHead = new Node(head.value);
        Node curCopy = copyHead;
        Node curHead = head.next;
        while (curHead.next != null) {
            curCopy.next = new Node(curHead.value);
            curHead = curHead.next;
            curCopy = curCopy.next;
        }
        curCopy.next = new Node(curHead.value);
        return copyHead;
    }


    private static Node reverseNode(Node head) {
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

    public static int randomInt(int range) {
        return (int) ((range * Math.random() + 1) - (range * Math.random() + 1));
    }

}



