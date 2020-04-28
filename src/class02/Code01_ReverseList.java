package class02;

import java.util.ArrayList;

public class Code01_ReverseList {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			value = data;
		}
	}

	public static class DoubleNode {
		public int value;
		public DoubleNode last;
		public DoubleNode next;

		public DoubleNode(int data) {
			value = data;
		}
	}

	public static Node reverseLinkedList(Node head) {
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

	public static DoubleNode reverseDoubleList(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	}

	public static Node testReverseLinkedList(Node head) {
		if (head == null) {
			return null;
		}
		ArrayList<Node> list = new ArrayList<>();
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		list.get(0).next = null;
		int N = list.size();
		for (int i = 1; i < N; i++) {
			list.get(i).next = list.get(i - 1);
		}
		return list.get(N - 1);
	}

	public static DoubleNode testReverseDoubleList(DoubleNode head) {
		if (head == null) {
			return null;
		}
		ArrayList<DoubleNode> list = new ArrayList<>();
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		list.get(0).next = null;
		DoubleNode pre = list.get(0);
		int N = list.size();
		for (int i = 1; i < N; i++) {
			DoubleNode cur = list.get(i);
			cur.last = null;
			cur.next = pre;
			pre.last = cur;
			pre = cur;
		}
		return list.get(N - 1);
	}

	public static Node generateRandomLinkedList(int len, int value) {
		int size = (int) (Math.random() * (len + 1));
		if (size == 0) {
			return null;
		}
		size--;
		Node head = new Node((int) (Math.random() * (value + 1)));
		Node pre = head;
		while (size != 0) {
			Node cur = new Node((int) (Math.random() * (value + 1)));
			pre.next = cur;
			pre = cur;
			size--;
		}
		return head;
	}

	public static DoubleNode generateRandomDoubleList(int len, int value) {
		int size = (int) (Math.random() * (len + 1));
		if (size == 0) {
			return null;
		}
		size--;
		DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
		DoubleNode pre = head;
		while (size != 0) {
			DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
			pre.next = cur;
			cur.last = pre;
			pre = cur;
			size--;
		}
		return head;
	}

	// 要求无环，有环别用这个函数
	public static boolean checkLinkedListEqual(Node head1, Node head2) {
		while (head1 != null && head2 != null) {
			if (head1.value != head2.value) {
				return false;
			}
			head1 = head1.next;
			head2 = head2.next;
		}
		return head1 == null && head2 == null;
	}

	// 要求无环，有环别用这个函数
	public static boolean checkDoubleListEqual(DoubleNode head1, DoubleNode head2) {
		boolean null1 = head1 == null;
		boolean null2 = head2 == null;
		if (null1 && null2) {
			return true;
		}
		if (null1 ^ null2) {
			return false;
		}
		if (head1.last != null || head2.last != null) {
			return false;
		}
		DoubleNode end1 = null;
		DoubleNode end2 = null;
		while (head1 != null && head2 != null) {
			if (head1.value != head2.value) {
				return false;
			}
			end1 = head1;
			end2 = head2;
			head1 = head1.next;
			head2 = head2.next;
		}
		if (head1 != null || head2 != null) {
			return false;
		}
		while (end1 != null && end2 != null) {
			if (end1.value != end2.value) {
				return false;
			}
			end1 = end1.last;
			end2 = end2.last;
		}
		return end1 == null && end2 == null;
	}

	public static void main(String[] args) {
		int len = 50;
		int value = 100;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			Node node1 = generateRandomLinkedList(len, value);
			Node reverse1 = reverseLinkedList(node1);
			Node back1 = testReverseLinkedList(reverse1);
			if (!checkLinkedListEqual(node1, back1)) {
				System.out.println("oops!");
				break;
			}
			DoubleNode node2 = generateRandomDoubleList(len, value);
			DoubleNode reverse2 = reverseDoubleList(node2);
			DoubleNode back2 = testReverseDoubleList(reverse2);
			if (!checkDoubleListEqual(node2, back2)) {
				System.out.println("oops!");
				break;
			}
		}
		System.out.println("finish!");

	}

}
