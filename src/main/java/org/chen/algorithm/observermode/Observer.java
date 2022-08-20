package org.chen.algorithm.observermode;

public interface Observer {
    // 把观测值直接传入观察者中并不合适，如果说后期参数修改了，那么会导致大量代码修改
    void update(float temp, float humidity,float pressure);
}
