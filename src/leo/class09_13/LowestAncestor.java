package leo.class09_13;

/**
 * @author Leo
 * @ClassName lowestAncestor
 * @DATE 2020/12/16 8:59 上午
 * @Description
 * 给定一棵二叉树的头节点head，和另外两个节点a和b。
 * 返回a和b的最低公共祖先
 */
public class LowestAncestor {

    static class Code {
        static Node lowestAncestor(Node head, Node a, Node b) {

            return process(head, a, b).ans;
        }

        static Info process(Node node, Node a, Node b) {
            if (node == null) {
                return new Info(null, false, false);
            }
            Info left = process(node, a, b);
            Info right = process(node, a, b);
            boolean findA = left.findA || right.findA || a == node;
            boolean findB = left.findB || right.findB || b == node;
            Node ans = null;
            if (left.ans != null) {
                ans = left.ans;
            }
            if (right.ans != null) {
                ans = right.ans;
            }
            if (ans == null) {
                if (findA && findB) {
                    ans = node;
                }
            }
            return new Info(ans, findA, findB);
        }

        static class Info {
            Node ans;
            boolean findA;
            boolean findB;

            public Info(Node ans, boolean findA, boolean findB) {
                this.ans = ans;
                this.findA = findA;
                this.findB = findB;
            }
        }
    }
}
