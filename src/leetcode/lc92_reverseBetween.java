package leetcode;


//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
//
// 说明:
//1 ≤ m ≤ n ≤ 链表长度。
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL, m = 2, n = 4
//输出: 1->4->3->2->5->NULL
// Related Topics 链表
// 👍 594 👎 0

/**
 * @author Leo
 * @ClassName lc92_reverseBetween
 * @DATE 2020/12/7 2:29 下午
 * @Description
 */
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class lc92_reverseBetween {
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if (head==null||m == n){
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null && m > 1) {
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }
        if (cur==null||cur.next == null){
            return head;
        }
        ListNode tail = cur;
        ListNode temp = pre;
        ListNode next = null;
        while (cur != null && n > 0) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            n--;
        }
        if (temp != null) {
            temp.next = pre;
        }else{
            head = pre;
        }
        tail.next = cur;
        return head;
    }


    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null && m > 1) {
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }
        ListNode preTemp = pre, next = null, tail = cur;
        while (cur != null && n > 0) {
            next = cur.next;
            cur.next = preTemp;
            preTemp = cur;
            cur = next;
        }
        if (pre != null) {
            pre.next = cur;
        }else{

        }
        tail.next = next;

        return head;
    }

}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
