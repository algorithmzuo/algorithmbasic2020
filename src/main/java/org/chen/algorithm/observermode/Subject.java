package org.chen.algorithm.observermode;

/**
 * 主题接口
 */
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}
