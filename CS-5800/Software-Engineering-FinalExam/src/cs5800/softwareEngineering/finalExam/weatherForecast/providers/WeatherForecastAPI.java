package cs5800.softwareEngineering.finalExam.weatherForecast.providers;

import cs5800.softwareEngineering.finalExam.weatherForecast.interfaces.WeatherObserver;
import cs5800.softwareEngineering.finalExam.weatherForecast.interfaces.WeatherService;
import cs5800.softwareEngineering.finalExam.weatherForecast.interfaces.WeatherSubject;
import cs5800.softwareEngineering.finalExam.weatherForecast.model.WeatherData;
import cs5800.softwareEngineering.finalExam.weatherForecast.providers.strategy.WeatherProviderStrategy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherForecastAPI implements WeatherSubject {
    private WeatherProviderStrategy strategy;
    private List<WeatherObserver> observers;
    private WeatherData latestData;

    public WeatherForecastAPI() {
        this.strategy = new WeatherProviderStrategy();
        this.observers = new ArrayList<>();
    }

    public void setProviderStrategy(WeatherService weatherService) {
        if (weatherService == null) {
            return;
        }
        strategy.setStrategy(weatherService);
    }

    /**
     * This does not return the weather data, instead it will notify the
     * observers
     */
    public void getWeather(String location, Date date) {
        this.latestData = strategy.getWeather(location, date);
        System.out.println("[Fetching weather data using " + strategy.getStrategy().getClass().getSimpleName()+"]");
        observers.forEach(observer -> observer.update(latestData));
    }

    @Override
    public void registerObserver(WeatherObserver observer) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }

    @Override
    public void removeObserver(WeatherObserver observer) {
        if (observers == null) {
            return;
        }
        System.out.println("<<<<<<<Removing Observer : " + observer.getObserverId()+">>>>>>>>>>");
        observers.removeIf(observerInList -> observerInList.getObserverId().equalsIgnoreCase(observer.getObserverId()));
    }

    @Override
    public void notifyObserver(WeatherObserver observer) {
        if (observers == null) {
            return;
        }
        System.out.println("Notifying Observer : " + observer.getObserverId());
        observer.update(latestData);
    }
}
