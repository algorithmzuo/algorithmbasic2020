package class11;

import java.util.*;

/**
 * 对二叉树进行序列化和反序列化，序列化的遍历方式有前序遍历和后序遍历以及层级遍历
 * 没有中序遍历，因为中序遍历会有歧义：
 *    __2
 *    /
 *  1
 *          1__
 *              \
 *                2
 * 以上两种结构的中序遍历方式都是[null,1,null,2,null]
 */
public class Code02_SerializeAndReconstructTree_Study {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 先序方式序列化
     * @return
     */
    public static Queue<String> preSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        pres(ans,head);
        return ans;
    }

    /**
     * 先序遍历二叉树，存入每个节点的值，如果为null，则存入"null"子串
     * @param ans
     * @param head
     */
    public static void pres(Queue<String> ans,Node head){
        if (head == null){
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.value));
            pres(ans,head.left);
            pres(ans,head.right);
        }
    }

    /**
     * 先序遍历序的反序列化
     * 1.遇到null，返回null
     * 2.有值则挂入新的节点
     * @param preStr
     * @return
     */
    public static Node buildByPreSerial(Queue<String> preStr){
        if (preStr.isEmpty()){
            return null;
        }
        return buildPre(preStr);
    }

    /**
     * 前序反序列化
     * @param preStr
     * @return
     */
    public static Node buildPre(Queue<String> preStr){
        String value = preStr.poll();
        if (value == null){
            return null;
        }
        Node node = new Node(Integer.valueOf(value));
        node.left = buildPre(preStr);
        node.right = buildPre(preStr);
        return node;
    }

    public static Node generateRandomBST(int maxLevel,int maxValue){
        return genrate(1,maxLevel,maxValue);
    }

    /**
     * 后序序列化
     * @param head
     * @return
     */
    public static Queue<String> posSerial(Node head){
        if(head == null){
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        poss(queue,head);
        return queue;
    }

    /**
     * 后序序列化
     * @param head
     * @return
     */
    public static void poss(Queue<String> queue,Node head){
        if (head == null){
            queue.add(null);
        }else {
            poss(queue, head.left);
            poss(queue, head.right);
            queue.add(String.valueOf(head.value));
        }
    }

    /**
     * 后序遍历反序列化
     * @param queue
     */
    public static Node buildByPost(Queue<String> queue){
        if (queue == null || queue.size() ==0){
            return null;
        }
        /**
         * 后序遍历后，根节点到了最后面
         * 左右中->中右左
         */
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return buildPost(stack);
    }

    /**
     * 反后序序列化
     * @param stack
     * @return
     */
    public static Node buildPost(Stack<String> stack){
        String value = stack.pop();
        if(value == null){
            return null;
        }else {
            Node head = new Node(Integer.valueOf(value));
            // 当前元素的顺序是中右左，因此分支先还原右分支，再还原左分支
            head.right = buildPost(stack);
            head.left = buildPost(stack);
            return head;
        }
    }

    /**
     * 层级遍历序列化
     * 1.创建保存节点值的队列queue1
     * 2.创建报存节点的队列queue2
     * 3.如果节点不为空，那么加入队列
     * 4.判断节点的左右节点是否存在，如果存在的需要加入到
     * 5.进入循环，每次弹出一个节点，重复步骤4
     * @param head
     * @return
     */
    public static Queue<String> levelSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        if (head == null){
            ans.add(null);
        }else {
            // 保存头节点
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while(!queue.isEmpty()) {
                // 这里把每个节点的左右子节点放进对列，没有子节点就不放
                // 没有子节点的位置，需要用null顶替放入序列化的队列
                Node node = queue.poll();
                if (node.left != null){
                    ans.add(String.valueOf(node.left.value));
                    queue.add(node.left);
                }else {
                    ans.add(null);
                }

            }
        }
        return ans;
    }

    /**
     * 构建二叉树：
     * 1.递归跳出条件，已经到达给定的层数，通过随机数50%的概率为null
     * 2.不为null的话创建新的节点
     * 3.生成
     *
     * @param level
     * @param maxlevel
     * @param maxValue
     * @return
     */
    public static Node genrate(int level,int maxlevel,int maxValue){
        if (level > maxlevel || Math.random() < 0.5) {
            return null;
        }
        Node node = new Node((int) (Math.random()*maxValue));
        node.left = genrate((level + 1),maxlevel,maxValue);
        node.right = genrate((level + 1),maxlevel,maxValue);
        // 最终返回网点头
        return node;
    }

    public static void printQueueAllElment(Queue<String> queue){
        System.out.println(Arrays.toString(queue.toArray()));
    }

    /**
     * 判断反序列化后数据是否相等
     * @param head1
     * @param head2
     * @return
     */
    public static boolean isSameValueStracture(Node head1,Node head2){
        if (head1 == null && head2 != null){
            return false;
        }
        if (head1 != null && head2 == null){
            return false;
        }
        if (head1 == null && head2 == null){
            return true;
        }
        if (head1.value != head2.value){
            return false;
        }
        return isSameValueStracture(head1.left,head2.left) && isSameValueStracture(head1.right,head2.right);
    }

    public static void main(String[] args) {
        int maxValue = 100;
        int maxLevel = 5;
        int testTime = 100;

        for (int i = 0 ; i < testTime;i++) {
            Node headNode = generateRandomBST(maxLevel, maxValue);
            Queue<String> preSerial = preSerial(headNode);
            Queue<String> posSerial = posSerial(headNode);

            Node buildByPre = buildByPreSerial(preSerial);
            Node buildByPos = buildByPost(posSerial);
            if (!isSameValueStracture(buildByPos,buildByPre) ){
                System.out.println("Oops");
            }
        }
        System.out.println("Done");
    }


}
