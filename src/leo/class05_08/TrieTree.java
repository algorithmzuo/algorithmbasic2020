package leo.class05_08;

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
