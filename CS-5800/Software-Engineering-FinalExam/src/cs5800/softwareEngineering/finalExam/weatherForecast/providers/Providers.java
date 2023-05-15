package cs5800.softwareEngineering.finalExam.weatherForecast.providers;

import cs5800.softwareEngineering.finalExam.weatherForecast.interfaces.WeatherService;
import cs5800.softwareEngineering.finalExam.weatherForecast.model.WeatherData;
import java.util.Date;

public abstract class Providers<T> implements WeatherService {
    protected abstract T makeAPICall(String location, Date date);
    protected abstract WeatherData transformProviderResponseToWeatherData(T response);
}
