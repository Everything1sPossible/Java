package com.sjh.DesignPattern.ObserverPattern.Ext;

import com.sjh.DesignPattern.ObserverPattern.Weather;

public class Test {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay conditionsDisplay =
                new CurrentConditionsDisplay(weatherData);

        Weather weather = new Weather();
        weather.setTemperature(80);
        weather.setHumidity(60.45);
        weather.setPressure(45);
        weatherData.setMeasurements(weather);
    }
}
