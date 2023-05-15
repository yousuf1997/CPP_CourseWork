package cs5800.softwareEngineering.finalExam.weatherForecast.providers.response;

public class OpenMeteoAPIResponse {
    private String city;
    private String date;
    private Double precipitation;
    private Double humidity;
    private Double wind;
    private String description;

    public OpenMeteoAPIResponse() {
        // Empty constructor
    }

    public OpenMeteoAPIResponse(String city, String date, Double precipitation, Double humidity, Double wind, String description) {
        this.city = city;
        this.date = date;
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.wind = wind;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getWind() {
        return wind;
    }

    public void setWind(Double wind) {
        this.wind = wind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

