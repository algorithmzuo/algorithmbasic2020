package class16.class16.study;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 宽度优先遍历
 */
public class Code01_BFS {
    public static void bfs(Node head){
        if (head == null){
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        nodes.add(head);
        set.add(head);
        while (!nodes.isEmpty()){
            Node node = nodes.poll();
            for (Node nextNode : node.nexts){
                if (!set.contains(nextNode)) {
                    set.add(nextNode);
                    nodes.add(nextNode);
                }
            }
        }
    }
}
