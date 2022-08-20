package org.chen.algorithm.observermode;

import java.util.ArrayList;

public class WheatherData implements Subject{
    private ArrayList observers;
    private float temperature;
    private float humidity;
    private float pressure;
    public WheatherData(){
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i > 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0;i < observers.size();i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(temperature,humidity,pressure);
        }
    }

    public void measutementsChanged(){
        notifyObserver();
    }

    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }
}
