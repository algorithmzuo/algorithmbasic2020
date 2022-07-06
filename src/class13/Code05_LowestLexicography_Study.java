package class13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 最小字典序
 * 两个字符串，
 */
public class Code05_LowestLexicography_Study {
    public String lowestString2(String[] strs){
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs,new newComparator());
        String ans ="";
        for (int i=0;i <strs.length;i++) {
            ans += strs[i];
        }
        return ans;
    }

    public class newComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2+o1);
        }
    }
}
