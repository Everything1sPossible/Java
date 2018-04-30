package com.sjh.DesignPattern.ObserverPattern;

import com.sjh.DesignPattern.ObserverPattern.impl.CurrentConditionsDisplay;
import com.sjh.DesignPattern.ObserverPattern.impl.StatisticsDisplay;
import com.sjh.DesignPattern.ObserverPattern.impl.WeatherData;

public class Test {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay conditionsDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);

        Weather weather = new Weather();
        weather.setTemperature(80);
        weather.setHumidity(60.45);
        weather.setPressure(45);
        weatherData.setMeasurements(weather);
    }
}
