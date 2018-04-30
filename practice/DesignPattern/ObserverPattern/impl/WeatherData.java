package com.sjh.DesignPattern.ObserverPattern.impl;

import com.sjh.DesignPattern.ObserverPattern.Observer;
import com.sjh.DesignPattern.ObserverPattern.Subject;
import com.sjh.DesignPattern.ObserverPattern.Weather;

import java.util.ArrayList;

/**
 * “主题”接口实现类
 * 获取天气信息
 */
public class WeatherData implements Subject {
    private ArrayList observers; //观察者列表
    private Weather weather;

    public WeatherData() {
        this.observers = new ArrayList();
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
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(this.weather);
        }
    }

    public void measurementsChanged() {
        notifyObserver();
    }

    public void setMeasurements(Weather weather) {
        this.weather = weather;
        measurementsChanged();
    }
}
