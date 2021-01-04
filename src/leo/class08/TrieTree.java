package leo.class08;

import java.util.HashMap;

/**
 * @author Leo
 * @ClassName TrieTree
 * @DATE 2020/12/1 5:06 下午
 * @Description 前缀树
 */
class TrieTree {

    public class Node{
        int pass;
        int end;
        Node[] next;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.next = new Node[26];
        }
    }

    private Node root;

    public TrieTree() {
        this.root = new Node();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] str = word.toCharArray();
        int path;
        Node node = this.root;
        node.pass++;
        for (int i = 0; i < str.length; i++) {
            path = str[i] - 'a';
            if (node.next[path] == null) {
                node.next[path] = new Node();
            }
            node.pass++;
            node = node.next[path];
        }
        node.end++;
    }

    public void delete(String word) {
        if (search(word) == 0) {
            return;
        }
        char[] chars = word.toCharArray();
        Node node = this.root;
        node.pass--;
        int path;
        for (int i = 0; i < chars.length; i++) {
            path = chars[i] - 'a';
            if (--node.next[path].pass == 0) {
                node.next[path] = null;
                return;
            }
            node = node.next[path];

        }
        node.end--;

    }

    public int search(String word) {
        if (word == null || "".equals(word.trim())) {
            return 0;
        }
        char[] chars = word.toCharArray();
        Node node = this.root;
        int path;
        for (int i = 0; i < chars.length; i++) {
            path = chars[i] - 'a';
            if (node.next[path] == null) {
                return 0;
            }
            node = node.next[path];
        }
        return node.end;
    }

    public int prefixNumber(String word) {

        if (word == null || "".equals(word.trim())) {
            return 0;
        }
        Node node = this.root;
        char[] chars = word.toCharArray();
        int path;
        for (int i = 0; i < chars.length; i++) {
            path = chars[i] - 'a';
            if (node.next[path] == null) {
                return 0;
            }
            node = node.next[path];
        }

        return node.pass;
    }

}

class TrieTree_Test{
    private HashMap<String,Integer> map;
    public TrieTree_Test(){
        this.map = new HashMap<>();
    }

    public void insert(String word) {
        if (word.trim().length() == 0 || word == null) {
            return;
        }
        if (map.containsKey(word)) {
            map.put(word, map.get(word) + 1);
        }else{
            map.put(word, 1);
        }
    }

    public void delete(String word) {
        if (this.search(word) == 0) {
            return;
        }
        if (this.map.get(word) == 1) {
            map.remove(word);
        }else{
            this.map.put(word, this.map.get(word) - 1);
        }
    }

    public int search(String word) {
        if (word.trim().length() == 0 || word == null) {
            return 0;
        }
        if (map.containsKey(word)) {
            return map.get(word);
        }
        return 0;
    }

    public int prefixNumber(String word) {
        if (word.trim().length() == 0 || word == null) {
            return 0;
        }
        int count = 0;
        for (String str : this.map.keySet()) {
            if (str.startsWith(word)) {
                count += this.map.get(str);
            }
        }
        return count;
    }
}

class TrieTree_Main {
    public static void main(String[] args){
        int strArrayLen = 100;
        int strLen = 50;
        int testTime = 10000;
        System.out.println("start");
        for (int i = 0; i < testTime; i++) {
            TrieTree trieTree = new TrieTree();
            TrieTree_Test test = new TrieTree_Test();
            String[] strings = generateRandomString(strArrayLen, strLen);
            for (int j = 0; j < strings.length; j++) {
                int random = (int) Math.random();

                if (random < 0.25) {
                    trieTree.insert(strings[j]);
                    test.insert(strings[j]);
                }else if (random<0.5){
                    trieTree.delete(strings[j]);
                    test.delete(strings[j]);
                }else if(random<0.75){
                    int mapCount = trieTree.search(strings[j]);
                    int testCount = test.search(strings[j]);
                    if (mapCount != testCount) {
                        System.out.println("mapCount : " + mapCount + " testCount : " + testCount);
                        System.out.println("count fuck!");
                        break;
                    }
                }else{
                    int mapPreCount = trieTree.prefixNumber(strings[j]);
                    int testPreCount = test.prefixNumber(strings[j]);
                    if (mapPreCount != testPreCount) {
                        System.out.println("mapPreCount : " + mapPreCount + " testPreCount : " + testPreCount);
                        System.out.println("pre count fuck!");
                        break;
                    }
                }
            }
        }
        System.out.println("end");

    }


    public static String[] generateRandomString(int strArrayLen, int strLen) {
        String[] strArray = new String[(int) (strArrayLen * Math.random() + 1)];

        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = generateRandomString(strLen);
        }
        return strArray;
    }

    private static String generateRandomString(int strLen) {
        char[] chars = new char[(int) (Math.random() * strLen + 1)];
        for (int i = 0; i < chars.length; i++) {
            int value = (int) (Math.random() * 6);
            chars[i] = (char) (97 + value);
        }
        return String.valueOf(chars);
    }
}
