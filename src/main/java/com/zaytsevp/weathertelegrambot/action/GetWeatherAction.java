package com.zaytsevp.weathertelegrambot.action;

import com.zaytsevp.weathertelegrambot.model.telegram.MessageParameterType;
import com.zaytsevp.weathertelegrambot.model.telegram.WeatherCodeEmojiLocator;
import com.zaytsevp.weathertelegrambot.model.weather.WeatherInfo;
import com.zaytsevp.weathertelegrambot.service.geocoder.GeoCoderService;
import com.zaytsevp.weathertelegrambot.service.weather.WeatherService;
import com.zaytsevp.weathertelegrambot.util.MessageParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

/**
 * @author Pavel Zaytsev
 */
@Component
public class GetWeatherAction {

    private final WeatherService weatherService;

    private final WeatherCodeEmojiLocator weatherCodeEmojiLocator;

    private final GeoCoderService geoCoderService;

    @Autowired
    public GetWeatherAction(WeatherService weatherService,
                            WeatherCodeEmojiLocator weatherCodeEmojiLocator,
                            GeoCoderService geoCoderService) {
        this.weatherService = weatherService;
        this.weatherCodeEmojiLocator = weatherCodeEmojiLocator;
        this.geoCoderService = geoCoderService;
    }

    public String executeImpl(String inputRawString, Update update) {
        Map<MessageParameterType, String> weatherParams = MessageParser.getWeatherParams(inputRawString);

        String localityName = weatherParams.get(MessageParameterType.CITY);

        Map<String, Double> geoCoordsByCityName = geoCoderService.getGeoCoordsByCityName(localityName);

        WeatherInfo weatherInfoByCityName = getWeatherInfoByCoords(geoCoordsByCityName.get("lat"),
                                                                   geoCoordsByCityName.get("lon"));

        return String.format("Уважаемый %s, прогноз погоды на %s:\n" +
                             "%s\n" +
                             "Населенный пункт %s - %s." +
                             "\nТемпература: %s градусов." +
                             "\nВетер: %d м/с." +
                             "\nОтносительная влажность: %s %%." +
                             "\nДавление: %s мм.",
                             getUsername(update),
                             "сегодня",
                             weatherCodeEmojiLocator.getWeatherEmojiByWeatherCode(weatherInfoByCityName.getWeather().get(0).getId()),
                             localityName,
                             weatherInfoByCityName.getWeather().get(0).getDescription(),
                             weatherInfoByCityName.getMain().getTemp(),
                             weatherInfoByCityName.getWind().getSpeed().intValue(),
                             weatherInfoByCityName.getMain().getHumidity(),
                             weatherInfoByCityName.getMain().getPressure());
    }

    private WeatherInfo getWeatherInfoByCoords(Double latitude, Double longitude) {
        return weatherService.getWeatherInfoByCityCoords(latitude, longitude);
    }

    private String getUsername(Update update) {
        return update.getMessage()
                     .getChat()
                     .getFirstName();
    }
}
