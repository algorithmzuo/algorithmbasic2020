package leo.class10_16.Graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Leo
 * @ClassName Graph
 * @DATE 2020/12/29 2:00 下午
 * @Description 图:由点集合边集组成
 *
 * 1.邻接表法
 * 2.邻接矩阵法
 * 3.除此之外还有其他众多的方式
 */
public class Graph {
    /**
     * 点集
     * key:为点结构的值
     * value:key对应的点结构
     */
    public HashMap<Integer,Node> nodes;

    /**
     * 边集
     * 边集不重复
     */
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
