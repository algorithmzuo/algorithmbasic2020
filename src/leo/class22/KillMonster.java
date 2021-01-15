package leo.class22;

/**
 * @author Leo
 * @ClassName KillMonster
 * @DATE 2021/1/14 9:06 下午
 * @Description
 *
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class KillMonster {

    static class Recursion{
        public static double kill(int n, int m, int k) {
            if (n < 1 || m < 1 || k < 1) {
                return 0;
            }
            long ans = process(n, m, k);
            return (double) ans / Math.pow(m + 1, k);
        }

        private static long process(int hp, int m, int rest) {
            if (hp <= 0) {
                return (long) Math.pow(m + 1, rest);
            }
            if (rest == 0) {
                return hp == 0 ? 1 : 0;
            }
            int ans = 0;
            for (int i = 0; i <= m; i++) {
                ans += process(hp - i, m, rest - 1);
            }
            return ans;

        }

    }

}
