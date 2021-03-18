//不要拷贝包信息的内容
package class39;

//本文件是Code02_SnacksWays问题的牛客题目解答
//但是用的分治的方法
//这是牛客的测试链接：
//https://www.nowcoder.com/questionTerminal/d94bb2fa461d42bcb4c0f2b94f5d4281
//把如下的全部代码拷贝进编辑器（java）
//可以直接通过
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Code02_SnacksWaysMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int bag = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		long ways = ways(arr, bag);
		System.out.println(ways);
		sc.close();
	}

	public static long ways(int[] arr, int bag) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0] <= bag ? 2 : 1;
		}
		int mid = (arr.length - 1) >> 1;
		TreeMap<Long, Long> lmap = new TreeMap<>();
		long ways = process(arr, 0, 0, mid, bag, lmap);
		TreeMap<Long, Long> rmap = new TreeMap<>();
		ways += process(arr, mid + 1, 0, arr.length - 1, bag, rmap);
		TreeMap<Long, Long> rpre = new TreeMap<>();
		long pre = 0;
		for (Entry<Long, Long> entry : rmap.entrySet()) {
			pre += entry.getValue();
			rpre.put(entry.getKey(), pre);
		}
		for (Entry<Long, Long> entry : lmap.entrySet()) {
			long lweight = entry.getKey();
			long lways = entry.getValue();
			Long floor = rpre.floorKey(bag - lweight);
			if (floor != null) {
				long rways = rpre.get(floor);
				ways += lways * rways;
			}
		}
		return ways + 1;
	}

	
	
	
	// arr 30
	// func(arr, 0, 14, 0, bag, map)
	
	// func(arr, 15, 29, 0, bag, map)
	
	// 从index出发，到end结束
	// 之前的选择，已经形成的累加和sum
	// 零食[index....end]自由选择，出来的所有累加和，不能超过bag，每一种累加和对应的方法数，填在map里
	// 最后不能什么货都没选
	// [3,3,3,3] bag = 6
	// 0 1 2 3
	// - - - - 0 -> （0 : 1）
	// - - - $ 3 -> （0 : 1）(3, 1)
	// - - $ - 3 -> （0 : 1）(3, 2)
	public static long func(int[] arr, int index, int end, long sum, long bag, TreeMap<Long, Long> map) {
		if(sum > bag) {
			return 0;
		}
		// sum <= bag
		if(index > end) { // 所有商品自由选择完了！
			// sum
			if(sum != 0) {
				if (!map.containsKey(sum)) {
					map.put(sum, 1L);
				} else {
					map.put(sum, map.get(sum) + 1);
				}
				return 1;
			} else {
				return 0;
			}			
		}
		// sum <= bag 并且 index <= end(还有货)
		// 1) 不要当前index位置的货
		long ways = func(arr, index + 1, end, sum, bag, map);
		
		// 2) 要当前index位置的货
		ways += func(arr, index + 1, end, sum + arr[index], bag, map);
		return ways;
	}

	public static long process(int[] arr, int index, long w, int end, int bag, TreeMap<Long, Long> map) {
		if (w > bag) {
			return 0;
		}
		if (index > end) {
			if (w != 0) {
				if (!map.containsKey(w)) {
					map.put(w, 1L);
				} else {
					map.put(w, map.get(w) + 1);
				}
				return 1;
			} else {
				return 0;
			}
		} else {
			long ways = process(arr, index + 1, w, end, bag, map);
			ways += process(arr, index + 1, w + arr[index], end, bag, map);
			return ways;
		}
	}

}