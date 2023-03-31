package class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

// 课上讲的并查集实现
// 请务必看补充的Code06_UnionFind
// 那是数组实现的并查集，并且有测试链接
// 可以直接通过
// 这个文件的并查集是用map实现的
// 但是笔试或者平时用的并查集一律用数组实现
// 所以Code06_UnionFind更具实战意义
// 一定要看！
public class Code05_UnionFind {

	// 课上讲的时候
	// 包了一层
	// 其实不用包一层哦
	public static class UnionFind<V> {
		public HashMap<V, V> father;
		public HashMap<V, Integer> size;

		public UnionFind(List<V> values) {
			father = new HashMap<>();
			size = new HashMap<>();
			for (V cur : values) {
				father.put(cur, cur);
				size.put(cur, 1);
			}
		}

		// 给你一个节点，请你往上到不能再往上，把代表返回
		public V findFather(V cur) {
			Stack<V> path = new Stack<>();
			while (cur != father.get(cur)) {
				path.push(cur);
				cur = father.get(cur);
			}
			while (!path.isEmpty()) {
				father.put(path.pop(), cur);
			}
			return cur;
		}

		public boolean isSameSet(V a, V b) {
			return findFather(a) == findFather(b);
		}

		public void union(V a, V b) {
			V aFather = findFather(a);
			V bFather = findFather(b);
			if (aFather != bFather) {
				int aSize = size.get(aFather);
				int bSize = size.get(bFather);
				if (aSize >= bSize) {
					father.put(bFather, aFather);
					size.put(aFather, aSize + bSize);
					size.remove(bFather);
				} else {
					father.put(aFather, bFather);
					size.put(bFather, aSize + bSize);
					size.remove(aFather);
				}
			}
		}

		public int sets() {
			return size.size();
		}

	}
}
