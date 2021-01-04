package leo.class16;

import leo.class16.Graph.Graph;
import leo.class16.Graph.Node;

import java.util.*;

/**
 * @author Leo
 * @ClassName TopologicalOrder
 * @DATE 2020/12/29 3:13 下午
 * @Description 拓扑排序 有向无环图
 * 要求：有向图且其中没有环
 * 应用：事件安排、编译顺序
 * 点次的概念:
 * xSum>ySum
 * xSum为x为头走过的点的总数
 * ySum为y为头走过的点的总数
 * 如果xSum>ySum x的拓扑序小于等于y的拓扑序
 * https://www.lintcode.com/problem/topological-sorting
 */
public class TopologicalOrder {
    /*
    1）在图中找到所有入度为0的点输出
    2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
    3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
    */
    static class Sort{
        public static List<Node> sortedTopology(Graph graph) {
            //入度map
            HashMap<Node, Integer> inMap = new HashMap<>();
            //入度入度为零的队列
            Queue<Node> zeroQueue = new LinkedList<>();
            //变量所有node得到in为0的节点,加入到队列中
            for (Node node : graph.nodes.values()) {
                inMap.put(node, node.in);
                if (node.in == 0) {
                    zeroQueue.offer(node);
                }
            }

            List<Node> list = new ArrayList<>();

            while (!zeroQueue.isEmpty()) {
                Node cur = zeroQueue.poll();
                //将入度为零的节点加入到结果中
                list.add(cur);
                for (Node next : cur.nexts) {
                    //将当前节点的邻居节点的入度减一
                    inMap.put(next, inMap.get(next) - 1);
                    //再次将入度为零的节点加入到队列中
                    if (next.in == 0) {
                        zeroQueue.offer(next);
                    }
                }
            }
            return list;
        }

    }



    /**
     * 点次的概念:
     * xSum>ySum
     * xSum为x为头走过的点的总数
     * ySum为y为头走过的点的总数
     * 如果xSum>ySum x的拓扑序小于等于y的拓扑序
     */
    static class DFS_Count {

        static class Record{
            //节点
            DirectedGraphNode node;
            //节点的点次
            long count;

            public Record(DirectedGraphNode node, long deep) {
                this.node = node;
                this.count = deep;
            }
        }
        /*
         * @param graph: A list of Directed graph node
         * @return: Any topological order for the given graph.
         */
        public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
            //点次缓存表,结果表
            HashMap<DirectedGraphNode, Record> order = new HashMap<>();
            for (DirectedGraphNode node : graph) {
                findRecord(node, order);
            }
            ArrayList<Record> recordList = new ArrayList<>();
            for (Record record : order.values()) {
                recordList.add(record);
            }
            recordList.sort(new MyComparator());
            ArrayList<DirectedGraphNode> list = new ArrayList<>();
            for (Record record : recordList) {
                list.add(record.node);
            }
            return list;
        }

        /**
         * 功能描述 : 获取cur节点所到之处的点次
         * @author Leo
         * @date 2020/12/29 4:47 下午
         * @param cur 当前节点
         * @param order 缓存
         * @return Record (当前节点,点次)
         */
        public static Record findRecord(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
            //查询缓存中是否存在,如果存在直接返回
            if (order.containsKey(cur)) {
                return order.get(cur);
            }
            //如果没有 开始计算
            long count = 0;
            for (DirectedGraphNode next : cur.neighbors) {
                count += findRecord(next, order).count;
            }
            //最后要加上自己的点次
            Record record = new Record(cur, count+1);
            //加入缓存
            order.put(cur, record);
            return record;
        }

        //倒序
        static class MyComparator implements Comparator<Record> {

            @Override
            public int compare(Record o1, Record o2) {
                return o1.count == o2.count ? 0 :(o1.count > o2.count ? -1 : 1);
            }

        }
    }

    /**
     * 最大深度
     * xDeep >= yDeep
     * xDeep 是以x头的最大深度
     * yDeep 是以y头的最大深度
     * 如果xDeep >= yDeep x的拓扑序小于等于y的拓扑序
     */
    class DFS_Deep {

        class Record{
            DirectedGraphNode node;
            int deep;

            public Record(DirectedGraphNode node, int deep) {
                this.node = node;
                this.deep = deep;
            }
        }

        public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
            HashMap<DirectedGraphNode, Record> order = new HashMap<>();
            for (DirectedGraphNode node : graph) {
                findRecord(node, order);
            }
            ArrayList<Record> records = new ArrayList<>();
            for (Record record : order.values()) {
                records.add(record);
            }
            records.sort((o1,o2)->{return o2.deep - o1.deep;});
            ArrayList<DirectedGraphNode> list = new ArrayList<>();
            for (Record record : records) {
                list.add(record.node);
            }
            return list;
        }

        public Record findRecord(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {

            if (order.containsKey(cur)) {
                return order.get(cur);
            }
            int deep = 0;
            for (DirectedGraphNode next : cur.neighbors) {
                deep = Math.max(deep, findRecord(next, order).deep);
            }
            Record record = new Record(cur, deep + 1);
            order.put(cur, record);
            return record;
        }


    }

    class BFS{
        public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
            Map<DirectedGraphNode, Integer> map = new HashMap<>();
            Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
            for (DirectedGraphNode node : graph) {
                map.put(node, 0);
            }
            for (DirectedGraphNode node : graph) {
                for (DirectedGraphNode next : node.neighbors) {
                    map.put(next, map.get(next) + 1);
                }
            }
            for (DirectedGraphNode node : map.keySet()) {
                if (map.get(node) == 0) {
                    zeroQueue.offer(node);
                }
            }
            ArrayList<DirectedGraphNode> list = new ArrayList<>();
            DirectedGraphNode cur;
            while (!zeroQueue.isEmpty()) {
                cur = zeroQueue.poll();
                list.add(cur);
                for (DirectedGraphNode next : cur.neighbors) {
                    map.put(next, map.get(next) - 1);
                    if (map.get(next) == 0) {
                        zeroQueue.offer(next);
                    }
                }
            }
            return list;
        }
    }


    /**
     * @author Leo
     * @date 2020/12/29 4:39 下午
     *
     */
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

}
