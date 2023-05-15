package cs5800.softwareEngineering.finalExam.weatherForecast.providers.strategy;

import cs5800.softwareEngineering.finalExam.weatherForecast.interfaces.WeatherService;
import cs5800.softwareEngineering.finalExam.weatherForecast.model.WeatherData;

import java.util.Date;

public class WeatherProviderStrategy {
    private WeatherService weatherService;

    public WeatherProviderStrategy() {
    }

    public void setStrategy(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public WeatherService getStrategy() {
        return this.weatherService;
    }

    public WeatherData getWeather(String location, Date date) {
        return weatherService.getWeatherData(location, date);
    }

}
