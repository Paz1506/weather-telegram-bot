package com.zaytsevp.weathertelegrambot.service.geocoder;

import com.zaytsevp.weathertelegrambot.model.geocoder.LocalityWithCoordsProjection;

/**
 * @author Pavel Zaytsev
 */
public interface GeoCoderService {
    LocalityWithCoordsProjection getGeoCoordsByCityName(String cityName);
}
