package leo.class11_17;

import java.nio.file.Path;
import java.util.*;

/**
 * @author Leo
 * @ClassName PrintAllSubsquences
 * @DATE 2020/12/30 11:16 上午
 * @Description 打印字符串的问题
 */
public class PrintStr {

    /**
     * 打印一个字符串的全部子序列
     */
    static class Subs{
        public static List<String> subs(String string) {
            List<String> list = new ArrayList<>();
            if (string == null) {
                return list;
            }
            char[] chars = string.toCharArray();
            String path = "";
            p(chars, 0, list, path);
            return list;
        }

        private static void p(char[] chars, int i, List<String> list, String path) {
            if (i == chars.length) {
                list.add(path);
                return;
            };
            p(chars, i + 1, list, path);
            p(chars, i + 1, list, path + String.valueOf(chars[i]));
        }

    }

    /**
     * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
     */
    static class SubNoRepeat {
        public static List<String> subNoRepeat(String string) {
            List<String> list = new ArrayList<>();
            if (string == null) {
                return list;
            }
            HashSet<String> set = new HashSet<>();
            char[] chars = string.toCharArray();
            String path = "";
            p(chars, 0, set, path);
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
            return list;
        }

        private static void p(char[] chars, int i, HashSet<String> set, String path) {
            if (i == chars.length) {
                set.add(path);
                return;
            } else {
                p(chars, i + 1, set, path);
                p(chars, i + 1, set, path + String.valueOf(chars[i]));
            }

        }
    }

    /**
     * 打印一个字符串的全部排列
     */
    static class Permutations{

    }

    /**
     * 打印一个字符串的全部排列，要求不要出现重复的排列
     */
    static class PermutationsNoRepeat{

        public static String[] permutation(String s) {
            if (s == null || s.length() == 0) {
                return new String[]{};
            }
            char[] chars = s.toCharArray();
            List<String> list = new ArrayList<>();
            f(chars,0,list);
            return list.toArray(new String[list.size()]);
        }

        public static void f(char[] chars,int i,List<String> list) {
            if (i == chars.length) {
                list.add(String.valueOf(chars));
            }else {
                boolean[] verify = new boolean[26];
                for (int j = i; j < chars.length; j++) {
                    if (!verify[chars[j] - 'a']) {
                        verify[chars[j] - 'a'] = true;
                        swap(chars, i, j);
                        f(chars, i + 1, list);
                        swap(chars, i, j);
                    }
                }
            }
        }

        private static void swap(char[] chars,int i,int j) {
            if (chars[i] == chars[j] || i == j) {
                return;
            }
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }

    }
    public static void main(String[] args){
        String str = "abcc";
        List<String> subs = Subs.subs(str);
        System.out.println(subs.toString());
        List<String> subNoRepeat = SubNoRepeat.subNoRepeat(str);
        System.out.println(subNoRepeat.toString());

        String[] permutation = PermutationsNoRepeat.permutation(str);
        System.out.println(new ArrayList<String>(Arrays.asList(permutation)).toString());

    }
}
