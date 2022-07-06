package class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * 深度有限遍历
 *
 */
public class Code02_DFS {

	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		// 这里的stack是路径，节点没有在set中时，把当前节点和下一个节点压入栈，然后循环
		Stack<Node> stack = new Stack<>();
		HashSet<Node> set = new HashSet<>();
		stack.add(node);
		set.add(node);
		System.out.println(node.value);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					stack.push(cur);
					stack.push(next);
					set.add(next);
					System.out.println(next.value);
					break;
				}
			}
		}
	}
	
	
	

}
