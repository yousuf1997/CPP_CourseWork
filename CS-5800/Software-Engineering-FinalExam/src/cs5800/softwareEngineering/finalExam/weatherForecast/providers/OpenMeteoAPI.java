package cs5800.softwareEngineering.finalExam.weatherForecast.providers;

import cs5800.softwareEngineering.finalExam.weatherForecast.model.WeatherData;
import cs5800.softwareEngineering.finalExam.weatherForecast.providers.response.OpenMeteoAPIResponse;
import cs5800.softwareEngineering.finalExam.weatherForecast.util.WeatherUtil;
import java.util.Date;
import java.util.List;

public class OpenMeteoAPI extends Providers<OpenMeteoAPIResponse> {
    @Override
    public WeatherData getWeatherData(String location, Date date) {
        return transformProviderResponseToWeatherData(makeAPICall(location, date));
    }

    @Override
    protected OpenMeteoAPIResponse makeAPICall(String location, Date date) {
        OpenMeteoAPIResponse lvResponse = new OpenMeteoAPIResponse();
        lvResponse.setCity(location);
        lvResponse.setDate(date.toString());
        lvResponse.setHumidity(WeatherUtil.round(WeatherUtil.getRandomNumber().nextDouble(115)));
        lvResponse.setWind(WeatherUtil.round(WeatherUtil.getRandomNumber().nextDouble(65)));
        lvResponse.setPrecipitation(WeatherUtil.round(WeatherUtil.getRandomNumber().nextDouble(125)));
        lvResponse.setDescription(List.of("Sunny","Cold","Hot","Rain","Snow").get(WeatherUtil.getRandomNumber().nextInt(4)));
        return lvResponse;
    }

    @Override
    protected WeatherData transformProviderResponseToWeatherData(OpenMeteoAPIResponse response) {
        return new WeatherData(response.getCity(), response.getDate(),
                String.valueOf(response.getPrecipitation() + "%"),
                String.valueOf(response.getHumidity() + "%"),
                String.valueOf(response.getWind() + " knots"),
                response.getDescription());
    }

}
