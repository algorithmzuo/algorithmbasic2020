package class12;

public class Code09_CoinsWay {

	// arr中都是正数且无重复值，返回组成aim的方法数
	public static int ways1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		return process1(arr, 0, aim);
	}

	public static int process1(int[] arr, int index, int rest) {
		if(index == arr.length) {
			return rest == 0 ? 1 : 0 ;	
		}
		int ways = 0;
		for(int zhang = 0;  zhang * arr[index] <= rest ;zhang++) {
			ways += process1(arr, index + 1, rest -  (zhang * arr[index])  );
		}
		return ways;
	}

	public static int ways2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int[][] dp = new int[arr.length+1][aim+1];
		// 一开始所有的过程，都没有计算呢
		// dp[..][..]  = -1
		for(int i = 0 ; i < dp.length; i++) {
			for(int j = 0 ; j < dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
		return process2(arr, 0, aim , dp);
	}
	
	// 如果index和rest的参数组合，是没算过的，dp[index][rest] == -1
	// 如果index和rest的参数组合，是算过的，dp[index][rest]  > - 1
	public static int process2(int[] arr, 
			int index, int rest,
			int[][] dp) {
		if(dp[index][rest] != -1) {
			return dp[index][rest];
		}
		if(index == arr.length) {
			dp[index][rest] = rest == 0 ? 1 :0;
			return  dp[index][rest];	
		}
		int ways = 0;
		for(int zhang = 0;  zhang * arr[index] <= rest ;zhang++) {
			ways += process2(arr, index + 1, rest -  (zhang * arr[index]) , dp );
		}
		dp[index][rest] = ways;
		return ways;
	}
	
	public static int ways3(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;// dp[N][1...aim] = 0;
		for(int index = N - 1; index >= 0; index--) {
			for(int rest = 0; rest <= aim; rest++) {
				int ways = 0;
				for(int zhang = 0;  zhang * arr[index] <= rest ;zhang++) {
					ways += dp[index + 1] [rest -  (zhang * arr[index])];
				}
				dp[index][rest] = ways;
			}
		}
		return dp[0][aim];
	}
	
	public static int ways4(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;// dp[N][1...aim] = 0;
		for(int index = N - 1; index >= 0; index--) {
			for(int rest = 0; rest <= aim; rest++) {
				dp[index][rest] = dp[index+1][rest];
				if(rest - arr[index] >= 0) {
					dp[index][rest] += dp[index][rest - arr[index]];
				}
			}
		}
		return dp[0][aim];
	}
	

	public static void main(String[] args) {
		int[] arr = { 5, 10,50,100 };
		int sum = 1000;
		System.out.println(ways1(arr, sum));
		System.out.println(ways2(arr, sum));
		System.out.println(ways3(arr, sum));
		System.out.println(ways4(arr, sum));
	}

}
