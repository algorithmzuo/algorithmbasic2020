package class25;

// 测试链接 : https://www.nowcoder.com/practice/2a2c00e7a88a498693568cef63a4b7bb
// 如果在牛客上做题，可以用如下的方式来做
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

public class Code01_MonotonousStackForNowcoder {

	public static int[] arr = new int[1000000];
	public static int[][] ans = new int[1000000][2];
	// stack1 : 相等值的位置也放
	// stack2 : 只放不相等值的最后一个位置
	// 比如 : arr = { 3, 3, 3, 4, 4, 6, 6, 6}
	//          位置  0  1  2  3  4  5  6  7
	// 如果位置依次压栈，
	// stack1中的记录是（位置） : 0 1 2 3 4 5 6 7
	// stack2中的记录是（位置） : 2 4 7
	public static int[] stack1 = new int[1000000];
	public static int[] stack2 = new int[1000000];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			for (int i = 0; i < n; i++) {
				in.nextToken();
				arr[i] = (int) in.nval;
			}
			getNearLess(n);
			for (int i = 0; i < n; i++) {
				out.println(ans[i][0] + " " + ans[i][1]);
			}
			out.flush();
		}
	}

	public static void getNearLess(int n) {
		int stackSize1 = 0;
		int stackSize2 = 0;
		for (int i = 0; i < n; i++) {
			while (stackSize1 > 0 && arr[stack1[stackSize1 - 1]] > arr[i]) {
				int curIndex = stack1[--stackSize1];
				int left = stackSize2 < 2 ? -1 : stack2[stackSize2 - 2];
				ans[curIndex][0] = left;
				ans[curIndex][1] = i;
				if (stackSize1 == 0 || arr[stack1[stackSize1 - 1]] != arr[curIndex]) {
					stackSize2--;
				}
			}
			if (stackSize1 != 0 && arr[stack1[stackSize1 - 1]] == arr[i]) {
				stack2[stackSize2 - 1] = i;
			} else {
				stack2[stackSize2++] = i;
			}
			stack1[stackSize1++] = i;
		}
		while (stackSize1 != 0) {
			int curIndex = stack1[--stackSize1];
			int left = stackSize2 < 2 ? -1 : stack2[stackSize2 - 2];
			ans[curIndex][0] = left;
			ans[curIndex][1] = -1;
			if (stackSize1 == 0 || arr[stack1[stackSize1 - 1]] != arr[curIndex]) {
				stackSize2--;
			}
		}
	}

}
