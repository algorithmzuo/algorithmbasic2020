package aaa;

public class Test {

    static class solution0 {
        public static int getIndexOf(String s1,String s2) {
            if(s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
                return -1;
            }
            char[] str1 = s1.toCharArray();
            char[] str2 = s2.toCharArray();
            int x = 0;
            int y = 0;
            int[] next = getNextArray(str2);
            while(x < str1.length && y < str2.length) {
                if(str1[x] == str2[y]) {
                    x++;y++;
                }else if(next[y] == -1) {
                    x++;
                }else {
                    y = next[y];
                }
            }
            return y == str2.length ? x-y : -1;
        }

        public static int[] getNextArray(char[] str2) {
            if(str2.length == 1) {
                return new int[]{-1};
            }
            int[] next = new int[str2.length];
            next[0] = -1;
            next[1] = 0;
            int i = 2;
            int cn = 0;
            while(i < next.length) {
                if(str2[i-1] == str2[cn]) {
                    next[i++] = ++cn;
                }else if (cn > 0) {
                    cn = next[cn];
                }else {
                    next[i++] = 0;
                }
            }
            return next;
        }
    }

    static class solution1 {
        public static int manacher(String s) {
            if(s == null || s.length() == 0) return 0;
            char[] str = manacherString(s);
            int[] pArr = new int[str.length];
            int C = -1;
            int R = -1;
            int max = Integer.MAX_VALUE;
            for (int i = 0; i < str.length; i++) {
                pArr[i] = R > i ? Math.min(pArr[2*C-1],R-i) : 1;
                while (i+pArr[i] < str.length && i-pArr[i] > -1) {
                    if(str[i+pArr[i]] == str[i-pArr[i]]) {
                        pArr[i]++;
                    }else {
                        break;
                    }
                }
                if(i-pArr[i] > R) {
                    R = i + pArr[i];
                    C = i;
                }
                max = Math.max(max,pArr[i]);
            }
            return max-1;
        }

        public static char[] manacherString(String str) {
            char[] charArr = str.toCharArray();
            char[] res = new char[str.length()*2+1];
            int index=0;
            for (int i = 0; i < res.length; i++) {
                res[i] = (i&1) == 0 ? '#' : charArr[index++];
            }
            return res;
        }

    }

    static class solution2 {
        public static int getIndexOf(String s1 ,String s2) {
            if(s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) return -1;
            char[] str1 = s1.toCharArray();
            char[] str2 = s2.toCharArray();
            int x = 0;
            int y = 0;
            int[] next = getNextArray(str2);
            while (x < str1.length && y < str2.length) {
                if(str1[x] == str2[y]) {
                    x++;
                    y++;
                }else if(next[y] == -1) {
                    x++;
                }else {
                    y = next[y];
                }
            }
            return y == str2.length ? x-y : -1;
        }

        public static int[] getNextArray(char[] str2) {
            if(str2.length == 1) return new int[]{-1};
            int[] next = new int[str2.length];
            next[0] = -1;
            next[1] = 0;
            int i =2;
            int cn = 0;
            while (i < next.length){
                if(str2[i-1] == str2[cn]) {
                    next[i++] = ++cn;
                } else if(cn > 0) {
                    cn = next[cn];
                } else {
                    next[i++] = 0;
                }
            }

            return next;
        }

    }


































}
