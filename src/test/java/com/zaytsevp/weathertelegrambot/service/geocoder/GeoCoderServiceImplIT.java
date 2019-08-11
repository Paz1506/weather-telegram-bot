package com.zaytsevp.weathertelegrambot.service.geocoder;

import com.zaytsevp.weathertelegrambot.model.geocoder.MainGeoResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.ApiContextInitializer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeoCoderServiceImplIT {

    @Autowired
    private GeoCoderService geoCoderService;

    static {
        ApiContextInitializer.init();
    }

    @Test
    public void getGeoInfoByCityName() {
        //TODO
    }
}