package leo.class21;

import class21.Code01_MinPathSum;

/**
 * @author Leo
 * @ClassName MinPathSum
 * @DATE 2021/1/13 2:15 下午
 * @Description
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 */
public class MinPathSum {

    static class Recursion {
        public static int minPathSum(int[][] arr) {
            if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
                return 0;
            }
            return process(arr, 0, 0);
        }

        private static int process(int[][] arr, int r, int l) {
            int min = arr[r][l];
            if (r == arr.length-1 && l == arr[0].length-1) {
                return min;
            } else if (r == arr.length-1 && l < arr[0].length-1) {
                return min + process(arr, r, l + 1);
            } else if (r < arr.length-1 && l == arr[0].length-1) {
                return min + process(arr, r + 1, l);
            }else {
                min += Math.min(process(arr, r + 1, l), process(arr, r, l + 1));
                return min;
            }

        }

    }

    static class Recursion1 {
        public static int minPathSum(int[][] arr) {
            if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
                return 0;
            }
            return process(arr, arr.length - 1, arr[0].length - 1);
        }

        private static int process(int[][] arr, int row, int col) {
            int min = arr[row][col];
            if (row == 0 && col == 0) {
                return min;
            } else if (row == 0 && col > 0) {
                return min + process(arr, row, col - 1);
            } else if (row > 0 && col == 0) {
                return min + process(arr, row - 1, col);
            }else{
                return min + Math.min(process(arr, row - 1, col), process(arr, row, col - 1));
            }

        }
    }

    static class Dp {
        public static int minPathSum(int[][] arr) {
            if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
                return 0;
            }
            int r = arr.length;
            int l = arr[0].length;
            int[][] dp = new int[r][l];
            dp[r - 1][l - 1] = arr[r-1][l-1];
            for (int i = r - 2; i >= 0; i--) {
                dp[i][l-1] = arr[i][l-1] + dp[i + 1][l-1];
            }
            for (int i = l - 2; i >= 0; i--) {
                dp[r-1][i] = arr[r-1][i] + dp[r-1][i + 1];
            }
            for (int i = r - 2; i >= 0; i--) {
                for (int j = l - 2; j >= 0; j--) {
                    dp[i][j] = arr[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
            return dp[0][0];
        }
    }

    static class Dp1 {
        public static int minPathSum(int[][] arr) {
            if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
                return 0;
            }
            int row = arr.length;
            int col = arr[0].length;
            int[][] dp = new int[row][col];
            dp[0][0] = arr[0][0];
            for (int i = 1; i < row; i++) {
                dp[i][0] = arr[i][0] + dp[i - 1][0];
            }
            for (int i = 1; i < col; i++) {
                dp[0][i] = arr[0][i] + dp[0][i - 1];
            }
            for (int r = 1; r < row; r++) {
                for (int c = 1; c < col; c++) {
                    dp[r][c] = arr[r][c] + Math.min(dp[r - 1][c], dp[r][c - 1]);
                }
            }
            return dp[row - 1][col - 1];
        }
    }

    static class OptimizationDp1 {
        public static int minPathSum(int[][] arr) {
            if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
                return 0;
            }
            int row = arr.length;
            int col = arr[0].length;
            int[] dp = new int[col];
            dp[0] = arr[0][0];
            for (int i = 1; i < col; i++) {
                dp[i] = arr[0][i] + dp[i - 1];
            }
            for (int r = 1; r < row; r++) {
                dp[0] += arr[r][0];
                for (int c = 1; c < col; c++) {
                    dp[c] = arr[r][c] + Math.min(dp[c - 1], dp[c]);
                }
            }
            return dp[col - 1];
        }
    }


    static class OptimizationDp {
        public static int minPathSum(int[][] arr) {
            if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
                return 0;
            }
            int r = arr.length;
            int c = arr[0].length;
            int[] dp = new int[c];
            dp[c-1] = arr[r - 1][c - 1];
            for (int i = c - 2; i >= 0; i--) {
                dp[i] = arr[r - 1][i] + dp[i + 1];
            }
            for (int i = r - 2; i >= 0; i--) {
                dp[c - 1] += arr[i][c - 1];
                for (int j = c - 2; j >= 0; j--) {
                    dp[j] = arr[i][j] + Math.min(dp[j + 1], dp[j]);
                }
            }

            return dp[0];
        }


    }
    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
         int[][] m = generateRandomMatrix(4, 4);
       /* int[][] m = {
                {7, 2, 8, 4},
                {1, 2, 0, 8},
                {7, 5, 1, 6},
                {0, 7, 0, 7}
        };*/
        System.out.println("start");

        for (int i = 0; i < 10000; i++) {
            int recursion = Recursion1.minPathSum(m);
            int dp = Dp.minPathSum(m);
            int dp1 = Dp1.minPathSum(m);
            int opt1 = OptimizationDp1.minPathSum(m);
            int opt = OptimizationDp.minPathSum(m);
            if (opt1 != opt) {
                System.out.println("====");
                printMatrix(m);
                System.out.println(opt);
                System.out.println(opt1);
                break;
            }
        }
        System.out.println("end");

    }
}
