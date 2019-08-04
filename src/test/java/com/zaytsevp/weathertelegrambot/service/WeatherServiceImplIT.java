package com.zaytsevp.weathertelegrambot.service;

import com.zaytsevp.weathertelegrambot.model.weather.WeatherInfo;
import com.zaytsevp.weathertelegrambot.service.weather.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceImplIT {

    @Autowired
    private WeatherService weatherService;

    @Test
    public void getWeatherInfoByCityName() {

        WeatherInfo khabarovsk = weatherService.getWeatherInfoByCityName("khabarovsk");
//        System.out.println("!!!!!!!!!!" + khabarovsk);
//
//        System.out.println("!!!!!!!!!!" + khabarovsk.getMain().getTemp());
//        System.out.println("!!!!!!!!!!" + khabarovsk.getWeather().get(0).getMain());
//        System.out.println("!!!!!!!!!!" + khabarovsk.getWeather().get(0).getDescription());
//        System.out.println("!!!!!!!!!!" + khabarovsk.getMain().getHumidity());
//        System.out.println("!!!!!!!!!!" + khabarovsk.getMain().getPressure());
//        System.out.println("!!!!!!!!!!" + khabarovsk.getMain().getTempMax());
//        System.out.println("!!!!!!!!!!" + khabarovsk.getMain().getTempMin());
    }
}