package class14;

// 这个文件课上没有讲
// 原理和课上讲的完全一样
// 最大的区别就是这个文件实现的并查集是用数组结构，而不是map结构
// 请务必理解这个文件的实现，而且还提供了测试链接
// 提交如下的code，并把"Code06_UnionFind"这个类名改成"Main"
// 在测试链接里可以直接通过
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 测试链接 : https://www.nowcoder.com/questionTerminal/e7ed657974934a30b2010046536a5372
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code06_UnionFind {

	public static int MAXN = 1000001;

	public static int[] father = new int[MAXN];

	public static int[] size = new int[MAXN];

	public static int[] help = new int[MAXN];

	// 初始化并查集
	public static void init(int n) {
		for (int i = 0; i <= n; i++) {
			father[i] = i;
			size[i] = 1;
		}
	}

	// 从i开始寻找集合代表点
	public static int find(int i) {
		int hi = 0;
		while (i != father[i]) {
			help[hi++] = i;
			i = father[i];
		}
		for (hi--; hi >= 0; hi--) {
			father[help[hi]] = i;
		}
		return i;
	}

	// 查询x和y是不是一个集合
	public static boolean isSameSet(int x, int y) {
		return find(x) == find(y);
	}

	// x所在的集合，和y所在的集合，合并成一个集合
	public static void union(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if (fx != fy) {
			if (size[fx] >= size[fy]) {
				size[fx] += size[fy];
				father[fy] = fx;
			} else {
				size[fy] += size[fx];
				father[fx] = fy;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			init(n);
			in.nextToken();
			int m = (int) in.nval;
			for (int i = 0; i < m; i++) {
				in.nextToken();
				int op = (int) in.nval;
				in.nextToken();
				int x = (int) in.nval;
				in.nextToken();
				int y = (int) in.nval;
				if (op == 1) {
					out.println(isSameSet(x, y) ? "Yes" : "No");
					out.flush();
				} else {
					union(x, y);
				}
			}
		}
	}
}
