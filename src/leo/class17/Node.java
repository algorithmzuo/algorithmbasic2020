package leo.class17;

import java.util.ArrayList;

/**
 * @author Leo
 * @ClassName Node
 * @DATE 2020/12/30 10:58 上午
 * @Description 点结构
 */
public class Node {

    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nodes;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
