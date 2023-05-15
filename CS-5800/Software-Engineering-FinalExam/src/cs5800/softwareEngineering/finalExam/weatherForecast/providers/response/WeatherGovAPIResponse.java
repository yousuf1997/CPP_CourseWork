package cs5800.softwareEngineering.finalExam.weatherForecast.providers.response;

public class WeatherGovAPIResponse {
    private String nameOfCity;
    private String dateOfWeather;
    private Double rateOfPrecipitation;
    private Double rateOfHumidity;
    private Double rateOfWind;
    private String descriptionOfWeather;

    public WeatherGovAPIResponse() {
        // Empty constructor
    }

    public WeatherGovAPIResponse(String nameOfCity, String dateOfWeather, Double rateOfPrecipitation, Double rateOfHumidity, Double rateOfWind, String descriptionOfWeather) {
        this.nameOfCity = nameOfCity;
        this.dateOfWeather = dateOfWeather;
        this.rateOfPrecipitation = rateOfPrecipitation;
        this.rateOfHumidity = rateOfHumidity;
        this.rateOfWind = rateOfWind;
        this.descriptionOfWeather = descriptionOfWeather;
    }

    public String getNameOfCity() {
        return nameOfCity;
    }

    public void setNameOfCity(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }

    public String getDateOfWeather() {
        return dateOfWeather;
    }

    public void setDateOfWeather(String dateOfWeather) {
        this.dateOfWeather = dateOfWeather;
    }

    public Double getRateOfPrecipitation() {
        return rateOfPrecipitation;
    }

    public void setRateOfPrecipitation(Double rateOfPrecipitation) {
        this.rateOfPrecipitation = rateOfPrecipitation;
    }

    public Double getRateOfHumidity() {
        return rateOfHumidity;
    }

    public void setRateOfHumidity(Double rateOfHumidity) {
        this.rateOfHumidity = rateOfHumidity;
    }

    public Double getRateOfWind() {
        return rateOfWind;
    }

    public void setRateOfWind(Double rateOfWind) {
        this.rateOfWind = rateOfWind;
    }

    public String getDescriptionOfWeather() {
        return descriptionOfWeather;
    }

    public void setDescriptionOfWeather(String descriptionOfWeather) {
        this.descriptionOfWeather = descriptionOfWeather;
    }
}

