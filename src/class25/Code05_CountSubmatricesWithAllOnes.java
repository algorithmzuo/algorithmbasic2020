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

	// 比如
	//              1
	//              1
	//              1         1
	//    1         1         1
	//    1         1         1
	//    1         1         1
	//             
	//    2  ....   6   ....  9
	// 如上图，假设在6位置，1的高度为6
	// 在6位置的左边，离6位置最近、且小于高度6的位置是2，2位置的高度是3
	// 在6位置的右边，离6位置最近、且小于高度6的位置是9，9位置的高度是4
	// 此时我们求什么？
	// 1) 求在3~8范围上，必须以高度6作为高的矩形，有几个？
	// 2) 求在3~8范围上，必须以高度5作为高的矩形，有几个？
	// 也就是说，<=4的高度，一律不求
	// 那么，1) 求必须以位置6的高度6作为高的矩形，有几个？
	// 3..3  3..4  3..5  3..6  3..7  3..8
	// 4..4  4..5  4..6  4..7  4..8
	// 5..5  5..6  5..7  5..8
	// 6..6  6..7  6..8
	// 7..7  7..8
	// 8..8
	// 这么多！= 21 = (9 - 2 - 1) * (9 - 2) / 2
	// 这就是任何一个数字从栈里弹出的时候，计算矩形数量的方式
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
