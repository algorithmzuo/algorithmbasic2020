package leo.class15;

/**
 * @author Leo
 * @ClassName FriendCircles
 * @DATE 2020/12/23 2:49 下午
 * @Description
 * 547 测试链接：https://leetcode.com/problems/friend-circles/
 */
public class FriendCircles {

    class Code1 {
        public int findCircleNum(int[][] M) {
            int n = M.length;
            UnionFind unionFind = new UnionFind(n);
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (M[i][j] == 1) {
                        unionFind.union(i, j);
                    }
                }

            }
            return unionFind.sets;
        }


        public class UnionFind {
            // i的父级节点是parent[i]
            int[] parent;
            // i的集合大小为size[i]
            int[] size;
            //辅助结构
            int[] help;
            //一共多少个集合
            int sets;
            public UnionFind(int n) {

                parent = new int[n];
                size = new int[n];
                help = new int[n];
                sets = n;
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
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

            public void union(int i, int j) {
                int iF = find(i);
                int jF = find(j);
                if (jF != iF) {
                    int iSize = size[iF];
                    int jSize = size[jF];
                    int big = iSize >= jSize ? iF : jF;
                    int small = big == iF ? jF : iF;
                    parent[small] = big;
                    size[big] = jSize + iSize;
                    sets--;
                }
            }

            public int sets() {
                return sets;
            }
        }
    }
    class Code2 {
        public int findCircleNum(int[][] M) {
            int n = M.length;
            UnionFind unionFind = new UnionFind(n);
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (M[i][j] == 1) {
                        unionFind.union(i, j);
                    }
                }
            }
            return unionFind.sets;
        }

        public class UnionFind {
            int[] parent;
            int[] size;
            int[] help;
            int sets;

            public UnionFind(int n) {
                parent = new int[n];
                size = new int[n];
                help = new int[n];
                sets = n;
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
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

            public void union(int i, int j) {
                int iF = find(i);
                int jF = find(j);
                if (iF != jF) {
                    int iSize = size[iF];
                    int jSize = size[jF];
                    int big = iSize >= jSize ? iF : jF;
                    int small = big == iF ? jF : iF;
                    parent[small] = big;
                    size[big] = iSize + jSize;
                    sets--;
                }

            }

            public int sets() {
                return sets;
            }

        }

    }

}
