package com.zaytsevp.weathertelegrambot.service.geocoder;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.zaytsevp.weathertelegrambot.feign.geocoder.YandexGeocoderFeignClient;
import com.zaytsevp.weathertelegrambot.model.geocoder.FeatureMember;
import com.zaytsevp.weathertelegrambot.model.geocoder.LocalityWithCoordsProjection;
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
    private final String locality = "locality";

    /** Для некоторых городов, например Москва - указан данный вид */
    private final String province = "province";

    private final int resultsCount = 10;

    public GeoCoderServiceImpl(YandexGeocoderFeignClient yandexGeocoderFeignClient,
                               @Value("${geocoder.apikey}") String apikey) {
        this.yandexGeocoderFeignClient = yandexGeocoderFeignClient;
        this.apikey = apikey;
    }

    @Override
    public LocalityWithCoordsProjection getGeoCoordsByCityName(String cityName) {
        MainGeoResponse weatherInfoByCityName = yandexGeocoderFeignClient.getLocalityCoordsByName(cityName, apikey, resultsCount, locality);

        List<FeatureMember> featureMembers = weatherInfoByCityName.getResponse()
                                                                  .getGeoObjectCollection()
                                                                  .getFeatureMember();

        if (featureMembers.size() > 0) {
            FeatureMember correctFeatureMember = getCorrectFeatureMember(featureMembers);

            return LocalityWithCoordsProjection.builder()
                                               .localityName(correctFeatureMember.getGeoObject().getName())
                                               .coords(getCoords(correctFeatureMember))
                                               .build();
        }

        throw new IllegalArgumentException("Город не найден!");
    }

    private FeatureMember getCorrectFeatureMember(List<FeatureMember> featureMembers) {
        return featureMembers.stream()
                             .filter(fm -> fm.getGeoObject()
                                             .getMetaDataProperty()
                                             .getGeocoderMetaData()
                                             .getKind()
                                             .equals(locality) ||
                                           fm.getGeoObject()
                                             .getMetaDataProperty()
                                             .getGeocoderMetaData()
                                             .getKind()
                                             .equals(province))
                             .findFirst()
                             .orElse(featureMembers.get(0));
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
