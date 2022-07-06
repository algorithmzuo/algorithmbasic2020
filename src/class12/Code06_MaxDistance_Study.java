package class12;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 获取两个节点间的最大值
 */
public class Code06_MaxDistance_Study {
    static class Node{
        public Integer value;
        public Node left;
        public Node right;
        public Node(Integer _value){
            this.value = _value;
        }
    }

    public static int maxDistance2(Node head){
        if(head == null){
            return 0;
        }
        //先序遍历获取所有节点
        ArrayList<Node> arr = new ArrayList<>();
        preNodeList(head,arr);
        // 获取每个节点和头节点映射关系
        HashMap<Node,Node> parentSet= new HashMap<>();
        getParentNode(head,parentSet);

        // 双循环计算每个元素的距离
        int max = 0;
        for (int i = 0;i < arr.size();i++){
            for (int j = i ; j < arr.size();j++) {
                max = Math.max(max,distance(parentSet,arr.get(i),arr.get(j)));
            }
        }
        return max;
    }

    /**
     * 这里采用暴力遍历所有的节点
     * @param parentMap 每个节点所有父节点的集合
     * @param o1 遍历的每个节点
     * @param o2 前序遍历中，每个节点的后节点
     * @return
     */
    public static Integer distance(HashMap<Node,Node> parentMap,Node o1,Node o2) {
        // 遍历获取节点的所有父节点
        Set<Node> parentSet = new HashSet<>();
        Node cur = o1;
        parentSet.add(o1);
        while(parentMap.get(o1) != null){
            cur = parentMap.get(01);
            parentSet.add(cur);
        }

        // 获取o2和o1的共同父节点
        cur = o2;
        while (!parentSet.contains(cur)){
            cur = parentMap.get(cur);
        }
        Node lowestAncenter = cur;

        // 计算o1到公共节点的距离
        cur = o1;
        int distance = 1;
        while (cur != lowestAncenter) {
            cur = parentMap.get(cur);
            distance++;
        }

        // 计算o2到公共节点的距离
        cur = o2;
        int o2distance = 1;
        while (cur != lowestAncenter) {
            cur = parentMap.get(cur);
            o2distance++;
        }
        return o2distance + distance - 1;
    }

    public static void getParentNode(Node head,HashMap<Node,Node> parentSet){
        if (head.left !=null) {
            parentSet.put(head.left,head);
            getParentNode(head.left,parentSet);
        }
        if (head.right !=null) {
            parentSet.put(head.right,head);
            getParentNode(head.right,parentSet);
        }
    }

    public static  void preNodeList(Node head,ArrayList<Node> list){
        if (head == null){
            return;
        }
        list.add(head);
        preNodeList(head.left,list);
        preNodeList(head.right,list);
    }

    static class Info{
        public Integer distance;
        public Integer high;
        public Info(Integer distance,Integer high) {
            this.distance = distance;
            this.high = high;
        }
    }
    public static Info maxDistance(Node root){
        if (root == null) {
            return new Info(0,0);
        }
        Info left = maxDistance(root.left);
        Info right = maxDistance(root.right);
        Integer high = Math.max(left.high,right.high);
        Integer leftDis = left.distance;
        Integer rightDis = right.distance;
        Integer maxDistance = left.high + right.high + 1;
        return new Info(maxDistance,high);
    }


    public static Node generateRandomBST (int maxValue,int maxLevel){
        return generate(1,maxValue,maxLevel);
    }

    public static Node generate(int curLevel,int maxValue,int maxLevel){
        if (curLevel > maxLevel || Math.random() < 0.3) {
            return null;
        }
        Node node = new Node((int)(Math.random()*maxValue));
        node.left = generate(curLevel + 1,maxLevel,maxValue);
        node.right = generate(curLevel + 1,maxValue,maxLevel);
        return node;
    }

    public static void main(String[] args) {
        int testTime = 100;
        int maxValue = 100;
        int maxLevel = 5;
        for (int i = 0 ;i < testTime;i++) {
            Node root = generateRandomBST(maxValue,maxLevel);
            if (maxDistance2(root) != maxDistance(root).distance) {
                System.out.println("oop");
            }
        }
        System.out.println("finsh");
    }
}
