package class16.class16.study;

import java.util.ArrayList;
import java.util.List;

/**
 * 图 - 点的结构
 */
public class Node {
    public int value;
    // 入度-有多少个节点指向该节点
    public int in;
    // 该节点指向多少个节点
    public int out;
    // 该节点的子节点
    public List<Node> nexts;
    // 该节点的边
    public List<Edge> edges;

    Node(int value){
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
