package leo.class16.Graph;

/**
 * @author Leo
 * @ClassName Edge
 * @DATE 2020/12/29 1:57 下午
 * @Description
 */
public class Edge {
    /**
     * 边的权重
     */
    public int weight;

    /**
     * 从哪个节点出发
     */
    public Node from;

    /**
     * 到哪个节点
     */
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
