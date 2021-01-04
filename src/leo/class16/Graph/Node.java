package leo.class16.Graph;

import java.util.ArrayList;

/**
 * @author Leo
 * @ClassName Node
 * @DATE 2020/12/29 1:53 下午
 * @Description 点结构
 */
public class Node {

    /**
     * 值
     */
    public int value;
    /**
     * 入度:指有多少边指向该节点
     */
    public int in;
    /**
     * 出度:有多少条边指向其他点
     */
    public int out;

    /**
     * 邻居:从该节点出发可以到达的点为邻居
     */
    public ArrayList<Node> nexts;

    /**
     * 从该节点出发有哪些边
     */
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
