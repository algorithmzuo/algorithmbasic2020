package org.chen.algorithm.template;

public abstract class CaffeinBeverage {
    final void prepareRecipe(){
        boilwater();
        brew();
        pourInCup();
        addElements();
    }

    abstract void brew();
    abstract void addElements();


    void boilwater(){
        System.out.println("Boiling water");
    }

    void pourInCup(){
        System.out.println("Pouring into cup");
    }
}
