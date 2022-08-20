package class10;

/**
 * 题目：给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 要求：如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)
 * 解题思路：
 * 1.两个链表无环
 *  最终的终点会相等
 * 2.两个链表都有环
 *  2.1 两个链表不想交
 *  2.2 两个链表同一个节点入环
 *  2.3 两个链表不同节点入环
 * 3.一个有环，一个无环（该情况一定不想交）
 */
public class Code01_FindFirstIntersectNode_Study {
    public static class  Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }

    /**
     * 获取链表的相交节点
     * @param head1
     * @param head2
     */
    public static Node getInsertNode(Node head1,Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoop(head1);
        Node loop2 = getLoop(head2);
        if (loop1 == null && loop2 == null){
            return noLoop(head1,head2);
        }
        if (loop1 != null && loop2 != null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
    }

    /**
     * 获取链表的入环节点，没有环则返回null
     * 快指针一次走两个，慢指针一次走一个，当快慢指针相遇时，快指针从head节点重新开始，每次走一个
     * 当快慢指针再次相遇时，此时相等的焦点则是入环节点
     * @param head
     * @return
     */
    public static Node getLoop(Node head){
        Node fastNode = head;
        Node slowNode = head;
        while (fastNode.next != null && fastNode.next.next !=null){
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if (fastNode == slowNode){
                break;
            }
        }
        if (fastNode == null || fastNode.next == null) {
            return null;
        }
        // while循环判断了是有环的,现在要获取入环节点
        // 让快节点重新指向头结点，如果再次相遇，那么此时的节点就是第一个入环节点
        fastNode = head;
        while (fastNode != slowNode){
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        return fastNode;
    }

    /**
     * 两个链表无环，求相交的第一个节点，没有则返回null
     * 1.判断终结点是否相等，如果相等则表示有交点
     * 2.长链接先走，走到和短连接相同的长度后，在和短连接一起走
     * 3.求第一个相等的交点
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1,Node head2){
        Node cur1 = head1;
        Node cur2 = head2;
        // 两个连接相差的长度
        int n= 0;
        while (cur1.next !=null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        if(cur1 != cur2){
            return null;
        }
        if (n == 0 ){
            return cur1;
        }
        // 把长链接设置为cur1
        cur1 = n > 0 ? head1 : head2;
        cur2 = n > 0 ? head2 : head1;
        n = Math.abs(n);
        while (n > 0){
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个链接都有环
     * 1.入环节点相同
     *   把入环节点当作终点，则该情况和无环节点的逻辑一样。判断最终节点是否相等，然后快慢针找到第一个相交的节点
     * 2。入环节点不同
     *   2.1 两个链表有各自的环，不想交
     *   2.2 两个链表入环节点不一样，相交，两个入环节点随便取哪个都是相交的节点
     * @param head1
     * @param head2
     * @return
     */
    public static Node bothLoop(Node head1, Node loop1,Node head2,Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 :head2;
            cur2 = n > 0 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0){
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            // 如果一个链表的环转一圈了，依旧没有找到相同的loop2节点，那么两个链表不相交
            Node cur = loop1.next;
            while (cur != loop1){
                if (cur == loop2){
                    return cur;
                }
                cur = cur.next;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
//        head1.next.next = new  Node(3);
//        head1.next.next.next = new  Node(4);
//        head1.next.next.next.next = new  Node(5);
//        head1.next.next.next.next.next = new  Node(6);
//        head1.next.next.next.next.next.next = new  Node(7);
//
//        // 0->9->8->6->7->null
         Node head2 = new  Node(0);
//        head2.next = new  Node(9);
//        head2.next.next = new  Node(8);
//        head2.next.next.next = head1.next.next.next.next.next; // 8->6
//        System.out.println(getInsertNode(head1, head2).value);

//         1->2->3->4->5->6->7->4...
        head1 = new  Node(1);
        head1.next = new  Node(2);
        head1.next.next = new  Node(3);
        head1.next.next.next = new  Node(4);
        head1.next.next.next.next = new  Node(5);
        head1.next.next.next.next.next = new  Node(6);
        head1.next.next.next.next.next.next = new  Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

//         0->9->8->2...
        head2 = new  Node(0);
        head2.next = new  Node(9);
        head2.next.next = new  Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getInsertNode(head1, head2).value);

//        // 0->9->8->6->4->5->6..
        head2 = new  Node(0);
        head2.next = new  Node(9);
        head2.next.next = new  Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getInsertNode(head1, head2).value);
    }
}
