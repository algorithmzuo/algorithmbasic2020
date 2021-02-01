package class27;

public class Code01_KMP {
    // O(N)
	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] str = s.toCharArray();
		char[] match = m.toCharArray();
		int x = 0; // str中当前比对到的位置
		int y = 0; // match中当前比对到的位置
		// M  M <= N   O(M)
		int[] next = getNextArray(match); // next[i]  match中i之前的字符串match[0..i-1]
		// O(N)
		while (x < str.length && y < match.length) {
			if (str[x] == match[y]) {
				x++;
				y++;
			} else if (next[y] == -1) { // y == 0
				x++;
			} else {
				y = next[y];
			}
		}
		return y == match.length ? x - y : -1;
	}

	// M   O(M)
	public static int[] getNextArray(char[] match) {
		if (match.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[match.length];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		// cn代表，cn位置的字符，是当前和i-1位置比较的字符
		int cn = 0;
		while (i < next.length) {
			if (match[i - 1] == match[cn]) { // 跳出来的时候
				next[i++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
	}

	// for test
	public static String getRandomString(int possibilities, int size) {
		char[] ans = new char[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		int possibilities = 5;
		int strSize = 20;
		int matchSize = 5;
		int testTimes = 5000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strSize);
			String match = getRandomString(possibilities, matchSize);
			if (getIndexOf(str, match) != str.indexOf(match)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
