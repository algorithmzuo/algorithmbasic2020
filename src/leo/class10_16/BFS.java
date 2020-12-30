package leo.class10_16;

import leo.class10_16.Graph.Node;

import java.util.*;

/**
 * @author Leo
 * @ClassName BFS
 * @DATE 2020/12/29 2:23 下午
 * @Description 图的宽度优先遍历
 *
 */
public class BFS {

    /**
     * 功能描述 : 深度优先遍历 借助set,
     *          过滤掉已经遍历过的节点
     *          出队列就打印
     * @author Leo
     * @date 2020/12/29 2:28 下午
     * @param node
     * @return void
     */
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.offer(next);
                }
            }

        }


    }

    public static void bfs1(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        Node cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.offer(next);
                }
            }
        }

    }

}
