package class05;

import java.util.Stack;

public class Code03_QuickSortRecursiveAndUnrecursive {

	// 荷兰国旗问题
	public static int[] netherlandsFlag(int[] arr, int L, int R) {
		if (L > R) {
			return new int[] { -1, -1 };
		}
		if (L == R) {
			return new int[] { L, R };
		}
		int less = L - 1;
		int more = R;
		int index = L;
		while (index < more) {
			if (arr[index] == arr[R]) {
				index++;
			} else if (arr[index] < arr[R]) {
				swap(arr, index++, ++less);
			} else {
				swap(arr, index, --more);
			}
		}
		swap(arr, more, R);
		return new int[] { less + 1, more };
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 快排递归版本
	public static void quickSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	public static void process(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
		int[] equalArea = netherlandsFlag(arr, L, R);
		process(arr, L, equalArea[0] - 1);
		process(arr, equalArea[1] + 1, R);
	}

	// 快排非递归版本需要的辅助类
	// 要处理的是什么范围上的排序
	public static class Op {
		public int l;
		public int r;

		public Op(int left, int right) {
			l = left;
			r = right;
		}
	}

	// 快排3.0 非递归版本
	public static void quickSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int N = arr.length;
		swap(arr, (int) (Math.random() * N), N - 1);
		int[] equalArea = netherlandsFlag(arr, 0, N - 1);
		int el = equalArea[0];
		int er = equalArea[1];
		Stack<Op> stack = new Stack<>();
		stack.push(new Op(0, el - 1));
		stack.push(new Op(er + 1, N - 1));
		while (!stack.isEmpty()) {
			Op op = stack.pop(); // op.l  ... op.r
			if (op.l < op.r) {
				swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
				equalArea = netherlandsFlag(arr, op.l, op.r);
				el = equalArea[0];
				er = equalArea[1];
				stack.push(new Op(op.l, el - 1));
				stack.push(new Op(er + 1, op.r));
			}
		}
	}

	// 生成随机数组（用于测试）
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// 拷贝数组（用于测试）
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// 对比两个数组（用于测试）
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// 打印数组（用于测试）
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 跑大样本随机测试（对数器）
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			quickSort1(arr1);
			quickSort2(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println("test end");
		System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
	}

}
