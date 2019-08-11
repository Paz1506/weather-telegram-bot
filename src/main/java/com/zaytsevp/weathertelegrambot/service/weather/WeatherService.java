package com.zaytsevp.weathertelegrambot.service.weather;

import com.zaytsevp.weathertelegrambot.model.weather.WeatherInfo;

/**
 * Сервис для получения информации о погоде
 *
 * @author Pavel Zaytsev
 */
public interface WeatherService {

    WeatherInfo getWeatherInfoByCityName(String cityName);

    WeatherInfo getWeatherInfoByCityCoords(Double lat, Double lng);
}
