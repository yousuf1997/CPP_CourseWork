package cs5800.softwareEngineering.finalExam.weatherForecast.providers.response;

public class WeatherAPIResponse {
    private String city_name;
    private String date_of_weather;
    private String precipitation_rate;
    private String humidity_rate;
    private String wind_rate;
    private String description_of_weather;

    public WeatherAPIResponse() {
        // Empty constructor
    }

    public WeatherAPIResponse(String city_name, String date_of_weather, String precipitation_rate, String humidity_rate, String wind_rate, String description_of_weather) {
        this.city_name = city_name;
        this.date_of_weather = date_of_weather;
        this.precipitation_rate = precipitation_rate;
        this.humidity_rate = humidity_rate;
        this.wind_rate = wind_rate;
        this.description_of_weather = description_of_weather;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDate_of_weather() {
        return date_of_weather;
    }

    public void setDate_of_weather(String date_of_weather) {
        this.date_of_weather = date_of_weather;
    }

    public String getPrecipitation_rate() {
        return precipitation_rate;
    }

    public void setPrecipitation_rate(String precipitation_rate) {
        this.precipitation_rate = precipitation_rate;
    }

    public String getHumidity_rate() {
        return humidity_rate;
    }

    public void setHumidity_rate(String humidity_rate) {
        this.humidity_rate = humidity_rate;
    }

    public String getWind_rate() {
        return wind_rate;
    }

    public void setWind_rate(String wind_rate) {
        this.wind_rate = wind_rate;
    }

    public String getDescription_of_weather() {
        return description_of_weather;
    }

    public void setDescription_of_weather(String description_of_weather) {
        this.description_of_weather = description_of_weather;
    }
}

