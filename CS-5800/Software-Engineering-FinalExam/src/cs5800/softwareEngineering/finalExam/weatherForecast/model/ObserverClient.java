package cs5800.softwareEngineering.finalExam.weatherForecast.model;

import cs5800.softwareEngineering.finalExam.weatherForecast.interfaces.WeatherObserver;

public class ObserverClient implements WeatherObserver {
    private String observerId;

    public ObserverClient(String observerId) {
        this.observerId = observerId;
    }
    @Override
    public void update(WeatherData data) {
        System.out.println("[" + this.observerId + " received weather updates.]");
        System.out.println(data.toString());
        System.out.println(" --------------------- " );
    }

    @Override
    public String getObserverId() {
        return this.observerId;
    }

    @Override
    public void setObserverId(String observerId) {
        this.observerId = observerId;
    }
}
