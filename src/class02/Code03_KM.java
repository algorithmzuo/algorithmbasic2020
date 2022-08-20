package class02;

import java.util.HashMap;
import java.util.HashSet;

// 输入一定能够保证，数组中所有的数都出现了M次，只有一种数出现了K次
// 1 <= K < M
// 返回这种数
public class Code03_KM {

	public static int test(int[] arr, int k, int m) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : arr) {
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}
		}
		int ans = 0;
		for (int num : map.keySet()) {
			if (map.get(num) == k) {
				ans = num;
				break;
			}
		}
		return ans;
	}

	public static HashMap<Integer, Integer> map = new HashMap<>();

	// 请保证arr中，只有一种数出现了K次，其他数都出现了M次
	public static int onlyKTimes(int[] arr, int k, int m) {
		if (map.size() == 0) {
			mapCreater(map);
		}
		int[] t = new int[32];
		// t[0] 0位置的1出现了几个
		// t[i] i位置的1出现了几个
		for (int num : arr) {
			while (num != 0) {
				int rightOne = num & (-num);
				t[map.get(rightOne)]++;
				num ^= rightOne;
			}
		}
		int ans = 0;
		// 如果这个出现了K次的数，就是0
		// 那么下面代码中的 : ans |= (1 << i);
		// 就不会发生
		// 那么ans就会一直维持0，最后返回0，也是对的！
		for (int i = 0; i < 32; i++) {
			if (t[i] % m != 0) {
				ans |= (1 << i);
			}
		}
		return ans;
	}

	public static void mapCreater(HashMap<Integer, Integer> map) {
		int value = 1;
		for (int i = 0; i < 32; i++) {
			map.put(value, i);
			value <<= 1;
		}
	}

	// 更简洁的写法
	public static int km(int[] arr, int k, int m) {
		int[] help = new int[32];
		for (int num : arr) {
			for (int i = 0; i < 32; i++) {
				help[i] += (num >> i) & 1;
			}
		}
		int ans = 0;
		for (int i = 0; i < 32; i++) {
			help[i] %= m;
			if (help[i] != 0) {
				ans |= 1 << i;
			}
		}
		return ans;
	}

	// 为了测试
	public static int[] randomArray(int maxKinds, int range, int k, int m) {
		int ktimeNum = randomNumber(range);
		// 真命天子出现的次数
		int times = k;
		// 2
		int numKinds = (int) (Math.random() * maxKinds) + 2;
		// k * 1 + (numKinds - 1) * m
		int[] arr = new int[times + (numKinds - 1) * m];
		int index = 0;
		for (; index < times; index++) {
			arr[index] = ktimeNum;
		}
		numKinds--;
		HashSet<Integer> set = new HashSet<>();
		set.add(ktimeNum);
		while (numKinds != 0) {
			int curNum = 0;
			do {
				curNum = randomNumber(range);
			} while (set.contains(curNum));
			set.add(curNum);
			numKinds--;
			for (int i = 0; i < m; i++) {
				arr[index++] = curNum;
			}
		}
		// arr 填好了
		for (int i = 0; i < arr.length; i++) {
			// i 位置的数，我想随机和j位置的数做交换
			int j = (int) (Math.random() * arr.length);// 0 ~ N-1
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
		return arr;
	}

	// 为了测试
	// [-range, +range]
	public static int randomNumber(int range) {
		return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
	}

	// 为了测试
	public static void main(String[] args) {
		int kinds = 5;
		int range = 30;
		int testTime = 100000;
		int max = 9;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
			int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
			int k = Math.min(a, b);
			int m = Math.max(a, b);
			// k < m
			if (k == m) {
				m++;
			}
			int[] arr = randomArray(kinds, range, k, m);
			int ans1 = test(arr, k, m);
			int ans2 = onlyKTimes(arr, k, m);
			int ans3 = km(arr, k, m);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println(ans1);
				System.out.println(ans3);
				System.out.println("出错了！");
			}
		}
		System.out.println("测试结束");
	}

}
