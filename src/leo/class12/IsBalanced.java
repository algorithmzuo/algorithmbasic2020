package leo.class12;

/**
 * @author Leo
 * @ClassName IsBalanced
 * @DATE 2020/12/11 3:57 下午
 * @Description
 * 判断是否是平衡二叉树
 * 每一颗子树的左右高的相差的绝对值不超过一
 * 1.是否是平衡
 * 2.当前树的高度
 */
public class IsBalanced {

    static class Code {
        public static boolean isBalanced(Node head) {
            return process(head).isBalanced;
        }

        public static Info process(Node node) {
            if (node == null) {
                return new Info(true, 0);
            }
            Info leftInfo = process(node.left);
            Info rightInfo = process(node.right);
            boolean isBalanced = true;
            int height = Math.max(leftInfo.height, rightInfo.height) + 1;
            if (!leftInfo.isBalanced || !rightInfo.isBalanced||Math.abs(leftInfo.height - rightInfo.height) > 1) {
                isBalanced = false;
            }
            return new Info(isBalanced, height);

        }
        /**
         * 以当前节点为头判断是否为平衡二叉树
         */
        static class Info{
            boolean isBalanced;
            int height;

            public Info(boolean b, int h) {
                this.isBalanced = b;
                this.height = h;
            }
        }



    }

    static class Code1 {
        public static boolean isBalanced(Node head) {
            if (head == null) {
                return true;
            }
            return process(head).isBalanced;

        }

        public static Info process(Node node){
            if (node == null) {
                return new Info(true, 0);
            }
            Info left = process(node.left);
            Info right = process(node.right);
            boolean is =  true;
            int height = Math.max(left.height, right.height) + 1;
            if (!left.isBalanced || !right.isBalanced || Math.abs(right.height - left.height) > 1) {
                is = false;
            }
            return new Info(is, height);
        }

        static class Info{
            boolean isBalanced;
            int height;
            public Info(boolean is,int h){
                this.isBalanced = is;
                this.height = h;
            }
        }
    }

    static class Code2{
        static boolean isBalanced(Node head){
            return process(head).isBalanced;
        }
        static Info process(Node node) {
            if (node == null) {
                return new Info(true, 0);
            }
            Info left = process(node.left);
            Info right = process(node.right);
            boolean isBalanced = true;
            int height = Math.max(left.height, right.height) + 1;
            if (!left.isBalanced || !right.isBalanced || Math.abs(right.height - left.height) > 1) {
                isBalanced = false;
            }
            return new Info(isBalanced, height);
        }

        static class Info{
            boolean isBalanced;
            int height;

            public Info(boolean is, int h) {
                this.isBalanced = is;
                this.height = h;
            }
        }
    }

    static class Logarithm{
        public static boolean isBalanced(Node head) {
            boolean[] ans = new boolean[1];
            ans[0] = true;
            process(head, ans);

            return ans[0];
        }

        public static int process(Node head, boolean[] ans) {
            if (!ans[0] || head == null) {
                return -1;
            }
            int leftHeight = process(head.left, ans);
            int rightHeight = process(head.right, ans);
            if (Math.abs(leftHeight - rightHeight) > 1) {
                ans[0] = false;
            }
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }


    public static void main(String[] args){

        int test = 10000;
        int level = 4;
        int range = 100;
        System.out.println("Start!");
        for (int i = 0; i < test; i++) {
            Node head = generateRandomNode(level, range);
            boolean codec = Code2.isBalanced(head);
            boolean logarithm = IsBalanced.Logarithm.isBalanced(head);
            if (codec != logarithm) {
                System.out.println("fuck!");
                break;
            }
        }
        System.out.println("End!");

    }

    public static Node generateRandomNode(int level, int range) {

        return generateRandomNode(1, level, range);

    }

    public static Node generateRandomNode(int curLevel, int level, int range) {
        if (curLevel > level || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (range * Math.random()));
        head.left = generateRandomNode(curLevel + 1, level, range);
        head.right = generateRandomNode(curLevel + 1, level, range);
        return head;
    }


}


