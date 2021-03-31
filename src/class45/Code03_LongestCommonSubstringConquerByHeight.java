package class45;

// 最长公共子串问题是面试常见题目之一
// 假设str1长度N，str2长度M
// 因为最优解的难度所限，一般在面试场上回答出O(N*M)的解法已经是比较优秀了
// 因为得到O(N*M)的解法，就已经需要用到动态规划了
// 但其实这个问题的最优解是O(N+M)，为了达到这个复杂度可是不容易
// 首先需要用到DC3算法得到后缀数组(sa)
// 进而用sa数组去生成height数组
// 而且在生成的时候，还有一个不回退的优化，都非常不容易理解
// 这就是后缀数组在面试算法中的地位 : 德高望重的噩梦
public class Code03_LongestCommonSubstringConquerByHeight {

	public static int lcs1(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int row = 0;
		int col = str2.length - 1;
		int max = 0;
		while (row < str1.length) {
			int i = row;
			int j = col;
			int len = 0;
			while (i < str1.length && j < str2.length) {
				if (str1[i] != str2[j]) {
					len = 0;
				} else {
					len++;
				}
				if (len > max) {
					max = len;
				}
				i++;
				j++;
			}
			if (col > 0) {
				col--;
			} else {
				row++;
			}
		}
		return max;
	}

	public static int lcs2(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length;
		int M = str2.length;
		int min = str1[0];
		int max = str1[0];
		for (int i = 1; i < N; i++) {
			min = Math.min(min, str1[i]);
			max = Math.max(max, str1[i]);
		}
		for (int i = 0; i < M; i++) {
			min = Math.min(min, str2[i]);
			max = Math.max(max, str2[i]);
		}
		int[] all = new int[N + M + 1];
		int index = 0;
		for (int i = 0; i < N; i++) {
			all[index++] = str1[i] - min + 2;
		}
		all[index++] = 1;
		for (int i = 0; i < M; i++) {
			all[index++] = str2[i] - min + 2;
		}
		DC3 dc3 = new DC3(all, max - min + 2);
		int n = all.length;
		int[] sa = dc3.sa;
		int[] height = dc3.height;
		int ans = 0;
		for (int i = 1; i < n; i++) {
			int Y = sa[i - 1];
			int X = sa[i];
			if (Math.min(X, Y) < N && Math.max(X, Y) > N) {
				ans = Math.max(ans, height[i]);
			}
		}
		return ans;
	}

	public static class DC3 {

		public int[] sa;

		public int[] rank;

		public int[] height;

		public DC3(int[] nums, int max) {
			sa = sa(nums, max);
			rank = rank();
			height = height(nums);
		}

		private int[] sa(int[] nums, int max) {
			int n = nums.length;
			int[] arr = new int[n + 3];
			for (int i = 0; i < n; i++) {
				arr[i] = nums[i];
			}
			return skew(arr, n, max);
		}

		private int[] skew(int[] nums, int n, int K) {
			int n0 = (n + 2) / 3, n1 = (n + 1) / 3, n2 = n / 3, n02 = n0 + n2;
			int[] s12 = new int[n02 + 3], sa12 = new int[n02 + 3];
			for (int i = 0, j = 0; i < n + (n0 - n1); ++i) {
				if (0 != i % 3) {
					s12[j++] = i;
				}
			}
			radixPass(nums, s12, sa12, 2, n02, K);
			radixPass(nums, sa12, s12, 1, n02, K);
			radixPass(nums, s12, sa12, 0, n02, K);
			int name = 0, c0 = -1, c1 = -1, c2 = -1;
			for (int i = 0; i < n02; ++i) {
				if (c0 != nums[sa12[i]] || c1 != nums[sa12[i] + 1] || c2 != nums[sa12[i] + 2]) {
					name++;
					c0 = nums[sa12[i]];
					c1 = nums[sa12[i] + 1];
					c2 = nums[sa12[i] + 2];
				}
				if (1 == sa12[i] % 3) {
					s12[sa12[i] / 3] = name;
				} else {
					s12[sa12[i] / 3 + n0] = name;
				}
			}
			if (name < n02) {
				sa12 = skew(s12, n02, name);
				for (int i = 0; i < n02; i++) {
					s12[sa12[i]] = i + 1;
				}
			} else {
				for (int i = 0; i < n02; i++) {
					sa12[s12[i] - 1] = i;
				}
			}
			int[] s0 = new int[n0], sa0 = new int[n0];
			for (int i = 0, j = 0; i < n02; i++) {
				if (sa12[i] < n0) {
					s0[j++] = 3 * sa12[i];
				}
			}
			radixPass(nums, s0, sa0, 0, n0, K);
			int[] sa = new int[n];
			for (int p = 0, t = n0 - n1, k = 0; k < n; k++) {
				int i = sa12[t] < n0 ? sa12[t] * 3 + 1 : (sa12[t] - n0) * 3 + 2;
				int j = sa0[p];
				if (sa12[t] < n0 ? leq(nums[i], s12[sa12[t] + n0], nums[j], s12[j / 3])
						: leq(nums[i], nums[i + 1], s12[sa12[t] - n0 + 1], nums[j], nums[j + 1], s12[j / 3 + n0])) {
					sa[k] = i;
					t++;
					if (t == n02) {
						for (k++; p < n0; p++, k++) {
							sa[k] = sa0[p];
						}
					}
				} else {
					sa[k] = j;
					p++;
					if (p == n0) {
						for (k++; t < n02; t++, k++) {
							sa[k] = sa12[t] < n0 ? sa12[t] * 3 + 1 : (sa12[t] - n0) * 3 + 2;
						}
					}
				}
			}
			return sa;
		}

