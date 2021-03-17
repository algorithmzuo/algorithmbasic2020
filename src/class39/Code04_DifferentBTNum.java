package class39;

public class Code04_DifferentBTNum {

//	k(0) = 1, k(1) = 1
//
//	k(n) = k(0) * k(n - 1) + k(1) * k(n - 2) + ... + k(n - 2) * k(1) + k(n - 1) * k(0)
//	或者
//	k(n) = c(2n, n) / (n + 1)
//	或者
//	k(n) = c(2n, n) - c(2n, n-1)

	public static long num1(int N) {
		if (N < 0) {
			return 0;
		}
		if (N < 2) {
			return 1;
		}
		long[] dp = new long[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			for (int leftSize = 0; leftSize < i; leftSize++) {
				dp[i] += dp[leftSize] * dp[i - 1 - leftSize];
			}
		}
		return dp[N];
	}

	public static long num2(int N) {
		if (N < 0) {
			return 0;
		}
		if (N < 2) {
			return 1;
		}
		long a = 1;
		long b = 1;
		for (int i = 1, j = N + 1; i <= N; i++, j++) {
			a *= i;
			b *= j;
			long gcd = gcd(a, b);
			a /= gcd;
			b /= gcd;
		}
		return (b / a) / (N + 1);
	}

	public static long gcd(long m, long n) {
		return n == 0 ? m : gcd(n, m % n);
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		for (int i = 0; i < 15; i++) {
			long ans1 = num1(i);
			long ans2 = num2(i);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
