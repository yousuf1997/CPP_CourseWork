package cs5800.softwareEngineering.finalExam.weatherForecast.interfaces;

import cs5800.softwareEngineering.finalExam.weatherForecast.model.WeatherData;

import java.util.Date;

public interface WeatherService {
    public WeatherData getWeatherData(String location, Date date);
}
