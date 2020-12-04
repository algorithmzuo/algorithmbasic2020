package leo.class06_09;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import javax.print.attribute.standard.NumberUp;
import javax.swing.plaf.TreeUI;
import java.util.Stack;

/**
 * @author Leo
 * @ClassName IsPalindromeList
 * @DATE 2020/12/2 8:54 下午
 * @Description
 */
public class IsPalindromeList {

    public static boolean isPalindromeList(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        boolean verify = true;
        Node mid = findMid(head);
        Node midNext = mid.next;
        mid.next = null;
        midNext = reverseNode(midNext);
        Node cur = head;
        Node curReverseNode = midNext;
        while (cur != null && curReverseNode != null) {
            if (cur.value != curReverseNode.value) {
                verify = false;
                break;
            }
            cur = cur.next;
            curReverseNode = curReverseNode.next;
        }
        midNext = reverseNode(midNext);
        mid.next = midNext;
        return verify;

    }

    public static Node findMid(Node head) {
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

    public static boolean isPalindromeListByStack(Node head) {
        boolean verify = true;
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            if (stack.pop().value != cur.value) {
                verify = false;
            }
            cur = cur.next;
        }
        return verify;
    }


    public static boolean isPalindromeList1(Node head){
        if (head == null || head.next == null) {
            return false;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {
            n3  = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;
        n2 = head;
        boolean verify = true;
        while (n2 != null && n1 != null) {
            if (n2.value != n1.value) {
                verify = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }


        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return verify;
    }


    public static boolean isPalindromeList2(Node head){
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        //findMid
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        //n1 mid
        //reverseNode half node
        n2 = n1.next;   //n2 is after half head
        //cut n1 is prefix half endNode
        //and n1 is after half tail
        n1.next = null;
        //temp is next
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        //n2 is head of the flipped node
        //n1 is the last node in the after half
        n3 = n1; //save n1
        n2 = head;
        boolean verify = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {

                verify = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        //reverseNode again
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }

        return verify;

    }


    public static boolean isPalindromeList3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next;
        }
        //n1 is mid;
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;
        n2 = head;
        boolean verify = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                verify = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return verify;

    }

    public static boolean isPalindromeList4(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        //reverseNode
        //n1 is mid
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;   //n1 is right
        n2  = head; //left
        boolean verify = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                verify = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return verify;
    }

    public static boolean isPalindromeList5(Node head)  {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        //n1 is mid
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        //n1 is head after flipping the linked list ;
        //n1 and n2 end node is the same one
        //n3 temporary save n1;
        n3 = n1;
        n2 = head;
        boolean verify = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                verify = false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        //if ListNode length is Even n2 is null ,
        //if ListNode length is odd n1 and n2 both null
        n2 = n3.next;
        n3.next = null;
        //reverse again
        while (n2 != null) {
            n1 = n2.next;
            n2.next = n3;
            n3 = n2;
            n2 = n1;
        }
        return verify;
    }


    public static boolean isPalindromeList6(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        //findMid
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        //reverse half node after
        //n1 is mid
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        //n1 is head;
        n3 = n1;
        n2 = head;
        boolean verify = true;
        //compare
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                verify = false;
                break;
            }
            n1= n1.next;
            n2 = n2.next;
        }
        //reverse
        n2 = n3.next;
        n3.next = null;
        while (n2 != null) {
            n1 = n2.next;
            n2.next = n3;
            n3 = n2;
            n2 = n1;
        }
        //return
        return verify;
    }


}


class IsPalindromeListMain {

    public static void main(String[] args){
        int testTime = 1000;
        int maxSize = 5;
        int range = 100;
        boolean a;
        boolean b;
        System.out.println("start!");

        for (int i = 0; i < testTime; i++) {
            Node originNode = generateRandomNode(maxSize, range);
            Node head = copyNode(originNode);
            b = IsPalindromeList.isPalindromeListByStack(head);
            a = IsPalindromeList.isPalindromeList6(head);
            if (!isEqualsNode(head, originNode)) {
                System.out.println("not equals node!");
            }
            if (a != b) {
                System.out.println(a);
                System.out.println(b);
                System.out.println("fuck!");
            }
        }
        System.out.println("end!");

    }

    private static boolean isEqualsNode(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 != null && n2 == null) {
            return false;
        }

        if (n1 == null && n2 != null) {
            return false;
        }
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        return true;
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
