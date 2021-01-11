package leo.class19;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leo
 * @ClassName StickersToSpellWord
 * @DATE 2021/1/7 5:52 下午
 * @Description
 * 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word
 * 给定一个字符串str，给定一个字符串类型的数组arr。
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 */
public class StickersToSpellWord {

    static class Recursion {
        public static int minStickers(String[] stickers, String target) {
            if (stickers == null || stickers.length == 0) {
                return -1;
            }
            int ans = process(stickers, target);
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        /**
         * 功能描述 :
         * @author Leo
         * @date 2021/1/8 10:40 上午
         * @param stickers 贴纸
         * @param target 剩余目标
         * @throw
         * @return int
         */
        private static int process(String[] stickers, String target) {
            if (target.length() == 0) {
                return 0;
            }
            int min = Integer.MAX_VALUE;
            for (String sticker : stickers) {
                String rest = minus(sticker, target);
                //rest 剩余目标
                //如果剩余目标长度不等于目标长度,代表匹配成功了,继续匹配剩余的目标
                if (rest.length() != target.length()) {
                    //如果没有有效解,会返回MAX_VALUE
                    min = Math.min(min, process(stickers, rest));
                }
            }
            return min + (min == Integer.MAX_VALUE ? 0 : 1);
        }

        private static String minus(String sticker, String target) {
            char[] s = sticker.toCharArray();
            char[] t = target.toCharArray();
            int[] count = new int[26];
            for (int i = 0; i < t.length; i++) {
                count[t[i] - 'a']++;
            }
            for (int i = 0; i < s.length; i++) {
                count[s[i] - 'a']--;
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    for (int j = 0; j < count[i]; j++) {
                        sb.append((char) (i + 'a'));
                    }
                }
            }
            return sb.toString();
        }
    }

    static class Recursion1 {
        public static int minStickers(String[] stickers, String target) {
            if (target == null || target.length() == 0 || stickers == null || stickers.length == 0) {
                return -1;
            }
            int ans = process(stickers, target);
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        private static int process(String[] stickers, String target) {
            if (target.length() == 0) {
                return 0;
            }
            int min = Integer.MAX_VALUE;
            for (String sticker : stickers) {
                String rest = minus(sticker, target);
                if (rest.length() != target.length()) {
                    min = Math.min(min, process(stickers, rest));
                }
            }
            return min + (min == Integer.MAX_VALUE ? 0 : 1);
        }

        private static String minus(String sticker, String target) {
            int[] count = new int[26];
            char[] sChar = sticker.toCharArray();
            char[] tChar = target.toCharArray();
            for (int i = 0; i < tChar.length; i++) {
                count[tChar[i] - 'a']++;
            }
            for (int i = 0; i < sChar.length; i++) {
                count[sChar[i] - 'a']--;
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    for (int j = 0; j < count[i]; j++) {
                        sb.append((char) (i + 'a'));
                    }
                }
            }
            return sb.toString();
        }

    }



    static class DpZuo {
        public static int minStickers(String[] stickers, String target) {
            int N = stickers.length;
            // 关键优化(用词频表替代贴纸数组)
            int[][] counts = new int[N][26];
            for (int i = 0; i < N; i++) {
                char[] str = stickers[i].toCharArray();
                for (char cha : str) {
                    counts[i][cha - 'a']++;
                }
            }
            int ans = process(counts, target);
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        // stickers[i] 数组，当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
        // 每一种贴纸都有无穷张
        // 返回搞定target的最少张数
        // 最少张数
        public static int process(int[][] stickers, String t) {
            if (t.length() == 0) {
                return 0;
            }
            // target做出词频统计
            // target  aabbc  2 2 1..
            //                0 1 2..
            char[] target = t.toCharArray();
            int[] tcounts = new int[26];
            for (char cha : target) {
                tcounts[cha - 'a']++;
            }
            int N = stickers.length;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                // 尝试第一张贴纸是谁
                int[] sticker = stickers[i];
                // 最关键的优化(重要的剪枝!这一步也是贪心!)
                if (sticker[target[0] - 'a'] > 0) {
                    StringBuilder builder = new StringBuilder();
                    for (int j = 0; j < 26; j++) {
                        if (tcounts[j] > 0) {
                            int nums = tcounts[j] - sticker[j];
                            for (int k = 0; k < nums; k++) {
                                builder.append((char) (j + 'a'));
                            }
                        }
                    }
                    String rest = builder.toString();
                    min = Math.min(min, process(stickers, rest));
                }
            }
            return min + (min == Integer.MAX_VALUE ? 0 : 1);
        }
    }
    static class Dp {
        public static int minStickers(String[] stickers, String target) {

            int n = stickers.length;
            int[][] count = new int[n][26];
            for (int i = 0; i < count.length; i++) {
                char[] chars = stickers[i].toCharArray();
                for (char c : chars) {
                    count[i][c - 'a']++;
                }
            }
            HashMap<String, Integer> dp = new HashMap<>();
            dp.put("", 0);
            int ans = process(count, target,dp);
            return ans == Integer.MAX_VALUE ? -1 : ans;

        }

