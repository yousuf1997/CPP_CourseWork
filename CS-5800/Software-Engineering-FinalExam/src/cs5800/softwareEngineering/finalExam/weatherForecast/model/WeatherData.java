package cs5800.softwareEngineering.finalExam.weatherForecast.model;

import java.util.Date;

public class WeatherData {
    private String cityName;
    private String date;
    private String precipitation;
    private String humidity;
    private String wind;
    private String description;

    public WeatherData(String cityName, String date, String precipitation, String humidity, String wind, String description) {
        this.cityName = cityName;
        this.date = date;
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.wind = wind;
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather Data for " + cityName + " on " + date + ":\n" +
                "Precipitation: " + precipitation + "\n" +
                "Humidity: " + humidity + "\n" +
                "Wind: " + wind + "\n" +
                "Description: " + description;
    }

}
