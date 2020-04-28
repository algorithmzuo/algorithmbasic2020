package class02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class HashMapAndSortedMap {
	
	
	public static class Node{
		public int value;
		public Node(int v) {
			 value = v;
		}
	}
	
	public static void main(String[] args) {
		// UnSortedMap
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1000000, "我是1000000");
		map.put(2, "我是2");
		map.put(3, "我是3");
		map.put(4, "我是4");
		map.put(5, "我是5");
		map.put(6, "我是6");
		map.put(1000000, "我是1000001");
		
		System.out.println(map.containsKey(1));
		System.out.println(map.containsKey(10));
		
		System.out.println(map.get(4));
		System.out.println(map.get(10));
		
		map.put(4, "他是4");
		System.out.println(map.get(4));
		
		map.remove(4);
		System.out.println(map.get(4));
		
		
		
		//       key
		HashSet<String>  set = new HashSet<>();
		set.add("abc");
		set.contains("abc");
		set.remove("abc");
		
		// 哈希表，增、删、改、查，在使用时，O（1）
		
		
		System.out.println("=====================");
		
		
		int a = 100000;
		int b = 100000;
		System.out.println(a == b);
		
		Integer c = 100000;
		Integer d = 100000;
		System.out.println(c.equals(d));
		
		Integer e = 127;  //  - 128  ~  127
		Integer f = 127;
		System.out.println(e == f);
		
		
		
		HashMap<Node, String> map2 = new HashMap<>();
		Node node1 = new Node(1);
		Node node2 = node1;
		map2.put(node1, "我是node1");
		map2.put(node2, "我是node1");
		System.out.println(map2.size());
		
		System.out.println("======================");
		
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		
		treeMap.put(3, "我是3");
		treeMap.put(4, "我是4");
		treeMap.put(8, "我是8");
		treeMap.put(5, "我是5");
		treeMap.put(7, "我是7");
		treeMap.put(1, "我是1");
		treeMap.put(2, "我是2");

		System.out.println(treeMap.containsKey(1));
		System.out.println(treeMap.containsKey(10));
		
		System.out.println(treeMap.get(4));
		System.out.println(treeMap.get(10));
		
		treeMap.put(4, "他是4");
		System.out.println(treeMap.get(4));
		
		treeMap.remove(4);
		System.out.println(treeMap.get(4));
		
		System.out.println(treeMap.firstKey());
		System.out.println(treeMap.lastKey());
		// <= 4
		System.out.println(treeMap.floorKey(4));
		// >= 4
		System.out.println(treeMap.ceilingKey(4));
		
		// O(logN)	
		
	}
	
	
	
}
