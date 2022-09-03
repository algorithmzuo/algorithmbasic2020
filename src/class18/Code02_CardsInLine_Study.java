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
 *                   g1，g2都是先手取l或者取R的最优解
 */
public class Code02_CardsInLine_Study {

}
