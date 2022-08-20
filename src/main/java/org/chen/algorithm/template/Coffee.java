package org.chen.algorithm.template;

public class Coffee extends CaffeinBeverage {
    @Override
    void brew() {
        System.out.println("Dripping Coffe through filter");
    }

    @Override
    void addElements() {
        System.out.println("Adding suger and Milk");
    }
}
