package org.chen.algorithm.strategymode;

import org.chen.algorithm.strategymode.impl.FlyBehaviorImpl;
import org.chen.algorithm.strategymode.impl.QuackBehaviorImpl;

public class MuteDuck extends Duck{
    MuteDuck(){
        quackBehavior = new QuackBehaviorImpl();
        flyBehavior = new FlyBehaviorImpl();
    }

    public void display(){
        System.out.println("I'm real MuteDuck");
    }

}
