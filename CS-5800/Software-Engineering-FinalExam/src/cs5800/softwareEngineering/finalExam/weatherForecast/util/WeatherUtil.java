package cs5800.softwareEngineering.finalExam.weatherForecast.util;

import java.util.Random;

public class WeatherUtil {
    private static Random randomNumber = null;

    public static Random getRandomNumber() {
        if (randomNumber == null) {
            randomNumber = new Random();
        }
        return randomNumber;
    }

    public static Double round(Double data) {
        return Math.round(data * 100.0) / 100.0;
    }

}
