package class43;

// 找到了贴瓷砖问题在线测试
// 测试链接 : http://poj.org/problem?id=2411
// 注册一个北京大学评测平台的号
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

// 本文件是状态压缩的动态规划版本，也就是课上讲的版本
public class Code03_PavingTile1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			in.nextToken();
			int m = (int) in.nval;
			if (n != 0 || m != 0) {
				long ans = ways(n, m);
				out.println(ans);
				out.flush();
			}
		}
	}

	// 状态压缩动态规划，最后一个版本
	// 其实其他版本也能通过
	public static long ways(int N, int M) {
		if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
			return 0;
		}
		if (N == 1 || M == 1) {
			return 1;
		}
		int big = N > M ? N : M;
		int small = big == N ? M : N;
		int sn = 1 << small;
		int limit = sn - 1;
		long[] dp = new long[sn];
		dp[limit] = 1;
		long[] cur = new long[sn];
		for (int level = 0; level < big; level++) {
			for (int status = 0; status < sn; status++) {
				if (dp[status] != 0) {
					int op = (~status) & limit;
					dfs(dp[status], op, 0, small - 1, cur);
				}
			}
			for (int i = 0; i < sn; i++) {
				dp[i] = 0;
			}
			long[] tmp = dp;
			dp = cur;
			cur = tmp;
		}
		return dp[limit];
	}

	public static void dfs(long way, int op, int index, int end, long[] cur) {
		if (index == end) {
			cur[op] += way;
		} else {
			dfs(way, op, index + 1, end, cur);
			if (((3 << index) & op) == 0) {
				dfs(way, op | (3 << index), index + 1, end, cur);
			}
		}
	}

}
