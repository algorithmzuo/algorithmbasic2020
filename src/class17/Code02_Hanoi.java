package class17;

import java.util.HashSet;
import java.util.Stack;

public class Code02_Hanoi {

	public static void hanoi1(int n) {
		leftToRight(n);
	}

	// 请把1~N层圆盘 从左 -> 右
	public static void leftToRight(int n) {
		if (n == 1) { // base case
			System.out.println("Move 1 from left to right");
			return;
		}
		leftToMid(n - 1);
		System.out.println("Move " + n + " from left to right");
		midToRight(n - 1);
	}

	// 请把1~N层圆盘 从左 -> 中
	public static void leftToMid(int n) {
		if (n == 1) {
			System.out.println("Move 1 from left to mid");
			return;
		}
		leftToRight(n - 1);
		System.out.println("Move " + n + " from left to mid");
		rightToMid(n - 1);
	}

	public static void rightToMid(int n) {
		if (n == 1) {
			System.out.println("Move 1 from right to mid");
			return;
		}
		rightToLeft(n - 1);
		System.out.println("Move " + n + " from right to mid");
		leftToMid(n - 1);
	}

	public static void midToRight(int n) {
		if (n == 1) {
			System.out.println("Move 1 from mid to right");
			return;
		}
		midToLeft(n - 1);
		System.out.println("Move " + n + " from mid to right");
		leftToRight(n - 1);
	}

	public static void midToLeft(int n) {
		if (n == 1) {
			System.out.println("Move 1 from mid to left");
			return;
		}
		midToRight(n - 1);
		System.out.println("Move " + n + " from mid to left");
		rightToLeft(n - 1);
	}

	public static void rightToLeft(int n) {
		if (n == 1) {
			System.out.println("Move 1 from right to left");
			return;
		}
		rightToMid(n - 1);
		System.out.println("Move " + n + " from right to left");
		midToLeft(n - 1);
	}

	public static void hanoi2(int n) {
		if (n > 0) {
			func(n, "left", "right", "mid");
		}
	}

	public static void func(int N, String from, String to, String other) {
		if (N == 1) { // base
			System.out.println("Move 1 from " + from + " to " + to);
		} else {
			func(N - 1, from, other, to);
			System.out.println("Move " + N + " from " + from + " to " + to);
			func(N - 1, other, to, from);
		}
	}

	public static class Record {
		public int level;
		public String from;
		public String to;
		public String other;

		public Record(int l, String f, String t, String o) {
			level = l;
			from = f;
			to = t;
			other = o;
		}
	}

	// 之前的迭代版本，很多同学表示看不懂
	// 所以我换了一个更容易理解的版本
	// 看注释吧！好懂！
	// 你把汉诺塔问题想象成二叉树
	// 比如当前还剩i层，其实打印这个过程就是：
	// 1) 去打印第一部分 -> 左子树
	// 2) 打印当前的动作 -> 当前节点
	// 3) 去打印第二部分 -> 右子树
	// 那么你只需要记录每一个任务 : 有没有加入过左子树的任务
	// 就可以完成迭代对递归的替代了
	public static void hanoi3(int N) {
		if (N < 1) {
			return;
		}
		// 每一个记录进栈
		Stack<Record> stack = new Stack<>();
		// 记录每一个记录有没有加入过左子树的任务
		HashSet<Record> finishLeft = new HashSet<>();
		// 初始的任务，认为是种子
		stack.add(new Record(N, "left", "right", "mid"));
		while (!stack.isEmpty()) {
			// 弹出当前任务
			Record cur = stack.pop();
			if (cur.level == 1) {
				// 如果层数只剩1了
				// 直接打印
				System.out.println("Move 1 from " + cur.from + " to " + cur.to);
			} else {
				// 如果不只1层
				if (!finishLeft.contains(cur)) {
					// 如果当前任务没有加入过左子树的任务
					// 现在就要加入了！
					// 把当前的任务重新压回去，因为还不到打印的时候
					// 再加入左子树任务！
					finishLeft.add(cur);
					stack.push(cur);
					stack.push(new Record(cur.level - 1, cur.from, cur.other, cur.to));
				} else {
					// 如果当前任务加入过左子树的任务
					// 说明此时已经是第二次弹出了！
					// 说明左子树的所有打印任务都完成了
					// 当前可以打印了！
					// 然后加入右子树的任务
					// 当前的任务可以永远的丢弃了！
					// 因为完成了左子树、打印了自己、加入了右子树
					// 再也不用回到这个任务了
					System.out.println("Move " + cur.level + " from " + cur.from + " to " + cur.to);
					stack.push(new Record(cur.level - 1, cur.other, cur.to, cur.from));
				}
			}
		}
	}

	public static void main(String[] args) {
		int n = 3;
		hanoi1(n);
		System.out.println("============");
		hanoi2(n);
		System.out.println("============");
		hanoi3(n);
	}

}
