package cs5800.softwareEngineering.finalExam.weatherForecast.interfaces;

import cs5800.softwareEngineering.finalExam.weatherForecast.model.WeatherData;

public interface WeatherObserver {
    public void update(WeatherData data);
    public String getObserverId();
    public void setObserverId(String observerId);
}
