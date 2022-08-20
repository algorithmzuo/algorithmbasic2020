package org.chen.algorithm.observermode;

/**
 * 展示天气的布告板
 */
public class CurrentConditionDisplay implements Observer,DisplayElement{
    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    @Override
    public void display() {
        System.out.println("Current Conditions:" + temperature + "F degress and " + humidity + "% humidity");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.display();
    }
}
