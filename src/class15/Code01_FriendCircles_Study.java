package class15;

/**
 * 求临省问题,并查集
 */
public class Code01_FriendCircles_Study {
    public int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0 ;i < N;i++) {
            for (int j = i + 1;j < N;j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.unionFind(i,j);
                }
            }
        }
        return unionFind.sets;
    }

    public class UnionFind{
        public int[] parents;
        public int[] size;
        public int[] help;
        public int sets;

        public  UnionFind(int N){
            parents = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0;i < N;i++){
                parents[i] = i;
                size[i] = 1;
            }
        }

        // 从i位置开始，一直往上找，知道找到代表节点
        public int find(int i){
            int helpIndex = 0;
            while (i != parents[i]) {
                // 把路径记录下来
                help[helpIndex++] = i;
                i = parents[i];
            }

            for (helpIndex--; helpIndex >=0;helpIndex--) {
                parents[help[helpIndex]]  = i;
            }
            return i;
        }

        public void unionFind(int i,int j){
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parents[f2]=f1;
                }else {
                    size[f2] += size[f1];
                    parents[f1] = f2;
                }
                sets--;
            }

        }

        public int getSets(){
            return sets;
        }
    }
}
