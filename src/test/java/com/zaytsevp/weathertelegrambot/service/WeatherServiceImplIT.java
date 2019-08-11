package com.zaytsevp.weathertelegrambot.service;

import com.zaytsevp.weathertelegrambot.model.weather.WeatherInfo;
import com.zaytsevp.weathertelegrambot.service.weather.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.ApiContextInitializer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceImplIT {

    @Autowired
    private WeatherService weatherService;

    static {
        ApiContextInitializer.init();
    }

    @Test
    public void getWeatherInfoByCityName() {
        // TODO
    }
}