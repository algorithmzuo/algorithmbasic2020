package leo.class10_16;

import leo.class10_16.Graph.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author Leo
 * @ClassName DFS
 * @DATE 2020/12/29 2:29 下午
 * @Description 深度优先遍历
 */
public class DFS {

    /**
     * 功能描述 : 深度优先遍历
     *          入栈就打印
     * @author Leo
     * @date 2020/12/29 2:34 下午
     * @param node
     * @return void
     */
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        set.add(node);
        stack.push(node);
        Node cur;
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    /**
                     * 栈永远放着整条路径
                     */
                    stack.push(cur);
                    stack.push(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

    public static void dfs1(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        Node cur;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
