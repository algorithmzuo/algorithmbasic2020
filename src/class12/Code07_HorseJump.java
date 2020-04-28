package class12;

public class Code07_HorseJump {

	// 10*9
	// 0~9 y
	// 0~8 x
	public static int ways(int a, int b, int step) {
		return f(0, 0, step, a, b);
	}

	// 马在(i,j)位置，还有step步要去跳
	// 返回最终来到(a,b)的方法数
	public static int f(int i, int j, int step, int a, int b) {
		if (i < 0 || i > 9 || j < 0 || j > 8) {
			return 0;
		}
		if (step == 0) {
			return (i == a && j == b) ? 1 : 0;
		}
		return f(i - 2, j + 1, step - 1, a, b) 
				+ f(i - 1, j + 2, step - 1, a, b) 
				+ f(i + 1, j + 2, step - 1, a, b)
				+ f(i + 2, j + 1, step - 1, a, b) 
				+ f(i + 2, j - 1, step - 1, a, b) 
				+ f(i + 1, j - 2, step - 1, a, b)
				+ f(i - 1, j - 2, step - 1, a, b) 
				+ f(i - 2, j - 1, step - 1, a, b);

	}
	
	
	public static int waysdp(int a, int b, int s) {
		// (i,j,0~ step)
		int[][][] dp = new int[10][9][s+1];
		dp[a][b][0] = 1;
		for(int step = 1 ; step <= s;step++ ) { // 按层来
			for(int i = 0 ; i < 10;i++) {
				for(int j = 0 ; j < 9; j++) {
					dp[i][j][step] = getValue(dp,i - 2, j + 1, step - 1) 
							+ getValue(dp,i - 1, j + 2, step - 1) 
							+ getValue(dp,i + 1, j + 2, step - 1)
							+ getValue(dp,i + 2, j + 1, step - 1) 
							+ getValue(dp,i + 2, j - 1, step - 1) 
							+ getValue(dp,i + 1, j - 2, step - 1)
							+ getValue(dp,i - 1, j - 2, step - 1) 
							+ getValue(dp,i - 2, j - 1, step - 1);
				}
			}
		}
		return dp[0][0][s];
	}

	// 在dp表中，得到dp[i][j][step]的值，但如果(i，j)位置越界的话，返回0；
	public static int getValue(int[][][] dp, int i, int j, int step) {
		if (i < 0 || i > 9 || j < 0 || j > 8) {
			return 0;
		}
		return dp[i][j][step];
	}

	public static void main(String[] args) {
		int x = 7;
		int y = 7;
		int step = 10;
		System.out.println(ways(x, y, step));
		System.out.println(waysdp(x, y, step));
	}
}
