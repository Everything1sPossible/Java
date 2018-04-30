package com.sjh.DesignPattern.ObserverPattern.Ext;


import com.sjh.DesignPattern.ObserverPattern.DisplayElement;
import com.sjh.DesignPattern.ObserverPattern.Weather;

import java.util.Observable;
import java.util.Observer;
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private Weather weather;
    private Observable observable;
    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
//            WeatherData weatherData = (WeatherData)o;
            this.weather = (Weather)arg;
            display();
        }
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
