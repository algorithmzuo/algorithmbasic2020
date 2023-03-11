package class16;

// 课上没讲这个实现
// 因为是一样的，都是用Kruskal算法实现最小生成树，只不过是牛客网的测试数据
// 测试链接 : https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code04_KruskalNowCoder {

	public static int MAXM = 100001;

	public static int[][] edges = new int[MAXM][3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			in.nextToken();
			int m = (int) in.nval;
			for (int i = 0; i < m; i++) {
				in.nextToken();
				edges[i][0] = (int) in.nval;
				in.nextToken();
				edges[i][1] = (int) in.nval;
				in.nextToken();
				edges[i][2] = (int) in.nval;
			}
			Arrays.sort(edges, 0, m, (a, b) -> a[2] - b[2]);
			build(n);
			int ans = 0;
			for (int[] edge : edges) {
				if (union(edge[0], edge[1])) {
					ans += edge[2];
				}
			}
			out.println(ans);
			out.flush();
		}
	}

	// 下面是并查集结构
	public static int MAXN = 10001;

	public static int[] father = new int[MAXN];

	public static int[] size = new int[MAXN];

	public static int[] help = new int[MAXN];

	public static void build(int n) {
		for (int i = 1; i <= n; i++) {
			father[i] = i;
			size[i] = 1;
		}
	}

	private static int find(int i) {
		int size = 0;
		while (i != father[i]) {
			help[size++] = i;
			i = father[i];
		}
		while (size > 0) {
			father[help[--size]] = i;
		}
		return i;
	}

	// 如果i和j，原本是一个集合，返回false
	// 如果i和j，不是一个集合，合并，然后返回true
	public static boolean union(int i, int j) {
		int fi = find(i);
		int fj = find(j);
		if (fi != fj) {
			if (size[fi] >= size[fj]) {
				father[fj] = fi;
				size[fi] += size[fj];
			} else {
				father[fi] = fj;
				size[fj] += size[fi];
			}
			return true;
		} else {
			return false;
		}
	}

}
