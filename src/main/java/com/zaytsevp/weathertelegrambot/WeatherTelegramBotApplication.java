package com.zaytsevp.weathertelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WeatherTelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherTelegramBotApplication.class, args);

        //FIXME Delete this -- only for tests
    }
}
