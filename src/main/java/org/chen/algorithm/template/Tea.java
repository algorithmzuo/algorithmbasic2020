package org.chen.algorithm.template;

public class Tea extends CaffeinBeverage{

    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addElements() {
        System.out.println("Adding Lemon");
    }
}
