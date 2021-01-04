package leo.class16;

import leo.class16.Graph.Edge;
import leo.class16.Graph.Graph;
import leo.class16.Graph.Node;

import java.util.*;

/**
 * @author Leo
 * @ClassName Kruskal
 * @DATE 2020/12/29 5:46 下午
 * @Description 最小生成树算法之Kruskal
 * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2）当前的边要么进入最小生成树的集合，要么丢弃
 * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5）考察完所有边之后，最小生成树的集合也得到了
 */
public class Kruskal {

    class Code{

        class UnionFind{
            Map<Node, Node> parentMap;
            Map<Node, Integer> sizeMap;

            public UnionFind(Collection<Node> nodes) {
                parentMap = new HashMap<>();
                sizeMap = new HashMap<>();
                Iterator<Node> iterator = nodes.iterator();
                while (iterator.hasNext()) {
                    Node cur = iterator.next();
                    parentMap.put(cur, cur);
                    sizeMap.put(cur, 0);
                }
            }

            public Node find(Node node) {
                Stack<Node> stack = new Stack<>();
                while (node != parentMap.get(node)) {
                    stack.push(node);
                    node = parentMap.get(node);
                }
                while (!stack.isEmpty()) {
                    parentMap.put(stack.pop(), node);
                }
                return node;
            }

            public boolean isSameSet(Node n1, Node n2) {
                return find(n1) == find(n2);
            }

            public void union(Node n1, Node n2) {
                Node aF = find(n1);
                Node bF = find(n2);
                if (n1 == n2) {
                    return;
                }
                int aSize = sizeMap.get(aF);
                int bSize = sizeMap.get(bF);
                Node big = aSize >= bSize ? aF : bF;
                Node small = big == aF ? bF : aF;
                parentMap.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }



        }
        public Set<Edge> kruskalMST(Graph graph){
            UnionFind unionFind = new UnionFind(graph.nodes.values());
            PriorityQueue<Edge> queue = new PriorityQueue<>((o1,o2)->{return o1.weight - o2.weight;});
            for (Edge edge : graph.edges) {
                queue.offer(edge);
            }
            Set<Edge> set = new HashSet<>();
            while (!queue.isEmpty()) {
                Edge edge = queue.poll();
                if (!unionFind.isSameSet(edge.from, edge.to)) {
                    set.add(edge);
                    unionFind.union(edge.from, edge.to);
                }
            }
            return set;
        }




    }

}
