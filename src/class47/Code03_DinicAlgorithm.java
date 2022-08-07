// 本题测试链接:
// https://lightoj.com/problem/internet-bandwidth
// 这是一道DinicAlgorithm算法的题
// 把如下代码粘贴进网页所提供的java编译器环境中
// 不需要修改任何内容可以直接通过
// 请看网页上的题目描述并结合main函数的写法去了解这个模板的用法
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法

package class47;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Code03_DinicAlgorithm {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int cases = (int) in.nval;
			for (int i = 1; i <= cases; i++) {
				in.nextToken();
				int n = (int) in.nval;
				in.nextToken();
				int s = (int) in.nval;
				in.nextToken();
				int t = (int) in.nval;
				in.nextToken();
				int m = (int) in.nval;
				Dinic dinic = new Dinic(n);
				for (int j = 0; j < m; j++) {
					in.nextToken();
					int from = (int) in.nval;
					in.nextToken();
					int to = (int) in.nval;
					in.nextToken();
					int weight = (int) in.nval;
					dinic.addEdge(from, to, weight);
					dinic.addEdge(to, from, weight);
				}
				int ans = dinic.maxFlow(s, t);
				out.println("Case " + i + ": " + ans);
				out.flush();
			}
		}
	}

	public static class Edge {
		public int from;
		public int to;
		public int available;

		public Edge(int a, int b, int c) {
			from = a;
			to = b;
			available = c;
		}
	}

	public static class Dinic {
		private int N;
		private ArrayList<ArrayList<Integer>> nexts;
		private ArrayList<Edge> edges;
		private int[] depth;
		private int[] cur;

		public Dinic(int nums) {
			N = nums + 1;
			nexts = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				nexts.add(new ArrayList<>());
			}
			edges = new ArrayList<>();
			depth = new int[N];
			cur = new int[N];
		}

		public void addEdge(int u, int v, int r) {
			int m = edges.size();
			edges.add(new Edge(u, v, r));
			nexts.get(u).add(m);
			edges.add(new Edge(v, u, 0));
			nexts.get(v).add(m + 1);
		}

		public int maxFlow(int s, int t) {
			int flow = 0;
			while (bfs(s, t)) {
				Arrays.fill(cur, 0);
				flow += dfs(s, t, Integer.MAX_VALUE);
				Arrays.fill(depth, 0);
			}
			return flow;
		}

		private boolean bfs(int s, int t) {
			LinkedList<Integer> queue = new LinkedList<>();
			queue.addFirst(s);
			boolean[] visited = new boolean[N];
			visited[s] = true;
			while (!queue.isEmpty()) {
				int u = queue.pollLast();
				for (int i = 0; i < nexts.get(u).size(); i++) {
					Edge e = edges.get(nexts.get(u).get(i));
					int v = e.to;
					if (!visited[v] && e.available > 0) {
						visited[v] = true;
						depth[v] = depth[u] + 1;
						if (v == t) {
							break;
						}
						queue.addFirst(v);
					}
				}
			}
			return visited[t];
		}

		// 当前来到了s点，s可变
		// 最终目标是t，t固定参数
		// r，收到的任务
		// 收集到的流，作为结果返回，ans <= r
		private int dfs(int s, int t, int r) {
			if (s == t || r == 0) {
				return r;
			}
			int f = 0;
			int flow = 0;
			// s点从哪条边开始试 -> cur[s]
			for (; cur[s] < nexts.get(s).size(); cur[s]++) {
				int ei = nexts.get(s).get(cur[s]);
				Edge e = edges.get(ei);
				Edge o = edges.get(ei ^ 1);
				if (depth[e.to] == depth[s] + 1 && (f = dfs(e.to, t, Math.min(e.available, r))) != 0) {
					e.available -= f;
					o.available += f;
					flow += f;
					r -= f;
					if (r <= 0) {
						break;
					}
				}
			}
			return flow;
		}
	}

}