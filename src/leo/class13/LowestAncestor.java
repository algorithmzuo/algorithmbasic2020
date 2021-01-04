package leo.class13;

/**
 * @author Leo
 * @ClassName lowestAncestor
 * @DATE 2020/12/16 8:59 上午
 * @Description
 * 给定一棵二叉树的头节点head，和另外两个节点a和b。
 * 返回a和b的最低公共祖先
 * https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 */
public class LowestAncestor {

    static class Code {
        static TreeNode lowestCommonAncestor(TreeNode head, TreeNode a, TreeNode b) {

            return process(head, a, b).ans;
        }

        static Info process(TreeNode node, TreeNode a, TreeNode b) {
            if (node == null) {
                return new Info(null, false, false);
            }
            Info left = process(node.left, a, b);
            Info right = process(node.right, a, b);
            boolean findA = left.findA || right.findA || a == node;
            boolean findB = left.findB || right.findB || b == node;
            TreeNode ans = null;
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
            TreeNode ans;
            boolean findA;
            boolean findB;

            public Info(TreeNode ans, boolean findA, boolean findB) {
                this.ans = ans;
                this.findA = findA;
                this.findB = findB;
            }
        }
    }

    class Code1 {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            return process(root, p, q).ans;
        }

        public Info process(TreeNode node,TreeNode a,TreeNode b) {
            if (node == null) {
                return new Info(null, false, false);
            }
            Info left = process(node.left, a, b);
            Info right = process(node.right, a, b);
            boolean findA = left.findA || right.findA || node == a;
            boolean findB = left.findB || right.findB || node == b;
            TreeNode ans = null;
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

        class Info {
            TreeNode ans;
            boolean findA;
            boolean findB;

            public Info(TreeNode ans, boolean findA, boolean findB) {
                this.ans = ans;
                this.findA = findA;
                this.findB = findB;
            }
        }


    }

    class Code2 {
        public TreeNode lowestCommonAncestor(TreeNode head, TreeNode a, TreeNode b) {

            return process(head, a, b).ans;
        }

        public Info process(TreeNode node, TreeNode a, TreeNode b) {
            if (node == null) {
                return new Info(null, false, false);
            }
            Info left = process(node.left, a, b);
            Info right = process(node.right, a, b);
            boolean findA = left.findA || right.findA || node == a;
            boolean findB = left.findB || right.findB || node == b;
            TreeNode ans = null;
            if (left.ans != null) {
                ans = left.ans;
            }
            if (right.ans != null) {
                ans = right.ans;
            }
            if (findA && findB) {
                if (ans == null) {
                    ans = node;
                }
            }
            return new Info(ans, findA, findB);
        }


        class Info{
            TreeNode ans;
            boolean findA;
            boolean findB;

            public Info(TreeNode ans, boolean a, boolean b) {

                this.ans = ans;
                this.findA = a;
                this.findB = b;
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
