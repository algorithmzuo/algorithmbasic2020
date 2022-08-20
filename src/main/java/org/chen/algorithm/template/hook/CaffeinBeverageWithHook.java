package org.chen.algorithm.template.hook;

/**
 * 钩子是一种被声明在抽象类中的方法，但只有空的或者默认的实现，
 * 钩子的存在让子类有能力对算法的不同点进行挂钩。
 * 也就是说这个钩子要不要实现是由子类决定的
 */
public abstract class CaffeinBeverageWithHook {
    public final void prepareRecipe(){
        boilwater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addElements();
        }
    }

    abstract void brew();
    abstract void addElements();


    void boilwater(){
        System.out.println("Boiling water");
    }

    void pourInCup(){
        System.out.println("Pouring into cup");
    }

    boolean customerWantsCondiments(){
        return true;
    }
}
