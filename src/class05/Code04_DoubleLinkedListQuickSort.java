package class05;

import java.util.ArrayList;
import java.util.Comparator;

// 双向链表的随机快速排序
// 课上没有讲，因为这是群里同学问的问题
// 作为补充放在这，有需要的同学可以看看
// 和课上讲的数组的经典快速排序在算法上没有区别
// 但是coding需要更小心
public class Code04_DoubleLinkedListQuickSort {

	public static class Node {
		public int value;
		public Node last;
		public Node next;

		public Node(int v) {
			value = v;
		}
	}

	public static Node quickSort(Node h) {
		if (h == null) {
			return null;
		}
		int N = 0;
		Node c = h;
		Node e = null;
		while (c != null) {
			N++;
			e = c;
			c = c.next;
		}
		return process(h, e, N).h;
	}

	public static class HeadTail {
		public Node h;
		public Node t;

		public HeadTail(Node head, Node tail) {
			h = head;
			t = tail;
		}
	}

	// L...R是一个双向链表的头和尾,
	// L的last指针指向null，R的next指针指向null
	// 也就是说L的左边没有，R的右边也没节点
	// 就是一个正常的双向链表，一共有N个节点
	// 将这一段用随机快排的方式排好序
	// 返回排好序之后的双向链表的头和尾(HeadTail)
	public static HeadTail process(Node L, Node R, int N) {
		if (L == null) {
			return null;
		}
		if (L == R) {
			return new HeadTail(L, R);
		}
		// L..R上不只一个节点
		// 随机得到一个随机下标
		int randomIndex = (int) (Math.random() * N);
		// 根据随机下标得到随机节点
		Node randomNode = L;
		while (randomIndex-- != 0) {
			randomNode = randomNode.next;
		}
		// 把随机节点从原来的环境里分离出来
		// 比如 a(L) -> b -> c -> d(R), 如果randomNode = c，那么调整之后
		// a(L) -> b -> d(R), c会被挖出来，randomNode = c
		if (randomNode == L || randomNode == R) {
			if (randomNode == L) {
				L = randomNode.next;
				L.last = null;
			} else {
				randomNode.last.next = null;
			}
		} else { // randomNode一定是中间的节点
			randomNode.last.next = randomNode.next;
			randomNode.next.last = randomNode.last;
		}
		randomNode.last = null;
		randomNode.next = null;
		Info info = partition(L, randomNode);
		// <randomNode的部分去排序
		HeadTail lht = process(info.lh, info.lt, info.ls);
		// >randomNode的部分去排序
		HeadTail rht = process(info.rh, info.rt, info.rs);
		// 左部分排好序、右部分排好序
		// 把它们串在一起
		if (lht != null) {
			lht.t.next = info.eh;
			info.eh.last = lht.t;
		}
		if (rht != null) {
			info.et.next = rht.h;
			rht.h.last = info.et;
		}
		// 返回排好序之后总的头和总的尾
		Node h = lht != null ? lht.h : info.eh;
		Node t = rht != null ? rht.t : info.et;
		return new HeadTail(h, t);
	}

	public static class Info {
		public Node lh;
		public Node lt;
		public int ls;
		public Node rh;
		public Node rt;
		public int rs;
		public Node eh;
		public Node et;

		public Info(Node lH, Node lT, int lS, Node rH, Node rT, int rS, Node eH, Node eT) {
			lh = lH;
			lt = lT;
			ls = lS;
			rh = rH;
			rt = rT;
			rs = rS;
			eh = eH;
			et = eT;
		}
	}

	// (L....一直到空)，是一个双向链表
	// pivot是一个不在(L....一直到空)的独立节点，它作为划分值
	// 根据荷兰国旗问题的划分方式，把(L....一直到空)划分成:
	// <pivot 、 =pivot 、 >pivot 三个部分，然后把pivot融进=pivot的部分
	// 比如 4(L)->6->7->1->5->0->9->null pivot=5(这个5和链表中的5，是不同的节点)
	// 调整完成后:
	// 4->1->0 小于的部分
	// 5->5 等于的部分
	// 6->7->9 大于的部分
	// 三个部分是断开的
	// 然后返回Info：
	// 小于部分的头、尾、节点个数 : lh,lt,ls
	// 大于部分的头、尾、节点个数 : rh,rt,rs
	// 等于部分的头、尾 : eh,et
	public static Info partition(Node L, Node pivot) {
		Node lh = null;
		Node lt = null;
		int ls = 0;
		Node rh = null;
		Node rt = null;
		int rs = 0;
		Node eh = pivot;
		Node et = pivot;
		Node tmp = null;
		while (L != null) {
			tmp = L.next;
			L.next = null;
			L.last = null;
			if (L.value < pivot.value) {
				ls++;
				if (lh == null) {
					lh = L;
					lt = L;
				} else {
					lt.next = L;
					L.last = lt;
					lt = L;
				}
			} else if (L.value > pivot.value) {
				rs++;
				if (rh == null) {
					rh = L;
					rt = L;
				} else {
					rt.next = L;
					L.last = rt;
					rt = L;
				}
			} else {
				et.next = L;
				L.last = et;
				et = L;
			}
			L = tmp;
		}
		return new Info(lh, lt, ls, rh, rt, rs, eh, et);
	}

	// 为了测试
	public static class NodeComp implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.value - o2.value;
		}

	}

	// 为了测试
	public static Node sort(Node head) {
		if (head == null) {
			return null;
		}
		ArrayList<Node> arr = new ArrayList<>();
		while (head != null) {
			arr.add(head);
			head = head.next;
		}
		arr.sort(new NodeComp());
		Node h = arr.get(0);
		h.last = null;
		Node p = h;
		for (int i = 1; i < arr.size(); i++) {
			Node c = arr.get(i);
			p.next = c;
			c.last = p;
			c.next = null;
			p = c;
		}
		return h;
	}

	// 为了测试
	public static Node generateRandomDoubleLinkedList(int n, int v) {
		if (n == 0) {
			return null;
		}
		Node[] arr = new Node[n];
		for (int i = 0; i < n; i++) {
			arr[i] = new Node((int) (Math.random() * v));
		}
		Node head = arr[0];
		Node pre = head;
		for (int i = 1; i < n; i++) {
			pre.next = arr[i];
			arr[i].last = pre;
			pre = arr[i];
		}
		return head;
	}

	// 为了测试
	public static Node cloneDoubleLinkedList(Node head) {
		if (head == null) {
			return null;
		}
		Node h = new Node(head.value);
		Node p = h;
		head = head.next;
		while (head != null) {
			Node c = new Node(head.value);
			p.next = c;
			c.last = p;
			p = c;
			head = head.next;
		}
		return h;
	}

	// 为了测试
	public static boolean equal(Node h1, Node h2) {
		return doubleLinkedListToString(h1).equals(doubleLinkedListToString(h2));
	}

	// 为了测试
	public static String doubleLinkedListToString(Node head) {
		Node cur = head;
		Node end = null;
		StringBuilder builder = new StringBuilder();
		while (cur != null) {
			builder.append(cur.value + " ");
			end = cur;
			cur = cur.next;
		}
		builder.append("| ");
		while (end != null) {
			builder.append(end.value + " ");
			end = end.last;
		}
		return builder.toString();
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 500;
		int V = 500;
		int testTime = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int size = (int) (Math.random() * N);
			Node head1 = generateRandomDoubleLinkedList(size, V);
			Node head2 = cloneDoubleLinkedList(head1);
			Node sort1 = quickSort(head1);
			Node sort2 = sort(head2);
			if (!equal(sort1, sort2)) {
				System.out.println("出错了!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
