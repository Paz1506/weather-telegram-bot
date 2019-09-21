package com.zaytsevp.weathertelegrambot.service.weather;

import com.zaytsevp.weathertelegrambot.feign.weather.OpenWeatherFeignClient;
import com.zaytsevp.weathertelegrambot.model.weather.ForecastWeatherInfo;
import com.zaytsevp.weathertelegrambot.model.weather.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Pavel Zaytsev
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private final String appId;

    private final String units;

    private final String lang;

    private final OpenWeatherFeignClient openWeatherFeignClient;

    @Autowired
    public WeatherServiceImpl(@Value("${weather.api.key}") String appId,
                              @Value("${weather.api.units}") String units,
                              @Value("${weather.api.lang}") String lang,
                              OpenWeatherFeignClient openWeatherFeignClient) {
        this.appId = appId;
        this.units = units;
        this.lang = lang;
        this.openWeatherFeignClient = openWeatherFeignClient;
    }


    @Override
    public WeatherInfo getWeatherInfoByCityName(String cityName) {
        return openWeatherFeignClient.getWeatherInfoByCityName(cityName, appId, units, lang);
    }

    @Override
    public WeatherInfo getWeatherInfoByCityCoords(Double lat, Double lng) {
        return openWeatherFeignClient.getWeatherInfoByCoords(lat, lng, appId, units, lang);
    }

    @Override
    public ForecastWeatherInfo getForecastWeatherInfoByCoords(Double lat, Double lng) {
        return openWeatherFeignClient.getForecastWeatherInfoByCoords(lat, lng, appId, units, lang);
    }
}
