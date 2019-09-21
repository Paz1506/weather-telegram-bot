package com.zaytsevp.weathertelegrambot.action;

import com.zaytsevp.weathertelegrambot.model.geocoder.LocalityWithCoordsProjection;
import com.zaytsevp.weathertelegrambot.model.telegram.MessageParameterType;
import com.zaytsevp.weathertelegrambot.model.telegram.WeatherCodeEmojiLocator;
import com.zaytsevp.weathertelegrambot.model.weather.ForecastWeatherInfo;
import com.zaytsevp.weathertelegrambot.model.weather.WeatherInfo;
import com.zaytsevp.weathertelegrambot.service.geocoder.GeoCoderService;
import com.zaytsevp.weathertelegrambot.service.weather.WeatherService;
import com.zaytsevp.weathertelegrambot.util.MessageParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Pavel Zaytsev
 */
@Component
public class GetWeatherAction {

    private final WeatherService weatherService;

    private final WeatherCodeEmojiLocator weatherCodeEmojiLocator;

    private final GeoCoderService geoCoderService;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YY HH:mm");

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

        LocalityWithCoordsProjection geoInfoByCityName = geoCoderService.getGeoCoordsByCityName(localityName);

        WeatherInfo weatherInfoByCityName = getWeatherInfoByCoords(geoInfoByCityName.getCoords().get("lat"),
                                                                   geoInfoByCityName.getCoords().get("lon"));

        ForecastWeatherInfo forecastWeatherInfo = getForecastWeatherInfo(geoInfoByCityName.getCoords().get("lat"),
                                                                         geoInfoByCityName.getCoords().get("lon"));

        List<WeatherInfo> weatherInfoFor3Days = getWeatherInfoFor3Days(forecastWeatherInfo, update.getMessage().getDate());

        String formattedForecastWeatherInfo = getFormattedForecastWeatherInfo(weatherInfoFor3Days);

        return String.format("Уважаемый %s, прогноз погоды на %s:\n" +
                             "%s Населенный пункт %s - %s." +
                             "\nТемпература: %s градусов." +
                             "\nВетер: %d м/с." +
                             "\nОтносительная влажность: %s %%." +
                             "\nДавление: %s мм." +
                             "\n\u2796\u2796\u2796\u2796\u2796\u2796\u2796\u2796\u2796\u2796 \n" +
                             " Прогноз на следующие 5 дней:" +
                             "\n\u2796\u2796\u2796\u2796\u2796\u2796\u2796\u2796\u2796\u2796 \n" +
                             "%s",
                             getUsername(update),
                             "сегодня",
                             weatherCodeEmojiLocator.getWeatherEmojiByWeatherCode(weatherInfoByCityName.getWeather()
                                                                                                       .get(0)
                                                                                                       .getId()),
                             geoInfoByCityName.getLocalityName(),
                             weatherInfoByCityName.getWeather()
                                                  .get(0)
                                                  .getDescription(),
                             weatherInfoByCityName.getMain()
                                                  .getTemp(),
                             weatherInfoByCityName.getWind()
                                                  .getSpeed()
                                                  .intValue(),
                             weatherInfoByCityName.getMain()
                                                  .getHumidity(),
                             weatherInfoByCityName.getMain()
                                                  .getPressure(),
                             formattedForecastWeatherInfo);
    }

    private WeatherInfo getWeatherInfoByCoords(Double latitude, Double longitude) {
        return weatherService.getWeatherInfoByCityCoords(latitude, longitude);
    }

    private ForecastWeatherInfo getForecastWeatherInfo(Double latitude, Double longitude) {
        return weatherService.getForecastWeatherInfoByCoords(latitude, longitude);
    }

    /** Get list forecast weather info for each 12 hours from user's time */
    private List<WeatherInfo> getWeatherInfoFor3Days(ForecastWeatherInfo forecastWeatherInfo, Integer userRequestTimestamp) {
        List<WeatherInfo> weatherList = forecastWeatherInfo.getList()
                                                           .stream()
                                                           .filter(weatherInfo -> weatherInfo.getDt() > userRequestTimestamp)
                                                           .collect(Collectors.toList());

        return IntStream.range(0, weatherList.size())
                        .filter(index -> index % 4 == 0)
                        .mapToObj(weatherList::get)
                        .collect(Collectors.toList());
    }

    // TODO: Move to another util-method
    private String getFormattedForecastWeatherInfo(List<WeatherInfo> weatherList) {
        StringBuilder resultStringBuilder = new StringBuilder();

        weatherList.forEach(weatherInfo -> {
            resultStringBuilder.append(String.format("\n%s %s : %s" +
                                                     "\nt: %s C, ветер: %d м/с, давл: %s мм, влажн: %s %%\n",
                                                     weatherCodeEmojiLocator.getWeatherEmojiByWeatherCode(weatherInfo.getWeather()
                                                                                                                     .get(0)
                                                                                                                     .getId()),
                                                     dateFormat.format(new Date(weatherInfo.getDt() * 1000)),
                                                     weatherInfo.getWeather()
                                                                .get(0)
                                                                .getDescription(),
                                                     weatherInfo.getMain()
                                                                .getTemp(),
                                                     weatherInfo.getWind()
                                                                .getSpeed()
                                                                .intValue(),
                                                     weatherInfo.getMain()
                                                                .getPressure(),
                                                     weatherInfo.getMain()
                                                                .getHumidity()
                                                    ));

        });

        return resultStringBuilder.toString();
    }

    private String getUsername(Update update) {
        return update.getMessage()
                     .getChat()
                     .getFirstName();
    }
}
