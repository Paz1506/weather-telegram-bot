package com.zaytsevp.weathertelegrambot.feign.geocoder;

import com.zaytsevp.weathertelegrambot.model.geocoder.MainGeoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Pavel Zaytsev
 */
@FeignClient(name = "geocode-maps.yandex.ru", url = "https://geocode-maps.yandex.ru/1.x/")
public interface YandexGeocoderFeignClient {

    @GetMapping(value = "?format=json", consumes = "application/json")
    MainGeoResponse getLocalityCoordsByName(@RequestParam(name = "geocode") String address,
                                            @RequestParam(name = "apikey") String apikey,
                                            @RequestParam(name = "results") Integer results,
                                            @RequestParam(name = "kind") String kind);
}
