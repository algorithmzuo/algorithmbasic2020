package leo.class20;

/**
 * @author Leo
 * @ClassName HorseJump
 * @DATE 2021/1/11 1:34 下午
 * @Description
 * 样本对应模型
 *
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 */
public class HorseJump {

    /**
     * O(8^k)
     */
    static class Recursion {

        public static int jump(int a, int b, int k) {
            return process(0, 0, k, a, b);
        }

        private static int process(int x, int y, int rest, int a, int b) {
            if (x < 0 || x > 9 || y < 0 || y > 8) {
                return 0;
            }
            if (rest == 0) {
                return x == a && y == b ? 1 : 0;
            }
            int ways = process(x - 1, y - 2, rest - 1, a, b);
            ways += process(x - 1, y + 2, rest - 1, a, b);
            ways += process(x + 1, y - 2, rest - 1, a, b);
            ways += process(x + 1, y + 2, rest - 1, a, b);

            ways += process(x - 2, y + 1, rest - 1, a, b);
            ways += process(x - 2, y - 1, rest - 1, a, b);
            ways += process(x + 2, y + 1, rest - 1, a, b);
            ways += process(x + 2, y - 1, rest - 1, a, b);
            return ways;
        }
    }

    /**
     * O(k)
     */
    static class Dp {
        public static int jump(int a, int b, int k) {
            int[][][] dp = new int[10][9][k + 1];
            dp[a][b][0] = 1;
            for (int rest = 1; rest <= k; rest++) {
                for (int x = 0; x < 10; x++) {
                    for (int y = 0; y < 9; y++) {
                        int ways = pick(dp, x - 1, y - 2, rest - 1);
                        ways += pick(dp, x - 1, y + 2, rest - 1);
                        ways += pick(dp, x + 1, y - 2, rest - 1);
                        ways += pick(dp, x + 1, y + 2, rest - 1);

                        ways += pick(dp, x - 2, y + 1, rest - 1);
                        ways += pick(dp, x - 2, y - 1, rest - 1);
                        ways += pick(dp, x + 2, y + 1, rest - 1);
                        ways += pick(dp, x + 2, y - 1, rest - 1);
                        dp[x][y][rest] = ways;
                    }
                }
            }
            return dp[0][0][k];
        }

        private static int pick(int[][][] dp, int x, int y, int rest) {
            if (x < 0 || x > 9 || y < 0 || y > 8) {
                return 0;
            }
            return dp[x][y][rest];
        }
    }



    public static void main(String[] args){
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(Recursion.jump(x, y, step));
        System.out.println(Dp.jump(x, y, step));
    }
}
