package org.chen.algorithm.observermode;

public class WeahterStation {
    public static void main(String[] args) {
        WheatherData wheatherData = new WheatherData();
        CurrentConditionDisplay display = new CurrentConditionDisplay(wheatherData);
        wheatherData.setMeasurements(80,65,30.4f);
        
    }
}
