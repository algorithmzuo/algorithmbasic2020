package class46;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

// 本文件不牵扯任何byte类型的转化
// 怎么转byte自己来，我只负责huffman算法本身的正确实现
// 字符串为空的时候，自己处理边界吧
// 实现的代码通过了大样本随机测试的对数器
// 可以从main函数的内容开始看起
public class Code05_HuffmanTree {

	// 根据文章str, 生成词频统计表
	public static HashMap<Character, Integer> countMap(String str) {
		HashMap<Character, Integer> ans = new HashMap<>();
		char[] s = str.toCharArray();
		for (char cha : s) {
			if (!ans.containsKey(cha)) {
				ans.put(cha, 1);
			} else {
				ans.put(cha, ans.get(cha) + 1);
			}
		}
		return ans;
	}

	public static class Node {
		public int count;
		public Node left;
		public Node right;

		public Node(int c) {
			count = c;
		}
	}

	public static class NodeComp implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.count - o2.count;
		}

	}

	// 根据由文章生成词频表countMap，生成哈夫曼编码表
	// key : 字符
	// value: 该字符编码后的二进制形式
	// 比如，频率表 A：60, B:45, C:13 D:69 E:14 F:5 G:3
	// A 10
	// B 01
	// C 0011
	// D 11
	// E 000
	// F 00101
	// G 00100
	public static HashMap<Character, String> huffmanForm(HashMap<Character, Integer> countMap) {
		HashMap<Character, String> ans = new HashMap<>();
		if (countMap.size() == 1) {
			for (char key : countMap.keySet()) {
				ans.put(key, "0");
			}
			return ans;
		}
		HashMap<Node, Character> nodes = new HashMap<>();
		PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComp());
		for (Entry<Character, Integer> entry : countMap.entrySet()) {
			Node cur = new Node(entry.getValue());
			char cha = entry.getKey();
			nodes.put(cur, cha);
			heap.add(cur);
		}
		while (heap.size() != 1) {
			Node a = heap.poll();
			Node b = heap.poll();
			Node h = new Node(a.count + b.count);
			h.left = a;
			h.right = b;
			heap.add(h);
		}
		Node head = heap.poll();
		fillForm(head, "", nodes, ans);
		return ans;
	}

	public static void fillForm(Node head, String pre, HashMap<Node, Character> nodes, HashMap<Character, String> ans) {
		if (nodes.containsKey(head)) {
			ans.put(nodes.get(head), pre);
		} else {
			fillForm(head.left, pre + "0", nodes, ans);
			fillForm(head.right, pre + "1", nodes, ans);
		}
	}

	// 原始字符串str，根据哈夫曼编码表，转译成哈夫曼编码返回
	public static String huffmanEncode(String str, HashMap<Character, String> huffmanForm) {
		char[] s = str.toCharArray();
		StringBuilder builder = new StringBuilder();
		for (char cha : s) {
			builder.append(huffmanForm.get(cha));
		}
		return builder.toString();
	}

	// 原始字符串的哈夫曼编码huffmanEncode，根据哈夫曼编码表，还原成原始字符串
	public static String huffmanDecode(String huffmanEncode, HashMap<Character, String> huffmanForm) {
		TrieNode root = createTrie(huffmanForm);
		TrieNode cur = root;
		char[] encode = huffmanEncode.toCharArray();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < encode.length; i++) {
			int index = encode[i] == '0' ? 0 : 1;
			cur = cur.nexts[index];
			if (cur.nexts[0] == null && cur.nexts[1] == null) {
				builder.append(cur.value);
				cur = root;
			}
		}
		return builder.toString();
	}

	public static TrieNode createTrie(HashMap<Character, String> huffmanForm) {
		TrieNode root = new TrieNode();
		for (char key : huffmanForm.keySet()) {
			char[] path = huffmanForm.get(key).toCharArray();
			TrieNode cur = root;
			for (int i = 0; i < path.length; i++) {
				int index = path[i] == '0' ? 0 : 1;
				if (cur.nexts[index] == null) {
					cur.nexts[index] = new TrieNode();
				}
				cur = cur.nexts[index];
			}
			cur.value = key;
		}
		return root;
	}

	public static class TrieNode {
		public char value;
		public TrieNode[] nexts;

		public TrieNode() {
			value = 0;
			nexts = new TrieNode[2];
		}
	}

	// 为了测试
	public static String randomNumberString(int len, int range) {
		char[] str = new char[len];
		for (int i = 0; i < len; i++) {
			str[i] = (char) ((int) (Math.random() * range) + 'a');
		}
		return String.valueOf(str);
	}

	// 为了测试
	public static void main(String[] args) {
		// 根据词频表生成哈夫曼编码表
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('A', 60);
		map.put('B', 45);
		map.put('C', 13);
		map.put('D', 69);
		map.put('E', 14);
		map.put('F', 5);
		map.put('G', 3);
		HashMap<Character, String> huffmanForm = huffmanForm(map);
		for (Entry<Character, String> entry : huffmanForm.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("====================");
		// str是原始字符串
		String str = "CBBBAABBACAABDDEFBA";
		System.out.println(str);
		// countMap是根据str建立的词频表
		HashMap<Character, Integer> countMap = countMap(str);
		// hf是根据countMap生成的哈夫曼编码表
		HashMap<Character, String> hf = huffmanForm(countMap);
		// huffmanEncode是原始字符串转译后的哈夫曼编码
		String huffmanEncode = huffmanEncode(str, hf);
		System.out.println(huffmanEncode);
		// huffmanDecode是哈夫曼编码还原成的原始字符串
		String huffmanDecode = huffmanDecode(huffmanEncode, hf);
		System.out.println(huffmanDecode);
		System.out.println("====================");
		System.out.println("大样本随机测试开始");
		// 字符串最大长度
		int len = 500;
		// 所含字符种类
		int range = 26;
		// 随机测试进行的次数
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			int N = (int) (Math.random() * len) + 1;
			String test = randomNumberString(N, range);
			HashMap<Character, Integer> counts = countMap(test);
			HashMap<Character, String> form = huffmanForm(counts);
			String encode = huffmanEncode(test, form);
			String decode = huffmanDecode(encode, form);
			if (!test.equals(decode)) {
				System.out.println(test);
				System.out.println(encode);
				System.out.println(decode);
				System.out.println("出错了!");
			}
		}
		System.out.println("大样本随机测试结束");
	}

}
