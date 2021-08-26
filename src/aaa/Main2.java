package aaa;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            int n = sc.nextInt();
            //所给数组 下标从1~n
            int[] num = new int[n+1];
            //前缀和数组
            int[] sum = new int[n+1];
            //dp数组，dp[i][j]表示i到j合并的最小代价，范围是1~n
            int[][] dp = new int[n+1][n+1];
            //dp数组初始化
            for(int i=0;i<=n;i++) {
                Arrays.fill(dp[i],Integer.MAX_VALUE);
            }
            sum[0] = 0;
            //sum数组初始化
            for(int i=1;i<=n;i++) {
                num[i] = sc.nextInt();
                sum[i] = num[i] + sum[i-1];
                dp[i][i] = 0;
            }
            //区间长度len从小到大 因为大区间依赖于小区间
            for(int len=1; len<n; len++) {
                //起点i，终点i+len
                for(int i=1; i+len<=n; i++) {
                    int j = i+len;
                    //在i到i+len之间取分割点k
                    for(int k=i; k<j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k+1][j]+sum[j]-sum[i-1]);
                    }
                }
            }
            //最终结果就是1~n的合并最小代价用dp[1][n]表示
            System.out.println(dp[1][n]);
        }
    }
}
