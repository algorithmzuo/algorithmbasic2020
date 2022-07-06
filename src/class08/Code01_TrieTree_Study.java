package class08;

import class16.Node;
import util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 前缀树
 * 哈希表增删改查是O(1)的时间复杂度，但是前提是字串的长度是可忽略的，如果不可忽略那么复杂度为O(n字符长度)
 * 查询字符串出现几次，并且已字符开头的字串有多少，这些hash表无法统计
 * 前缀树的时间复杂度也是O(n字符长度)
 * 所以前缀树和哈希表在时间复杂度上时一样的，但是他可以支持的查询方式更多
 */
public class Code01_TrieTree_Study {
    public static class Node1{
        public int pass;
        public int end;
        public Node1 nexts[];
        public Node1(){
            pass = 0;
            end = 0;
            nexts = new Node1[26];
        }

        @Override
        public String toString() {
            return "Node1{" +
                    "pass=" + pass +
                    ", end=" + end +
                    ", nexts=" + Arrays.toString(nexts) +
                    '}';
        }
    }

    public static class Trie1{
        public Node1 root;

        public Trie1(){
            root = new Node1();
        }

        /**
         * 前缀树插入新的字串
         * 把字符串转为字符数组插入并统计
         * @param str
         */
        public void insert(String str) {
            if (StringUtils.isEmpty(str)) {
                return;
            }
            char[] arr = str.toCharArray();
            Node1 node = root;
            node.pass++;
            int index = 0;
            for (int i =0 ;i < arr.length;i++) {
                index = arr[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node1();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        /**
         * 前缀树删除值
         * @param str
         */
        public void delete(String str){
            if (search(str) == 0 ) {
                return;
            }
            char[] chars = str.toCharArray();
            Node1 node = root;
            node.pass--;
            int index = 0;
            for (int i= 0;i < chars.length;i++) {
                index = chars[i] - 'a';
                if (--node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;
        }

        /**
         * 前缀树查找字符串出现过几次
         * 如果有某个字符不存在为null，则返回不存在
         * @param str
         */
        public int search(String str) {
            if (StringUtils.isEmpty(str)) {
                return 0;
            }
            char[] chars = str.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0;i < chars.length;i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;

        }

        /**
         * 查看有多少个以该字符为前缀的字符串
         * @param str
         * @return
         */
        public int prefixNumber(String str) {
            if (str.isEmpty()) {
                return 0;
            }
            char[] chars = str.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0;i < chars.length;i++){
                index = chars[i] -'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }


    public static class Node2{
        public int pass;
        public int end;
        public HashMap<Integer,Node2> nexts;
        public Node2(){
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }

        @Override
        public String toString() {
            return "Node2{" +
                    "pass=" + pass +
                    ", end=" + end +
                    ", nexts=" + nexts +
                    '}';
        }
    }

    public static class Trie2{
        Node2 root;
        public Trie2(){
            root = new Node2();
        }

        public void insert(String str) {
            if (str.isEmpty()) {
                return;
            }
            Node2 node = root;
            char[] chars = str.toCharArray();
            int index = 0;
            for (int i = 0;i < chars.length;i++) {
                index = chars[i];
                if (node.nexts.get(index) == null) {
                    node.nexts.put(index,new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String str) {
            if (search(str) <= 0) {
                return;
            }
            char[] chars = str.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0;i < chars.length;i++) {
                index = chars[i];
                if (--node.nexts.get(index).pass ==0) {
                    node.nexts.remove(index);
                    return;
                }
                node = node.nexts.get(index);
            }
            node.end--;
        }

        /**
         * 查找该字符传出现过几次
         * @param str
         * @return
         */
        public int search(String str) {
            if (str.isEmpty()) {
                return 0 ;
            }
            char[] chars = str.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0;i < chars.length;i++) {
                index = chars[i];
                if (node.nexts.get(index) == null) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        /**
         * 查找该字符传出现过几次
         * @param str
         * @return
         */
        public int prefixNumber(String str) {
            if (str.isEmpty()) {
                return 0 ;
            }
            char[] chars = str.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0;i < chars.length;i++) {
                index = chars[i];
                if (node.nexts.get(index) == null) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }

    }

    public static class Right{
        @Override
        public String toString() {
            return "Right{" +
                    "strArr=" + strArr +
                    '}';
        }

        public HashMap<String,Integer> strArr  ;

        public Right(){
            strArr = new HashMap<>();
        }
        public void insert(String str) {
            if (strArr.containsKey(str)) {
                strArr.put(str,strArr.get(str) + 1);
            }else {
                strArr.put(str,1);
            }
        }

        public void delete(String str) {
            if (strArr.containsKey(str)) {
                if (strArr.get(str) == 1) {
                    strArr.remove(str);
                }else {
                    strArr.put(str, strArr.get(str) - 1);
                }
            }
        }

        public int search(String word) {
            if (!strArr.containsKey(word)) {
                return 0;
            } else {
                return strArr.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : strArr.keySet()) {
                if (cur.startsWith(pre)) {
//                    count++;
                    count+=strArr.get(cur);
                }
            }
            return count;
        }
    }


    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 26);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 10;
        int strLen = 5;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    System.out.println("search:"+arr[j]+
                            " ans1:"+ ans1 + " ans2:" + ans2 + " ans3:" + ans3+
                            "   arry" + Arrays.toString(arr));
                    if (ans1 != ans2 || ans2 != ans3) {

                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("prefixNumber:"+arr[j]+
                                " ans1:"+ ans1 + " ans2:" + ans2 + " ans3:" + ans3+
                                "   arry" + Arrays.toString(arr));
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
