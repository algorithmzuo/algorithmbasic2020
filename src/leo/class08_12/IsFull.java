package leo.class08_12;

/**
 * @author Leo
 * @ClassName IsFull
 * @DATE 2020/12/11 7:14 下午
 * @Description 判断一颗二叉树是否是满二叉树
 * 高度为h,节点为(2^h)-1个
 */
public class IsFull {

    static class Code{
        static boolean isFull(Node head){
            if (head == null) {
                return true;
            }
            Info info = process(head);
            return info.nodeCount == (1 << info.height) - 1;
        }


        public static Info process(Node node){
            if (node == null) {
                return new Info(0, 0);
            }
            Info left = process(node.left);
            Info right = process(node.right);
            int height = Math.max(left.height, right.height) + 1;
            int nodeCount = left.nodeCount + right.nodeCount + 1;
            return new Info(height, nodeCount);
        }
        static class Info{
            int height;
            int nodeCount;
            public Info(int h,int c){
                this.height = h;
                this.nodeCount = c;
            }
        }
    }

    static class Logarithm{
        static boolean isFull(Node head) {
            if (head == null) {
                return true;
            }
            int n = n(head);
            int h = h(head);
            return (1 << h) - 1 == n;
        }
        static int n(Node node){
            if (node == null) {
                return 0;
            }
            return n(node.left) + n(node.right) + 1;
        }

        static int h(Node node){
            if (node == null) {
                return 0;
            }
            return Math.max(h(node.left), h(node.right)) + 1;
        }
    }

    public static void main(String[] args){
        int level = 5;
        int range = 100;
        int testTime = 1000000;
        System.out.println("start!");

        for (int i = 0; i < testTime; i++) {
            Node head = generateRandomNode(level, range);
            boolean c = Code.isFull(head);
            boolean l = Logarithm.isFull(head);
            if (c != l) {
                System.out.println("fuck!!!!");
                break;
            }
        }
        System.out.println("end!");

    }

    public static Node generateRandomNode(int n, int r){
        return generate(1,n,r);
    }

    public static Node generate(int i, int n, int r) {
        if (i > n || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (r * Math.random()));
        head.left = generate((i + 1), n, r);
        head.left = generate((i + 1), n, r);

        return head;
    }
}
