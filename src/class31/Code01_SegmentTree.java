package class31;

public class Code01_SegmentTree {

	public static class SegmentTree {
		// arr[]为原序列的信息从0开始，但在arr里是从1开始的
		// sum[]模拟线段树维护区间和
		// lazy[]为累加和懒惰标记
		// change[]为更新的值
		// update[]为更新慵懒标记
		private int MAXN;
		private int[] arr;
		private int[] sum;
		private int[] lazy;
		private int[] change;
		private boolean[] update;

		public SegmentTree(int[] origin) {
			MAXN = origin.length + 1;
			arr = new int[MAXN]; // arr[0] 不用 从1开始使用
			for (int i = 1; i < MAXN; i++) {
				arr[i] = origin[i - 1];
			}
			sum = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围的累加和信息
			lazy = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围沒有往下傳遞的纍加任務
			change = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围有没有更新操作的任务
			update = new boolean[MAXN << 2]; // 用来支持脑补概念中，某一个范围更新任务，更新成了什么
		}

		private void pushUp(int rt) {
			sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
		}

		// 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
		// 分发策略是什么
		// ln表示左子树元素结点个数，rn表示右子树结点个数
		private void pushDown(int rt, int ln, int rn) {
			if (update[rt]) {
				update[rt << 1] = true;
				update[rt << 1 | 1] = true;
				change[rt << 1] = change[rt];
				change[rt << 1 | 1] = change[rt];
				lazy[rt << 1] = 0;
				lazy[rt << 1 | 1] = 0;
				sum[rt << 1] = change[rt] * ln;
				sum[rt << 1 | 1] = change[rt] * rn;
				update[rt] = false;
			}
			if (lazy[rt] != 0) {
				lazy[rt << 1] += lazy[rt];
				sum[rt << 1] += lazy[rt] * ln;
				lazy[rt << 1 | 1] += lazy[rt];
				sum[rt << 1 | 1] += lazy[rt] * rn;
				lazy[rt] = 0;
			}
		}

		// 在初始化阶段，先把sum数组，填好
		// 在arr[l~r]范围上，去build，1~N，
		// rt : 这个范围在sum中的下标
		public void build(int l, int r, int rt) {
			if (l == r) {
				sum[rt] = arr[l];
				return;
			}
			int mid = (l + r) >> 1;
			build(l, mid, rt << 1);
			build(mid + 1, r, rt << 1 | 1);
			pushUp(rt);
		}

		
		// L~R  所有的值变成C
		// l~r  rt
		public void update(int L, int R, int C, int l, int r, int rt) {
			if (L <= l && r <= R) {
				update[rt] = true;
				change[rt] = C;
				sum[rt] = C * (r - l + 1);
				lazy[rt] = 0;
				return;
			}
			// 当前任务躲不掉，无法懒更新，要往下发
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			if (L <= mid) {
				update(L, R, C, l, mid, rt << 1);
			}
			if (R > mid) {
				update(L, R, C, mid + 1, r, rt << 1 | 1);
			}
			pushUp(rt);
		}

		// L~R, C 任务！
		// rt，l~r
		public void add(int L, int R, int C, int l, int r, int rt) {
			// 任务如果把此时的范围全包了！
			if (L <= l && r <= R) {
				sum[rt] += C * (r - l + 1);
				lazy[rt] += C;
				return;
			}
			// 任务没有把你全包！
			// l  r  mid = (l+r)/2
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			// L~R
			if (L <= mid) {
				add(L, R, C, l, mid, rt << 1);
			}
			if (R > mid) {
				add(L, R, C, mid + 1, r, rt << 1 | 1);
			}
			pushUp(rt);
		}

		// 1~6 累加和是多少？ 1~8 rt
		public long query(int L, int R, int l, int r, int rt) {
			if (L <= l && r <= R) {
				return sum[rt];
			}
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			long ans = 0;
			if (L <= mid) {
				ans += query(L, R, l, mid, rt << 1);
			}
			if (R > mid) {
				ans += query(L, R, mid + 1, r, rt << 1 | 1);
			}
			return ans;
		}

	}

	public static class Right {
		public int[] arr;

		public Right(int[] origin) {
			arr = new int[origin.length + 1];
			for (int i = 0; i < origin.length; i++) {
				arr[i + 1] = origin[i];
			}
		}

		public void update(int L, int R, int C) {
			for (int i = L; i <= R; i++) {
				arr[i] = C;
			}
		}

		public void add(int L, int R, int C) {
			for (int i = L; i <= R; i++) {
				arr[i] += C;
			}
		}

		public long query(int L, int R) {
			long ans = 0;
			for (int i = L; i <= R; i++) {
				ans += arr[i];
			}
			return ans;
		}

	}

	public static int[] genarateRandomArray(int len, int max) {
		int size = (int) (Math.random() * len) + 1;
		int[] origin = new int[size];
		for (int i = 0; i < size; i++) {
			origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return origin;
	}

	public static boolean test() {
		int len = 100;
		int max = 1000;
		int testTimes = 5000;
		int addOrUpdateTimes = 1000;
		int queryTimes = 500;
		for (int i = 0; i < testTimes; i++) {
			int[] origin = genarateRandomArray(len, max);
			SegmentTree seg = new SegmentTree(origin);
			int S = 1;
			int N = origin.length;
			int root = 1;
			seg.build(S, N, root);
			Right rig = new Right(origin);
			for (int j = 0; j < addOrUpdateTimes; j++) {
				int num1 = (int) (Math.random() * N) + 1;
				int num2 = (int) (Math.random() * N) + 1;
				int L = Math.min(num1, num2);
				int R = Math.max(num1, num2);
				int C = (int) (Math.random() * max) - (int) (Math.random() * max);
				if (Math.random() < 0.5) {
					seg.add(L, R, C, S, N, root);
					rig.add(L, R, C);
				} else {
					seg.update(L, R, C, S, N, root);
					rig.update(L, R, C);
				}
			}
			for (int k = 0; k < queryTimes; k++) {
				int num1 = (int) (Math.random() * N) + 1;
				int num2 = (int) (Math.random() * N) + 1;
				int L = Math.min(num1, num2);
				int R = Math.max(num1, num2);
				long ans1 = seg.query(L, R, S, N, root);
				long ans2 = rig.query(L, R);
				if (ans1 != ans2) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[] origin = { 2, 1, 1, 2, 3, 4, 5 };
		SegmentTree seg = new SegmentTree(origin);
		int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
		int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
		int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
		int L = 2; // 操作区间的开始位置 -> 可变
		int R = 5; // 操作区间的结束位置 -> 可变
		int C = 4; // 要加的数字或者要更新的数字 -> 可变
		// 区间生成，必须在[S,N]整个范围上build
		seg.build(S, N, root);
		// 区间修改，可以改变L、R和C的值，其他值不可改变
		seg.add(L, R, C, S, N, root);
		// 区间更新，可以改变L、R和C的值，其他值不可改变
		seg.update(L, R, C, S, N, root);
		// 区间查询，可以改变L和R的值，其他值不可改变
		long sum = seg.query(L, R, S, N, root);
		System.out.println(sum);

		System.out.println("对数器测试开始...");
		System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

	}

}