		private void radixPass(int[] nums, int[] input, int[] output, int offset, int n, int k) {
			int[] cnt = new int[k + 1];
			for (int i = 0; i < n; ++i) {
				cnt[nums[input[i] + offset]]++;
			}
			for (int i = 0, sum = 0; i < cnt.length; ++i) {
				int t = cnt[i];
				cnt[i] = sum;
				sum += t;
			}
			for (int i = 0; i < n; ++i) {
				output[cnt[nums[input[i] + offset]]++] = input[i];
			}
		}

		private boolean leq(int a1, int a2, int b1, int b2) {
			return a1 < b1 || (a1 == b1 && a2 <= b2);
		}

		private boolean leq(int a1, int a2, int a3, int b1, int b2, int b3) {
			return a1 < b1 || (a1 == b1 && leq(a2, a3, b2, b3));
		}

		private int[] rank() {
			int n = sa.length;
			int[] ans = new int[n];
			for (int i = 0; i < n; i++) {
				ans[sa[i]] = i;
			}
			return ans;
		}

		private int[] height(int[] s) {
			int n = s.length;
			int[] ans = new int[n];
			// 依次求h[i] , k = 0
			for (int i = 0, k = 0; i < n; ++i) {
				if (rank[i] != 0) {
					if (k > 0) {
						--k;
					}
					int j = sa[rank[i] - 1];
					while (i + k < n && j + k < n && s[i + k] == s[j + k]) {
						++k;
					}
					// h[i] = k
					ans[rank[i]] = k;
				}
			}
			return ans;
		}

	}

	// for test
	public static String randomNumberString(int len, int range) {
		char[] str = new char[len];
		for (int i = 0; i < len; i++) {
			str[i] = (char) ((int) (Math.random() * range) + 'a');
		}
		return String.valueOf(str);
	}

	public static void main(String[] args) {
		int len = 30;
		int range = 5;
		int testTime = 100000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTime; i++) {
			int N1 = (int) (Math.random() * len);
			int N2 = (int) (Math.random() * len);
			String str1 = randomNumberString(N1, range);
			String str2 = randomNumberString(N2, range);
			int ans1 = lcs1(str1, str2);
			int ans2 = lcs2(str1, str2);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("功能测试结束");
		System.out.println("==========");

		System.out.println("性能测试开始");
		len = 80000;
		range = 26;
		long start;
		long end;

		String str1 = randomNumberString(len, range);
		String str2 = randomNumberString(len, range);

		start = System.currentTimeMillis();
		int ans1 = lcs1(str1, str2);
		end = System.currentTimeMillis();
		System.out.println("方法1结果 : " + ans1 + " , 运行时间 : " + (end - start) + " ms");

		start = System.currentTimeMillis();
		int ans2 = lcs2(str1, str2);
		end = System.currentTimeMillis();
		System.out.println("方法2结果 : " + ans2 + " , 运行时间 : " + (end - start) + " ms");

		System.out.println("性能测试结束");

	}

}