        private static int process(int[][] stickers, String target, HashMap<String, Integer> dp) {
            if (dp.containsKey(target)){
                return dp.get(target);
            }
            if (target.length() == 0) {
                return 0;
            }
            int min = Integer.MAX_VALUE;
            //target词频统计
            char[] t = target.toCharArray();
            int[] tCount = new int[26];
            for (char c : t) {
                tCount[c - 'a']++;
            }
            for (int i = 0; i < stickers.length; i++) {
                //第i号纸条
                int[] sticker = stickers[i];
                //通过target第一个字符比对,需要哪些贴纸(剪枝,贪心)
                if (sticker[t[0] - 'a'] > 0) {
                    StringBuffer sb = new StringBuffer();
                    //开始判断需要的纸条,以及剩下的target
                    for (int j = 0; j < 26; j++) {
                        int num = tCount[j] - sticker[j];
                        for (int k = 0; k < num; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                    String restTarget = sb.toString();
                    min = Math.min(min, process(stickers, restTarget, dp));

                }
            }
            int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
            dp.put(target, ans);
            return ans;
        }
    }

    static class Dp1{
        public static int minStickers(String[] stickers, String target) {
            int n = stickers.length;
            int[][] count = new int[n][26];

            for (int i = 0; i < n; i++) {
                char[] chars = stickers[i].toCharArray();
                for (char c : chars) {
                    count[i][c - 'a']++;
                }
            }
            Map<String, Integer> dp = new HashMap<>();
            dp.put("", 0);
            int ans = process(count, target, dp);
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        private static int process(int[][] stickers, String target, Map<String, Integer> dp) {
            if (dp.containsKey(target)) {
                return dp.get(target);
            }
            if (target.length() == 0) {
                return 0;
            }
            char[] t = target.toCharArray();
            //统计target词频
            int[] tCount = new int[26];
            for (char c : t) {
                tCount[c - 'a']++;
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < stickers.length; i++) {
                int[] sticker = stickers[i];
                if (sticker[t[0] - 'a'] > 0) {
                    StringBuffer sb = new StringBuffer();
                    for (int j = 0; j < 26; j++) {
                        int num = tCount[j] - sticker[j];
                        for (int k = 0; k < num; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                    String rest = sb.toString();
                    min = Math.min(min, process(stickers, rest, dp));
                }
            }
            min += min == Integer.MAX_VALUE ? 0 : 1;
            dp.put(target, min);
            return min;
        }
    }

    static class Dp2 {
        public static int minStickers(String[] stickers, String target) {
            if (target == null || target.length() == 0) {
                return -1;
            }
            int n = stickers.length;
            int[][] sCount = new int[n][26];
            for (int i = 0; i < n; i++) {
                char[] chars = stickers[i].toCharArray();
                for (char c : chars) {
                    sCount[i][c - 'a']++;
                }
            }
            Map<String, Integer> dp = new HashMap<>();
            dp.put("", 0);
            int ans = process(sCount, target, dp);
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        private static int process(int[][] stickers, String target, Map<String, Integer> dp) {
            if (dp.containsKey(target)) {
                return dp.get(target);
            }
            if (target.length() == 0) {
                return 0;
            }
            int[] tCount = new int[26];
            char[] t = target.toCharArray();
            for (char c : t) {
                tCount[c - 'a']++;
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < stickers.length; i++) {
                int[] sticker = stickers[i];
                //从target第一个字符开始,剪枝
                if (sticker[t[0] - 'a'] > 0) {
                    StringBuffer sb = new StringBuffer();
                    for (int j = 0; j < 26; j++) {
                        int mun = tCount[j] - sticker[j];
                        for (int k = 0; k < mun; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                    String rest = sb.toString();
                    min = Math.min(min, process(stickers, rest, dp));
                }
            }
            min += (min == Integer.MAX_VALUE ? 0 : 1);
            dp.put(target, min);
            return min;
        }

    }

    public static void main(String[] args){

        String[] stickers = {"heavy", "claim", "seven", "set", "had", "it", "dead", "jump", "design", "question", "sugar", "dress", "any", "special", "ground", "huge", "use", "busy", "prove", "there", "lone", "window", "trip", "also", "hot", "choose", "tie", "several", "be", "that", "corn", "after", "excite", "insect", "cat", "cook", "glad", "like", "wont", "gray", "especially", "level", "when", "cover", "ocean", "try", "clean", "property", "root", "wing"};
        String target = "travelbell";
        /*String[] stickers = {"heavy", "claim", "seven", "set", "had", "it", "dead", "sugar"};
        String target = "travelell";*/
        /*System.out.println(Recursion1.minStickers(stickers, target));
        System.out.println(Recursion.minStickers(stickers, target));*/
        System.out.println(Dp1.minStickers(stickers, target));
        System.out.println(Dp2.minStickers(stickers, target));

    }
}
