package class19;

import class33.Hash;
import com.sun.xml.internal.bind.v2.util.StackRecorder;

import java.util.HashMap;

public class Code03_StickersToSpellWord_Study {
    private static int minStickers(String[] stickers,String target) {
        int ans = process1(stickers,target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 返回组成target的最小贴纸数,这个方法会超时
     * @param stickers 贴纸数组
     * @param target 目标字符
     * @return
     */
    private static int process1(String[] stickers, String target) {
        if(target.length() ==0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first: stickers) {
            // 每一个图片都去试下，看目标数组还剩下什么字符
            String rest = minus(target,first);
            if (rest.length() != target.length()) {
                min = Math.min(min,process1(stickers,rest));
            }
        }
        // 这里min加1是要把进入递归之前的那次算上
        return min + (min == Integer.MAX_VALUE ? 0:1);
    }

    /**
     * 将两个字符串放到一个大小为26的数组中，相同字母增，轮到尝试数组first时，相同字母减，看最后还剩下什么字母凭借起来还原
     * @param target
     * @param first
     * @return
     */
    private static String minus(String target, String first) {
        char[] targets = target.toCharArray();
        char[] firsts = first.toCharArray();
        int[] result = new int[26];
        for (char ch:targets) {
            result[ch - 'a']++;
        }
        for (char ch : firsts) {
            result[ch-'a']--;
        }
        //剩余的字母还原
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i < 26;i++) {
            if (result[i] > 0) {
                for (int j = 0;j < result[i];j++) {
                    stringBuilder.append((char) (i + 'a'));
                }
            }
        }
        return stringBuilder.toString();
    }

    private static int minStackers2(String[] stickers,String target) {
        int N = stickers.length;
        // 用词频代替贴纸数组
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char cha:chars
                 ) {
                counts[i][cha - 'a']++;
            }
        }
        int ans= process2(counts,target);
        return ans == Integer.MAX_VALUE ? 0:ans;
    }

    private static int process2(int[][] counts, String target) {
        if (target.length() == 0 ) {
            return 0;
        }
        //target 做出词频统计
        char[] targetChar = target.toCharArray();
        int[] targetCount = new int[26];
        for (char tchar: targetChar
             ) {
            targetCount[tchar - 'a']++;
        }

        int N = counts.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0;i < N;i++) {
            // 取出第一张卡片
            int[] stick = counts[i];
            // 这里的优化点在于说，先判断下这些卡片中是否有目标字串第一个字母的卡片，有的话才开始
            // 相对于上一个方法，这里就少了很多尝试
            if (stick[targetChar[0]-'a'] > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                // 这个for循环相当于是上一个方法中的minus方法，看这张卡片拼完之后还剩下哪些图片
                for (int j = 0 ; j < 26;j++) {
                    if (targetCount[j] > 0) {
                        int nums = targetCount[j] - stick[j];
                        for (int k = 0 ; k < nums;k++) {
                            stringBuilder.append((char)(j + 'a'));
                        }
                    }
                }
                String rest = stringBuilder.toString();
                min = Math.min(min,process2(counts,rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

  /*<--------------------------------------------------------------------------------->*/
    // 上面的两种方式在面对数量稍大的情况下会超时，这里需要在优化下，使用傻缓存的方式
    public static int minStaickers3(String[] stickers,String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0;i < N ;i++) {
            char[] sticker = stickers[i].toCharArray();
            for (char c : sticker){
                counts[i][c-'a']++;
            }
        }

        HashMap<String ,Integer> dp = new HashMap<>();
        dp.put("",0);
        int ans = process3(counts,target,dp);
        return ans == Integer.MAX_VALUE ? -1:ans;
    }

    private static int process3(int[][] counts, String target, HashMap<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        char[] targetChar = target.toCharArray();
        int[] tcounts = new int[26];
        for (char ch : targetChar) {
            tcounts[ch - 'a']++;
        }

        int N = counts.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0 ;i < N;i++ ) {
            int[] stick = counts[i];
            if (stick[targetChar[0]-'a'] > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                // 这个for循环相当于是上一个方法中的minus方法，看这张卡片拼完之后还剩下哪些图片
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - stick[j];
                        for (int k = 0; k < nums; k++) {
                            stringBuilder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = stringBuilder.toString();
                min = Math.min(min, process3(counts, rest,dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target,ans);
        return ans;
    }
}

