package class27;

import java.util.ArrayList;

public class Code02_TreeEqual {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static boolean containsTree1(Node big, Node small) {
		if (small == null) {
			return true;
		}
		if (big == null) {
			return false;
		}
		if (isSameValueStructure(big, small)) {
			return true;
		}
		return containsTree1(big.left, small) || containsTree1(big.right, small);
	}

	public static boolean isSameValueStructure(Node head1, Node head2) {
		if (head1 == null && head2 != null) {
			return false;
		}
		if (head1 != null && head2 == null) {
			return false;
		}
		if (head1 == null && head2 == null) {
			return true;
		}
		if (head1.value != head2.value) {
			return false;
		}
		return isSameValueStructure(head1.left, head2.left) 
				&& isSameValueStructure(head1.right, head2.right);
	}

	public static boolean containsTree2(Node big, Node small) {
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

	public static ArrayList<String> preSerial(Node head) {
		ArrayList<String> ans = new ArrayList<>();
		pres(head, ans);
		return ans;
	}

	public static void pres(Node head, ArrayList<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value));
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
		int bigTreeLevel = 7;
		int smallTreeLevel = 4;
		int nodeMaxValue = 5;
		int testTimes = 100000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
			Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
			boolean ans1 = containsTree1(big, small);
			boolean ans2 = containsTree2(big, small);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish!");

	}

}
