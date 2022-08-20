package org.chen.algorithm.strategymode.impl;

import org.chen.algorithm.strategymode.QuackBehavior;

public class QuackBehaviorImpl implements QuackBehavior {
    @Override
    public void quack() {
        // 会叫的鸭子
        System.out.println("Quack");
    }
}
