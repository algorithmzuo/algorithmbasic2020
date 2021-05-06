package class37;

import java.util.TreeMap;

import class35.Code01_AVLTreeMap.AVLTreeMap;
import class36.Code01_SizeBalancedTreeMap.SizeBalancedTreeMap;
import class36.Code02_SkipListMap.SkipListMap;

// 本文件为avl、sbt、skiplist三种结构的测试文件
public class Compare {

	public static void functionTest() {
		System.out.println("功能测试开始");
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		AVLTreeMap<Integer, Integer> avl = new AVLTreeMap<>();
		SizeBalancedTreeMap<Integer, Integer> sbt = new SizeBalancedTreeMap<>();
		SkipListMap<Integer, Integer> skip = new SkipListMap<>();
		int maxK = 500;
		int maxV = 50000;
		int testTime = 1000000;
		for (int i = 0; i < testTime; i++) {
			int addK = (int) (Math.random() * maxK);
			int addV = (int) (Math.random() * maxV);
			treeMap.put(addK, addV);
			avl.put(addK, addV);
			sbt.put(addK, addV);
			skip.put(addK, addV);

			int removeK = (int) (Math.random() * maxK);
			treeMap.remove(removeK);
			avl.remove(removeK);
			sbt.remove(removeK);
			skip.remove(removeK);

			int querryK = (int) (Math.random() * maxK);
			if (treeMap.containsKey(querryK) != avl.containsKey(querryK)
					|| sbt.containsKey(querryK) != skip.containsKey(querryK)
					|| treeMap.containsKey(querryK) != sbt.containsKey(querryK)) {
				System.out.println("containsKey Oops");
				System.out.println(treeMap.containsKey(querryK));
				System.out.println(avl.containsKey(querryK));
				System.out.println(sbt.containsKey(querryK));
				System.out.println(skip.containsKey(querryK));
				break;
			}

			if (treeMap.containsKey(querryK)) {
				int v1 = treeMap.get(querryK);
				int v2 = avl.get(querryK);
				int v3 = sbt.get(querryK);
				int v4 = skip.get(querryK);
				if (v1 != v2 || v3 != v4 || v1 != v3) {
					System.out.println("get Oops");
					System.out.println(treeMap.get(querryK));
					System.out.println(avl.get(querryK));
					System.out.println(sbt.get(querryK));
					System.out.println(skip.get(querryK));
					break;
				}
				Integer f1 = treeMap.floorKey(querryK);
				Integer f2 = avl.floorKey(querryK);
				Integer f3 = sbt.floorKey(querryK);
				Integer f4 = skip.floorKey(querryK);
				if (f1 == null && (f2 != null || f3 != null || f4 != null)) {
					System.out.println("floorKey Oops");
					System.out.println(treeMap.floorKey(querryK));
					System.out.println(avl.floorKey(querryK));
					System.out.println(sbt.floorKey(querryK));
					System.out.println(skip.floorKey(querryK));
					break;
				}
				if (f1 != null && (f2 == null || f3 == null || f4 == null)) {
					System.out.println("floorKey Oops");
					System.out.println(treeMap.floorKey(querryK));
					System.out.println(avl.floorKey(querryK));
					System.out.println(sbt.floorKey(querryK));
					System.out.println(skip.floorKey(querryK));
					break;
				}
				if (f1 != null) {
					int ans1 = f1;
					int ans2 = f2;
					int ans3 = f3;
					int ans4 = f4;
					if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
						System.out.println("floorKey Oops");
						System.out.println(treeMap.floorKey(querryK));
						System.out.println(avl.floorKey(querryK));
						System.out.println(sbt.floorKey(querryK));
						System.out.println(skip.floorKey(querryK));
						break;
					}
				}
				f1 = treeMap.ceilingKey(querryK);
				f2 = avl.ceilingKey(querryK);
				f3 = sbt.ceilingKey(querryK);
				f4 = skip.ceilingKey(querryK);
				if (f1 == null && (f2 != null || f3 != null || f4 != null)) {
					System.out.println("ceilingKey Oops");
					System.out.println(treeMap.ceilingKey(querryK));
					System.out.println(avl.ceilingKey(querryK));
					System.out.println(sbt.ceilingKey(querryK));
					System.out.println(skip.ceilingKey(querryK));
					break;
				}
				if (f1 != null && (f2 == null || f3 == null || f4 == null)) {
					System.out.println("ceilingKey Oops");
					System.out.println(treeMap.ceilingKey(querryK));
					System.out.println(avl.ceilingKey(querryK));
					System.out.println(sbt.ceilingKey(querryK));
					System.out.println(skip.ceilingKey(querryK));
					break;
				}
				if (f1 != null) {
					int ans1 = f1;
					int ans2 = f2;
					int ans3 = f3;
					int ans4 = f4;
					if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
						System.out.println("ceilingKey Oops");
						System.out.println(treeMap.ceilingKey(querryK));
						System.out.println(avl.ceilingKey(querryK));
						System.out.println(sbt.ceilingKey(querryK));
						System.out.println(skip.ceilingKey(querryK));
						break;
					}
				}

			}

