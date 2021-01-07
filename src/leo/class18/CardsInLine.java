package leo.class18;

import class18.Code02_CardsInLine;
import org.w3c.dom.ranges.Range;

/**
 * @author Leo
 * @ClassName CardsInLine
 * @DATE 2021/1/5 11:48 下午
 * @Description
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数
 */
public class CardsInLine {

    static class Recursion {
        public static int win(int[] arr) {
            if (arr.length == 0 || arr == null) {
                return 0;
            }
            int f = f(arr, 0, arr.length - 1);
            int g = g(arr, 0, arr.length - 1);
            return Math.max(f, g);
        }

        private static int g(int[] arr, int l, int r) {
            if (l == r) {
                return 0;
            }
            int p1 = f(arr, l + 1, r);
            int p2 = f(arr, l, r - 1);
            return Math.min(p1, p2);
        }

        private static int f(int[] arr, int l, int r) {
            if (l == r) {
                return arr[l];
            }
            int p1 = arr[l] + g(arr, l + 1, r);
            int p2 = arr[r] + g(arr, l, r - 1);
            return Math.max(p1, p2);
        }
    }

    static class Recursion1 {
        public static int win(int[] arr) {
            if (arr.length == 0 || arr == null) {
                return 0;
            }
            int f = f(arr, 0, arr.length - 1);
            int g = g(arr, 0, arr.length - 1);
            return Math.max(g, f);
        }

        private static int g(int[] arr, int l, int r) {
            if (l == r) {
                return 0;
            }
            int p1 = f(arr, l + 1, r);
            int p2 = f(arr, l, r - 1);
            return Math.min(p1, p2);
        }

        private static int f(int[] arr, int l, int r) {
            if (l == r) {
                return arr[l];
            }
            int p1 = arr[l] + g(arr, l + 1, r);
            int p2 = arr[r] + g(arr, l, r - 1);
            return Math.max(p1, p2);
        }
    }

    static class RecursionDp {
        public static int win(int[] arr) {
            if (arr.length == 0 || arr == null) {
                return 0;
            }
            int n = arr.length;
            int[][] fDp = new int[n][n];
            int[][] gDp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    fDp[i][j] = -1;
                    gDp[i][j] = -1;
                }
            }
            int f = f(arr, 0, arr.length - 1, fDp, gDp);
            int g = g(arr, 0, arr.length - 1, fDp, gDp);
            return Math.max(f, g);
        }

        private static int f(int[] arr, int l, int r, int[][] fDp, int[][] gDp) {
            if (fDp[l][r] != -1) {
                return fDp[l][r];
            }
            int ans = 0;
            if (l == r) {
                ans = arr[l];
            }else{
                int p1 = arr[l] + g(arr, l + 1, r, fDp, gDp);
                int p2 = arr[r] + g(arr, l, r - 1, fDp, gDp);
                ans = Math.max(p1, p2);
            }
            fDp[l][r] = ans;
            return ans;
        }
        private static int g(int[] arr, int l, int r, int[][] fDp, int[][] gDp) {
            if (gDp[l][r] != -1) {
                return gDp[l][r];
            }
            int ans = 0;
            if (l < r) {
                int p1 = f(arr, l + 1, r, fDp, gDp);
                int p2 = f(arr, l, r - 1, fDp, gDp);
                ans = Math.min(p1, p2);
            }
            gDp[l][r] = ans;
            return ans;
        }
    }

    static class RecursionDp1 {
        public static int win(int[] arr) {
            if (arr.length == 0 || arr == null) {
                return 0;
            }
            int n = arr.length;
            int[][] fDp = new int[n][n];
            int[][] gDp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    fDp[i][j] = -1;
                    gDp[i][j] = -1;
                }
            }
            int f = f(arr, 0, n - 1, fDp, gDp);
            int g = g(arr, 0, n - 1, fDp, gDp);
            return Math.max(f, g);
        }

        private static int g(int[] arr, int l, int r, int[][] fDp, int[][] gDp) {
            if (gDp[l][r] != -1) {
                return gDp[l][r];
            }
            int ans = 0;
            if (l != r) {
                int p1 = f(arr, l + 1, r, fDp, gDp);
                int p2 = f(arr, l, r - 1, fDp, gDp);
                ans = Math.min(p1, p2);
            }
            gDp[l][r] = ans;
            return ans;

        }

        private static int f(int[] arr, int l, int r, int[][] fDp, int[][] gDp) {
            if (fDp[l][r] != -1) {
                return fDp[l][r];
            }
            int ans = 0;
            if (l == r) {
                ans = arr[l];
            }else{
                int p1 = arr[l] + g(arr, l + 1, r, fDp, gDp);
                int p2 = arr[r] + g(arr, l, r - 1, fDp, gDp);
                ans = Math.max(p1, p2);
            }
            fDp[l][r] = ans;
            return ans;
        }
    }

    static class DP {
        public static int win(int[] arr) {
            if (arr.length == 0 || arr == null) {
                return 0;
            }
            int n = arr.length;
            int[][] fDp = new int[n][n];
            int[][] gDp = new int[n][n];
            for (int i = 0; i < n; i++) {
                //当l==r时 fDp的状态
                fDp[i][i] = arr[i];
            }
            //对角线的位置已经设置好了
            for (int startCol = 1; startCol < n; startCol++) {
                int l = 0;//row
                int r = startCol;//col
                //防止列越界
                while (r < n) {
                    fDp[l][r] = Math.max(arr[l] + gDp[l + 1][r], arr[r] + gDp[l][r - 1]);
                    gDp[l][r] = Math.min(fDp[l + 1][r], fDp[l][r - 1]);
                    l++;
                    r++;

                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(fDp[i][j]);
                    System.out.print(",");
                }
                System.out.println();
            }
            System.out.println("--------");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(gDp[i][j]);
                    System.out.print(",");
                }
                System.out.println();
            }

            return Math.max(fDp[0][n - 1], gDp[0][n - 1]);
        }
    }

    static class DP1 {
        public static int win(int[] arr) {
            if (arr.length == 0 || arr == null) {
                return 0;
            }
            int n = arr.length;

            int[][] fDp = new int[n][n];
            int[][] gDp = new int[n][n];
            for (int i = 0; i < n; i++) {
                fDp[i][i] = arr[i];
            }
            for (int i = 1; i < n; i++) {
                int row = 0;
                int col = i;
                while (col < n) {
                    fDp[row][col] = Math.max(arr[row] + gDp[row + 1][col], arr[col] + gDp[row][col - 1]);
                    gDp[row][col] = Math.min(fDp[row + 1][col], fDp[row][col - 1]);
                    row++;
                    col++;
                }
            }
            return Math.max(fDp[0][n - 1], gDp[0][n - 1]);

        }
    }



    public static void main(String[] args){
        int maxSize = 30;
        int range = 60;
        int[] arr = randomArray(maxSize, range);

//        arr = new int[]{9, 8, 3, 5, 2};
        System.out.println(Recursion1.win(arr));
        System.out.println(RecursionDp1.win(arr));
        System.out.println(DP1.win(arr));
    }

    public static int[] randomArray(int maxSize, int range) {
        int size = (int) (Math.random() * maxSize + 1);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (range * Math.random() + 1);
        }
        return arr;
    }

}
