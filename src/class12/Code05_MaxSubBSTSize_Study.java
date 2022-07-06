package class12;

import java.util.ArrayList;

/**
 * x树上，求x树上最大的搜索二叉树
 */
public class Code05_MaxSubBSTSize_Study {
    static class Node{
        private Integer value;
        private Node left;
        private Node right;
        private Node(int _value){
            this.value = _value;
        }
    }

    /**
     * 这里获取二叉树高度
     * @param node
     * @return
     */
    public static Integer getBSTSize(Node node){
        if (node == null){
            return 0;
        }
        ArrayList<Node> list = new ArrayList<>();
        in(node,list);
        for (int i = 1;i < list.size();i++){
            if (list.get(i).value  <=  list.get(i - 1).value) {
                return 0;
            }
        }
        return list.size();
    }

    /**
     * 中序遍历，刚好可以排查是否符合搜索二叉树的规则，即左子树的最大值小于X，右子树的最小只大于X
     * @param node
     * @param arr
     */
    public static void in(Node node ,ArrayList<Node> arr){
        if (node == null){
            return;
        }
        in(node.left,arr);
        arr.add(node);
        in(node.right,arr);
    }

    public static Integer maxSubBSTSize2(Node node){
        if (node == null){
            return 0;
        }
        int h = getBSTSize(node);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize2(node.left),maxSubBSTSize2(node.right));
    }



    /**
     * 定义一个类，用来一些必要的参数
     */
    static class Info{
        /**
         * 左右树最大搜索二叉树大小
         */
        public Integer maxBSTSUBSize;
        /**
         * 数的所有节点，可以和maxBSTSUBSize比较，判断x节点本身是不是属于做头结点
         */
        public Integer allSize;
        /**
         * 节点左子树的最大值
         */
        public Integer max;
        /**
         * 节点右子树的最小值
         */
        public Integer min;

        public Info(Integer _maxBSTSUBSize,Integer _allSize,Integer _max,Integer _min){
            this.maxBSTSUBSize = _maxBSTSUBSize;
            this.allSize = _allSize;
            this.max = _max;
            this.min = _min;
        }
    }

    public static Integer maxSubBSTSize(Node node){
        if (node == null){
            return 0;
        }
        return process(node).maxBSTSUBSize;
    }

    /**
     * 当x做头节点时：
     * 左子树要为搜索二叉树，右子树要为搜索二叉树
     * x大于左子树的最大值，小于右子树的最小值
     * 当x不做头结点时
     * ·左子树为搜索二叉树，求左子树的节点数
     *  右子树为搜索二叉树，求右子树的节点数
     *
     * @param node
     * @return
     */
    public static Info process(Node node){
        if (node == null){
            return null;
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int min = node.value;
        int max = node.value;
        int allSize = 1;
        if (left != null){
            min = Math.min(left.min,min);
            max = Math.max(left.max,max);
            allSize += left.allSize;
        }
        if (right != null) {
            min = Math.min(right.min,min);
            max = Math.max(right.max,max);
            allSize += right.allSize;
        }
        // 用来获取左子树的最大搜索二叉树值
        int p1 = -1;
        if (left != null){
            p1 = left.maxBSTSUBSize;
        }
        // 用来保存右子树的最大搜索二叉树值
        int p2 = -1;
        if (right != null){
            p2 = right.maxBSTSUBSize;
        }
        // 这个是判断x是否也满足搜索二叉树的条件
        int p3 = -1;
        // 判断左子树是否是搜索二叉树
        boolean leftBST = left == null ? true:(left.maxBSTSUBSize == left.allSize);
        boolean rightBST = right == null ? true:(right.maxBSTSUBSize == right.allSize);

        if (leftBST && rightBST){
            // 到这里可以确定两个子树都是搜索二叉树，那么下一步就是判断值大小了
            boolean leftMaxLessX = left == null ? true : left.max < node.value;
            boolean rightMinMoreX = right == null ? true: right.min > node.value;
            if (leftMaxLessX && rightMinMoreX){
                int leftSize = left == null ? 0 : left.allSize;
                int rightSize = right == null ? 0 : right.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        return new Info(Math.max(p3,Math.max(p1,p2)),allSize,min,max);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int testTime = 1000;
        int maxValue = 100;
        for (int i  =0 ; i< testTime;i++){
            Node head = generateRandomBST(maxLevel,maxValue);
            if (maxSubBSTSize(head) != maxSubBSTSize2(head)){
                System.out.println("oops");
            }
        }
        System.out.println("finish");
    }

}
