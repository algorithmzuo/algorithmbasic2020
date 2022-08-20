package class18;

public class Code01_RobotWalk_Study {
    /**
     * 一共有N个位置
     *  起始位置M
     *  剩余K步
     *  目标位置P
     * @param n
     * @return
     */
//    public static int ways1(int n,int start,int k,int aim) {
//        if (n <2 ||  start < 1 || start > n|| aim < 1 || aim > n|| k < 1 ) {
//            return -1;
//        }
//    }

    /**
     * 方法一使用暴力递归
     * @param n 有多少步数
     * @param cur 开始的步数
     * @param k 剩余的步数
     * @param aim 目标的步数
     * @return
     */
    public static int process1(int n,int cur,int k,int aim){
        if (k == 0) {
            return cur == aim ? 1: 0;
        }
        if (cur == 1) {
            return process1(n,2,k-1,aim);
        }
        if (cur == n) {
            return process1(n,n - 1,k -1,aim);
        }
        return process1(n,cur-1,k-1,aim) + process1(n,cur+1,k-1,aim);
    }

    /**
     * 傻缓存
     * @param n 有多少步数
     * @param cur 当前在哪步
     * @param k 剩余步数
     * @param aim 目标步数
     * @return
     */
    public static int ways2(int n ,int cur,int k,int aim) {
        if (n < 2 || cur < 1 || cur > n|| aim < 1 || aim > n || k < 1) {
            return -1;
        }
        int[][] arr = new int[n][k];
        for (int i = 0;i < n;i++) {
            for (int j = 0 ;j < k;j++) {
                arr[i][j] = -1;
            }
        }
        return process2(n,cur,k,aim,arr);
    }
    public static int process2(int n,int cur,int k,int aim,int[][] arr) {
        // 之前计算过
        if (arr[cur][k] != -1) {
            return arr[cur][k];
        }
        int ans = 0;
        if (k == 0) {
            ans = cur == aim ? 1:0;
        }else if (cur == 1){
            ans = process2(n,cur + 1,k-1,aim,arr);
        }else if (cur==n) {
            ans = process2(n,cur - 1,k - 1,aim,arr);
        }else {
            ans = process2(n,cur-1,k-1,aim,arr) + process2(n,cur+1,k-1,aim,arr);
        }
        arr[cur][k] = ans;
        return ans;
    }

    /**
     *
     * @param n
     * @param cur
     * @param k
     * @param aim
     * @return
     */
//    public static int way3(int n,int cur,int k,int aim) {
//
//    }
}
