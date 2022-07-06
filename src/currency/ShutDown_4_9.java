package currency;

import java.util.concurrent.TimeUnit;

public class ShutDown_4_9 {
    public static void main(String[] args) throws Exception{
        Runner one = new Runner();
        Thread currentThread = new Thread(one, "CountThread1");
        currentThread.start();
        TimeUnit.SECONDS.sleep(1);
        currentThread.interrupt();
        Runner two = new Runner();
        currentThread = new Thread(two,"CountThread2");
        currentThread.start();
        TimeUnit.SECONDS.sleep(1);
        two.cancle();
    }

    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted() ) {
                i++;
            }
            System.out.println("Count i = " + i);
        }
        public void cancle(){
            on = false;
        }
    }
}
