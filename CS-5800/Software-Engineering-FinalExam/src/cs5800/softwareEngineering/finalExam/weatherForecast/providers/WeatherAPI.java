package cs5800.softwareEngineering.finalExam.weatherForecast.providers;

import cs5800.softwareEngineering.finalExam.weatherForecast.model.WeatherData;
import cs5800.softwareEngineering.finalExam.weatherForecast.providers.response.WeatherAPIResponse;
import cs5800.softwareEngineering.finalExam.weatherForecast.util.WeatherUtil;
import java.util.Date;
import java.util.List;

public class WeatherAPI extends Providers<WeatherAPIResponse> {
    @Override
    public WeatherData getWeatherData(String location, Date date) {
        return transformProviderResponseToWeatherData(makeAPICall(location, date));
    }

    @Override
    protected WeatherAPIResponse makeAPICall(String location, Date date) {
        WeatherAPIResponse lvResponse = new WeatherAPIResponse();
        lvResponse.setCity_name(location);
        lvResponse.setDate_of_weather(date.toString());
        lvResponse.setHumidity_rate(WeatherUtil.round(WeatherUtil.getRandomNumber().nextDouble(115)).toString());
        lvResponse.setWind_rate(WeatherUtil.round(WeatherUtil.getRandomNumber().nextDouble(65)).toString());
        lvResponse.setPrecipitation_rate(WeatherUtil.round(WeatherUtil.getRandomNumber().nextDouble(125)).toString());
        lvResponse.setDescription_of_weather(List.of("Sunny","Cold","Hot","Rain","Snow").get(WeatherUtil.getRandomNumber().nextInt(4)));
        return lvResponse;
    }

    @Override
    protected WeatherData transformProviderResponseToWeatherData(WeatherAPIResponse response) {
        return new WeatherData(response.getCity_name(), response.getDate_of_weather(),
                response.getPrecipitation_rate(),
                response.getHumidity_rate() + "%",
                response.getWind_rate() + " knots",
                response.getDescription_of_weather());
    }

}
