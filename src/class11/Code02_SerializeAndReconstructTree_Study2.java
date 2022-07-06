package class11;

import class33.Hash;
import util.PrintTree;
import util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化和反序列化
 */
public class Code02_SerializeAndReconstructTree_Study2 {


    /**
     * 这里通过先序遍历序列化
     * @param root
     */
    public static Queue<String> preSerialize(TreeNode root){
        Queue<String> ser = new LinkedList<>();
        pres(root,ser);
        return ser;
    }

    public static void pres(TreeNode root,Queue<String> ser){
        if (root == null){
            ser.add(null);
        }else {
            ser.add(root.val);
            pres(root.left,ser);
            pres(root.right,ser);
        }
    }

    /**
     * 先序序列化的反序列化
     */
    public static TreeNode buildByPreOrder(Queue<String> ser){
        if (ser == null || ser.size() == 0){
            return null;
        }
        return preb(ser);
    }
    
    public static TreeNode preb(Queue<String> ser){
        String str = ser.poll();
        if (str ==  null){
            return null;
        }
        TreeNode head = new TreeNode(str);
        head.left = preb(ser);
        head.right = preb(ser);
        return head;
    }

    /**
     * 后序序列化
     * @param node
     * @return
     */
    public static Queue<String> postSerialize(TreeNode node){
        Queue<String> ser = new LinkedList<>();
        posSer(node,ser);
        return ser;
    }

    public static void posSer(TreeNode head,Queue<String> ser){
        if (head == null){
            ser.add(null);
        }else {
            posSer(head.left,ser);
            posSer(head.right,ser);
            ser.add(head.val);
        }
    }


    /**
     * 后序反序列化
     * @return
     */
    public static TreeNode posDeSer(Queue<String> ser){
        if (ser == null || ser.size() == 0) {
            return null;
        }
        // 原序列顺序是 左右中，放到stack中后，顺序就是中右左，这就成了前序遍历的反序列了
        Stack<String> stack = new Stack<>();
        while (!ser.isEmpty()) {
            stack.push(ser.poll());
        }
        return buildByPos(stack);
    }

    public static TreeNode buildByPos(Stack<String> ser){
        String str = ser.pop();
        if (str == null){
            return null;
        }
        TreeNode head = new TreeNode(str);
        head.right = buildByPos(ser);
        head.left = buildByPos(ser);
        return head;
    }

    /**
     * 和层级遍历没啥区别，只是对了一个存储节点值的队列
     * @param node
     * @return
     */
    public static Queue<String> levelSeri(TreeNode node){
        Queue<String> queue = new LinkedList<>();
        if (node == null){
            queue.add(null);
        }else{
            Queue<TreeNode> levelSer = new LinkedList<>();
            levelSer.add(node);
            queue.add(node.val);
            while(!levelSer.isEmpty()){
                TreeNode node1 = levelSer.poll();
                if (node1.left != null){
                    queue.add(node1.left.val);
                    levelSer.add(node1.left);
                }else {
                    queue.add(null);
                }
                if (node1.right != null) {
                    queue.add(node1.right.val);
                    levelSer.add(node1.right);
                }else{
                    queue.add(null);
                }
            }
        }
        return queue;
    }

    public static TreeNode buildByLevel(Queue<String> ser){
        if (ser == null && ser.size() == 0){
            return null;
        }
        TreeNode head = generateNode(ser.poll());
        Queue<TreeNode> levelNode = new LinkedList<>();
        if (head != null) {
            levelNode.add(head);
        }
        while (!levelNode.isEmpty()){
            TreeNode node = levelNode.poll();
            node.left = generateNode(ser.poll());
            node.right = generateNode(ser.poll());
            if (node.left != null){
                levelNode.add(node.left);
            }
            if (node.right != null){
                levelNode.add(node.right);
            }
        }
        return head;
    }

    public static TreeNode generateNode(String str){
        if (str == null){
            return null;
        }else {
            return new TreeNode(str);
        }
    }


    public static TreeNode generateRandomBST(int maxLevel,int maxValue){
        return generate(1,maxLevel,maxValue);
    }

    public static TreeNode generate(int curLevel,int maxLevel,int maxValue){
        if (curLevel> maxLevel || Math.random() < 0.5){
            return null;
        }
        TreeNode head = new TreeNode(String.valueOf((int)(Math.random()*(maxValue + 1))));
        head.left = generate(curLevel + 1,maxLevel,maxValue);
        head.right = generate(curLevel+1,maxLevel,maxValue);
        return head;
    }

    public static void main(String[] args) {
        PrintTree printTree = new PrintTree();
//        TreeNode head = generateRandomBST(5,100);
//        System.out.println("old pre Tree:");
//        PrintTree.printTree(head);
//        Queue<String> strings = preSerialize(head);
//        System.out.println(Arrays.toString(strings.toArray()));
//        TreeNode newNode = buildByPreOrder(strings);
//        System.out.println("new pre Tree:");
//        PrintTree.printTree(newNode);

        // 后序序列化
//        TreeNode posHead = generateRandomBST(5,100);
//        System.out.println("old pos Tree:");
//        PrintTree.printTree(posHead);
//        Queue<String> stringQueue = postSerialize(posHead);
//        System.out.println(Arrays.toString(stringQueue.toArray()));
//        TreeNode node = posDeSer(stringQueue);
//        System.out.println("new pos Tree:");
//        PrintTree.printTree(node);

        // 层级序列化
        TreeNode levelHead = generateRandomBST(5,100);
        System.out.println("old pos Tree:");
        printTree.printTree(levelHead);
        Queue<String> stringQueue = levelSeri(levelHead);
        System.out.println(Arrays.toString(stringQueue.toArray()));
        TreeNode node = buildByLevel(stringQueue);
        System.out.println("new pos Tree:");

    }

}
