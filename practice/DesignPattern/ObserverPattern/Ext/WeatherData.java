package com.sjh.DesignPattern.ObserverPattern.Ext;

import com.sjh.DesignPattern.ObserverPattern.Weather;

import java.util.Observable;

public class WeatherData extends Observable {
    private Weather weather;

    public WeatherData() {
    }
    public void measurementsChanged() {
        setChanged();//表明状态发生改变
        //调用没有参数的方法，表明“观察者”们将会“拉”数据，而不是“推送”给他们
//        notifyObservers();
        notifyObservers(weather);
    }

    public void setMeasurements(Weather weather) {
        this.weather = weather;
        measurementsChanged();
    }

    public Weather getWeather() {
        return weather;
    }
}
