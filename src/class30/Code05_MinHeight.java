package class30;

public class Code05_MinHeight {

	public static class Node {
		public int val;
		public Node left;
		public Node right;

		public Node(int x) {
			val = x;
		}
	}

	public static int minHeight1(Node head) {
		if (head == null) {
			return 0;
		}
		return p(head);
	}

	// 返回x为头的树，最小深度是多少
	public static int p(Node x) {
		if (x.left == null && x.right == null) {
			return 1;
		}
		// 左右子树起码有一个不为空
		int leftH = Integer.MAX_VALUE;
		if (x.left != null) {
			leftH = p(x.left);
		}
		int rightH = Integer.MAX_VALUE;
		if (x.right != null) {
			rightH = p(x.right);
		}
		return 1 + Math.min(leftH, rightH);
	}

	// 根据morris遍历改写
	public static int minHeight2(Node head) {
		if (head == null) {
			return 0;
		}
		Node cur = head;
		Node mostRight = null;
		int curLevel = 0;
		int minHeight = Integer.MAX_VALUE;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				int rightBoardSize = 1;
				while (mostRight.right != null && mostRight.right != cur) {
					rightBoardSize++;
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) { // 第一次到达
					curLevel++;
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else { // 第二次到达
					if (mostRight.left == null) {
						minHeight = Math.min(minHeight, curLevel);
					}
					curLevel -= rightBoardSize;
					mostRight.right = null;
				}
			} else { // 只有一次到达
				curLevel++;
			}
			cur = cur.right;
		}
		int finalRight = 1;
		cur = head;
		while (cur.right != null) {
			finalRight++;
			cur = cur.right;
		}
		if (cur.left == null && cur.right == null) {
			minHeight = Math.min(minHeight, finalRight);
		}
		return minHeight;
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int treeLevel = 7;
		int nodeMaxValue = 5;
		int testTimes = 100000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(treeLevel, nodeMaxValue);
			int ans1 = minHeight1(head);
			int ans2 = minHeight2(head);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish!");

	}

}
