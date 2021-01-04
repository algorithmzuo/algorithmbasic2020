package leo.class17;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Leo
 * @ClassName Graph
 * @DATE 2020/12/30 11:01 上午
 * @Description 图结构
 */
public class Graph {

    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
