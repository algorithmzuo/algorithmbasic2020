package org.chen.algorithm.strategymode.impl;

import org.chen.algorithm.strategymode.FlyBehavior;

public class FlyBehaviorImpl implements FlyBehavior {
    @Override
    public void fly() {
        // 会飞的鸭子
        System.out.println("I'm flying");
    }
}
