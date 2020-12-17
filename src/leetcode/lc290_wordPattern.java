package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Leo
 * @ClassName lc290_wordPattern
 * @DATE 2020/12/16 11:34 上午
 * @Description
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 */
public class lc290_wordPattern {

    public static boolean wordPattern(String pattern, String s) {
        char[] p = pattern.toCharArray();
        String[] sArray = s.split(" ");
        if (p.length != sArray.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0;i < sArray.length; i++) {
            if(!map.containsKey(p[i])) {
                if (set.contains(sArray[i])){
                    return false;
                }
                map.put(p[i],sArray[i]);
                set.add(sArray[i]);
            } else {
                if(!map.get(p[i]).equals(sArray[i])){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        boolean b = wordPattern("abba","dog dog dog dog");
        System.out.println(b);
    }
}
