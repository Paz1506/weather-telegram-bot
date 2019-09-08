package com.zaytsevp.weathertelegrambot.model.geocoder;

import lombok.*;

import java.util.Map;

/**
 * Проекция информации о найденном населенном пункте
 *
 * @author Pavel Zaytsev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalityWithCoordsProjection {

    /** наименование нас. пункта */
    private String localityName;

    /** информация о долготе/широте */
    private Map<String, Double> coords;
}
