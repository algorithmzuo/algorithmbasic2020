package org.chen.algorithm.strategymode.impl;

import org.chen.algorithm.strategymode.QuackBehavior;

public class MuteQuackImpl implements QuackBehavior {
    @Override
    public void quack() {
        // 不会叫的鸭子
        System.out.println("Mute Quack");
    }
}
