package leo.class21;

/**
 * @author Leo
 * @ClassName BobDie
 * @DATE 2021/1/14 2:39 下午
 * @Description
 *
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class BobDie {
    static class Recursion {
        public static double livePosibility(int n, int m, int row, int col, int k) {
            if (row < 0 || col < 0 || row >= n || col >= m) {
                return 0;
            }
            return (double)process(n, m, row, col, k)/Math.pow(4,k);
        }

        private static long process(int n, int m, int row, int col, int rest) {
            if (row < 0 || col < 0 || row >= n || col >= m) {
                return 0;
            }
            if (rest == 0) {
                return 1;
            }
            long p1 = process(n, m, row, col + 1, rest - 1);
            long p2 = process(n, m, row , col - 1, rest - 1);
            long p3 = process(n, m, row + 1, col, rest - 1);
            long p4 = process(n, m, row - 1, col, rest - 1);
            return p1 + p2 + p3 + p4;
        }
    }
    static class Dp {
        public static double livePosibility(int n, int m, int row, int col, int k) {
            if (row < 0 || col < 0 || row >= n || col >= m) {
                return 0;
            }
            long[][][] dp = new long[n][m][k + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dp[i][j][0] = 1;
                }
            }
            for (int rest = 1; rest <= k; rest++) {
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < m; c++) {
                        dp[r][c][rest] = pick(dp,r,c+1,n,m,rest-1);
                        dp[r][c][rest] += pick(dp,r,c-1,n,m,rest-1);
                        dp[r][c][rest] += pick(dp,r+1,c,n,m,rest-1);
                        dp[r][c][rest] += pick(dp,r-1,c,n,m,rest-1);
                    }
                }
            }
            return (double) dp[row][col][k]/Math.pow(4,k);
        }

        private static long pick(long[][][] dp, int r, int c, int n, int m, int rest) {

            if (r < 0 || c < 0 || r >= n || c >= m) {
                return 0;
            }
            return dp[r][c][rest];
        }
    }

    public static void main(String[] args) {
        System.out.println(Recursion.livePosibility(6, 6, 3, 3, 5));
        System.out.println(Dp.livePosibility(6, 6, 3, 3, 5));
    }

}
