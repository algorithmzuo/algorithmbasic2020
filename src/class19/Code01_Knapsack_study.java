package class19;

public class Code01_Knapsack_study {
    /**
     * 暴力枚举
     * @param w 物品的重量数组
     * @param v 物品的价值数组
     * @param bag 背包成熟重量大小
     * @return
     */
    private static int ways1(int[] w,int[] v,int bag) {
        // 排除无效情况
        if(w == null ||v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process(w,v,0,bag);
    }

    /**
     *
     * @param w 物品的重量数组
     * @param v 物品的价值数组
     * @param index
     * @param rest 剩余重量
     * @return
     */
    private static int process(int[] w, int[] v, int index, int rest) {
        // base case
        // 背包没有剩余空间了,甚至是为负数了,返回-1表示无效解
        if (rest < 0) {
            return -1;
        }
        // 越界了
        if (index == w.length) {
            return 0;
        }
        // 不要index的物品
        int value1 = process(w,v,index + 1,rest);
        // 要index的物品
        int value2 = 0;
        int next = process(w,v,index + 1,rest - w[index]);
        // next == -1表明本次是无效的
        if (next != -1) {
            value2 =  next + v[index];
        }
        return Math.max(value1,value2);
    }

    /**
     * 改动态规划
     * @param w 重量数组
     * @param v 价值数组
     * @param bag 剩余数量
     * @return 背包最大价值
     */
    private static int way2(int[] w,int[] v,int bag) {
        // 排除无效情况
        if(w == null ||v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        //dp[N][0] = 0; 初始化默认填写好
        // 表格中，每一行的值依赖于下一行的值，因此这里是从下往上取推
        for (int i = N -1;i >= 0 ;i-- ){
            for (int rest = 0; rest <= bag;rest++) {
                int p1 = dp[i+1][rest];
                int p2 = 0;
                int next = rest -  w[i] < 0 ? -1 : dp[i + 1][rest - w[i]];
                if (next != -1) {
                    p2 = v[i] + next;
                }
                dp[i][rest] = Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3,2,4,7,3,1,7};
        int[] value = {5,6,3,19,12,4,2};
        int bag = 15;
        System.out.println(ways1(weights,value,bag));
        System.out.println(way2(weights,value,bag));
    }

}
