package com.zaytsevp.weathertelegrambot.feign.telegram;

import com.zaytsevp.weathertelegrambot.model.weather.WeatherInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Pavel Zaytsev
 */
@FeignClient(name = "api.telegram.org", url = "https://api.telegram.org/bot")
public interface TelegramBotFeignClient {

    @GetMapping(value = "/weather", consumes = "application/json")
    WeatherInfo getWeatherInfoByCityName(@RequestParam(name = "q") String cityName,
                                         @RequestParam(name = "appid") String appId,
                                         @RequestParam(name = "units") String units,
                                         @RequestParam(name = "lang") String lang);
}
