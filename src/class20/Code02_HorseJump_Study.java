package class20;

/**
 * 跳马问题
 * 棋盘有横坐标九条线，纵坐标10条线
 * x,y,k 象棋马走日，经过k步最后落在x，y坐标，问又多少中走法
 */
public class Code02_HorseJump_Study {

    public static int jump(int a,int b,int rest){
        return process(0,0,a,b,rest);
    }

    private static int process(int x, int y, int a, int b, int rest) {
        if (x < 0 || y<0||x >8 || y>9) {
            return 0;
        }
        if (rest == 0) {
            return (x == a && y==b) ? 1:0;
        }
        int ways = process(x+1,y+2,a,b,rest-1);
         ways += process(x+2,y-1,a,b,rest-1);
         ways += process(x+1,y-2,a,b,rest-1);
         ways += process(x-1,y+2,a,b,rest-1);
         ways += process(x-1,y-2,a,b,rest-1);
         ways += process(x-2,y-1,a,b,rest-1);
         ways += process(x-2,y+1,a,b,rest-1);
         ways += process(x+2,y+1,a,b,rest-1);
         return ways;
    }

    /**
     * 这是一个三维的
     * @param a
     * @param b
     * @param k
     * @return
     */
    public static int dp(int a,int b,int k) {
        int[][][] dp = new int[9][10][k+1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <=k ; rest++) {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 10; y++) {
                    int ways = pick(dp,x+1,y+2,rest-1);
                    ways += pick(dp,x+2,y-1,rest-1);
                    ways += pick(dp,x+1,y-2,rest-1);
                    ways += pick(dp,x-1,y+2,rest-1);
                    ways += pick(dp,x-1,y-2,rest-1);
                    ways += pick(dp,x-2,y-1,rest-1);
                    ways += pick(dp,x-2,y+1,rest-1);
                    ways += pick(dp,x+2,y+1,rest-1);
                    dp[x][y][rest] = ways;
                }
            }
        }
        //获取在起始位置[x,y]的值
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp,int x,int y,int rest){
        if (x < 0 || x > 8 || y  < 0 || y > 9) {
            return 0;
        }
        return dp[x][y][rest];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(jump(7,7,10));
        System.out.println(dp(7,7,10));
    }
}
