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