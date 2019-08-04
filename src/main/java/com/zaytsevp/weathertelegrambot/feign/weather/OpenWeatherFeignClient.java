package com.zaytsevp.weathertelegrambot.feign.weather;

import com.zaytsevp.weathertelegrambot.model.weather.WeatherInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Pavel Zaytsev
 */
@FeignClient(name = "api.openweathermap.org", url = "http://api.openweathermap.org/data/2.5/")
public interface OpenWeatherFeignClient {

    @GetMapping(value = "/weather", consumes = "application/json")
    WeatherInfo getWeatherInfoByCityName(@RequestParam(name = "q") String cityName,
                                         @RequestParam(name = "appid") String appId,
                                         @RequestParam(name = "units") String units,
                                         @RequestParam(name = "lang") String lang);
}
