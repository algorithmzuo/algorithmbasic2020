package leo.class15;

import java.util.*;

/**
 * @author Leo
 * @ClassName NumberOfIslands
 * @DATE 2020/12/23 3:26 下午
 * @Description 岛问题1
 * 200
 * 测试链接：https://leetcode.com/problems/number-of-islands/
 */
public class NumberOfIslands {

    static class Code1 {
        static class C{
            public static int numIslands(char[][] grid) {
                int count = 0;
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        if (grid[i][j] == '1') {
                            count++;
                            infect(grid, i, j);
                        }
                    }
                }
                return count;
            }

            private static void infect(char[][] grid, int r, int c) {
                if (r < 0 || r == grid.length || c < 0 || c == grid[0].length || grid[r][c] != '1') {
                    return;
                }
                grid[r][c] = '2';
                infect(grid, r-1, c);
                infect(grid, r+1, c);
                infect(grid, r, c-1);
                infect(grid, r, c+1);
            }
        }
        static class C1 {
            public static int numIslands (char[][] grid) {
                int count = 0;
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        if (grid[i][j] == '1') {
                            count++;
                            infect(grid, i, j);
                        }
                    }
                }
                return count;
            }

            private static void infect(char[][] grid, int i, int j) {
                if (i < 0 || j == grid[0].length || j < 0 || i == grid.length || grid[i][j] != '1') {
                    return;
                }
                grid[i][j] = '2';
                infect(grid, i + 1, j);
                infect(grid, i - 1, j);
                infect(grid, i, j + 1);
                infect(grid, i, j - 1);
            }
        }

    }

    static class Code2 {

        static class C {
            public static int numIslands(char[][] grid) {
                List<Dot> list = new ArrayList<>();
                int r = grid.length;
                int c = grid[0].length;
                Dot[][] dot = new Dot[r][c];
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (grid[i][j] == '1') {
                            dot[i][j] = new Dot();
                            list.add(dot[i][j]);
                        }
                    }
                }
                UnionFind<Dot> unionFind = new UnionFind<>(list);
                //第一列每一行与上面合并,
                for (int i = 1; i < r; i++) {
                    if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                        unionFind.union(dot[i - 1][0], dot[i][0]);
                    }
                }
                //第一行每一列与左面合并.
                for (int i = 1; i < c; i++) {
                    if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                        unionFind.union(dot[0][i - 1], dot[0][i]);
                    }
                }
                //除了第一行和第一列 每个位置与自己的左侧和上方合并.
                for (int i = 1; i < r; i++) {
                    for (int j = 1; j < c; j++) {
                        if (grid[i][j] == '1') {
                            if (grid[i - 1][j] == '1') {
                                unionFind.union(dot[i][j], dot[i - 1][j]);
                            }
                            if (grid[i][j - 1] == '1') {
                                unionFind.union(dot[i][j], dot[i][j - 1]);

                            }

                        }
                    }
                }
                return unionFind.sets();
            }
            static class UnionFind<V> {
                HashMap<V,Node<V>> nodes ;
                HashMap<Node<V>,Node<V>> parents;
                HashMap<Node<V>,Integer> sizeMap;
                public UnionFind(List<V> list){
                    nodes = new HashMap<>();
                    parents = new HashMap<>();
                    sizeMap = new HashMap<>();
                    for (V dot : list) {
                        Node<V> node = new Node<V>(dot);
                        nodes.put(dot, node);
                        parents.put(node, node);
                        sizeMap.put(node, 1);
                    }
                }

                public Node<V> find(Node<V> node) {
                    Stack<Node> stack = new Stack<>();
                    while (node != parents.get(node)) {
                        stack.push(node);
                        node = parents.get(node);
                    }
                    while (!stack.isEmpty()) {
                        parents.put(stack.pop(), node);
                    }
                    return node;
                }

                public void union(V a, V b) {
                    Node<V> aF = find(nodes.get(a));
                    Node<V> bF = find(nodes.get(b));
                    if (aF != bF) {
                        int aSize = sizeMap.get(aF);
                        int bSize = sizeMap.get(bF);
                        Node big = aSize >= bSize ? aF : bF;
                        Node small = big == aF ? bF : aF;
                        parents.put(small, big);
                        sizeMap.put(big, aSize + bSize);
                        sizeMap.remove(small);
                    }
                }

                public int sets() {
                    return sizeMap.size();
                }
            }
            static class Dot {

            }
            static class Node<V> {
                V value;
                public Node(V v) {
                    this.value = v;
                }
            }
        }

        static class C1 {
            public int numIslands(char[][] grid) {
                List<Dot> list = new ArrayList<>();
                int r = grid.length;
                int c = grid[0].length;
                Dot[][] dot = new Dot[r][c];
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (grid[i][j] == '1') {
                            dot[i][j] = new Dot();
                            list.add(dot[i][j]);
                        }
                    }
                }
                UnionFind u = new UnionFind(list);
                //上左依次合并
                //第一行每一列与第二行每一列合并
                for (int i = 1; i < grid.length; i++) {
                    if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                        u.union(dot[i - 1][0], dot[i][0]);
                    }
                }
                //每行的第一列与第二列合并
                for (int i = 1; i < grid[0].length; i++) {
                    if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                        u.union(dot[0][i - 1], dot[0][i]);
                    }
                }

                for (int i = 1; i < r; i++) {
                    for (int j = 1; j < c; j++) {
                        if (grid[i][j] == '1') {
                            if (grid[i - 1][j] == '1') {
                                u.union(dot[i][j], dot[i - 1][j]);
                            }
                            if (grid[i][j - 1] == '1') {
                                u.union(dot[i][j], dot[i][j - 1]);
                            }
                        }
                    }

                }
                return u.sets();
            }

            class UnionFind<V>{
                HashMap<V,Node<V>> nodes;
                HashMap<Node<V>,Node<V>> parent;
                HashMap<Node<V>,Integer> size;

                public UnionFind(List<V> list) {
                    nodes = new HashMap<>();
                    parent = new HashMap<>();
                    size = new HashMap<>();
                    for (int i = 0; i < list.size(); i++) {
                        Node node = new Node(list.get(i));
                        nodes.put(list.get(i), node);
                        parent.put(node, node);
                        size.put(node, 1);
                    }
                }

                public Node find(V v) {
                    Stack<Node> stack = new Stack<>();
                    Node cur = nodes.get(v);
                    while (cur != parent.get(cur)) {
                        stack.push(cur);
                        cur = parent.get(cur);
                    }
                    while (!stack.isEmpty()) {
                        parent.put(stack.pop(), cur);
                    }
                    return cur;
                }

                public void union(V a, V b) {
                    Node aF = find(a);
                    Node bF = find(b);
                    if (aF == bF) {
                        return;
                    }
                    int aSize = size.get(aF);
                    int bSize = size.get(bF);
                    Node big = aSize >= bSize ? aF : bF;
                    Node small = big == aF ? bF : aF;
                    parent.put(small, big);
                    size.put(big, aSize + bSize);
                    size.remove(small);
                }

                public int sets() {
                    return size.size();
                }
            }

            class Dot {

            }

            class Node<V> {
                V value ;
                public Node(V v) {
                    this.value = v;
                }
            }

        }


    }

    static class Code3 {
        //row col 对应下标 row * colTotal + col
        static class C {
            public static int numIslands(char[][] grid) {
                int row = grid.length;
                int col = grid[0].length;
                UnionFind u = new UnionFind(grid);
                //第一列中的元素与上合并
                for (int i = 1; i < row; i++) {
                    if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                        u.union(i - 1, 0, i, 0);
                    }
                }
                //第一行中的元素与左和合并
                for (int i = 1; i < col; i++) {
                    if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                        u.union(0, i - 1, 0, i);
                    }
                }
                //除了第一行和第一列每个元素与上 左 合并
                for (int i = 1; i < row; i++) {
                    for (int j = 1; j < col; j++) {
                        if (grid[i][j] == '1') {
                            if (grid[i - 1][j] == '1') {
                                u.union(i, j, i - 1, j);
                            }
                            if (grid[i][j - 1] == '1') {
                                u.union(i, j, i, j - 1);
                            }
                        }

                    }
                }
                return u.sets();
            }

            static class UnionFind {
                int[] parent;
                int[] size;
                int[] help;
                int sets;
                int col;

                public UnionFind(char[][] grid) {
                    int row = grid.length;
                    col = grid[0].length;
                    int n = row * col;
                    parent = new int[n];
                    size  = new int[n];
                    help = new int[n];
                    sets = 0;
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            if (grid[i][j] == '1') {
                                int index = getIndex(i, j);
                                parent[index] = index;
                                size[index] = 1;
                                sets++;
                            }
                        }
                    }
                }

                public int find(int i) {
                    int h = 0;
                    while (i != parent[i]) {
                        help[h++] = i;
                        i = parent[i];
                    }
                    for (h--; h >= 0; h--) {
                        parent[help[h]] = i;
                    }
                    return i;
                }

                public void union(int r1, int c1, int r2, int c2) {
                    int i1 = getIndex(r1, c1);
                    int i2 = getIndex(r2, c2);
                    int aF = find(i1);
                    int bF = find(i2);
                    if (aF != bF) {
                        int aSize = size[aF];
                        int bSize = size[bF];
                        int big = aSize >= bSize ? aF : bF;
                        int small = big == aF ? bF : aF;
                        parent[small] = big;
                        size[big] = bSize + aSize;
                        sets--;
                    }
                }

                public int sets() {
                    return sets;
                }
                int getIndex(int r, int c) {
                    return r * col + c;
                }
            }
        }

        class C1 {
            public int numIslands(char[][] grid) {
                UnionFind u = new UnionFind(grid);
                int row = grid.length;
                int col = grid[0].length;
                for (int i = 1; i < row; i++) {
                    if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                        u.union(i - 1, 0, i, 0);
                    }
                }
                for (int i = 1; i < col; i++) {
                    if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                        u.union(0, i - 1, 0, i);
                    }
                }
                for (int i = 1; i < row; i++) {
                    for (int j = 1; j < col; j++) {
                        if (grid[i][j] == '1') {
                            if (grid[i - 1][j] == '1') {
                                u.union(i, j, i - 1, j);
                            }
                            if (grid[i][j - 1] == '1') {
                                u.union(i, j, i, j - 1);
                            }
                        }

                    }
                }
                return u.sets();
            }
            class UnionFind {
                int[] parent;
                int[] size;
                int[] help;
                int col;
                int set;

                public UnionFind(char[][] grid) {
                    int row = grid.length;
                    col = grid[0].length;
                    int n = row * col;
                    parent = new int[n];
                    size = new int[n];
                    help = new int[n];
                    set = 0;
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            if (grid[i][j] == '1') {
                                int x = getIndex(i, j);
                                parent[x] = x;
                                size[x] = 1;
                                set++;
                            }
                        }
                    }

                }

                public int find(int i) {
                    int hi = 0;
                    while (i != parent[i]) {
                        help[hi++] = i;
                        i = parent[i];
                    }
                    for (hi--; hi >= 0; hi--) {
                        parent[help[hi]] = i;
                    }
                    return i;
                }
                public void union(int i1, int j1, int i2, int j2) {
                    int a = getIndex(i1, j1);
                    int b = getIndex(i2, j2);
                    int aF = find(a);
                    int bF = find(b);
                    if (aF == bF) {
                        return;
                    }
                    int aSize = size[aF];
                    int bSize = size[bF];
                    int big = aSize >= bSize ? aF : bF;
                    int small = big == aF ? bF : aF;
                    parent[small] = big;
                    size[big] = aSize + bSize;
                    set--;
                }

                public int sets() {
                    return set;
                }
                public int getIndex(int i, int j) {
                    return i * col + j;
                }
            }
        }
    }

}
