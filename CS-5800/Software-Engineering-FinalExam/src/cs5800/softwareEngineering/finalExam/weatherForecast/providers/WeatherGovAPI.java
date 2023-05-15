package cs5800.softwareEngineering.finalExam.weatherForecast.providers;

import cs5800.softwareEngineering.finalExam.weatherForecast.model.WeatherData;
import cs5800.softwareEngineering.finalExam.weatherForecast.providers.response.WeatherGovAPIResponse;
import cs5800.softwareEngineering.finalExam.weatherForecast.util.WeatherUtil;
import java.util.Date;
import java.util.List;

public class WeatherGovAPI extends Providers<WeatherGovAPIResponse> {
    @Override
    public WeatherData getWeatherData(String location, Date date) {
        return transformProviderResponseToWeatherData(makeAPICall(location, date));
    }

    @Override
    protected WeatherGovAPIResponse makeAPICall(String location, Date date) {
        WeatherGovAPIResponse lvResponse = new WeatherGovAPIResponse();
        lvResponse.setNameOfCity(location);
        lvResponse.setDateOfWeather(date.toString());
        lvResponse.setRateOfHumidity(WeatherUtil.round(WeatherUtil.getRandomNumber().nextDouble(115)));
        lvResponse.setRateOfWind(WeatherUtil.round(WeatherUtil.getRandomNumber().nextDouble(65)));
        lvResponse.setRateOfPrecipitation(WeatherUtil.round(WeatherUtil.getRandomNumber().nextDouble(125)));
        lvResponse.setDescriptionOfWeather(List.of("Sunny","Cold","Hot","Rain","Snow").get(WeatherUtil.getRandomNumber().nextInt(4)));
        return lvResponse;
    }

    @Override
    protected WeatherData transformProviderResponseToWeatherData(WeatherGovAPIResponse response) {
        return new WeatherData(response.getNameOfCity(), response.getDateOfWeather(),
                response.getRateOfPrecipitation().toString(),
                response.getRateOfHumidity() + "%",
                response.getRateOfWind() + " knots",
                response.getDescriptionOfWeather());
    }

}
