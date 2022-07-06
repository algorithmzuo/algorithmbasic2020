package class12;

public class Code03_IsBalanced_Study {
    static class Node{
        private Integer value;
        private Node l;
        private Node r;
        public Node(Integer _value){
            value = _value;
        }
    }

    private static boolean isBalanced(Node node){
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process(node,ans);
        return ans[0];
    }
    private static Integer process(Node node,boolean[] ans){
        if (!ans[0] || node == null){
            return -1;
        }
        int lHeigh = process(node.l,ans);
        int rHeigh = process(node.r,ans);
        if (Math.abs(lHeigh - rHeigh) > 1) {
            ans[0]  = false;
        }
        return Math.max(lHeigh,rHeigh) + 1;
    }

    static class NodeInfo{
        public Integer heigh;
        public boolean isBalance;
        public NodeInfo(Integer heigh,boolean isBalance){
            this.heigh = heigh;
            this.isBalance = isBalance;
        }
    }

    /**
     * 判断是否平衡二叉树
     * 这里使用递归模板，分析递归左树和右树需要什么信息，并且保证每步骤需要的动心都是相同的
     * @param node
     * @return
     */
    private static boolean isBalanced2(Node node){
        return process2(node).isBalance;
    }

    private static NodeInfo process2(Node node){
        // 每一节点都需要返回当前节点的高度和是否平衡。
        // 平衡的判断标准主要就是他的左右子树的高度差是否≤1
        if (node == null){
            return new NodeInfo(0,true);
        }
        // 每个节点都需要获取左右节点的节点信息，需要判断该节点下得子树高度差和是否平衡
        NodeInfo left = process2(node.l);
        NodeInfo right = process2(node.r);

        // 计算高度差
        Integer heigh = Math.max(left.heigh,right.heigh) + 1;
        boolean isAbalanced = true;
        if (!left.isBalance){
            isAbalanced = false;
        }
        if (!right.isBalance){
            isAbalanced = false;
        }
        if (Math.abs(left.heigh - right.heigh) > 1) {
            isAbalanced = false;
        }
        return new NodeInfo(heigh,isAbalanced);
    }

    private  static Node generateRandomBST(int maxValue,int maxLevel){
        return generate(1,maxValue,maxLevel);
    }
    private static Node generate(int curLevel,int maxValue,int maxLevel){
        if (curLevel > maxLevel || Math.random() <0.3){
            return  null;
        }
        Node root = new Node((int)(Math.random()* maxValue +1));
        root.l = generate(curLevel + 1,maxValue,maxLevel);
        root.r = generate(curLevel + 1,maxValue,maxLevel);
        return root;
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int maxValue = 100;
        int maxLevl = 3;
        for (int i = 0 ;i < testTime;i++){
            Node root = generateRandomBST(maxValue,maxLevl);
            boolean re1 = isBalanced(root);
            boolean re2 = isBalanced2(root);
            if (re1 != re2) {
                System.out.println("re1 and re2:" + re1+","+ re2);
            }
        }
        System.out.println("success");
    }

}
