package class11;

import java.util.ArrayList;
import java.util.List;

public class Code03_PrintAllPermutations {

	public static ArrayList<String> permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process(chs, 0, res);
		return res;
	}

	// str[0..i-1]已经做好决定的
	// str[i...]都有机会来到i位置
	// i终止位置，str当前的样子，就是一种结果 -> ans
	public static void process(char[] str, int i, ArrayList<String> ans) {
		if (i == str.length) {
			ans.add(String.valueOf(str));
		}
		// 如果i没有终止，i...  都可以来到i位置
		for (int j = i; j < str.length; j++) { // j  i后面所有的字符都有机会
			swap(str, i, j);
			process(str, i + 1, ans);
			swap(str, i, j);
		}
	}
	

	public static ArrayList<String> permutationNoRepeat(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process2(chs, 0, res);
		return res;
	}

	// str[0..i-1]已经做好决定的
	// str[i...]都有机会来到i位置
	// i终止位置，str当前的样子，就是一种结果 -> ans
	public static void process2(char[] str, int i, ArrayList<String> res) {
		if (i == str.length) {
			res.add(String.valueOf(str));
			return;
		}
		boolean[] visit = new boolean[26]; // visit[0 1 .. 25]
		for (int j = i; j < str.length; j++) {
			// str[j] = 'a'   -> 0   visit[0] -> 'a'
			
			// str[j] = 'z'   -> 25   visit[25] -> 'z'
			if (!visit[str[j] - 'a']) {
				
				visit[str[j] - 'a'] = true;
				swap(str, i, j);
				process2(str, i + 1, res);
				swap(str, i, j);
				
			}
		}
	}

	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

	public static void main(String[] args) {
		String s = "aac";
		List<String> ans1 = permutation(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans2 = permutationNoRepeat(s);
		for (String str : ans2) {
			System.out.println(str);
		}

	}

}
