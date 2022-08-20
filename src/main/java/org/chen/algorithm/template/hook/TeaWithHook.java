package org.chen.algorithm.template.hook;

import org.chen.algorithm.template.CaffeinBeverage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

public class TeaWithHook extends CaffeinBeverageWithHook {

    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addElements() {
        System.out.println("Adding Lemon");
    }

    @Override
    boolean customerWantsCondiments() {
        String answer = getUserInput();
        if (answer.toLowerCase(Locale.ROOT).startsWith("y")) {
            return true;
        }else {
            return false;
        }
    }

    public String getUserInput(){
        String answer = null;
        System.out.println("Would you like milk and suger with your coffee?");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            answer = bufferedReader.readLine();

        }catch (Exception ex){
            System.err.println("IO Error trying to read your answer");
        }
        if (answer == null) {
            return "no";
        }
        return answer;
    }
}
