package org.chen.algorithm.strategymode;

public class ServerMain {
    public static void main(String[] args) {
        Duck duck = new MuteDuck();
        duck.performFly();
        duck.performQuack();
    }
}
