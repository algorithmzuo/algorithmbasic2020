package leo.class08_12;

import class08_12.Code08_MaxDistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Leo
 * @ClassName Max
 * @DATE 2020/12/11 7:00 下午
 * @Description 求二叉树的两个节点的最大距离
 * 1.与顶级头结点有关
 * 2.最大距离在左子树上
 * 3.最大距离在右子树上
 * 4.最大距离是左子树的高度与头结点的距离
 * 5.最大距离是右子树的高度与头结点的距离
 *
 */
public class MaxDistance {

    static class Code{
        public static int maxDistance(Node head) {
            return process(head).maxDistance;
        }

        public static Info process(Node node) {
            if (node == null) {
                return new Info(0, 0);
            }
            Info left = process(node.left);
            Info right = process(node.right);
            int maxDistance;
            int height = Math.max(left.height, right.height) + 1;
            int p1 = left.maxDistance;
            int p2 = right.maxDistance;
            int p3 = left.height + right.height + 1;
            maxDistance = Math.max(Math.max(p1, p2), p3);
            return new Info(maxDistance, height);

        }

        static class Info{
            int maxDistance;
            int height;

            public Info(int max, int h) {
                this.maxDistance = max;
                this.height = h;
            }
        }
    }

    static class Code1{
        public static int maxDistance(Node head){
            return process(head).maxDistance;
        }

        public static Info process(Node node){
            if (node == null) {
                return new Info(0, 0);
            }
            Info left = process(node.left);
            Info right = process(node.right);
            int p1 = left.maxDistance;
            int p2 = right.maxDistance;
            int h = Math.max(right.height, left.height) + 1;
            int p3 = right.height + left.height + 1;
            int maxDistance = Math.max(Math.max(p1, p2), p3);
            return new Info(maxDistance, h);
        }

        public static class Info{
            int height;
            int maxDistance;
            public Info(int max,int h){
                this.maxDistance = max;
                this.height = h;
            }
        }


    }


    static class Logarithm{
        public static int maxDistance1(Node head) {
            if (head == null) {
                return 0;
            }
            ArrayList<Node> arr = getPrelist(head);
            HashMap<Node, Node> parentMap = getParentMap(head);
            int max = 0;
            for (int i = 0; i < arr.size(); i++) {
                for (int j = i; j < arr.size(); j++) {
                    max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
                }
            }
            return max;
        }

        public static ArrayList<Node> getPrelist(Node head) {
            ArrayList<Node> arr = new ArrayList<>();
            fillPrelist(head, arr);
            return arr;
        }

        public static void fillPrelist(Node head, ArrayList<Node> arr) {
            if (head == null) {
                return;
            }
            arr.add(head);
            fillPrelist(head.left, arr);
            fillPrelist(head.right, arr);
        }

        public static HashMap<Node, Node> getParentMap(Node head) {
            HashMap<Node, Node> map = new HashMap<>();
            map.put(head, null);
            fillParentMap(head, map);
            return map;
        }

        public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
            if (head.left != null) {
                parentMap.put(head.left, head);
                fillParentMap(head.left, parentMap);
            }
            if (head.right != null) {
                parentMap.put(head.right, head);
                fillParentMap(head.right, parentMap);
            }
        }

        public static int distance(HashMap<Node, Node> parentMap, Node o1, Node o2) {
            HashSet<Node> o1Set = new HashSet<>();
            Node cur = o1;
            o1Set.add(cur);
            while (parentMap.get(cur) != null) {
                cur = parentMap.get(cur);
                o1Set.add(cur);
            }
            cur = o2;
            while (!o1Set.contains(cur)) {
                cur = parentMap.get(cur);
            }
            Node lowestAncestor = cur;
            cur = o1;
            int distance1 = 1;
            while (cur != lowestAncestor) {
                cur = parentMap.get(cur);
                distance1++;
            }
            cur = o2;
            int distance2 = 1;
            while (cur != lowestAncestor) {
                cur = parentMap.get(cur);
                distance2++;
            }
            return distance1 + distance2 - 1;
        }

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
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (Logarithm.maxDistance1(head) != Code1.maxDistance(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
