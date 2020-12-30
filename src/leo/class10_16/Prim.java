package leo.class10_16;

import com.sun.org.apache.regexp.internal.RE;
import leo.class10_16.Graph.Edge;
import leo.class10_16.Graph.Graph;
import leo.class10_16.Graph.Node;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Leo
 * @ClassName Prim
 * @DATE 2020/12/30 10:04 上午
 * @Description 最小生成树算法之Prim
 * 1）可以从任意节点出发来寻找最小生成树
 * 2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
 * 3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
 * 4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
 * 5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
 * 6）当所有点都被选取，最小生成树就得到了
 *
 */
public class Prim {


    public static Set<Edge> primMST(Graph graph) {
        //根据权重排序 小根堆
        PriorityQueue<Edge> queue = new PriorityQueue<>((o1, o2) -> {return o1.weight - o2.weight;});
        for (Edge edge : graph.edges) {
            queue.offer(edge);
        }
        Set<Node> set = new HashSet<>();
        Set<Edge> result = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            //点解锁边
            if (!set.contains(node)) {
                for (Edge edge : node.edges) {
                    queue.offer(edge);
                }
            }
            //边解锁点
            while (!queue.isEmpty()) {
                Edge edge = queue.poll();
                Node toNode = edge.to;
                //点又解锁边
                if (!set.contains(toNode)) {
                    set.add(toNode);
                    result.add(edge);
                    for (Edge e : toNode.edges) {
                        queue.offer(e);
                    }
                }

            }
            // 如果要防森林,就加上break,如果确定是一个图,就取消break
            //break

        }

        return result;
    }
}
