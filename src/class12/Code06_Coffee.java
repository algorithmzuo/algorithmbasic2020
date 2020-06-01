package class12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 题目
// 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
// 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
// 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
// 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
// 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
// 四个参数：arr, n, a, b
// 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
public class Code06_Coffee {

	// 方法一：暴力尝试方法
	public static int minTime1(int[] arr, int n, int a, int b) {
		int[] times = new int[arr.length];
		int[] drink = new int[n];
		return forceMake(arr, times, 0, drink, n, a, b);
	}

	// 方法一，每个人暴力尝试用每一个咖啡机给自己做咖啡
	public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
		if (kth == n) {
			int[] drinkSorted = Arrays.copyOf(drink, kth);
			Arrays.sort(drinkSorted);
			return forceWash(drinkSorted, a, b, 0, 0, 0);
		}
		int time = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int work = arr[i];
			int pre = times[i];
			drink[kth] = pre + work;
			times[i] = pre + work;
			time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
			drink[kth] = 0;
			times[i] = pre;
		}
		return time;
	}

	// 方法一，暴力尝试洗咖啡杯的方式
	public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
		if (index == drinks.length) {
			return time;
		}
		// 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
		int wash = Math.max(drinks[index], washLine) + a;
		int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

		// 选择二：当前index号咖啡杯，选择自然挥发
		int dry = drinks[index] + b;
		int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
		return Math.min(ans1, ans2);
	}

	// 方法二：稍微好一点的解法
	public static class Machine {
		public int timePoint;
		public int workTime;

		public Machine(int t, int w) {
			timePoint = t;
			workTime = w;
		}
	}

	public static class MachineComparator implements Comparator<Machine> {

		@Override
		public int compare(Machine o1, Machine o2) {
			return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
		}

	}

	// 方法二，每个人暴力尝试用每一个咖啡机给自己做咖啡，优化成贪心
	public static int minTime2(int[] arr, int n, int a, int b) {
		PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
		for (int i = 0; i < arr.length; i++) {
			heap.add(new Machine(0, arr[i]));
		}
		int[] drinks = new int[n];
		for (int i = 0; i < n; i++) {
			Machine cur = heap.poll();
			cur.timePoint += cur.workTime;
			drinks[i] = cur.timePoint;
			heap.add(cur);
		}
		return process(drinks, a, b, 0, 0);
	}

	// 方法二，洗咖啡杯的方式和原来一样，只是这个暴力版本减少了一个可变参数

	// process(drinks, 3, 10, 0,0)
	// a 洗一杯的时间 固定变量
	// b 自己挥发干净的时间 固定变量
	// drinks 每一个员工喝完的时间 固定变量
	// drinks[0..index-1]都已经干净了，不用你操心了
	// drinks[index...]都想变干净，这是我操心的，washLine表示洗的机器何时可用
	// drinks[index...]变干净，最少的时间点返回
	public static int process(int[] drinks, int a, int b, int index, int washLine) {
		if (index == drinks.length - 1) {
			return Math.min(Math.max(washLine, drinks[index]) + a, drinks[index] + b);
		}
		// 剩不止一杯咖啡
		// wash是我当前的咖啡杯，洗完的时间
		int wash = Math.max(washLine, drinks[index]) + a;// 洗，index一杯，结束的时间点
		// index+1...变干净的最早时间
		int next1 = process(drinks, a, b, index + 1, wash);
		// index....
		int p1 = Math.max(wash, next1);
		int dry = drinks[index] + b; // 挥发，index一杯，结束的时间点
		int next2 = process(drinks, a, b, index + 1, washLine);
		int p2 = Math.max(dry, next2);
		return Math.min(p1, p2);
	}

	public static int dp(int[] drinks, int a, int b) {
		if (a >= b) {
			return drinks[drinks.length - 1] + b;
		}
		// a < b
		int N = drinks.length;
		int limit = 0; // 咖啡机什么时候可用
		for (int i = 0; i < N; i++) {
			limit = Math.max(limit, drinks[i]) + a;
		}
		int[][] dp = new int[N][limit + 1];
		// N-1行，所有的值
		for (int washLine = 0; washLine <= limit; washLine++) {
			dp[N - 1][washLine] = Math.min(Math.max(washLine, drinks[N - 1]) + a, drinks[N - 1] + b);
		}
		for (int index = N - 2; index >= 0; index--) {
			for (int washLine = 0; washLine <= limit; washLine++) {
				int p1 = Integer.MAX_VALUE;
				int wash = Math.max(washLine, drinks[index]) + a;
				if (wash <= limit) {
					p1 = Math.max(wash, dp[index + 1][wash]);
				}
				int p2 = Math.max(drinks[index] + b, dp[index + 1][washLine]);
				dp[index][washLine] = Math.min(p1, p2);
			}
		}
		return dp[0][0];
	}

	// 方法三：最终版本，把方法二洗咖啡杯的暴力尝试进一步优化成动态规划
	public static int minTime3(int[] arr, int n, int a, int b) {
		PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
		for (int i = 0; i < arr.length; i++) {
			heap.add(new Machine(0, arr[i]));
		}
		int[] drinks = new int[n];
		for (int i = 0; i < n; i++) {
			Machine cur = heap.poll();
			cur.timePoint += cur.workTime;
			drinks[i] = cur.timePoint;
			heap.add(cur);
		}
		if (a >= b) {
			return drinks[n - 1] + b;
		}
		int[][] dp = new int[n][drinks[n - 1] + n * a];
		for (int i = 0; i < dp[0].length; i++) {
			dp[n - 1][i] = Math.min(Math.max(i, drinks[n - 1]) + a, drinks[n - 1] + b);
		}
		for (int row = n - 2; row >= 0; row--) { // row 咖啡杯的编号
			int washLine = drinks[row] + (row + 1) * a;
			for (int col = 0; col < washLine; col++) {
				int wash = Math.max(col, drinks[row]) + a;
				dp[row][col] = Math.min(Math.max(wash, dp[row + 1][wash]), Math.max(drinks[row] + b, dp[row + 1][col]));
			}
		}
		return dp[0][0];
	}

	// for test
	public static int[] randomArray(int len, int max) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		System.out.print("arr : ");
		for (int j = 0; j < arr.length; j++) {
			System.out.print(arr[j] + ", ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] test = { 1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15 };
		int a1 = 3;
		int b1 = 10;
		System.out.println(process(test, a1, b1, 0, 0));
		System.out.println(dp(test, a1, b1));

		int len = 5;
		int max = 9;
		int testTime = 50000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(len, max);
			int n = (int) (Math.random() * 5) + 1;
			int a = (int) (Math.random() * 5) + 1;
			int b = (int) (Math.random() * 10) + 1;
			int ans1 = minTime1(arr, n, a, b);
			int ans2 = minTime2(arr, n, a, b);
			int ans3 = minTime3(arr, n, a, b);
			if (ans1 != ans2 || ans2 != ans3) {
				printArray(arr);
				System.out.println("n : " + n);
				System.out.println("a : " + a);
				System.out.println("b : " + b);
				System.out.println(ans1 + " , " + ans2 + " , " + ans3);
				System.out.println("===============");
				break;
			}
		}

	}

}
