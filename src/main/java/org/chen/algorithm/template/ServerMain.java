package org.chen.algorithm.template;

import org.chen.algorithm.template.hook.TeaWithHook;

import java.util.Arrays;

/**
 * 模板模式：该模板会保护一个算法也就是模板，将代码的复用最大化
 * 模板方法模式在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中，模板使得子类可以在
 * 不改变算法结构的情况下，重新定义算法中的这些步骤
 */
public class ServerMain {
    public static void main(String[] args) {
        // 不适用钩子
        Tea tea = new Tea();
        tea.prepareRecipe();

        Coffee coffee = new Coffee();
        coffee.prepareRecipe();

        // 使用钩子
        TeaWithHook teaWithHook = new TeaWithHook();
        System.out.println("making...");
        teaWithHook.prepareRecipe();

        String[] strings = new String[12];
    }

}
