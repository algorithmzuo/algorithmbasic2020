package class21;

/**
 * 一个二维表格，求从（0，0）点到最右下脚的最小距离
 */
public class Code01_MinPathSum_Study {
    /**
     * 方法一：
     *  同样使用一个二维数组，使用循环计算每个格子的最小路径，最后返回最右下角的值就可以
     * @param m
     * @return
     */
    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 ){
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0] + m[i][0];
        }
        for (int j = 1;j < col;j++) {
            dp[0][j] = dp[0][j-1] + m[0][j];
        }

        for (int i = 1;i < row;i++) {
            for (int j = 1; j < col;j++) {
                dp[i][j] = Math.min(dp[i][j-1],dp[i-1][j]) + m[i][j];
            }
        }
        return dp[row-1][col-1];
    }

    /**
     * 这里使用的一维数组来优化，
     * @param m
     * @return
     */
    private static int minPathSum2(int[][] m) {
        if (m ==null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[] dp = new int[col];
        dp[0] = m[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] =  dp[j-1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[0] += m[i][0];
            for (int j = 1;j < col;j++) {
                dp[j] = Math.min(dp[j-1],dp[j]) + m[i][j];
            }
        }
        return dp[col-1];
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize,colSize);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
    }

    private static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] m = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                m[i][j] = (int)(Math.random()*5);
            }
        }
        return m;
    }
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }


}
