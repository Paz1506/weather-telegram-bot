package com.zaytsevp.weathertelegrambot.service.geocoder;

import java.util.Map;

/**
 * @author Pavel Zaytsev
 */
public interface GeoCoderService {
    Map<String, Double> getGeoCoordsByCityName(String cityName);
}
