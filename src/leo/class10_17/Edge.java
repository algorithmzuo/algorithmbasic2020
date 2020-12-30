package leo.class10_17;

/**
 * @author Leo
 * @ClassName Edge
 * @DATE 2020/12/30 10:59 上午
 * @Description 边结构
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
