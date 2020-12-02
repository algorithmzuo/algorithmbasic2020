package leo.class05_08;

import java.util.HashMap;

/**
 * @author Leo
 * @ClassName TrieTree_Map
 * @DATE 2020/12/2 10:33 上午
 * @Description next 用map实现
 */
public class TrieTree_Map {

    private class Node {
        private int pass;
        private int end;
        private HashMap<Integer,Node> next;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.next = new HashMap<>();
        }

    }

    private Node root;

    public TrieTree_Map() {
        this.root = new Node();
    }

    public void insert(String word) {
        if (word == null || "".equals(word.trim())) {
            return;
        }
        Node node = this.root;
        root.pass++;
        int path;
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            path = chars[i];
            if (node.next.get(path) == null) {
                node.next.put(path, new Node());
            }
            node = node.next.get(path);
            node.pass++;
        }
        node.end++;

    }

    public void delete(String word) {
        if (search(word) == 0) {
            return;
        }
        Node node = this.root;
        node.pass--;
        int path;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            path = chars[i];
            if (--node.next.get(path).pass == 0) {
                node.next.remove(path);
                return;
            }
            node = node.next.get(path);
        }
        node.end--;

    }

    public int search(String word) {
        if (word == null || "".equals(word.trim())) {
            return 0;
        }
        Node node = this.root;
        int path;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            path = chars[i];
            if (node.next.get(path) == null) {
                return 0;
            }
            node = node.next.get(path);
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
            path = chars[i];
            if (node.next.get(path) == null) {
                return 0;
            }
            node = node.next.get(path);
        }
        return node.pass;
    }

}
class TrieTree_Map_Test {
    private HashMap<String,Integer> map;
    public TrieTree_Map_Test(){
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

class TrieTree_MapMain{

    public static void main(String[] args){
        int strArrayLen = 100;
        int strLen = 50;
        int testTime = 10000;
        System.out.println("start");
        for (int i = 0; i < testTime; i++) {
            TrieTree_Map trieTree_map = new TrieTree_Map();
            TrieTree_Map_Test test = new TrieTree_Map_Test();
            String[] strings = generateRandomString(strArrayLen, strLen);
            for (int j = 0; j < strings.length; j++) {
                int random = (int) Math.random();

                if (random < 0.25) {
                    trieTree_map.insert(strings[j]);
                    test.insert(strings[j]);
                }else if (random<0.5){
                    trieTree_map.delete(strings[j]);
                    test.delete(strings[j]);
                }else if(random<0.75){
                    int mapCount = trieTree_map.search(strings[j]);
                    int testCount = test.search(strings[j]);
                    if (mapCount != testCount) {
                        System.out.println("mapCount : " + mapCount + " testCount : " + testCount);
                        System.out.println("count fuck!");
                        break;
                    }
                }else{
                    int mapPreCount = trieTree_map.prefixNumber(strings[j]);
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
