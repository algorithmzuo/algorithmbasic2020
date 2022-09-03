package class18;

/**
 * base case:
 * 对于先手而言有三种情况：
 *  L == R  只剩最后一张牌，那么就先手取这张牌， return arr[L]
 *  L != R  有两种情况 1.f(arr,L,R)1 = arr[L] + g(arr,L+1,R)
 *                   2.f(arr,L,R)2 = arr[R] + g(arr,L,R-1)
 *                   取最大：Max(f(arr,L,R)1,f(arr,L,R)2);
 * 对于后手而言有三种情况：
 *  后手只能被动的等待先手完成选择
 *  L == R  只剩最后一张牌，那么后手返回0
 *  L != R  有两种情况 1.g(arr,L,R)1 = f(arr,L+1,R) 先手取了L
 *                   2.g(arr,L,R)2 = f(arr,L,R-1) 先手取了R
 *                   取最大：Min(g(arr,L,R)1,g(arr,L,R)2);
 *                   这里为什么取min？
 *                   g1，g2都是先手取l或者取R的最优解，那么g就 只能被动接受那个最小的值，因为f决定聪明只会取完最大的留下最小的
 */
public class Code02_CardsInLine_Study {
    private static int ways1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr,0,arr.length - 1);
        int back = g1(arr,0,arr.length -1);
        return Math.max(first,back);
    }

    private static int g1(int[] arr, int l, int r) {
        // 对于后手，只剩一张牌时，先手已经拿完，后手什么也拿不到
        if (l == r){
            return 0;
        }
        int p1 = f1(arr,l+1,r); // 对手拿走了l位置
        int p2 = f1(arr,l,r-1);// 对手拿走了r位置
        return Math.min(p1,p2);
    }

    private static int f1(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int p1 = arr[l] + g1(arr,l+1,r);
        int p2 = arr[r] + g1(arr,l,r-1);
        return Math.max(p1,p2);
    }

    /**
     * 添加傻缓存
     *
     * @param arr
     */
    private static int ways2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // 这里两个函数之间互相引用，可以使用两个数组
        int[][] fdp = new int[N][N];
        int[][] gdp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fdp[i][j] = -1;
                gdp[i][j] = -1;
            }
        }
        // 获取先手和后手取完数的最大值
        int first = f2(arr,0,arr.length - 1,fdp,gdp);
        int second = g2(arr,0,arr.length - 1,fdp,gdp);
        return Math.max(first,second);
    }

    private static int g2(int[] arr, int l, int r, int[][] fdp, int[][] gdp) {
        if (gdp[l][r] != -1) {
            return gdp[l][r];
        }
        int ans = 0;
        // l==r 只剩最后一个数，后手肯定拿不到
        if (l != r){
            // 后手等先手拿完后取最小值
            int p1 = f2(arr,l+1,r,fdp,gdp);
            int p2 = f2(arr,l,r-1,fdp,gdp);
            ans = Math.min(p1,p2);
        }
        gdp[l][r] = ans;
        return ans;
    }

    private static int f2(int[] arr, int l, int r, int[][] fdp, int[][] gdp) {
        if (fdp[l][r] != -1) {
            return fdp[l][r];
        }
        int ans = 0;
        if (l == r) {
            ans = arr[l];
        }else {
            int p1 = arr[l] + g2(arr,l+1,r,fdp,gdp);
            int p2 = arr[r] + g2(arr,l,r-1,fdp,gdp);
            ans = Math.max(p1,p2);
        }
        fdp[l][r] = ans;
        return ans;
    }


    /**
     * 傻缓存改严格依赖
     * 这里在设计两个表，一个f表，一个g表，其中f表的元素依赖于g表同位置的左元素和下元素，g表的元素依赖于f表同位置的左元素和下元素
     * 所以这个表格的依赖是对角线的依赖
     */
    private static int ways3(int[] arr) {
        if (arr == null || arr.length ==0 ){
            return 0;
        }
        int N = arr.length;
        int[][] fdp = new int[N][N];
        int[][] gdp = new int[N][N];
        for (int i = 0; i < N; i++) {
            fdp[i][i] = arr[i];
        }
        // 画出表格可以知道，r == l的对角线已经知道值，因此对角线是从col = 1开始
        // 这个循环是按照对角线取填充元素
        for (int startCol = 1; startCol < N; startCol++) {
            int l = 0; // 代表行
            int r = startCol; // 代表列
            //在求对角线元素时，行不会超，但是列会超出范围
            while (r < N) {
                fdp[l][r] = Math.max(arr[l] + gdp[l + 1][r],arr[r] +gdp[l][r - 1]);
                gdp[l][r] = Math.min(fdp[l+1][r],fdp[l][r-1]);
                l++;
                r++;
            }
        }
        return Math.max(fdp[0][N-1],gdp[0][N-1]);
    }

    public static void main(String[] args) {
        int[] arr = {5,7,4,5,8,1,6,0,3,4,6,1,7};
        System.out.println(ways1(arr));
        System.out.println(ways2(arr));
        System.out.println(ways3(arr));
    }
}
