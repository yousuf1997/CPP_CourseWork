package cs5800.softwareEngineering.finalExam.weatherForecast.drivers;

import cs5800.softwareEngineering.finalExam.weatherForecast.model.ObserverClient;
import cs5800.softwareEngineering.finalExam.weatherForecast.providers.OpenMeteoAPI;
import cs5800.softwareEngineering.finalExam.weatherForecast.providers.WeatherAPI;
import cs5800.softwareEngineering.finalExam.weatherForecast.providers.WeatherForecastAPI;
import cs5800.softwareEngineering.finalExam.weatherForecast.providers.WeatherGovAPI;
import java.util.Date;

public class WeatherForecastAPIDriver {
    public static void main(String[] args) {
        ObserverClient bbcClient = new ObserverClient("BBC Weather News");
        ObserverClient cnnClient = new ObserverClient("CNN Weather News");
        ObserverClient foxClient = new ObserverClient("Fox Weather News");
        ObserverClient newYorkTimesClient = new ObserverClient("New York Times Weather News");
        ObserverClient laTimesClient = new ObserverClient("LA Times Weather News");
        ObserverClient socalNewsClient = new ObserverClient("SOCAL cal News Weather News");

        WeatherForecastAPI api = new WeatherForecastAPI();
        api.registerObserver(bbcClient);
        api.registerObserver(cnnClient);
        api.registerObserver(foxClient);
        api.registerObserver(newYorkTimesClient);
        api.registerObserver(laTimesClient);
        api.registerObserver(socalNewsClient);

        api.setProviderStrategy(new OpenMeteoAPI());
        api.getWeather("Los Angeles", new Date());

        api.setProviderStrategy(new WeatherAPI());
        api.getWeather("San Diego", new Date());

        api.setProviderStrategy(new WeatherGovAPI());
        api.getWeather("San Francisco", new Date());

        // removing
        api.removeObserver(socalNewsClient);
        api.setProviderStrategy(new OpenMeteoAPI());
        api.getWeather("London", new Date());

        // creating new observer
        ObserverClient indianNews = new ObserverClient("India Weather News");
        // notifying the weather report to only this client about the previous data
        api.notifyObserver(indianNews);

    }
}
