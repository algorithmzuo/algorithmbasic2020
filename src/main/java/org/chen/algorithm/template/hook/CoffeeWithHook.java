package org.chen.algorithm.template.hook;

import org.chen.algorithm.template.CaffeinBeverage;

public class CoffeeWithHook extends CaffeinBeverageWithHook {
    @Override
    void brew() {
        System.out.println("Dripping Coffe through filter");
    }

    @Override
    void addElements() {
        System.out.println("Adding suger and Milk");
    }
}
