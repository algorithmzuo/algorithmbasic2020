package leo.class09_14;

import java.util.HashSet;

/**
 * @author Leo
 * @ClassName Light
 * @DATE 2020/12/22 4:19 下午
 * @Description
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Light {

    static class Code {
        static int minLight(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            char[] chars = s.toCharArray();
            int i = 0;
            int l = 0;
            while (i < chars.length) {
                if (chars[i] == 'X') {
                    i++;
                }else{
                    l++;
                    if (i + 1 == chars.length) {
                        break;
                    } else if (chars[i + 1] == 'X') {
                        i += 2;
                    }else {
                        i += 3;
                    }
                    }
            }
            return l;
        }
    }

    static class Violence {
        public static int minLight(String road) {
            if (road == null || road.length() == 0) {
                return 0;
            }
            return process(road.toCharArray(), 0, new HashSet<>());
        }

        // str[index....]位置，自由选择放灯还是不放灯
        // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
        // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
        public static int process(char[] str, int index, HashSet<Integer> lights) {
            if (index == str.length) { // 结束的时候
                for (int i = 0; i < str.length; i++) {
                    if (str[i] != 'X') { // 当前位置是点的话
                        if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                            return Integer.MAX_VALUE;
                        }
                    }
                }
                return lights.size();
            } else { // str还没结束
                // i X .
                int no = process(str, index + 1, lights);
                int yes = Integer.MAX_VALUE;
                if (str[index] == '.') {
                    lights.add(index);
                    yes = process(str, index + 1, lights);
                    lights.remove(index);
                }
                return Math.min(no, yes);
            }
        }
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        System.out.println("start");
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = Violence.minLight(test);
            int ans2 = Code.minLight(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }

}
