package leo.class09_15;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 * @ClassName NumberOfIslandsII
 * @DATE 2020/12/25 3:14 下午
 * @Description 305
 * 测试链接：https://leetcode.com/problems/number-of-islands-ii/
 */
public class NumberOfIslandsII {

    static class C {
        public static List<Integer> numIslands(int m, int n, int[][] positions) {
            UnionFind1 uf = new UnionFind1(m, n);
            List<Integer> ans = new ArrayList<>();
            for (int[] position : positions) {
                ans.add(uf.connect(position[0], position[1]));
            }
            return ans;
        }

        public static class UnionFind1 {
            private int[] parent;
            private int[] size;
            private int[] help;
            private final int row;
            private final int col;
            private int sets;

            public UnionFind1(int m, int n) {
                row = m;
                col = n;
                sets = 0;
                int len = row * col;
                parent = new int[len];
                size = new int[len];
                help = new int[len];
            }

            private int index(int r, int c) {
                return r * col + c;
            }

            private int find(int i) {
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

            private void union(int r1, int c1, int r2, int c2) {
                if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                    return;
                }
                int i1 = index(r1, c1);
                int i2 = index(r2, c2);
                if (size[i1] == 0 || size[i2] == 0) {
                    return;
                }
                int f1 = find(i1);
                int f2 = find(i2);
                if (f1 != f2) {
                    if (size[f1] >= size[f2]) {
                        size[f1] += size[f2];
                        parent[f2] = f1;
                    } else {
                        size[f2] += size[f1];
                        parent[f1] = f2;
                    }
                    sets--;
                }
            }

            public int connect(int r, int c) {
                int index = index(r, c);
                if (size[index] == 0) {
                    parent[index] = index;
                    size[index] = 1;
                    sets++;
                    union(r - 1, c, r, c);
                    union(r + 1, c, r, c);
                    union(r, c - 1, r, c);
                    union(r, c + 1, r, c);
                }
                return sets;
            }

        }
    }


    static class C1 {
        public static List<Integer> numIslands(int m,int n,int[][] positions) {
            UnionFind unionFind = new UnionFind(m, n);
            List<Integer> list = new ArrayList<>();
            for (int[] position : positions) {
                list.add(unionFind.connect(position[0], position[1]));
            }
            return list;
        }

        static class UnionFind {
            int[] parent;
            int[] size;
            int[] help;
            int col;
            int row;
            int set;

            public UnionFind(int m, int n) {
                row = m;
                col = n;
                int len = row * col;
                parent = new int[len];
                size = new int[len];
                help = new int[len];
                set = 0;
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
            public int index(int r, int c) {
                return r * col + c;
            }
            public void union(int r1, int c1, int r2, int c2) {
                if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                    return;
                }
                int i1 = index(r1, c1);
                int i2 = index(r2, c2);
                if (size[i1] == 0 || size[i2] == 0) {
                    return;
                }
                int aF = find(i1);
                int bF = find(i2);
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

            public int connect(int r, int c) {
                int i = index(r, c);
                if (size[i] == 0) {
                    size[i] = 1;
                    parent[i] = i;
                    set++;
                    union(r - 1, c, r, c);
                    union(r + 1, c, r, c);
                    union(r, c - 1, r, c);
                    union(r, c + 1, r, c);
                }
                return set;
            }
        }

    }


    public static void main(String[] args){

        int mMax = 20;
        int nMax = 20;

        int test = 100;
        System.out.println("start");
        for (int i = 0; i < test; i++) {
            int m = (int) ((Math.random() * mMax) + 1);
            int n = (int) ((Math.random() * nMax) + 1);
            int[][] positions = randomPositions(m, n);
            List<Integer> list = C.numIslands(m, n, positions);
            List<Integer> list1 = C1.numIslands(m, n, positions);
            if (!isListEqual(list, list1)) {
                System.out.println(list.toString());
                System.out.println(list1.toString());
                break;

            }
        }
        System.out.println("end");

    }

    public static boolean isListEqual(List l0, List l1){
        if (l0 == l1)
            return true;
        if (l0 == null && l1 == null)
            return true;
        if (l0 == null || l1 == null)
            return false;
        if (l0.size() != l1.size())
            return false;
        for (Object o : l0) {
            if (!l1.contains(o))
                return false;
        }
        for (Object o : l1) {
            if (!l0.contains(o))
                return false;
        }
        return true;
    }

    private static int[][] randomPositions(int m, int n) {
        int size = (int) (Math.random() * m * n) + 1;
        int[][] positions = new int[size][2];
        for (int i = 0; i < positions.length; i++) {
            positions[i][0] = (int) (Math.random() * m);
            positions[i][1] = (int) (Math.random() * n);
        }
        return positions;

    }
}