			Integer f1 = treeMap.firstKey();
			Integer f2 = avl.firstKey();
			Integer f3 = sbt.firstKey();
			Integer f4 = skip.firstKey();
			if (f1 == null && (f2 != null || f3 != null || f4 != null)) {
				System.out.println("firstKey Oops");
				System.out.println(treeMap.firstKey());
				System.out.println(avl.firstKey());
				System.out.println(sbt.firstKey());
				System.out.println(skip.firstKey());
				break;
			}
			if (f1 != null && (f2 == null || f3 == null || f4 == null)) {
				System.out.println("firstKey Oops");
				System.out.println(treeMap.firstKey());
				System.out.println(avl.firstKey());
				System.out.println(sbt.firstKey());
				System.out.println(skip.firstKey());
				break;
			}
			if (f1 != null) {
				int ans1 = f1;
				int ans2 = f2;
				int ans3 = f3;
				int ans4 = f4;
				if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
					System.out.println("firstKey Oops");
					System.out.println(treeMap.firstKey());
					System.out.println(avl.firstKey());
					System.out.println(sbt.firstKey());
					System.out.println(skip.firstKey());
					break;
				}
			}

			f1 = treeMap.lastKey();
			f2 = avl.lastKey();
			f3 = sbt.lastKey();
			f4 = skip.lastKey();
			if (f1 == null && (f2 != null || f3 != null || f4 != null)) {
				System.out.println("lastKey Oops");
				System.out.println(treeMap.lastKey());
				System.out.println(avl.lastKey());
				System.out.println(sbt.lastKey());
				System.out.println(skip.lastKey());
				break;
			}
			if (f1 != null && (f2 == null || f3 == null || f4 == null)) {
				System.out.println("firstKey Oops");
				System.out.println(treeMap.lastKey());
				System.out.println(avl.lastKey());
				System.out.println(sbt.lastKey());
				System.out.println(skip.lastKey());
				break;
			}
			if (f1 != null) {
				int ans1 = f1;
				int ans2 = f2;
				int ans3 = f3;
				int ans4 = f4;
				if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
					System.out.println("lastKey Oops");
					System.out.println(treeMap.lastKey());
					System.out.println(avl.lastKey());
					System.out.println(sbt.lastKey());
					System.out.println(skip.lastKey());
					break;
				}
			}
			if (treeMap.size() != avl.size() || sbt.size() != skip.size() || treeMap.size() != sbt.size()) {
				System.out.println("size Oops");
				System.out.println(treeMap.size());
				System.out.println(avl.size());
				System.out.println(sbt.size());
				System.out.println(skip.size());
				break;
			}
		}
		System.out.println("功能测试结束");
	}

	public static void performanceTest() {
		System.out.println("性能测试开始");
		TreeMap<Integer, Integer> treeMap;
		AVLTreeMap<Integer, Integer> avl;
		SizeBalancedTreeMap<Integer, Integer> sbt;
		SkipListMap<Integer, Integer> skip;
		long start;
		long end;
		int max = 1000000;
		treeMap = new TreeMap<>();
		avl = new AVLTreeMap<>();
		sbt = new SizeBalancedTreeMap<>();
		skip = new SkipListMap<>();
		System.out.println("顺序递增加入测试，数据规模 : " + max);
		start = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			treeMap.put(i, i);
		}
		end = System.currentTimeMillis();
		System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			avl.put(i, i);
		}
		end = System.currentTimeMillis();
		System.out.println("avl 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			sbt.put(i, i);
		}
		end = System.currentTimeMillis();
		System.out.println("sbt 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			skip.put(i, i);
		}
		end = System.currentTimeMillis();
		System.out.println("skip 运行时间 : " + (end - start) + "ms");

		System.out.println("顺序递增删除测试，数据规模 : " + max);
		start = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			treeMap.remove(i);
		}
		end = System.currentTimeMillis();
		System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			avl.remove(i);
		}
		end = System.currentTimeMillis();
		System.out.println("avl 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			sbt.remove(i);
		}
		end = System.currentTimeMillis();
		System.out.println("sbt 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			skip.remove(i);
		}
		end = System.currentTimeMillis();
		System.out.println("skip 运行时间 : " + (end - start) + "ms");

		System.out.println("顺序递减加入测试，数据规模 : " + max);
		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			treeMap.put(i, i);
		}
		end = System.currentTimeMillis();
		System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			avl.put(i, i);
		}
		end = System.currentTimeMillis();
		System.out.println("avl 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			sbt.put(i, i);
		}
		end = System.currentTimeMillis();
		System.out.println("sbt 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			skip.put(i, i);
		}
		end = System.currentTimeMillis();
		System.out.println("skip 运行时间 : " + (end - start) + "ms");

		System.out.println("顺序递减删除测试，数据规模 : " + max);
		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			treeMap.remove(i);
		}
		end = System.currentTimeMillis();
		System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			avl.remove(i);
		}
		end = System.currentTimeMillis();
		System.out.println("avl 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			sbt.remove(i);
		}
		end = System.currentTimeMillis();
		System.out.println("sbt 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			skip.remove(i);
		}
		end = System.currentTimeMillis();
		System.out.println("skip 运行时间 : " + (end - start) + "ms");

		System.out.println("随机加入测试，数据规模 : " + max);
		start = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			treeMap.put((int) (Math.random() * i), i);
		}
		end = System.currentTimeMillis();
		System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			avl.put((int) (Math.random() * i), i);
		}
		end = System.currentTimeMillis();
		System.out.println("avl 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			sbt.put((int) (Math.random() * i), i);
		}
		end = System.currentTimeMillis();
		System.out.println("sbt 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			skip.put((int) (Math.random() * i), i);
		}
		end = System.currentTimeMillis();
		System.out.println("skip 运行时间 : " + (end - start) + "ms");

		System.out.println("随机删除测试，数据规模 : " + max);
		start = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			treeMap.remove((int) (Math.random() * i));
		}
		end = System.currentTimeMillis();
		System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			avl.remove((int) (Math.random() * i));
		}
		end = System.currentTimeMillis();
		System.out.println("avl 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			sbt.remove((int) (Math.random() * i));
		}
		end = System.currentTimeMillis();
		System.out.println("sbt 运行时间 : " + (end - start) + "ms");

		start = System.currentTimeMillis();
		for (int i = max; i >= 0; i--) {
			skip.remove((int) (Math.random() * i));
		}
		end = System.currentTimeMillis();
		System.out.println("skip 运行时间 : " + (end - start) + "ms");

		System.out.println("性能测试结束");
	}

	public static void main(String[] args) {
		functionTest();
		System.out.println("======");
		performanceTest();
	}

}
