package class13;

import java.util.LinkedList;

// 测试链接 : https://leetcode.com/problems/check-completeness-of-a-binary-tree/

public class Code01_IsCBT {

	// 不要提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int v) {
			val = v;
		}
	}

	public static boolean isCompleteTree1(TreeNode head) {
		if (head == null) {
			return true;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		// 是否遇到过左右两个孩子不双全的节点
		boolean leaf = false;
		TreeNode l = null;
		TreeNode r = null;
		queue.add(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			if (
			// 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
			(leaf && (l != null || r != null)) || (l == null && r != null)

			) {
				return false;
			}
			if (l != null) {
				queue.add(l);
			}
			if (r != null) {
				queue.add(r);
			}
			if (l == null || r == null) {
				leaf = true;
			}
		}
		return true;
	}

	public static boolean isCompleteTree2(TreeNode head) {
		return process(head).isCBT;
	}

	public static class Info {
		public boolean isFull;
		public boolean isCBT;
		public int height;

		public Info(boolean full, boolean cbt, int h) {
			isFull = full;
			isCBT = cbt;
			height = h;
		}
	}

	public static Info process(TreeNode x) {
		if (x == null) {
			return new Info(true, true, 0);
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		boolean isCBT = false;
		if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
			isCBT = true;
		} else if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
			isCBT = true;
		} else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
			isCBT = true;
		} else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
			isCBT = true;
		}
		return new Info(isFull, isCBT, height);
	}

	// for test
	public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		TreeNode head = new TreeNode((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomBST(maxLevel, maxValue);
			if (isCompleteTree1(head) != isCompleteTree2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
