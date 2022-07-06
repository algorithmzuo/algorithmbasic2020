package class16.class16.study;

import java.util.*;

/**
 * 深度优先遍历
 * 深度优先遍历，当节点存在多个子节点时，随机一个节点跟下去
 * 知道行成回路或者没有路的时候，在往回走
 * 走到之前有多个岔路的时候，选择另一个路继续
 * 一直遍历完所有的节点
 */
public class    Code02_DFS {
    public static void dfs(Node head){
        if (head == null){
            return;
        }
        Stack<Node> nodes = new Stack<>();
        Set<Node> sets = new HashSet<>();
        nodes.add(head);
        sets.add(head);
        while (!nodes.isEmpty()) {
            Node cur = nodes.pop();
            for (Node next : cur.nexts) {
                if (!sets.contains(next)) {
                    nodes.push(cur);
                    nodes.push(next);
                    sets.add(next);
                }
            }
        }
    }
}
