package leo.class02;

/**
 * @author Leo
 * @ClassName DeleteGivenValue
 * @DATE 2020/11/19 2:16 下午
 * @Description 删除给定的值
 */
public class DeleteGivenValue {

    /**
     * 单向链表
     */
    public static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 双向链表
     */
    public static class DoubleNode{
        int value;
        DoubleNode pre;
        DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }


    /**
     * 功能描述 : 单链表删除某个给定值
     * @author Leo
     * @date 2020/11/19 4:44 下午
     * @param head
     * @param value
     * @return leo.class02.DeleteGivenValue.Node
     */
    public static Node removeNodeOfValue(Node head,int value) {
        if (head != null) {
            while (head != null) {
                if (head.value != value) {
                    break;
                }
                head = head.next;
            }
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == value) {
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static Node removeNodeOfValue1(Node head, int value) {
        while (head != null) {
            if (head.value != value) {
                break;
            }
            head = head.next;
        }
        Node cur = head;
        Node pre = head;
        while (cur != null) {
            if (cur.value == value) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;

    }

    /**
     * 功能描述 : 双链表删除某个给定值
     * @author Leo
     * @date 2020/11/19 4:44 下午
     * @param head
     * @param value
     * @return leo.class02.DeleteGivenValue.DoubleNode
     */
    public static DoubleNode removeDoubleNodeOfValue(DoubleNode head, int value) {
        while (head != null) {
            if (head.value != value) {
                break;
            }
            if (head.next != null) {
                head.next.pre = null;
            }
            head = head.next;

        }

        DoubleNode pre = head;
        DoubleNode cur = head;
        while (cur != null) {
            if (cur.value == value) {
                pre.next = cur.next;
                if (cur.next != null) {
                    cur.next.pre = pre;
                }
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static DoubleNode removeDoubleNodeOfValue1(DoubleNode head, int value) {
        while (head != null) {
            if (head.value != value) {
                break;
            }
            if (head.next != null) {
                head.next.pre = null;
            }
            head = head.next;
        }
        DoubleNode cur = head;
        DoubleNode pre = head;
        while (cur != null) {
            if (cur.value == value) {
                pre.next = cur.next;
                cur.pre = pre;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;

    }

    public static DoubleNode removeDoubleNodeOfValue2(DoubleNode head, int value) {
        while (head != null) {
            if (head.value != value) {
                break;
            }
            head = head.next;
        }
        if (head != null) {
            head.pre = null;
        }
        DoubleNode cur = head;
        DoubleNode pre = head;
        while (cur != null) {
            if (cur.value == value) {
                pre.next = cur.next;
                cur.pre = pre;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 功能描述 : 验证单链表删除结果
     * @author Leo
     * @date 2020/11/19 3:38 下午
     * @param head
     * @param value
     * @return boolean
     */
    public static boolean verifyRemoveNodeOfValue(Node head, int value) {
        Node cur = head;
        while (cur != null) {
            if (cur.value == value) {
                return false;
            }else{
                cur = cur.next;
            }
        }
        return true;
    }


    /**
     * 功能描述 : 验证双链表删除结果
     * @author Leo
     * @date 2020/11/19 3:40 下午
     * @param head
     * @param value
     * @return boolean
     */
    public static boolean verifyRemoveDoubleNodeOfValue(DoubleNode head, int value) {
        DoubleNode cur = head;
        while (cur != null) {
            if (cur.value == value) {
                return false;
            }else{
                cur = cur.next;
            }
        }
        return true;
    }

    /**
     * 功能描述 : 随机生成单链表
     * @author Leo
     * @date 2020/11/19 3:07 下午
     * @param sizeMax
     * @param range
     * @return leo.class02.DeleteGivenValue.Node
     */
    public static Node randomNode(int sizeMax, int range) {
        int size = (int) ((range + 1) * Math.random());
        if (size == 0) {
            return null;
        }
        Node node = new Node(randomInt(range));
        Node pre = node;
        size--;
        while (size != 0) {
            Node cur   = new Node(randomInt(range));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return node;
    }

    /**
     * 功能描述 : 随机生成双向链表
     * @author Leo
     * @date 2020/11/19 3:33 下午
     * @param sizeMax
     * @param range
     * @throw
     * @return leo.class02.DeleteGivenValue.DoubleNode
     */
    public static DoubleNode randomDoubleNode(int sizeMax, int range) {
        int size = (int) ((range + 1) * Math.random());
        if (size == 0) {
            return null;
        }
        DoubleNode head = new DoubleNode(randomInt(range));
        DoubleNode pre = head;
        size--;
        while (size != 0) {
            DoubleNode cur = new DoubleNode(randomInt(range));
            cur.pre = pre;
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    public static int randomInt(int range) {
        return (int) (((range + 1) * Math.random())-((range + 1) * Math.random()));
    }


    public static void main(String[] args) {
        int sizeMax = 40;
        int range = 80;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int value = randomInt(range);
            Node nodeHead = randomNode(sizeMax, range);
            Node node = removeNodeOfValue1(nodeHead, value);

            if (!verifyRemoveNodeOfValue(node,value)) {
                System.out.println("node fuck!");
                break;
            }
            DoubleNode doubleNodeHead = randomDoubleNode(sizeMax, range);
            DoubleNode doubleNode = removeDoubleNodeOfValue2(doubleNodeHead, value);
            if (!verifyRemoveDoubleNodeOfValue(doubleNode, value)) {
                System.out.println("doubleNode fuck");
                break;
            }

        }
        System.out.println("测试结束");

    }
}
