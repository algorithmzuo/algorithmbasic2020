package class12;

/**
 * 判断是不是满二叉树的条件是，树的高度是h ，那么节点数一定是2^h-1
 */
public class Code04_IsFull_Study {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static boolean isFull2(Node root){
        if (root == null){
            return true;
        }
        int h = h(root);
        int n = n(root);
        return (1<<h)-1 == n;
    }

    public static int n(Node node){
        if (node == null){
            return 0;
        }
        return n(node.left) + n(node.right) + 1;
    }

    public static int h(Node node){
        if (node == null){
            return 0;
        }
        return Math.max(h(node.left),h(node.right)) +1;
    }


    static class Info{
        // 总的结点数
        private Integer nums;
        // 数的最高高度
        private Integer height;
        private Info(Integer nums,Integer height){
            this.nums = nums;
            this.height = height;
        }
    }


    public static boolean isFull(Node root){
        if (root == null){
            return true;
        }
        Info info = process(root);
        return (1 << info.height) -1 == info.nums;
    }

    /**
     * 这里简单分析下，如果使用递归，我每次递归需要什么数据，返回什么以及结束条件是什么
     * 1.数据：记录当前节点的高度，当前节点包括他的子树总节点数
     * 2.返回的高度和节点数，用于统计
     * 3.结束条件是，节点为null了，此时节点数和高度都是0；
     * @param root
     * @return
     */
    public static Info process(Node root){
        // 结束递归的条件
        if (root == null){
            return new Info(0,0);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int nums = left.nums + right.nums +1;
        int height = Math.max(left.height, right.height) + 1;
        return new Info(nums,height);
    }

    public static Node generateRandomBST(int maxValue,int maxLevel){
        return generate(1,maxLevel,maxValue);
    }
    public static Node generate(int curLeve,int maxLevel,int maxValue){
        if (curLeve > maxLevel|| Math.random()<0.3){
            return null;
        }
        Node root = new Node((int)(Math.random() * maxValue + 1));
        root.left = generate(curLeve + 1,maxLevel,maxValue);
        root.right = generate(curLeve + 1,maxLevel,maxValue);
        return root;
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int maxVlaue = 100;
        int maxLevel = 5;
        for (int i = 0;i <testTime;i++){
            Node node = generateRandomBST(maxVlaue,maxLevel);
//            System.out.println(i);
            if (isFull(node) != isFull2(node)){
                System.out.println("oop");
            }
        }
        System.out.println("success");
    }
}
