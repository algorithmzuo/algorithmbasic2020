package class16;

// 课上没讲这个实现
// 因为是一样的，都是根据入度来求拓扑排序，只不过是牛客网的测试数据
// 测试链接 : https://www.nowcoder.com/questionTerminal/88f7e156ca7d43a1a535f619cd3f495c
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class Code03_TopologicalOrderBFS2 {

	public static int MAXN = 200001;

	public static int[] queue = new int[MAXN];

	public static int[] indegree = new int[MAXN];

	public static int[] ans = new int[MAXN];

	public static int n, m, from, to;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			m = (int) in.nval;
			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}
			for (int i = 0; i < m; i++) {
				in.nextToken();
				from = (int) in.nval;
				in.nextToken();
				to = (int) in.nval;
				graph.get(from).add(to);
			}
			if (!topoSort(graph)) {
				out.println(-1);
			} else {
				for (int i = 0; i < n - 1; i++) {
					out.print(ans[i] + " ");
				}
				out.println(ans[n - 1]);
			}
			out.flush();
		}
	}

	// 有拓扑排序返回true
	// 没有拓扑排序返回false
	public static boolean topoSort(ArrayList<ArrayList<Integer>> graph) {
		Arrays.fill(indegree, 1, n + 1, 0);
		for (ArrayList<Integer> nexts : graph) {
			for (int next : nexts) {
				indegree[next]++;
			}
		}
		int l = 0;
		int r = 0;
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				queue[r++] = i;
			}
		}
		int cnt = 0;
		while (l < r) {
			int cur = queue[l++];
			ans[cnt++] = cur;
			for (int next : graph.get(cur)) {
				if (--indegree[next] == 0) {
					queue[r++] = next;
				}
			}
		}
		return cnt == n;
	}

}
