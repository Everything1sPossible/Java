package com.sjh.DesignPattern.ObserverPattern.impl;

import com.sjh.DesignPattern.ObserverPattern.DisplayElement;
import com.sjh.DesignPattern.ObserverPattern.Observer;
import com.sjh.DesignPattern.ObserverPattern.Subject;
import com.sjh.DesignPattern.ObserverPattern.Weather;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private Weather weather;
    private Subject weatherData;
    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    @Override
    public void update(Weather weather) {
        this.weather = weather;
        display();
    }
    @Override
    public void display() {
        System.out.println("CurrentConditionsDisplay{" +
                "temperature=" + this.weather.getTemperature() +
                ", humidity=" + this.weather.getHumidity() +
                ", pressure=" + this.weather.getPressure() +
                '}');
    }

}
