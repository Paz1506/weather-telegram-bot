package com.zaytsevp.weathertelegrambot.service.geocoder;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.zaytsevp.weathertelegrambot.feign.geocoder.YandexGeocoderFeignClient;
import com.zaytsevp.weathertelegrambot.model.geocoder.FeatureMember;
import com.zaytsevp.weathertelegrambot.model.geocoder.MainGeoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Pavel Zaytsev
 */
@Service
public class GeoCoderServiceImpl implements GeoCoderService {

    private final YandexGeocoderFeignClient yandexGeocoderFeignClient;

    private final String apikey;

    /** Получаем координаты только для населенных пунктов */
    private final String kind = "locality";

    private final int resultsCount = 1;

    public GeoCoderServiceImpl(YandexGeocoderFeignClient yandexGeocoderFeignClient,
                               @Value("${geocoder.apikey}") String apikey) {
        this.yandexGeocoderFeignClient = yandexGeocoderFeignClient;
        this.apikey = apikey;
    }

    @Override
    public Map<String, Double> getGeoCoordsByCityName(String cityName) {
        MainGeoResponse weatherInfoByCityName = yandexGeocoderFeignClient.getLocalityCoordsByName(cityName, apikey, resultsCount, kind);

        List<FeatureMember> featureMember = weatherInfoByCityName.getResponse()
                                                                 .getGeoObjectCollection()
                                                                 .getFeatureMember();

        if (featureMember.size() > 0) {
            return getCoords(featureMember.get(0));
        }

        throw new IllegalArgumentException("Город не найден!");
    }

    private Map<String, Double> getCoords(FeatureMember featureMember) {
        List<String> stringCoords = Splitter.on(" ")
                                            .splitToList(featureMember.getGeoObject()
                                                                      .getPoint()
                                                                      .getPos());

        return ImmutableMap.of("lon", Double.valueOf(stringCoords.get(0)),
                               "lat", Double.valueOf(stringCoords.get(1)));
    }
}
