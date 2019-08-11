package com.zaytsevp.weathertelegrambot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.ApiContextInitializer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherTelegramBotApplicationTests {

    static {
        ApiContextInitializer.init();
    }

    @Test
    public void contextLoads() {
    }

}
