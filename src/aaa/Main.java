package aaa;

import java.util.Scanner;

public class Main {
    private static int n;
    private static int[] arr;
    private static int[] sum;
    private static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入石子的堆数:");
        n = sc.nextInt();
        arr = new int[2*n];
        sum = new int[2*n];
        for(int i = 1; i <= n; i++){
            arr[i] = sc.nextInt();
            sum[i] = sum[i - 1] + arr[i];
        }
        for(int i = n + 1; i <= 2 * n - 1; i++){
            arr[i] = arr[i - n];
            sum[i] = sum[i - 1] + arr[i];
        }
        minFun();
    }

    private static void minFun(){
        dp = new int[2 * n][2*n];
        for(int r = 1; r <= n - 1; r++){
            for(int i = 1; i <= 2 * n - 1; i++){
                int j = i + r;
                if(j >= 2 * n){
                    continue;
                }
                dp[i][j] = dp[i][i] + dp[i + 1][j] + sum[j] - sum[i - 1];
                for(int k = i + 1; k < j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1]);
                }
            }
        }
        int result = dp[1][n];
        for(int i = 2; i <= n; i++){
            result = Math.min(result, dp[i][n - 1 + i]);
        }
        System.out.println("最低得分:"+result);
    }
}
