package currency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * TwinsLock表示同一时刻至多允许两个线程同时访问
 */
public class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer{
        Sync(int count){
            if (count < 0) {
                throw new IllegalArgumentException("count must large than zero.");
            }else {
                // 表明资源同步数是2
                setState(2);
            }
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (;;){
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current,newCount)){
                    // 如果newCount < 0，这里返回false，表示未获取同步状态
                    // 如果newCount > 0 ，且cas返回true，表示获取同步状态
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (;;){
                int current = getState();
                int newCount = returnCount + current;
                if (compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }
    }
    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.releaseShared(1);
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
