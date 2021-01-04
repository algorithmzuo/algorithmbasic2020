package leo.class16.Graph;

/**
 * @author Leo
 * @ClassName GraphGenerator
 * @DATE 2020/12/29 2:08 下午
 * @Description 创建图
 */
public class GraphGenerator {

    /**
     * 功能描述 : 数组邻接矩阵法创建图结构
     * @author Leo
     * @date 2020/12/29 2:08 下午
     * @param matrix matrix[i] = [weight,formValue,toValue]
     * @return leo.class10_16.Graph.Graph
     */
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int form = matrix[i][1];
            int to = matrix[i][2];
            if (!graph.nodes.containsKey(form)) {
                graph.nodes.put(form, new Node(form));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(form, new Node(to));
            }
            Node formNode = graph.nodes.get(form);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, formNode, toNode);
            formNode.nexts.add(toNode);
            formNode.out++;
            toNode.in++;
            formNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }

    class Train {
        public Graph createGraph(int[][] matrix) {
            Graph graph = new Graph();
            for (int i = 0; i < matrix.length; i++) {
                int weight = matrix[i][0];
                int from = matrix[i][0];
                int to = matrix[i][0];
                if (!graph.nodes.containsKey(from)) {
                    graph.nodes.put(from, new Node(from));
                }
                if (!graph.nodes.containsKey(to)) {
                    graph.nodes.put(to, new Node(to));
                }
                Node fromNode = graph.nodes.get(from);
                Node toNode = graph.nodes.get(to);
                Edge edge = new Edge(weight, fromNode, toNode);
                fromNode.nexts.add(toNode);
                fromNode.out++;
                toNode.in++;
                fromNode.edges.add(edge);
                graph.edges.add(edge);
            }
            return graph;
        }

    }
}
