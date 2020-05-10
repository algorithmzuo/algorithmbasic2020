package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code07_MergeUsers {

	public static class Node<V> {
		V value;

		public Node(V v) {
			value = v;
		}
	}

	public static class UnionSet<V> {
		public HashMap<V, Node<V>> nodes;
		public HashMap<Node<V>, Node<V>> parents;
		public HashMap<Node<V>, Integer> sizeMap;

		public UnionSet(List<V> values) {
			for (V cur : values) {
				Node<V> node = new Node<>(cur);
				nodes.put(cur, node);
				parents.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		// 从点cur开始，一直往上找，找到不能再往上的代表点，返回
		public Node<V> findFather(Node<V> cur) {
			Stack<Node<V>> path = new Stack<>();
			while (cur != parents.get(cur)) {
				path.push(cur);
				cur = parents.get(cur);
			}
			// cur头节点
			while (!path.isEmpty()) {
				parents.put(path.pop(), cur);
			}
			return cur;
		}

		public boolean isSameSet(V a, V b) {
			if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
				return false;
			}
			return findFather(nodes.get(a)) == findFather(nodes.get(b));
		}

		public void union(V a, V b) {
			if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
				return;
			}
			Node<V> aHead = findFather(nodes.get(a));
			Node<V> bHead = findFather(nodes.get(b));
			if (aHead != bHead) {
				int aSetSize = sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
				Node<V> small = big == aHead ? bHead : aHead;
				parents.put(small, big);
				sizeMap.put(big, aSetSize + bSetSize);
				sizeMap.remove(small);
			}
		}
		
		
		public int getSetNum() {
			return sizeMap.size();
		}
		
	}

	public static class User {
		public String a;
		public String b;
		public String c;

		public User(String a, String b, String c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}

	// (1,10,13) (2,10,37) (400,500,37)
	// 如果两个user，a字段一样、或者b字段一样、或者c字段一样，就认为是一个人
	// 请合并users，返回合并之后的用户数量
	public static int mergeUsers(List<User> users) {
		UnionSet<User> unionFind = new UnionSet<>(users);
		HashMap<String, User> mapA = new HashMap<>();
		HashMap<String, User> mapB = new HashMap<>();
		HashMap<String, User> mapC = new HashMap<>();
		for(User user : users) {
			if(mapA.containsKey(user.a)) {
				unionFind.union(user, mapA.get(user.a));
			}else {
				mapA.put(user.a, user);
			}
			if(mapB.containsKey(user.b)) {
				unionFind.union(user, mapB.get(user.b));
			}else {
				mapB.put(user.b, user);
			}
			if(mapC.containsKey(user.c)) {
				unionFind.union(user, mapC.get(user.c));
			}else {
				mapC.put(user.c, user);
			}
		}
		// 向并查集询问，合并之后，还有多少个集合？
		return unionFind.getSetNum();
	}

}
