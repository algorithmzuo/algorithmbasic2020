// 不要拷贝包信息的内容
package class39;

// 优化版本
// 这是牛客的测试链接：
// https://www.nowcoder.com/questionTerminal/d94bb2fa461d42bcb4c0f2b94f5d4281
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交如下的代码，并把主类名改成"Main"
// 可以直接通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code02_SnacksWaysMain2 {

	// 用来收集所有输入的数字
	public static long[] arr = new long[31];
	public static int size = 0;
	// 用来生成左部分可能的所有累加和
	public static long[] leftSum = new long[1 << 16];
	// 准备的数组可能用不完，左部分生成了多少累加和，用leftSize表示
	public static int leftSize = 0;
	// 用来生成右部分可能的所有累加和
	public static long[] rightSum = new long[1 << 16];
	// 准备的数组可能用不完，左部分生成了多少累加和，用leftSize表示
	public static int rightSize = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			size = (int) in.nval;
			in.nextToken();
			int bag = (int) in.nval;
			for (int i = 0; i < size; i++) {
				in.nextToken();
				arr[i] = (int) in.nval;
			}
			long ways = ways(bag);
			out.println(ways);
			out.flush();
		}
	}

	public static long ways(long w) {
		if (size == 0) {
			return 0;
		}
		if (size == 1) {
			return arr[0] <= w ? 2 : 1;
		}
		// 求中点
		int mid = size >> 1;
		// 生成左侧的累加和
		leftSize = 0;
		dfsLeft(0, mid + 1, 0L);
		// 生成右侧的累加和
		rightSize = 0;
		dfsRight(mid + 1, size, 0L);
		// 把左侧累加和排序
		Arrays.sort(leftSum, 0, leftSize);
		// 把右侧累加和排序
		Arrays.sort(rightSum, 0, rightSize);
		// 解释一下，接下来的流程。
		// 举个例子，比如：
		// 左侧累加和是:{0, 1, 1, 1, 2, 2, 3, 4, 4}
		// 右侧累加和是:{0, 1, 2, 3, 3, 3, 4, 4, 5}
		// w = 5
		// 左侧严格得到0的方法数：1
		// 右侧得到<=5的方法数（二分求出）：9
		// 1 * 9
		// 左侧严格得到1的方法数：3
		// 右侧得到<=4的方法数（二分求出）：8
		// 3 * 8
		// 左侧严格得到2的方法数：2
		// 右侧得到<=3的方法数（二分求出）：6
		// 2 * 6
		// 左侧严格得到3的方法数：1
		// 右侧得到<=2的方法数（二分求出）：3
		// 1 * 3
		// 左侧严格得到4的方法数：2
		// 右侧得到<=1的方法数（二分求出）：2
		// 2 * 2
		// 都累加起来
		// 其实和课上讲的一样！多看一下例子
		long ans = 0;
		long count = 1;
		for (int i = 1; i < leftSize; i++) {
			if (leftSum[i] != leftSum[i - 1]) {
				ans += count * (long) find(w - leftSum[i - 1]);
				count = 1;
			} else {
				count++;
			}
		}
		ans += count * (long) find(w - leftSum[leftSize - 1]);
		return ans;
	}

	// 生成左部分的累加和，每一个累加和出来，都记录
	public static void dfsLeft(int cur, int end, long sum) {
		if (cur == end) { // 已经终止位置了
			// 记录累加和
			leftSum[leftSize++] = sum;
		} else {
			// 可能性1，不要当前数
			dfsLeft(cur + 1, end, sum);
			// 可能性2，要当前数
			dfsLeft(cur + 1, end, sum + arr[cur]);
		}
	}

	// 生成右部分的累加和，每一个累加和出来，都记录
	public static void dfsRight(int cur, int end, long sum) {
		if (cur == end) { // 已经终止位置了
			// 记录累加和
			rightSum[rightSize++] = sum;
		} else {
			// 可能性1，不要当前数
			dfsRight(cur + 1, end, sum);
			// 可能性2，要当前数
			dfsRight(cur + 1, end, sum + arr[cur]);
		}
	}

	// <= num的数的个数，返回
	public static int find(long num) {
		int ans = -1;
		int l = 0;
		int r = rightSize - 1;
		int m = 0;
		while (l <= r) {
			m = (l + r) / 2;
			if (rightSum[m] <= num) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans + 1;
	}

}