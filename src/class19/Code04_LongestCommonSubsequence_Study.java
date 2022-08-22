package class19;

/**
 * 求最长公共回文
 */
public class Code04_LongestCommonSubsequence_Study {
    public static int longestCommonSubsequece(String s1,String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        //尝试
        return process(chars1,chars2,chars1.length-1,chars2.length - 1);
    }

    /**
     * base case
     * @param s1
     * @param s2
     * @param i
     * @param j
     * @return
     */
    private static int process(char[] s1, char[] s2, int i, int j) {
        if (i == 0 && j==0) {
            return s1[i] == s2[j] ? 1 : 0;
        }else if (i == 0){
            if (s1[i] == s2[j]) {
                return 1;
            }else {
                return process(s1,s2,i,j-1);
            }
        }else if (j ==0) {
            if (s1[i] == s2[j]) {
                return 1;
            }else {
                return process(s1,s2,i - 1,j);
            }
        }else {
            // 此时两个都没有到最后一个字符
            //s1[i] 不是回文的一个字串
            int p1 = process(s1,s2,i-1,j);
            //s2[j]不是回文的一个字符
            int p2 = process(s1,s2,i,j-1);
            // s1[i] s2[j]都是回文的字串，那么要求俩字符相等
            int p3 = s1[i] == s2[j] ? (1 + process(s1,s2,i-1,j-1)):0;
            return Math.max(Math.max(p1,p2),p3);
        }
    }

    private static int process2(String s1,String s2) {
        if (s1 == null || s2 == null || s1.length() ==0 || s2.length() == 0) {
            return 0;
        }

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int m = chars1.length;
        int n  = chars2.length;

        int[][] result = new int[m][n];
        result[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int i = 1; i < m; i++) {
            result[i][0] = chars1[i] == chars2[0] ? 1 :result[i-1][0];
        }
        for (int j = 1; j < n; j++) {
            result[0][j] = chars1[0] == chars2[j] ? 1 : result[0][j-1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j<n;j++) {
                int p1 = result[i-1][j];
                int p2 = result[i][j-1];
                int p3 = chars1[i] == chars2[j] ? (1 + result[i-1][j-1]):0;
                result[i][j] = Math.max(Math.max(p1,p2),p3);
            }
        }
        return result[m-1][n-1];
    }
}
