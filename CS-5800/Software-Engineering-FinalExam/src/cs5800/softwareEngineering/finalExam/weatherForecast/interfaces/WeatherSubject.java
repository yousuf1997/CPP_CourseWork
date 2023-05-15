package cs5800.softwareEngineering.finalExam.weatherForecast.interfaces;

public interface WeatherSubject {
    public void registerObserver(WeatherObserver observer);
    public void removeObserver(WeatherObserver observer);
    public void notifyObserver(WeatherObserver observer);
}
