package class25;

// 测试链接：https://leetcode.com/problems/count-submatrices-with-all-ones
public class Code05_CountSubmatricesWithAllOnes {

	public static int numSubmat(int[][] mat) {
		if (mat == null || mat.length == 0 || mat[0].length == 0) {
			return 0;
		}
		int nums = 0;
		int[] height = new int[mat[0].length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
			}
			nums += countFromBottom(height);
		}
		return nums;

	}

	public static int countFromBottom(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int nums = 0;
		int[] stack = new int[height.length];
		int si = -1;
		for (int i = 0; i < height.length; i++) {
			while (si != -1 && height[stack[si]] >= height[i]) {
				int cur = stack[si--];
				if (height[cur] > height[i]) {
					int left = si == -1 ? -1 : stack[si];
					int n = i - left - 1;
					int down = Math.max(left == -1 ? 0 : height[left], height[i]);
					nums += (height[cur] - down) * num(n);
				}

			}
			stack[++si] = i;
		}
		while (si != -1) {
			int cur = stack[si--];
			int left = si == -1  ? -1 : stack[si];
			int n = height.length - left - 1;
			int down = left == -1 ? 0 : height[left];
			nums += (height[cur] - down) * num(n);
		}
		return nums;
	}

	public static int num(int n) {
		return ((n * (1 + n)) >> 1);
	}

}
