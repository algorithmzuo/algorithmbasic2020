package class27;

import java.util.ArrayList;

public class Code02_TreeEqual {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int v) {
			val = v;
		}
	}

	// 测试链接 : https://leetcode.cn/problems/subtree-of-another-tree/
	// 提交如下代码可以直接通过
	public static boolean isSubtree(TreeNode big, TreeNode small) {
		if (small == null) {
			return true;
		}
		if (big == null) {
			return false;
		}
		ArrayList<String> b = preSerial(big);
		ArrayList<String> s = preSerial(small);
		String[] str = new String[b.size()];
		for (int i = 0; i < str.length; i++) {
			str[i] = b.get(i);
		}

		String[] match = new String[s.size()];
		for (int i = 0; i < match.length; i++) {
			match[i] = s.get(i);
		}
		return getIndexOf(str, match) != -1;
	}

	public static ArrayList<String> preSerial(TreeNode head) {
		ArrayList<String> ans = new ArrayList<>();
		pres(head, ans);
		return ans;
	}

	public static void pres(TreeNode head, ArrayList<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.val));
			pres(head.left, ans);
			pres(head.right, ans);
		}
	}

	public static int getIndexOf(String[] str1, String[] str2) {
		if (str1 == null || str2 == null || str1.length < 1 || str1.length < str2.length) {
			return -1;
		}
		int x = 0;
		int y = 0;
		int[] next = getNextArray(str2);
		while (x < str1.length && y < str2.length) {
			if (isEqual(str1[x], str2[y])) {
				x++;
				y++;
			} else if (next[y] == -1) {
				x++;
			} else {
				y = next[y];
			}
		}
		return y == str2.length ? x - y : -1;
	}

	public static int[] getNextArray(String[] ms) {
		if (ms.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		int cn = 0;
		while (i < next.length) {
			if (isEqual(ms[i - 1], ms[cn])) {
				next[i++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
	}

	public static boolean isEqual(String a, String b) {
		if (a == null && b == null) {
			return true;
		} else {
			if (a == null || b == null) {
				return false;
			} else {
				return a.equals(b);
			}
		}
	}

}
