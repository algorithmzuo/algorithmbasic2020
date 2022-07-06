package class15;

import java.util.*;

public class Code02_NumberOfIslands_Study {
    /**
     * 使用数组做并查集容器
     * @param board
     * @return
     */
    public static int numIsIland1(char[][] board) {return 0;}

    /**
     * 数组并查集
     */
    public static class UnionFind2{

    }

    /**
     * 方法2，使用Map并查集，当查询次数过多时做到时间复杂度为O（1）
     * 重点：区分唯一性，这里的1需要用一个类的引用来替代，不然无法区分不同的1
     * @param board
     * @return
     */
    public static int numIsIland2(char[][] board) {
        // 用Dot代替原数据中的'1'，区分不同的'1'。这里new Dot代表'1'，null代表'0'
        int row = board.length;
        int col = board[0].length;
        Dot[][] dots = new Dot[row][col];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0;i < row;i++) {
            for (int j = 0; j< col;j++) {
                if (board[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        UnionFind1<Dot> unionFind1 = new UnionFind1<>(dotList);
        // 单独处理0列的数据，0列的数据不需要考虑左方向的数据
        for (int j = 1;j < col;j++) {
            if (board[0][j-1] == '1' && board[0][j] == '1'){
                unionFind1.union(dots[0][j], dots[0][j - 1]);
            }
        }
        // 单独处理0行的数据，0行的数据不需要考虑上方向的数据
        for (int i = 1;i < row;i++) {
            if (board[i][0] == '1' && board[i-1][0] == '1') {
                unionFind1.union(dots[i][0],dots[i-1][0]);
            }
        }
        // 处理剩下的所有数据
        for (int i = 1;i < row;i++) {
            for (int j = 1; j < col;j++) {
                if (board[i][j] == '1') {
                    if (board[i-1][j] == '1') {
                        unionFind1.union(dots[i][j],dots[i-1][j]);
                    }
                    if (board[i][j-1] == '1') {
                        unionFind1.union(dots[i][j-1],dots[i][j]);
                    }
                }
            }
        }
        return unionFind1.sets();
    }

    public static class Dot{

    }

    /**
     * 并查集的三要素
     * 1.父类 parents
     * 2.指针集合 nodes
     * 3.集合大小 size
     *
     * 并查集的三个重要函数
     * 1.查找父类
     * 2.合并
     * 3.判断是否相同
     * @param <V>
     */
    public static class UnionFind1<V>{
        public Map<V,Node<V>> nodes;
        public Map<Node<V>,Node<V>> parents;
        public Map<Node<V>,Integer> size;
        public UnionFind1(List<V> board){
            nodes = new HashMap<>();
            parents = new HashMap<>();
            size = new HashMap<>();
            for (V value : board) {
                Node<V> node = new Node<>(value);
                nodes.put(value,node);
                parents.put(node,node);
                size.put(node,1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            while (cur != parents.get(cur)) {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(),cur);
            }
            return cur;
        }

        /**
         * 合并，小集合挂大集合
         * @param o1
         * @param o2
         */
        public void union(V o1,V o2){
            Node<V> find1 = findFather(nodes.get(o1));
            Node<V> find2 = findFather(nodes.get(o2));
            if (find1 != find2) {
                int size1 = size.get(find1);
                int size2 = size.get(find2);
                Node<V> max = size1 >= size2 ? find1:find2;
                Node<V> min = find1 == max ? find2 : find1;
                parents.put(min,max);
                size.put(max,size1 + size2);
                size.remove(min);
            }
        }
        public int sets(){
            return size.size();
        }
    }

    public static class Node<V>{
        V value;
        public Node(V value) {
            this.value =value;
        }
    }


    /**
     * 方法三：这个是判断循环判断上下左右四个方向是否有连同1的，时间复杂度是O(M*N)
     * 也就是感染的方式
     * @param board
     * @return
     */
    public static int numIsIland3(char[][] board){
        if (board == null || board.length == 0 ) {
            return 0;
        }
        int num = 0;
        for (int i = 0 ;i < board.length;i++) {
            for (int j = 0; j < board[0].length;j++) {
                if (board[i][j] == '1') {
                    num++;
                    infection(board,i,j);
                }
            }
        }
        return num;
    }

    /**
     * 把每个元素的上下左右四个方向都感染
     * @param board
     * @param i
     * @param j
     */
    public static void infection(char[][] board,int i,int j) {
        if (i < 0 ||  i >= board.length || j < 0 || j >= board[0].length || board[i][j] != '1') {
            return;
        }
        board[i][j] = '0';
        // 上
        infection(board,i- 1,j);
        // 下
        infection(board,i + 1,j);
        // 左
        infection(board,i,j - 1);
        // 右
        infection(board,i,j + 1);
    }

    public static void main(String[] args) {
        char[][] board = {{'1','0','0','1'},{'0','1','1','0'},{'0','1','1','1'},{'1','0','1','1'}};
        char[][] board1 = {{'1','0','0','1'},{'0','1','1','0'},{'0','1','1','1'},{'1','0','1','1'}};
        int result = numIsIland3(board);
        System.out.println(result);
        int resule2 = numIsIland2(board1);
        System.out.println(resule2);
    }
}
