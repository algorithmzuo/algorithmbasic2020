package class03;

public class Code04_RingArray_Study {
    public static class MyQueue {
        // 大小一定的数组
        private int[] arr;
        // 数组大小
        private int size;
        // 加入元素指定的下标
        private int addi;
        // 弹出元素指定的下标
        private int polli;

        public MyQueue(int limit){
            arr = new int[limit];
            size = 0;
            addi = 0;
            polli = 0;
        }

        public void add(int value) {
            if (size == arr.length) {
                throw new RuntimeException("数组已满，无法添加新元素");
            }
            arr[addi] = value;
            size++;
            addi = nextIndex(addi);
        }

        public int poll(){
            if (size == 0) {
                throw new RuntimeException("数组为空，无法获取元素");
            }
            int result = arr[polli];
            size--;
            polli = nextIndex(polli);
            return result;
        }

        private int nextIndex(int value) {
            return value < arr.length -1 ? ++value : 0;
        }


        public static void main(String[] args) {
            int times = 20;
            int limit = 5;
            int testNums = 5;
            int range = 20;
            for (int i = 0;i < times;i++) {
                MyQueue queue = new MyQueue(limit);
                for (int j = 0 ;j < testNums ;j++) {
                    queue.add((int)((Math.random() * range) + 1));
                    queue.poll();
                    System.out.println("addi=" + queue.addi + "polli=" + queue.polli) ;
                }
                System.out.println();
            }
        }
    }
}
